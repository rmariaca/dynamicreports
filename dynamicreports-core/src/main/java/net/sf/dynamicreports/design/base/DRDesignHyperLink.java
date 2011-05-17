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

package net.sf.dynamicreports.design.base;

import net.sf.dynamicreports.design.definition.DRIDesignHyperLink;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.report.constant.HyperLinkType;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignHyperLink implements DRIDesignHyperLink {
	private DRIDesignSimpleExpression anchorNameExpression;
	private DRIDesignSimpleExpression anchorExpression;
	private DRIDesignSimpleExpression pageExpression;
	private DRIDesignSimpleExpression referenceExpression;
	private DRIDesignSimpleExpression tooltipExpression;
	private HyperLinkType hyperLinkType;

	public DRIDesignSimpleExpression getAnchorNameExpression() {
		return anchorNameExpression;
	}

	public void setAnchorNameExpression(DRIDesignSimpleExpression anchorNameExpression) {
		this.anchorNameExpression = anchorNameExpression;
	}

	public DRIDesignSimpleExpression getAnchorExpression() {
		return anchorExpression;
	}

	public void setAnchorExpression(DRIDesignSimpleExpression anchorExpression) {
		this.anchorExpression = anchorExpression;
	}

	public DRIDesignSimpleExpression getPageExpression() {
		return pageExpression;
	}

	public void setPageExpression(DRIDesignSimpleExpression pageExpression) {
		this.pageExpression = pageExpression;
	}

	public DRIDesignSimpleExpression getReferenceExpression() {
		return referenceExpression;
	}

	public void setReferenceExpression(DRIDesignSimpleExpression referenceExpression) {
		this.referenceExpression = referenceExpression;
	}

	public DRIDesignSimpleExpression getTooltipExpression() {
		return tooltipExpression;
	}

	public void setTooltipExpression(DRIDesignSimpleExpression tooltipExpression) {
		this.tooltipExpression = tooltipExpression;
	}

	public HyperLinkType getType() {
		return hyperLinkType;
	}

	public void setType(HyperLinkType hyperLinkType) {
		this.hyperLinkType = hyperLinkType;
	}
}
