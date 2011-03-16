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

package net.sf.dynamicreports.examples.crosstab;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.CrosstabPercentageType;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class PercentageCrosstabReport {

	public PercentageCrosstabReport() {
		build();
	}

	private void build() {
		CrosstabRowGroupBuilder<String> rowGroup = ctab.rowGroup("state", String.class)
		                                               .setTotalHeader("Total for state");

		CrosstabColumnGroupBuilder<String> columnGroup = ctab.columnGroup("item", String.class);

		FieldBuilder<BigDecimal> quantityField = field("unitprice", BigDecimal.class);

		CrosstabBuilder crosstab = ctab.crosstab()
		                               .headerCell(cmp.text("State / Item").setStyle(Templates.boldCenteredStyle))
		                               .rowGroups(rowGroup)
		                               .columnGroups(columnGroup)
		                               .measures(
		                              		ctab.measure("Unit price", quantityField, Calculation.SUM),
		                              		ctab.measure("%", quantityField, Calculation.SUM).setPercentageType(CrosstabPercentageType.GRAND_TOTAL));

		try {
			report()
			  .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
			  .setTemplate(Templates.reportTemplate)
			  .title(Templates.createTitleComponent("Crosstab"))
			  .summary(crosstab)
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("state", "item", "unitprice");
		dataSource.add("New York", "Notebook", new BigDecimal(500));
		dataSource.add("New York", "DVD", new BigDecimal(30));
		dataSource.add("New York", "DVD", new BigDecimal(45.6));
		dataSource.add("New York", "DVD", new BigDecimal(36));
		dataSource.add("New York", "DVD", new BigDecimal(41));
		dataSource.add("New York", "Book", new BigDecimal(11));
		dataSource.add("New York", "Book", new BigDecimal(9));
		dataSource.add("New York", "Book", new BigDecimal(14.8));

		dataSource.add("Washington", "Notebook", new BigDecimal(610));
		dataSource.add("Washington", "DVD", new BigDecimal(40));
		dataSource.add("Washington", "DVD", new BigDecimal(35));
		dataSource.add("Washington", "DVD", new BigDecimal(46.4));
		dataSource.add("Washington", "DVD", new BigDecimal(42));
		dataSource.add("Washington", "Book", new BigDecimal(12));
		dataSource.add("Washington", "Book", new BigDecimal(8));
		dataSource.add("Washington", "Book", new BigDecimal(14));
		dataSource.add("Washington", "Book", new BigDecimal(10));

		dataSource.add("Florida", "Notebook", new BigDecimal(460.7));
		dataSource.add("Florida", "DVD", new BigDecimal(49));
		dataSource.add("Florida", "DVD", new BigDecimal(32));
		dataSource.add("Florida", "DVD", new BigDecimal(47));
		dataSource.add("Florida", "Book", new BigDecimal(11));
		dataSource.add("Florida", "Book", new BigDecimal(6.1));
		dataSource.add("Florida", "Book", new BigDecimal(16));
		dataSource.add("Florida", "Book", new BigDecimal(18));
		return dataSource;
	}

	public static void main(String[] args) {
		new PercentageCrosstabReport();
	}
}