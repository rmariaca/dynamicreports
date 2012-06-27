/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
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

package net.sf.dynamicreports.design.base.style;

import java.util.List;

import net.sf.dynamicreports.design.definition.style.DRIDesignParagraph;
import net.sf.dynamicreports.design.definition.style.DRIDesignTabStop;
import net.sf.dynamicreports.report.constant.LineSpacing;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignParagraph implements DRIDesignParagraph {
	private LineSpacing lineSpacing;
	private Float lineSpacingSize;
	private Integer firstLineIndent;
	private Integer leftIndent;
	private Integer rightIndent;
	private Integer spacingBefore;
	private Integer spacingAfter;
	private Integer tabStopWidth;
	private List<DRIDesignTabStop> tabStops;

	@Override
	public LineSpacing getLineSpacing() {
		return lineSpacing;
	}

	public void setLineSpacing(LineSpacing lineSpacing) {
		this.lineSpacing = lineSpacing;
	}

	@Override
	public Float getLineSpacingSize() {
		return lineSpacingSize;
	}

	public void setLineSpacingSize(Float lineSpacingSize) {
		this.lineSpacingSize = lineSpacingSize;
	}

	@Override
	public Integer getFirstLineIndent() {
		return firstLineIndent;
	}

	public void setFirstLineIndent(Integer firstLineIndent) {
		this.firstLineIndent = firstLineIndent;
	}

	@Override
	public Integer getLeftIndent() {
		return leftIndent;
	}

	public void setLeftIndent(Integer leftIndent) {
		this.leftIndent = leftIndent;
	}

	@Override
	public Integer getRightIndent() {
		return rightIndent;
	}

	public void setRightIndent(Integer rightIndent) {
		this.rightIndent = rightIndent;
	}

	@Override
	public Integer getSpacingBefore() {
		return spacingBefore;
	}

	public void setSpacingBefore(Integer spacingBefore) {
		this.spacingBefore = spacingBefore;
	}

	@Override
	public Integer getSpacingAfter() {
		return spacingAfter;
	}

	public void setSpacingAfter(Integer spacingAfter) {
		this.spacingAfter = spacingAfter;
	}

	@Override
	public Integer getTabStopWidth() {
		return tabStopWidth;
	}

	public void setTabStopWidth(Integer tabStopWidth) {
		this.tabStopWidth = tabStopWidth;
	}

	@Override
	public List<DRIDesignTabStop> getTabStops() {
		return tabStops;
	}

	public void setTabStops(List<DRIDesignTabStop> tabStops) {
		this.tabStops = tabStops;
	}
}
