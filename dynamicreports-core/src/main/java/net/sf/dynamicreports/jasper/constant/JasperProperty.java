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

package net.sf.dynamicreports.jasper.constant;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperProperty {
	//text
	public static final String PRINT_KEEP_FULL_TEXT = "net.sf.jasperreports.print.keep.full.text";
	public static final String TEXT_TRUNCATE_AT_CHAR = "net.sf.jasperreports.text.truncate.at.char";
	public static final String TEXT_TRUNCATE_SUFFIX = "net.sf.jasperreports.text.truncate.suffix";
	public static final String TEXT_MEASURER_FACTORY = "net.sf.jasperreports.text.measurer.factory";
	
	//chart
	public static final String CHART_THEME = "net.sf.jasperreports.chart.theme";
	
	//pdf
	public static final String EXPORT_PDF_TAG_H1 = "net.sf.jasperreports.export.pdf.tag.h1";
	public static final String EXPORT_PDF_TAG_H2 = "net.sf.jasperreports.export.pdf.tag.h2";
	public static final String EXPORT_PDF_TAG_H3 = "net.sf.jasperreports.export.pdf.tag.h3";
	public static final String EXPORT_PDF_TAG_TABLE = "net.sf.jasperreports.export.pdf.tag.table";
	public static final String EXPORT_PDF_TAG_TR = "net.sf.jasperreports.export.pdf.tag.tr";
	public static final String EXPORT_PDF_TAG_TH = "net.sf.jasperreports.export.pdf.tag.th";
	public static final String EXPORT_PDF_TAG_TD = "net.sf.jasperreports.export.pdf.tag.td";
	public static final String EXPORT_PDF_TAG_COLSPAN = "net.sf.jasperreports.export.pdf.tag.colspan";
	public static final String EXPORT_PDF_TAG_ROWSPAN = "net.sf.jasperreports.export.pdf.tag.rowspan";
	public static final String EXPORT_PDF_FORCE_SVG_SHAPES = "net.sf.jasperreports.export.pdf.force.svg.shapes";
	public static final String EXPORT_PDF_FORCE_LINEBREAK_POLICY = "net.sf.jasperreports.export.pdf.force.linebreak.policy";
	public static final String EXPORT_PDF_CREATE_BATCH_MODE_BOOKMARKS = "net.sf.jasperreports.export.pdf.create.batch.mode.bookmarks";
	public static final String EXPORT_PDF_COMPRESSED = "net.sf.jasperreports.export.pdf.compressed";
	public static final String EXPORT_PDF_ENCRYPTED = "net.sf.jasperreports.export.pdf.encrypted";
	public static final String EXPORT_PDF_128_BIT_KEY = "net.sf.jasperreports.export.pdf.128.bit.key";
	public static final String EXPORT_PDF_TAGGED = "net.sf.jasperreports.export.pdf.tagged";
	public static final String EXPORT_PDF_TAG_LANGUAGE = "net.sf.jasperreports.export.pdf.tag.language";
	
	//html
	public static final String EXPORT_HTML_ID = "net.sf.jasperreports.export.html.id";
	public static final String EXPORT_HTML_FRAMES_AS_NESTED_TABLES = "net.sf.jasperreports.export.html.frames.as.nested.tables";
	public static final String EXPORT_HTML_REMOVE_EMPTY_SPACE_BETWEEN_ROWS = "net.sf.jasperreports.export.html.remove.empty.space.between.rows";
	public static final String EXPORT_HTML_WHITE_PAGE_BACKGROUND = "net.sf.jasperreports.export.html.white.page.background";
	public static final String EXPORT_HTML_WRAP_BREAK_WORD = "net.sf.jasperreports.export.html.wrap.break.word";
	public static final String EXPORT_HTML_SIZE_UNIT = "net.sf.jasperreports.export.html.size.unit";
	public static final String EXPORT_HTML_USING_IMAGES_TO_ALIGN = "net.sf.jasperreports.export.html.using.images.to.align";
	
	//export
	public static final String EXPORT_CHARACTER_ENCODING = "net.sf.jasperreports.export.character.encoding";
	public static final String EXPORT_GRAPHICD2D_MIN_JOB_SIZE = "net.sf.jasperreports.export.graphics2d.min.job.size";
	
	//xls
	public static final String EXPORT_XLS_CREATE_CUSTOM_PALETTE = "net.sf.jasperreports.export.xls.create.custom.palette";
	public static final String EXPORT_XLS_ONE_PAGE_PER_SHEET = "net.sf.jasperreports.export.xls.one.page.per.sheet";
	public static final String EXPORT_XLS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS = "net.sf.jasperreports.export.xls.remove.empty.space.between.rows";
	public static final String EXPORT_XLS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS = "net.sf.jasperreports.export.xls.remove.empty.space.between.columns";
	public static final String EXPORT_XLS_WHITE_PAGE_BACKGROUND = "net.sf.jasperreports.export.xls.white.page.background";
	public static final String EXPORT_XLS_DETECT_CELL_TYPE = "net.sf.jasperreports.export.xls.detect.cell.type";
	public static final String EXPORT_XLS_SIZE_FIX_ENABLED = "net.sf.jasperreports.export.xls.size.fix.enabled";
	public static final String EXPORT_XLS_IGNORE_GRAPHICS = "net.sf.jasperreports.export.xls.ignore.graphics";
	public static final String EXPORT_XLS_COLLAPSE_ROW_SPAN = "net.sf.jasperreports.export.xls.collapse.row.span";
	public static final String EXPORT_XLS_IGNORE_CELL_BORDER = "net.sf.jasperreports.export.xls.ignore.cell.border";
	public static final String EXPORT_XLS_MAX_ROWS_PER_SHEET = "net.sf.jasperreports.export.xls.max.rows.per.sheet";
	
	//xml
	public static final String EXPORT_XML_VALIDATION = "net.sf.jasperreports.export.xml.validation";
	
	//csv
	public static final String EXPORT_CSV_FIELD_DELIMITER = "net.sf.jasperreports.export.csv.field.delimiter";
	public static final String EXPORT_CSV_RECORD_DELIMITER = "net.sf.jasperreports.export.csv.record.delimiter";
}
