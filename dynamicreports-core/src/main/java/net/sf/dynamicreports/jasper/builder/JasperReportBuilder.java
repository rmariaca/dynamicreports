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

package net.sf.dynamicreports.jasper.builder;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;

import net.sf.dynamicreports.design.base.DRDesignReport;
import net.sf.dynamicreports.jasper.base.JasperReportDesign;
import net.sf.dynamicreports.report.builder.QueryBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class JasperReportBuilder extends ReportBuilder<JasperReportBuilder> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private JasperReportDesign reportDesign;
	private JasperDesign jasperDesign;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;	
	private JRDataSource dataSource;
	private Connection connection;
	
	public JasperReportBuilder() {		
	}
	
	public JasperReportBuilder setDataSource(Collection<?> collection) {
		return setDataSource(new JRBeanCollectionDataSource(collection));
	}
	
	public JasperReportBuilder setDataSource(ResultSet resultSet) {
		return setDataSource(new JRResultSetDataSource(resultSet));
	}
	
	public JasperReportBuilder setDataSource(QueryBuilder query, Connection connection) {	
		Validate.notNull(query, "query must not be null");
		Validate.notNull(connection, "connection must not be null");
		getObject().setQuery(query.build());
		this.connection = connection;
		dataSource = null;
		return this;
	}
	
	public JasperReportBuilder setDataSource(JRDataSource dataSource) {
		this.dataSource = dataSource;
		getObject().setQuery(null);
		connection = null;
		return this;
	}
	
	public JasperReportBuilder rebuild() {
		builded = false;
		reportDesign = null;
		jasperDesign = null;
		jasperReport = null;
		jasperPrint = null;
		return this;
	}
	
	private JasperReportDesign toJasperReportDesign() throws DRException {
		if (reportDesign == null) {
			reportDesign = new JasperReportDesign(new DRDesignReport(build()));
		}
		return reportDesign;
	}
	
	public JasperDesign toJasperDesign() throws DRException {
		if (jasperDesign == null) {
			jasperDesign = toJasperReportDesign().getDesign();
		}
		return jasperDesign;
	}
		
	public JasperReport toJasperReport() throws DRException {		
		if (jasperReport == null) {
			try {
				jasperReport = JasperCompileManager.compileReport(toJasperDesign());
			}
			catch (JRException e) {
				throw new DRException(e);
			}
		}
		return jasperReport;
	}
	
	public JasperPrint toJasperPrint() throws DRException {
		if (jasperPrint == null) {
			try {
				if (connection != null && getObject().getQuery() != null) {
					jasperPrint = JasperFillManager.fillReport(toJasperReport(), toJasperReportDesign().getParameters(), connection);
				}
				else {
					jasperPrint = JasperFillManager.fillReport(toJasperReport(), toJasperReportDesign().getParameters(), dataSource);
				}
			}
			catch (JRException e) {
				throw new DRException(e);
			}
		}
		return jasperPrint;
	}
	
	public JasperReportBuilder show() throws DRException {
		JasperViewer.viewReport(toJasperPrint());
		return this;
	}
	
	public JasperReportBuilder showJrXml() throws DRException {		
		try {
			JasperDesignViewer.viewReportDesign(toJasperDesign());
		} catch (JRException e) {
			throw new DRException(e);
		}
		return this;
	}
	
	public JasperReportBuilder toJrXml(OutputStream outputStream) throws DRException {
		Validate.notNull(outputStream, "outputStream must not be null");
		try {
			JRXmlWriter.writeReport(toJasperDesign(), outputStream, "UTF-8");
		} catch (JRException e) {
			throw new DRException(e);
		}
		return this;
	}
	
	public JasperReportBuilder print() throws DRException {
		return print(true);
	}
	
	public JasperReportBuilder print(boolean withPrintDialog) throws DRException {
		try {
			JasperPrintManager.printReport(toJasperPrint(), withPrintDialog);
		} catch (JRException e) {
			throw new DRException(e);
		}
		return this;
	}
	
	public JasperReportBuilder toPdf(OutputStream outputStream) throws DRException {
		return export(new JRPdfExporter(), outputStream);
	}
	
	public JasperReportBuilder toXls(OutputStream outputStream) throws DRException {
		return export(new JRXlsExporter(), outputStream);
	}

	public JasperReportBuilder toExcelApiXls(OutputStream outputStream) throws DRException {
		return export(new JExcelApiExporter(), outputStream);
	}
	
	public JasperReportBuilder toCsv(OutputStream outputStream) throws DRException {
		return export(new JRCsvExporter(), outputStream);
	}
	
	private JasperReportBuilder export(JRExporter exporter, OutputStream outputStream) throws DRException {
		Validate.notNull(outputStream, "outputStream must not be null");
		try {
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, toJasperPrint());
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();
		} catch (JRException e) {
			throw new DRException(e);
		}
		return this;
	}
}
