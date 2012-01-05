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

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;

import net.sf.dynamicreports.jasper.definition.export.JasperIExporter;
import net.sf.dynamicreports.report.constant.Constants;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class AbstractJasperExporter implements JasperIExporter {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Writer outputWriter;
	private OutputStream outputStream;
	private File outputFile;
	private String outputFileName;
	
	private Integer pageIndex;
	private Integer startPageIndex;
	private Integer endPageIndex;
	private String characterEncoding;
	private Integer offsetX;
	private Integer offsetY;
	private Boolean ignorePageMargins;
		
	public Writer getOutputWriter() {
		return outputWriter;
	}

	public void setOutputWriter(Writer outputWriter) {
		Validate.notNull(outputWriter, "outputWriter must not be null");
		this.outputWriter = outputWriter;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		Validate.notNull(outputStream, "outputStream must not be null");
		this.outputStream = outputStream;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		Validate.notNull(outputFile, "outputFile must not be null");
		this.outputFile = outputFile;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		Validate.notNull(outputFileName, "outputFileName must not be null");
		this.outputFileName = outputFileName;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}
	
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public Integer getStartPageIndex() {
		return startPageIndex;
	}
	
	public void setStartPageIndex(Integer startPageIndex) {
		this.startPageIndex = startPageIndex;
	}
	
	public Integer getEndPageIndex() {
		return endPageIndex;
	}
	
	public void setEndPageIndex(Integer endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
	public String getCharacterEncoding() {
		return characterEncoding;
	}
	
	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}
	
	public Integer getOffsetX() {
		return offsetX;
	}
	
	public void setOffsetX(Integer offsetX) {
		this.offsetX = offsetX;
	}
	
	public Integer getOffsetY() {
		return offsetY;
	}
	
	public void setOffsetY(Integer offsetY) {
		this.offsetY = offsetY;
	}
	
	public Boolean getIgnorePageMargins() {
		return ignorePageMargins;
	}
	
	public void setIgnorePageMargins(Boolean ignorePageMargins) {
		this.ignorePageMargins = ignorePageMargins;
	}	
}
