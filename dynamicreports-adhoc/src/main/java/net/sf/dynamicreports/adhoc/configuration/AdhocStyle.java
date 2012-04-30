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
import java.io.Serializable;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocStyle implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private AdhocFont font;
	private AdhocPen topBorder;
	private AdhocPen leftBorder;
	private AdhocPen bottomBorder;
	private AdhocPen rightBorder;
	private Color foregroundColor;
	private Color backgroundColor;
	private AdhocHorizontalAlignment horizontalAlignment;
	private AdhocVerticalAlignment verticalAlignment;
	private String pattern;

	public AdhocFont getFont() {
		return font;
	}

	public void setFont(AdhocFont font) {
		this.font = font;
	}

	public AdhocPen getTopBorder() {
		return topBorder;
	}

	public void setTopBorder(AdhocPen topBorder) {
		this.topBorder = topBorder;
	}

	public AdhocPen getLeftBorder() {
		return leftBorder;
	}

	public void setLeftBorder(AdhocPen leftBorder) {
		this.leftBorder = leftBorder;
	}

	public AdhocPen getBottomBorder() {
		return bottomBorder;
	}

	public void setBottomBorder(AdhocPen bottomBorder) {
		this.bottomBorder = bottomBorder;
	}

	public AdhocPen getRightBorder() {
		return rightBorder;
	}

	public void setRightBorder(AdhocPen rightBorder) {
		this.rightBorder = rightBorder;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public AdhocHorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(AdhocHorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public AdhocVerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(AdhocVerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public AdhocStyle clone() {
		AdhocStyle clone;
		try {
			clone = (AdhocStyle) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}

		if (font != null) {
			clone.font = font.clone();
		}
		if (topBorder != null) {
			clone.topBorder = topBorder.clone();
		}
		if (leftBorder != null) {
			clone.leftBorder = leftBorder.clone();
		}
		if (bottomBorder != null) {
			clone.bottomBorder = bottomBorder.clone();
		}
		if (rightBorder != null) {
			clone.rightBorder = rightBorder.clone();
		}

		return clone;
	}

}
