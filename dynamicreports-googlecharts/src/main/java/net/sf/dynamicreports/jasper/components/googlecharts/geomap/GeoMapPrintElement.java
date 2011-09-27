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

package net.sf.dynamicreports.jasper.components.googlecharts.geomap;

import net.sf.dynamicreports.jasper.components.googlecharts.GoogleChartsExtensionsRegistryFactory;
import net.sf.jasperreports.engine.JRGenericElementType;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GeoMapPrintElement {
	public static final String GEOMAP_ELEMENT_NAME = "geoMap";
	public static final JRGenericElementType GEOMAP_ELEMENT_TYPE = new JRGenericElementType(GoogleChartsExtensionsRegistryFactory.NAMESPACE, GEOMAP_ELEMENT_NAME);

	public static final String PARAMETER_SHOW_LEGEND = "showLegend";
	public static final String PARAMETER_DATA_MODE = "dataMode";
	public static final String PARAMETER_REGION = "region";
	public static final String PARAMETER_COLORS = "colors";
	public static final String PARAMETER_DATASET = "dataset";
}