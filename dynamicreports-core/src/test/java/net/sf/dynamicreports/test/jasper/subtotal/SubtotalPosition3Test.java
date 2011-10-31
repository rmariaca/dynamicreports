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

package net.sf.dynamicreports.test.jasper.subtotal;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SubtotalPosition3Test extends AbstractJasperPositionTest {
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.columns(
				column1 = col.column("", "field1", type.stringType()).setFixedWidth(540),
				column2 = col.column("", "field2", type.integerType()))
			.subtotalsAtSummary(
					subtotal1 = sbt.sum(column2));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		//columns
		columnDetailPositionTest(column1, 0, 0, 0, 540, 26);
		columnDetailPositionTest(column2, 0, 540, 0, 35, 26);

		//summary
		subtotalPositionTest(subtotal1, 0, 540, 0, 35, 26);
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2");
		dataSource.add("text", 1000000);
		dataSource.add("text", 1000000);
		return dataSource;
	}
}