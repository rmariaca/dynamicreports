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
import net.sf.dynamicreports.adhoc.configuration.AdhocChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocChartSerie;
import net.sf.dynamicreports.adhoc.configuration.AdhocChartType;
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
import net.sf.dynamicreports.adhoc.configuration.AdhocProperties;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.configuration.AdhocSort;
import net.sf.dynamicreports.adhoc.configuration.AdhocStyle;
import net.sf.dynamicreports.adhoc.configuration.AdhocSubtotal;
import net.sf.dynamicreports.adhoc.configuration.AdhocTextField;
import net.sf.dynamicreports.adhoc.configuration.AdhocVerticalAlignment;
import net.sf.dynamicreports.adhoc.exception.AdhocException;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.MarginBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.SortBuilder;
import net.sf.dynamicreports.report.builder.chart.AbstractBaseChartBuilder;
import net.sf.dynamicreports.report.builder.chart.AbstractCategoryChartBuilder;
import net.sf.dynamicreports.report.builder.chart.AbstractChartBuilder;
import net.sf.dynamicreports.report.builder.chart.AbstractChartSerieBuilder;
import net.sf.dynamicreports.report.builder.chart.AbstractPieChartBuilder;
import net.sf.dynamicreports.report.builder.chart.AbstractTimeSeriesChartBuilder;
import net.sf.dynamicreports.report.builder.chart.AbstractXyChartBuilder;
import net.sf.dynamicreports.report.builder.chart.AreaChartBuilder;
import net.sf.dynamicreports.report.builder.chart.AxisFormatBuilder;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.BarChartBuilder;
import net.sf.dynamicreports.report.builder.chart.BubbleChartBuilder;
import net.sf.dynamicreports.report.builder.chart.CategoryChartSerieBuilder;
import net.sf.dynamicreports.report.builder.chart.Charts;
import net.sf.dynamicreports.report.builder.chart.DifferenceChartBuilder;
import net.sf.dynamicreports.report.builder.chart.LayeredBarChartBuilder;
import net.sf.dynamicreports.report.builder.chart.LineChartBuilder;
import net.sf.dynamicreports.report.builder.chart.Pie3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.PieChartBuilder;
import net.sf.dynamicreports.report.builder.chart.ScatterChartBuilder;
import net.sf.dynamicreports.report.builder.chart.SpiderChartBuilder;
import net.sf.dynamicreports.report.builder.chart.StackedAreaChartBuilder;
import net.sf.dynamicreports.report.builder.chart.StackedBar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.StackedBarChartBuilder;
import net.sf.dynamicreports.report.builder.chart.TimeSeriesChartBuilder;
import net.sf.dynamicreports.report.builder.chart.XyAreaChartBuilder;
import net.sf.dynamicreports.report.builder.chart.XyBarChartBuilder;
import net.sf.dynamicreports.report.builder.chart.XyChartSerieBuilder;
import net.sf.dynamicreports.report.builder.chart.XyLineChartBuilder;
import net.sf.dynamicreports.report.builder.chart.XyzChartSerieBuilder;
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
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
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
		addComponents();
	}

	protected String getColumnTitle(String name) {
		return null;
	}

	protected DRIDataType<?, ?> getFieldType(String name) {
		return null;
	}

	protected FieldBuilder<?> field(String name) {
		DRIDataType<?, ?> type = getFieldType(name);
		if (type != null) {
			return DynamicReports.field(name, type);
		}
		return DynamicReports.field(name, Object.class);
	}

	protected ColumnBuilder<?, ?> column(AdhocColumn adhocColumn) {
		TextColumnBuilder<?> column = Columns.column(field(adhocColumn.getName()));
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
			group = Groups.group(field(adhocGroup.getName()));
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
			subtotal = Subtotals.aggregate(field(adhocSubtotal.getName()), (ValueColumnBuilder<?, ?>) subtotalColumn, calculation(adhocSubtotal.getCalculation()));
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
			sort = DynamicReports.asc(field(adhocSort.getName()));
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

	protected void addComponents() {
	}

	protected ComponentBuilder<?, ?> component(AdhocComponent adhocComponent) {
		if (adhocComponent instanceof AdhocTextField) {
			return textField((AdhocTextField) adhocComponent);
		}
		if (adhocComponent instanceof AdhocChart) {
			return chart((AdhocChart) adhocComponent);
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

	protected AbstractChartBuilder<?> chart(AdhocChart adhocChart) {
		AdhocChartType type = adhocChart.getType();
		if (type == null) {
			type = AdhocChartType.BAR;
		}

		switch (type) {
		case AREA:
			return areaChart(adhocChart);
		case STACKEDAREA:
			return stackedAreaChart(adhocChart);
		case BAR:
			return barChart(adhocChart);
		case STACKEDBAR:
			return stackedBarChart(adhocChart);
		case BAR3D:
			return bar3DChart(adhocChart);
		case STACKEDBAR3D:
			return stackedBar3DChart(adhocChart);
		case LINE:
			return lineChart(adhocChart);
		case LAYEREDBAR:
			return layeredBarChart(adhocChart);
		case DIFFERENCE:
			return differenceChart(adhocChart);
		case PIE:
			return pieChart(adhocChart);
		case PIE3D:
			return pie3DChart(adhocChart);
		case SCATTER:
			return scatterChart(adhocChart);
		case SPIDER:
			return spiderChart(adhocChart);
		case TIMESERIES:
			return timeSeriesChart(adhocChart);
		case XYAREA:
			return xyAreaChart(adhocChart);
		case XYBAR:
			return xyBarChart(adhocChart);
		case XYLINE:
			return xyLineChart(adhocChart);
		case BUBBLE:
			return bubbleChart(adhocChart);
		default:
			throw new AdhocException("Chart type " + type.name() + " not supported");
		}
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

	protected void baseChart(AdhocChart adhocChart, AbstractBaseChartBuilder<?, ?, ?> baseChart) {
		chart(adhocChart, baseChart);
		if (adhocChart.getSeriesColors() != null && !adhocChart.getSeriesColors().isEmpty()) {
			for (Color adhocSeriesColor : adhocChart.getSeriesColors()) {
				baseChart.addSeriesColor(adhocSeriesColor);
			}
		}
		baseChart.setOrientation(orientation(adhocChart.getOrientation()));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void categoryChart(AdhocChart adhocChart, AbstractCategoryChartBuilder<?, ?> categoryChart) {
		baseChart(adhocChart, categoryChart);
		ColumnBuilder valueColumn = columns.get(adhocChart.getXValue());
		if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
			categoryChart.setCategory((ValueColumnBuilder) valueColumn);
		}
		else {
			FieldBuilder field = field(adhocChart.getXValue());
			categoryChart.setCategory(field);
		}
		if (adhocChart.getSeries() != null && !adhocChart.getSeries().isEmpty()) {
			for (AdhocChartSerie adhocChartSerie : adhocChart.getSeries()) {
				categoryChart.addSerie(categoryChartSerie(adhocChartSerie));
			}
		}
		AxisFormatBuilder categoryAxisFormat = axisFormat(adhocChart.getXAxisFormat());
		if (categoryAxisFormat != null) {
			categoryChart.setCategoryAxisFormat(categoryAxisFormat);
		}
		AxisFormatBuilder valueAxisFormat = axisFormat(adhocChart.getYAxisFormat());
		if (valueAxisFormat != null) {
			categoryChart.setValueAxisFormat(valueAxisFormat);
		}
		Boolean useSeriesAsCategory = adhocChart.getProperty(AdhocProperties.CHART_USE_SERIES_AS_CATEGORY);
		categoryChart.setUseSeriesAsCategory(useSeriesAsCategory);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void timeSeriesChart(AdhocChart adhocChart, AbstractTimeSeriesChartBuilder<?, ?> timeSeriesChart) {
		baseChart(adhocChart, timeSeriesChart);
		ColumnBuilder valueColumn = columns.get(adhocChart.getXValue());
		if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
			timeSeriesChart.setTimePeriod((ValueColumnBuilder) valueColumn);
		}
		else {
			FieldBuilder field = field(adhocChart.getXValue());
			timeSeriesChart.setTimePeriod(field);
		}
		if (adhocChart.getSeries() != null && !adhocChart.getSeries().isEmpty()) {
			for (AdhocChartSerie adhocChartSerie : adhocChart.getSeries()) {
				timeSeriesChart.addSerie(categoryChartSerie(adhocChartSerie));
			}
		}
		AxisFormatBuilder timeAxisFormat = axisFormat(adhocChart.getXAxisFormat());
		if (timeAxisFormat != null) {
			timeSeriesChart.setTimeAxisFormat(timeAxisFormat);
		}
		AxisFormatBuilder valueAxisFormat = axisFormat(adhocChart.getYAxisFormat());
		if (valueAxisFormat != null) {
			timeSeriesChart.setValueAxisFormat(valueAxisFormat);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void pieChart(AdhocChart adhocChart, AbstractPieChartBuilder<?, ?> pieChart) {
		baseChart(adhocChart, pieChart);
		ColumnBuilder valueColumn = columns.get(adhocChart.getXValue());
		if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
			pieChart.setKey((ValueColumnBuilder) valueColumn);
		}
		else {
			FieldBuilder field = field(adhocChart.getXValue());
			pieChart.setKey(field);
		}
		if (adhocChart.getSeries() != null && !adhocChart.getSeries().isEmpty()) {
			for (AdhocChartSerie adhocChartSerie : adhocChart.getSeries()) {
				pieChart.addSerie(categoryChartSerie(adhocChartSerie));
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void xyChart(AdhocChart adhocChart, AbstractXyChartBuilder<?, ?> xyChart) {
		baseChart(adhocChart, xyChart);
		ColumnBuilder valueColumn = columns.get(adhocChart.getXValue());
		if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
			xyChart.setXValue((ValueColumnBuilder) valueColumn);
		}
		else {
			FieldBuilder field = field(adhocChart.getXValue());
			xyChart.setXValue(field);
		}
		if (adhocChart.getSeries() != null && !adhocChart.getSeries().isEmpty()) {
			for (AdhocChartSerie adhocChartSerie : adhocChart.getSeries()) {
				xyChart.addSerie(xyChartSerie(adhocChartSerie));
			}
		}
		AxisFormatBuilder xAxisFormat = axisFormat(adhocChart.getXAxisFormat());
		if (xAxisFormat != null) {
			xyChart.setXAxisFormat(xAxisFormat);
		}
		AxisFormatBuilder yAxisFormat = axisFormat(adhocChart.getYAxisFormat());
		if (yAxisFormat != null) {
			xyChart.setYAxisFormat(yAxisFormat);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void spiderChart(AdhocChart adhocChart, SpiderChartBuilder spiderChart) {
		chart(adhocChart, spiderChart);
		ColumnBuilder valueColumn = columns.get(adhocChart.getXValue());
		if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
			spiderChart.setCategory((ValueColumnBuilder) valueColumn);
		}
		else {
			FieldBuilder field = field(adhocChart.getXValue());
			spiderChart.setCategory(field);
		}
		if (adhocChart.getSeries() != null && !adhocChart.getSeries().isEmpty()) {
			for (AdhocChartSerie adhocChartSerie : adhocChart.getSeries()) {
				spiderChart.addSerie(categoryChartSerie(adhocChartSerie));
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void bubbleChart(AdhocChart adhocChart, BubbleChartBuilder bubbleChart) {
		baseChart(adhocChart, bubbleChart);
		ColumnBuilder valueColumn = columns.get(adhocChart.getXValue());
		if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
			bubbleChart.setXValue((ValueColumnBuilder) valueColumn);
		}
		else {
			FieldBuilder field = field(adhocChart.getXValue());
			bubbleChart.setXValue(field);
		}
		if (adhocChart.getSeries() != null && !adhocChart.getSeries().isEmpty()) {
			for (AdhocChartSerie adhocChartSerie : adhocChart.getSeries()) {
				bubbleChart.addSerie(xyzChartSerie(adhocChartSerie));
			}
		}
		AxisFormatBuilder xAxisFormat = axisFormat(adhocChart.getXAxisFormat());
		if (xAxisFormat != null) {
			bubbleChart.setXAxisFormat(xAxisFormat);
		}
		AxisFormatBuilder yAxisFormat = axisFormat(adhocChart.getYAxisFormat());
		if (yAxisFormat != null) {
			bubbleChart.setYAxisFormat(yAxisFormat);
		}
	}

	protected AreaChartBuilder areaChart(AdhocChart adhocChart) {
		AreaChartBuilder areaChart = Charts.areaChart();
		categoryChart(adhocChart, areaChart);
		return areaChart;
	}

	protected StackedAreaChartBuilder stackedAreaChart(AdhocChart adhocChart) {
		StackedAreaChartBuilder stackedAreaChart = Charts.stackedAreaChart();
		categoryChart(adhocChart, stackedAreaChart);
		return stackedAreaChart;
	}

	protected BarChartBuilder barChart(AdhocChart adhocChart) {
		BarChartBuilder barChart = Charts.barChart();
		categoryChart(adhocChart, barChart);
		return barChart;
	}

	protected StackedBarChartBuilder stackedBarChart(AdhocChart adhocChart) {
		StackedBarChartBuilder stackedBarChart = Charts.stackedBarChart();
		categoryChart(adhocChart, stackedBarChart);
		return stackedBarChart;
	}

	protected Bar3DChartBuilder bar3DChart(AdhocChart adhocChart) {
		Bar3DChartBuilder bar3DChart = Charts.bar3DChart();
		categoryChart(adhocChart, bar3DChart);
		return bar3DChart;
	}

	protected StackedBar3DChartBuilder stackedBar3DChart(AdhocChart adhocChart) {
		StackedBar3DChartBuilder stackedBar3DChart = Charts.stackedBar3DChart();
		categoryChart(adhocChart, stackedBar3DChart);
		return stackedBar3DChart;
	}

	protected LineChartBuilder lineChart(AdhocChart adhocChart) {
		LineChartBuilder lineChart = Charts.lineChart();
		categoryChart(adhocChart, lineChart);
		return lineChart;
	}

	protected LayeredBarChartBuilder layeredBarChart(AdhocChart adhocChart) {
		LayeredBarChartBuilder layeredChart = Charts.layeredBarChart();
		categoryChart(adhocChart, layeredChart);
		return layeredChart;
	}

	protected DifferenceChartBuilder differenceChart(AdhocChart adhocChart) {
		DifferenceChartBuilder differenceChart = Charts.differenceChart();
		timeSeriesChart(adhocChart, differenceChart);
		return differenceChart;
	}

	protected PieChartBuilder pieChart(AdhocChart adhocChart) {
		PieChartBuilder pieChart = Charts.pieChart();
		pieChart(adhocChart, pieChart);
		return pieChart;
	}

	protected Pie3DChartBuilder pie3DChart(AdhocChart adhocChart) {
		Pie3DChartBuilder pie3DChart = Charts.pie3DChart();
		pieChart(adhocChart, pie3DChart);
		return pie3DChart;
	}

	protected ScatterChartBuilder scatterChart(AdhocChart adhocChart) {
		ScatterChartBuilder scatterChart = Charts.scatterChart();
		xyChart(adhocChart, scatterChart);
		return scatterChart;
	}

	protected SpiderChartBuilder spiderChart(AdhocChart adhocChart) {
		SpiderChartBuilder spiderChart = Charts.spiderChart();
		spiderChart(adhocChart, spiderChart);
		return spiderChart;
	}

	protected TimeSeriesChartBuilder timeSeriesChart(AdhocChart adhocChart) {
		TimeSeriesChartBuilder timeSeriesChart = Charts.timeSeriesChart();
		timeSeriesChart(adhocChart, timeSeriesChart);
		return timeSeriesChart;
	}

	protected XyAreaChartBuilder xyAreaChart(AdhocChart adhocChart) {
		XyAreaChartBuilder xyAreaChart = Charts.xyAreaChart();
		xyChart(adhocChart, xyAreaChart);
		return xyAreaChart;
	}

	protected XyBarChartBuilder xyBarChart(AdhocChart adhocChart) {
		XyBarChartBuilder xyBarChart = Charts.xyBarChart();
		xyChart(adhocChart, xyBarChart);
		return xyBarChart;
	}

	protected XyLineChartBuilder xyLineChart(AdhocChart adhocChart) {
		XyLineChartBuilder xyLineChart = Charts.xyLineChart();
		xyChart(adhocChart, xyLineChart);
		return xyLineChart;
	}

	protected BubbleChartBuilder bubbleChart(AdhocChart adhocChart) {
		BubbleChartBuilder bubbleChart = Charts.bubbleChart();
		bubbleChart(adhocChart, bubbleChart);
		return bubbleChart;
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

	protected void chartSerie(AdhocChartSerie adhocChartSerie, AbstractChartSerieBuilder<?, ?> chartSerie) {
		if (adhocChartSerie.getSeries() != null) {
			ColumnBuilder<?, ?> seriesColumn = columns.get(adhocChartSerie.getSeries());
			if (seriesColumn != null && seriesColumn instanceof ValueColumnBuilder<?, ?>) {
				chartSerie.setSeries((ValueColumnBuilder<?, ?>) seriesColumn);
			}
			else {
				chartSerie.setSeries(field(adhocChartSerie.getSeries()));
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected CategoryChartSerieBuilder categoryChartSerie(AdhocChartSerie adhocChartSerie) {
		CategoryChartSerieBuilder categoryChartSerie;
		ColumnBuilder valueColumn = columns.get(adhocChartSerie.getYValue());
		if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
			categoryChartSerie = Charts.serie((ValueColumnBuilder) valueColumn);
		}
		else {
			FieldBuilder field = field(adhocChartSerie.getYValue());
			categoryChartSerie = Charts.serie(field);
		}
		chartSerie(adhocChartSerie, categoryChartSerie);
		if (adhocChartSerie.getLabel() != null) {
			categoryChartSerie.setLabel(adhocChartSerie.getLabel());
		}
		return categoryChartSerie;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected XyChartSerieBuilder xyChartSerie(AdhocChartSerie adhocChartSerie) {
		XyChartSerieBuilder xyChartSerie;
		ColumnBuilder valueColumn = columns.get(adhocChartSerie.getYValue());
		if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
			xyChartSerie = Charts.xySerie((ValueColumnBuilder) valueColumn);
		}
		else {
			FieldBuilder field = field(adhocChartSerie.getYValue());
			xyChartSerie = Charts.xySerie(field);
		}
		chartSerie(adhocChartSerie, xyChartSerie);
		if (adhocChartSerie.getXValue() != null) {
			valueColumn = columns.get(adhocChartSerie.getXValue());
			if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
				xyChartSerie.setXValue((ValueColumnBuilder) valueColumn);
			}
			else {
				FieldBuilder field = field(adhocChartSerie.getXValue());
				xyChartSerie.setXValue(field);
			}
		}
		if (adhocChartSerie.getLabel() != null) {
			xyChartSerie.setLabel(adhocChartSerie.getLabel());
		}
		return xyChartSerie;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected XyzChartSerieBuilder xyzChartSerie(AdhocChartSerie adhocChartSerie) {
		XyzChartSerieBuilder xyzChartSerie = Charts.xyzSerie();
		chartSerie(adhocChartSerie, xyzChartSerie);
		if (adhocChartSerie.getXValue() != null) {
			ColumnBuilder valueColumn = columns.get(adhocChartSerie.getXValue());
			if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
				xyzChartSerie.setXValue((ValueColumnBuilder) valueColumn);
			}
			else {
				FieldBuilder field = field(adhocChartSerie.getXValue());
				xyzChartSerie.setXValue(field);
			}
		}
		if (adhocChartSerie.getYValue() != null) {
			ColumnBuilder valueColumn = columns.get(adhocChartSerie.getYValue());
			if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
				xyzChartSerie.setYValue((ValueColumnBuilder) valueColumn);
			}
			else {
				FieldBuilder field = field(adhocChartSerie.getYValue());
				xyzChartSerie.setYValue(field);
			}
		}
		if (adhocChartSerie.getZValue() != null) {
			ColumnBuilder valueColumn = columns.get(adhocChartSerie.getZValue());
			if (valueColumn != null && valueColumn instanceof ValueColumnBuilder) {
				xyzChartSerie.setZValue((ValueColumnBuilder) valueColumn);
			}
			else {
				FieldBuilder field = field(adhocChartSerie.getZValue());
				xyzChartSerie.setZValue(field);
			}
		}
		return xyzChartSerie;
	}
}
