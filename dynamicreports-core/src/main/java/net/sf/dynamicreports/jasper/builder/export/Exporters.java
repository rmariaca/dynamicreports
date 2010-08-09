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
public class Exporters {
	
	//csv
	public static JasperCsvExporterBuilder csvExporter(Writer outputWriter) {
		return new JasperCsvExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperCsvExporterBuilder csvExporter(OutputStream outputStream) {
		return new JasperCsvExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperCsvExporterBuilder csvExporter(File outputFile) {
		return new JasperCsvExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperCsvExporterBuilder csvExporter(String outputFileName) {
		return new JasperCsvExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//docx
	public static JasperDocxExporterBuilder docxExporter(Writer outputWriter) {
		return new JasperDocxExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperDocxExporterBuilder docxExporter(OutputStream outputStream) {
		return new JasperDocxExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperDocxExporterBuilder docxExporter(File outputFile) {
		return new JasperDocxExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperDocxExporterBuilder docxExporter(String outputFileName) {
		return new JasperDocxExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//html
	public static JasperHtmlExporterBuilder htmlExporter(Writer outputWriter) {
		return new JasperHtmlExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperHtmlExporterBuilder htmlExporter(OutputStream outputStream) {
		return new JasperHtmlExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperHtmlExporterBuilder htmlExporter(File outputFile) {
		return new JasperHtmlExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperHtmlExporterBuilder htmlExporter(String outputFileName) {
		return new JasperHtmlExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//ods
	public static JasperOdsExporterBuilder odsExporter(Writer outputWriter) {
		return new JasperOdsExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperOdsExporterBuilder odsExporter(OutputStream outputStream) {
		return new JasperOdsExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperOdsExporterBuilder odsExporter(File outputFile) {
		return new JasperOdsExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperOdsExporterBuilder odsExporter(String outputFileName) {
		return new JasperOdsExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//odt
	public static JasperOdtExporterBuilder odtExporter(Writer outputWriter) {
		return new JasperOdtExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperOdtExporterBuilder odtExporter(OutputStream outputStream) {
		return new JasperOdtExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperOdtExporterBuilder odtExporter(File outputFile) {
		return new JasperOdtExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperOdtExporterBuilder odtExporter(String outputFileName) {
		return new JasperOdtExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//pdf
	public static JasperPdfExporterBuilder pdfExporter(Writer outputWriter) {
		return new JasperPdfExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperPdfExporterBuilder pdfExporter(OutputStream outputStream) {
		return new JasperPdfExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperPdfExporterBuilder pdfExporter(File outputFile) {
		return new JasperPdfExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperPdfExporterBuilder pdfExporter(String outputFileName) {
		return new JasperPdfExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//rtf
	public static JasperRtfExporterBuilder rtfExporter(Writer outputWriter) {
		return new JasperRtfExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperRtfExporterBuilder rtfExporter(OutputStream outputStream) {
		return new JasperRtfExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperRtfExporterBuilder rtfExporter(File outputFile) {
		return new JasperRtfExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperRtfExporterBuilder rtfExporter(String outputFileName) {
		return new JasperRtfExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//text
	public static JasperTextExporterBuilder textExporter(Writer outputWriter) {
		return new JasperTextExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperTextExporterBuilder textExporter(OutputStream outputStream) {
		return new JasperTextExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperTextExporterBuilder textExporter(File outputFile) {
		return new JasperTextExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperTextExporterBuilder textExporter(String outputFileName) {
		return new JasperTextExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//xhtml
	public static JasperXhtmlExporterBuilder xhtmlExporter(Writer outputWriter) {
		return new JasperXhtmlExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperXhtmlExporterBuilder xhtmlExporter(OutputStream outputStream) {
		return new JasperXhtmlExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperXhtmlExporterBuilder xhtmlExporter(File outputFile) {
		return new JasperXhtmlExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperXhtmlExporterBuilder xhtmlExporter(String outputFileName) {
		return new JasperXhtmlExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//excelApiXls
	public static JasperExcelApiXlsExporterBuilder excelApiXlsExporter(Writer outputWriter) {
		return new JasperExcelApiXlsExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperExcelApiXlsExporterBuilder excelApiXlsExporter(OutputStream outputStream) {
		return new JasperExcelApiXlsExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperExcelApiXlsExporterBuilder excelApiXlsExporter(File outputFile) {
		return new JasperExcelApiXlsExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperExcelApiXlsExporterBuilder excelApiXlsExporter(String outputFileName) {
		return new JasperExcelApiXlsExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//xls
	public static JasperXlsExporterBuilder xlsExporter(Writer outputWriter) {
		return new JasperXlsExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperXlsExporterBuilder xlsExporter(OutputStream outputStream) {
		return new JasperXlsExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperXlsExporterBuilder xlsExporter(File outputFile) {
		return new JasperXlsExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperXlsExporterBuilder xlsExporter(String outputFileName) {
		return new JasperXlsExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//xlsx
	public static JasperXlsxExporterBuilder xlsxExporter(Writer outputWriter) {
		return new JasperXlsxExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperXlsxExporterBuilder xlsxExporter(OutputStream outputStream) {
		return new JasperXlsxExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperXlsxExporterBuilder xlsxExporter(File outputFile) {
		return new JasperXlsxExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperXlsxExporterBuilder xlsxExporter(String outputFileName) {
		return new JasperXlsxExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//xml
	public static JasperXmlExporterBuilder xmlExporter(Writer outputWriter) {
		return new JasperXmlExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperXmlExporterBuilder xmlExporter(OutputStream outputStream) {
		return new JasperXmlExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperXmlExporterBuilder xmlExporter(File outputFile) {
		return new JasperXmlExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperXmlExporterBuilder xmlExporter(String outputFileName) {
		return new JasperXmlExporterBuilder().setOutputFileName(outputFileName);
	}
	
	//xmlss
	public static JasperXmlssExporterBuilder xmlssExporter(Writer outputWriter) {
		return new JasperXmlssExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperXmlssExporterBuilder xmlssExporter(OutputStream outputStream) {
		return new JasperXmlssExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperXmlssExporterBuilder xmlssExporter(File outputFile) {
		return new JasperXmlssExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperXmlssExporterBuilder xmlssExporter(String outputFileName) {
		return new JasperXmlssExporterBuilder().setOutputFileName(outputFileName);
	}
}
