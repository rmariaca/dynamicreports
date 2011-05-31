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

package net.sf.dynamicreports.test.jasper.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

import org.apache.commons.lang.StringUtils;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperExpressionTest extends AbstractJasperValueTest implements Serializable {
	private static final long serialVersionUID = 1L;

	private TextColumnBuilder<Integer> column3;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.columns(
				col.column("field1", Integer.class).setTitle(exp.jasper("\"Column1\"", String.class)),
				col.column("field2", Integer.class).setTitle(exp.jasper("\"Column2\"", String.class)),
				column3 = col.column(exp.jasper("$F{field1} - $F{field2}", Integer.class)));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		columnDetailValueTest(column3, "0", "8", "3");

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			getReportBuilder().toJrXml(bos);
			String jrxml = new String(bos.toByteArray());
			Assert.assertFalse("jrxml contains dependency to dynamicreports", StringUtils.contains(jrxml, "net.sf.dynamicreports"));
		} catch (DRException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2");
		dataSource.add(1, 1);
		dataSource.add(10, 2);
		dataSource.add(5, 2);
		return dataSource;
	}
}
