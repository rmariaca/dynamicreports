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

package net.sf.dynamicreports.report.builder.subtotal;

import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.definition.expression.DRIComplexExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class SubtotalBuilders {

	//calculation
	public <T> AggregationSubtotalBuilder<T> aggregate(ColumnBuilder<?, ?> subtotalColumn, Calculation calculation) {
		return Subtotals.aggregate(subtotalColumn, calculation);
	}

	public <T> AggregationSubtotalBuilder<T> aggregate(String fieldName, Class<?> valueClass, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
		return Subtotals.aggregate(fieldName, valueClass, showInColumn, calculation);
	}

	public <T> AggregationSubtotalBuilder<T> aggregate(FieldBuilder<?> field, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
		return Subtotals.aggregate(field, showInColumn, calculation);
	}

	public <T> AggregationSubtotalBuilder<T> aggregate(DRISimpleExpression<?> expression, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
		return Subtotals.aggregate(expression, showInColumn, calculation);
	}
	
	//sum
	public <T extends Number> AggregationSubtotalBuilder<T> sum(ColumnBuilder<?, T> subtotalColumn) {
		return Subtotals.sum(subtotalColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<T> sum(String fieldName, Class<T> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.sum(fieldName, valueClass, showInColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<T> sum(FieldBuilder<T> field, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.sum(field, showInColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<T> sum(DRISimpleExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.sum(expression, showInColumn);
	}
	
	//custom
	public <T> CustomSubtotalBuilder<T> customValue(DRISimpleExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.customValue(expression, showInColumn);	
	}

	public <T> CustomSubtotalBuilder<T> customValue(DRIComplexExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.customValue(expression, showInColumn);	
	}
	
	//percentage
	public PercentageSubtotalBuilder percentage(ColumnBuilder<?, ? extends Number> subtotalColumn) {
		return Subtotals.percentage(subtotalColumn);
	}
	
	public PercentageSubtotalBuilder percentage(String fieldName, Class<? extends Number> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.percentage(fieldName, valueClass, showInColumn);
	}
	
	public PercentageSubtotalBuilder percentage(FieldBuilder<? extends Number> field, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.percentage(field, showInColumn);
	}
	
	public PercentageSubtotalBuilder percentage(DRISimpleExpression<? extends Number> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.percentage(expression, showInColumn);
	}
}
