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

package net.sf.dynamicreports.jasper.builder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import net.sf.dynamicreports.jasper.base.export.AbstractJasperExporter;
import net.sf.dynamicreports.jasper.base.reporthandler.JasperReportBuilderHandler;
import net.sf.dynamicreports.jasper.builder.export.AbstractJasperExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperExcelApiXlsExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperTextExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXhtmlExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXmlExporterBuilder;
import net.sf.dynamicreports.jasper.definition.JasperReportHandler;
import net.sf.dynamicreports.jasper.transformation.ExporterTransform;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;

import org.apache.commons.lang.Validate;

/**
 * This report builder allows concatenating several separated reports into one single document.
 * Each report starts on a new page with its own page dimension.
 *
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperConcatenatedReportBuilder implements Serializable {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private JasperReportHandler jasperReportHandler;

	public JasperConcatenatedReportBuilder() {
		this(new JasperReportBuilderHandler());
	}

	public JasperConcatenatedReportBuilder(JasperReportHandler jasperReportHandler) {
		this.jasperReportHandler = jasperReportHandler;
	}

	public JasperConcatenatedReportBuilder concatenate(JasperReportBuilder ...jasperReportBuilders) {
		Validate.notNull(jasperReportBuilders, "jasperReportBuilders must not be null");
		Validate.noNullElements(jasperReportBuilders, "jasperReportBuilders must not contains null jasperReportBuilder");
		jasperReportHandler.concatenate(jasperReportBuilders);
		return this;
	}

	public JasperConcatenatedReportBuilder toPng(OutputStream outputStream) throws DRException {
		return toPng(outputStream, 1);
	}

	public JasperConcatenatedReportBuilder toPng(OutputStream outputStream, float zoom) throws DRException {
		Validate.notNull(outputStream, "outputStream must not be null");
		Validate.isTrue(zoom > 0, "zoom must be > 0");

		int maxWidth = 0;
		int maxHeight = 0;

		for (JasperPrint jasperPrint : jasperReportHandler.getPrintList()) {
			int pages = jasperPrint.getPages().size();
			int pageWidth = (int) (jasperPrint.getPageWidth() * zoom);
			maxWidth += pageWidth * pages + (pages - 1) + 2;
			int height = (int) (jasperPrint.getPageHeight() * zoom) + 2;
			if (height > maxHeight) {
				maxHeight = height;
			}
		}

		Image pageImage = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) pageImage.getGraphics();
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(new Rectangle2D.Float(1, 1, maxWidth - 1, maxHeight - 1));

		int offset = 1;
		for (JasperPrint jasperPrint : jasperReportHandler.getPrintList()) {
			int pageWidth = (int) (jasperPrint.getPageWidth() * zoom);
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				try {
					JRGraphics2DExporter exporter = new JRGraphics2DExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, pageImage.getGraphics());
					exporter.setParameter(JRGraphics2DExporterParameter.OFFSET_X, offset);
					exporter.setParameter(JRGraphics2DExporterParameter.OFFSET_Y, 1);
					exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(i));
					exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, new Float(zoom));
					exporter.exportReport();
					offset += pageWidth + 1;
				} catch (JRException e) {
					throw new DRException(e);
				}
			}
		}
		try {
			ImageIO.write((RenderedImage) pageImage, "png", outputStream);
		} catch (IOException e) {
			throw new DRException(e);
		}
		return this;
	}

	public JasperConcatenatedReportBuilder continuousPageNumbering() {
		return setContinuousPageNumbering(true);
	}

	public JasperConcatenatedReportBuilder setContinuousPageNumbering(boolean continuousPageNumbering) {
		jasperReportHandler.setContinuousPageNumbering(continuousPageNumbering);
		return this;
	}

	//csv
	public JasperConcatenatedReportBuilder toCsv(OutputStream outputStream) throws DRException {
		return toCsv(Exporters.csvExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toCsv(JasperCsvExporterBuilder csvExporterBuilder) throws DRException {
		return export(csvExporterBuilder);
	}

	//docx
	public JasperConcatenatedReportBuilder toDocx(OutputStream outputStream) throws DRException {
		return toDocx(Exporters.docxExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toDocx(JasperDocxExporterBuilder docxExporterBuilder) throws DRException {
		return export(docxExporterBuilder);
	}

	//html
	public JasperConcatenatedReportBuilder toHtml(OutputStream outputStream) throws DRException {
		return toHtml(Exporters.htmlExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toHtml(JasperHtmlExporterBuilder htmlExporterBuilder) throws DRException {
		return export(htmlExporterBuilder);
	}

	//ods
	public JasperConcatenatedReportBuilder toOds(OutputStream outputStream) throws DRException {
		return toOds(Exporters.odsExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toOds(JasperOdsExporterBuilder odsExporterBuilder) throws DRException {
		return export(odsExporterBuilder);
	}

	//odt
	public JasperConcatenatedReportBuilder toOdt(OutputStream outputStream) throws DRException {
		return toOdt(Exporters.odtExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toOdt(JasperOdtExporterBuilder odtExporterBuilder) throws DRException {
		return export(odtExporterBuilder);
	}

	//pdf
	public JasperConcatenatedReportBuilder toPdf(OutputStream outputStream) throws DRException {
		return toPdf(Exporters.pdfExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toPdf(JasperPdfExporterBuilder pdfExporterBuilder) throws DRException {
		return export(pdfExporterBuilder);
	}

	//rtf
	public JasperConcatenatedReportBuilder toRtf(OutputStream outputStream) throws DRException {
		return toRtf(Exporters.rtfExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toRtf(JasperRtfExporterBuilder rtfExporterBuilder) throws DRException {
		return export(rtfExporterBuilder);
	}

	//text
	public JasperConcatenatedReportBuilder toText(OutputStream outputStream) throws DRException {
		return toText(Exporters.textExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toText(JasperTextExporterBuilder textExporterBuilder) throws DRException {
		return export(textExporterBuilder);
	}

	//xhtml
	public JasperConcatenatedReportBuilder toXhtml(OutputStream outputStream) throws DRException {
		return toXhtml(Exporters.xhtmlExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toXhtml(JasperXhtmlExporterBuilder xhtmlExporterBuilder) throws DRException {
		return export(xhtmlExporterBuilder);
	}

	//excelApiXls
	public JasperConcatenatedReportBuilder toExcelApiXls(OutputStream outputStream) throws DRException {
		return toExcelApiXls(Exporters.excelApiXlsExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toExcelApiXls(JasperExcelApiXlsExporterBuilder excelApiXlsExporterBuilder) throws DRException {
		return export(excelApiXlsExporterBuilder);
	}

	//xls
	public JasperConcatenatedReportBuilder toXls(OutputStream outputStream) throws DRException {
		return toXls(Exporters.xlsExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toXls(JasperXlsExporterBuilder xlsExporterBuilder) throws DRException {
		return export(xlsExporterBuilder);
	}

	//xlsx
	public JasperConcatenatedReportBuilder toXlsx(OutputStream outputStream) throws DRException {
		return toXlsx(Exporters.xlsxExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toXlsx(JasperXlsxExporterBuilder xlsxExporterBuilder) throws DRException {
		return export(xlsxExporterBuilder);
	}

	//xml
	public JasperConcatenatedReportBuilder toXml(OutputStream outputStream) throws DRException {
		return toXml(Exporters.xmlExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toXml(JasperXmlExporterBuilder xmlExporterBuilder) throws DRException {
		return export(xmlExporterBuilder);
	}

	//pptx
	public JasperConcatenatedReportBuilder toPptx(OutputStream outputStream) throws DRException {
		return toPptx(Exporters.pptxExporter(outputStream));
	}

	public JasperConcatenatedReportBuilder toPptx(JasperPptxExporterBuilder pptxExporterBuilder) throws DRException {
		return export(pptxExporterBuilder);
	}

	public JasperConcatenatedReportBuilder export(AbstractJasperExporterBuilder<?, ? extends AbstractJasperExporter> exporterBuilder) throws DRException {
		Validate.notNull(exporterBuilder, "exporterBuilder must not be null");
		try {
			ExporterTransform exporterTransform = new ExporterTransform(exporterBuilder.build());
			JRExporter exporter = exporterTransform.transform();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperReportHandler.getPrintList());
			exporter.exportReport();
		} catch (JRException e) {
			throw new DRException(e);
		}
		return this;
	}
}
