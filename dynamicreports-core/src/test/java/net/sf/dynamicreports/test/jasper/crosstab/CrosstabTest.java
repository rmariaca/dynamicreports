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

package net.sf.dynamicreports.test.jasper.crosstab;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.test.jasper.AbstractJasperTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class CrosstabTest extends AbstractJasperTest {
	private static final long serialVersionUID = 1L;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
		TextColumnBuilder<String> column2 = col.column("Column2", "field2", String.class);
		TextColumnBuilder<Integer> column3 = col.column("Column3", "field3", Integer.class);
		TextColumnBuilder<Integer> column4 = col.column("Column4", "field4", Integer.class);

		CrosstabBuilder crosstab = ctab.crosstab();
		crosstab.addRowGroup(ctab.rowGroup().setExpression(column1.build()));
		crosstab.addColumnGroup(ctab.columnGroup().setExpression(column2.build()));
		crosstab.addMeasure(ctab.measure().setValueExpression(column3.build()).setCalculation(Calculation.SUM));
		crosstab.addMeasure(ctab.measure().setValueExpression(column4.build()).setCalculation(Calculation.SUM));

		rb.columns(column1, column2,	column3,	column4)
			.summary(crosstab);
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2", "field3", "field4");
		dataSource.add("a", "c", 1, 2);
		dataSource.add("a", "c", 2, 3);
		dataSource.add("a", "d", 3, 4);
		dataSource.add("a", "d", 4, 5);
		dataSource.add("b", "c", 5, 6);
		dataSource.add("b", "c", 6, 7);
		dataSource.add("b", "d", 7, 8);
		dataSource.add("b", "d", 8, 9);
		return dataSource;
	}
}
