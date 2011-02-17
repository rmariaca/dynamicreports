/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
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
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.crosstab.DRCrosstabCellContent;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.builder.expression.SystemMessageExpression;
import net.sf.dynamicreports.report.definition.DRIReportScriptlet;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstab;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabCellContent;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabCellStyle;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabGroup;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabMeasureCell;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabMeasureVariable;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
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
		DRDesignCrosstabCellContent whenNoDataCell = cellContent(crosstab.getWhenNoDataCell(), defaultStyleType, resetType, resetGroup);
		designCrosstab.setWidth(accessor.getTemplateTransform().getCrosstabWidth(crosstab));
		designCrosstab.setHeight(accessor.getTemplateTransform().getCrosstabHeight(crosstab, whenNoDataCell));
		designCrosstab.setRepeatColumnHeaders(crosstab.isRepeatColumnHeaders());
		designCrosstab.setRepeatRowHeaders(crosstab.isRepeatRowHeaders());
		designCrosstab.setColumnBreakOffset(crosstab.getColumnBreakOffset());
		designCrosstab.setIgnoreWidth(crosstab.getIgnoreWidth());
		designCrosstab.setRunDirection(crosstab.getRunDirection());
		designCrosstab.setWhenNoDataCell(whenNoDataCell);
		designCrosstab.setHeaderCell(cellContent(crosstab.getHeaderCell(), defaultStyleType, resetType, resetGroup));
		for (DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups()) {
			addColumnGroup(designCrosstab, crosstab, columnGroup, defaultStyleType, resetType, resetGroup);
		}
		for (DRICrosstabRowGroup<?> rowGroup : crosstab.getRowGroups()) {
			addRowGroup(designCrosstab, crosstab, rowGroup, defaultStyleType, resetType, resetGroup);
		}
		addCells(crosstab, designCrosstab, defaultStyleType, resetType, resetGroup);
		for (DRICrosstabMeasure<?> measure : crosstab.getMeasures()) {
			if (measure instanceof DRICrosstabMeasureVariable<?>) {
				addMeasure(designCrosstab, (DRICrosstabMeasureVariable<?>) measure);
			}
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
	private void group(DRDesignCrosstabGroup designGroup, DRICrosstab crosstab, DRICrosstabGroup group, boolean showTotal, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		designGroup.setName(group.getName());
		designGroup.setOrderType(group.getOrderType());

		designGroup.setExpression(accessor.getExpressionTransform().transformExpression(group.getExpression()));
		if (group.getOrderByExpression() != null) {
			CrosstabExpression orderByExpression = new CrosstabExpression(crosstab, group.getOrderByExpression());
			designGroup.setOrderByExpression(accessor.getExpressionTransform().transformExpression(orderByExpression));
		}
		if (group.getComparatorExpression() != null) {
			CrosstabExpression comparatorExpression = new CrosstabExpression(crosstab, group.getComparatorExpression());
			designGroup.setComparatorExpression(accessor.getExpressionTransform().transformExpression(comparatorExpression));
		}

		DRTextField textField = new DRTextField();
		textField.setValueExpression(group);
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

	private void addColumnGroup(DRDesignCrosstab designCrosstab, DRICrosstab crosstab, DRICrosstabColumnGroup<?> columnGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabColumnGroup designColumnGroup = new DRDesignCrosstabColumnGroup();
		boolean showTotal = accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup);
		group(designColumnGroup, crosstab, columnGroup, showTotal, defaultStyleType, resetType, resetGroup);
		designColumnGroup.setTotalPosition(accessor.getTemplateTransform().getCrosstabColumnGroupTotalPosition(columnGroup));
		designCrosstab.getColumnGroups().add(designColumnGroup);
	}

	private void addRowGroup(DRDesignCrosstab designCrosstab, DRICrosstab crosstab, DRICrosstabRowGroup<?> rowGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabRowGroup designRowGroup = new DRDesignCrosstabRowGroup();
		boolean showTotal = accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup);
		group(designRowGroup, crosstab, rowGroup, showTotal, defaultStyleType, resetType, resetGroup);
		designRowGroup.setTotalPosition(accessor.getTemplateTransform().getCrosstabRowGroupTotalPosition(rowGroup));
		designCrosstab.getRowGroups().add(designRowGroup);
	}

	private void addCells(DRICrosstab crosstab, DRDesignCrosstab designCrosstab, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabCell designCell = cell(crosstab, null, null, defaultStyleType, resetType, resetGroup);
		designCrosstab.getCells().add(designCell);

		for (DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups()) {
			if (accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup)) {
				designCell = cell(crosstab, null, columnGroup, defaultStyleType, resetType, resetGroup);
				designCrosstab.getCells().add(designCell);
			}
		}

		for (DRICrosstabRowGroup<?> rowGroup : crosstab.getRowGroups()) {
			if (accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup)) {
				designCell = cell(crosstab, rowGroup, null, defaultStyleType, resetType, resetGroup);
				designCrosstab.getCells().add(designCell);

				for (DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups()) {
					if (accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup)) {
						designCell = cell(crosstab, rowGroup, columnGroup, defaultStyleType, resetType, resetGroup);
						designCrosstab.getCells().add(designCell);
					}
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DRDesignCrosstabCell cell(DRICrosstab crosstab, DRICrosstabRowGroup<?> rowGroup, DRICrosstabColumnGroup<?> columnGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabCell designCell = new DRDesignCrosstabCell();
		String rowTotalGroup = null;
		String columnTotalGroup = null;
		if (rowGroup != null) {
			rowTotalGroup = rowGroup.getName();
		}
		if (columnGroup != null) {
			columnTotalGroup = columnGroup.getName();
		}
		designCell.setRowTotalGroup(rowTotalGroup);
		designCell.setColumnTotalGroup(columnTotalGroup);

		DRDesignList designList = new DRDesignList();
		for (DRICrosstabMeasure<?> measure : crosstab.getMeasures()) {
			if (!(measure instanceof DRICrosstabMeasureCell<?>)) {
				continue;
			}
			DRICrosstabMeasureCell<?> measureCell = (DRICrosstabMeasureCell<?>) measure;
			DRTextField textField = new DRTextField();

			if (measureCell instanceof DRICrosstabMeasureVariable<?>) {
				textField.setValueExpression((DRICrosstabMeasureVariable) measure);
			}
			else {
				CrosstabExpression valueExpression = new CrosstabExpression(crosstab, measureCell.getCellExpression());
				textField.setValueExpression(valueExpression);
			}

			textField.setPattern(measureCell.getPattern());
			textField.setHorizontalAlignment(measureCell.getHorizontalAlignment());
			textField.setValueFormatter(measureCell.getValueFormatter());
			textField.setDataType(measureCell.getDataType());
			textField.setStretchWithOverflow(measureCell.getStretchWithOverflow());
			textField.setStyle(getCellStyle(measureCell.getStyles(), rowGroup, columnGroup));
			DRDesignTextField designTextField = accessor.getComponentTransform().textField(textField, DefaultStyleType.TEXT);
			String name = "cell_measure[" + measureCell.getName() + "]";
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

	private DRStyle getCellStyle(List<DRICrosstabCellStyle> styles, DRICrosstabRowGroup<?> rowGroup, DRICrosstabColumnGroup<?> columnGroup) {
		for (DRICrosstabCellStyle crosstabCellStyle : styles) {
			if (equalsGroup(rowGroup, crosstabCellStyle.getRowGroup()) &&
					equalsGroup(columnGroup, crosstabCellStyle.getColumnGroup())) {
				return (DRStyle) crosstabCellStyle.getStyle();
			}
		}
		for (DRICrosstabCellStyle crosstabCellStyle : styles) {
			if (crosstabCellStyle.getRowGroup() == null && crosstabCellStyle.getColumnGroup() == null) {
				return (DRStyle) crosstabCellStyle.getStyle();
			}
		}
		return null;
	}

	private boolean equalsGroup(DRICrosstabGroup<?> group1, DRICrosstabGroup<?> group2) {
		if (group1 == null && group2 == null) {
			return true;
		}
		if (group1 != null && group2 != null) {
			return group1.equals(group2);
		}
		return false;
	}

	private DRDesignCrosstabCellContent cellContent(DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRCrosstabCellContent cellContent = new DRCrosstabCellContent();
		return cellContent(cellContent, defaultStyleType, resetType, resetGroup);
	}

	private void addMeasure(DRDesignCrosstab designCrosstab, DRICrosstabMeasureVariable<?> measure) throws DRException {
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

	private class CrosstabExpression extends AbstractComplexExpression<Object> {
		private static final long serialVersionUID = 1L;

		private DRISimpleExpression<?> expression;

		public CrosstabExpression(DRICrosstab crosstab, DRISimpleExpression<?> expression) {
			this.expression = expression;
			for (DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups()) {
				addExpression(columnGroup);
			}
			for (DRICrosstabRowGroup<?> rowGroup : crosstab.getRowGroups()) {
				addExpression(rowGroup);
			}
			for (DRICrosstabMeasure<?> measure : crosstab.getMeasures()) {
				if (measure instanceof DRICrosstabMeasureVariable<?>) {
					addExpression((DRICrosstabMeasureVariable<?>) measure);
				}
			}
		}

		@Override
		public Object evaluate(List<?> values, ReportParameters reportParameters) {
			DRIReportScriptlet scriptlet = (DRIReportScriptlet) reportParameters.getValue(DRIReportScriptlet.NAME);
			for (int i = 0; i < getExpressions().size(); i++) {
				scriptlet.setSystemValue(getExpressions().get(i).getName(), values.get(i));
			}
			return expression.evaluate(reportParameters);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Class getValueClass() {
			return expression.getValueClass();
		}
	}
}
