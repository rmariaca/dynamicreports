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
import net.sf.dynamicreports.examples.complex.ReportData;
import net.sf.dynamicreports.examples.complex.ReportDesign;
import net.sf.dynamicreports.examples.complex.invoice.InvoiceData;
import net.sf.dynamicreports.examples.complex.invoice.InvoiceDesign;
import net.sf.dynamicreports.examples.complex.sales.SalesData;
import net.sf.dynamicreports.examples.complex.sales.SalesDesign;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ConcatenatedReport1 {

	public ConcatenatedReport1() {
		build();
	}

	private void build() {
		try {
			concatenatedReport()
			  .concatenate(
			  	invoiceReport(), salesReport())
			  .toPdf(Exporters.pdfExporter("c:/report.pdf"));
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JasperReportBuilder invoiceReport() throws DRException {
		return createReport(new InvoiceDesign(), new InvoiceData());
	}

	private JasperReportBuilder salesReport() throws DRException {
		return createReport(new SalesDesign(), new SalesData());
	}

	private <T extends ReportData> JasperReportBuilder createReport(ReportDesign<T> design, T data) throws DRException {
		JasperReportBuilder reportBuilder = DynamicReports.report();
		if (data != null) {
			reportBuilder.setDataSource(data.createDataSource());
		}
		design.configureReport(reportBuilder, data);
		return reportBuilder;
	}

	public static void main(String[] args) {
		new ConcatenatedReport1();
	}
}