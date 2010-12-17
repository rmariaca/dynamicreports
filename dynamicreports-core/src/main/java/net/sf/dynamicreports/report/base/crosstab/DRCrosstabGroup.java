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

package net.sf.dynamicreports.report.base.crosstab;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.CrosstabTotalPosition;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabGroup;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class DRCrosstabGroup implements DRICrosstabGroup {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private String name;	
	private CrosstabTotalPosition totalPosition;	
	private DRCrosstabBucket bucket;	
	private DRCrosstabCellContent header;	
	private DRCrosstabCellContent totalHeader;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public CrosstabTotalPosition getTotalPosition() {
		return totalPosition;
	}
	
	public void setTotalPosition(CrosstabTotalPosition totalPosition) {
		this.totalPosition = totalPosition;
	}
	
	public DRCrosstabBucket getBucket() {
		return bucket;
	}
	
	public void setBucket(DRCrosstabBucket bucket) {
		this.bucket = bucket;
	}
	
	public DRCrosstabCellContent getHeader() {
		return header;
	}
	
	public void setHeader(DRCrosstabCellContent header) {
		this.header = header;
	}
	
	public DRCrosstabCellContent getTotalHeader() {
		return totalHeader;
	}
	
	public void setTotalHeader(DRCrosstabCellContent totalHeader) {
		this.totalHeader = totalHeader;
	}
}