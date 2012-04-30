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

package net.sf.dynamicreports.adhoc.configuration;

import java.awt.Color;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocChart extends AdhocComponent {
	private static final long serialVersionUID = 1L;

	private String title;
	private AdhocFont titleFont;
	private Color titleColor;
	private Boolean showLegend;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AdhocFont getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(AdhocFont titleFont) {
		this.titleFont = titleFont;
	}

	public Color getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(Color titleColor) {
		this.titleColor = titleColor;
	}

	public Boolean getShowLegend() {
		return showLegend;
	}

	public void setShowLegend(Boolean showLegend) {
		this.showLegend = showLegend;
	}

	@Override
	public AdhocChart clone() {
		AdhocChart clone = (AdhocChart) super.clone();

		if (titleFont != null) {
			clone.titleFont = titleFont.clone();
		}

		return clone;
	}

}
