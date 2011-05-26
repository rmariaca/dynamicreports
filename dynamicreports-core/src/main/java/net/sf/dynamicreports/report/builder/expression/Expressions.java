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
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class Expressions {
	private static final PrintInFirstPageExpression printInFirstPage = new PrintInFirstPageExpression();
	private static final PrintNotInFirstPageExpression printNotInFirstPage = new PrintNotInFirstPageExpression();
	private static final ReportRowNumberExpression reportRowNumber = new ReportRowNumberExpression();
	private static final PageRowNumberExpression pageRowNumber = new PageRowNumberExpression();
	private static final ColumnRowNumberExpression columnRowNumber = new ColumnRowNumberExpression();
	private static final PageNumberExpression pageNumber = new PageNumberExpression();
	private static final ColumnNumberExpression columnNumber = new ColumnNumberExpression();
	private static final PrintInOddRowExpression printInOddRow = new PrintInOddRowExpression();
	private static final PrintInEvenRowExpression printInEvenRow = new PrintInEvenRowExpression();

	public static PrintInFirstPageExpression printInFirstPage() {
		return printInFirstPage;
	}

	public static PrintNotInFirstPageExpression printNotInFirstPage() {
		return printNotInFirstPage;
	}

	public static PrintWhenGroupHasMoreThanOneRowExpression printWhenGroupHasMoreThanOneRow(String groupName) {
		return new PrintWhenGroupHasMoreThanOneRowExpression(groupName);
	}

	public static PrintWhenGroupHasMoreThanOneRowExpression printWhenGroupHasMoreThanOneRow(GroupBuilder<?> group) {
		Validate.notNull(group, "group must not be null");
		return printWhenGroupHasMoreThanOneRow(group.getGroup().getName());
	}

	public static ReportRowNumberExpression reportRowNumber() {
		return reportRowNumber;
	}

	public static PageRowNumberExpression pageRowNumber() {
		return pageRowNumber;
	}

	public static ColumnRowNumberExpression columnRowNumber() {
		return columnRowNumber;
	}

	public static PageNumberExpression pageNumber() {
		return pageNumber;
	}

	public static ColumnNumberExpression columnNumber() {
		return columnNumber;
	}

	public static GroupRowNumberExpression groupRowNumber(String groupName) {
		return new GroupRowNumberExpression(groupName);
	}

	public static GroupRowNumberExpression groupRowNumber(GroupBuilder<?> group) {
		Validate.notNull(group, "group must not be null");
		return groupRowNumber(group.getGroup().getName());
	}

	public static ValueExpression<Date> date(Date date) {
		return value(date, Date.class);
	}

	public static ValueExpression<Number> number(Number number) {
		return value(number, Number.class);
	}

	public static ValueExpression<Image> image(Image image) {
		return value(image, Image.class);
	}

	public static ValueExpression<InputStream> inputStream(InputStream inputStream) {
		return value(inputStream, InputStream.class);
	}

	public static ValueExpression<URL> url(URL url) {
		return value(url, URL.class);
	}

	public static <T> ValueExpression<T> value(T value) {
		return new ValueExpression<T>(value);
	}

	public static <T> ValueExpression<T> value(T value, Class<? super T> valueClass) {
		return new ValueExpression<T>(value, valueClass);
	}

	public static JasperExpression<String> text(String text) {
		return jasper("\"" + text + "\"", String.class);
	}

	public static MessageExpression message(String key) {
		if (key != null) {
			return new MessageExpression(key);
		}
		return null;
	}

	public static MessageExpression message(String key, Object[] arguments) {
		if (key != null) {
			return new MessageExpression(key, arguments);
		}
		return null;
	}

	//jasper
	public static <T> JasperExpression<T> jasper(String expression, Class<? super T> valueClass) {
		return new JasperExpression<T>(expression, valueClass);
	}

	//property
	public static PropertyExpression property(String name, DRIExpression<String> valueExpression) {
		return new PropertyExpression(name, valueExpression);
	}

	public static PropertyExpression property(String name, String value) {
		return new PropertyExpression(name, text(value));
	}

	//parameter
	public static ParameterExpression parameter(String name, DRIExpression<?> valueExpression) {
		return new ParameterExpression(name, valueExpression);
	}

	public static ParameterExpression parameter(String name, Object value) {
		return new ParameterExpression(name, value(value));
	}

	public static PrintInOddRowExpression printInOddRow() {
		return printInOddRow;
	}

	public static PrintInEvenRowExpression printInEvenRow() {
		return printInEvenRow;
	}
}
