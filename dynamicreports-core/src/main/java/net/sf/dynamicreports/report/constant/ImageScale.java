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

package net.sf.dynamicreports.report.constant;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public enum ImageScale {
	/**
	 * A constant value specifying that if the actual image is larger than the image element size, it will be cut off so
	 * that it keeps its original resolution, and only the region that fits the specified size will be displayed.
	 */
	NO_RESIZE,
	/**
	 * A constant value specifying that if the dimensions of the actual image do not fit those specified for the
	 * image element that displays it, the image can be forced to obey them and stretch itself so that it fits
	 * in the designated output area.
	 */
	FILL,
	/**
	 * A constant value specifying that if the actual image does not fit into the image element, it can be adapted
	 * to those dimensions without needing to change its original proportions.
	 */
	FILL_PROPORTIONALLY	
}
