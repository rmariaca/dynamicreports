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

package net.sf.dynamicreports.examples.miscellaneous;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class UnitsReport {

	public UnitsReport() {
		build();
	}

	private void build() {
		TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType())
			.setFixedWidth(cm(10));
		TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());
		TextColumnBuilder<BigDecimal> priceColumn = col.column("Unit price", "unitprice", type.bigDecimalType());

		StyleBuilder style = stl.style(Templates.bold12CenteredStyle)
			.setBorder(stl.pen1Point());
		TextFieldBuilder<String> text1 = cmp.text("width = 120 pixels")
				.setFixedWidth(120)
				.setStyle(style);
		TextFieldBuilder<String> text2 = cmp.text("width = 10cm")
			.setFixedWidth(cm(10))
			.setStyle(style);
		TextFieldBuilder<String> text3 = cmp.text("width = 5 inches")
			.setFixedWidth(inch(5))
			.setStyle(style);
		TextFieldBuilder<String> text4 = cmp.text("width = 150mm")
			.setFixedWidth(mm(150))
			.setStyle(style);

		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .columns(
			  	itemColumn, quantityColumn, priceColumn)
			  .title(
			  	Templates.createTitleComponent("Units"),
			  	text1, text2, text3, text4, cmp.verticalGap(cm(1)))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("item", "quantity", "unitprice");
		for (int i = 0; i < 20; i++) {
			dataSource.add("Book", (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
		}
		return dataSource;
	}

	public static void main(String[] args) {
		new UnitsReport();
	}
}