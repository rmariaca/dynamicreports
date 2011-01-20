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

package net.sf.dynamicreports.jasper.transformation;

import net.sf.dynamicreports.design.transformation.StyleResolver;
import net.sf.dynamicreports.jasper.definition.export.JasperICsvExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIDocxExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIExcelApiXlsExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIExcelExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIHtmlExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIImageExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIOdsExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIOdtExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIPdfExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIRtfExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperITextExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIXhtmlExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIXlsExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIXlsxExporter;
import net.sf.dynamicreports.jasper.definition.export.JasperIXmlExporter;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.defaults.Defaults;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.JRXmlExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ExporterTransform {
	private JasperIExporter jasperExporter;

	public ExporterTransform(JasperIExporter jasperExporter) {
		this.jasperExporter = jasperExporter;
	}

	public JRExporter transform() throws DRException {
		JRExporter jrExporter;

		if (jasperExporter instanceof JasperICsvExporter) {
			jrExporter = csv((JasperICsvExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIDocxExporter) {
			jrExporter = docx((JasperIDocxExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIExcelApiXlsExporter) {
			jrExporter = excelApiXls((JasperIExcelApiXlsExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIXlsExporter) {
			jrExporter = xls((JasperIXlsExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIXlsxExporter) {
			jrExporter = xlsx((JasperIXlsxExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIHtmlExporter) {
			jrExporter = html((JasperIHtmlExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIOdsExporter) {
			jrExporter = ods((JasperIOdsExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIOdtExporter) {
			jrExporter = odt((JasperIOdtExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIPdfExporter) {
			jrExporter = pdf((JasperIPdfExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIRtfExporter) {
			jrExporter = rtf((JasperIRtfExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperITextExporter) {
			jrExporter = text((JasperITextExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIXhtmlExporter) {
			jrExporter = xhtml((JasperIXhtmlExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIXmlExporter) {
			jrExporter = xml((JasperIXmlExporter) jasperExporter);
		}
		else if (jasperExporter instanceof JasperIImageExporter) {
			jrExporter = image((JasperIImageExporter) jasperExporter);
		}
		else {
			throw new JasperDesignException("Exporter " + jasperExporter.getClass().getName() + " not supported");
		}

		return jrExporter;
	}

	private JRExporter exporter(JRExporter jrExporter, JasperIExporter jasperExporter) {
		if (jasperExporter.getOutputWriter() != null) {
			jrExporter.setParameter(JRExporterParameter.OUTPUT_WRITER, jasperExporter.getOutputWriter());
		}
		if (jasperExporter.getOutputStream() != null) {
			jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, jasperExporter.getOutputStream());
		}
		if (jasperExporter.getOutputFile() != null) {
			jrExporter.setParameter(JRExporterParameter.OUTPUT_FILE, jasperExporter.getOutputFile());
		}
		if (jasperExporter.getOutputFileName() != null) {
			jrExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, jasperExporter.getOutputFileName());
		}
		if (jasperExporter.getPageIndex() != null) {
			jrExporter.setParameter(JRExporterParameter.PAGE_INDEX, jasperExporter.getPageIndex());
		}
		if (jasperExporter.getStartPageIndex() != null) {
			jrExporter.setParameter(JRExporterParameter.START_PAGE_INDEX, jasperExporter.getStartPageIndex());
		}
		if (jasperExporter.getEndPageIndex() != null) {
			jrExporter.setParameter(JRExporterParameter.END_PAGE_INDEX, jasperExporter.getEndPageIndex());
		}
		if (jasperExporter.getCharacterEncoding() != null) {
			jrExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, jasperExporter.getCharacterEncoding());
		}
		if (jasperExporter.getOffsetX() != null) {
			jrExporter.setParameter(JRExporterParameter.OFFSET_X, jasperExporter.getOffsetX());
		}
		if (jasperExporter.getOffsetY() != null) {
			jrExporter.setParameter(JRExporterParameter.OFFSET_Y, jasperExporter.getOffsetY());
		}
		if (jasperExporter.getIgnorePageMargins() != null) {
			jrExporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, jasperExporter.getIgnorePageMargins());
		}
		return null;
	}

	private JRExporter xml(JasperIXmlExporter jasperExporter) {
		JRExporter jrExporter = new JRXmlExporter();
		exporter(jrExporter, jasperExporter);
		if (jasperExporter.getEmbeddingImages() != null) {
			jrExporter.setParameter(JRXmlExporterParameter.IS_EMBEDDING_IMAGES, jasperExporter.getEmbeddingImages());
		}
		return jrExporter;
	}

	private JRExporter xhtml(JasperIXhtmlExporter jasperExporter) {
		JRExporter jrExporter = new JRXhtmlExporter();
		exporter(jrExporter, jasperExporter);
		return jrExporter;
	}

	private JRExporter text(JasperITextExporter jasperExporter) {
		JRExporter jrExporter = new JRTextExporter();
		exporter(jrExporter, jasperExporter);
		if (jasperExporter.getCharacterWidth() != null) {
			jrExporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, jasperExporter.getCharacterWidth());
		}
		else {
			DRFont font = Defaults.getDefaults().getFont();
			jrExporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Float(StyleResolver.getFontWidth(font)));
		}
		if (jasperExporter.getCharacterHeight() != null) {
			jrExporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, jasperExporter.getCharacterHeight());
		}
		else {
			DRFont font = Defaults.getDefaults().getFont();
			jrExporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Float(StyleResolver.getFontHeight(font)));
		}
		if (jasperExporter.getPageWidth() != null) {
			jrExporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, jasperExporter.getPageWidth());
		}
		if (jasperExporter.getPageHeight() != null) {
			jrExporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, jasperExporter.getPageHeight());
		}
		if (jasperExporter.getBetweenPagesText() != null) {
			jrExporter.setParameter(JRTextExporterParameter.BETWEEN_PAGES_TEXT, jasperExporter.getBetweenPagesText());
		}
		if (jasperExporter.getLineSeparator() != null) {
			jrExporter.setParameter(JRTextExporterParameter.LINE_SEPARATOR, jasperExporter.getLineSeparator());
		}
		return jrExporter;
	}

	private JRExporter rtf(JasperIRtfExporter jasperExporter) {
		JRExporter jrExporter = new JRRtfExporter();
		exporter(jrExporter, jasperExporter);
		return jrExporter;
	}

	private JRExporter pdf(JasperIPdfExporter jasperExporter) {
		JRExporter jrExporter = new JRPdfExporter();
		exporter(jrExporter, jasperExporter);
		if (jasperExporter.getCreatingBatchModeBookmarks() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, jasperExporter.getCreatingBatchModeBookmarks());
		}
		if (jasperExporter.getCompressed() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.IS_COMPRESSED, jasperExporter.getCompressed());
		}
		if (jasperExporter.getEncrypted() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, jasperExporter.getEncrypted());
		}
		if (jasperExporter.getBitKey128() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY, jasperExporter.getBitKey128());
		}
		if (jasperExporter.getUserPassword() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, jasperExporter.getUserPassword());
		}
		if (jasperExporter.getOwnerPassword() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, jasperExporter.getOwnerPassword());
		}
		if (jasperExporter.getPermissions() != null && !jasperExporter.getPermissions().isEmpty()) {
			jrExporter.setParameter(JRPdfExporterParameter.PERMISSIONS, ConstantTransform.pdfPermission(jasperExporter.getPermissions()));
		}
		if (jasperExporter.getPdfVersion() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.PDF_VERSION, ConstantTransform.pdfVersion(jasperExporter.getPdfVersion()));
		}
		if (jasperExporter.getMetadataTitle() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.METADATA_TITLE, jasperExporter.getMetadataTitle());
		}
		if (jasperExporter.getMetadataAuthor() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, jasperExporter.getMetadataAuthor());
		}
		if (jasperExporter.getMetadataSubject() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.METADATA_SUBJECT, jasperExporter.getMetadataSubject());
		}
		if (jasperExporter.getMetadataKeyWords() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.METADATA_KEYWORDS, jasperExporter.getMetadataKeyWords());
		}
		if (jasperExporter.getMetadataCreator() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.METADATA_CREATOR, jasperExporter.getMetadataCreator());
		}
		if (jasperExporter.getForcelineBreakPolicy() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.FORCE_LINEBREAK_POLICY, jasperExporter.getForcelineBreakPolicy());
		}
		if (jasperExporter.getForceSvgShapes() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.FORCE_SVG_SHAPES, jasperExporter.getForceSvgShapes());
		}
		if (jasperExporter.getPdfJavaScript() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, jasperExporter.getPdfJavaScript());
		}
		if (jasperExporter.getTagged() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.IS_TAGGED, jasperExporter.getTagged());
		}
		if (jasperExporter.getTagLanguage() != null) {
			jrExporter.setParameter(JRPdfExporterParameter.TAG_LANGUAGE, jasperExporter.getTagLanguage());
		}
		return jrExporter;
	}

	private JRExporter odt(JasperIOdtExporter jasperExporter) {
		JRExporter jrExporter = new JROdtExporter();
		exporter(jrExporter, jasperExporter);
		return jrExporter;
	}

	private JRExporter ods(JasperIOdsExporter jasperExporter) {
		JRExporter jrExporter = new JROdsExporter();
		exporter(jrExporter, jasperExporter);
		return jrExporter;
	}

	private JRExporter html(JasperIHtmlExporter jasperExporter) {
		JRExporter jrExporter = new JRHtmlExporter();
		exporter(jrExporter, jasperExporter);
		if (jasperExporter.getOutputImagesToDir() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, jasperExporter.getOutputImagesToDir());
		}
		if (jasperExporter.getImagesDirName() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, jasperExporter.getImagesDirName());
		}
		if (jasperExporter.getImagesURI() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, jasperExporter.getImagesURI());
		}
		if (jasperExporter.getHtmlHeader() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, jasperExporter.getHtmlHeader());
		}
		if (jasperExporter.getBetweenPagesHtml() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, jasperExporter.getBetweenPagesHtml());
		}
		if (jasperExporter.getHtmlFooter() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, jasperExporter.getHtmlFooter());
		}
		if (jasperExporter.getRemoveEmptySpaceBetweenRows() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, jasperExporter.getRemoveEmptySpaceBetweenRows());
		}
		if (jasperExporter.getWhitePageBackground() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND, jasperExporter.getWhitePageBackground());
		}
		if (jasperExporter.getUsingImagesToAlign() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, jasperExporter.getUsingImagesToAlign());
		}
		if (jasperExporter.getWrapBreakWord() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.IS_WRAP_BREAK_WORD, jasperExporter.getWrapBreakWord());
		}
		if (jasperExporter.getSizeUnit() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT, ConstantTransform.sizeUnit(jasperExporter.getSizeUnit()));
		}
		if (jasperExporter.getFramesAsNestedTables() != null) {
			jrExporter.setParameter(JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES, jasperExporter.getFramesAsNestedTables());
		}
		return jrExporter;
	}

	private JRExporter excel(JRXlsAbstractExporter jrExporter, JasperIExcelExporter jasperExporter) {
		exporter(jrExporter, jasperExporter);
		if (jasperExporter.getOnePagePerSheet() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, jasperExporter.getOnePagePerSheet());
		}
		if (jasperExporter.getRemoveEmptySpaceBetweenRows() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, jasperExporter.getRemoveEmptySpaceBetweenRows());
		}
		if (jasperExporter.getRemoveEmptySpaceBetweenColumns() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, jasperExporter.getRemoveEmptySpaceBetweenColumns());
		}
		if (jasperExporter.getWhitePageBackground() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, jasperExporter.getWhitePageBackground());
		}
		if (jasperExporter.getDetectCellType() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE, jasperExporter.getDetectCellType());
		}
		if (jasperExporter.getSheetNames() != null && !jasperExporter.getSheetNames().isEmpty()) {
			String[] sheetNames = jasperExporter.getSheetNames().toArray(new String[jasperExporter.getSheetNames().size()]);
			jrExporter.setParameter(JRXlsAbstractExporterParameter.SHEET_NAMES, sheetNames);
		}
		if (jasperExporter.getFontSizeFixEnabled() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_FONT_SIZE_FIX_ENABLED, jasperExporter.getFontSizeFixEnabled());
		}
		if (jasperExporter.getImageBorderFixEnabled() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED, jasperExporter.getImageBorderFixEnabled());
		}
		if (jasperExporter.getMaxRowsPerSheet() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.MAXIMUM_ROWS_PER_SHEET, jasperExporter.getMaxRowsPerSheet());
		}
		if (jasperExporter.getIgnoreGraphics() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS, jasperExporter.getIgnoreGraphics());
		}
		if (jasperExporter.getCollapseRowSpan() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_COLLAPSE_ROW_SPAN, jasperExporter.getCollapseRowSpan());
		}
		if (jasperExporter.getIgnoreCellBorder() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER, jasperExporter.getIgnoreCellBorder());
		}
		if (jasperExporter.getIgnoreCellBackground() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BACKGROUND, jasperExporter.getIgnoreCellBackground());
		}
		if (jasperExporter.getPassword() != null) {
			jrExporter.setParameter(JRXlsAbstractExporterParameter.PASSWORD, jasperExporter.getPassword());
		}
		return jrExporter;
	}

	private JRExporter xlsx(JasperIXlsxExporter jasperExporter) {
		JRXlsAbstractExporter jrExporter = new JRXlsxExporter();
		excel(jrExporter, jasperExporter);
		return jrExporter;
	}

	private JRExporter xls(JasperIXlsExporter jasperExporter) {
		JRXlsAbstractExporter jrExporter = new JRXlsExporter();
		excel(jrExporter, jasperExporter);
		return jrExporter;
	}

	private JRExporter excelApiXls(JasperIExcelApiXlsExporter jasperExporter) {
		JRXlsAbstractExporter jrExporter = new JExcelApiExporter();
		excel(jrExporter, jasperExporter);
		return jrExporter;
	}

	private JRExporter docx(JasperIDocxExporter jasperExporter) {
		JRExporter jrExporter = new JRDocxExporter();
		exporter(jrExporter, jasperExporter);
		if (jasperExporter.getFramesAsNestedTables() != null) {
			jrExporter.setParameter(JRDocxExporterParameter.FRAMES_AS_NESTED_TABLES, jasperExporter.getFramesAsNestedTables());
		}
		if (jasperExporter.getFlexibleRowHeight() != null) {
			jrExporter.setParameter(JRDocxExporterParameter.FLEXIBLE_ROW_HEIGHT, jasperExporter.getFlexibleRowHeight());
		}
		return jrExporter;
	}

	private JRExporter csv(JasperICsvExporter jasperExporter) {
		JRExporter jrExporter = new JRCsvExporter();
		exporter(jrExporter, jasperExporter);
		if (jasperExporter.getFieldDelimiter() != null) {
			jrExporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, jasperExporter.getFieldDelimiter());
		}
		if (jasperExporter.getRecordDelimiter() != null) {
			jrExporter.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, jasperExporter.getRecordDelimiter());
		}
		return jrExporter;
	}

	public JRExporter image(JasperIImageExporter jasperExporter) throws DRException {
		try {
			JRGraphics2DExporter jrExporter = new JRGraphics2DExporter();
			if (jasperExporter.getCharacterEncoding() != null) {
				jrExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, jasperExporter.getCharacterEncoding());
			}
			if (jasperExporter.getOffsetY() != null) {
				jrExporter.setParameter(JRExporterParameter.OFFSET_Y, jasperExporter.getOffsetY());
			}
			if (jasperExporter.getIgnorePageMargins() != null) {
				jrExporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, jasperExporter.getIgnorePageMargins());
			}
			if (jasperExporter.getZoom() != null) {
				jrExporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, jasperExporter.getZoom());
			}
			return jrExporter;
		} catch (JRException e) {
			throw new DRException(e);
		}
	}
}
