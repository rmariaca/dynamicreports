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

package net.sf.dynamicreports.test.jasper.datasource;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class FilterTest extends AbstractJasperValueTest implements Serializable {
	private static final long serialVersionUID = 1L;

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.columns(
				column1 = col.column("Column1", "field1", type.stringType()),
				column2 = col.column("Column2", "field2", type.stringType()))
			.setFilterExpression(new FilterExpression());
	}

	@Override
	protected boolean serializableTest() {
		return false;
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		//column1
		columnTitleCountTest(column1, 1);
		columnTitleValueTest(column1, "Column1");
		columnDetailCountTest(column1, 2);
		columnDetailValueTest(column1, "a", "a");
		//column2
		columnTitleCountTest(column2, 1);
		columnTitleValueTest(column2, "Column2");
		columnDetailCountTest(column2, 2);
		columnDetailValueTest(column2, "text", "text");
	}

	private class FilterExpression extends AbstractSimpleExpression<Boolean> {
		private static final long serialVersionUID = 1L;

		public Boolean evaluate(ReportParameters reportParameters) {
			String value = reportParameters.getValue("field1");
			return value.equals("a");
		}
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2");
		dataSource.add("a", "text");
		dataSource.add("b", "text");
		dataSource.add("a", "text");
		dataSource.add("b", "text");
		return dataSource;
	}
}
