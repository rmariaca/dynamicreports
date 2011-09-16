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

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.component.BaseFillComponent;
import net.sf.jasperreports.engine.component.FillPrepareResult;
import net.sf.jasperreports.engine.fill.JRTemplateGenericElement;
import net.sf.jasperreports.engine.fill.JRTemplateGenericPrintElement;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GeoMapFillComponent extends BaseFillComponent {

	private GeoMapComponent geoMapComponent;
	// private Float latitude;

	public GeoMapFillComponent(GeoMapComponent geoMapComponent) {
		this.geoMapComponent = geoMapComponent;
	}

	protected GeoMapComponent getGeoMap() {
		return geoMapComponent;
	}

	public void evaluate(byte evaluation) throws JRException {
		if (isEvaluateNow()) {
			evaluateGeoMap(evaluation);
		}
	}

	private void evaluateGeoMap(byte evaluation) throws JRException {
		// latitude =	(Float)fillContext.evaluate(mapComponent.getLatitudeExpression(),	evaluation);
	}

	private boolean isEvaluateNow() {
		return geoMapComponent.getEvaluationTime() == EvaluationTimeEnum.NOW;
	}

	public FillPrepareResult prepare(int availableHeight) {
		return FillPrepareResult.PRINT_NO_STRETCH;
		// return isEvaluateNow() && (latitude == null || longitude == null)
		// ? FillPrepareResult.NO_PRINT_NO_OVERFLOW
		// : FillPrepareResult.PRINT_NO_STRETCH;
	}

	public JRPrintElement fill() {
		JRComponentElement element = fillContext.getComponentElement();
		JRTemplateGenericElement template = new JRTemplateGenericElement(fillContext.getElementOrigin(),
				fillContext.getDefaultStyleProvider(), GeoMapPrintElement.GEOMAP_ELEMENT_TYPE);

		JRTemplateGenericPrintElement printElement = new JRTemplateGenericPrintElement(template);
		printElement.setX(element.getX());
		printElement.setY(fillContext.getElementPrintY());
		printElement.setWidth(element.getWidth());
		printElement.setHeight(element.getHeight());

		if (isEvaluateNow()) {
			copy(printElement);
		} else {
			fillContext.registerDelayedEvaluation(printElement, geoMapComponent.getEvaluationTime(),
					geoMapComponent.getEvaluationGroup());
		}

		return printElement;
	}

	@Override
	public void evaluateDelayedElement(JRPrintElement element, byte evaluation) throws JRException {
		evaluateGeoMap(evaluation);
		copy((JRGenericPrintElement) element);
	}

	private void copy(JRGenericPrintElement printElement) {
		//printElement.setParameterValue(MapPrintElement.PARAMETER_LATITUDE, latitude);
	}
}
