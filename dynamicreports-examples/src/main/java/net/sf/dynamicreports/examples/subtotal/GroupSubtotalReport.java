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

package net.sf.dynamicreports.examples.subtotal;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GroupSubtotalReport {

	public GroupSubtotalReport() {
		build();
	}

	private void build() {
		TextColumnBuilder<String>     countryColumn  = col.column("Country",  "country",  type.stringType());
		TextColumnBuilder<String>     itemColumn     = col.column("Item",     "item",     type.stringType());
		TextColumnBuilder<Integer>    quantityColumn = col.column("Quantity", "quantity", type.integerType());
		TextColumnBuilder<BigDecimal> priceColumn    = col.column("Price",    "price",    type.bigDecimalType());

		ColumnGroupBuilder countryGroup = grp.group(countryColumn);

		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .columns(
			  	countryColumn, itemColumn, quantityColumn, priceColumn)
			  .groupBy(
			  	countryGroup)
			  .subtotalsAtFirstGroupFooter(
			  	sbt.sum(quantityColumn))
			  .subtotalsAtGroupFooter(
			  	countryGroup, sbt.sum(priceColumn))
			  .subtotalsAtSummary(
			  	sbt.text("Total", itemColumn), sbt.sum(quantityColumn), sbt.sum(priceColumn))
			  .title(Templates.createTitleComponent("GroupSubtotal"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("country", "item", "quantity", "price");
		dataSource.add("USA", "Book", 4, new BigDecimal(10));
		dataSource.add("USA", "Book", 3, new BigDecimal(10));
		dataSource.add("USA", "Notebook", 2, new BigDecimal(20));
		dataSource.add("USA", "Notebook", 1, new BigDecimal(20));
		dataSource.add("Canada", "Book", 6, new BigDecimal(15));
		dataSource.add("Canada", "Book", 2, new BigDecimal(15));
		dataSource.add("Canada", "Notebook", 3, new BigDecimal(30));
		dataSource.add("Canada", "Notebook", 2, new BigDecimal(30));
		return dataSource;
	}

	public static void main(String[] args) {
		new GroupSubtotalReport();
	}
}