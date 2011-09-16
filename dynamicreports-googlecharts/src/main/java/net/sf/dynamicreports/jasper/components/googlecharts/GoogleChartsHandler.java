package net.sf.dynamicreports.jasper.components.googlecharts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.component.ComponentXmlWriter;
import net.sf.jasperreports.engine.component.ComponentsEnvironment;
import net.sf.jasperreports.engine.component.XmlDigesterConfigurer;
import net.sf.jasperreports.engine.export.GenericElementHandler;
import net.sf.jasperreports.engine.export.GenericElementHandlerBundle;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.util.JRXmlWriteHelper;
import net.sf.jasperreports.engine.util.XmlNamespace;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

import org.apache.commons.digester.Digester;

public class GoogleChartsHandler implements XmlDigesterConfigurer, ComponentXmlWriter, GenericElementHandlerBundle {
	private Map<String, Class<? extends Component>> components;
	private Map<String, GenericElementHandler> handlers;

	public GoogleChartsHandler() {
		components = new HashMap<String, Class<? extends Component>>();
		handlers = new HashMap<String, GenericElementHandler>();
	}

	public void add(String name, Class<? extends Component> componentClass, GenericElementHandler handler) {
		components.put(name, componentClass);
		handlers.put(name, handler);
	}

	public void configureDigester(Digester digester) {
		for (String name : components.keySet()) {
			String mapPattern = "*/componentElement/googleCharts/" + name;
			digester.addObjectCreate(mapPattern, components.get(name));
		}
	}

	public void writeToXml(ComponentKey componentKey, Component component, JRXmlWriter reportWriter) throws IOException {
		if (components.containsKey(componentKey.getName())) {
			JRXmlWriteHelper writer = reportWriter.getXmlWriteHelper();

			String namespaceURI = componentKey.getNamespace();
			String schemaLocation = ComponentsEnvironment.getComponentsBundle(namespaceURI).getXmlParser().getPublicSchemaLocation();
			XmlNamespace namespace = new XmlNamespace(namespaceURI, componentKey.getNamespacePrefix(), schemaLocation);

			writer.startElement(componentKey.getName(), namespace);

			writer.closeElement();
		}
	}

	public String getNamespace() {
		return GoogleChartsExtensionsRegistryFactory.NAMESPACE;
	}

	public GenericElementHandler getHandler(String elementName, String exporterKey) {
		if (handlers.containsKey(elementName)) {
			if (JRHtmlExporter.HTML_EXPORTER_KEY.equals(exporterKey)
					|| JRXhtmlExporter.XHTML_EXPORTER_KEY.equals(exporterKey)) {
				return handlers.get(elementName);
			}
		}
		return null;
	}
}
