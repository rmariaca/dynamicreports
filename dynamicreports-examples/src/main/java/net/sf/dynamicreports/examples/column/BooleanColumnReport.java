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

package net.sf.dynamicreports.examples.column;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class BooleanColumnReport {

	public BooleanColumnReport() {
		build();
	}

	private void build() {
		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
			  .columns(
			  	col.booleanColumn("Boolean\ndefault", "boolean"),
			  	col.booleanColumn("Boolean\nTEXT_TRUE_FALSE", "boolean").setComponentType(BooleanComponentType.TEXT_TRUE_FALSE),
			  	col.booleanColumn("Boolean\nTEXT_YES_NO", "boolean").setComponentType(BooleanComponentType.TEXT_YES_NO),
			  	col.booleanColumn("Boolean\nIMAGE_STYLE_1", "boolean").setComponentType(BooleanComponentType.IMAGE_STYLE_1),
			  	col.booleanColumn("Boolean\nIMAGE_STYLE_2", "boolean").setComponentType(BooleanComponentType.IMAGE_STYLE_2),
			  	col.booleanColumn("Boolean\nIMAGE_STYLE_3", "boolean").setComponentType(BooleanComponentType.IMAGE_STYLE_3),
			  	col.booleanColumn("Boolean\nIMAGE_STYLE_4", "boolean").setComponentType(BooleanComponentType.IMAGE_STYLE_4),
			  	col.booleanColumn("Boolean\nIMAGE_CHECKBOX_1", "boolean").setComponentType(BooleanComponentType.IMAGE_CHECKBOX_1),
			  	col.booleanColumn("Boolean\nIMAGE_CHECKBOX_2", "boolean").setComponentType(BooleanComponentType.IMAGE_CHECKBOX_2),
			  	col.booleanColumn("Boolean\nIMAGE_BALL", "boolean").setComponentType(BooleanComponentType.IMAGE_BALL))
			  .title(Templates.createTitleComponent("BooleanColumn"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("boolean");
		dataSource.add(true);
		dataSource.add(false);
		dataSource.add(true);
		dataSource.add(false);
		return dataSource;
	}

	public static void main(String[] args) {
		new BooleanColumnReport();
	}
}