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

package net.sf.dynamicreports.jasper.builder.export;

import net.sf.dynamicreports.jasper.base.export.JasperTextExporter;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class JasperTextExporterBuilder extends AbstractJasperExporterBuilder<JasperTextExporterBuilder, JasperTextExporter> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected JasperTextExporterBuilder() {
		super(new JasperTextExporter());
	}

	public JasperTextExporterBuilder setCharacterWidth(Integer characterWidth) {
		this.getObject().setCharacterWidth(characterWidth);
		return this;
	}

	public JasperTextExporterBuilder setCharacterHeight(Integer characterHeight) {
		this.getObject().setCharacterHeight(characterHeight);
		return this;
	}

	public JasperTextExporterBuilder setPageWidth(Integer pageWidth) {
		this.getObject().setPageWidth(pageWidth);
		return this;
	}

	public JasperTextExporterBuilder setPageHeight(Integer pageHeight) {
		this.getObject().setPageHeight(pageHeight);
		return this;
	}

	public JasperTextExporterBuilder setBetweenPagesText(String betweenPagesText) {
		this.getObject().setBetweenPagesText(betweenPagesText);
		return this;
	}

	public JasperTextExporterBuilder setLineSeparator(String lineSeparator) {
		this.getObject().setLineSeparator(lineSeparator);
		return this;
	}
}
