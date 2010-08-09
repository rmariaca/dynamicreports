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

package net.sf.dynamicreports.jasper.builder.export;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;


/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class ExporterBuilders {
	
	//csv
	public JasperCsvExporterBuilder csvExporter(Writer outputWriter) {
		return Exporters.csvExporter(outputWriter);
	}
	
	public JasperCsvExporterBuilder csvExporter(OutputStream outputStream) {
		return Exporters.csvExporter(outputStream);
	}
	
	public JasperCsvExporterBuilder csvExporter(File outputFile) {
		return Exporters.csvExporter(outputFile);
	}
	
	public JasperCsvExporterBuilder csvExporter(String outputFileName) {
		return Exporters.csvExporter(outputFileName);
	}
	
	//docx
	public JasperDocxExporterBuilder docxExporter(Writer outputWriter) {
		return Exporters.docxExporter(outputWriter);
	}
	
	public JasperDocxExporterBuilder docxExporter(OutputStream outputStream) {
		return Exporters.docxExporter(outputStream);
	}
	
	public JasperDocxExporterBuilder docxExporter(File outputFile) {
		return Exporters.docxExporter(outputFile);
	}
	
	public JasperDocxExporterBuilder docxExporter(String outputFileName) {
		return Exporters.docxExporter(outputFileName);
	}
	
	//html
	public JasperHtmlExporterBuilder htmlExporter(Writer outputWriter) {
		return Exporters.htmlExporter(outputWriter);
	}
	
	public JasperHtmlExporterBuilder htmlExporter(OutputStream outputStream) {
		return Exporters.htmlExporter(outputStream);
	}
	
	public JasperHtmlExporterBuilder htmlExporter(File outputFile) {
		return Exporters.htmlExporter(outputFile);
	}
	
	public JasperHtmlExporterBuilder htmlExporter(String outputFileName) {
		return Exporters.htmlExporter(outputFileName);
	}
	
	//ods
	public JasperOdsExporterBuilder odsExporter(Writer outputWriter) {
		return Exporters.odsExporter(outputWriter);
	}
	
	public JasperOdsExporterBuilder odsExporter(OutputStream outputStream) {
		return Exporters.odsExporter(outputStream);
	}
	
	public JasperOdsExporterBuilder odsExporter(File outputFile) {
		return Exporters.odsExporter(outputFile);
	}
	
	public JasperOdsExporterBuilder odsExporter(String outputFileName) {
		return Exporters.odsExporter(outputFileName);
	}
	
	//odt
	public JasperOdtExporterBuilder odtExporter(Writer outputWriter) {
		return Exporters.odtExporter(outputWriter);
	}
	
	public JasperOdtExporterBuilder odtExporter(OutputStream outputStream) {
		return Exporters.odtExporter(outputStream);
	}
	
	public JasperOdtExporterBuilder odtExporter(File outputFile) {
		return Exporters.odtExporter(outputFile);
	}
	
	public JasperOdtExporterBuilder odtExporter(String outputFileName) {
		return Exporters.odtExporter(outputFileName);
	}
	
	//pdf
	public JasperPdfExporterBuilder pdfExporter(Writer outputWriter) {
		return Exporters.pdfExporter(outputWriter);
	}
	
	public JasperPdfExporterBuilder pdfExporter(OutputStream outputStream) {
		return Exporters.pdfExporter(outputStream);
	}
	
	public JasperPdfExporterBuilder pdfExporter(File outputFile) {
		return Exporters.pdfExporter(outputFile);
	}
	
	public JasperPdfExporterBuilder pdfExporter(String outputFileName) {
		return Exporters.pdfExporter(outputFileName);
	}
	
	//rtf
	public JasperRtfExporterBuilder rtfExporter(Writer outputWriter) {
		return Exporters.rtfExporter(outputWriter);
	}
	
	public JasperRtfExporterBuilder rtfExporter(OutputStream outputStream) {
		return Exporters.rtfExporter(outputStream);
	}
	
	public JasperRtfExporterBuilder rtfExporter(File outputFile) {
		return Exporters.rtfExporter(outputFile);
	}
	
	public JasperRtfExporterBuilder rtfExporter(String outputFileName) {
		return Exporters.rtfExporter(outputFileName);
	}
	
	//text
	public JasperTextExporterBuilder textExporter(Writer outputWriter) {
		return Exporters.textExporter(outputWriter);
	}
	
	public JasperTextExporterBuilder textExporter(OutputStream outputStream) {
		return Exporters.textExporter(outputStream);
	}
	
	public JasperTextExporterBuilder textExporter(File outputFile) {
		return Exporters.textExporter(outputFile);
	}
	
	public JasperTextExporterBuilder textExporter(String outputFileName) {
		return Exporters.textExporter(outputFileName);
	}
	
	//xhtml
	public JasperXhtmlExporterBuilder xhtmlExporter(Writer outputWriter) {
		return Exporters.xhtmlExporter(outputWriter);
	}
	
	public JasperXhtmlExporterBuilder xhtmlExporter(OutputStream outputStream) {
		return Exporters.xhtmlExporter(outputStream);
	}
	
	public JasperXhtmlExporterBuilder xhtmlExporter(File outputFile) {
		return Exporters.xhtmlExporter(outputFile);
	}
	
	public JasperXhtmlExporterBuilder xhtmlExporter(String outputFileName) {
		return Exporters.xhtmlExporter(outputFileName);
	}
	
	//excelApiXls
	public JasperExcelApiXlsExporterBuilder excelApiXlsExporter(Writer outputWriter) {
		return Exporters.excelApiXlsExporter(outputWriter);
	}
	
	public JasperExcelApiXlsExporterBuilder excelApiXlsExporter(OutputStream outputStream) {
		return Exporters.excelApiXlsExporter(outputStream);
	}
	
	public JasperExcelApiXlsExporterBuilder excelApiXlsExporter(File outputFile) {
		return Exporters.excelApiXlsExporter(outputFile);
	}
	
	public JasperExcelApiXlsExporterBuilder excelApiXlsExporter(String outputFileName) {
		return Exporters.excelApiXlsExporter(outputFileName);
	}
	
	//xls
	public JasperXlsExporterBuilder xlsExporter(Writer outputWriter) {
		return Exporters.xlsExporter(outputWriter);
	}
	
	public JasperXlsExporterBuilder xlsExporter(OutputStream outputStream) {
		return Exporters.xlsExporter(outputStream);
	}
	
	public JasperXlsExporterBuilder xlsExporter(File outputFile) {
		return Exporters.xlsExporter(outputFile);
	}
	
	public JasperXlsExporterBuilder xlsExporter(String outputFileName) {
		return Exporters.xlsExporter(outputFileName);
	}
	
	//xlsx
	public JasperXlsxExporterBuilder xlsxExporter(Writer outputWriter) {
		return Exporters.xlsxExporter(outputWriter);
	}
	
	public JasperXlsxExporterBuilder xlsxExporter(OutputStream outputStream) {
		return Exporters.xlsxExporter(outputStream);
	}
	
	public JasperXlsxExporterBuilder xlsxExporter(File outputFile) {
		return Exporters.xlsxExporter(outputFile);
	}
	
	public JasperXlsxExporterBuilder xlsxExporter(String outputFileName) {
		return Exporters.xlsxExporter(outputFileName);
	}
	
	//xml
	public JasperXmlExporterBuilder xmlExporter(Writer outputWriter) {
		return Exporters.xmlExporter(outputWriter);
	}
	
	public JasperXmlExporterBuilder xmlExporter(OutputStream outputStream) {
		return Exporters.xmlExporter(outputStream);
	}
	
	public JasperXmlExporterBuilder xmlExporter(File outputFile) {
		return Exporters.xmlExporter(outputFile);
	}
	
	public JasperXmlExporterBuilder xmlExporter(String outputFileName) {
		return Exporters.xmlExporter(outputFileName);
	}
	
	//xmlss
	public JasperXmlssExporterBuilder xmlssExporter(Writer outputWriter) {
		return Exporters.xmlssExporter(outputWriter);
	}
	
	public JasperXmlssExporterBuilder xmlssExporter(OutputStream outputStream) {
		return Exporters.xmlssExporter(outputStream);
	}
	
	public JasperXmlssExporterBuilder xmlssExporter(File outputFile) {
		return Exporters.xmlssExporter(outputFile);
	}
	
	public JasperXmlssExporterBuilder xmlssExporter(String outputFileName) {
		return Exporters.xmlssExporter(outputFileName);
	}
}
