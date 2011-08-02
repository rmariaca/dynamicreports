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

import java.math.BigDecimal;
import java.util.Locale;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.QueryLanguage;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.junit.Assert;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class XmlReportTest extends AbstractJasperValueTest {
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<BigDecimal> column3;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		try {
			rb.setLocale(Locale.ENGLISH)
				.columns(
					column1 =	col.column("Column1", field("field1", type.stringType()).setDescription("field1")),
					column2 =	col.column("Column2", field("field2", type.integerType()).setDescription("field2")),
					column3 =	col.column("Column3", field("field3", type.bigDecimalType()).setDescription("field3")))
				.setQuery("/data/row", QueryLanguage.XPATH)
				.setParameter(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, JRXmlUtils.parse(XmlReportTest.class.getResourceAsStream("data.xml")));
		} catch (JRException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
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
		columnDetailCountTest(column1, 1);
		columnDetailValueTest(column1, 0, "text");
		//column2
		columnTitleCountTest(column2, 1);
		columnTitleValueTest(column2, "Column2");
		columnDetailCountTest(column2, 1);
		columnDetailValueTest(column2, 0, "5");
		//column3
		columnTitleCountTest(column3, 1);
		columnTitleValueTest(column3, "Column3");
		columnDetailCountTest(column3, 1);
		columnDetailValueTest(column3, 0, "100.00");
	}
}