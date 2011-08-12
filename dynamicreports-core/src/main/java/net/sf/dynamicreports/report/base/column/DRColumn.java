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

package net.sf.dynamicreports.report.base.column;

import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.column.DRIColumn;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRColumn<T extends DRIComponent> implements DRIColumn<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private String name;
	private T component;

	private DRIExpression<?> titleExpression;
	private DRStyle titleStyle;
	private Integer titleHeight;
	private ComponentDimensionType titleHeightType;
	private Integer titleRows;

	protected DRColumn() {
		this.name = ReportUtils.generateUniqueName("column");
	}

	public DRColumn(T component) {
		Validate.notNull(component, "component must not be null");
		this.name = ReportUtils.generateUniqueName("column");
		this.component = component;
	}

	public T getComponent() {
		return component;
	}

	public DRIExpression<?> getTitleExpression() {
		return titleExpression;
	}

	public void setTitleExpression(DRIExpression<?> titleExpression) {
		this.titleExpression = titleExpression;
	}

	public DRStyle getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(DRStyle titleStyle) {
		this.titleStyle = titleStyle;
	}

  /**
   * Returns the column title height.
   *
   * @return the column title height >= 0
   */
	public Integer getTitleHeight() {
		return titleHeight;
	}

  /**
   * Sets the column title height.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param titleHeight the column title height >= 0
   * @exception IllegalArgumentException if <code>titleHeight</code> is < 0
   */
	public void setTitleHeight(Integer titleHeight) {
		if (titleHeight != null) {
			Validate.isTrue(titleHeight >= 0, "titleHeight must be >= 0");
		}
		this.titleHeight = titleHeight;
	}

	public ComponentDimensionType getTitleHeightType() {
		return titleHeightType;
	}

	public void setTitleHeightType(ComponentDimensionType titleHeightType) {
		this.titleHeightType = titleHeightType;
	}

  /**
   * Returns the number of title rows.
   *
   * @return the number of title rows >= 0
   */
	public Integer getTitleRows() {
		return titleRows;
	}

  /**
   * This method is used to define the height of a column title.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param rows the number of rows >= 0
   * @exception IllegalArgumentException if <code>rows</code> is < 0
   */
	public void setTitleRows(Integer titleRows) {
		if (titleRows != null) {
			Validate.isTrue(titleRows >= 0, "titleRows must be >= 0");
		}
		this.titleRows = titleRows;
	}

	public String getName() {
		return name;
	}
}
