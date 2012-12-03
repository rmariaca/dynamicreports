package net.sf.dynamicreports.design.transformation;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.net.URL;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.renderers.BatikRenderer;

public class CustomBatikRenderer extends BatikRenderer {
	private static final long serialVersionUID = 1L;

	private Dimension dimension;

	public CustomBatikRenderer(URL svgURL, int width, int height) throws JRException {
		super(JRLoader.loadBytes(svgURL), null);
		this.dimension = new Dimension(width, height);
	}

	@Override
	public Dimension2D getDimension(JasperReportsContext jasperReportsContext) {
		return dimension;
	}

}
