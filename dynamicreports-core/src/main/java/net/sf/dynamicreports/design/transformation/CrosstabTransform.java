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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.component.DRDesignFiller;
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
import net.sf.dynamicreports.report.base.component.DRFiller;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.crosstab.DRCrosstabCellContent;
import net.sf.dynamicreports.report.base.crosstab.DRCrosstabCellStyle;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.base.style.DRConditionalStyle;
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
import net.sf.dynamicreports.report.definition.style.DRIConditionalStyle;
import net.sf.dynamicreports.report.definition.style.DRISimpleStyle;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
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
			addColumnGroup(crosstab, designCrosstab, columnGroup, defaultStyleType, resetType, resetGroup);
		}
		for (DRICrosstabRowGroup<?> rowGroup : crosstab.getRowGroups()) {
			addRowGroup(crosstab, designCrosstab, rowGroup, defaultStyleType, resetType, resetGroup);
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

	private CrosstabRowCount addRowCountExpression(DRDesignCrosstab designCrosstab) throws DRException {
		DRDesignCrosstabRowGroup lastRowGroup = designCrosstab.getRowGroups().get(designCrosstab.getRowGroups().size() - 1);
		DRFiller filler = new DRFiller();
		CrosstabRowCount rowCountExpression = new CrosstabRowCount();
		filler.setPrintWhenExpression(rowCountExpression);
		DRDesignFiller designTextField = accessor.getComponentTransform().filler(filler);
		lastRowGroup.getHeader().getList().addComponent(designTextField);
		return rowCountExpression;
	}

	private DRDesignCrosstabCellContent cellContent(DRICrosstabCellContent cellContent, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabCellContent designCellContents = new DRDesignCrosstabCellContent();
		designCellContents.setList(accessor.getComponentTransform().list(cellContent.getList(), defaultStyleType, resetType, resetGroup));
		designCellContents.setStyle(accessor.getStyleTransform().transformStyle(cellContent.getStyle(), false, DefaultStyleType.NONE));
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
		DRDesignCrosstabCellContent header = createCellContent(null, defaultStyleType, resetType, resetGroup);
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
			DRDesignCrosstabCellContent totalHeader = createCellContent(null, defaultStyleType, resetType, resetGroup);
			designTextField = accessor.getComponentTransform().textField(textField, DefaultStyleType.TEXT);
			designTextField.setUniqueName("group_" + designGroup.getName() + ".totalheader");
			totalHeader.getList().addComponent(designTextField);
			designGroup.setTotalHeader(totalHeader);
		}
	}

	private void addColumnGroup(DRICrosstab crosstab, DRDesignCrosstab designCrosstab, DRICrosstabColumnGroup<?> columnGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabColumnGroup designColumnGroup = new DRDesignCrosstabColumnGroup();
		boolean showTotal = accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup);
		group(designColumnGroup, crosstab, columnGroup, showTotal, defaultStyleType, resetType, resetGroup);
		designColumnGroup.setTotalPosition(accessor.getTemplateTransform().getCrosstabColumnGroupTotalPosition(columnGroup));
		designCrosstab.getColumnGroups().add(designColumnGroup);
	}

	private void addRowGroup(DRICrosstab crosstab, DRDesignCrosstab designCrosstab, DRICrosstabRowGroup<?> rowGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstabRowGroup designRowGroup = new DRDesignCrosstabRowGroup();
		boolean showTotal = accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup);
		group(designRowGroup, crosstab, rowGroup, showTotal, defaultStyleType, resetType, resetGroup);
		designRowGroup.setTotalPosition(accessor.getTemplateTransform().getCrosstabRowGroupTotalPosition(rowGroup));
		designCrosstab.getRowGroups().add(designRowGroup);
	}

	private void addCells(DRICrosstab crosstab, DRDesignCrosstab designCrosstab, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		Map<DRICrosstabMeasure<?>, List<DRICrosstabCellStyle>> measuresStyle = measuresStyle(crosstab);

		DRIStyle cellStyle = getCellStyle(crosstab, designCrosstab);
		DRDesignCrosstabCell designCell = cell(crosstab, cellStyle, measuresStyle, null, null, defaultStyleType, resetType, resetGroup);
		designCrosstab.getCells().add(designCell);

		for (DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups()) {
			if (accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup)) {
				designCell = cell(crosstab, cellStyle, measuresStyle, null, columnGroup, defaultStyleType, resetType, resetGroup);
				designCrosstab.getCells().add(designCell);
			}
		}

		for (DRICrosstabRowGroup<?> rowGroup : crosstab.getRowGroups()) {
			if (accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup)) {
				designCell = cell(crosstab, cellStyle, measuresStyle, rowGroup, null, defaultStyleType, resetType, resetGroup);
				designCrosstab.getCells().add(designCell);

				for (DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups()) {
					if (accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup)) {
						designCell = cell(crosstab, cellStyle, measuresStyle, rowGroup, columnGroup, defaultStyleType, resetType, resetGroup);
						designCrosstab.getCells().add(designCell);
					}
				}
			}
		}
	}

	private DRIStyle getCellStyle(DRICrosstab crosstab, DRDesignCrosstab designCrosstab) throws DRException {
		if (designCrosstab.getRowGroups().isEmpty()) {
			return null;
		}

		DRStyle cellStyle = null;
		DRISimpleStyle detailOddRowStyle = accessor.getTemplateTransform().getCrosstabOddRowStyle(crosstab);
		DRISimpleStyle detailEvenRowStyle = accessor.getTemplateTransform().getCrosstabEvenRowStyle(crosstab);
		if (detailOddRowStyle != null || detailEvenRowStyle != null) {
			CrosstabRowCount rowCountExpression = addRowCountExpression(designCrosstab);
			List<DRIConditionalStyle> rowHighlighters = new ArrayList<DRIConditionalStyle>();
			if (detailOddRowStyle != null) {
				rowHighlighters.add(detailRowConditionalStyle(detailOddRowStyle, new CrosstabPrintInOddRow(rowCountExpression)));
			}
			if (detailEvenRowStyle != null) {
				rowHighlighters.add(detailRowConditionalStyle(detailEvenRowStyle, new CrosstabPrintInEvenRow(rowCountExpression)));
			}
			if (!rowHighlighters.isEmpty()) {
				cellStyle = new DRStyle();
				for (DRIConditionalStyle conditionalStyle : rowHighlighters) {
					cellStyle.addConditionalStyle((DRConditionalStyle) conditionalStyle);
				}
			}
		}
		return cellStyle;
	}

	private Map<DRICrosstabMeasure<?>, List<DRICrosstabCellStyle>> measuresStyle(DRICrosstab crosstab) {
		Map<DRICrosstabMeasure<?>, List<DRICrosstabCellStyle>> measuresStyle = new HashMap<DRICrosstabMeasure<?>, List<DRICrosstabCellStyle>>();

		for (DRICrosstabMeasure<?> measure : crosstab.getMeasures()) {
			if (!(measure instanceof DRICrosstabMeasureCell<?>)) {
				continue;
			}
			DRICrosstabMeasureCell<?> measureCell = (DRICrosstabMeasureCell<?>) measure;
			List<DRICrosstabCellStyle> styles = new ArrayList<DRICrosstabCellStyle>();
			measuresStyle.put(measure, styles);

			for (DRICrosstabCellStyle cellStyle : measureCell.getStyles()) {
				DRStyle newStyle = cellStyle(crosstab, cellStyle.getStyle());
				styles.add(new DRCrosstabCellStyle(newStyle, cellStyle.getRowGroup(), cellStyle.getColumnGroup()));
			}
		}

		return measuresStyle;
	}

	private DRStyle cellStyle(DRICrosstab crosstab, DRIStyle style) {
		if (!style.getConditionalStyles().isEmpty()) {
			DRStyle newStyle = new DRStyle();
			if (style.getParentStyle() != null) {
				newStyle.setParentStyle(cellStyle(crosstab, style.getParentStyle()));
			}
			accessor.getStyleTransform().copyStyle(newStyle, style);
			for (DRIConditionalStyle conditionalStyle : style.getConditionalStyles()) {
				CrosstabExpression<Boolean> conditionalStyleExpression = new CrosstabExpression<Boolean>(crosstab, conditionalStyle.getConditionExpression());
				DRConditionalStyle newConditionalStyle = new DRConditionalStyle(conditionalStyleExpression);
				accessor.getStyleTransform().copyStyle(newConditionalStyle, conditionalStyle);
				newStyle.addConditionalStyle(newConditionalStyle);
			}
			return newStyle;
		}
		return (DRStyle) style;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DRDesignCrosstabCell cell(DRICrosstab crosstab, DRIStyle cellStyle, Map<DRICrosstabMeasure<?>, List<DRICrosstabCellStyle>> measuresStyle, DRICrosstabRowGroup<?> rowGroup, DRICrosstabColumnGroup<?> columnGroup, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
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
			textField.setStyle(getCellStyle(measuresStyle.get(measure), rowGroup, columnGroup));
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

		DRDesignCrosstabCellContent content = createCellContent(cellStyle, defaultStyleType, resetType, resetGroup);
		designCell.setContent(content);
		content.getList().addComponent(designList);

		return designCell;
	}

	private DRConditionalStyle detailRowConditionalStyle(DRISimpleStyle style, DRISimpleExpression<Boolean> expression) {
		DRConditionalStyle conditionalStyle = new DRConditionalStyle(expression);
		accessor.getStyleTransform().copyStyle(conditionalStyle, style);
		return conditionalStyle;
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

	private DRDesignCrosstabCellContent createCellContent(DRIStyle style, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRCrosstabCellContent cellContent = new DRCrosstabCellContent();
		cellContent.setStyle(style);
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

	private class CrosstabExpression<T> extends AbstractComplexExpression<T> {
		private static final long serialVersionUID = 1L;

		private DRIExpression<T> expression;

		public CrosstabExpression(DRICrosstab crosstab, DRIExpression<T> expression) {
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
		public T evaluate(List<?> values, ReportParameters reportParameters) {
			DRIReportScriptlet scriptlet = (DRIReportScriptlet) reportParameters.getValue(DRIReportScriptlet.NAME);
			for (int i = 0; i < getExpressions().size(); i++) {
				scriptlet.setSystemValue(getExpressions().get(i).getName(), values.get(i));
			}
			if (expression instanceof DRIExpression) {
				return ((DRISimpleExpression<T>) expression).evaluate(reportParameters);
			}
			return null;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Class getValueClass() {
			return expression.getValueClass();
		}
	}

	private class CrosstabRowCount extends AbstractSimpleExpression<Boolean> {
		private static final long serialVersionUID = 1L;

		private int rowCount = 0;

		public Boolean evaluate(ReportParameters reportParameters) {
			rowCount++;
			return false;
		}
	}

	private class CrosstabPrintInOddRow extends AbstractSimpleExpression<Boolean> {
		private static final long serialVersionUID = 1L;

		private CrosstabRowCount crosstabRowCount;

		private CrosstabPrintInOddRow(CrosstabRowCount crosstabRowCount) {
			this.crosstabRowCount = crosstabRowCount;
		}

		public Boolean evaluate(ReportParameters reportParameters) {
			return crosstabRowCount.rowCount % 2 == 0;
		}
	}

	private class CrosstabPrintInEvenRow extends AbstractSimpleExpression<Boolean> {
		private static final long serialVersionUID = 1L;

		private CrosstabRowCount crosstabRowCount;

		private CrosstabPrintInEvenRow(CrosstabRowCount crosstabRowCount) {
			this.crosstabRowCount = crosstabRowCount;
		}

		public Boolean evaluate(ReportParameters reportParameters) {
			return crosstabRowCount.rowCount % 2 != 0;
		}
	}
}
