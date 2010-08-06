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

import net.sf.dynamicreports.report.base.chart.dataset.DRChartSerie;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class ChartSerieBuilder extends AbstractBuilder<ChartSerieBuilder, DRChartSerie> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	protected ChartSerieBuilder(ColumnBuilder<?, ? extends Number> column) {
		super(new DRChartSerie());
		Validate.notNull(column, "column must not be null");
		getObject().setValueExpression(column.getColumn());
		getObject().setLabelExpression(column.getColumn().getTitleExpression());
	}

	protected ChartSerieBuilder(FieldBuilder<? extends Number> field) {
		super(new DRChartSerie());
		Validate.notNull(field, "field must not be null");
		getObject().setValueExpression(field.build());
	}
	
	protected ChartSerieBuilder(DRISimpleExpression<? extends Number> valueExpression) {
		super(new DRChartSerie());
		getObject().setValueExpression(valueExpression);
	}

	public ChartSerieBuilder setLabel(String label) {
		getObject().setLabelExpression(Expressions.text(label));
		return this;
	}	
	
	public ChartSerieBuilder setLabel(DRISimpleExpression<String> labelExpression) {
		getObject().setLabelExpression(labelExpression);
		return this;
	}
	
	public DRChartSerie getChartSerie() {
		return build();
	}
}
