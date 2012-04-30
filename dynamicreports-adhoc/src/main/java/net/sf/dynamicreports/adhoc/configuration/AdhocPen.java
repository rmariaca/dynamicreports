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
public class AdhocPen implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private Float lineWidth;
	private Color color;

	public Float getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Float lineWidth) {
		this.lineWidth = lineWidth;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public AdhocPen clone() {
		AdhocPen clone;
		try {
			clone = (AdhocPen) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}

		return clone;
	}

}
