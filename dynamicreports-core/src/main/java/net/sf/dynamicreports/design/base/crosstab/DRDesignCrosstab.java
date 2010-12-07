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

package net.sf.dynamicreports.design.base.crosstab;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstab;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCell;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabColumnGroup;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabMeasure;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabRowGroup;
import net.sf.dynamicreports.report.constant.RunDirection;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignCrosstab extends DRDesignComponent implements DRIDesignCrosstab {
	private boolean repeatColumnHeaders;	
	private boolean repeatRowHeaders;	
	private int columnBreakOffset;	
	private Boolean ignoreWidth;	
	private RunDirection runDirection;	
	private DRDesignCrosstabCellContent whenNoDataCell;	
	private DRDesignCrosstabCellContent headerCell;	
	private List<DRIDesignCrosstabColumnGroup> columnGroups;	
	private List<DRIDesignCrosstabRowGroup> rowGroups;	
	private List<DRIDesignCrosstabCell> cells;	
	private List<DRIDesignCrosstabMeasure> measures;
	
	public DRDesignCrosstab() {
		super("crosstab");
	}
	
	@Override
	protected void init() {
		super.init();
		columnGroups = new ArrayList<DRIDesignCrosstabColumnGroup>();
		rowGroups = new ArrayList<DRIDesignCrosstabRowGroup>();
		cells = new ArrayList<DRIDesignCrosstabCell>();
		measures = new ArrayList<DRIDesignCrosstabMeasure>();
	}
	
	public boolean isRepeatColumnHeaders() {
		return repeatColumnHeaders;
	}
	
	public void setRepeatColumnHeaders(boolean repeatColumnHeaders) {
		this.repeatColumnHeaders = repeatColumnHeaders;
	}
	
	public boolean isRepeatRowHeaders() {
		return repeatRowHeaders;
	}
	
	public void setRepeatRowHeaders(boolean repeatRowHeaders) {
		this.repeatRowHeaders = repeatRowHeaders;
	}
	
	public int getColumnBreakOffset() {
		return columnBreakOffset;
	}
	
	public void setColumnBreakOffset(int columnBreakOffset) {
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
	
	public List<DRIDesignCrosstabColumnGroup> getColumnGroups() {
		return columnGroups;
	}
	
	public void setColumnGroups(List<DRIDesignCrosstabColumnGroup> columnGroups) {
		this.columnGroups = columnGroups;
	}
	
	public List<DRIDesignCrosstabRowGroup> getRowGroups() {
		return rowGroups;
	}
	
	public void setRowGroups(List<DRIDesignCrosstabRowGroup> rowGroups) {
		this.rowGroups = rowGroups;
	}
	
	public List<DRIDesignCrosstabCell> getCells() {
		return cells;
	}
	
	public void setCells(List<DRIDesignCrosstabCell> cells) {
		this.cells = cells;
	}
	
	public List<DRIDesignCrosstabMeasure> getMeasures() {
		return measures;
	}
	
	public void setMeasures(List<DRIDesignCrosstabMeasure> measures) {
		this.measures = measures;
	}
}