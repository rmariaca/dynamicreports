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

package net.sf.dynamicreports.report.builder.chart;

import java.awt.Color;

import net.sf.dynamicreports.report.base.chart.dataset.DRValueDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRMeterPlot;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.MeterShape;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class MeterChartBuilder extends AbstractChartBuilder<MeterChartBuilder> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected MeterChartBuilder() {
		super(ChartType.METER);
	}

	//dataset
	public MeterChartBuilder setValue(ValueColumnBuilder<?, ? extends Number> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setValueExpression(column.getColumn());
		return this;
	}

	public MeterChartBuilder setValue(String fieldName, Class<? extends Number> valueClass) {
		return setValue(DynamicReports.field(fieldName, valueClass));
	}

	public MeterChartBuilder setValue(FieldBuilder<? extends Number> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setValueExpression(field.build());
		return this;
	}

	public MeterChartBuilder setValue(DRIExpression<? extends Number> expression) {
		getDataset().setValueExpression(expression);
		return this;
	}

	//plot
	public void setDataRangeLowExpression(Number dataRangeLowValue) {
		getPlot().setDataRangeLowExpression(Expressions.number(dataRangeLowValue));
	}

	public MeterChartBuilder setDataRangeLowExpression(DRIExpression<? extends Number> dataRangeLowExpression) {
		getPlot().setDataRangeLowExpression(dataRangeLowExpression);
		return this;
	}

	public void setDataRangeHighExpression(Number dataRangeHighValue) {
		getPlot().setDataRangeHighExpression(Expressions.number(dataRangeHighValue));
	}

	public MeterChartBuilder setDataRangeHighExpression(DRIExpression<? extends Number> dataRangeHighExpression) {
		getPlot().setDataRangeHighExpression(dataRangeHighExpression);
		return this;
	}

	public MeterChartBuilder setValueColor(Color valueColor) {
		getPlot().setValueColor(valueColor);
		return this;
	}

	public MeterChartBuilder setValueMask(String valueMask) {
		getPlot().setValueMask(valueMask);
		return this;
	}

	public MeterChartBuilder setValueFont(FontBuilder valueFont) {
		Validate.notNull(valueFont, "valueFont must not be null");
		getPlot().setValueFont(valueFont.build());
		return this;
	}

	public MeterChartBuilder setShape(MeterShape shape) {
		getPlot().setShape(shape);
		return this;
	}

	public MeterChartBuilder intervals(MeterIntervalBuilder ...intervals) {
		return addInterval(intervals);
	}

	public MeterChartBuilder addInterval(MeterIntervalBuilder ...intervals) {
		Validate.notNull(intervals, "intervals must not be null");
		Validate.noNullElements(intervals, "intervals must not contains null interval");
		for (MeterIntervalBuilder interval : intervals) {
			getPlot().addInterval(interval.build());
		}
		return this;
	}

	public MeterChartBuilder setMeterAngle(Integer meterAngle) {
		getPlot().setMeterAngle(meterAngle);
		return this;
	}

	public MeterChartBuilder setUnits(String units) {
		getPlot().setUnits(units);
		return this;
	}

	public MeterChartBuilder setTickInterval(Double tickInterval) {
		getPlot().setTickInterval(tickInterval);
		return this;
	}

	public MeterChartBuilder setMeterBackgroundColor(Color meterBackgroundColor) {
		getPlot().setMeterBackgroundColor(meterBackgroundColor);
		return this;
	}

	public MeterChartBuilder setNeedleColor(Color needleColor) {
		getPlot().setNeedleColor(needleColor);
		return this;
	}

	public MeterChartBuilder setTickColor(Color tickColor) {
		getPlot().setTickColor(tickColor);
		return this;
	}

	public MeterChartBuilder setTickLabelFont(FontBuilder tickLabelFont) {
		Validate.notNull(tickLabelFont, "tickLabelFont must not be null");
		getPlot().setTickLabelFont(tickLabelFont.build());
		return this;
	}

	private DRValueDataset getDataset() {
		return (DRValueDataset) getObject().getDataset();
	}

	private DRMeterPlot getPlot() {
		return (DRMeterPlot) getObject().getPlot();
	}
}
