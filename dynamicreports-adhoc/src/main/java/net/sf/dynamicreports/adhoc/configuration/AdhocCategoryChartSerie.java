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

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocCategoryChartSerie implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private String series;
	private String value;
	private String label;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = super.equals(obj);
		if (!equals) {
			return false;
		}
		if (obj == null)
			return false;
		if (!(obj instanceof AdhocCategoryChartSerie))
			return false;

		AdhocCategoryChartSerie object = (AdhocCategoryChartSerie) obj;
		if (!(series == null ? object.getSeries() == null : series.equals(object.getSeries()))) {
			return false;
		}
		if (!(value == null ? object.getValue() == null : value.equals(object.getValue()))) {
			return false;
		}
		if (!(label == null ? object.getLabel() == null : label.equals(object.getLabel()))) {
			return false;
		}

		return true;
	}

	@Override
	public AdhocCategoryChartSerie clone() {
		AdhocCategoryChartSerie clone;
		try {
			clone = (AdhocCategoryChartSerie) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}

		return clone;
	}

}
