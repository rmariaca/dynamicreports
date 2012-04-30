package net.sf.dynamicreports.adhoc.configuration;

import java.util.Properties;

public class AdhocRestriction {
	private Properties properties;

	public AdhocRestriction() {
		properties = new Properties();
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
