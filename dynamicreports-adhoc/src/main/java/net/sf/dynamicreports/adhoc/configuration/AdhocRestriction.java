/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
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

package net.sf.dynamicreports.adhoc.configuration;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocRestriction implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

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

	@Override
	public boolean equals(Object obj) {
		boolean equals = super.equals(obj);
		if (!equals) {
			return false;
		}
		if (obj == null)
			return false;
		if (!(obj instanceof AdhocRestriction))
			return false;

		AdhocRestriction object = (AdhocRestriction) obj;
		if (!(properties == null ? object.getProperties() == null : properties.equals(object.getProperties()))) {
			return false;
		}

		return true;
	}

	@Override
	public AdhocRestriction clone() {
		AdhocRestriction clone;
		try {
			clone = (AdhocRestriction) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}

		if (properties != null) {
			clone.properties = new Properties();
			for (Object keyObject : properties.keySet()) {
				String key = (String) keyObject;
				clone.addProperty(key, properties.getProperty(key));
			}
		}

		return clone;
	}

}
