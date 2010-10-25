package net.sf.dynamicreports.design.base;

import net.sf.dynamicreports.design.definition.DRIDesignTemplateDesign;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.exception.DRException;

public class DRDesignTemplateDesign implements DRIDesignTemplateDesign {
	private DRITemplateDesign<?> templateDesign;

	public DRDesignTemplateDesign (DRITemplateDesign<?> templateDesign) {
		this.templateDesign = templateDesign;		
	}
	
	public Object getDesign() throws DRException {
		return templateDesign.getDesign();
	}
}
