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

package net.sf.dynamicreports.report.builder.expression;

import java.awt.Image;
import java.io.InputStream;
import java.util.Date;

import net.sf.dynamicreports.report.builder.group.GroupBuilder;

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
	
	public PageXofYExpression pageXofY() {		
		return Expressions.pageXofY();
	}
	
	public PageXslashYExpression pageXslashY() {		
		return Expressions.pageXslashY();
	}
	
	public GroupRowNumberExpression groupRowNumber(String groupName) {		
		return Expressions.groupRowNumber(groupName);
	}
	
	public GroupRowNumberExpression groupRowNumber(GroupBuilder<?> group) {
		return Expressions.groupRowNumber(group);
	}
	
	public DateExpression date(Date date) {
		return Expressions.date(date);
	}
	
	public NumberExpression number(Number number) {
		return Expressions.number(number);
	}

	public ImageExpression image(Image image) {
		return Expressions.image(image);
	}

	public ImageInputStreamExpression image(InputStream image) {
		return Expressions.image(image);
	}
	
	public StringExpression text(String text) {
		return Expressions.text(text);
	}

	public MessageExpression message(String key) {
		return Expressions.message(key);
	}
	
	public PrintInOddRowExpression printInOddRow() {
		return Expressions.printInOddRow();
	}
	
	public PrintInEvenRowExpression printInEvenRow() {
		return Expressions.printInEvenRow();
	}
}
