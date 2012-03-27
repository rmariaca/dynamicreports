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
import java.util.Date;

import net.sf.dynamicreports.report.base.chart.dataset.DRTimeSeriesDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRDifferencePlot;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DifferenceChartBuilder extends AbstractBaseChartBuilder<DifferenceChartBuilder, DRDifferencePlot, DRTimeSeriesDataset> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected DifferenceChartBuilder() {
		super(ChartType.DIFFERENCE);
	}

	//dataset
	public DifferenceChartBuilder setTimePeriod(ValueColumnBuilder<?, ? extends Date> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setValueExpression(column.getColumn());
		return this;
	}

	public DifferenceChartBuilder setTimePeriod(String fieldName, Class<? extends Date> valueClass) {
		return setTimePeriod(DynamicReports.field(fieldName, valueClass));
	}

	public DifferenceChartBuilder setTimePeriod(FieldBuilder<? extends Date> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setValueExpression(field.build());
		return this;
	}

	public DifferenceChartBuilder setTimePeriod(DRIExpression<? extends Date> expression) {
		getDataset().setValueExpression(expression);
		return this;
	}

	public DifferenceChartBuilder series(CategoryChartSerieBuilder ...chartSeries) {
		return addSerie(chartSeries);
	}

	public DifferenceChartBuilder addSerie(CategoryChartSerieBuilder ...chartSeries) {
		Validate.notNull(chartSeries, "chartSeries must not be null");
		Validate.noNullElements(chartSeries, "chartSeries must not contains null chartSerie");
		for (CategoryChartSerieBuilder chartSerie : chartSeries) {
			getDataset().addSerie(chartSerie.build());
		}
		return this;
	}

	public DifferenceChartBuilder setTimePeriodType(TimePeriod timePeriodType) {
		getDataset().setTimePeriodType(timePeriodType);
		return this;
	}

	public DifferenceChartBuilder setItemHyperLink(HyperLinkBuilder itemHyperLink) {
		Validate.notNull(itemHyperLink, "itemHyperLink must not be null");
		getDataset().setItemHyperLink(itemHyperLink.build());
		return this;
	}

	//plot
	public DifferenceChartBuilder setTimeAxisFormat(AxisFormatBuilder timeAxisFormat) {
		Validate.notNull(timeAxisFormat, "timeAxisFormat must not be null");
		getPlot().setXAxisFormat(timeAxisFormat.build());
		return this;
	}

	public DifferenceChartBuilder setValueAxisFormat(AxisFormatBuilder valueAxisFormat) {
		Validate.notNull(valueAxisFormat, "valueAxisFormat must not be null");
		getPlot().setYAxisFormat(valueAxisFormat.build());
		return this;
	}

	public DifferenceChartBuilder setPositiveColor(Color positiveColor) {
		getPlot().setPositiveColor(positiveColor);
		return this;
	}

	public DifferenceChartBuilder setNegativeColor(Color negativeColor) {
		getPlot().setNegativeColor(negativeColor);
		return this;
	}

	public DifferenceChartBuilder setShowShapes(Boolean showShapes) {
		getPlot().setShowShapes(showShapes);
		return this;
	}

}
