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

package net.sf.dynamicreports.jasper.builder.export;

import net.sf.dynamicreports.jasper.base.export.AbstractJasperExcelExporter;
import net.sf.dynamicreports.report.constant.Constants;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
@SuppressWarnings("unchecked")
public abstract class AbstractJasperExcelExporterBuilder<T extends AbstractJasperExcelExporterBuilder<T, U>, U extends AbstractJasperExcelExporter> extends AbstractJasperExporterBuilder<T, U> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected AbstractJasperExcelExporterBuilder(U exporter) {
		super(exporter);
	}

	public T setOnePagePerSheet(Boolean onePagePerSheet) {
		this.getObject().setOnePagePerSheet(onePagePerSheet);
		return (T) this;
	}

	public T setRemoveEmptySpaceBetweenRows(Boolean removeEmptySpaceBetweenRows) {
		this.getObject().setRemoveEmptySpaceBetweenRows(removeEmptySpaceBetweenRows);
		return (T) this;
	}

	public T setRemoveEmptySpaceBetweenColumns(Boolean removeEmptySpaceBetweenColumns) {
		this.getObject().setRemoveEmptySpaceBetweenColumns(removeEmptySpaceBetweenColumns);
		return (T) this;
	}

	public T setWhitePageBackground(Boolean whitePageBackground) {
		this.getObject().setWhitePageBackground(whitePageBackground);
		return (T) this;
	}

	public T setDetectCellType(Boolean detectCellType) {
		this.getObject().setDetectCellType(detectCellType);
		return (T) this;
	}

	public T sheetNames(String ...sheetNames) {
		return addSheetName(sheetNames);
	}

	public T addSheetName(String ...sheetNames) {
		Validate.notNull(sheetNames, "sheetNames must not be null");
		Validate.noNullElements(sheetNames, "sheetNames must not contains null sheetName");
		for (String sheetName : sheetNames) {
			this.getObject().addSheetName(sheetName);
		}
		return (T) this;
	}

	public T setFontSizeFixEnabled(Boolean fontSizeFixEnabled) {
		this.getObject().setFontSizeFixEnabled(fontSizeFixEnabled);
		return (T) this;
	}

	public T setImageBorderFixEnabled(Boolean imageBorderFixEnabled) {
		this.getObject().setImageBorderFixEnabled(imageBorderFixEnabled);
		return (T) this;
	}

	public T setMaxRowsPerSheet(Integer maxRowsPerSheet) {
		this.getObject().setMaxRowsPerSheet(maxRowsPerSheet);
		return (T) this;
	}

	public T setIgnoreGraphics(Boolean ignoreGraphics) {
		this.getObject().setIgnoreGraphics(ignoreGraphics);
		return (T) this;
	}

	public T setCollapseRowSpan(Boolean collapseRowSpan) {
		this.getObject().setCollapseRowSpan(collapseRowSpan);
		return (T) this;
	}

	public T setIgnoreCellBorder(Boolean ignoreCellBorder) {
		this.getObject().setIgnoreCellBorder(ignoreCellBorder);
		return (T) this;
	}

	public T setIgnoreCellBackground(Boolean ignoreCellBackground) {
		this.getObject().setIgnoreCellBackground(ignoreCellBackground);
		return (T) this;
	}

	public T setPassword(String password) {
		this.getObject().setPassword(password);
		return (T) this;
	}
}
