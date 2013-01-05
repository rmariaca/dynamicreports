/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
 * http://www.dynamicreports.org
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

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRCsvMetadataExporterParameter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterTagHelper;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterNature;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsAbstractMetadataExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.util.JRTextMeasurerUtil;
import net.sf.jasperreports.engine.xml.PrintSaxParserFactory;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class JasperProperty {

	//chart
	/**
	 * @see JRChart#PROPERTY_CHART_RENDER_TYPE
	 */
	public static final String CHART_RENDER_TYPE = JRChart.PROPERTY_CHART_RENDER_TYPE;
	/**
	 * @see JRChart#PROPERTY_CHART_THEME
	 */
	public static final String CHART_THEME = JRChart.PROPERTY_CHART_THEME;

	//export
	/**
	 * @see JRExporterParameter#PROPERTY_CHARACTER_ENCODING
	 */
	public static final String EXPORT_CHARACTER_ENCODING = JRExporterParameter.PROPERTY_CHARACTER_ENCODING;
	/**
	 * @see JRExporterParameter#PROPERTY_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS
	 */
	public static final String EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS = JRExporterParameter.PROPERTY_EXPORT_PARAMETERS_OVERRIDE_REPORT_HINTS;
	/**
	 * @see JRExporterParameter#PROPERTY_IGNORE_PAGE_MARGINS
	 */
	public static final String EXPORT_IGNORE_PAGE_MARGINS = JRExporterParameter.PROPERTY_IGNORE_PAGE_MARGINS;
	/**
	 * @see JRGraphics2DExporter#MINIMIZE_PRINTER_JOB_SIZE
	 */
	public static final String EXPORT_GRAPHICD2D_MINIMIZE_PRINTER_JOB_SIZE = JRGraphics2DExporter.MINIMIZE_PRINTER_JOB_SIZE;
	/**
	 * @see JRAbstractExporter#PROPERTY_DEFAULT_FILTER_FACTORY
	 */
	public static final String EXPORT_DEFAULT_FILTER_FACTORY = JRAbstractExporter.PROPERTY_DEFAULT_FILTER_FACTORY;

	//text
	/**
	 * @see JRTextElement#PROPERTY_PRINT_KEEP_FULL_TEXT
	 */
	public static final String PRINT_KEEP_FULL_TEXT = JRTextElement.PROPERTY_PRINT_KEEP_FULL_TEXT;
	/**
	 * @see JRTextElement#PROPERTY_TRUNCATE_AT_CHAR
	 */
	public static final String TEXT_TRUNCATE_AT_CHAR = JRTextElement.PROPERTY_TRUNCATE_AT_CHAR;
	/**
	 * @see JRTextElement#PROPERTY_TRUNCATE_SUFFIX
	 */
	public static final String TEXT_TRUNCATE_SUFFIX = JRTextElement.PROPERTY_TRUNCATE_SUFFIX;
	/**
	 * @see JRTextElement#PROPERTY_SAVE_LINE_BREAKS
	 */
	public static final String TEXT_SAVE_LINE_BREAKS = JRTextElement.PROPERTY_SAVE_LINE_BREAKS;
	/**
	 * @see JRTextMeasurerUtil#PROPERTY_TEXT_MEASURER_FACTORY
	 */
	public static final String TEXT_MEASURER_FACTORY = JRTextMeasurerUtil.PROPERTY_TEXT_MEASURER_FACTORY;
	/**
	 * @see JRTextExporterParameter#PROPERTY_CHARACTER_WIDTH
	 */
	public static final String EXPORT_TEXT_CHARACTER_WIDTH = JRTextExporterParameter.PROPERTY_CHARACTER_WIDTH;
	/**
	 * @see JRTextExporterParameter#PROPERTY_CHARACTER_HEIGHT
	 */
	public static final String EXPORT_TEXT_CHARACTER_HEIGHT = JRTextExporterParameter.PROPERTY_CHARACTER_HEIGHT;
	/**
	 * @see JRTextExporterParameter#PROPERTY_PAGE_WIDTH
	 */
	public static final String EXPORT_TEXT_PAGE_WIDTH = JRTextExporterParameter.PROPERTY_PAGE_WIDTH;
	/**
	 * @see JRTextExporterParameter#PROPERTY_PAGE_HEIGHT
	 */
	public static final String EXPORT_TEXT_PAGE_HEIGHT = JRTextExporterParameter.PROPERTY_PAGE_HEIGHT;

	//pdf
	/**
	 * @see JRPdfExporterTagHelper#PROPERTY_TAG_TABLE
	 */
	public static final String EXPORT_PDF_TAG_TABLE = JRPdfExporterTagHelper.PROPERTY_TAG_TABLE;
	/**
	 * @see JRPdfExporterTagHelper#PROPERTY_TAG_TR
	 */
	public static final String EXPORT_PDF_TAG_TR = JRPdfExporterTagHelper.PROPERTY_TAG_TR;
	/**
	 * @see JRPdfExporterTagHelper#PROPERTY_TAG_TH
	 */
	public static final String EXPORT_PDF_TAG_TH = JRPdfExporterTagHelper.PROPERTY_TAG_TH;
	/**
	 * @see JRPdfExporterTagHelper#PROPERTY_TAG_TD
	 */
	public static final String EXPORT_PDF_TAG_TD = JRPdfExporterTagHelper.PROPERTY_TAG_TD;
	/**
	 * @see JRPdfExporterTagHelper#PROPERTY_TAG_H1
	 */
	public static final String EXPORT_PDF_TAG_H1 = JRPdfExporterTagHelper.PROPERTY_TAG_H1;
	/**
	 * @see JRPdfExporterTagHelper#PROPERTY_TAG_H2
	 */
	public static final String EXPORT_PDF_TAG_H2 = JRPdfExporterTagHelper.PROPERTY_TAG_H2;
	/**
	 * @see JRPdfExporterTagHelper#PROPERTY_TAG_H3
	 */
	public static final String EXPORT_PDF_TAG_H3 = JRPdfExporterTagHelper.PROPERTY_TAG_H3;
	/**
	 * @see JRPdfExporterTagHelper#PROPERTY_TAG_COLSPAN
	 */
	public static final String EXPORT_PDF_TAG_COLSPAN = JRPdfExporterTagHelper.PROPERTY_TAG_COLSPAN;
	/**
	 * @see JRPdfExporterTagHelper#PROPERTY_TAG_ROWSPAN
	 */
	public static final String EXPORT_PDF_TAG_ROWSPAN = JRPdfExporterTagHelper.PROPERTY_TAG_ROWSPAN;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_CREATE_BATCH_MODE_BOOKMARKS
	 */
	public static final String EXPORT_PDF_CREATE_BATCH_MODE_BOOKMARKS = JRPdfExporterParameter.PROPERTY_CREATE_BATCH_MODE_BOOKMARKS;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_COMPRESSED
	 */
	public static final String EXPORT_PDF_COMPRESSED = JRPdfExporterParameter.PROPERTY_COMPRESSED;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_ENCRYPTED
	 */
	public static final String EXPORT_PDF_ENCRYPTED = JRPdfExporterParameter.PROPERTY_ENCRYPTED;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_128_BIT_KEY
	 */
	public static final String EXPORT_PDF_128_BIT_KEY = JRPdfExporterParameter.PROPERTY_128_BIT_KEY;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_USER_PASSWORD
	 */
	public static final String EXPORT_PDF_USER_PASSWORD = JRPdfExporterParameter.PROPERTY_USER_PASSWORD;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_OWNER_PASSWORD
	 */
	public static final String EXPORT_PDF_OWNER_PASSWORD = JRPdfExporterParameter.PROPERTY_OWNER_PASSWORD;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_PDF_VERSION
	 */
	public static final String EXPORT_PDF_VERSION = JRPdfExporterParameter.PROPERTY_PDF_VERSION;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_FORCE_SVG_SHAPES
	 */
	public static final String EXPORT_PDF_FORCE_SVG_SHAPES = JRPdfExporterParameter.PROPERTY_FORCE_SVG_SHAPES;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_PDF_JAVASCRIPT
	 */
	public static final String EXPORT_PDF_JAVASCRIPT = JRPdfExporterParameter.PROPERTY_PDF_JAVASCRIPT;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_PRINT_SCALING
	 */
	public static final String EXPORT_PDF_PRINT_SCALING = JRPdfExporterParameter.PROPERTY_PRINT_SCALING;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_TAGGED
	 */
	public static final String EXPORT_PDF_TAGGED = JRPdfExporterParameter.PROPERTY_TAGGED;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_TAG_LANGUAGE
	 */
	public static final String EXPORT_PDF_TAG_LANGUAGE = JRPdfExporterParameter.PROPERTY_TAG_LANGUAGE;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_COLLAPSE_MISSING_BOOKMARK_LEVELS
	 */
	public static final String EXPORT_PDF_COLLAPSE_MISSING_BOOKMARK_LEVELS = JRPdfExporterParameter.PROPERTY_COLLAPSE_MISSING_BOOKMARK_LEVELS;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_PDFA_CONFORMANCE
	 */
	public static final String EXPORT_PDF_PDFA_CONFORMANCE = JRPdfExporterParameter.PROPERTY_PDFA_CONFORMANCE;
	/**
	 * @see JRPdfExporterParameter#PROPERTY_PDFA_ICC_PROFILE_PATH
	 */
	public static final String EXPORT_PDF_PDFA_ICC_PROFILE_PATH = JRPdfExporterParameter.PROPERTY_PDFA_ICC_PROFILE_PATH;

	//html
	/**
	 * @see JRHtmlExporter#PROPERTY_HTML_CLASS
	 */
	public static final String EXPORT_HTML_CLASS = JRHtmlExporter.PROPERTY_HTML_CLASS;
	/**
	 * @see JRHtmlExporter#PROPERTY_HTML_ID
	 */
	public static final String EXPORT_HTML_ID = JRHtmlExporter.PROPERTY_HTML_ID;
	/**
	 * @see JRHtmlExporter#PROPERTY_ACCESSIBLE
	 */
	public static final String EXPORT_HTML_ACCESSIBLE = JRHtmlExporter.PROPERTY_ACCESSIBLE;
	/**
	 * @see JRHtmlExporterParameter#PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS
	 */
	public static final String EXPORT_HTML_REMOVE_EMPTY_SPACE_BETWEEN_ROWS = JRHtmlExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS;
	/**
	 * @see JRHtmlExporterParameter#PROPERTY_WHITE_PAGE_BACKGROUND
	 */
	public static final String EXPORT_HTML_WHITE_PAGE_BACKGROUND = JRHtmlExporterParameter.PROPERTY_WHITE_PAGE_BACKGROUND;
	/**
	 * @see JRHtmlExporterParameter#PROPERTY_USING_IMAGES_TO_ALIGN
	 */
	public static final String EXPORT_HTML_USING_IMAGES_TO_ALIGN = JRHtmlExporterParameter.PROPERTY_USING_IMAGES_TO_ALIGN;
	/**
	 * @see JRHtmlExporterParameter#PROPERTY_WRAP_BREAK_WORD
	 */
	public static final String EXPORT_HTML_WRAP_BREAK_WORD = JRHtmlExporterParameter.PROPERTY_WRAP_BREAK_WORD;
	/**
	 * @see JRHtmlExporterParameter#PROPERTY_SIZE_UNIT
	 */
	public static final String EXPORT_HTML_SIZE_UNIT = JRHtmlExporterParameter.PROPERTY_SIZE_UNIT;
	/**
	 * @see JRHtmlExporterParameter#PROPERTY_FRAMES_AS_NESTED_TABLES
	 */
	public static final String EXPORT_HTML_FRAMES_AS_NESTED_TABLES = JRHtmlExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES;
	/**
	 * @see JRHtmlExporterParameter#PROPERTY_FLUSH_OUTPUT
	 */
	public static final String EXPORT_HTML_FLUSH_OUTPUT = JRHtmlExporterParameter.PROPERTY_FLUSH_OUTPUT;

	//xls
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_ONE_PAGE_PER_SHEET
	 */
	public static final String EXPORT_XLS_ONE_PAGE_PER_SHEET = JRXlsAbstractExporterParameter.PROPERTY_ONE_PAGE_PER_SHEET;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS
	 */
	public static final String EXPORT_XLS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS = JRXlsAbstractExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS ;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS
	 */
	public static final String EXPORT_XLS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS = JRXlsAbstractExporterParameter.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_WHITE_PAGE_BACKGROUND
	 */
	public static final String EXPORT_XLS_WHITE_PAGE_BACKGROUND = JRXlsAbstractExporterParameter.PROPERTY_WHITE_PAGE_BACKGROUND;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_DETECT_CELL_TYPE
	 */
	public static final String EXPORT_XLS_DETECT_CELL_TYPE = JRXlsAbstractExporterParameter.PROPERTY_DETECT_CELL_TYPE;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_SHEET_NAMES_PREFIX
	 */
	public static final String EXPORT_XLS_SHEET_NAMES_PREFIX = JRXlsAbstractExporterParameter.PROPERTY_SHEET_NAMES_PREFIX;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_FONT_SIZE_FIX_ENABLED
	 */
	public static final String EXPORT_XLS_FONT_SIZE_FIX_ENABLED = JRXlsAbstractExporterParameter.PROPERTY_FONT_SIZE_FIX_ENABLED;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_IMAGE_BORDER_FIX_ENABLED
	 */
	public static final String EXPORT_XLS_IMAGE_BORDER_FIX_ENABLED = JRXlsAbstractExporterParameter.PROPERTY_IMAGE_BORDER_FIX_ENABLED;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_MAXIMUM_ROWS_PER_SHEET
	 */
	public static final String EXPORT_XLS_MAXIMUM_ROWS_PER_SHEET = JRXlsAbstractExporterParameter.PROPERTY_MAXIMUM_ROWS_PER_SHEET;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_IGNORE_GRAPHICS
	 */
	public static final String EXPORT_XLS_IGNORE_GRAPHICS = JRXlsAbstractExporterParameter.PROPERTY_IGNORE_GRAPHICS;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_COLLAPSE_ROW_SPAN
	 */
	public static final String EXPORT_XLS_COLLAPSE_ROW_SPAN = JRXlsAbstractExporterParameter.PROPERTY_COLLAPSE_ROW_SPAN;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_IGNORE_CELL_BORDER
	 */
	public static final String EXPORT_XLS_IGNORE_CELL_BORDER = JRXlsAbstractExporterParameter.PROPERTY_IGNORE_CELL_BORDER;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_CREATE_CUSTOM_PALETTE
	 */
	public static final String EXPORT_XLS_CREATE_CUSTOM_PALETTE = JRXlsAbstractExporterParameter.PROPERTY_CREATE_CUSTOM_PALETTE;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_IGNORE_CELL_BACKGROUND
	 */
	public static final String EXPORT_XLS_IGNORE_CELL_BACKGROUND = JRXlsAbstractExporterParameter.PROPERTY_IGNORE_CELL_BACKGROUND;
	/**
	 * @see JRXlsAbstractExporterParameter#PROPERTY_PASSWORD
	 */
	public static final String EXPORT_XLS_PASSWORD = JRXlsAbstractExporterParameter.PROPERTY_PASSWORD;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_CELL_FORMULA
	 */
	public static final String EXPORT_XLS_CELL_FORMULA = JRXlsAbstractExporter.PROPERTY_CELL_FORMULA;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_CELL_PATTERN
	 */
	public static final String EXPORT_XLS_CELL_PATTERN = JRXlsAbstractExporter.PROPERTY_CELL_PATTERN;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_WRAP_TEXT
	 */
	public static final String EXPORT_XLS_WRAP_TEXT = JRXlsAbstractExporter.PROPERTY_WRAP_TEXT;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_FIT_WIDTH
	 */
	public static final String EXPORT_XLS_FIT_WIDTH = JRXlsAbstractExporter.PROPERTY_FIT_WIDTH;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_FIT_HEIGHT
	 */
	public static final String EXPORT_XLS_FIT_HEIGHT = JRXlsAbstractExporter.PROPERTY_FIT_HEIGHT;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_CELL_LOCKED
	 */
	public static final String EXPORT_XLS_CELL_LOCKED = JRXlsAbstractExporter.PROPERTY_CELL_LOCKED;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_CELL_HIDDEN
	 */
	public static final String EXPORT_XLS_CELL_HIDDEN = JRXlsAbstractExporter.PROPERTY_CELL_HIDDEN;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_SHEET_HEADER_LEFT
	 */
	public static final String EXPORT_XLS_SHEET_HEADER_LEFT = JRXlsAbstractExporter.PROPERTY_SHEET_HEADER_LEFT;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_SHEET_HEADER_CENTER
	 */
	public static final String EXPORT_XLS_SHEET_HEADER_CENTER = JRXlsAbstractExporter.PROPERTY_SHEET_HEADER_CENTER;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_SHEET_HEADER_RIGHT
	 */
	public static final String EXPORT_XLS_SHEET_HEADER_RIGHT = JRXlsAbstractExporter.PROPERTY_SHEET_HEADER_RIGHT;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_SHEET_FOOTER_LEFT
	 */
	public static final String EXPORT_XLS_SHEET_FOOTER_LEFT = JRXlsAbstractExporter.PROPERTY_SHEET_FOOTER_LEFT;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_SHEET_FOOTER_CENTER
	 */
	public static final String EXPORT_XLS_SHEET_FOOTER_CENTER = JRXlsAbstractExporter.PROPERTY_SHEET_FOOTER_CENTER;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_SHEET_FOOTER_RIGHT
	 */
	public static final String EXPORT_XLS_SHEET_FOOTER_RIGHT = JRXlsAbstractExporter.PROPERTY_SHEET_FOOTER_RIGHT;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_SHEET_DIRECTION
	 */
	public static final String EXPORT_XLS_SHEET_DIRECTION = JRXlsAbstractExporter.PROPERTY_SHEET_DIRECTION;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_FREEZE_ROW
	 */
	public static final String EXPORT_XLS_FREEZE_ROW = JRXlsAbstractExporter.PROPERTY_FREEZE_ROW;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_FREEZE_COLUMN
	 */
	public static final String EXPORT_XLS_FREEZE_COLUMN = JRXlsAbstractExporter.PROPERTY_FREEZE_COLUMN;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_FREEZE_ROW_EDGE
	 */
	public static final String EXPORT_XLS_FREEZE_ROW_EDGE = JRXlsAbstractExporter.PROPERTY_FREEZE_ROW_EDGE;
	/**
	 * @see JRXlsAbstractExporter#PROPERTY_FREEZE_COLUMN_EDGE
	 */
	public static final String EXPORT_XLS_FREEZE_COLUMN_EDGE = JRXlsAbstractExporter.PROPERTY_FREEZE_COLUMN_EDGE;
	/**
	 * @see JRXlsAbstractExporterNature#PROPERTY_BREAK_BEFORE_ROW
	 */
	public static final String EXPORT_XLS_BREAK_BEFORE_ROW = JRXlsAbstractExporterNature.PROPERTY_BREAK_BEFORE_ROW;
	/**
	 * @see JRXlsAbstractExporterNature#PROPERTY_BREAK_AFTER_ROW
	 */
	public static final String EXPORT_XLS_BREAK_AFTER_ROW = JRXlsAbstractExporterNature.PROPERTY_BREAK_AFTER_ROW;

	//xls metadata
	/**
	 * @see JRXlsAbstractMetadataExporterParameter#PROPERTY_COLUMN_NAMES_PREFIX
	 */
	public static final String EXPORT_XLSMETADATA_COLUMN_NAMES_PREFIX = JRXlsAbstractMetadataExporterParameter.PROPERTY_COLUMN_NAMES_PREFIX;
	/**
	 * @see JRXlsAbstractMetadataExporterParameter#PROPERTY_WRITE_HEADER
	 */
	public static final String EXPORT_XLSMETADATA_WRITE_HEADER = JRXlsAbstractMetadataExporterParameter.PROPERTY_WRITE_HEADER;
	/**
	 * @see JRXlsAbstractMetadataExporterParameter#PROPERTY_COLUMN_NAME
	 */
	public static final String EXPORT_XLSMETADATA_COLUMN_NAME = JRXlsAbstractMetadataExporterParameter.PROPERTY_COLUMN_NAME;
	/**
	 * @see JRXlsAbstractMetadataExporterParameter#PROPERTY_REPEAT_VALUE
	 */
	public static final String EXPORT_XLSMETADATA_REPEAT_VALUE = JRXlsAbstractMetadataExporterParameter.PROPERTY_REPEAT_VALUE;
	/**
	 * @see JRXlsAbstractMetadataExporterParameter#PROPERTY_DATA
	 */
	public static final String EXPORT_XLSMETADATA_DATA = JRXlsAbstractMetadataExporterParameter.PROPERTY_DATA;

	//jxl
	/**
	 * @see JExcelApiExporter#PROPERTY_USE_TEMP_FILE
	 */
	public static final String EXPORT_JXL_USE_TEMP_FILE = JExcelApiExporter.PROPERTY_USE_TEMP_FILE;
	/**
	 * @see JRXlsAbstractMetadataExporterParameter#PROPERTY_COMPLEX_FORMAT
	 */
	public static final String EXPORT_JXL_COMPLEX_FORMAT = JExcelApiExporter.PROPERTY_COMPLEX_FORMAT;

	//xml
	/**
	 * @see PrintSaxParserFactory#EXPORT_XML_VALIDATION
	 */
	public static final String EXPORT_XML_VALIDATION = PrintSaxParserFactory.EXPORT_XML_VALIDATION;

	//csv
	/**
	 * @see JRCsvExporterParameter#PROPERTY_FIELD_DELIMITER
	 */
	public static final String EXPORT_CSV_FIELD_DELIMITER = JRCsvExporterParameter.PROPERTY_FIELD_DELIMITER;
	/**
	 * @see JRCsvExporterParameter#PROPERTY_RECORD_DELIMITER
	 */
	public static final String EXPORT_CSV_RECORD_DELIMITER = JRCsvExporterParameter.PROPERTY_RECORD_DELIMITER;

	//csv metadata
	/**
	 * @see JRCsvMetadataExporterParameter#PROPERTY_COLUMN_NAMES_PREFIX
	 */
	public static final String EXPORT_CSVMETADATA_COLUMN_NAMES_PREFIX = JRCsvMetadataExporterParameter.PROPERTY_COLUMN_NAMES_PREFIX;
	/**
	 * @see JRCsvMetadataExporterParameter#PROPERTY_WRITE_HEADER
	 */
	public static final String EXPORT_CSVMETADATA_WRITE_HEADER = JRCsvMetadataExporterParameter.PROPERTY_WRITE_HEADER;
	/**
	 * @see JRCsvMetadataExporterParameter#PROPERTY_COLUMN_NAME
	 */
	public static final String EXPORT_CSVMETADATA_COLUMN_NAME = JRCsvMetadataExporterParameter.PROPERTY_COLUMN_NAME;
	/**
	 * @see JRCsvMetadataExporterParameter#PROPERTY_REPEAT_VALUE
	 */
	public static final String EXPORT_CSVMETADATA_REPEAT_VALUE = JRCsvMetadataExporterParameter.PROPERTY_REPEAT_VALUE;
	/**
	 * @see JRCsvMetadataExporterParameter#PROPERTY_DATA
	 */
	public static final String EXPORT_CSVMETADATA_DATA = JRCsvMetadataExporterParameter.PROPERTY_DATA;

	//docx
	/**
	 * @see JRDocxExporter#PROPERTY_HIDDEN_TEXT
	 */
	public static final String EXPORT_DOCX_HIDDEN_TEXT = JRDocxExporter.PROPERTY_HIDDEN_TEXT;
	/**
	 * @see JRDocxExporterParameter#PROPERTY_FRAMES_AS_NESTED_TABLES
	 */
	public static final String EXPORT_DOCX_FRAMES_AS_NESTED_TABLES = JRDocxExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES;
	/**
	 * @see JRDocxExporterParameter#PROPERTY_FLEXIBLE_ROW_HEIGHT
	 */
	public static final String EXPORT_DOCX_FLEXIBLE_ROW_HEIGHT = JRDocxExporterParameter.PROPERTY_FLEXIBLE_ROW_HEIGHT;
}
