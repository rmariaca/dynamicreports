/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
 * http://dynamicreports.sourceforge.net
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.design.transformation;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.net.URL;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.renderers.BatikRenderer;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
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
