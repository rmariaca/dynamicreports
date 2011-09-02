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

import net.sf.dynamicreports.report.base.column.DRColumn;
import net.sf.dynamicreports.report.base.component.DRComponent;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.grid.ColumnGridComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("unchecked")
public abstract class ColumnBuilder<T extends ColumnBuilder<T, U>, U extends DRColumn<?>> extends AbstractBuilder<T, U> implements ColumnGridComponentBuilder {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected ColumnBuilder(U column) {
		super(column);
	}

	public T setTitle(DRIExpression<?> titleExpression) {
		getObject().setTitleExpression(titleExpression);
		return (T) this;
	}

	public T setTitle(String title) {
		getObject().setTitleExpression(Expressions.text(title));
		return (T) this;
	}

	public T setTitleStyle(StyleBuilder titleStyle) {
		if (titleStyle != null) {
			getObject().setTitleStyle(titleStyle.getStyle());
		}
		else {
			getObject().setTitleStyle(null);
		}
		return (T) this;
	}

	public T setStyle(StyleBuilder style) {
		if (style != null) {
			getComponent().setStyle(style.getStyle());
		}
		else {
			getComponent().setStyle(null);
		}
		return (T) this;
	}

	public T setPrintWhenExpression(DRIExpression<Boolean> printWhenExpression) {
		getComponent().setPrintWhenExpression(printWhenExpression);
		return (T) this;
	}

  /**
   * This method is used to define the preferred height of a column title.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param rows the number of preferred rows >= 0
   * @exception IllegalArgumentException if <code>rows</code> is < 0
   */
	public T setTitleRows(Integer rows) {
		getObject().setTitleRows(rows);
		return (T) this;
	}

  /**
   * This method is used to define the fixed height of a column title.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param rows the number of fixed rows >= 0
   * @exception IllegalArgumentException if <code>rows</code> is < 0
   */
	public T setTitleFixedRows(Integer rows) {
		getObject().setTitleRows(rows);
		getObject().setTitleHeightType(ComponentDimensionType.FIXED);
		return (T) this;
	}

  /**
   * This method is used to define the minimum height of a column title.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param rows the number of minimum rows >= 0
   * @exception IllegalArgumentException if <code>rows</code> is < 0
   */
	public T setTitleMinRows(Integer rows) {
		getObject().setTitleRows(rows);
		getObject().setTitleHeightType(ComponentDimensionType.EXPAND);
		return (T) this;
	}

  /**
   * Sets the preferred height of a column title.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column title preferred height >= 0
   * @exception IllegalArgumentException if <code>height</code> is < 0
   */
	public T setTitleHeight(Integer height) {
		getObject().setTitleHeight(height);
		return (T) this;
	}

  /**
   * Sets the fixed height of a column title.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column title fixed height >= 0
   * @exception IllegalArgumentException if <code>height</code> is < 0
   */
	public T setTitleFixedHeight(Integer height) {
		getObject().setTitleHeight(height);
		getObject().setTitleHeightType(ComponentDimensionType.FIXED);
		return (T) this;
	}

  /**
   * Sets the minimum height of a column title.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column title minimum height >= 0
   * @exception IllegalArgumentException if <code>height</code> is < 0
   */
	public T setTitleMinHeight(Integer height) {
		getObject().setTitleHeight(height);
		getObject().setTitleHeightType(ComponentDimensionType.EXPAND);
		return (T) this;
	}

	protected DRComponent getComponent() {
		return (DRComponent) getObject().getComponent();
	}

	public U getColumn() {
		return build();
	}
}
