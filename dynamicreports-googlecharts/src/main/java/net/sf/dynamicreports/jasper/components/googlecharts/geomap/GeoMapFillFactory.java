package net.sf.dynamicreports.jasper.components.googlecharts.geomap;

import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.component.ComponentFillFactory;
import net.sf.jasperreports.engine.component.FillComponent;
import net.sf.jasperreports.engine.fill.JRFillCloneFactory;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;

public class GeoMapFillFactory implements ComponentFillFactory {

	public FillComponent toFillComponent(Component component, JRFillObjectFactory factory) {
		GeoMapComponent geoMap = (GeoMapComponent) component;
		return new GeoMapFillComponent(geoMap);
	}

	public FillComponent cloneFillComponent(FillComponent component, JRFillCloneFactory factory) {
		GeoMapFillComponent fillGeoMap = (GeoMapFillComponent) component;
		return new GeoMapFillComponent(fillGeoMap.getGeoMap());
	}

}
