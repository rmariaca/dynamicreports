package net.sf.dynamicreports.jasper.components.googlecharts.geomap;

import net.sf.jasperreports.engine.JRCloneable;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

public interface GeoMapComponent extends Component, JRCloneable {

	public EvaluationTimeEnum getEvaluationTime();

	public String getEvaluationGroup();
}
