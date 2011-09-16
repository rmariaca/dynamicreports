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

import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class StandardGeoMapComponent implements GeoMapComponent, Serializable, JRChangeEventsSupport {
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String PROPERTY_LATITUDE_EXPRESSION = "latitudeExpression";
	public static final String PROPERTY_EVALUATION_TIME = "evaluationTime";
	public static final String PROPERTY_EVALUATION_GROUP = "evaluationGroup";

	//private JRExpression latitudeExpression;
	private EvaluationTimeEnum evaluationTime = EvaluationTimeEnum.NOW;
	private String evaluationGroup;

	private transient JRPropertyChangeSupport eventSupport;

	public StandardGeoMapComponent() {
	}

	public StandardGeoMapComponent(GeoMapComponent map, JRBaseObjectFactory objectFactory) {
		//this.latitudeExpression = objectFactory.getExpression(map.getLatitudeExpression());
		this.evaluationTime = map.getEvaluationTime();
		this.evaluationGroup = map.getEvaluationGroup();
	}

	/*public JRExpression getLatitudeExpression() {
		return latitudeExpression;
	}

	public void setLatitudeExpression(JRExpression latitudeExpression) {
		Object old = this.latitudeExpression;
		this.latitudeExpression = latitudeExpression;
		getEventSupport().firePropertyChange(PROPERTY_LATITUDE_EXPRESSION, old, this.latitudeExpression);
	}*/

	public EvaluationTimeEnum getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(EvaluationTimeEnum evaluationTimeValue) {
		Object old = this.evaluationTime;
		this.evaluationTime = evaluationTimeValue;
		getEventSupport().firePropertyChange(PROPERTY_EVALUATION_TIME, old, this.evaluationTime);
	}

	public String getEvaluationGroup() {
		return evaluationGroup;
	}

	public void setEvaluationGroup(String evaluationGroup) {
		Object old = this.evaluationGroup;
		this.evaluationGroup = evaluationGroup;
		getEventSupport().firePropertyChange(PROPERTY_EVALUATION_GROUP, old, this.evaluationGroup);
	}

	public JRPropertyChangeSupport getEventSupport() {
		synchronized (this) {
			if (eventSupport == null) {
				eventSupport = new JRPropertyChangeSupport(this);
			}
		}

		return eventSupport;
	}

	@Override
	public Object clone() {
		StandardGeoMapComponent clone = null;
		try {
			clone = (StandardGeoMapComponent) super.clone();
		} catch (CloneNotSupportedException e) {
			// never
			throw new JRRuntimeException(e);
		}
		//clone.latitudeExpression = JRCloneUtils.nullSafeClone(latitudeExpression);
		clone.eventSupport = null;
		return clone;
	}
}