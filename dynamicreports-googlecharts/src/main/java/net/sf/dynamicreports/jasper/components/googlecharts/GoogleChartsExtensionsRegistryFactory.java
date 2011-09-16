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

package net.sf.dynamicreports.jasper.components.googlecharts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.sf.dynamicreports.jasper.components.googlecharts.geomap.GeoMapCompiler;
import net.sf.dynamicreports.jasper.components.googlecharts.geomap.GeoMapComponent;
import net.sf.dynamicreports.jasper.components.googlecharts.geomap.GeoMapElementHtmlHandler;
import net.sf.dynamicreports.jasper.components.googlecharts.geomap.GeoMapFillFactory;
import net.sf.dynamicreports.jasper.components.googlecharts.geomap.GeoMapPrintElement;
import net.sf.dynamicreports.report.components.CustomComponentTransform;
import net.sf.dynamicreports.report.components.googlecharts.geomap.GeoMapTransform;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.component.ComponentManager;
import net.sf.jasperreports.engine.component.ComponentsBundle;
import net.sf.jasperreports.engine.component.DefaultComponentManager;
import net.sf.jasperreports.engine.component.DefaultComponentXmlParser;
import net.sf.jasperreports.engine.component.DefaultComponentsBundle;
import net.sf.jasperreports.engine.export.GenericElementHandlerBundle;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GoogleChartsExtensionsRegistryFactory implements ExtensionsRegistryFactory {
	public static final String NAMESPACE = "http://dynamicreports.sourceforge.net/googlecharts";
	public static final String XSD_LOCATION = "http://dynamicreports.sourceforge.net/xsd/googlecharts.xsd";
	public static final String XSD_RESOURCE = "net/sf/dynamicreports/jasper/components/googlecharts/googlecharts.xsd";

	private static final ExtensionsRegistry REGISTRY;

	static {
		final DefaultComponentsBundle bundle = new DefaultComponentsBundle();
		final GoogleChartsHandler handler = new GoogleChartsHandler();
		final List<CustomComponentTransform<?, ?>> transforms = new ArrayList<CustomComponentTransform<?, ?>>();

		GoogleChartsDesignConverter designConverter = new GoogleChartsDesignConverter();

		DefaultComponentXmlParser parser = new DefaultComponentXmlParser();
		parser.setNamespace(NAMESPACE);
		parser.setPublicSchemaLocation(XSD_LOCATION);
		parser.setInternalSchemaResource(XSD_RESOURCE);
		parser.setDigesterConfigurer(handler);
		bundle.setXmlParser(parser);

		HashMap<String, ComponentManager> componentManagers = new HashMap<String, ComponentManager>();
		bundle.setComponentManagers(componentManagers);

		REGISTRY = new ExtensionsRegistry() {
			public List<?> getExtensions(Class<?> extensionType) {
				if (ComponentsBundle.class.equals(extensionType)) {
					return Collections.singletonList(bundle);
				}
				if (GenericElementHandlerBundle.class.equals(extensionType)) {
					return Collections.singletonList(handler);
				}
				if (CustomComponentTransform.class.equals(extensionType)) {
					return transforms;
				}
				return null;
			}
		};

		//geoMap
		handler.add(GeoMapPrintElement.GEOMAP_ELEMENT_NAME, GeoMapComponent.class, new GeoMapElementHtmlHandler());
		DefaultComponentManager geoMapManager = new DefaultComponentManager();
		geoMapManager.setDesignConverter(designConverter);
		geoMapManager.setComponentCompiler(new GeoMapCompiler());
		geoMapManager.setComponentXmlWriter(handler);
		geoMapManager.setComponentFillFactory(new GeoMapFillFactory());
		componentManagers.put(GeoMapPrintElement.GEOMAP_ELEMENT_NAME, geoMapManager);
		transforms.add(new GeoMapTransform());
	}

	public ExtensionsRegistry createRegistry(String registryId, JRPropertiesMap properties) {
		return REGISTRY;
	}
}