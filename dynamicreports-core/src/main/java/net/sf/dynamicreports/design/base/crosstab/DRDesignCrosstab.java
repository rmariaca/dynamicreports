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

package net.sf.dynamicreports.design.base.crosstab;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstab;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabMeasure;
import net.sf.dynamicreports.report.constant.RunDirection;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignCrosstab extends DRDesignComponent implements DRIDesignCrosstab {
	private DRDesignCrosstabDataset dataset;
	private Boolean repeatColumnHeaders;
	private Boolean repeatRowHeaders;
	private Integer columnBreakOffset;
	private Boolean ignoreWidth;
	private RunDirection runDirection;
	private DRDesignCrosstabCellContent whenNoDataCell;
	private DRDesignCrosstabCellContent headerCell;
	private List<DRDesignCrosstabColumnGroup> columnGroups;
	private List<DRDesignCrosstabRowGroup> rowGroups;
	private List<DRDesignCrosstabCell> cells;
	private List<DRIDesignCrosstabMeasure> measures;

	public DRDesignCrosstab() {
		super("crosstab");
	}

	@Override
	protected void init() {
		super.init();
		columnGroups = new ArrayList<DRDesignCrosstabColumnGroup>();
		rowGroups = new ArrayList<DRDesignCrosstabRowGroup>();
		cells = new ArrayList<DRDesignCrosstabCell>();
		measures = new ArrayList<DRIDesignCrosstabMeasure>();
	}

	public DRDesignCrosstabDataset getDataset() {
		return dataset;
	}

	public void setDataset(DRDesignCrosstabDataset dataset) {
		this.dataset = dataset;
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

	public DRDesignCrosstabCellContent getWhenNoDataCell() {
		return whenNoDataCell;
	}

	public void setWhenNoDataCell(DRDesignCrosstabCellContent whenNoDataCell) {
		this.whenNoDataCell = whenNoDataCell;
	}

	public DRDesignCrosstabCellContent getHeaderCell() {
		return headerCell;
	}

	public void setHeaderCell(DRDesignCrosstabCellContent headerCell) {
		this.headerCell = headerCell;
	}

	public List<DRDesignCrosstabColumnGroup> getColumnGroups() {
		return columnGroups;
	}

	public void setColumnGroups(List<DRDesignCrosstabColumnGroup> columnGroups) {
		this.columnGroups = columnGroups;
	}

	public List<DRDesignCrosstabRowGroup> getRowGroups() {
		return rowGroups;
	}

	public void setRowGroups(List<DRDesignCrosstabRowGroup> rowGroups) {
		this.rowGroups = rowGroups;
	}

	public List<DRDesignCrosstabCell> getCells() {
		return cells;
	}

	public void setCells(List<DRDesignCrosstabCell> cells) {
		this.cells = cells;
	}

	public List<DRIDesignCrosstabMeasure> getMeasures() {
		return measures;
	}

	public void setMeasures(List<DRIDesignCrosstabMeasure> measures) {
		this.measures = measures;
	}
}