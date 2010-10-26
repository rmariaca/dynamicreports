/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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

package net.sf.dynamicreports.report.base;

import java.util.List;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.definition.DRIField;
import net.sf.dynamicreports.report.definition.DRIMargin;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class AbstractTemplateDesign<T> implements DRITemplateDesign<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	public List<DRIField<?>> getFields() {
		return null;
	}
	
	public String getResourceBundleName() {
		return null;
	}

	public Boolean getIgnorePagination() {
		return null;
	}

	public WhenNoDataType getWhenNoDataType() {
		return null;
	}

	public Boolean getTitleOnANewPage() {
		return null;
	}

	public Boolean getSummaryOnANewPage() {
		return null;
	}

	public Boolean getSummaryWithPageHeaderAndFooter() {
		return null;
	}

	public Boolean getFloatColumnFooter() {
		return null;
	}

	public Integer getPageWidth() {
		return null;
	}

	public Integer getPageHeight() {
		return null;
	}

	public PageOrientation getPageOrientation() {
		return null;
	}

	public DRIMargin getPageMargin() {
		return null;
	}

	public Integer getPageColumnsPerPage() {
		return null;
	}

	public Integer getPageColumnSpace() {
		return null;
	}

	public Integer getPageColumnWidth() {
		return null;
	}

	public int getTitleComponentsCount() {
		return 0;
	}

	public int getPageHeaderComponentsCount() {
		return 0;
	}

	public int getPageFooterComponentsCount() {
		return 0;
	}

	public int getColumnHeaderComponentsCount() {
		return 0;
	}

	public int getColumnFooterComponentsCount() {
		return 0;
	}

	public int getLastPageFooterComponentsCount() {
		return 0;
	}

	public int getSummaryComponentsCount() {
		return 0;
	}

	public int getNoDataComponentsCount() {
		return 0;
	}

	public int getBackgroundComponentsCount() {
		return 0;
	}

	public T getDesign() throws DRException {
		return null;
	}
}
