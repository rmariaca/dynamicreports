package net.sf.dynamicreports.report.components.googlecharts.geomap;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.transformation.DesignTransformAccessor;
import net.sf.dynamicreports.jasper.components.googlecharts.GoogleChartsExtensionsRegistryFactory;
import net.sf.dynamicreports.jasper.components.googlecharts.geomap.GeoMapPrintElement;
import net.sf.dynamicreports.jasper.components.googlecharts.geomap.StandardGeoMapComponent;
import net.sf.dynamicreports.jasper.transformation.ConstantTransform;
import net.sf.dynamicreports.jasper.transformation.JasperTransformAccessor;
import net.sf.dynamicreports.report.components.CustomComponentTransform;
import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

public class GeoMapTransform implements CustomComponentTransform<DRIGeoMap, DRIDesignGeoMap> {

	public boolean isTransform(Object component) {
		return component instanceof DRIGeoMap || component instanceof DRIDesignGeoMap;
	}

	public DRIDesignGeoMap designComponent(DesignTransformAccessor accessor, DRIGeoMap geoMap, ResetType resetType, DRDesignGroup resetGroup) {
		DRDesignGeoMap designGeoMap = new DRDesignGeoMap();
		designGeoMap.setEvaluationTime(accessor.getComponentTransform().evaluationTimeFromResetType(resetType));
		designGeoMap.setEvaluationGroup(resetGroup);
		return designGeoMap;
	}

	public JRComponentElement jasperComponent(JasperTransformAccessor accessor, DRIDesignGeoMap geoMap) {
		StandardGeoMapComponent jrGeoMap = new StandardGeoMapComponent();
		EvaluationTime evaluationTime = geoMap.getEvaluationTime();
		jrGeoMap.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && geoMap.getEvaluationGroup() != null) {
			jrGeoMap.setEvaluationGroup(accessor.getGroupTransform().getGroup(geoMap.getEvaluationGroup()).getName());
		}

		JRDesignComponentElement jrComponent = new JRDesignComponentElement();
		jrComponent.setComponent(jrGeoMap);
		jrComponent.setComponentKey(new ComponentKey(GoogleChartsExtensionsRegistryFactory.NAMESPACE, "jr", GeoMapPrintElement.GEOMAP_ELEMENT_NAME));

		return jrComponent;
	}

}
