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

package net.sf.dynamicreports.report.builder.column;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class Columns {
	
	//text	
	public static <T> TextColumnBuilder<T> column(String fieldName, Class<T> valueClass) {
		return column(DynamicReports.field(fieldName, valueClass));
	}

	public static <T> TextColumnBuilder<T> column(String title, String fieldName, Class<T> valueClass) {
		return column(title, DynamicReports.field(fieldName, valueClass));
	}

	public static <T> TextColumnBuilder<T> column(String fieldName, DRIDataType<? super T, T> dataType) {
		Validate.notNull(dataType, "dataType must not be null");		
		TextColumnBuilder<T> textColumnBuilder = new TextColumnBuilder<T>(DynamicReports.field(fieldName, dataType.getValueClass()));
		textColumnBuilder.setDataType(dataType);
		return textColumnBuilder;
	}

	public static <T> TextColumnBuilder<T> column(String title, String fieldName, DRIDataType<? super T, T> dataType) {				
		TextColumnBuilder<T> textColumnBuilder = column(fieldName, dataType);
		textColumnBuilder.setTitle(title);
		return textColumnBuilder;
	}
	
	public static <T> TextColumnBuilder<T> column(FieldBuilder<T> field) {
		TextColumnBuilder<T> textColumnBuilder = new TextColumnBuilder<T>(field);
		if (field.getField().getDataType() != null) {
			textColumnBuilder.setDataType(field.getField().getDataType());
		}
		return textColumnBuilder;
	}	

	public static <T> TextColumnBuilder<T> column(String title, FieldBuilder<T> field) {
		return column(field).setTitle(title);
	}
	
	//expression
	public static <T> TextColumnBuilder<T> column(DRISimpleExpression<T> expression) {
		return new TextColumnBuilder<T>(expression);
	}

	public static <T> TextColumnBuilder<T> column(String title, DRISimpleExpression<T> expression) {
		return column(expression).setTitle(title);
	}
	
	//percentage
	public static PercentageColumnBuilder percentageColumn(ValueColumnBuilder<?, ? extends Number> column) {
		return new PercentageColumnBuilder(column);
	}	
	
	public static PercentageColumnBuilder percentageColumn(String title, ValueColumnBuilder<?, ? extends Number> column) {
		return percentageColumn(column).setTitle(title);
	}	
	
	public static PercentageColumnBuilder percentageColumn(String fieldName, Class<? extends Number> valueClass) {
		return percentageColumn(DynamicReports.field(fieldName, valueClass));
	}

	public static PercentageColumnBuilder percentageColumn(String title, String fieldName, Class<? extends Number> valueClass) {
		return percentageColumn(fieldName, valueClass).setTitle(title);
	}	
	
	public static PercentageColumnBuilder percentageColumn(FieldBuilder<? extends Number> field) {
		return new PercentageColumnBuilder(field);
	}	
	
	public static PercentageColumnBuilder percentageColumn(String title, FieldBuilder<? extends Number> field) {
		return percentageColumn(field).setTitle(title);
	}	
	
	/*public static PercentageColumnBuilder percentageColumn(DRISimpleExpression<? extends Number> expression) {
		return new PercentageColumnBuilder(expression);
	}
	
	public static PercentageColumnBuilder percentageColumn(String title, DRISimpleExpression<? extends Number> expression) {
		return percentageColumn(expression).setTitle(title);
	}	*/	
	
	//column row number
	public static TextColumnBuilder<Integer> columnRowNumberColumn() {
		return column(Expressions.columnRowNumber());
	}
	
	public static TextColumnBuilder<Integer> columnRowNumberColumn(String title) {
		return columnRowNumberColumn().setTitle(title);
	}
	
	//page row number
	public static TextColumnBuilder<Integer> pageRowNumberColumn() {
		return column(Expressions.pageRowNumber());
	}
	
	public static TextColumnBuilder<Integer> pageRowNumberColumn(String title) {
		return pageRowNumberColumn().setTitle(title);
	}
	
	//report row number
	public static TextColumnBuilder<Integer> reportRowNumberColumn() {
		return column(Expressions.reportRowNumber());
	}
	
	public static TextColumnBuilder<Integer> reportRowNumberColumn(String title) {
		return reportRowNumberColumn().setTitle(title);
	}
	
	//component
	public static ComponentColumnBuilder componentColumn(ComponentBuilder<?, ?> component) {
		Validate.notNull(component, "component must not be null");
		return new ComponentColumnBuilder(component);
	}
	
	public static ComponentColumnBuilder componentColumn(String title, ComponentBuilder<?, ?> component) {
		return componentColumn(component).setTitle(title);
	}
}
