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

package net.sf.dynamicreports.jasper.base.export;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.jasper.definition.export.JasperIExcelExporter;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
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
	
	@Override
	public Boolean getOnePagePerSheet() {
		return onePagePerSheet;
	}
	
	public void setOnePagePerSheet(Boolean onePagePerSheet) {
		this.onePagePerSheet = onePagePerSheet;
	}
	
	@Override
	public Boolean getRemoveEmptySpaceBetweenRows() {
		return removeEmptySpaceBetweenRows;
	}
	
	public void setRemoveEmptySpaceBetweenRows(Boolean removeEmptySpaceBetweenRows) {
		this.removeEmptySpaceBetweenRows = removeEmptySpaceBetweenRows;
	}
	
	@Override
	public Boolean getRemoveEmptySpaceBetweenColumns() {
		return removeEmptySpaceBetweenColumns;
	}
	
	public void setRemoveEmptySpaceBetweenColumns(Boolean removeEmptySpaceBetweenColumns) {
		this.removeEmptySpaceBetweenColumns = removeEmptySpaceBetweenColumns;
	}
	
	@Override
	public Boolean getWhitePageBackground() {
		return whitePageBackground;
	}
	
	public void setWhitePageBackground(Boolean whitePageBackground) {
		this.whitePageBackground = whitePageBackground;
	}
	
	@Override
	public Boolean getDetectCellType() {
		return detectCellType;
	}
	
	public void setDetectCellType(Boolean detectCellType) {
		this.detectCellType = detectCellType;
	}
	
	@Override
	public List<String> getSheetNames() {
		return sheetNames;
	}
	
	public void setSheetNames(List<String> sheetNames) {
		this.sheetNames = sheetNames;
	}

	public void addSheetName(String sheetName) {
		this.sheetNames.add(sheetName);
	}
	
	@Override
	public Boolean getFontSizeFixEnabled() {
		return fontSizeFixEnabled;
	}
	
	public void setFontSizeFixEnabled(Boolean fontSizeFixEnabled) {
		this.fontSizeFixEnabled = fontSizeFixEnabled;
	}
	
	@Override
	public Boolean getImageBorderFixEnabled() {
		return imageBorderFixEnabled;
	}
	
	public void setImageBorderFixEnabled(Boolean imageBorderFixEnabled) {
		this.imageBorderFixEnabled = imageBorderFixEnabled;
	}
	
	@Override
	public Integer getMaxRowsPerSheet() {
		return maxRowsPerSheet;
	}
	
	public void setMaxRowsPerSheet(Integer maxRowsPerSheet) {
		this.maxRowsPerSheet = maxRowsPerSheet;
	}
	
	@Override
	public Boolean getIgnoreGraphics() {
		return ignoreGraphics;
	}
	
	public void setIgnoreGraphics(Boolean ignoreGraphics) {
		this.ignoreGraphics = ignoreGraphics;
	}
	
	@Override
	public Boolean getCollapseRowSpan() {
		return collapseRowSpan;
	}
	
	public void setCollapseRowSpan(Boolean collapseRowSpan) {
		this.collapseRowSpan = collapseRowSpan;
	}
	
	@Override
	public Boolean getIgnoreCellBorder() {
		return ignoreCellBorder;
	}
	
	public void setIgnoreCellBorder(Boolean ignoreCellBorder) {
		this.ignoreCellBorder = ignoreCellBorder;
	}
	
	@Override
	public Boolean getIgnoreCellBackground() {
		return ignoreCellBackground;
	}
	
	public void setIgnoreCellBackground(Boolean ignoreCellBackground) {
		this.ignoreCellBackground = ignoreCellBackground;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}	
}
