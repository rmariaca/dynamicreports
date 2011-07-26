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

package net.sf.dynamicreports.test.jasper.tableofcontents;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class TableOfContentsPosition3Test extends AbstractJasperPositionTest {
	private TextColumnBuilder<String> column1;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.setPageFormat(PageType.A6, PageOrientation.LANDSCAPE)
			.tableOfContents()
	  	.columns(
	  		column1 = col.column("Column1", "field1", type.stringType()),
	  		col.column("Column2", "field2", type.stringType()))
		  .groupBy(column1);
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(5);

		elementPositionTest("title.textField1", 0, 10, 10, 401, 19);

		for (int i = 0; i < 14; i++) {
			elementPositionTest("detail.list2", i, 10, 49 + 16 * i, 401, 16);
			elementPositionTest("detail.textField1", i, 0, 0, 189, 16);
			elementPositionTest("detail.textField2", i, 189, 0, 190, 16);
			elementPositionTest("detail.textField3", i, 379, 0, 22, 16);
		}
		for (int i = 14; i < 20; i++) {
			elementPositionTest("detail.list2", i, 10, 10 + 16 * (i - 14), 401, 16);
			elementPositionTest("detail.textField1", i, 0, 0, 189, 16);
			elementPositionTest("detail.textField2", i, 189, 0, 190, 16);
			elementPositionTest("detail.textField3", i, 379, 0, 22, 16);
		}
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2");
		for (int i = 0; i < 20; i++) {
			dataSource.add("value" + i, "text");
		}
		return dataSource;
	}
}
