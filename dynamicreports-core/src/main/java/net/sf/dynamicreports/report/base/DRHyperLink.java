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

package net.sf.dynamicreports.report.base;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIHyperLink;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRHyperLink implements DRIHyperLink {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private DRISimpleExpression<String> linkExpression;	
	private DRISimpleExpression<String> tooltipExpression;
	
	public DRISimpleExpression<String> getLinkExpression() {
		return linkExpression;
	}
	
	public void setLinkExpression(DRISimpleExpression<String> linkExpression) {
		Validate.notNull(linkExpression, "linkExpression must not be null");
		this.linkExpression = linkExpression;
	}

	public DRISimpleExpression<String> getTooltipExpression() {
		return tooltipExpression;
	}
	
	public void setTooltipExpression(DRISimpleExpression<String> tooltipExpression) {
		this.tooltipExpression = tooltipExpression;
	}
}
