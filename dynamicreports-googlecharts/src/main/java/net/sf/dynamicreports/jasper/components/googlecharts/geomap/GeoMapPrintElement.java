package net.sf.dynamicreports.jasper.components.googlecharts.geomap;

import net.sf.dynamicreports.jasper.components.googlecharts.GoogleChartsExtensionsRegistryFactory;
import net.sf.jasperreports.engine.JRGenericElementType;

public class GeoMapPrintElement {
	public static final String GEOMAP_ELEMENT_NAME = "geoMap";
	public static final JRGenericElementType GEOMAP_ELEMENT_TYPE = new JRGenericElementType(GoogleChartsExtensionsRegistryFactory.NAMESPACE, GEOMAP_ELEMENT_NAME);

}