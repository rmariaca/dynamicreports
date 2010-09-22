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

package net.sf.dynamicreports.jasper.base.export;

import net.sf.dynamicreports.jasper.constant.SizeUnit;
import net.sf.dynamicreports.jasper.definition.export.JasperIHtmlExporter;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperHtmlExporter extends AbstractJasperExporter implements JasperIHtmlExporter {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Boolean outputImagesToDir;
	private String imagesDirName;
	private String imagesURI;
	private String htmlHeader;
	private String betweenPagesHtml;
	private String htmlFooter;
	private Boolean removeEmptySpaceBetweenRows;
	private Boolean whitePageBackground;
	private Boolean usingImagesToAlign;
	private Boolean wrapBreakWord;
	private SizeUnit sizeUnit;
	private Boolean framesAsNestedTables;
	
	public Boolean getOutputImagesToDir() {
		return outputImagesToDir;
	}
	
	public void setOutputImagesToDir(Boolean outputImagesToDir) {
		this.outputImagesToDir = outputImagesToDir;
	}
	
	public String getImagesDirName() {
		return imagesDirName;
	}
	
	public void setImagesDirName(String imagesDirName) {
		this.imagesDirName = imagesDirName;
	}
	
	public void setImagesURI(String imagesURI) {
		this.imagesURI = imagesURI;
	}
	
	public String getImagesURI() {
		return imagesURI;
	}	
	
	public String getHtmlHeader() {
		return htmlHeader;
	}
	
	public void setHtmlHeader(String htmlHeader) {
		this.htmlHeader = htmlHeader;
	}
	
	public String getBetweenPagesHtml() {
		return betweenPagesHtml;
	}
	
	public void setBetweenPagesHtml(String betweenPagesHtml) {
		this.betweenPagesHtml = betweenPagesHtml;
	}
	
	public String getHtmlFooter() {
		return htmlFooter;
	}
	
	public void setHtmlFooter(String htmlFooter) {
		this.htmlFooter = htmlFooter;
	}
	
	public Boolean getRemoveEmptySpaceBetweenRows() {
		return removeEmptySpaceBetweenRows;
	}
	
	public void setRemoveEmptySpaceBetweenRows(Boolean removeEmptySpaceBetweenRows) {
		this.removeEmptySpaceBetweenRows = removeEmptySpaceBetweenRows;
	}
	
	public Boolean getWhitePageBackground() {
		return whitePageBackground;
	}
	
	public void setWhitePageBackground(Boolean whitePageBackground) {
		this.whitePageBackground = whitePageBackground;
	}
	
	public Boolean getUsingImagesToAlign() {
		return usingImagesToAlign;
	}
	
	public void setUsingImagesToAlign(Boolean usingImagesToAlign) {
		this.usingImagesToAlign = usingImagesToAlign;
	}
	
	public Boolean getWrapBreakWord() {
		return wrapBreakWord;
	}
	
	public void setWrapBreakWord(Boolean wrapBreakWord) {
		this.wrapBreakWord = wrapBreakWord;
	}
	
	public SizeUnit getSizeUnit() {
		return sizeUnit;
	}
	
	public void setSizeUnit(SizeUnit sizeUnit) {
		this.sizeUnit = sizeUnit;
	}
	
	public Boolean getFramesAsNestedTables() {
		return framesAsNestedTables;
	}
	
	public void setFramesAsNestedTables(Boolean framesAsNestedTables) {
		this.framesAsNestedTables = framesAsNestedTables;
	}	
}
