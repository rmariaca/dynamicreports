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

package net.sf.dynamicreports.report.base.crosstab;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.base.component.DRDimensionComponent;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.RunDirection;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstab;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRCrosstab extends DRDimensionComponent implements DRICrosstab {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Boolean repeatColumnHeaders;
	private Boolean repeatRowHeaders;
	private Integer columnBreakOffset;
	private Boolean ignoreWidth;
	private RunDirection runDirection;
	private Integer cellWidth;
	private Integer cellHeight;
	private DRCrosstabCellContent whenNoDataCell;
	private DRCrosstabCellContent headerCell;
	private List<DRICrosstabColumnGroup<?>> columnGroups;
	private List<DRICrosstabRowGroup<?>> rowGroups;
	private List<DRICrosstabMeasure<?>> measures;

	@Override
	protected void init() {
		super.init();
		columnGroups = new ArrayList<DRICrosstabColumnGroup<?>>();
		rowGroups = new ArrayList<DRICrosstabRowGroup<?>>();
		measures = new ArrayList<DRICrosstabMeasure<?>>();

		whenNoDataCell = new DRCrosstabCellContent();
		headerCell = new DRCrosstabCellContent();
	}

	public Boolean isRepeatColumnHeaders() {
		return repeatColumnHeaders;
	}

	public void setRepeatColumnHeaders(Boolean repeatColumnHeaders) {
		this.repeatColumnHeaders = repeatColumnHeaders;
	}

	public Boolean isRepeatRowHeaders() {
		return repeatRowHeaders;
	}

	public void setRepeatRowHeaders(Boolean repeatRowHeaders) {
		this.repeatRowHeaders = repeatRowHeaders;
	}

	public Integer getColumnBreakOffset() {
		return columnBreakOffset;
	}

	public void setColumnBreakOffset(Integer columnBreakOffset) {
		this.columnBreakOffset = columnBreakOffset;
	}

	public Boolean getIgnoreWidth() {
		return ignoreWidth;
	}

	public void setIgnoreWidth(Boolean ignoreWidth) {
		this.ignoreWidth = ignoreWidth;
	}

	public RunDirection getRunDirection() {
		return runDirection;
	}

	public void setRunDirection(RunDirection runDirection) {
		this.runDirection = runDirection;
	}

	public DRCrosstabCellContent getWhenNoDataCell() {
		return whenNoDataCell;
	}

	public DRCrosstabCellContent getHeaderCell() {
		return headerCell;
	}

	public List<DRICrosstabColumnGroup<?>> getColumnGroups() {
		return columnGroups;
	}

	public Integer getCellWidth() {
		return cellWidth;
	}

	public void setCellWidth(Integer cellWidth) {
		this.cellWidth = cellWidth;
	}

	public Integer getCellHeight() {
		return cellHeight;
	}

	public void setCellHeight(Integer cellHeight) {
		this.cellHeight = cellHeight;
	}

	public void addColumnGroup(DRICrosstabColumnGroup<?> columnGroup) {
		Validate.notNull(columnGroup, "columnGroup must not be null");
		this.columnGroups.add(columnGroup);
	}

	public List<DRICrosstabRowGroup<?>> getRowGroups() {
		return rowGroups;
	}

	public void addRowGroup(DRICrosstabRowGroup<?> rowGroup) {
		Validate.notNull(rowGroup, "rowGroup must not be null");
		this.rowGroups.add(rowGroup);
	}

	public List<DRICrosstabMeasure<?>> getMeasures() {
		return measures;
	}

	public void addMeasure(DRICrosstabMeasure<?> measure) {
		Validate.notNull(measure, "measure must not be null");
		this.measures.add(measure);
	}
}