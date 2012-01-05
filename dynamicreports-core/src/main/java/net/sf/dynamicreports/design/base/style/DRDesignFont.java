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

package net.sf.dynamicreports.design.base.style;

import net.sf.dynamicreports.design.definition.style.DRIDesignFont;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignFont implements DRIDesignFont {	
	private String fontName;
	private Integer fontSize;
	private Boolean bold;
	private Boolean italic;
	private Boolean underline;
	private Boolean strikeThrough;
	private String pdfFontName;
	private String pdfEncoding;
	private Boolean pdfEmbedded;
	
	public String getFontName() {
		return fontName;
	}
	
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	
	public Boolean getBold() {
		return bold;
	}
	
	public void setBold(Boolean bold) {
		this.bold = bold;
	}
	
	public Boolean getItalic() {
		return italic;
	}
	
	public void setItalic(Boolean italic) {
		this.italic = italic;
	}
	
	public Boolean getUnderline() {
		return underline;
	}
	
	public void setUnderline(Boolean underline) {
		this.underline = underline;
	}
	
	public Boolean getStrikeThrough() {
		return strikeThrough;
	}
	
	public void setStrikeThrough(Boolean strikeThrough) {
		this.strikeThrough = strikeThrough;
	}
	
	public Integer getFontSize() {
		return fontSize;
	}
	
	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}
	
	public String getPdfFontName() {
		return pdfFontName;
	}
	
	public void setPdfFontName(String pdfFontName) {
		this.pdfFontName = pdfFontName;
	}
	
	public String getPdfEncoding() {
		return pdfEncoding;
	}
	
	public void setPdfEncoding(String pdfEncoding) {
		this.pdfEncoding = pdfEncoding;
	}
	
	public Boolean getPdfEmbedded() {
		return pdfEmbedded;
	}
	
	public void setPdfEmbedded(Boolean pdfEmbedded) {
		this.pdfEmbedded = pdfEmbedded;
	}	
}
