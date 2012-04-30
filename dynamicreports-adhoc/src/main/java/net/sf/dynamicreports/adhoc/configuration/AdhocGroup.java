package net.sf.dynamicreports.adhoc.configuration;

import java.util.Properties;

public class AdhocGroup {
	private String name;
	private Boolean startInNewPage;
	private AdhocGroupHeaderLayout layout;
	private AdhocStyle style;
	private AdhocStyle titleStyle;
	private Properties properties;

	public AdhocGroup() {
		properties = new Properties();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStartInNewPage() {
		return startInNewPage;
	}

	public void setStartInNewPage(Boolean startInNewPage) {
		this.startInNewPage = startInNewPage;
	}

	public AdhocGroupHeaderLayout getLayout() {
		return layout;
	}

	public void setLayout(AdhocGroupHeaderLayout layout) {
		this.layout = layout;
	}

	public AdhocStyle getStyle() {
		return style;
	}

	public void setStyle(AdhocStyle style) {
		this.style = style;
	}

	public AdhocStyle getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(AdhocStyle titleStyle) {
		this.titleStyle = titleStyle;
	}

	public Properties getProperties() {
		return properties;
	}

	public void getProperty(String key) {
		this.properties.getProperty(key);
	}
	
	public void addProperty(String key, String value) {
		this.properties.setProperty(key, value);
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
