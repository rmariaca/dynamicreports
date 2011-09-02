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

import net.sf.dynamicreports.report.base.column.DRValueColumn;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRIPropertyExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("unchecked")
public abstract class ValueColumnBuilder<T extends ValueColumnBuilder<T, U>, U> extends ColumnBuilder<T, DRValueColumn<U>> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected ValueColumnBuilder() {
		super(new DRValueColumn<U>(new DRTextField<U>()));
	}

	protected void setValueExpression(DRIExpression<U> valueExpression) {
		getComponent().setValueExpression(valueExpression);
	}

	public T setPrintRepeatedDetailValues(Boolean printRepeatedDetailValues) {
		getObject().setPrintRepeatedDetailValues(printRepeatedDetailValues);
		return (T) this;
	}

	public T setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		getComponent().setHorizontalAlignment(horizontalAlignment);
		return (T) this;
	}

	public T setPattern(String pattern) {
		getComponent().setPattern(pattern);
		return (T) this;
	}

	public T setValueFormatter(DRIValueFormatter<?, ? super U> valueFormatter) {
		getComponent().setValueFormatter(valueFormatter);
		return (T) this;
	}

	public T setDataType(DRIDataType<? super U, U> dataType) {
		getComponent().setDataType(dataType);
		return (T) this;
	}

  /**
   * This method is used to define the preferred width of a column.
   * The width is set to the <code>columns</code> multiplied by width of the
   * character <em>m</em> for the font used
   *
   * @param columns the number of preferred columns >= 0
   * @exception IllegalArgumentException if <code>columns</code> is < 0
   */
	public T setColumns(Integer columns) {
		getComponent().setColumns(columns);
		return (T) this;
	}

  /**
   * This method is used to define the fixed width of a column.
   * The width is set to the <code>columns</code> multiplied by width of the
   * character <em>m</em> for the font used
   *
   * @param columns the number of fixed columns >= 0
   * @exception IllegalArgumentException if <code>columns</code> is < 0
   */
	public T setFixedColumns(Integer columns) {
		getComponent().setColumns(columns);
		getComponent().setWidthType(ComponentDimensionType.FIXED);
		return (T) this;
	}

  /**
   * This method is used to define the minimum width of a column.
   * The width is set to the <code>columns</code> multiplied by width of the
   * character <em>m</em> for the font used
   *
   * @param columns the number of minimum columns >= 0
   * @exception IllegalArgumentException if <code>columns</code> is < 0
   */
	public T setMinColumns(Integer columns) {
		getComponent().setColumns(columns);
		getComponent().setWidthType(ComponentDimensionType.EXPAND);
		return (T) this;
	}

  /**
   * This method is used to define the preferred height of a column.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param rows the number of preferred rows >= 0
   * @exception IllegalArgumentException if <code>rows</code> is < 0
   */
	public T setRows(Integer rows) {
		getComponent().setRows(rows);
		return (T) this;
	}

  /**
   * This method is used to define the fixed height of a column.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param rows the number of fixed rows >= 0
   * @exception IllegalArgumentException if <code>rows</code> is < 0
   */
	public T setFixedRows(Integer rows) {
		getComponent().setRows(rows);
		getComponent().setHeightType(ComponentDimensionType.FIXED);
		return (T) this;
	}

  /**
   * This method is used to define the minimum height of a column.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param rows the number of minimum rows >= 0
   * @exception IllegalArgumentException if <code>rows</code> is < 0
   */
	public T setMinRows(Integer rows) {
		getComponent().setRows(rows);
		getComponent().setHeightType(ComponentDimensionType.EXPAND);
		return (T) this;
	}

	public T setHyperLink(HyperLinkBuilder hyperLink) {
		if (hyperLink != null) {
			getComponent().setHyperLink(hyperLink.getHyperLink());
		}
		else {
			getComponent().setHyperLink(null);
		}
		return (T) this;
	}

  /**
   * Sets the preferred width of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the column preferred width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public T setWidth(Integer width) {
		getComponent().setWidth(width);
		return (T) this;
	}

  /**
   * Sets the fixed width of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the column fixed width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public T setFixedWidth(Integer width) {
		getComponent().setWidth(width);
		getComponent().setWidthType(ComponentDimensionType.FIXED);
		return (T) this;
	}

  /**
   * Sets the minimum width of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the column minimum width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public T setMinWidth(Integer width) {
		getComponent().setWidth(width);
		getComponent().setWidthType(ComponentDimensionType.EXPAND);
		return (T) this;
	}

  /**
   * Sets the preferred height of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column preferred height >= 0
   * @exception IllegalArgumentException if <code>height</code> is < 0
   */
	public T setHeight(Integer height) {
		getComponent().setHeight(height);
		return (T) this;
	}

  /**
   * Sets the fixed height of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column fixed height >= 0
   * @exception IllegalArgumentException if <code>height</code> is < 0
   */
	public T setFixedHeight(Integer height) {
		getComponent().setHeight(height);
		getComponent().setHeightType(ComponentDimensionType.FIXED);
		return (T) this;
	}

  /**
   * Sets the minimum height of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column minimum height >= 0
   * @exception IllegalArgumentException if <code>height</code> is < 0
   */
	public T setMinHeight(Integer height) {
		getComponent().setHeight(height);
		getComponent().setHeightType(ComponentDimensionType.EXPAND);
		return (T) this;
	}

	public T setStretchWithOverflow(Boolean stretchWithOverflow) {
		getComponent().setStretchWithOverflow(stretchWithOverflow);
		return (T) this;
	}

	public T addProperty(DRIPropertyExpression propertyExpression) {
		getComponent().addPropertyExpression(propertyExpression);
		return (T) this;
	}

	public T addProperty(String name, DRIExpression<String> valueExpression) {
		getComponent().addPropertyExpression(Expressions.property(name, valueExpression));
		return (T) this;
	}

	public T addProperty(String name, String value) {
		getComponent().addPropertyExpression(Expressions.property(name, value));
		return (T) this;
	}

	@Override
	protected DRTextField<U> getComponent() {
		return (DRTextField<U>) getObject().getComponent();
	}
}
