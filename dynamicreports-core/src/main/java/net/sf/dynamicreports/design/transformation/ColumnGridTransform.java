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

package net.sf.dynamicreports.design.transformation;

import net.sf.dynamicreports.design.base.component.DRDesignFiller;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.base.style.DRDesignStyle;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.base.grid.DRColumnGridList;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.definition.DRIColumn;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.definition.grid.DRIColumnGrid;
import net.sf.dynamicreports.report.definition.grid.DRIColumnGridComponent;
import net.sf.dynamicreports.report.definition.grid.DRIColumnGridList;
import net.sf.dynamicreports.report.definition.grid.DRIColumnGridListCell;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ColumnGridTransform {
	private DesignTransformAccessor accessor;
	private DRIColumnGridList columnGridList;
	
	public ColumnGridTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;	
	}
	
	public void transform() {
		DRIReport report = accessor.getReport();
		DRIColumnGrid columnGrid = report.getColumnGrid();
		if (columnGrid != null && !columnGrid.getList().getListCells().isEmpty()) {
			this.columnGridList = columnGrid.getList();
			return;
		}
		
		DRColumnGridList columnGridList = new DRColumnGridList();
		if (columnGrid != null) {		
			columnGridList.setGap(columnGrid.getList().getGap());
			columnGridList.setType(columnGrid.getList().getType());
		}
		addColumnsToGridList(columnGridList);
		this.columnGridList = columnGridList;
	}

	private void addColumnsToGridList(DRColumnGridList columnGridList) {
		DRIReport report = accessor.getReport();
		for (DRIColumn<?> column : report.getColumns()) {
			if (!accessor.getGroupTransform().getHideGroupColumns().contains(column)) {
				columnGridList.addComponent(column);
			}
		}
	}
	
	protected ColumnGrid createColumnGrid() throws DRException {
		return createColumnGrid(null);
	}
	
	protected ColumnGrid createColumnGrid(DRDesignStyle groupPaddingStyle) throws DRException {
		ColumnGrid columnGrid = new ColumnGrid();
		DRDesignList list = list(columnGridList, columnGrid);
		int groupPadding = accessor.getGroupTransform().getGroupPadding();
		if (groupPadding > 0) {
			DRDesignFiller filler = new DRDesignFiller();
			filler.setStyle(groupPaddingStyle);
			filler.setWidth(groupPadding);
			filler.setHeight(0);
			list.addComponent(0, HorizontalCellComponentAlignment.CENTER, null, filler);
		}
		columnGrid.setList(list);
		return columnGrid;
	}
	
	private DRDesignList list(DRIColumnGridComponent columnGridComponent, ColumnGrid columnGrid) throws DRException {		
		if (columnGridComponent instanceof DRIColumn<?>) {
			DRDesignList list = new DRDesignList(ListType.VERTICAL);
			DRIColumn<?> column = (DRIColumn<?>) columnGridComponent;
			list.setWidth(accessor.getTemplateTransform().getColumnWidth(column, accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
			columnGrid.addList(column, list);
			return list;
		}
		else if (columnGridComponent instanceof DRIColumnGridList) {
			return columnGridList((DRIColumnGridList) columnGridComponent, columnGrid);
		}
		else {
			throw new DRDesignReportException("Column grid component " + columnGridComponent.getClass().getName() + " not supported");
		}		
	}
	
	private DRDesignList columnGridList(DRIColumnGridList columnGridList, ColumnGrid columnGrid) throws DRException {
		DRDesignList list = new DRDesignList();
		list.setType(columnGridList.getType());
		list.setGap(columnGridList.getGap());
		for (DRIColumnGridListCell cell : columnGridList.getListCells()) {
			DRIColumnGridComponent component = cell.getComponent();
			HorizontalCellComponentAlignment horizontalAlignment = cell.getHorizontalAlignment();
			VerticalCellComponentAlignment verticalAlignment = cell.getVerticalAlignment();
			if (component instanceof DRIColumn<?>) {
				DRIColumn<?> column = (DRIColumn<?>) component;				
				if (horizontalAlignment == null) {
					horizontalAlignment = accessor.getComponentTransform().toHorizontalCellComponentAlignment(column.getValueField().getWidthType());
				}				
				if (verticalAlignment == null) {
					verticalAlignment = accessor.getComponentTransform().toVerticalCellComponentAlignment(column.getValueField().getHeightType());
				}		
			}
			list.addComponent(horizontalAlignment, cell.getVerticalAlignment(), list(component, columnGrid));
		}	
		return list;
	}
}
