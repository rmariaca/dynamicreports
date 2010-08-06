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

import net.sf.dynamicreports.report.base.component.DRDimensionComponent;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings({"unchecked", "ucd"})
public abstract class DimensionComponentBuilder<T extends DimensionComponentBuilder<T, U>, U extends DRDimensionComponent> extends ComponentBuilder<T, U> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	public DimensionComponentBuilder(U component) {
		super(component);
	}
	
  /**
   * Sets the component preferred dimension. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param width the component preferred width >= 1
   * @param height the component preferred height >= 1
   * @exception IllegalArgumentException if <code>width</code> is < 1
   * @exception IllegalArgumentException if <code>height</code> is < 1
   */
	public T setDimension(Integer width, Integer height) {
		getObject().setWidth(width);
		getObject().setHeight(width);
		return (T) this;
	}
	
  /**
   * Sets the component fixed dimension. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param width the component fixed width >= 1
   * @param height the component fixed height >= 1
   * @exception IllegalArgumentException if <code>width</code> is < 1
   * @exception IllegalArgumentException if <code>height</code> is < 1
   */
	public T setFixedDimension(Integer width, Integer height) {
		getObject().setWidth(width);
		getObject().setHeight(width);
		getObject().setWidthType(ComponentDimensionType.FIXED);
		getObject().setHeightType(ComponentDimensionType.FIXED);
		return (T) this;
	}
	
  /**
   * Sets the component minimum dimension. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param width the component minimum width >= 1
   * @param height the component minimum height >= 1
   * @exception IllegalArgumentException if <code>width</code> is < 1
   * @exception IllegalArgumentException if <code>height</code> is < 1
   */
	public T setMinDimension(Integer width, Integer height) {
		getObject().setWidth(width);
		getObject().setHeight(width);
		getObject().setWidthType(ComponentDimensionType.EXPAND);
		getObject().setHeightType(ComponentDimensionType.EXPAND);
		return (T) this;
	}
	
  /**
   * Sets the component preferred width. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param width the component preferred width >= 1
   * @exception IllegalArgumentException if <code>width</code> is < 1
   */
	public T setWidth(Integer width) {
		getObject().setWidth(width);
		return (T) this;
	}
	
  /**
   * Sets the component fixed width. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param width the component fixed width >= 1
   * @exception IllegalArgumentException if <code>width</code> is < 1
   */
	public T setFixedWidth(Integer width) {
		getObject().setWidth(width);
		getObject().setWidthType(ComponentDimensionType.FIXED);
		return (T) this;
	}
	
  /**
   * Sets the component minimum width. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param width the component minimum width >= 1
   * @exception IllegalArgumentException if <code>width</code> is < 1
   */
	public T setMinWidth(Integer width) {
		getObject().setWidth(width);
		getObject().setWidthType(ComponentDimensionType.EXPAND);
		return (T) this;
	}
	
  /**
   * Sets the component preferred height. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param height the component preferred height >= 1
   * @exception IllegalArgumentException if <code>height</code> is < 1
   */
	public T setHeight(Integer height) {
		getObject().setHeight(height);
		return (T) this;
	}
	
  /**
   * Sets the component fixed height. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param height the component fixed height >= 1
   * @exception IllegalArgumentException if <code>height</code> is < 1
   */
	public T setFixedHeight(Integer height) {
		getObject().setHeight(height);
		getObject().setHeightType(ComponentDimensionType.FIXED);
		return (T) this;
	}
	
  /**
   * Sets the component minimum height. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param height the component minimum height >= 1
   * @exception IllegalArgumentException if <code>height</code> is < 1
   */
	public T setMinHeight(Integer height) {
		getObject().setHeight(height);
		getObject().setHeightType(ComponentDimensionType.EXPAND);
		return (T) this;
	}
}
