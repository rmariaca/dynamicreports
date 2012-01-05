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

package net.sf.dynamicreports.examples.templatedesign;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.InputStream;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperTemplateDesignReport3 {

	public JasperTemplateDesignReport3() {
		build();
	}

	private void build() {
		InputStream is = JasperTemplateDesignReport3.class.getResourceAsStream("templatedesign3.jrxml");

		try {
			report()
			  .setTemplateDesign(is)
			  .title(Templates.createTitleComponent("JasperTemplateDesign3"))
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		return new JREmptyDataSource(2);
	}

	public static void main(String[] args) {
		new JasperTemplateDesignReport3();
	}
}