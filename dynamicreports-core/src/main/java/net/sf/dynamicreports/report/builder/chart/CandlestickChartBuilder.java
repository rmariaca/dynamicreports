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

package net.sf.dynamicreports.report.builder.chart;

import java.util.Date;

import net.sf.dynamicreports.report.base.chart.dataset.DRHighLowDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRCandlestickPlot;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.VariableBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class CandlestickChartBuilder extends AbstractBaseChartBuilder<CandlestickChartBuilder, DRCandlestickPlot, DRHighLowDataset> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected CandlestickChartBuilder() {
		super(ChartType.CANDLESTICK);
	}

	//dataset
	//series
	public CandlestickChartBuilder setSeries(ValueColumnBuilder<?, String> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setSeriesExpression(column.getColumn());
		return this;
	}

	public CandlestickChartBuilder setSeries(FieldBuilder<String> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setSeriesExpression(field.build());
		return this;
	}

	public CandlestickChartBuilder setSeries(DRIExpression<String> valueExpression) {
		getDataset().setSeriesExpression(valueExpression);
		return this;
	}

	//date
	public CandlestickChartBuilder setDate(ValueColumnBuilder<?, Date> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setDateExpression(column.getColumn());
		return this;
	}

	public CandlestickChartBuilder setDate(FieldBuilder<Date> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setDateExpression(field.build());
		return this;
	}

	public CandlestickChartBuilder setDate(DRIExpression<Date> valueExpression) {
		getDataset().setDateExpression(valueExpression);
		return this;
	}

	//hight
	public CandlestickChartBuilder setHigh(ValueColumnBuilder<?, ? extends Number> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setHighExpression(column.getColumn());
		return this;
	}

	public CandlestickChartBuilder setHigh(FieldBuilder<? extends Number> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setHighExpression(field.build());
		return this;
	}

	public CandlestickChartBuilder setHigh(DRIExpression<? extends Number> valueExpression) {
		getDataset().setHighExpression(valueExpression);
		return this;
	}

	public CandlestickChartBuilder setHigh(VariableBuilder<? extends Number> variable) {
		Validate.notNull(variable, "variable must not be null");
		getDataset().setHighExpression(variable.build());
		return this;
	}

	//low
	public CandlestickChartBuilder setLow(ValueColumnBuilder<?, ? extends Number> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setLowExpression(column.getColumn());
		return this;
	}

	public CandlestickChartBuilder setLow(FieldBuilder<? extends Number> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setLowExpression(field.build());
		return this;
	}

	public CandlestickChartBuilder setLow(DRIExpression<? extends Number> valueExpression) {
		getDataset().setLowExpression(valueExpression);
		return this;
	}

	public CandlestickChartBuilder setLow(VariableBuilder<? extends Number> variable) {
		Validate.notNull(variable, "variable must not be null");
		getDataset().setLowExpression(variable.build());
		return this;
	}

	//open
	public CandlestickChartBuilder setOpen(ValueColumnBuilder<?, ? extends Number> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setOpenExpression(column.getColumn());
		return this;
	}

	public CandlestickChartBuilder setOpen(FieldBuilder<? extends Number> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setOpenExpression(field.build());
		return this;
	}

	public CandlestickChartBuilder setOpen(DRIExpression<? extends Number> valueExpression) {
		getDataset().setOpenExpression(valueExpression);
		return this;
	}

	public CandlestickChartBuilder setOpen(VariableBuilder<? extends Number> variable) {
		Validate.notNull(variable, "variable must not be null");
		getDataset().setOpenExpression(variable.build());
		return this;
	}

	//close
	public CandlestickChartBuilder setClose(ValueColumnBuilder<?, ? extends Number> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setCloseExpression(column.getColumn());
		return this;
	}

	public CandlestickChartBuilder setClose(FieldBuilder<? extends Number> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setCloseExpression(field.build());
		return this;
	}

	public CandlestickChartBuilder setClose(DRIExpression<? extends Number> valueExpression) {
		getDataset().setCloseExpression(valueExpression);
		return this;
	}

	public CandlestickChartBuilder setClose(VariableBuilder<? extends Number> variable) {
		Validate.notNull(variable, "variable must not be null");
		getDataset().setCloseExpression(variable.build());
		return this;
	}

	//volume
	public CandlestickChartBuilder setVolume(ValueColumnBuilder<?, ? extends Number> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setVolumeExpression(column.getColumn());
		return this;
	}

	public CandlestickChartBuilder setVolume(FieldBuilder<? extends Number> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setVolumeExpression(field.build());
		return this;
	}

	public CandlestickChartBuilder setVolume(DRIExpression<? extends Number> valueExpression) {
		getDataset().setVolumeExpression(valueExpression);
		return this;
	}

	public CandlestickChartBuilder setVolume(VariableBuilder<? extends Number> variable) {
		Validate.notNull(variable, "variable must not be null");
		getDataset().setVolumeExpression(variable.build());
		return this;
	}

	//plot
	public CandlestickChartBuilder setTimeAxisFormat(AxisFormatBuilder timeAxisFormat) {
		Validate.notNull(timeAxisFormat, "timeAxisFormat must not be null");
		getPlot().setXAxisFormat(timeAxisFormat.build());
		return this;
	}

	public CandlestickChartBuilder setValueAxisFormat(AxisFormatBuilder valueAxisFormat) {
		Validate.notNull(valueAxisFormat, "valueAxisFormat must not be null");
		getPlot().setYAxisFormat(valueAxisFormat.build());
		return this;
	}

	public CandlestickChartBuilder setShowVolume(Boolean showVolume) {
		getPlot().setShowVolume(showVolume);
		return this;
	}
}
