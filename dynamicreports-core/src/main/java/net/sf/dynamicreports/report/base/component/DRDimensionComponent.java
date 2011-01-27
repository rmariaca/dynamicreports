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

package net.sf.dynamicreports.report.base.component;

import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.component.DRIDimensionComponent;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class DRDimensionComponent extends DRComponent implements DRIDimensionComponent {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Integer width;
	private Integer height;
	private ComponentDimensionType widthType;
	private ComponentDimensionType heightType;
	
  /**
   * Returns the component width.
   *
   * @return the component width >= 1
   */
	public Integer getWidth() {
		return width;
	}

  /**
   * Sets the component width. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param width the component width >= 1
   * @exception IllegalArgumentException if <code>width</code> is < 1
   */
	public void setWidth(Integer width) {		
		if (width != null) {
			Validate.isTrue(width >= 1, "width must be >= 1");
		}
		this.width = width;
	}

  /**
   * Returns the component height.
   *
   * @return the component height >= 1
   */
	public Integer getHeight() {
		return height;
	}

  /**
   * Sets the component height. 
   * @see net.sf.dynamicreports.report.builder.Units
   * 
   * @param height the component height >= 1
   * @exception IllegalArgumentException if <code>height</code> is < 1
   */
	public void setHeight(Integer height) {
		if (height != null) {
			Validate.isTrue(height >= 1, "height must be >= 1");
		}
		this.height = height;
	}

	public ComponentDimensionType getWidthType() {
		return widthType;
	}

	public void setWidthType(ComponentDimensionType widthType) {
		this.widthType = widthType;
	}

	public ComponentDimensionType getHeightType() {
		return heightType;
	}

	public void setHeightType(ComponentDimensionType heightType) {
		this.heightType = heightType;
	}	
}
