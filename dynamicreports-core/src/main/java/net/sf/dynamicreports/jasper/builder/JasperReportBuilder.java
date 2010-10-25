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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.dynamicreports.design.base.DRDesignReport;
import net.sf.dynamicreports.jasper.base.JasperReportDesign;
import net.sf.dynamicreports.jasper.base.export.AbstractJasperExporter;
import net.sf.dynamicreports.jasper.base.templatedesign.JasperEmptyTemplateDesign;
import net.sf.dynamicreports.jasper.base.templatedesign.JasperTemplateDesign;
import net.sf.dynamicreports.jasper.builder.export.AbstractJasperExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperExcelApiXlsExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperTextExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXhtmlExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXmlExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXmlssExporterBuilder;
import net.sf.dynamicreports.jasper.transformation.ExporterTransform;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.QueryBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.QueryLanguage;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
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
	private JRVirtualizer virtualizer;
	
	public JasperReportBuilder() {	
		setTemplateDesign(new JasperEmptyTemplateDesign());
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
	
	public JasperReportBuilder setTemplateDesign(InputStream inputStream) throws DRException {
		return setTemplateDesign(new JasperTemplateDesign(inputStream));
	}
	
	public JasperReportBuilder setTemplateDesign(File file) throws DRException {
		return setTemplateDesign(new JasperTemplateDesign(file));
	}
	
	public JasperReportBuilder setTemplateDesign(String fileName) throws DRException {
		return setTemplateDesign(new JasperTemplateDesign(fileName));
	}
	
	public JasperReportBuilder setTemplateDesign(JasperDesign jasperDesign) throws DRException {
		return setTemplateDesign(new JasperTemplateDesign(jasperDesign));
	}
	
	private JasperReportBuilder setTemplateDesign(DRITemplateDesign<JasperDesign> templateDesign) {	
		getObject().setTemplateDesign(templateDesign);
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
			Map<String, Object> parameters = toJasperReportDesign().getParameters();
			if (virtualizer != null) {
				parameters = new HashMap<String, Object>(parameters);
				parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			}
			
			try {
				if (connection != null && getObject().getQuery() != null) {
					jasperPrint = JasperFillManager.fillReport(toJasperReport(), parameters, connection);
				}
				else {
					jasperPrint = JasperFillManager.fillReport(toJasperReport(), parameters, dataSource);
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
	
	public JasperReportBuilder show(boolean exitOnClose) throws DRException {
		JasperViewer.viewReport(toJasperPrint(), exitOnClose, null);
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
	
	public JasperReportBuilder setVirtualizer(JRVirtualizer virtualizer) {		
		this.virtualizer = virtualizer;
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
	
	//csv
	public JasperReportBuilder toCsv(OutputStream outputStream) throws DRException {
		return toCsv(Exporters.csvExporter(outputStream));
	}

	public JasperReportBuilder toCsv(JasperCsvExporterBuilder csvExporterBuilder) throws DRException {
		return export(csvExporterBuilder);
	}
	
	//docx
	public JasperReportBuilder toDocx(OutputStream outputStream) throws DRException {
		return toDocx(Exporters.docxExporter(outputStream));
	}

	public JasperReportBuilder toDocx(JasperDocxExporterBuilder docxExporterBuilder) throws DRException {
		return export(docxExporterBuilder);
	}
	
	//html
	public JasperReportBuilder toHtml(OutputStream outputStream) throws DRException {
		return toHtml(Exporters.htmlExporter(outputStream));
	}
	
	public JasperReportBuilder toHtml(JasperHtmlExporterBuilder htmlExporterBuilder) throws DRException {
		return export(htmlExporterBuilder);
	}
	
	//ods
	public JasperReportBuilder toOds(OutputStream outputStream) throws DRException {
		return toOds(Exporters.odsExporter(outputStream));
	}
	
	public JasperReportBuilder toOds(JasperOdsExporterBuilder odsExporterBuilder) throws DRException {
		return export(odsExporterBuilder);
	}
	
	//odt
	public JasperReportBuilder toOdt(OutputStream outputStream) throws DRException {
		return toOdt(Exporters.odtExporter(outputStream));
	}
	
	public JasperReportBuilder toOdt(JasperOdtExporterBuilder odtExporterBuilder) throws DRException {
		return export(odtExporterBuilder);
	}
	
	//pdf
	public JasperReportBuilder toPdf(OutputStream outputStream) throws DRException {
		return toPdf(Exporters.pdfExporter(outputStream));
	}
	
	public JasperReportBuilder toPdf(JasperPdfExporterBuilder pdfExporterBuilder) throws DRException {
		return export(pdfExporterBuilder);
	}
	
	//rtf
	public JasperReportBuilder toRtf(OutputStream outputStream) throws DRException {
		return toRtf(Exporters.rtfExporter(outputStream));
	}
	
	public JasperReportBuilder toRtf(JasperRtfExporterBuilder rtfExporterBuilder) throws DRException {
		return export(rtfExporterBuilder);
	}
	
	//text
	public JasperReportBuilder toText(OutputStream outputStream) throws DRException {
		return toText(Exporters.textExporter(outputStream));
	}
	
	public JasperReportBuilder toText(JasperTextExporterBuilder textExporterBuilder) throws DRException {
		return export(textExporterBuilder);
	}
	
	//xhtml
	public JasperReportBuilder toXhtml(OutputStream outputStream) throws DRException {
		return toXhtml(Exporters.xhtmlExporter(outputStream));
	}
	
	public JasperReportBuilder toXhtml(JasperXhtmlExporterBuilder xhtmlExporterBuilder) throws DRException {
		return export(xhtmlExporterBuilder);
	}
	
	//excelApiXls
	public JasperReportBuilder toExcelApiXls(OutputStream outputStream) throws DRException {
		return toExcelApiXls(Exporters.excelApiXlsExporter(outputStream));
	}
	
	public JasperReportBuilder toExcelApiXls(JasperExcelApiXlsExporterBuilder excelApiXlsExporterBuilder) throws DRException {
		return export(excelApiXlsExporterBuilder);
	}
	
	//xls
	public JasperReportBuilder toXls(OutputStream outputStream) throws DRException {
		return toXls(Exporters.xlsExporter(outputStream));
	}
	
	public JasperReportBuilder toXls(JasperXlsExporterBuilder xlsExporterBuilder) throws DRException {
		return export(xlsExporterBuilder);
	}
	
	//xlsx
	public JasperReportBuilder toXlsx(OutputStream outputStream) throws DRException {
		return toXlsx(Exporters.xlsxExporter(outputStream));
	}
	
	public JasperReportBuilder toXlsx(JasperXlsxExporterBuilder xlsxExporterBuilder) throws DRException {
		return export(xlsxExporterBuilder);
	}
	
	//xml
	public JasperReportBuilder toXml(OutputStream outputStream) throws DRException {
		return toXml(Exporters.xmlExporter(outputStream));
	}

	public JasperReportBuilder toXml(JasperXmlExporterBuilder xmlExporterBuilder) throws DRException {
		return export(xmlExporterBuilder);
	}
	
	//xmlss
	public JasperReportBuilder toXmlss(OutputStream outputStream) throws DRException {
		return toXmlss(Exporters.xmlssExporter(outputStream));
	}
	
	public JasperReportBuilder toXmlss(JasperXmlssExporterBuilder xmlssExporterBuilder) throws DRException {
		return export(xmlssExporterBuilder);
	}
	
	private JasperReportBuilder export(AbstractJasperExporterBuilder<?, ? extends AbstractJasperExporter> exporterBuilder) throws DRException {
		Validate.notNull(exporterBuilder, "exporterBuilder must not be null");
		try {
			ExporterTransform exporterTransform = new ExporterTransform(exporterBuilder.build());
			JRExporter exporter = exporterTransform.transform();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, toJasperPrint());
			exporter.exportReport();
		} catch (JRException e) {
			throw new DRException(e);
		}
		return this;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public JRDataSource getDataSource() {
		return dataSource;
	}
}
