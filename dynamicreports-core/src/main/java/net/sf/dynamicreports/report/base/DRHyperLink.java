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
import net.sf.dynamicreports.report.constant.HyperLinkType;
import net.sf.dynamicreports.report.definition.DRIHyperLink;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRHyperLink implements DRIHyperLink {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private DRISimpleExpression<String> anchorNameExpression;
	private DRISimpleExpression<String> anchorExpression;
	private DRISimpleExpression<String> pageExpression;
	private DRISimpleExpression<String> referenceExpression;
	private DRISimpleExpression<String> tooltipExpression;
	private HyperLinkType hyperLinkType;

	public DRISimpleExpression<String> getAnchorNameExpression() {
		return anchorNameExpression;
	}

	public void setAnchorNameExpression(DRISimpleExpression<String> anchorNameExpression) {
		this.anchorNameExpression = anchorNameExpression;
	}

	public DRISimpleExpression<String> getAnchorExpression() {
		return anchorExpression;
	}

	public void setAnchorExpression(DRISimpleExpression<String> anchorExpression) {
		this.anchorExpression = anchorExpression;
	}

	public DRISimpleExpression<String> getPageExpression() {
		return pageExpression;
	}

	public void setPageExpression(DRISimpleExpression<String> pageExpression) {
		this.pageExpression = pageExpression;
	}

	public DRISimpleExpression<String> getReferenceExpression() {
		return referenceExpression;
	}

	public void setReferenceExpression(DRISimpleExpression<String> referenceExpression) {
		this.referenceExpression = referenceExpression;
	}

	public DRISimpleExpression<String> getTooltipExpression() {
		return tooltipExpression;
	}

	public void setTooltipExpression(DRISimpleExpression<String> tooltipExpression) {
		this.tooltipExpression = tooltipExpression;
	}

	public HyperLinkType getType() {
		return hyperLinkType;
	}

	public void setType(HyperLinkType hyperLinkType) {
		this.hyperLinkType = hyperLinkType;
	}
}
