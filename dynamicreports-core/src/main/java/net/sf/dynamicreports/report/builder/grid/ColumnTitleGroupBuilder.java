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

package net.sf.dynamicreports.report.builder.grid;

import net.sf.dynamicreports.report.base.grid.DRColumnTitleGroup;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class ColumnTitleGroupBuilder extends AbstractBuilder<ColumnTitleGroupBuilder, DRColumnTitleGroup> implements ColumnGridComponentBuilder {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected ColumnTitleGroupBuilder() {
		super(new DRColumnTitleGroup());
	}

	public ColumnTitleGroupBuilder add(ColumnGridComponentBuilder ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ColumnGridComponentBuilder component : components) {
			getObject().addComponent(component.build());
		}
		return this;
	}

	public ColumnTitleGroupBuilder setTitle(DRIExpression<?> titleExpression) {
		getObject().setTitleExpression(titleExpression);
		return this;
	}

	public ColumnTitleGroupBuilder setTitle(String title) {
		getObject().setTitleExpression(Expressions.text(title));
		return this;
	}

	public ColumnTitleGroupBuilder setTitleStyle(StyleBuilder titleStyle) {
		if (titleStyle != null) {
			getObject().setTitleStyle(titleStyle.getStyle());
		}
		else {
			getObject().setTitleStyle(null);
		}
		return this;
	}

  /**
   * This method is used to define the preferred height of a column title.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param rows the number of preferred rows >= 1
   * @exception IllegalArgumentException if <code>rows</code> is < 1
   */
	public ColumnTitleGroupBuilder setTitleRows(Integer rows) {
		getObject().setTitleRows(rows);
		return this;
	}

  /**
   * This method is used to define the fixed height of a column title.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param rows the number of fixed rows >= 1
   * @exception IllegalArgumentException if <code>rows</code> is < 1
   */
	public ColumnTitleGroupBuilder setTitleFixedRows(Integer rows) {
		getObject().setTitleRows(rows);
		getObject().setTitleHeightType(ComponentDimensionType.FIXED);
		return this;
	}

  /**
   * This method is used to define the minimum height of a column title.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param rows the number of minimum rows >= 1
   * @exception IllegalArgumentException if <code>rows</code> is < 1
   */
	public ColumnTitleGroupBuilder setTitleMinRows(Integer rows) {
		getObject().setTitleRows(rows);
		getObject().setTitleHeightType(ComponentDimensionType.EXPAND);
		return this;
	}

  /**
   * Sets the preferred height of a column title.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column title preferred height >= 1
   * @exception IllegalArgumentException if <code>height</code> is < 1
   */
	public ColumnTitleGroupBuilder setTitleHeight(Integer height) {
		getObject().setTitleHeight(height);
		return this;
	}

  /**
   * Sets the fixed height of a column title.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column title fixed height >= 1
   * @exception IllegalArgumentException if <code>height</code> is < 1
   */
	public ColumnTitleGroupBuilder setTitleFixedHeight(Integer height) {
		getObject().setTitleHeight(height);
		getObject().setTitleHeightType(ComponentDimensionType.FIXED);
		return this;
	}

  /**
   * Sets the minimum height of a column title.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param height the column title minimum height >= 1
   * @exception IllegalArgumentException if <code>height</code> is < 1
   */
	public ColumnTitleGroupBuilder setTitleMinHeight(Integer height) {
		getObject().setTitleHeight(height);
		getObject().setTitleHeightType(ComponentDimensionType.EXPAND);
		return this;
	}

	public DRColumnTitleGroup getColumnGridTitleGroup() {
		return build();
	}
}
