/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
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

package net.sf.dynamicreports.adhoc.report;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.dynamicreports.adhoc.configuration.AdhocAxisFormat;
import net.sf.dynamicreports.adhoc.configuration.AdhocCalculation;
import net.sf.dynamicreports.adhoc.configuration.AdhocCategoryChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocCategoryChartSerie;
import net.sf.dynamicreports.adhoc.configuration.AdhocCategoryChartType;
import net.sf.dynamicreports.adhoc.configuration.AdhocChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocComponent;
import net.sf.dynamicreports.adhoc.configuration.AdhocFont;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroup;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroupHeaderLayout;
import net.sf.dynamicreports.adhoc.configuration.AdhocHorizontalAlignment;
import net.sf.dynamicreports.adhoc.configuration.AdhocOrderType;
import net.sf.dynamicreports.adhoc.configuration.AdhocOrientation;
import net.sf.dynamicreports.adhoc.configuration.AdhocPage;
import net.sf.dynamicreports.adhoc.configuration.AdhocPageOrientation;
import net.sf.dynamicreports.adhoc.configuration.AdhocPen;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.configuration.AdhocSort;
import net.sf.dynamicreports.adhoc.configuration.AdhocStyle;
import net.sf.dynamicreports.adhoc.configuration.AdhocSubtotal;
import net.sf.dynamicreports.adhoc.configuration.AdhocTextField;
import net.sf.dynamicreports.adhoc.configuration.AdhocVerticalAlignment;
import net.sf.dynamicreports.adhoc.exception.AdhocException;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.MarginBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.SortBuilder;
import net.sf.dynamicreports.report.builder.chart.AbstractCategoryChartBuilder;
import net.sf.dynamicreports.report.builder.chart.AbstractChartBuilder;
import net.sf.dynamicreports.report.builder.chart.AxisFormatBuilder;
import net.sf.dynamicreports.report.builder.chart.CategoryChartSerieBuilder;
import net.sf.dynamicreports.report.builder.chart.Charts;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.DimensionComponentBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.group.GroupBuilder;
import net.sf.dynamicreports.report.builder.group.Groups;
import net.sf.dynamicreports.report.builder.style.BaseStyleBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.PenBuilder;
import net.sf.dynamicreports.report.builder.style.SimpleStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.style.Styles;
import net.sf.dynamicreports.report.builder.subtotal.SubtotalBuilder;
import net.sf.dynamicreports.report.builder.subtotal.Subtotals;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DefaultAdhocReportCustomizer implements AdhocReportCustomizer {
	protected ReportBuilder<?> report;
	protected AdhocReport adhocReport;
	protected Map<String, ColumnBuilder<?, ?>> columns = new LinkedHashMap<String, ColumnBuilder<?, ?>>();
	protected Map<String, GroupBuilder<?>> groups = new LinkedHashMap<String, GroupBuilder<?>>();
	protected Map<String, ComponentBuilder<?, ?>> components = new LinkedHashMap<String, ComponentBuilder<?, ?>>();

	public void customize(ReportBuilder<?> report, AdhocReport adhocReport) throws DRException {
		this.report = report;
		this.adhocReport = adhocReport;
		report.setTextStyle(style(adhocReport.getTextStyle()));
		report.setColumnStyle(style(adhocReport.getColumnStyle()));
		report.setColumnTitleStyle(style(adhocReport.getColumnTitleStyle()));
		report.setGroupStyle(style(adhocReport.getGroupStyle()));
		report.setGroupTitleStyle(style(adhocReport.getGroupTitleStyle()));
		report.setSubtotalStyle(style(adhocReport.getSubtotalStyle()));
		report.setDetailOddRowStyle(simpleStyle(adhocReport.getDetailOddRowStyle()));
		report.setHighlightDetailOddRows(adhocReport.getHighlightDetailOddRows());
		report.setDetailEvenRowStyle(simpleStyle(adhocReport.getDetailEvenRowStyle()));
		report.setHighlightDetailEvenRows(adhocReport.getHighlightDetailEvenRows());
		report.setIgnorePagination(adhocReport.getIgnorePagination());
		report.setTableOfContents(adhocReport.getTableOfContents());
		page(report, adhocReport.getPage());
		if (adhocReport.getPage() != null) {
			report.setIgnorePageWidth(adhocReport.getPage().getIgnorePageWidth());
		}
		for (AdhocColumn adhocColumn : adhocReport.getColumns()) {
			ColumnBuilder<?, ?> column = column(adhocColumn);
			report.addColumn(column);
			columns.put(adhocColumn.getName(), column);
		}
		for (AdhocGroup adhocGroup : adhocReport.getGroups()) {
			GroupBuilder<?> group = group(adhocGroup);
			report.addGroup(group);
			groups.put(adhocGroup.getName(), group);
		}
		for (AdhocSort adhocSort : adhocReport.getSorts()) {
			SortBuilder sort = sort(adhocSort);
			report.addSort(sort);
		}
		for (AdhocComponent adhocComponent : adhocReport.getComponents()) {
			ComponentBuilder<?, ?> component = component(adhocComponent);
			components.put(adhocComponent.getKey(), component);
		}
		addSubtotals();
	}

	protected Class<?> getFieldClass(String name) {
		return Object.class;
	}

	protected String getColumnTitle(String name) {
		return null;
	}

	protected ColumnBuilder<?, ?> column(AdhocColumn adhocColumn) {
		TextColumnBuilder<?> column = Columns.column(adhocColumn.getName(), getFieldClass(adhocColumn.getName()));
		if (adhocColumn.getTitle() != null) {
			column.setTitle(adhocColumn.getTitle());
		}
		else {
			String columnTitle = getColumnTitle(adhocColumn.getName());
			if (columnTitle != null) {
				column.setTitle(columnTitle);
			}
		}
		if (adhocColumn.getWidth() != null) {
			column.setFixedWidth(adhocColumn.getWidth());
		}
		column.setStyle(style(adhocColumn.getStyle()));
		column.setTitleStyle(style(adhocColumn.getTitleStyle()));
		return column;
	}

	protected GroupBuilder<?> group(AdhocGroup adhocGroup) {
		GroupBuilder<?> group;
		ColumnBuilder<?, ?> groupColumn = columns.get(adhocGroup.getName());
		if (groupColumn != null && groupColumn instanceof ValueColumnBuilder<?, ?>) {
			group = Groups.group((ValueColumnBuilder<?, ?>) groupColumn);
		}
		else {
			group = Groups.group(adhocGroup.getName(), getFieldClass(adhocGroup.getName()));
		}
		group.setStartInNewPage(adhocGroup.getStartInNewPage());
		group.setHeaderLayout(groupHeaderLayout(adhocGroup.getHeaderLayout()));
		group.setStyle(style(adhocGroup.getStyle()));
		group.setTitleStyle(style(adhocGroup.getTitleStyle()));
		return group;
	}

	protected GroupHeaderLayout groupHeaderLayout(AdhocGroupHeaderLayout groupHeaderLayout) {
		if (groupHeaderLayout == null) {
			return null;
		}

		switch (groupHeaderLayout) {
		case EMPTY:
			return GroupHeaderLayout.EMPTY;
		case VALUE:
			return GroupHeaderLayout.VALUE;
		case TITLE_AND_VALUE:
			return GroupHeaderLayout.TITLE_AND_VALUE;
		default:
			throw new AdhocException("Group header layout" + groupHeaderLayout.name() + " is not supported");
		}
	}

	protected void addSubtotals() {
		report.subtotalsAtSummary(subtotals());
	}

	protected SubtotalBuilder<?, ?>[] subtotals() {
		SubtotalBuilder<?, ?>[] subtotals = new SubtotalBuilder<?, ?>[adhocReport.getSubtotals().size()];
		int index = 0;
		for (AdhocSubtotal adhocSubtotal : adhocReport.getSubtotals()) {
			SubtotalBuilder<?, ?> subtotal = subtotal(adhocSubtotal);
			subtotals[index++] = subtotal;
		}
		return subtotals;
	}

	protected SubtotalBuilder<?, ?> subtotal(AdhocSubtotal adhocSubtotal) {
		SubtotalBuilder<?, ?> subtotal;
		ColumnBuilder<?, ?> subtotalColumn = columns.get(adhocSubtotal.getName());
		if (subtotalColumn != null && subtotalColumn instanceof ValueColumnBuilder<?, ?>) {
			subtotal = Subtotals.aggregate((ValueColumnBuilder<?, ?>) subtotalColumn, calculation(adhocSubtotal.getCalculation()));
		}
		else {
			subtotal = Subtotals.aggregate(adhocSubtotal.getName(), getFieldClass(adhocSubtotal.getName()), (ValueColumnBuilder<?, ?>) subtotalColumn, calculation(adhocSubtotal.getCalculation()));
		}
		if (adhocSubtotal.getLabel() != null) {
			subtotal.setLabel(adhocSubtotal.getLabel());
		}
		subtotal.setStyle(style(adhocSubtotal.getStyle()));
		subtotal.setLabelStyle(style(adhocSubtotal.getLabelStyle()));
		return subtotal;
	}

	protected Calculation calculation(AdhocCalculation adhocCalculation) {
		if (adhocCalculation == null) {
			return Calculation.NOTHING;
		}

		switch (adhocCalculation) {
		case NOTHING:
			return Calculation.NOTHING;
		case COUNT:
			return Calculation.COUNT;
		case SUM:
			return Calculation.SUM;
		case AVERAGE:
			return Calculation.AVERAGE;
		case LOWEST:
			return Calculation.LOWEST;
		case HIGHEST:
			return Calculation.HIGHEST;
		case STANDARD_DEVIATION:
			return Calculation.STANDARD_DEVIATION;
		case VARIANCE:
			return Calculation.VARIANCE;
		case FIRST:
			return Calculation.FIRST;
		case DISTINCT_COUNT:
			return Calculation.DISTINCT_COUNT;
		default:
			throw new AdhocException("Calculation " + adhocCalculation.name() + " not supported");
		}
	}

	protected SortBuilder sort(AdhocSort adhocSort) {
		SortBuilder sort;
		ColumnBuilder<?, ?> sortColumn = columns.get(adhocSort.getName());
		if (sortColumn != null && sortColumn instanceof TextColumnBuilder<?>) {
			sort = DynamicReports.asc((TextColumnBuilder<?>) sortColumn);
		}
		else {
			sort = DynamicReports.asc(adhocSort.getName(), getFieldClass(adhocSort.getName()));
		}
		sort.setOrderType(orderType(adhocSort.getOrderType()));

		return sort;
	}

	protected OrderType orderType(AdhocOrderType adhocOrderType) {
		if (adhocOrderType == null) {
			return OrderType.ASCENDING;
		}

		switch (adhocOrderType) {
		case ASCENDING:
			return OrderType.ASCENDING;
		case DESCENDING:
			return OrderType.DESCENDING;
		default:
			throw new AdhocException("Order type " + adhocOrderType.name() + " not supported");
		}
	}

	protected BaseStyleBuilder<?, ?> baseStyle(AdhocStyle adhocStyle, BaseStyleBuilder<?, ?> baseStyle) {
		if (adhocStyle.getFont() != null) {
			baseStyle.setFont(font(adhocStyle.getFont()));
		}
		if (adhocStyle.getTopBorder() != null) {
			baseStyle.setTopBorder(pen(adhocStyle.getTopBorder()));
		}
		if (adhocStyle.getLeftBorder() != null) {
			baseStyle.setLeftBorder(pen(adhocStyle.getLeftBorder()));
		}
		if (adhocStyle.getBottomBorder() != null) {
			baseStyle.setBottomBorder(pen(adhocStyle.getBottomBorder()));
		}
		if (adhocStyle.getRightBorder() != null) {
			baseStyle.setRightBorder(pen(adhocStyle.getRightBorder()));
		}
		baseStyle.setForegroundColor(adhocStyle.getForegroundColor());
		baseStyle.setBackgroundColor(adhocStyle.getBackgroundColor());
		baseStyle.setHorizontalAlignment(horizontalAlignment(adhocStyle.getHorizontalAlignment()));
		baseStyle.setVerticalAlignment(verticalAlignment(adhocStyle.getVerticalAlignment()));
		baseStyle.setPattern(adhocStyle.getPattern());

		return baseStyle;
	}

	protected StyleBuilder style(AdhocStyle adhocStyle) {
		if (adhocStyle == null) {
			return null;
		}

		StyleBuilder style= Styles.style();
		baseStyle(adhocStyle, style);
		return style;
	}

	protected SimpleStyleBuilder simpleStyle(AdhocStyle adhocStyle) {
		if (adhocStyle == null) {
			return null;
		}

		SimpleStyleBuilder simpleStyle= Styles.simpleStyle();
		baseStyle(adhocStyle, simpleStyle);
		return simpleStyle;
	}

	protected FontBuilder font(AdhocFont adhocFont) {
		if (adhocFont == null) {
			return null;
		}

		FontBuilder font = Styles.font();
		font.setFontName(adhocFont.getFontName());
		font.setFontSize(adhocFont.getFontSize());
		font.setBold(adhocFont.getBold());
		font.setItalic(adhocFont.getItalic());
		font.setUnderline(adhocFont.getUnderline());
		font.setStrikeThrough(adhocFont.getStrikeThrough());
		return font;
	}

	protected PenBuilder pen(AdhocPen adhocPen) {
		if (adhocPen == null) {
			return null;
		}

		PenBuilder pen = Styles.pen();
		pen.setLineWidth(adhocPen.getLineWidth());
		pen.setLineColor(adhocPen.getLineColor());
		return pen;
	}

	protected HorizontalAlignment horizontalAlignment(AdhocHorizontalAlignment adhocHorizontalAlignment) {
		if (adhocHorizontalAlignment == null) {
			return null;
		}

		switch (adhocHorizontalAlignment) {
		case LEFT:
			return HorizontalAlignment.LEFT;
		case CENTER:
			return HorizontalAlignment.CENTER;
		case RIGHT:
			return HorizontalAlignment.RIGHT;
		case JUSTIFIED:
			return HorizontalAlignment.JUSTIFIED;
		default:
			throw new AdhocException("Horizontal alignment " + adhocHorizontalAlignment.name() + " not supported");
		}
	}

	protected VerticalAlignment verticalAlignment(AdhocVerticalAlignment adhocVerticalAlignment) {
		if (adhocVerticalAlignment == null) {
			return null;
		}

		switch (adhocVerticalAlignment) {
		case TOP:
			return VerticalAlignment.TOP;
		case MIDDLE:
			return VerticalAlignment.MIDDLE;
		case BOTTOM:
			return VerticalAlignment.BOTTOM;
		case JUSTIFIED:
			return VerticalAlignment.JUSTIFIED;
		default:
			throw new AdhocException("Vertical alignment " + adhocVerticalAlignment.name() + " not supported");
		}
	}

	protected void page(ReportBuilder<?> report, AdhocPage adhocPage) {
		if (adhocPage == null) {
			return;
		}

		report.setPageFormat(adhocPage.getWidth(), adhocPage.getHeight(), pageOrientation(adhocPage.getOrientation()));
		MarginBuilder margin = DynamicReports.margin();
		if (adhocPage.getTopMargin() != null) {
			margin.setTop(adhocPage.getTopMargin());
		}
		if (adhocPage.getBottomMargin() != null) {
			margin.setBottom(adhocPage.getBottomMargin());
		}
		if (adhocPage.getLeftMargin() != null) {
			margin.setLeft(adhocPage.getLeftMargin());
		}
		if (adhocPage.getRightMargin() != null) {
			margin.setRight(adhocPage.getRightMargin());
		}
		report.setPageMargin(margin);
		report.setIgnorePageWidth(adhocPage.getIgnorePageWidth());
	}

	protected PageOrientation pageOrientation(AdhocPageOrientation adhocPageOrientation) {
		if (adhocPageOrientation == null) {
			return PageOrientation.PORTRAIT;
		}

		switch (adhocPageOrientation) {
		case PORTRAIT:
			return PageOrientation.PORTRAIT;
		case LANDSCAPE:
			return PageOrientation.LANDSCAPE;
		default:
			throw new AdhocException("Page orientation " + adhocPageOrientation.name() + " not supported");
		}
	}

	protected ComponentBuilder<?, ?> component(AdhocComponent adhocComponent) {
		if (adhocComponent instanceof AdhocTextField) {
			return textField((AdhocTextField) adhocComponent);
		}
		if (adhocComponent instanceof AdhocCategoryChart) {
			return categoryChart((AdhocCategoryChart) adhocComponent);
		}
		throw new AdhocException("Component " + adhocComponent.getClass().getName() + " not supported");
	}

	protected void component(AdhocComponent adhocComponent, ComponentBuilder<?, ?> component) {
		component.setStyle(style(adhocComponent.getStyle()));
		if (component instanceof DimensionComponentBuilder) {
			if (adhocComponent.getWidth() != null) {
				((DimensionComponentBuilder<?, ?>) component).setFixedWidth(adhocComponent.getWidth());
			}
			if (adhocComponent.getHeight() != null) {
				((DimensionComponentBuilder<?, ?>) component).setFixedHeight(adhocComponent.getHeight());
			}
		}
	}

	protected TextFieldBuilder<?> textField(AdhocTextField adhocTextField) {
		TextFieldBuilder<String> textField = Components.text(adhocTextField.getText());
		component(adhocTextField, textField);
		return textField;
	}

	protected void chart(AdhocChart adhocChart, AbstractChartBuilder<?> chart) {
		component(adhocChart, chart);
		if (adhocChart.getTitle() != null) {
			chart.setTitle(adhocChart.getTitle());
		}
		if (adhocChart.getTitleFont() != null) {
			chart.setTitleFont(font(adhocChart.getTitleFont()));
		}
		chart.setTitleColor(adhocChart.getTitleColor());
		chart.setShowLegend(adhocChart.getShowLegend());
	}

	protected Orientation orientation(AdhocOrientation adhocOrientation) {
		if (adhocOrientation == null) {
			return null;
		}

		switch (adhocOrientation) {
		case HORIZONTAL:
			return Orientation.HORIZONTAL;
		case VERTICAL:
			return Orientation.VERTICAL;
		default:
			throw new AdhocException("Orientation " + adhocOrientation.name() + " not supported");
		}
	}

	protected AxisFormatBuilder axisFormat(AdhocAxisFormat adhocAxisFormat) {
		if (adhocAxisFormat == null) {
			return null;
		}

		AxisFormatBuilder axisFormat = Charts.axisFormat();
		if (adhocAxisFormat.getLabel() != null) {
			axisFormat.setLabel(adhocAxisFormat.getLabel());
		}
		axisFormat.setLabelFont(font(adhocAxisFormat.getLabelFont()));
		axisFormat.setLabelColor(adhocAxisFormat.getLabelColor());
		return axisFormat;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected AbstractCategoryChartBuilder<?, ?> categoryChart(AdhocCategoryChart adhocCategoryChart) {
		AbstractCategoryChartBuilder<?, ?> categoryChart = categoryChart(adhocCategoryChart.getType());
		chart(adhocCategoryChart, categoryChart);
		ColumnBuilder valueColumn = columns.get(adhocCategoryChart.getCategory());
		if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
			categoryChart.setCategory((ValueColumnBuilder) valueColumn);
		}
		else {
			Class fieldClass = getFieldClass(adhocCategoryChart.getCategory());
			categoryChart.setCategory(adhocCategoryChart.getCategory(), fieldClass);
		}
		if (adhocCategoryChart.getSeries() != null && !adhocCategoryChart.getSeries().isEmpty()) {
			for (AdhocCategoryChartSerie adhocCategoryChartSerie : adhocCategoryChart.getSeries()) {
				categoryChart.addSerie(categoryChartSerie(adhocCategoryChartSerie));
			}
		}
		if (adhocCategoryChart.getSeriesColors() != null && !adhocCategoryChart.getSeriesColors().isEmpty()) {
			for (Color adhocSeriesColor : adhocCategoryChart.getSeriesColors()) {
				categoryChart.addSeriesColor(adhocSeriesColor);
			}
		}
		AxisFormatBuilder categoryAxisFormat = axisFormat(adhocCategoryChart.getCategoryAxisFormat());
		if (categoryAxisFormat != null) {
			categoryChart.setCategoryAxisFormat(categoryAxisFormat);
		}
		AxisFormatBuilder valueAxisFormat = axisFormat(adhocCategoryChart.getValueAxisFormat());
		if (valueAxisFormat != null) {
			categoryChart.setValueAxisFormat(valueAxisFormat);
		}
		categoryChart.setOrientation(orientation(adhocCategoryChart.getOrientation()));
		categoryChart.setUseSeriesAsCategory(adhocCategoryChart.getUseSeriesAsCategory());

		return categoryChart;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected CategoryChartSerieBuilder categoryChartSerie(AdhocCategoryChartSerie adhocCategoryChartSerie) {
		CategoryChartSerieBuilder categoryChartSerie;
		ColumnBuilder valueColumn = columns.get(adhocCategoryChartSerie.getValue());
		if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
			categoryChartSerie = Charts.serie((ValueColumnBuilder) valueColumn);
		}
		else {
			Class fieldClass = getFieldClass(adhocCategoryChartSerie.getValue());
			categoryChartSerie = Charts.serie(adhocCategoryChartSerie.getValue(), fieldClass);
		}
		if (adhocCategoryChartSerie.getSeries() != null) {
			ColumnBuilder<?, ?> seriesColumn = columns.get(adhocCategoryChartSerie.getSeries());
			if (seriesColumn != null && seriesColumn instanceof ValueColumnBuilder<?, ?>) {
				categoryChartSerie.setSeries((ValueColumnBuilder<?, ?>) seriesColumn);
			}
			else {
				categoryChartSerie.setSeries(adhocCategoryChartSerie.getSeries(), getFieldClass(adhocCategoryChartSerie.getSeries()));
			}
		}
		if (adhocCategoryChartSerie.getLabel() != null) {
			categoryChartSerie.setLabel(adhocCategoryChartSerie.getLabel());
		}
		return categoryChartSerie;
	}

	protected AbstractCategoryChartBuilder<?, ?> categoryChart(AdhocCategoryChartType adhocCategoryChartType) {
		if (adhocCategoryChartType == null) {
			return Charts.barChart();
		}

		switch (adhocCategoryChartType) {
		case AREA:
			return Charts.areaChart();
		case STACKEDAREA:
			return Charts.stackedAreaChart();
		case BAR:
			return Charts.barChart();
		case STACKEDBAR:
			return Charts.stackedBarChart();
		case BAR3D:
			return Charts.bar3DChart();
		case STACKEDBAR3D:
			return Charts.stackedBar3DChart();
		case LINE:
			return Charts.lineChart();
		case LAYEREDBAR:
			return Charts.layeredBarChart();
		default:
			throw new AdhocException("Category chart type " + adhocCategoryChartType.name() + " not supported");
		}
	}
}
