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

import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.definition.DRIDesignBand;
import net.sf.dynamicreports.report.constant.SplitType;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignBand implements DRIDesignBand {
	private String name;
	private SplitType splitType;
	private DRDesignList list;
	private DRDesignComponent bandComponent;
	
	public DRDesignBand(String name) {
		this.name = name;		
	}
	
	public String getName() {
		return name;
	}
	
	public SplitType getSplitType() {
		return splitType;
	}
	
	public void setSplitType(SplitType splitType) {
		this.splitType = splitType;
	}
		
	public void setList(DRDesignList list) {
		this.list = list;
	}
	
	public DRDesignList getList() {
		return list;
	}
	
	public void addComponent(DRDesignComponent component) {
		list.addComponent(component);
	}

	public void addComponent(int index, DRDesignComponent component) {
		list.addComponent(index, component);
	}
	
	public DRDesignComponent getBandComponent() {
		return bandComponent;
	}

	public void setBandComponent(DRDesignComponent component) {
		this.bandComponent = component;
	}	
}
