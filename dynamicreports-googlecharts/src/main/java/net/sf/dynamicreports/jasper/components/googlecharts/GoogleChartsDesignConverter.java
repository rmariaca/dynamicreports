package net.sf.dynamicreports.jasper.components.googlecharts;

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.component.ComponentDesignConverter;
import net.sf.jasperreports.engine.convert.ElementIconConverter;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.engine.util.JRImageLoader;

public class GoogleChartsDesignConverter extends ElementIconConverter implements ComponentDesignConverter {

	public GoogleChartsDesignConverter() {
		super(JRImageLoader.SUBREPORT_IMAGE_RESOURCE);
	}

	public JRPrintElement convert(ReportConverter reportConverter, JRComponentElement element) {
		return convert(reportConverter, (JRElement) element);
	}
}
