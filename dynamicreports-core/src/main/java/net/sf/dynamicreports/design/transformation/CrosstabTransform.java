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
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
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
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.crosstab.DRCrosstabCellContent;
import net.sf.dynamicreports.report.base.style.DRStyle;
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
	private Map<DRDesignCrosstab, DRICrosstab> crosstabs;

	public CrosstabTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
		init();
	}

	private void init() {
		crosstabs = new HashMap<DRDesignCrosstab, DRICrosstab>();
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
		for (DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups()) {
			addColumnGroup(designCrosstab, columnGroup, defaultStyleType, resetType, resetGroup);
		}
		for (DRICrosstabRowGroup<?> rowGroup : crosstab.getRowGroups()) {
			addRowGroup(designCrosstab, rowGroup, defaultStyleType, resetType, resetGroup);
		}
		addCells(crosstab, designCrosstab, defaultStyleType, resetType, resetGroup);
		for (DRICrosstabMeasure<?> measure : crosstab.getMeasures()) {
			addMeasure(designCrosstab, measure);
		}
		crosstabs.put(designCrosstab, crosstab);

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
		textField.setPattern(group.getHeaderPattern());
		textField.setHorizontalAlignment(group.getHeaderHorizontalAlignment());
		textField.setValueFormatter(group.getHeaderValueFormatter());
		textField.setDataType(group.getDataType());
		textField.setStretchWithOverflow(group.getHeaderStretchWithOverflow());
		textField.setStyle((DRStyle) group.getHeaderStyle());
		DRDesignCrosstabCellContent header = cellContent(defaultStyleType, resetType, resetGroup);
		DRDesignTextField designTextField = accessor.getComponentTransform().textField(textField, DefaultStyleType.TEXT);
		designTextField.setUniqueName("group_" + designGroup.getName() + ".header");
		header.getList().addComponent(designTextField);
		designGroup.setHeader(header);
		if (showTotal) {
			textField = new DRTextField();
			DRIExpression<?> totalHeaderExpression = group.getTotalHeaderExpression();
			if (totalHeaderExpression == null) {
				totalHeaderExpression = new SystemMessageExpression("total");
			}
			textField.setValueExpression(totalHeaderExpression);
			textField.setStyle((DRStyle) group.getTotalHeaderStyle());
			DRDesignCrosstabCellContent totalHeader = cellContent(defaultStyleType, resetType, resetGroup);
			designTextField = accessor.getComponentTransform().textField(textField, DefaultStyleType.TEXT);
			designTextField.setUniqueName("group_" + designGroup.getName() + ".totalheader");
			totalHeader.getList().addComponent(designTextField);
			designGroup.setTotalHeader(totalHeader);
		}
	}

	private void addColumnGroup(DRDesignCrosstab designCrosstab, DRICrosstabColumnGroup<?> columnGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabColumnGroup designColumnGroup = new DRDesignCrosstabColumnGroup();
		boolean showTotal = accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup);
		group(designColumnGroup, columnGroup, showTotal, defaultStyleType, resetType, resetGroup);
		designColumnGroup.setTotalPosition(accessor.getTemplateTransform().getCrosstabColumnGroupTotalPosition(columnGroup));
		designColumnGroup.setPosition(columnGroup.getPosition());
		designCrosstab.getColumnGroups().add(designColumnGroup);
	}

	private void addRowGroup(DRDesignCrosstab designCrosstab, DRICrosstabRowGroup<?> rowGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
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

		for (DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups()) {
			if (accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup)) {
				designCell = cell(crosstab.getMeasures(), null, columnGroup.getName(), defaultStyleType, resetType, resetGroup);
				designCrosstab.getCells().add(designCell);
			}
		}

		for (DRICrosstabRowGroup<?> rowGroup : crosstab.getRowGroups()) {
			if (accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup)) {
				designCell = cell(crosstab.getMeasures(), rowGroup.getName(), null, defaultStyleType, resetType, resetGroup);
				designCrosstab.getCells().add(designCell);

				for (DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups()) {
					if (accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup)) {
						designCell = cell(crosstab.getMeasures(), rowGroup.getName(), columnGroup.getName(), defaultStyleType, resetType, resetGroup);
						designCrosstab.getCells().add(designCell);
					}
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DRDesignCrosstabCell cell(List<DRICrosstabMeasure<?>> measures, String rowTotalGroup, String columnTotalGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabCell designCell = new DRDesignCrosstabCell();
		designCell.setRowTotalGroup(rowTotalGroup);
		designCell.setColumnTotalGroup(columnTotalGroup);

		DRDesignList designList = new DRDesignList();
		for (DRICrosstabMeasure<?> measure : measures) {
			Class<?> valueClass = ReportUtils.getVariableValueClass(measure.getCalculation(), measure.getValueClass());
			DRIExpression valueExpression = new CrosstabExpression(measure.getName(), valueClass);
			DRTextField textField = new DRTextField();
			textField.setValueExpression(valueExpression);
			textField.setPattern(measure.getPattern());
			textField.setHorizontalAlignment(measure.getHorizontalAlignment());
			textField.setValueFormatter(measure.getValueFormatter());
			textField.setDataType(measure.getDataType());
			textField.setStretchWithOverflow(measure.getStretchWithOverflow());
			textField.setStyle((DRStyle) measure.getStyle());
			DRDesignTextField designTextField = accessor.getComponentTransform().textField(textField, DefaultStyleType.TEXT);
			String name = "cell_measure[" + measure.getName() + "]";
			if (rowTotalGroup != null) {
				name += "_rowgroup[" + rowTotalGroup + "]";
			}
			if (columnTotalGroup != null) {
				name += "_columngroup[" + columnTotalGroup + "]";
			}
			designTextField.setUniqueName(name);
			designList.addComponent(designTextField);
		}

		DRDesignCrosstabCellContent content = cellContent(defaultStyleType, resetType, resetGroup);
		designCell.setContent(content);
		content.getList().addComponent(designList);

		return designCell;
	}

	private DRDesignCrosstabCellContent cellContent(DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRCrosstabCellContent cellContent = new DRCrosstabCellContent();
		return cellContent(cellContent, defaultStyleType, resetType, resetGroup);
	}

	private void addMeasure(DRDesignCrosstab designCrosstab, DRICrosstabMeasure<?> measure) throws DRException {
		DRDesignCrosstabMeasure designMeasure = new DRDesignCrosstabMeasure();
		designMeasure.setName(measure.getName());
		designMeasure.setValueExpression(accessor.getExpressionTransform().transformExpression(measure.getValueExpression()));
		designMeasure.setCalculation(measure.getCalculation());
		designMeasure.setPercentageType(measure.getPercentageType());

		designCrosstab.getMeasures().add(designMeasure);
	}

	protected DRICrosstab getCrosstab(DRDesignCrosstab designCrosstab) {
		return crosstabs.get(designCrosstab);
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
