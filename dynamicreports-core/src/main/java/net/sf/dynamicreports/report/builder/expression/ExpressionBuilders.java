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

package net.sf.dynamicreports.report.builder.expression;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import net.sf.dynamicreports.report.builder.group.GroupBuilder;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class ExpressionBuilders {

	public PrintInFirstPageExpression printInFirstPage() {
		return Expressions.printInFirstPage();
	}

	public PrintNotInFirstPageExpression printNotInFirstPage() {
		return Expressions.printNotInFirstPage();
	}

	public PrintWhenGroupHasMoreThanOneRowExpression printWhenGroupHasMoreThanOneRow(String groupName) {
		return Expressions.printWhenGroupHasMoreThanOneRow(groupName);
	}

	public PrintWhenGroupHasMoreThanOneRowExpression printWhenGroupHasMoreThanOneRow(GroupBuilder<?> group) {
		return Expressions.printWhenGroupHasMoreThanOneRow(group);
	}

	public ReportRowNumberExpression reportRowNumber() {
		return Expressions.reportRowNumber();
	}

	public PageRowNumberExpression pageRowNumber() {
		return Expressions.pageRowNumber();
	}

	public ColumnRowNumberExpression columnRowNumber() {
		return Expressions.columnRowNumber();
	}

	public PageNumberExpression pageNumber() {
		return Expressions.pageNumber();
	}

	public ColumnNumberExpression columnNumber() {
		return Expressions.columnNumber();
	}

	public GroupRowNumberExpression groupRowNumber(String groupName) {
		return Expressions.groupRowNumber(groupName);
	}

	public GroupRowNumberExpression groupRowNumber(GroupBuilder<?> group) {
		return Expressions.groupRowNumber(group);
	}

	public ValueExpression<Date> date(Date date) {
		return Expressions.date(date);
	}

	public ValueExpression<Number> number(Number number) {
		return Expressions.number(number);
	}

	public ValueExpression<Image> image(Image image) {
		return Expressions.image(image);
	}

	public ValueExpression<InputStream> inputStream(InputStream inputStream) {
		return Expressions.inputStream(inputStream);
	}

	public ValueExpression<URL> url(URL url) {
		return Expressions.url(url);
	}

	public <T> ValueExpression<T> value(T value) {
		return Expressions.value(value);
	}

	public <T> ValueExpression<T> value(T value, Class<? super T> valueClass) {
		return Expressions.value(value, valueClass);
	}

	public ValueExpression<String> text(String text) {
		return Expressions.text(text);
	}

	public MessageExpression message(String key) {
		return Expressions.message(key);
	}

	public MessageExpression message(String key, Object[] arguments) {
		return Expressions.message(key, arguments);
	}

	//jasper
	public <T> JasperExpression<T> jasper(String expression, Class<? super T> valueClass) {
		return Expressions.jasper(expression, valueClass);
	}

	//property
	public PropertyExpression property(String name, DRISimpleExpression<String> valueExpression) {
		return Expressions.property(name, valueExpression);
	}

	public PropertyExpression property(String name, String value) {
		return Expressions.property(name, value);
	}

	//parameter
	public ParameterExpression parameter(String name, DRISimpleExpression<?> valueExpression) {
		return Expressions.parameter(name, valueExpression);
	}

	public ParameterExpression parameter(String name, Object value) {
		return Expressions.parameter(name, value);
	}

	public PrintInOddRowExpression printInOddRow() {
		return Expressions.printInOddRow();
	}

	public PrintInEvenRowExpression printInEvenRow() {
		return Expressions.printInEvenRow();
	}
}
