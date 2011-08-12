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

import net.sf.dynamicreports.report.base.column.DRBooleanColumn;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class BooleanColumnBuilder extends ColumnBuilder<BooleanColumnBuilder, DRBooleanColumn> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected BooleanColumnBuilder(FieldBuilder<Boolean> field) {
		super(new DRBooleanColumn());
		Validate.notNull(field, "field must not be null");
		getObject().setValueExpression(field.getField());
	}

	protected BooleanColumnBuilder(DRIExpression<Boolean> valueExpression) {
		super(new DRBooleanColumn());
		getObject().setValueExpression(valueExpression);
	}

  /**
   * Sets the preferred width of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the column preferred width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public BooleanColumnBuilder setWidth(Integer width) {
		getObject().setWidth(width);
		return this;
	}

  /**
   * Sets the fixed width of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the column fixed width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public BooleanColumnBuilder setFixedWidth(Integer width) {
		getObject().setWidth(width);
		getObject().setWidthType(ComponentDimensionType.FIXED);
		return this;
	}

  /**
   * Sets the minimum width of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the column minimum width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public BooleanColumnBuilder setMinWidth(Integer width) {
		getObject().setWidth(width);
		getObject().setWidthType(ComponentDimensionType.EXPAND);
		return this;
	}

  /**
   * Sets the preferred height of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column preferred height >= 0
   * @exception IllegalArgumentException if <code>height</code> is < 0
   */
	public BooleanColumnBuilder setHeight(Integer height) {
		getObject().setHeight(height);
		return this;
	}

  /**
   * Sets the fixed height of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column fixed height >= 0
   * @exception IllegalArgumentException if <code>height</code> is < 0
   */
	public BooleanColumnBuilder setFixedHeight(Integer height) {
		getObject().setHeight(height);
		getObject().setHeightType(ComponentDimensionType.FIXED);
		return this;
	}

  /**
   * Sets the minimum height of a column.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column minimum height >= 0
   * @exception IllegalArgumentException if <code>height</code> is < 0
   */
	public BooleanColumnBuilder setMinHeight(Integer height) {
		getObject().setHeight(height);
		getObject().setHeightType(ComponentDimensionType.EXPAND);
		return this;
	}

	public BooleanColumnBuilder setComponentType(BooleanComponentType booleanComponentType) {
		getObject().setComponentType(booleanComponentType);
		return this;
	}

	public BooleanColumnBuilder setImageDimension(Integer width, Integer height) {
		getObject().setImageWidth(width);
		getObject().setImageHeight(height);
		return this;
	}

	public BooleanColumnBuilder setImageWidth(Integer width) {
		getObject().setImageWidth(width);
		return this;
	}

	public BooleanColumnBuilder setImageHeight(Integer height) {
		getObject().setImageHeight(height);
		return this;
	}

	@Override
	public BooleanColumnBuilder setStyle(StyleBuilder style) {
		if (style != null) {
			getObject().setStyle(style.getStyle());
		}
		else {
			getObject().setStyle(null);
		}
		return this;
	}

	@Override
	public BooleanColumnBuilder setPrintWhenExpression(DRIExpression<Boolean> printWhenExpression) {
		getObject().setPrintWhenExpression(printWhenExpression);
		return this;
	}
}
