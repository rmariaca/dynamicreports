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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.dynamicreports.report.components.googlecharts.geomap.GeoMapDataMode;
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
		Boolean showLegend = (Boolean) element.getParameterValue(GeoMapPrintElement.PARAMETER_SHOW_LEGEND);
		GeoMapDataMode dataMode = (GeoMapDataMode) element.getParameterValue(GeoMapPrintElement.PARAMETER_DATA_MODE);
		String region = (String) element.getParameterValue(GeoMapPrintElement.PARAMETER_REGION);
		@SuppressWarnings("unchecked")
		List<Color> colors = (List<Color>) element.getParameterValue(GeoMapPrintElement.PARAMETER_COLORS);
		List<String> stringColors = null;
		if (colors != null && !colors.isEmpty()) {
			stringColors = new ArrayList<String>();
			for (Color color : colors) {
				stringColors.add(getColorString(color));
			}
		}
		@SuppressWarnings("unchecked")
		Set<GeoMapData> dataset = (Set<GeoMapData>) element.getParameterValue(GeoMapPrintElement.PARAMETER_DATASET);

		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("showLegend", showLegend);
		if (dataMode != null) {
			switch (dataMode) {
			case REGIONS:
				velocityContext.put("dataMode", "regions");
				break;
			case MARKERS:
				velocityContext.put("dataMode", "markers");
				break;
			default:
				break;
			}
		}
		velocityContext.put("id", "map_" + element.hashCode());
		velocityContext.put("region", region);
		velocityContext.put("colors", stringColors);
		velocityContext.put("dataset", dataset);
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

  private String getColorString(Color color) {
    int colorMask = Integer.parseInt("FFFFFF", 16);
    String hex = Integer.toHexString(color.getRGB() & colorMask).toUpperCase();
    return "0x" + ("000000" + hex).substring(hex.length());
  }

	public boolean toExport(JRGenericPrintElement element) {
		return true;
	}
}