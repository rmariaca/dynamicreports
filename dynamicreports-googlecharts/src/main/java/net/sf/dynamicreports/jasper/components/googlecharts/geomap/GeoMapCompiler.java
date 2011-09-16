package net.sf.dynamicreports.jasper.components.googlecharts.geomap;

import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.component.ComponentCompiler;
import net.sf.jasperreports.engine.design.JRVerifier;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

public class GeoMapCompiler implements ComponentCompiler {

	public void collectExpressions(Component component, JRExpressionCollector collector) {
		//GeoMapComponent geoMap = (GeoMapComponent) component;
		//collector.addExpression(geoMap.getLatitudeExpression());
	}

	public Component toCompiledComponent(Component component, JRBaseObjectFactory baseFactory) {
		GeoMapComponent geoMap = (GeoMapComponent) component;
		return new StandardGeoMapComponent(geoMap, baseFactory);
	}

	public void verify(Component component, JRVerifier verifier) {
		GeoMapComponent geoMap = (GeoMapComponent) component;

		EvaluationTimeEnum evaluationTime = geoMap.getEvaluationTime();
		if (evaluationTime == EvaluationTimeEnum.AUTO) {
			verifier.addBrokenRule("Auto evaluation time is not supported for geo maps", geoMap);
		} else if (evaluationTime == EvaluationTimeEnum.GROUP) {
			String evaluationGroup = geoMap.getEvaluationGroup();
			if (evaluationGroup == null || evaluationGroup.length() == 0) {
				verifier.addBrokenRule("No evaluation group set for geo map", geoMap);
			} else if (!verifier.getReportDesign().getGroupsMap().containsKey(evaluationGroup)) {
				verifier.addBrokenRule("Map evalution group \"" + evaluationGroup + " not found", geoMap);
			}
		}
	}
}