/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
 * http://www.dynamicreports.org
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

package net.sf.dynamicreports.report.builder.style;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.dynamicreports.jasper.base.JasperTemplateStyleLoader;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class TemplateStyleFileBuilder implements TemplateStyleListBuilder {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private DRIStyle[] styles;

	public TemplateStyleFileBuilder(InputStream inputStream) {
		this(JasperTemplateStyleLoader.loadStyles(inputStream));
	}

	public TemplateStyleFileBuilder(File file) {
		this(JasperTemplateStyleLoader.loadStyles(file));
	}

	public TemplateStyleFileBuilder(String fileName) throws DRException {
		this(JasperTemplateStyleLoader.loadStyles(fileName));
	}

	public TemplateStyleFileBuilder(URL url) {
		this(JasperTemplateStyleLoader.loadStyles(url));
	}

	private TemplateStyleFileBuilder(DRIStyle[] styles) {
		this.styles = styles;
	}

	@Override
	public DRIStyle[] getStyles() {
		return styles;
	}
}
