/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2016 Ricardo Mariaca
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

package net.sf.dynamicreports.jasper.builder.export;

import net.sf.dynamicreports.jasper.base.export.JasperXhtmlExporter;
import net.sf.dynamicreports.jasper.constant.SizeUnit;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 * @deprecated To be removed. Use JasperHtmlExporterBuilder instead.
 */
@Deprecated
public class JasperXhtmlExporterBuilder extends AbstractJasperExporterBuilder<JasperXhtmlExporterBuilder, JasperXhtmlExporter> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected JasperXhtmlExporterBuilder() {
		super(new JasperXhtmlExporter());
	}

	public JasperXhtmlExporterBuilder setOutputImagesToDir(Boolean outputImagesToDir) {
		this.getObject().setOutputImagesToDir(outputImagesToDir);
		return this;
	}

	public JasperXhtmlExporterBuilder setImagesDirName(String imagesDirName) {
		this.getObject().setImagesDirName(imagesDirName);
		return this;
	}

	public JasperXhtmlExporterBuilder setImagesURI(String imagesURI) {
		this.getObject().setImagesURI(imagesURI);
		return this;
	}

	public JasperXhtmlExporterBuilder setHtmlHeader(String htmlHeader) {
		this.getObject().setHtmlHeader(htmlHeader);
		return this;
	}

	public JasperXhtmlExporterBuilder setBetweenPagesHtml(String betweenPagesHtml) {
		this.getObject().setBetweenPagesHtml(betweenPagesHtml);
		return this;
	}

	public JasperXhtmlExporterBuilder setHtmlFooter(String htmlFooter) {
		this.getObject().setHtmlFooter(htmlFooter);
		return this;
	}

	public JasperXhtmlExporterBuilder setWhitePageBackground(Boolean whitePageBackground) {
		this.getObject().setWhitePageBackground(whitePageBackground);
		return this;
	}

	public JasperXhtmlExporterBuilder setWrapBreakWord(Boolean wrapBreakWord) {
		this.getObject().setWrapBreakWord(wrapBreakWord);
		return this;
	}

	public JasperXhtmlExporterBuilder setSizeUnit(SizeUnit sizeUnit) {
		this.getObject().setSizeUnit(sizeUnit);
		return this;
	}

	public JasperXhtmlExporterBuilder setIgnorePageMargins(Boolean ignorePageMargins) {
		this.getObject().setIgnorePageMargins(ignorePageMargins);
		return this;
	}
}
