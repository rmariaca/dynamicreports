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
	public boolean equals(Object obj) {
		boolean equals = super.equals(obj);
		if (!equals) {
			return false;
		}
		if (obj == null)
			return false;
		if (!(obj instanceof AdhocChart))
			return false;

		AdhocChart object = (AdhocChart) obj;
		if (!(title == null ? object.getTitle() == null : title.equals(object.getTitle()))) {
			return false;
		}
		if (!(titleFont == null ? object.getTitleFont() == null : titleFont.equals(object.getTitleFont()))) {
			return false;
		}
		if (!(titleColor == null ? object.getTitleColor() == null : titleColor.equals(object.getTitleColor()))) {
			return false;
		}
		if (!(showLegend == null ? object.getShowLegend() == null : showLegend.equals(object.getShowLegend()))) {
			return false;
		}

		return true;
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
