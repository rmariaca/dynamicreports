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

package net.sf.dynamicreports.report.builder.chart;

import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class ChartBuilders {

	public AxisFormatBuilder axisFormat() {
		return Charts.axisFormat();
	}

	public ChartSerieBuilder serie(ColumnBuilder<?, ? extends Number> column) {
		return Charts.serie(column);
	}

	public ChartSerieBuilder serie(String fieldName, Class<? extends Number> valueClass) {
		return Charts.serie(fieldName, valueClass);
	}
	
	public ChartSerieBuilder serie(FieldBuilder<? extends Number> field) {
		return Charts.serie(field);
	}
	
	public ChartSerieBuilder serie(DRISimpleExpression<? extends Number> valueExpression) {
		return Charts.serie(valueExpression);
	}
	
	public AreaChartBuilder areaChart() {
		return Charts.areaChart();
	}

	public StackedAreaChartBuilder stackedAreaChart() {
		return Charts.stackedAreaChart();
	}
	
	public BarChartBuilder barChart() {
		return Charts.barChart();
	}
	
	public StackedBarChartBuilder stackedBarChart() {
		return Charts.stackedBarChart();
	}
	
	public Bar3DChartBuilder bar3DChart() {
		return Charts.bar3DChart();
	}
	
	public StackedBar3DChartBuilder stackedBar3DChart() {
		return Charts.stackedBar3DChart();
	}
	
	public LineChartBuilder lineChart() {
		return Charts.lineChart();
	}
	
	public PieChartBuilder pieChart() {
		return Charts.pieChart();
	}
	
	public Pie3DChartBuilder pie3DChart() {
		return Charts.pie3DChart();
	}
	
	public TimeSeriesChartBuilder timeSeriesChart() {
		return Charts.timeSeriesChart();
	}
	
	public XyAreaChartBuilder xyAreaChart() {
		return Charts.xyAreaChart();
	}
	
	public XyBarChartBuilder xyBarChart() {
		return Charts.xyBarChart();
	}
	
	public XyLineChartBuilder xyLineChart() {
		return Charts.xyLineChart();
	}
	
	public ScatterChartBuilder scatterChart() {
		return Charts.scatterChart();
	}
}
