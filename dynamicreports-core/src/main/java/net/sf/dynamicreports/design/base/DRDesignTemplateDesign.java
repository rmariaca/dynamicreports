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

import net.sf.dynamicreports.design.definition.DRIDesignTemplateDesign;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignTemplateDesign implements DRIDesignTemplateDesign {
	private DRITemplateDesign<?> templateDesign;

	public DRDesignTemplateDesign (DRITemplateDesign<?> templateDesign) {
		this.templateDesign = templateDesign;		
	}
	
	public int getTitleComponentsCount() {
		return templateDesign.getTitleComponentsCount();
	}

	public int getPageHeaderComponentsCount() {
		return templateDesign.getPageHeaderComponentsCount();
	}

	public int getPageFooterComponentsCount() {
		return templateDesign.getPageFooterComponentsCount();
	}

	public int getColumnHeaderComponentsCount() {
		return templateDesign.getColumnHeaderComponentsCount();
	}

	public int getColumnFooterComponentsCount() {
		return templateDesign.getColumnFooterComponentsCount();
	}

	public int getLastPageFooterComponentsCount() {
		return templateDesign.getLastPageFooterComponentsCount();
	}

	public int getSummaryComponentsCount() {
		return templateDesign.getSummaryComponentsCount();
	}

	public int getNoDataComponentsCount() {
		return templateDesign.getNoDataComponentsCount();
	}

	public int getBackgroundComponentsCount() {
		return templateDesign.getBackgroundComponentsCount();
	}
	
	public Object getDesign() throws DRException {
		return templateDesign.getDesign();
	}
}
