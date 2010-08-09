/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class RowNumberColumnsReport {
	
	public RowNumberColumnsReport() {
		build();
	}
	
	private void build() {			
		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .setPageColumnsPerPage(2)
			  .setPageColumnSpace(10)
			  .columns(
			  		col.reportRowNumberColumn("Report row"),
			  		col.pageRowNumberColumn("Page row"),
			  		col.columnRowNumberColumn("Page column row"))
			  .title(Templates.createTitleComponent("RowNumberColumns"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();	
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource();
		for (int i = 0; i < 150; i++) {
			dataSource.add();	
		}		
		return dataSource;
	}
	
	public static void main(String[] args) {
		new RowNumberColumnsReport();
	}
}