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

package net.sf.dynamicreports;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Project {
	private String version;
	private String developmentVersion;
	private Date date;
	private String outputDirectory;
	private String activationVersion;
	private String avalonframeworkimplVersion;
	private String barcode4jVersion;
	private String batikbridgeVersion;
	private String bcprovjdk14Version;
	private String commonsbeanutilsVersion;
	private String commonscollectionsVersion;
	private String commonsdigesterVersion;
	private String commonslangVersion;
	private String commonsloggingVersion;
	private String itextVersion;
	private String jasperreportsVersion;
	private String jaxbimplVersion;
	private String jcommonVersion;
	private String jdtcoreVersion;
	private String jfreechartVersion;
	private String jxlVersion;
	private String log4jVersion;
	private String poiVersion;
	private String staxapiVersion;
	private String xmlapisVersion;
	private String springframeworkVersion;
			
	public Project() throws IOException {
		version = System.getenv("version");
		developmentVersion = System.getenv("developmentVersion");
		date = new Date();
		outputDirectory = System.getenv("outputDirectory");
		
		Properties properties = new Properties();
		String prop = System.getenv("properties");
		prop = prop.substring(1, prop.length() - 1);
		prop = prop.replaceAll(", ", "\n");
		properties.load(new ByteArrayInputStream(prop.getBytes()));
		
		activationVersion = properties.getProperty("activationVersion");
		avalonframeworkimplVersion = properties.getProperty("avalonframeworkimplVersion");
		barcode4jVersion = properties.getProperty("barcode4jVersion");
		batikbridgeVersion = properties.getProperty("batikbridgeVersion");
		bcprovjdk14Version = properties.getProperty("bcprovjdk14Version");
		commonsbeanutilsVersion = properties.getProperty("commonsbeanutilsVersion");
		commonscollectionsVersion = properties.getProperty("commonscollectionsVersion");
		commonsdigesterVersion = properties.getProperty("commonsdigesterVersion");
		commonslangVersion = properties.getProperty("commonslangVersion");
		commonsloggingVersion = properties.getProperty("commonsloggingVersion");
		itextVersion = properties.getProperty("itextVersion");
		jasperreportsVersion = properties.getProperty("jasperreportsVersion");
		jaxbimplVersion = properties.getProperty("jaxbimplVersion");
		jcommonVersion = properties.getProperty("jcommonVersion");
		jdtcoreVersion = properties.getProperty("jdtcoreVersion");
		jfreechartVersion = properties.getProperty("jfreechartVersion");
		jxlVersion = properties.getProperty("jxlVersion");
		log4jVersion = properties.getProperty("log4jVersion");
		poiVersion = properties.getProperty("poiVersion");
		staxapiVersion = properties.getProperty("staxapiVersion");
		xmlapisVersion = properties.getProperty("xmlapisVersion");
		springframeworkVersion = properties.getProperty("springframeworkVersion");
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDevelopmentVersion() {
		return developmentVersion;
	}

	public void setDevelopmentVersion(String developmentVersion) {
		this.developmentVersion = developmentVersion;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getOutputDirectory() {
		return outputDirectory;
	}

	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public String getActivationVersion() {
		return activationVersion;
	}

	public void setActivationVersion(String activationVersion) {
		this.activationVersion = activationVersion;
	}

	public String getAvalonframeworkimplVersion() {
		return avalonframeworkimplVersion;
	}

	public void setAvalonframeworkimplVersion(String avalonframeworkimplVersion) {
		this.avalonframeworkimplVersion = avalonframeworkimplVersion;
	}

	public String getBarcode4jVersion() {
		return barcode4jVersion;
	}

	public void setBarcode4jVersion(String barcode4jVersion) {
		this.barcode4jVersion = barcode4jVersion;
	}

	public String getBatikbridgeVersion() {
		return batikbridgeVersion;
	}

	public void setBatikbridgeVersion(String batikbridgeVersion) {
		this.batikbridgeVersion = batikbridgeVersion;
	}

	public String getBcprovjdk14Version() {
		return bcprovjdk14Version;
	}

	public void setBcprovjdk14Version(String bcprovjdk14Version) {
		this.bcprovjdk14Version = bcprovjdk14Version;
	}

	public String getCommonsbeanutilsVersion() {
		return commonsbeanutilsVersion;
	}

	public void setCommonsbeanutilsVersion(String commonsbeanutilsVersion) {
		this.commonsbeanutilsVersion = commonsbeanutilsVersion;
	}

	public String getCommonscollectionsVersion() {
		return commonscollectionsVersion;
	}

	public void setCommonscollectionsVersion(String commonscollectionsVersion) {
		this.commonscollectionsVersion = commonscollectionsVersion;
	}

	public String getCommonsdigesterVersion() {
		return commonsdigesterVersion;
	}

	public void setCommonsdigesterVersion(String commonsdigesterVersion) {
		this.commonsdigesterVersion = commonsdigesterVersion;
	}

	public String getCommonslangVersion() {
		return commonslangVersion;
	}

	public void setCommonslangVersion(String commonslangVersion) {
		this.commonslangVersion = commonslangVersion;
	}

	public String getCommonsloggingVersion() {
		return commonsloggingVersion;
	}

	public void setCommonsloggingVersion(String commonsloggingVersion) {
		this.commonsloggingVersion = commonsloggingVersion;
	}

	public String getItextVersion() {
		return itextVersion;
	}

	public void setItextVersion(String itextVersion) {
		this.itextVersion = itextVersion;
	}

	public String getJasperreportsVersion() {
		return jasperreportsVersion;
	}

	public void setJasperreportsVersion(String jasperreportsVersion) {
		this.jasperreportsVersion = jasperreportsVersion;
	}

	public String getJaxbimplVersion() {
		return jaxbimplVersion;
	}

	public void setJaxbimplVersion(String jaxbimplVersion) {
		this.jaxbimplVersion = jaxbimplVersion;
	}

	public String getJcommonVersion() {
		return jcommonVersion;
	}

	public void setJcommonVersion(String jcommonVersion) {
		this.jcommonVersion = jcommonVersion;
	}

	public String getJdtcoreVersion() {
		return jdtcoreVersion;
	}

	public void setJdtcoreVersion(String jdtcoreVersion) {
		this.jdtcoreVersion = jdtcoreVersion;
	}

	public String getJfreechartVersion() {
		return jfreechartVersion;
	}

	public void setJfreechartVersion(String jfreechartVersion) {
		this.jfreechartVersion = jfreechartVersion;
	}

	public String getJxlVersion() {
		return jxlVersion;
	}

	public void setJxlVersion(String jxlVersion) {
		this.jxlVersion = jxlVersion;
	}

	public String getLog4jVersion() {
		return log4jVersion;
	}

	public void setLog4jVersion(String log4jVersion) {
		this.log4jVersion = log4jVersion;
	}

	public String getPoiVersion() {
		return poiVersion;
	}

	public void setPoiVersion(String poiVersion) {
		this.poiVersion = poiVersion;
	}

	public String getStaxapiVersion() {
		return staxapiVersion;
	}

	public void setStaxapiVersion(String staxapiVersion) {
		this.staxapiVersion = staxapiVersion;
	}

	public String getXmlapisVersion() {
		return xmlapisVersion;
	}

	public void setXmlapisVersion(String xmlapisVersion) {
		this.xmlapisVersion = xmlapisVersion;
	}

	public String getSpringframeworkVersion() {
		return springframeworkVersion;
	}

	public void setSpringframeworkVersion(String springframeworkVersion) {
		this.springframeworkVersion = springframeworkVersion;
	}	
}
