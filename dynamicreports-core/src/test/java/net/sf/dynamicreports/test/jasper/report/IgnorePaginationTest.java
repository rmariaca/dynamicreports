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

package net.sf.dynamicreports.test.jasper.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class IgnorePaginationTest extends AbstractJasperValueTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		
		rb.columns(				
				col.column("Column1", "field1", Integer.class))
			.setIgnorePagination(true);
	}

	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(1);
		
		JasperPrint jasperPrint = getJasperPrint();
		Assert.assertEquals(JRReport.ORIENTATION_PORTRAIT, jasperPrint.getOrientation());
		Assert.assertEquals(595, jasperPrint.getPageWidth());
		Assert.assertEquals(1636, jasperPrint.getPageHeight());
	}
	
	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1");
		for (int i = 0; i < 100; i++) {
			dataSource.add(i);
		}		
		return dataSource;
	}
}
