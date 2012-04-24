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

package net.sf.dynamicreports.examples.group;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class CustomGroupReport {
	
	public CustomGroupReport() {
		build();
	}
	
	private void build() {				
		CustomGroupBuilder yearGroup = grp.group(new YearExpression());
		
		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .columns(
			  	col.column("Order date", "orderdate", type.stringType()),
			  	col.column("Item",       "item",      type.stringType()),
			  	col.column("Quantity",   "quantity",  type.integerType()),
			  	col.column("Unit price", "unitprice", type.bigDecimalType()))
			  .groupBy(yearGroup)
			  .title(Templates.createTitleComponent("CustomGroup"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();	
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private class YearExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		public String evaluate(ReportParameters reportParameters) {
			String orderDate = reportParameters.getValue("orderdate");
			return orderDate.split("-")[0];
		}		
	}
	
	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("orderdate", "item", "quantity", "unitprice");
		dataSource.add("2009-11-01", "DVD", 5, new BigDecimal(30));
		dataSource.add("2009-11-01", "Book", 3, new BigDecimal(11));
		dataSource.add("2009-12-01", "DVD", 1, new BigDecimal(28));		
		dataSource.add("2009-12-01", "Book", 1, new BigDecimal(15));
		dataSource.add("2010-01-01", "DVD", 4, new BigDecimal(32));
		dataSource.add("2010-01-01", "Book", 2, new BigDecimal(15));
		dataSource.add("2010-02-01", "DVD", 3, new BigDecimal(25));
		dataSource.add("2010-02-01", "Book", 5, new BigDecimal(12));
		return dataSource;
	}
	
	public static void main(String[] args) {
		new CustomGroupReport();
	}
}