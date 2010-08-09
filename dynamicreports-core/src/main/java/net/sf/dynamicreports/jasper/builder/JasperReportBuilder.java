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

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;

import javax.imageio.ImageIO;

import net.sf.dynamicreports.design.base.DRDesignReport;
import net.sf.dynamicreports.design.transformation.StyleResolver;
import net.sf.dynamicreports.jasper.base.JasperReportDesign;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.QueryBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.QueryLanguage;
import net.sf.dynamicreports.report.defaults.Defaults;
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
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.export.xmlss.JRXmlssExporter;
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
	
	public JasperReportBuilder setDataSource(String sql, Connection connection) {	
		Validate.notNull(sql, "sql must not be null");
		return setDataSource(DynamicReports.query(sql, QueryLanguage.SQL), connection);
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
	
	public JasperReportBuilder toPng(OutputStream outputStream, int pageIndex) throws DRException {
		return toPng(outputStream, pageIndex, 1);
	}
	
	public JasperReportBuilder toPng(OutputStream outputStream, int pageIndex, float zoom) throws DRException {
		Validate.notNull(outputStream, "outputStream must not be null");
		Validate.isTrue(zoom > 0, "zoom must be > 0");
		
		JasperPrint jasperPrint = toJasperPrint();		
		int fromPage;
		int toPage;		
		if (pageIndex < 0) {
			fromPage = 0;
			toPage = jasperPrint.getPages().size();
		}
		else {
			fromPage = pageIndex;
			toPage = pageIndex + 1;
		}
		int pages = toPage - fromPage;
		
		int pageWidth = (int) (jasperPrint.getPageWidth() * zoom);
		int width = pageWidth * pages + (pages - 1) + 2;
		int height = (int) (jasperPrint.getPageHeight() * zoom) + 2;
		Image pageImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		int offset = 1;
		for (int i = 0; i < pages; i++) {			
			try {
				JRGraphics2DExporter exporter = new JRGraphics2DExporter();			
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, pageImage.getGraphics());
				exporter.setParameter(JRGraphics2DExporterParameter.OFFSET_X, offset);
				exporter.setParameter(JRGraphics2DExporterParameter.OFFSET_Y, 1);
				exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(fromPage++));
				exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, new Float(zoom));
				exporter.exportReport();
				offset += pageWidth + 1;				
			} catch (JRException e) {
				throw new DRException(e);
			}
		}
		try {
			ImageIO.write((RenderedImage) pageImage, "png", outputStream);
		} catch (IOException e) {
			throw new DRException(e);
		}
		return this;
	}
	
	public JasperReportBuilder toCsv(OutputStream outputStream) throws DRException {
		return export(new JRCsvExporter(), outputStream);
	}
	
	public JasperReportBuilder toDocx(OutputStream outputStream) throws DRException {
		return export(new JRDocxExporter(), outputStream);
	}

	public JasperReportBuilder toHtml(OutputStream outputStream) throws DRException {
		return export(new JRHtmlExporter(), outputStream);
	}
	
	public JasperReportBuilder toOds(OutputStream outputStream) throws DRException {
		return export(new JROdsExporter(), outputStream);
	}
	
	public JasperReportBuilder toOdt(OutputStream outputStream) throws DRException {
		return export(new JROdtExporter(), outputStream);
	}
	
	public JasperReportBuilder toPdf(OutputStream outputStream) throws DRException {
		return export(new JRPdfExporter(), outputStream);
	}
	
	public JasperReportBuilder toRtf(OutputStream outputStream) throws DRException {
		return export(new JRRtfExporter(), outputStream);
	}
	
	public JasperReportBuilder toText(OutputStream outputStream) throws DRException {		
		DRFont font = Defaults.getDefaults().getFont();
		JRTextExporter exporter = new JRTextExporter();
		exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Float(StyleResolver.getFontWidth(font)));
		exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Float(StyleResolver.getFontHeight(font)));
		return export(exporter, outputStream);
	}
	
	public JasperReportBuilder toXhtml(OutputStream outputStream) throws DRException {
		return export(new JRXhtmlExporter(), outputStream);
	}
	
	public JasperReportBuilder toExcelApiXls(OutputStream outputStream) throws DRException {
		return export(new JExcelApiExporter(), outputStream);
	}
	
	public JasperReportBuilder toXls(OutputStream outputStream) throws DRException {
		return export(new JRXlsExporter(), outputStream);
	}
	
	public JasperReportBuilder toXlsx(OutputStream outputStream) throws DRException {
		return export(new JRXlsxExporter(), outputStream);
	}
	
	public JasperReportBuilder toXml(OutputStream outputStream) throws DRException {
		return export(new JRXmlExporter(), outputStream);
	}

	public JasperReportBuilder toXmlss(OutputStream outputStream) throws DRException {
		return export(new JRXmlssExporter(), outputStream);
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
