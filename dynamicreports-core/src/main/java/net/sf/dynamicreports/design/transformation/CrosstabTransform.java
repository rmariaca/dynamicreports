/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
 * http://dynamicreports.sourceforge.net
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.design.transformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstab;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabCell;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabCellContent;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabColumnGroup;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabGroup;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabMeasure;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabRowGroup;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.base.component.DRComponent;
import net.sf.dynamicreports.report.base.component.DRList;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.crosstab.DRCrosstabCellContent;
import net.sf.dynamicreports.report.builder.expression.SystemMessageExpression;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstab;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabCellContent;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabGroup;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISystemExpression;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class CrosstabTransform {
	private DesignTransformAccessor accessor;

	public CrosstabTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
	}

	protected DRDesignCrosstab transform(DRICrosstab crosstab, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstab designCrosstab = new DRDesignCrosstab();
		designCrosstab.setWidth(accessor.getTemplateTransform().getCrosstabWidth(crosstab));
		designCrosstab.setHeight(accessor.getTemplateTransform().getCrosstabHeight(crosstab));
		designCrosstab.setRepeatColumnHeaders(crosstab.isRepeatColumnHeaders());
		designCrosstab.setRepeatRowHeaders(crosstab.isRepeatRowHeaders());
		designCrosstab.setColumnBreakOffset(crosstab.getColumnBreakOffset());
		designCrosstab.setIgnoreWidth(crosstab.getIgnoreWidth());
		designCrosstab.setRunDirection(crosstab.getRunDirection());
		designCrosstab.setWhenNoDataCell(cellContent(crosstab.getWhenNoDataCell(), defaultStyleType, resetType, resetGroup));
		designCrosstab.setHeaderCell(cellContent(crosstab.getHeaderCell(), defaultStyleType, resetType, resetGroup));
		for (DRICrosstabColumnGroup columnGroup : crosstab.getColumnGroups()) {
			addColumnGroup(designCrosstab, columnGroup, defaultStyleType, resetType, resetGroup);
		}
		for (DRICrosstabRowGroup rowGroup : crosstab.getRowGroups()) {
			addRowGroup(designCrosstab, rowGroup, defaultStyleType, resetType, resetGroup);
		}
		addCells(crosstab, designCrosstab, defaultStyleType, resetType, resetGroup);
		for (DRICrosstabMeasure measure : crosstab.getMeasures()) {
			addMeasure(designCrosstab, measure);
		}
		calculateCellDimensions(crosstab, designCrosstab);

		return designCrosstab;
	}

	private DRDesignCrosstabCellContent cellContent(DRICrosstabCellContent cellContent, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabCellContent designCellContents = new DRDesignCrosstabCellContent();
		designCellContents.setList(accessor.getComponentTransform().list(cellContent.getList(), defaultStyleType, resetType, resetGroup));
		return designCellContents;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void group(DRDesignCrosstabGroup designGroup, DRICrosstabGroup group, boolean showTotal, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		designGroup.setName(group.getName());
		designGroup.setOrderType(group.getOrderType());
		designGroup.setExpression(accessor.getExpressionTransform().transformExpression(group.getExpression()));
		designGroup.setOrderByExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(group.getOrderByExpression()));
		designGroup.setComparatorExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(group.getComparatorExpression()));

		DRIExpression valueExpression = new CrosstabExpression(designGroup.getName(), designGroup.getExpression().getValueClass());
		DRTextField textField = new DRTextField();
		textField.setValueExpression(valueExpression);
		designGroup.setHeader(cellContent(textField, defaultStyleType, resetType, resetGroup));
		if (showTotal) {
			textField = new DRTextField();
			DRIExpression<?> totalHeaderExpression = group.getTotalHeaderExpression();
			if (totalHeaderExpression == null) {
				totalHeaderExpression = new SystemMessageExpression("total");
			}
			textField.setValueExpression(totalHeaderExpression);
			designGroup.setTotalHeader(cellContent(textField, defaultStyleType, resetType, resetGroup));
		}
	}

	private void addColumnGroup(DRDesignCrosstab designCrosstab, DRICrosstabColumnGroup columnGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabColumnGroup designColumnGroup = new DRDesignCrosstabColumnGroup();
		boolean showTotal = accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup);
		group(designColumnGroup, columnGroup, showTotal, defaultStyleType, resetType, resetGroup);
		designColumnGroup.setTotalPosition(accessor.getTemplateTransform().getCrosstabColumnGroupTotalPosition(columnGroup));
		designColumnGroup.setPosition(columnGroup.getPosition());
		designCrosstab.getColumnGroups().add(designColumnGroup);
	}

	private void addRowGroup(DRDesignCrosstab designCrosstab, DRICrosstabRowGroup rowGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabRowGroup designRowGroup = new DRDesignCrosstabRowGroup();
		boolean showTotal = accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup);
		group(designRowGroup, rowGroup, showTotal, defaultStyleType, resetType, resetGroup);
		designRowGroup.setTotalPosition(accessor.getTemplateTransform().getCrosstabRowGroupTotalPosition(rowGroup));
		designRowGroup.setPosition(rowGroup.getPosition());
		designCrosstab.getRowGroups().add(designRowGroup);
	}

	private void addCells(DRICrosstab crosstab, DRDesignCrosstab designCrosstab, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabCell designCell = cell(crosstab.getMeasures(), null, null, defaultStyleType, resetType, resetGroup);
		designCrosstab.getCells().add(designCell);

		for (DRICrosstabColumnGroup columnGroup : crosstab.getColumnGroups()) {
			if (accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup)) {
				designCell = cell(crosstab.getMeasures(), null, columnGroup.getName(), defaultStyleType, resetType, resetGroup);
				designCrosstab.getCells().add(designCell);
			}
		}

		for (DRICrosstabRowGroup rowGroup : crosstab.getRowGroups()) {
			if (accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup)) {
				designCell = cell(crosstab.getMeasures(), rowGroup.getName(), null, defaultStyleType, resetType, resetGroup);
				designCrosstab.getCells().add(designCell);

				for (DRICrosstabColumnGroup columnGroup : crosstab.getColumnGroups()) {
					if (accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup)) {
						designCell = cell(crosstab.getMeasures(), rowGroup.getName(), columnGroup.getName(), defaultStyleType, resetType, resetGroup);
						designCrosstab.getCells().add(designCell);
					}
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DRDesignCrosstabCell cell(List<DRICrosstabMeasure> measures, String rowTotalGroup, String columnTotalGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabCell designCell = new DRDesignCrosstabCell();
		designCell.setRowTotalGroup(rowTotalGroup);
		designCell.setColumnTotalGroup(columnTotalGroup);

		DRList list= new DRList();

		for (DRICrosstabMeasure designMeasure : measures) {
			Class<?> valueClass = ReportUtils.getVariableValueClass(designMeasure.getCalculation(), designMeasure.getValueClass());
			DRIExpression valueExpression = new CrosstabExpression(designMeasure.getName(), valueClass);
			DRTextField textField = new DRTextField();
			textField.setValueExpression(valueExpression);
			list.addComponent(textField);
		}

		designCell.setContent(cellContent(list, defaultStyleType, resetType, resetGroup));
		return designCell;
	}

	private DRDesignCrosstabCellContent cellContent(DRComponent component, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRCrosstabCellContent cellContent = new DRCrosstabCellContent();
		cellContent.addComponent(component);
		return cellContent(cellContent, defaultStyleType, resetType, resetGroup);
	}

	private void addMeasure(DRDesignCrosstab designCrosstab, DRICrosstabMeasure measure) throws DRException {
		DRDesignCrosstabMeasure designMeasure = new DRDesignCrosstabMeasure();
		designMeasure.setName(measure.getName());
		designMeasure.setValueExpression(accessor.getExpressionTransform().transformExpression(measure.getValueExpression()));
		designMeasure.setCalculation(measure.getCalculation());
		designMeasure.setPercentageType(measure.getPercentageType());

		designCrosstab.getMeasures().add(designMeasure);
	}

	private void calculateCellDimensions(DRICrosstab crosstab, DRDesignCrosstab designCrosstab) {
		int cellWidth = accessor.getTemplateTransform().getCrosstabCellWidth(crosstab);
		int cellHeight = accessor.getTemplateTransform().getCrosstabCellHeight(crosstab);
		Map<String, GroupCellDimension> columnGroups = new HashMap<String, GroupCellDimension>();
		Map<String, GroupCellDimension> rowGroups = new HashMap<String, GroupCellDimension>();
		int groupWidth = 0;
		int groupHeight = 0;

		GroupCellDimension previousCellDimension = null;
		for (int i = crosstab.getColumnGroups().size() - 1; i >= 0; i--) {
			DRICrosstabColumnGroup columnGroup = crosstab.getColumnGroups().get(i);
			int headerWidth = 0;
			int headerHeight = 0;
			int totalHeaderWidth = 0;
			int totalHeaderHeight = 0;

			if (previousCellDimension == null) {
				headerWidth = cellWidth;
			}
			else {
				headerWidth = previousCellDimension.getHeaderWidth() + previousCellDimension.getTotalHeaderWidth();
			}
			headerHeight = accessor.getTemplateTransform().getCrosstabColumnGroupHeaderHeight(columnGroup);
			if (accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup)) {
				totalHeaderWidth = accessor.getTemplateTransform().getCrosstabColumnGroupTotalHeaderWidth(columnGroup);
				totalHeaderHeight = headerHeight;
				if (previousCellDimension != null) {
					totalHeaderHeight += previousCellDimension.getTotalHeaderHeight();
				}
			}

			GroupCellDimension groupCellDimension = new GroupCellDimension();
			groupCellDimension.setHeaderWidth(headerWidth);
			groupCellDimension.setHeaderHeight(headerHeight);
			groupCellDimension.setTotalHeaderWidth(totalHeaderWidth);
			groupCellDimension.setTotalHeaderHeight(totalHeaderHeight);
			columnGroups.put(columnGroup.getName(), groupCellDimension);
			previousCellDimension = groupCellDimension;

			groupHeight += groupCellDimension.getHeaderHeight();
		}

		previousCellDimension = null;
		for (int i = crosstab.getRowGroups().size() - 1; i >= 0; i--) {
			DRICrosstabRowGroup rowGroup = crosstab.getRowGroups().get(i);
			int headerWidth = 0;
			int headerHeight = 0;
			int totalHeaderWidth = 0;
			int totalHeaderHeight = 0;

			headerWidth = accessor.getTemplateTransform().getCrosstabRowGroupHeaderWidth(rowGroup);
			if (previousCellDimension == null) {
				headerHeight = cellHeight;
			}
			else {
				headerHeight = previousCellDimension.getHeaderHeight() + previousCellDimension.getTotalHeaderHeight();
			}
			if (accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup)) {
				totalHeaderWidth = headerWidth;
				if (previousCellDimension != null) {
					totalHeaderWidth += previousCellDimension.getTotalHeaderWidth();
				}
				totalHeaderHeight = accessor.getTemplateTransform().getCrosstabRowGroupTotalHeaderHeight(rowGroup);
			}

			GroupCellDimension groupCellDimension = new GroupCellDimension();
			groupCellDimension.setHeaderWidth(headerWidth);
			groupCellDimension.setHeaderHeight(headerHeight);
			groupCellDimension.setTotalHeaderWidth(totalHeaderWidth);
			groupCellDimension.setTotalHeaderHeight(totalHeaderHeight);
			rowGroups.put(rowGroup.getName(), groupCellDimension);
			previousCellDimension = groupCellDimension;

			groupWidth += groupCellDimension.getHeaderWidth();
		}

		designCrosstab.getWhenNoDataCell().setWidth(groupWidth);
		designCrosstab.getWhenNoDataCell().setHeight(groupHeight);
		designCrosstab.getHeaderCell().setWidth(groupWidth);
		designCrosstab.getHeaderCell().setHeight(groupHeight);

		for (DRDesignCrosstabColumnGroup designColumnGroup : designCrosstab.getColumnGroups()) {
			GroupCellDimension groupCellDimension = columnGroups.get(designColumnGroup.getName());
			designColumnGroup.setHeight(groupCellDimension.getHeaderHeight());
			designColumnGroup.getHeader().setWidth(groupCellDimension.getHeaderWidth());
			designColumnGroup.getHeader().setHeight(groupCellDimension.getHeaderHeight());
			if (designColumnGroup.getTotalHeader() != null) {
				designColumnGroup.getTotalHeader().setWidth(groupCellDimension.getTotalHeaderWidth());
				designColumnGroup.getTotalHeader().setHeight(groupCellDimension.getTotalHeaderHeight());
			}
		}

		for (DRDesignCrosstabRowGroup designRowGroup : designCrosstab.getRowGroups()) {
			GroupCellDimension groupCellDimension = rowGroups.get(designRowGroup.getName());
			designRowGroup.setWidth(groupCellDimension.getHeaderWidth());
			designRowGroup.getHeader().setWidth(groupCellDimension.getHeaderWidth());
			designRowGroup.getHeader().setHeight(groupCellDimension.getHeaderHeight());
			if (designRowGroup.getTotalHeader() != null) {
				designRowGroup.getTotalHeader().setWidth(groupCellDimension.getTotalHeaderWidth());
				designRowGroup.getTotalHeader().setHeight(groupCellDimension.getTotalHeaderHeight());
			}
		}

		for (DRDesignCrosstabCell designCell : designCrosstab.getCells()) {
			if (designCell.getColumnTotalGroup() == null && designCell.getRowTotalGroup() == null) {
				designCell.getContent().setWidth(cellWidth);
				designCell.getContent().setHeight(cellHeight);
			}
			else if (designCell.getColumnTotalGroup() != null && designCell.getRowTotalGroup() == null) {
				GroupCellDimension groupCellDimension = columnGroups.get(designCell.getColumnTotalGroup());
				designCell.getContent().setWidth(groupCellDimension.getTotalHeaderWidth());
				designCell.getContent().setHeight(cellHeight);
			}
			else if (designCell.getColumnTotalGroup() == null && designCell.getRowTotalGroup() != null) {
				GroupCellDimension groupCellDimension = rowGroups.get(designCell.getRowTotalGroup());
				designCell.getContent().setWidth(cellWidth);
				designCell.getContent().setHeight(groupCellDimension.getTotalHeaderHeight());
			}
			else {
				GroupCellDimension groupCellDimension = columnGroups.get(designCell.getColumnTotalGroup());
				designCell.getContent().setWidth(groupCellDimension.getTotalHeaderWidth());
				groupCellDimension = rowGroups.get(designCell.getRowTotalGroup());
				designCell.getContent().setHeight(groupCellDimension.getTotalHeaderHeight());
			}
		}
	}

	private class GroupCellDimension {
		private int headerWidth;
		private int headerHeight;
		private int totalHeaderWidth;
		private int totalHeaderHeight;

		public int getHeaderWidth() {
			return headerWidth;
		}

		public void setHeaderWidth(int headerWidth) {
			this.headerWidth = headerWidth;
		}

		public int getHeaderHeight() {
			return headerHeight;
		}

		public void setHeaderHeight(int headerHeight) {
			this.headerHeight = headerHeight;
		}

		public int getTotalHeaderWidth() {
			return totalHeaderWidth;
		}

		public void setTotalHeaderWidth(int totalHeaderWidth) {
			this.totalHeaderWidth = totalHeaderWidth;
		}

		public int getTotalHeaderHeight() {
			return totalHeaderHeight;
		}

		public void setTotalHeaderHeight(int totalHeaderHeight) {
			this.totalHeaderHeight = totalHeaderHeight;
		}
	}

	@SuppressWarnings("rawtypes")
	private class CrosstabExpression implements DRISystemExpression {
		private static final long serialVersionUID = 1L;

		private String name;
		private Class<?> valueClass;

		public CrosstabExpression(String name, Class<?> valueClass) {
			this.name = name;
			this.valueClass = valueClass;
		}

		public String getName() {
			return name;
		}

		public Class getValueClass() {
			return valueClass;
		}
	}
}
