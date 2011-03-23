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

package net.sf.dynamicreports.report.definition.column;

import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.definition.DRIValue;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.style.DRIStyle;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DRIBooleanColumn extends DRIExpression<Boolean>, DRIColumn<DRIComponent>, DRIValue<Boolean> {

	public Integer getWidth();

	public Integer getHeight();

	public ComponentDimensionType getWidthType();

	public ComponentDimensionType getHeightType();

	public DRIExpression<Boolean> getValueExpression();

	public BooleanComponentType getComponentType();

	public Integer getImageWidth();

	public Integer getImageHeight();

	public DRIStyle getStyle();

	public DRISimpleExpression<Boolean> getPrintWhenExpression();
}
