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
