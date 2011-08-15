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

package net.sf.dynamicreports.report.definition;

import java.io.Serializable;

import net.sf.dynamicreports.report.constant.GroupFooterPosition;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.definition.component.DRITextField;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.style.DRIStyle;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DRIGroup extends Serializable {

	public String getName();

	public DRITextField<?> getValueField();

	public DRIExpression<?> getTitleExpression();

	public DRIStyle getTitleStyle();

	public Integer getTitleWidth();

	public GroupHeaderLayout getHeaderLayout();

	public Boolean getHideColumn();

	public Boolean getGroupByDataType();

	public Boolean getShowColumnHeaderAndFooter();

	public DRIExpression<Boolean> getPrintSubtotalsWhenExpression();

	public Integer getPadding();

	public Boolean getStartInNewPage();

	public Boolean getStartInNewColumn();

	public Boolean getReprintHeaderOnEachPage();

	public Boolean getResetPageNumber();

	public Integer getMinHeightToStartNewPage();

	public GroupFooterPosition getFooterPosition();

	public Boolean getKeepTogether();

	public DRIBand getHeaderBand();

	public DRIBand getFooterBand();
}
