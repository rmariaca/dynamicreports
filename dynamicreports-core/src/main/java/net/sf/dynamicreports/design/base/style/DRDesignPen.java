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

package net.sf.dynamicreports.design.base.style;

import java.awt.Color;

import net.sf.dynamicreports.design.definition.style.DRIDesignPen;
import net.sf.dynamicreports.report.constant.LineStyle;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignPen implements DRIDesignPen {	
	private Float lineWidth;
	private LineStyle lineStyle;
	private Color lineColor;
	
	public Float getLineWidth() {
		return lineWidth;
	}
	
	public void setLineWidth(Float lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	public LineStyle getLineStyle() {
		return lineStyle;
	}
	
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}
	
	public Color getLineColor() {
		return lineColor;
	}
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}	
}
