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

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.definition.expression.DRIComplexExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class Subtotals {

	//calculation
	public static <T> AggregationSubtotalBuilder<T> aggregate(ColumnBuilder<?, ?> subtotalColumn, Calculation calculation) {
		Validate.notNull(subtotalColumn, "subtotalColumn must not be null");
		return new AggregationSubtotalBuilder<T>(subtotalColumn, calculation);
	}

	public static <T> AggregationSubtotalBuilder<T> aggregate(String fieldName, Class<?> valueClass, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
		return aggregate(DynamicReports.field(fieldName, valueClass), showInColumn, calculation);
	}

	public static <T> AggregationSubtotalBuilder<T> aggregate(FieldBuilder<?> field, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
		Validate.notNull(showInColumn, "showInColumn must not be null");
		return new AggregationSubtotalBuilder<T>(field, showInColumn, calculation);
	}

	public static <T> AggregationSubtotalBuilder<T> aggregate(DRISimpleExpression<?> expression, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
		Validate.notNull(showInColumn, "showInColumn must not be null");
		return new AggregationSubtotalBuilder<T>(expression, showInColumn, calculation);
	}
	
	//sum
	public static <T extends Number> AggregationSubtotalBuilder<T> sum(ColumnBuilder<?, T> subtotalColumn) {
		return aggregate(subtotalColumn, Calculation.SUM);
	}

	public static <T extends Number> AggregationSubtotalBuilder<T> sum(String fieldName, Class<T> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return aggregate(fieldName, valueClass, showInColumn, Calculation.SUM);
	}

	public static <T extends Number> AggregationSubtotalBuilder<T> sum(FieldBuilder<T> field, ColumnBuilder<?, ?> showInColumn) {
		return aggregate(field, showInColumn, Calculation.SUM);
	}

	public static <T extends Number> AggregationSubtotalBuilder<T> sum(DRISimpleExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		return aggregate(expression, showInColumn, Calculation.SUM);
	}
	
	//custom
	public static <T> CustomSubtotalBuilder<T> customValue(DRISimpleExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		Validate.notNull(showInColumn, "showInColumn must not be null");
		return new CustomSubtotalBuilder<T>(expression, showInColumn);	
	}

	public static <T> CustomSubtotalBuilder<T> customValue(DRIComplexExpression<T> expression, ColumnBuilder<?, ?> showInColumn) {
		Validate.notNull(showInColumn, "showInColumn must not be null");
		return new CustomSubtotalBuilder<T>(expression, showInColumn);	
	}
	
	//percentage
	public static PercentageSubtotalBuilder percentage(ColumnBuilder<?, ? extends Number> subtotalColumn) {
		Validate.notNull(subtotalColumn, "subtotalColumn must not be null");
		return new PercentageSubtotalBuilder(subtotalColumn);
	}
	
	public static PercentageSubtotalBuilder percentage(String fieldName, Class<? extends Number> valueClass, ColumnBuilder<?, ?> showInColumn) {
		return percentage(DynamicReports.field(fieldName, valueClass), showInColumn);
	}
	
	public static PercentageSubtotalBuilder percentage(FieldBuilder<? extends Number> field, ColumnBuilder<?, ?> showInColumn) {
		Validate.notNull(showInColumn, "showInColumn must not be null");
		return new PercentageSubtotalBuilder(field, showInColumn);
	}
	
	public static PercentageSubtotalBuilder percentage(DRISimpleExpression<? extends Number> expression, ColumnBuilder<?, ?> showInColumn) {
		Validate.notNull(showInColumn, "showInColumn must not be null");
		return new PercentageSubtotalBuilder(expression, showInColumn);
	}
}
