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

package net.sf.dynamicreports.report.builder.column;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.expression.AddExpression;
import net.sf.dynamicreports.report.builder.expression.DivideExpression;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.expression.MultiplyExpression;
import net.sf.dynamicreports.report.builder.expression.SubtractExpression;
import net.sf.dynamicreports.report.builder.expression.ValueExpression;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIValue;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class TextColumnBuilder<T> extends ValueColumnBuilder<TextColumnBuilder<T>, T> implements DRIValue<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected TextColumnBuilder(FieldBuilder<T> field) {
		Validate.notNull(field, "field must not be null");
		setValueExpression(field.getField());
	}

	protected TextColumnBuilder(DRIExpression<T> valueExpression) {
		setValueExpression(valueExpression);
	}

	//add
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> add(TextColumnBuilder<? extends Number> column) {
		Validate.isTrue(Number.class.isAssignableFrom(getObject().getValueClass()), "Only Number column can multiply");
		DRIExpression<? extends Number> value1Expression = (DRIExpression<? extends Number>) this.getComponent().getValueExpression();
		DRIExpression<? extends Number> value2Expression = column.getComponent().getValueExpression();
		AddExpression exp = new AddExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<BigDecimal>(exp).setDataType(type.bigDecimalType());
	}

	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> add(Number number) {
		Validate.isTrue(Number.class.isAssignableFrom(getObject().getValueClass()), "Only Number column can multiply");
		DRIExpression<? extends Number> value1Expression = (DRIExpression<? extends Number>) this.getComponent().getValueExpression();
		ValueExpression<Number> value2Expression = Expressions.number(number);
		AddExpression exp = new AddExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<BigDecimal>(exp).setDataType(type.bigDecimalType());
	}

	//subtract
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> subtract(TextColumnBuilder<? extends Number> column) {
		Validate.isTrue(Number.class.isAssignableFrom(getObject().getValueClass()), "Only Number column can subtract");
		DRIExpression<? extends Number> value1Expression = (DRIExpression<? extends Number>) this.getComponent().getValueExpression();
		DRIExpression<? extends Number> value2Expression = column.getComponent().getValueExpression();
		SubtractExpression exp = new SubtractExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<BigDecimal>(exp).setDataType(type.bigDecimalType());
	}

	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> subtract(Number number) {
		Validate.isTrue(Number.class.isAssignableFrom(getObject().getValueClass()), "Only Number column can subtract");
		DRIExpression<? extends Number> value1Expression = (DRIExpression<? extends Number>) this.getComponent().getValueExpression();
		ValueExpression<Number> value2Expression = Expressions.number(number);
		SubtractExpression exp = new SubtractExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<BigDecimal>(exp).setDataType(type.bigDecimalType());
	}

	//multiply
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> multiply(TextColumnBuilder<? extends Number> column) {
		Validate.isTrue(Number.class.isAssignableFrom(getObject().getValueClass()), "Only Number column can multiply");
		DRIExpression<? extends Number> value1Expression = (DRIExpression<? extends Number>) this.getComponent().getValueExpression();
		DRIExpression<? extends Number> value2Expression = column.getComponent().getValueExpression();
		MultiplyExpression exp = new MultiplyExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<BigDecimal>(exp).setDataType(type.bigDecimalType());
	}

	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> multiply(Number number) {
		Validate.isTrue(Number.class.isAssignableFrom(getObject().getValueClass()), "Only Number column can multiply");
		DRIExpression<? extends Number> value1Expression = (DRIExpression<? extends Number>) this.getComponent().getValueExpression();
		ValueExpression<Number> value2Expression = Expressions.number(number);
		MultiplyExpression exp = new MultiplyExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<BigDecimal>(exp).setDataType(type.bigDecimalType());
	}

	//divide
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> divide(int scale, TextColumnBuilder<? extends Number> column) {
		Validate.isTrue(Number.class.isAssignableFrom(getObject().getValueClass()), "Only Number column can divide");
		DRIExpression<? extends Number> value1Expression = (DRIExpression<? extends Number>) this.getComponent().getValueExpression();
		DRIExpression<? extends Number> value2Expression = column.getComponent().getValueExpression();
		DivideExpression exp = new DivideExpression(scale, value1Expression, value2Expression);
		return new TextColumnBuilder<BigDecimal>(exp).setDataType(type.bigDecimalType());
	}

	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> divide(int scale, Number number) {
		Validate.isTrue(Number.class.isAssignableFrom(getObject().getValueClass()), "Only Number column can divide");
		DRIExpression<? extends Number> value1Expression = (DRIExpression<? extends Number>) this.getComponent().getValueExpression();
		ValueExpression<Number> value2Expression = Expressions.number(number);
		DivideExpression exp = new DivideExpression(scale, value1Expression, value2Expression);
		return new TextColumnBuilder<BigDecimal>(exp).setDataType(type.bigDecimalType());
	}

	public String getName() {
		return getObject().getName();
	}
}
