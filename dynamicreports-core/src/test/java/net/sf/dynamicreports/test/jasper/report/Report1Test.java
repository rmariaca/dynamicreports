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
import java.math.BigDecimal;
import java.util.ListResourceBundle;
import java.util.Locale;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.AbstractScriptlet;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Report1Test extends AbstractJasperValueTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TextColumnBuilder<Integer> column1;
	private ReportScriptlet scriptlet;
	private BigDecimal parameter;
	
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.columns(				
				column1 = col.column("Column1", "field1", Integer.class))
			.setLocale(Locale.ENGLISH)
			.setResourceBundle(new ResourceBundle())
			.setShowColumnTitle(false)
			.setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
			.scriptlets(scriptlet = new ReportScriptlet())
			.parameters(parameter("parameter", parameter = new BigDecimal(10)));
	}

	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(1);
		columnTitleCountTest(column1, 0);
		
		JasperPrint jasperPrint = getJasperPrint();
		Assert.assertEquals(JRReport.ORIENTATION_LANDSCAPE, jasperPrint.getOrientation());
		Assert.assertEquals(1190, jasperPrint.getPageWidth());
		Assert.assertEquals(842, jasperPrint.getPageHeight());
		
		Assert.assertEquals(50, scriptlet.count);
	}
	
	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1");
		for (int i = 0; i < 50; i++) {
			dataSource.add(i);
		}		
		return dataSource;
	}
	
	private class ResourceBundle extends ListResourceBundle implements Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		protected Object[][] getContents() {
			return new Object[][] {{"bundleKey", "bundleValue"}};
		}		
	}
	
	private class ReportScriptlet extends AbstractScriptlet implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private int count;		
		
		@Override
		public void afterReportInit(ReportParameters reportParameters) {
			super.afterReportInit(reportParameters);
			Assert.assertEquals(Locale.ENGLISH, reportParameters.getLocale());
			Assert.assertEquals("bundleValue", reportParameters.getMessage("bundleKey"));
			Assert.assertEquals(parameter, reportParameters.getValue("parameter"));
			Assert.assertEquals(this, reportParameters.getScriptlet(getName()));
		}
		
		@Override
		public void afterDetailEval(ReportParameters reportParameters) {
			super.afterDetailEval(reportParameters);
			count++;
		}		
	}
}
