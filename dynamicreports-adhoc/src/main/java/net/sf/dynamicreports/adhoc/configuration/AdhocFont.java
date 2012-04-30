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

import java.io.Serializable;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocFont implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Boolean bold;
	private Boolean italic;
	private Boolean underline;
	private Boolean strikeThrough;
	private Integer size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getBold() {
		return bold;
	}

	public void setBold(Boolean bold) {
		this.bold = bold;
	}

	public Boolean getItalic() {
		return italic;
	}

	public void setItalic(Boolean italic) {
		this.italic = italic;
	}

	public Boolean getUnderline() {
		return underline;
	}

	public void setUnderline(Boolean underline) {
		this.underline = underline;
	}

	public Boolean getStrikeThrough() {
		return strikeThrough;
	}

	public void setStrikeThrough(Boolean strikeThrough) {
		this.strikeThrough = strikeThrough;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public AdhocFont clone() {
		AdhocFont clone;
		try {
			clone = (AdhocFont) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}

		return clone;
	}

}
