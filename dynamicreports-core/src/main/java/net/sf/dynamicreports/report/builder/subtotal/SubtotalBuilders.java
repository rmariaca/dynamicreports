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
	
	//average
	public <T extends Number> AggregationSubtotalBuilder<Number> avg(ColumnBuilder<?, T> subtotalColumn) {
		return Subtotals.avg(subtotalColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<Number> avg(String fieldName, Class<T> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.avg(fieldName, valueClass, showInColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<Number> avg(FieldBuilder<T> field, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.avg(field, showInColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<Number> avg(DRISimpleExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.avg(expression, showInColumn);
	}
	
	//count
	public AggregationSubtotalBuilder<Long> count(ColumnBuilder<?, ?> subtotalColumn) {
		return Subtotals.count(subtotalColumn);
	}

	public AggregationSubtotalBuilder<Long> count(String fieldName, Class<?> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.count(fieldName, valueClass, showInColumn);
	}

	public AggregationSubtotalBuilder<Long> count(FieldBuilder<?> field, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.count(field, showInColumn);
	}

	public AggregationSubtotalBuilder<Long> count(DRISimpleExpression<?> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.count(expression, showInColumn);
	}
	
	//distinct count
	public AggregationSubtotalBuilder<Long> distinctCount(ColumnBuilder<?, ?> subtotalColumn) {
		return Subtotals.distinctCount(subtotalColumn);
	}

	public AggregationSubtotalBuilder<Long> distinctCount(String fieldName, Class<?> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.distinctCount(fieldName, valueClass, showInColumn);
	}

	public AggregationSubtotalBuilder<Long> distinctCount(FieldBuilder<?> field, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.distinctCount(field, showInColumn);
	}

	public AggregationSubtotalBuilder<Long> distinctCount(DRISimpleExpression<?> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.distinctCount(expression, showInColumn);
	}
	
	//first
	public <T> AggregationSubtotalBuilder<T> first(ColumnBuilder<?, T> subtotalColumn) {
		return Subtotals.first(subtotalColumn);
	}

	public <T> AggregationSubtotalBuilder<T> first(String fieldName, Class<T> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.first(fieldName, valueClass, showInColumn);
	}

	public <T> AggregationSubtotalBuilder<T> first(FieldBuilder<T> field, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.first(field, showInColumn);
	}

	public <T> AggregationSubtotalBuilder<T> first(DRISimpleExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.first(expression, showInColumn);
	}
	
	//highest
	public <T> AggregationSubtotalBuilder<T> max(ColumnBuilder<?, T> subtotalColumn) {
		return Subtotals.max(subtotalColumn);
	}

	public <T> AggregationSubtotalBuilder<T> max(String fieldName, Class<T> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.max(fieldName, valueClass, showInColumn);
	}

	public <T> AggregationSubtotalBuilder<T> max(FieldBuilder<T> field, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.max(field, showInColumn);
	}

	public <T> AggregationSubtotalBuilder<T> max(DRISimpleExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.max(expression, showInColumn);
	}
	
	//lowest
	public <T> AggregationSubtotalBuilder<T> min(ColumnBuilder<?, T> subtotalColumn) {
		return Subtotals.min(subtotalColumn);
	}

	public <T> AggregationSubtotalBuilder<T> min(String fieldName, Class<T> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.min(fieldName, valueClass, showInColumn);
	}

	public <T> AggregationSubtotalBuilder<T> min(FieldBuilder<T> field, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.min(field, showInColumn);
	}

	public <T> AggregationSubtotalBuilder<T> min(DRISimpleExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.min(expression, showInColumn);
	}
	
	//standard deviation
	public <T extends Number> AggregationSubtotalBuilder<Number> stdDev(ColumnBuilder<?, T> subtotalColumn) {
		return Subtotals.stdDev(subtotalColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<Number> stdDev(String fieldName, Class<T> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.stdDev(fieldName, valueClass, showInColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<Number> stdDev(FieldBuilder<T> field, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.stdDev(field, showInColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<Number> stdDev(DRISimpleExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.stdDev(expression, showInColumn);
	}
	
	//variance
	public <T extends Number> AggregationSubtotalBuilder<Number> var(ColumnBuilder<?, T> subtotalColumn) {
		return Subtotals.var(subtotalColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<Number> var(String fieldName, Class<T> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.var(fieldName, valueClass, showInColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<Number> var(FieldBuilder<T> field, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.var(field, showInColumn);
	}

	public <T extends Number> AggregationSubtotalBuilder<Number> var(DRISimpleExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.var(expression, showInColumn);
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
	
	/*public PercentageSubtotalBuilder percentage(DRISimpleExpression<? extends Number> expression, ColumnBuilder<?, ?> showInColumn) {
		return Subtotals.percentage(expression, showInColumn);
	}*/
}
