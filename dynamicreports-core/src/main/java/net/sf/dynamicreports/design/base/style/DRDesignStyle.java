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

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.definition.style.DRIDesignStyle;
import net.sf.dynamicreports.report.ReportUtils;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignStyle extends DRDesignBaseStyle implements DRIDesignStyle {
	private String name;
	private DRDesignStyle parentStyle;
	private List<DRDesignConditionalStyle> conditionalStyles;

	public DRDesignStyle() {
		this.name = ReportUtils.generateUniqueName("style");
		this.conditionalStyles = new ArrayList<DRDesignConditionalStyle>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public DRDesignStyle getParentStyle() {
		return parentStyle;
	}

	public void setParentStyle(DRDesignStyle parentStyle) {
		this.parentStyle = parentStyle;
	}

	@Override
	public List<DRDesignConditionalStyle> getConditionalStyles() {
		return conditionalStyles;
	}

	public void setConditionalStyles(List<DRDesignConditionalStyle> conditionalStyles) {
		this.conditionalStyles = conditionalStyles;
	}

	public void addConditionalStyle(DRDesignConditionalStyle conditionalStyle) {
		this.conditionalStyles.add(conditionalStyle);
	}
}
