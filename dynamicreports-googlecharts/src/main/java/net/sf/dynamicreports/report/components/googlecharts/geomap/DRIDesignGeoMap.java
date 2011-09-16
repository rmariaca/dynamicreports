package net.sf.dynamicreports.report.components.googlecharts.geomap;

import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.DRIDesignGroup;
import net.sf.dynamicreports.report.components.DRIDesignCustomComponent;

public interface DRIDesignGeoMap extends DRIDesignCustomComponent {

	public EvaluationTime getEvaluationTime();

	public DRIDesignGroup getEvaluationGroup();
}
