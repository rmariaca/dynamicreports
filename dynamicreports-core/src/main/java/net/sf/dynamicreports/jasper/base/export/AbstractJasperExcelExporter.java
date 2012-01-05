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

package net.sf.dynamicreports.jasper.base.export;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.jasper.definition.export.JasperIExcelExporter;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class AbstractJasperExcelExporter extends AbstractJasperExporter implements JasperIExcelExporter {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Boolean onePagePerSheet;
	private Boolean removeEmptySpaceBetweenRows;
	private Boolean removeEmptySpaceBetweenColumns;
	private Boolean whitePageBackground;
	private Boolean detectCellType;
	private List<String> sheetNames;
	private Boolean fontSizeFixEnabled;
	private Boolean imageBorderFixEnabled;
	private Integer maxRowsPerSheet;
	private Boolean ignoreGraphics;
	private Boolean collapseRowSpan;
	private Boolean ignoreCellBorder;
	private Boolean ignoreCellBackground;
	private String password;
	
	public AbstractJasperExcelExporter() {
		this.sheetNames = new ArrayList<String>();
	}
	
	public Boolean getOnePagePerSheet() {
		return onePagePerSheet;
	}
	
	public void setOnePagePerSheet(Boolean onePagePerSheet) {
		this.onePagePerSheet = onePagePerSheet;
	}
	
	public Boolean getRemoveEmptySpaceBetweenRows() {
		return removeEmptySpaceBetweenRows;
	}
	
	public void setRemoveEmptySpaceBetweenRows(Boolean removeEmptySpaceBetweenRows) {
		this.removeEmptySpaceBetweenRows = removeEmptySpaceBetweenRows;
	}
	
	public Boolean getRemoveEmptySpaceBetweenColumns() {
		return removeEmptySpaceBetweenColumns;
	}
	
	public void setRemoveEmptySpaceBetweenColumns(Boolean removeEmptySpaceBetweenColumns) {
		this.removeEmptySpaceBetweenColumns = removeEmptySpaceBetweenColumns;
	}
	
	public Boolean getWhitePageBackground() {
		return whitePageBackground;
	}
	
	public void setWhitePageBackground(Boolean whitePageBackground) {
		this.whitePageBackground = whitePageBackground;
	}
	
	public Boolean getDetectCellType() {
		return detectCellType;
	}
	
	public void setDetectCellType(Boolean detectCellType) {
		this.detectCellType = detectCellType;
	}
	
	public List<String> getSheetNames() {
		return sheetNames;
	}
	
	public void setSheetNames(List<String> sheetNames) {
		this.sheetNames = sheetNames;
	}

	public void addSheetName(String sheetName) {
		this.sheetNames.add(sheetName);
	}
	
	public Boolean getFontSizeFixEnabled() {
		return fontSizeFixEnabled;
	}
	
	public void setFontSizeFixEnabled(Boolean fontSizeFixEnabled) {
		this.fontSizeFixEnabled = fontSizeFixEnabled;
	}
	
	public Boolean getImageBorderFixEnabled() {
		return imageBorderFixEnabled;
	}
	
	public void setImageBorderFixEnabled(Boolean imageBorderFixEnabled) {
		this.imageBorderFixEnabled = imageBorderFixEnabled;
	}
	
	public Integer getMaxRowsPerSheet() {
		return maxRowsPerSheet;
	}
	
	public void setMaxRowsPerSheet(Integer maxRowsPerSheet) {
		this.maxRowsPerSheet = maxRowsPerSheet;
	}
	
	public Boolean getIgnoreGraphics() {
		return ignoreGraphics;
	}
	
	public void setIgnoreGraphics(Boolean ignoreGraphics) {
		this.ignoreGraphics = ignoreGraphics;
	}
	
	public Boolean getCollapseRowSpan() {
		return collapseRowSpan;
	}
	
	public void setCollapseRowSpan(Boolean collapseRowSpan) {
		this.collapseRowSpan = collapseRowSpan;
	}
	
	public Boolean getIgnoreCellBorder() {
		return ignoreCellBorder;
	}
	
	public void setIgnoreCellBorder(Boolean ignoreCellBorder) {
		this.ignoreCellBorder = ignoreCellBorder;
	}
	
	public Boolean getIgnoreCellBackground() {
		return ignoreCellBackground;
	}
	
	public void setIgnoreCellBackground(Boolean ignoreCellBackground) {
		this.ignoreCellBackground = ignoreCellBackground;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}	
}
