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

package net.sf.dynamicreports.design.base.chart;

import java.awt.Color;

import net.sf.dynamicreports.design.base.style.DRDesignFont;
import net.sf.dynamicreports.design.definition.chart.DRIDesignChartSubtitle;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignChartSubtitle implements DRIDesignChartSubtitle {		
	private Color color;
	private DRDesignFont font;
	private DRIDesignSimpleExpression title;
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public DRDesignFont getFont() {
		return font;
	}
	
	public void setFont(DRDesignFont font) {
		this.font = font;
	}
	
	public DRIDesignSimpleExpression getTitle() {
		return title;
	}
	
	public void setTitle(DRIDesignSimpleExpression title) {
		this.title = title;
	}	
}
