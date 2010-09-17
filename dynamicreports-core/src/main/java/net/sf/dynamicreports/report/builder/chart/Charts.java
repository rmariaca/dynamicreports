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

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class Charts {

	public static AxisFormatBuilder axisFormat() {
		return new AxisFormatBuilder();
	}

	public static ChartSerieBuilder serie(ValueColumnBuilder<?, ? extends Number> column) {
		return new ChartSerieBuilder(column);
	}

	public static ChartSerieBuilder serie(String fieldName, Class<? extends Number> valueClass) {
		return serie(DynamicReports.field(fieldName, valueClass));
	}
	
	public static ChartSerieBuilder serie(FieldBuilder<? extends Number> field) {
		return new ChartSerieBuilder(field);
	}
	
	public static ChartSerieBuilder serie(DRISimpleExpression<? extends Number> valueExpression) {
		return new ChartSerieBuilder(valueExpression);
	}
	
	public static AreaChartBuilder areaChart() {
		return new AreaChartBuilder();
	}

	public static StackedAreaChartBuilder stackedAreaChart() {
		return new StackedAreaChartBuilder();
	}
	
	public static BarChartBuilder barChart() {
		return new BarChartBuilder();
	}
	
	public static StackedBarChartBuilder stackedBarChart() {
		return new StackedBarChartBuilder();
	}
	
	public static Bar3DChartBuilder bar3DChart() {
		return new Bar3DChartBuilder();
	}
	
	public static StackedBar3DChartBuilder stackedBar3DChart() {
		return new StackedBar3DChartBuilder();
	}
	
	public static LineChartBuilder lineChart() {
		return new LineChartBuilder();
	}
	
	public static PieChartBuilder pieChart() {
		return new PieChartBuilder();
	}
	
	public static Pie3DChartBuilder pie3DChart() {
		return new Pie3DChartBuilder();
	}
	
	public static TimeSeriesChartBuilder timeSeriesChart() {
		return new TimeSeriesChartBuilder();
	}
	
	public static XyAreaChartBuilder xyAreaChart() {
		return new XyAreaChartBuilder();
	}
	
	public static XyBarChartBuilder xyBarChart() {
		return new XyBarChartBuilder();
	}
	
	public static XyLineChartBuilder xyLineChart() {
		return new XyLineChartBuilder();
	}
	
	public static ScatterChartBuilder scatterChart() {
		return new ScatterChartBuilder();
	}
}
