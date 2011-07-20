/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
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

package net.sf.dynamicreports.examples.columngrid;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ColumnTitleGroupReport {

	public ColumnTitleGroupReport() {
		build();
	}

	private void build() {
  	TextColumnBuilder<String>     itemColumn      = col.column("Item",        "item",      type.stringType());
  	TextColumnBuilder<Date>       orderDateColumn = col.column("Order date",  "orderdate", type.dateType());
  	TextColumnBuilder<Integer>    quantityColumn  = col.column("Quantity",    "quantity",  type.integerType());
  	TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price",  "unitprice", type.bigDecimalType());

		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .columnGrid(
			  	itemColumn, grid.titleGroup("Group 1", orderDateColumn, grid.titleGroup("Group 2", quantityColumn, unitPriceColumn)))
			  .columns(
			  	itemColumn, orderDateColumn, quantityColumn, unitPriceColumn)
			  .title(Templates.createTitleComponent("ColumnTitleGroup"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("item", "orderdate", "quantity", "unitprice");
		dataSource.add("Notebook", new Date(), 1, new BigDecimal(500));
		return dataSource;
	}

	public static void main(String[] args) {
		new ColumnTitleGroupReport();
	}
}