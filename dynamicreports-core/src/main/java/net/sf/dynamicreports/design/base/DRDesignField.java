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

import net.sf.dynamicreports.design.definition.DRIDesignField;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignField implements DRIDesignField {		
	private String name;
	private Class<?> valueClass;
	private boolean external;

	public DRDesignField() {
		this.external = false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Class<?> getValueClass() {
		return valueClass;
	}

	public void setValueClass(Class<?> valueClass) {
		this.valueClass = valueClass;
	}
		
	public boolean isExternal() {
		return external;
	}
	
	public void setExternal(boolean external) {
		this.external = external;
	}
}
