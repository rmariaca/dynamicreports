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

import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.export.GenericElementHtmlHandler;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterContext;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.util.JRColorUtil;
import net.sf.jasperreports.web.util.VelocityUtil;

import org.apache.velocity.VelocityContext;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GeoMapElementHtmlHandler implements GenericElementHtmlHandler {

	private static final String GEOMAP_ELEMENT_HTML_TEMPLATE = "net/sf/dynamicreports/jasper/components/googlecharts/geomap/GeoMapElementHtmlTemplate.vm";

	public String getHtmlFragment(JRHtmlExporterContext context, JRGenericPrintElement element)	{
		//Float latitude = (Float)element.getParameterValue(MapPrintElement.PARAMETER_LATITUDE);
		//latitude = latitude == null ? MapPrintElement.DEFAULT_LATITUDE : latitude;

		VelocityContext velocityContext = new VelocityContext();
		//velocityContext.put("latitude", latitude);
		if(context.getExporter() instanceof JRXhtmlExporter) {
			velocityContext.put("xhtml", "xhtml");
			velocityContext.put("elementX", ((JRXhtmlExporter)context.getExporter()).toSizeUnit(element.getX()));
			velocityContext.put("elementY", ((JRXhtmlExporter)context.getExporter()).toSizeUnit(element.getY()));
		}
		else {
			velocityContext.put("elementX", ((JRHtmlExporter)context.getExporter()).toSizeUnit(element.getX()));
			velocityContext.put("elementY", ((JRHtmlExporter)context.getExporter()).toSizeUnit(element.getY()));
		}
		velocityContext.put("elementWidth", element.getWidth());
		velocityContext.put("elementHeight", element.getHeight());

		if (element.getModeValue() == ModeEnum.OPAQUE) {
			velocityContext.put("backgroundColor", JRColorUtil.getColorHexa(element.getBackcolor()));
		}
		return VelocityUtil.processTemplate(GEOMAP_ELEMENT_HTML_TEMPLATE, velocityContext);
	}

	public boolean toExport(JRGenericPrintElement element) {
		return true;
	}
}