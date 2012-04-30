package net.sf.dynamicreports.adhoc;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.transformation.AdhocToXmlTransform;
import net.sf.dynamicreports.adhoc.transformation.XmlToAdhocTransform;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocConfiguration;
import net.sf.dynamicreports.report.exception.DRException;

public class AdhocManager {
	private static AdhocToXmlTransform adhocToXmlTransform = new AdhocToXmlTransform();
	private static XmlToAdhocTransform xmlToAdhocTransform = new XmlToAdhocTransform();

	public static void saveConfiguration(AdhocConfiguration adhocConfiguration, OutputStream os) throws DRException {
		XmlAdhocConfiguration xmlAdhocConfiguration = adhocToXmlTransform.transform(adhocConfiguration);
		try {
			Marshaller marshaller = JAXBContext.newInstance(XmlAdhocConfiguration.class).createMarshaller();
			JAXBElement<XmlAdhocConfiguration> element = new net.sf.dynamicreports.adhoc.xmlconfiguration.ObjectFactory().createConfiguration(xmlAdhocConfiguration);
			marshaller.marshal(element, new StreamResult(os));
		} catch (JAXBException e) {
			throw new DRException(e);
		}
	}

	public static AdhocConfiguration loadConfiguration(InputStream is) throws DRException {
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(XmlAdhocConfiguration.class).createUnmarshaller();
			JAXBElement<XmlAdhocConfiguration> element = unmarshaller.unmarshal(new StreamSource(is), XmlAdhocConfiguration.class);
			XmlAdhocConfiguration xmlAdhocConfiguration = element.getValue();
			AdhocConfiguration adhocConfiguration = xmlToAdhocTransform.transform(xmlAdhocConfiguration);
			return adhocConfiguration;
		} catch (JAXBException e) {
			throw new DRException(e);
		}
	}

}
