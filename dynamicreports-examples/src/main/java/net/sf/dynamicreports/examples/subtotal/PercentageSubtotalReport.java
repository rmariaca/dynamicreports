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

package net.sf.dynamicreports.examples.subtotal;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.math.BigDecimal;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.PercentageSubtotalBuilder;
import net.sf.dynamicreports.report.constant.PercentageTotalType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class PercentageSubtotalReport {
	
	public PercentageSubtotalReport() {
		build();
	}
	
	private void build() {
		TextColumnBuilder<String>     countryColumn = col.column("Country", "country", type.stringType());
		TextColumnBuilder<String>     itemColumn    = col.column("Item",    "item",    type.stringType());
		TextColumnBuilder<BigDecimal> priceColumn   = col.column("Price",   "price",   type.bigDecimalType());
		
		ColumnGroupBuilder countryGroup = grp.group(countryColumn);
		ColumnGroupBuilder itemGroup    = grp.group(itemColumn);
		
		StyleBuilder countryLabelStyle       = stl.style().setForegroudColor(Color.RED);
		StyleBuilder countryStyle            = stl.style(countryLabelStyle)
		                                          .setTopBorder(stl.pen1Point());
		StyleBuilder itemInCountryLabelStyle = stl.style().setForegroudColor(Color.GREEN);
		StyleBuilder itemInCountryStyle      = stl.style(itemInCountryLabelStyle)
		                                          .setTopBorder(stl.pen1Point());
		StyleBuilder itemLabelStyle          = stl.style().setForegroudColor(Color.BLUE);
		StyleBuilder itemStyle               = stl.style(itemLabelStyle)
		                                          .setTopBorder(stl.pen1Point());
		
		PercentageSubtotalBuilder countryPercentage       = sbt.percentage(priceColumn).setLabel("country price [%]")
		                                                       .setLabelStyle(countryLabelStyle)
		                                                       .setStyle(countryStyle);
		
		PercentageSubtotalBuilder itemInCountryPercentage = sbt.percentage(priceColumn).setLabel("item in country price[%]")
		                                                       .setLabelStyle(itemInCountryLabelStyle)
		                                                       .setStyle(itemInCountryStyle);
		
		PercentageSubtotalBuilder itemPercentage          = sbt.percentage(priceColumn).setLabel("item price[%]")
		                                                       .setLabelStyle(itemLabelStyle)
		                                                       .setStyle(itemStyle)
		                                                       .setTotalType(PercentageTotalType.REPORT);
		
		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .columns(
			  	countryColumn, itemColumn,	priceColumn)
			  .groupBy(
			  	countryGroup, itemGroup)
			  .subtotalsOfPercentageAtGroupFooter(
			  	countryGroup, countryPercentage)
			  .subtotalsOfPercentageAtGroupFooter(
			  	itemGroup, itemInCountryPercentage, itemPercentage)
			  .title(Templates.createTitleComponent("PercentageSubtotal"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("country", "item", "price");
		dataSource.add("USA", "Book", new BigDecimal(10));
		dataSource.add("USA", "Book", new BigDecimal(10));
		dataSource.add("USA", "Notebook", new BigDecimal(20));
		dataSource.add("USA", "Notebook", new BigDecimal(20));
		dataSource.add("Canada", "Book", new BigDecimal(15));
		dataSource.add("Canada", "Book", new BigDecimal(15));
		dataSource.add("Canada", "Notebook", new BigDecimal(30));
		dataSource.add("Canada", "Notebook", new BigDecimal(30));
		return dataSource;
	}
	
	public static void main(String[] args) {
		new PercentageSubtotalReport();
	}
}