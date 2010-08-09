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

package net.sf.dynamicreports.examples.subreport;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class TitleSubreport {
	
	public TitleSubreport() {
		build();
	}
	
	private void build() {
		try {
			report()
			  .setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
			  .title(
			  	Templates.createTitleComponent("TitleSubreport"),
			  	cmp.subreport(createSubreport()))
			  .pageFooter(Templates.footerComponent)
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JasperReportBuilder createSubreport() {
		JasperReportBuilder report = report();
		report
		  .setTemplate(Templates.reportTemplate)
		  .setPageMargin(margin(0))
		  .title(cmp.text("Subreport in title").setStyle(Templates.bold12CenteredStyle))
		  .columns(
		  	col.column("Item",       "item",      type.stringType()),
		  	col.column("Quantity",   "quantity",  type.integerType()),
		  	col.column("Unit price", "unitprice", type.bigDecimalType()))
		  .setDataSource(createSubreportDataSource());
		
		return report;
	}
	
	private JRDataSource createSubreportDataSource() {
		DataSource dataSource = new DataSource("item", "quantity", "unitprice");
		for (int i = 0; i < 10; i++) {
			dataSource.add("Book", (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
		}
		return dataSource;
	}
	
	public static void main(String[] args) {
		new TitleSubreport();
	}
}