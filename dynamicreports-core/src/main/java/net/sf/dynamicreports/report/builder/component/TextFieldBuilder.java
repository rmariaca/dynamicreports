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

package net.sf.dynamicreports.report.builder.component;

import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.VariableBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIComplexExpression;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class TextFieldBuilder<T> extends HyperLinkComponentBuilder<TextFieldBuilder<T>, DRTextField<T>> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	protected TextFieldBuilder() {
		super(new DRTextField<T>());
	}
	
	public TextFieldBuilder<T> setText(VariableBuilder<T> variable) {
		Validate.notNull(variable, "variable must not be null");
		getObject().setValueExpression(variable.getVariable());
		return this;
	}
	
	public TextFieldBuilder<T> setText(FieldBuilder<T> field) {
		Validate.notNull(field, "field must not be null");
		getObject().setValueExpression(field.getField());
		if (getObject().getDataType() == null) {
			getObject().setDataType(field.getField().getDataType());
		}
		return this;
	}
	
	public TextFieldBuilder<T> setText(DRISimpleExpression<T> textExpression) {
		getObject().setValueExpression(textExpression);
		return this;
	}

	public TextFieldBuilder<T> setText(DRIComplexExpression<T> textExpression) {
		getObject().setValueExpression(textExpression);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public TextFieldBuilder<T> setText(String text) {
		getObject().setValueExpression((DRIExpression<T>) Expressions.text(text));
		return this;
	}

	@SuppressWarnings("unchecked")
	public TextFieldBuilder<T> setText(Number number) {
		getObject().setValueExpression((DRIExpression<T>) Expressions.number(number));
		return this;
	}
	
	public TextFieldBuilder<T> setPattern(String pattern) {
		getObject().setPattern(pattern);
		return this;
	}

	public TextFieldBuilder<T> setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		getObject().setHorizontalAlignment(horizontalAlignment);
		return this;
	}
	
	public TextFieldBuilder<T> setValueFormatter(DRIValueFormatter<?, ? super T> valueFormatter) {
		getObject().setValueFormatter(valueFormatter);
		return this;
	}
	
	public TextFieldBuilder<T> setDataType(DRIDataType<? super T, T> dataType) {
		getObject().setDataType(dataType);
		return this;
	}	
	
  /**
   * This method is used to define the preferred width of a textField.
   * The width is set to the <code>columns</code> multiplied by width of the
   * character <em>m</em> for the font used
   * 
   * @param columns the number of preferred columns >= 1
   * @exception IllegalArgumentException if <code>columns</code> is < 1
   */
	public TextFieldBuilder<T> setColumns(Integer columns) {
		getObject().setColumns(columns);
		return this;
	}
	
  /**
   * This method is used to define the fixed width of a textField.
   * The width is set to the <code>columns</code> multiplied by width of the
   * character <em>m</em> for the font used
   * 
   * @param columns the number of fixed columns >= 1
   * @exception IllegalArgumentException if <code>columns</code> is < 1
   */
	public TextFieldBuilder<T> setFixedColumns(Integer columns) {
		getObject().setColumns(columns);
		getObject().setWidthType(ComponentDimensionType.FIXED);
		return this;
	}

  /**
   * This method is used to define the minimum width of a textField.
   * The width is set to the <code>columns</code> multiplied by width of the
   * character <em>m</em> for the font used
   * 
   * @param columns the number of minimum columns >= 1
   * @exception IllegalArgumentException if <code>columns</code> is < 1
   */
	public TextFieldBuilder<T> setMinColumns(Integer columns) {
		getObject().setColumns(columns);
		getObject().setWidthType(ComponentDimensionType.EXPAND);
		return this;
	}
	
  /**
   * This method is used to define the preferred height of a textField.
   * The height is set to the <code>rows</code> multiplied by height of the font
   * 
   * @param rows the number of preferred rows >= 1
   * @exception IllegalArgumentException if <code>rows</code> is < 1
   */
	public TextFieldBuilder<T> setRows(Integer rows) {
		getObject().setRows(rows);
		return this;
	}	
	
  /**
   * This method is used to define the fixed height of a textField.
   * The height is set to the <code>rows</code> multiplied by height of the font
   * 
   * @param rows the number of fixed rows >= 1
   * @exception IllegalArgumentException if <code>rows</code> is < 1
   */
	public TextFieldBuilder<T> setFixedRows(Integer rows) {
		getObject().setRows(rows);
		getObject().setHeightType(ComponentDimensionType.FIXED);
		return this;
	}
		
  /**
   * This method is used to define the minimum height of a textField.
   * The height is set to the <code>rows</code> multiplied by height of the font
   * 
   * @param rows the number of minimum rows >= 1
   * @exception IllegalArgumentException if <code>rows</code> is < 1
   */
	public TextFieldBuilder<T> setMinRows(Integer rows) {
		getObject().setRows(rows);
		getObject().setHeightType(ComponentDimensionType.EXPAND);
		return this;
	}
}
