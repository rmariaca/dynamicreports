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

package net.sf.dynamicreports.examples.datasource;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.chart.ChartSerieBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SubDatasourceReport {

	public SubDatasourceReport() {
		build();
	}

	private void build() {
		FontBuilder  boldFont = stl.fontArialBold().setFontSize(12);

		FieldBuilder<String> itemField = field("item", type.stringType());
		FieldBuilder<Integer> quantityField = field("quantity", type.integerType());
		FieldBuilder<BigDecimal> unitPriceField = field("unitprice", type.bigDecimalType());

		ChartSerieBuilder quantitySerie = cht.serie(quantityField).setLabel("Quantity");
		ChartSerieBuilder unitPriceSerie = cht.serie(unitPriceField).setLabel("Unit price");

		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .title(
			  	Templates.createTitleComponent("SubDatasource"),
			  	cht.barChart()
			  		.setDataSource(createDataSource1())
			  	  .setTitle("SubDatasource 1")
			  	  .setTitleFont(boldFont)
			  	  .setCategory(itemField)
			  	  .series(
			  	  	 quantitySerie, unitPriceSerie),
			  	cht.barChart()
						.setDataSource(createDataSource2())
					  .setTitle("SubDatasource 2")
					  .setTitleFont(boldFont)
					  .setCategory(itemField)
					  .series(
					  	quantitySerie, unitPriceSerie))
			  .pageFooter(Templates.footerComponent)
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource1() {
		DataSource dataSource = new DataSource("item", "quantity", "unitprice");
		dataSource.add("Book", 170, new BigDecimal(100));
		dataSource.add("Notebook", 90, new BigDecimal(450));
		dataSource.add("PDA", 120, new BigDecimal(250));
		return dataSource;
	}

	private JRDataSource createDataSource2() {
		DataSource dataSource = new DataSource("item", "quantity", "unitprice");
		dataSource.add("Book", 100, new BigDecimal(120));
		dataSource.add("Notebook", 190, new BigDecimal(350));
		dataSource.add("PDA", 800, new BigDecimal(290));
		return dataSource;
	}

	public static void main(String[] args) {
		new SubDatasourceReport();
	}
}
