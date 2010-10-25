package net.sf.dynamicreports.jasper.base.templatedesign;

import net.sf.dynamicreports.report.base.AbstractTemplateDesign;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.design.JasperDesign;

public class JasperEmptyTemplateDesign extends AbstractTemplateDesign<JasperDesign> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	@Override
	public JasperDesign getDesign() throws DRException {
		JasperDesign design = new JasperDesign();
		design.setName("Report");
		return design;
	}
}
