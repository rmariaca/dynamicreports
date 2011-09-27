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

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.util.JRCloneUtils;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class StandardGeoMapDataset extends JRDesignElementDataset implements GeoMapDataset {
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String PROPERTY_LOCATION_EXPRESSION = "locationExpression";
	public static final String PROPERTY_VALUE_EXPRESSION = "valueExpression";
	public static final String PROPERTY_TOOLTIP_EXPRESSION = "tooltipExpression";

	private JRExpression locationExpression;
	private JRExpression valueExpression;
	private JRExpression tooltipExpression;

	public StandardGeoMapDataset() {
	}

	public StandardGeoMapDataset(GeoMapDataset dataset, JRBaseObjectFactory factory) {
		super(dataset, factory);
		this.locationExpression = dataset.getLocationExpression();
		this.valueExpression = dataset.getValueExpression();
		this.tooltipExpression = dataset.getTooltipExpression();
	}

	public void collectExpressions(JRExpressionCollector collector) {
		GeoMapCompiler.collectExpressions(this, collector);
	}

	public JRExpression getLocationExpression() {
		return locationExpression;
	}

	public void setLocationExpression(JRExpression locationExpression) {
		Object old = this.locationExpression;
		this.locationExpression = locationExpression;
		getEventSupport().firePropertyChange(PROPERTY_LOCATION_EXPRESSION, old, this.locationExpression);
	}

	public JRExpression getValueExpression() {
		return valueExpression;
	}

	public void setValueExpression(JRExpression valueExpression) {
		Object old = this.valueExpression;
		this.valueExpression = valueExpression;
		getEventSupport().firePropertyChange(PROPERTY_VALUE_EXPRESSION, old, this.valueExpression);
	}

	public JRExpression getTooltipExpression() {
		return tooltipExpression;
	}

	public void setTooltipExpression(JRExpression tooltipExpression) {
		this.tooltipExpression = tooltipExpression;

		Object old = this.tooltipExpression;
		this.tooltipExpression = tooltipExpression;
		getEventSupport().firePropertyChange(PROPERTY_TOOLTIP_EXPRESSION, old, this.tooltipExpression);
	}

	@Override
	public Object clone() {
		StandardGeoMapDataset clone = (StandardGeoMapDataset) super.clone();
		clone.locationExpression = JRCloneUtils.nullSafeClone(locationExpression);
		clone.valueExpression = JRCloneUtils.nullSafeClone(valueExpression);
		clone.tooltipExpression = JRCloneUtils.nullSafeClone(tooltipExpression);
		return clone;
	}
}
