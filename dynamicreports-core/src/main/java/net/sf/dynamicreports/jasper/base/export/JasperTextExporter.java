/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca
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

import net.sf.dynamicreports.jasper.definition.export.JasperITextExporter;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class JasperTextExporter extends AbstractJasperExporter implements JasperITextExporter {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Integer characterWidth;
	private Integer characterHeight;
	private Integer pageWidth;
	private Integer pageHeight;
	private String betweenPagesText;
	private String lineSeparator;
	
	@Override
	public Integer getCharacterWidth() {
		return characterWidth;
	}
	
	public void setCharacterWidth(Integer characterWidth) {
		this.characterWidth = characterWidth;
	}
	
	@Override
	public Integer getCharacterHeight() {
		return characterHeight;
	}
	
	public void setCharacterHeight(Integer characterHeight) {
		this.characterHeight = characterHeight;
	}
	
	@Override
	public Integer getPageWidth() {
		return pageWidth;
	}
	
	public void setPageWidth(Integer pageWidth) {
		this.pageWidth = pageWidth;
	}
	
	@Override
	public Integer getPageHeight() {
		return pageHeight;
	}
	
	public void setPageHeight(Integer pageHeight) {
		this.pageHeight = pageHeight;
	}
	
	@Override
	public String getBetweenPagesText() {
		return betweenPagesText;
	}
	
	public void setBetweenPagesText(String betweenPagesText) {
		this.betweenPagesText = betweenPagesText;
	}
	
	@Override
	public String getLineSeparator() {
		return lineSeparator;
	}
	
	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}	
}
