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

package net.sf.dynamicreports.report.builder.crosstab;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class Crosstabs {

	public static CrosstabBuilder crosstab() {
		return new CrosstabBuilder();
	}

	//column group
	public static <T> CrosstabColumnGroupBuilder<T> columnGroup(ValueColumnBuilder<?, T> column) {
		return new CrosstabColumnGroupBuilder<T>(column);
	}

	public static <T> CrosstabColumnGroupBuilder<T> columnGroup(FieldBuilder<T> field) {
		return new CrosstabColumnGroupBuilder<T>(field);
	}

	public static <T> CrosstabColumnGroupBuilder<T> columnGroup(String fieldName, Class<T> valueClass) {
		return new CrosstabColumnGroupBuilder<T>(DynamicReports.field(fieldName, valueClass));
	}

	public static <T> CrosstabColumnGroupBuilder<T> columnGroup(DRISimpleExpression<T> expression) {
		return new CrosstabColumnGroupBuilder<T>(expression);
	}

	//row group
	public static <T> CrosstabRowGroupBuilder<T> rowGroup(ValueColumnBuilder<?, T> column) {
		return new CrosstabRowGroupBuilder<T>(column);
	}

	public static <T> CrosstabRowGroupBuilder<T> rowGroup(FieldBuilder<T> field) {
		return new CrosstabRowGroupBuilder<T>(field);
	}

	public static <T> CrosstabRowGroupBuilder<T> rowGroup(String fieldName, Class<T> valueClass) {
		return new CrosstabRowGroupBuilder<T>(DynamicReports.field(fieldName, valueClass));
	}

	public static <T> CrosstabRowGroupBuilder<T> rowGroup(DRISimpleExpression<T> expression) {
		return new CrosstabRowGroupBuilder<T>(expression);
	}

	//measure
	public static <T> CrosstabMeasureVariableBuilder<T> measureVariable(ValueColumnBuilder<?, ?> column, Calculation calculation) {
		Validate.notNull(column, "column must not be null");
		return new CrosstabMeasureVariableBuilder<T>(column, calculation);
	}

	public static <T> CrosstabMeasureVariableBuilder<T> measureVariable(FieldBuilder<T> field, Calculation calculation) {
		Validate.notNull(field, "field must not be null");
		return new CrosstabMeasureVariableBuilder<T>(field, calculation);
	}

	public static <T> CrosstabMeasureVariableBuilder<T> measureVariable(String fieldName, Class<?> valueClass, Calculation calculation) {
		return new CrosstabMeasureVariableBuilder<T>(DynamicReports.field(fieldName, valueClass), calculation);
	}

	public static <T> CrosstabMeasureVariableBuilder<T> measureVariable(DRISimpleExpression<?> expression, Calculation calculation) {
		return new CrosstabMeasureVariableBuilder<T>(expression, calculation);
	}

	public static <T> CrosstabMeasureVariableCellBuilder<T> measure(ValueColumnBuilder<?, ?> column, Calculation calculation) {
		Validate.notNull(column, "column must not be null");
		return new CrosstabMeasureVariableCellBuilder<T>(column, calculation);
	}

	public static <T> CrosstabMeasureVariableCellBuilder<T> measure(FieldBuilder<T> field, Calculation calculation) {
		Validate.notNull(field, "field must not be null");
		return new CrosstabMeasureVariableCellBuilder<T>(field, calculation);
	}

	public static <T> CrosstabMeasureVariableCellBuilder<T> measure(String fieldName, Class<?> valueClass, Calculation calculation) {
		return new CrosstabMeasureVariableCellBuilder<T>(DynamicReports.field(fieldName, valueClass), calculation);
	}

	public static <T> CrosstabMeasureVariableCellBuilder<T> measure(DRISimpleExpression<?> expression, Calculation calculation) {
		return new CrosstabMeasureVariableCellBuilder<T>(expression, calculation);
	}

	public static <T> CrosstabMeasureCellBuilder<T> measure(DRISimpleExpression<?> expression) {
		return new CrosstabMeasureCellBuilder<T>(expression);
	}
}
