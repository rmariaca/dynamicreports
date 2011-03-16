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

package net.sf.dynamicreports.test.jasper.style;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.io.Serializable;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Style6Test extends AbstractJasperStyleTest implements Serializable {
	private static final long serialVersionUID = 1L;

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.highlightDetailEvenRows()
			.columns(
					column1 = col.column("Column1", "field1", type.stringType()).setStyle(stl.style().setBackgroundColor(Color.BLUE)),
					column2 = col.column("Column2", "field2", type.stringType()));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		Color color1 = new Color(60, 60, 251);
		Color color2 = new Color(240, 240, 240);

		//column1
		columnDetailStyleTest(column1, 0, null, color1, "Arial", 10, null, null);
		columnDetailStyleTest(column1, 1, null, Color.BLUE, "Arial", 10, null, null);
		columnDetailStyleTest(column1, 2, null, color1, "Arial", 10, null, null);

		//column2
		columnDetailStyleTest(column2, 0, Color.BLACK, color2, "Arial", 10, null, null);
		columnDetailStyleTest(column2, 1, Color.BLACK, null, "Arial", 10, null, null);
		columnDetailStyleTest(column2, 2, Color.BLACK, color2, "Arial", 10, null, null);
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2", "field3");
		dataSource.add("1", "1", "1");
		dataSource.add("1", "1", "1");
		dataSource.add("1", "1", "1");
		return dataSource;
	}
}
