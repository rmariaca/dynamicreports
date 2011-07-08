/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
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

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.base.component.DRDesignFiller;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.base.style.DRDesignStyle;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.grid.DRColumnGridList;
import net.sf.dynamicreports.report.base.grid.DRColumnGridListCell;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.definition.column.DRIBooleanColumn;
import net.sf.dynamicreports.report.definition.column.DRIColumn;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.component.DRIDimensionComponent;
import net.sf.dynamicreports.report.definition.grid.DRIColumnGrid;
import net.sf.dynamicreports.report.definition.grid.DRIColumnGridComponent;
import net.sf.dynamicreports.report.definition.grid.DRIColumnGridList;
import net.sf.dynamicreports.report.definition.grid.DRIColumnGridListCell;
import net.sf.dynamicreports.report.definition.grid.DRIColumnTitleGroup;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ColumnGridTransform {
	private DesignTransformAccessor accessor;
	private DRIColumnGridList columnDefaultGridList;
	private DRIColumnGridList columnTitleGridList;

	public ColumnGridTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
	}

	public void transform() {
		DRIReport report = accessor.getReport();
		DRIColumnGrid columnGrid = report.getColumnGrid();
		if (columnGrid != null && !columnGrid.getList().getListCells().isEmpty()) {
			if (isTitleGroup(columnGrid.getList())) {
				this.columnDefaultGridList = createColumnDefaultGrid(columnGrid.getList());
			}
			else {
				this.columnDefaultGridList = columnGrid.getList();
			}
			this.columnTitleGridList = columnGrid.getList();
			return;
		}

		DRColumnGridList columnGridList = new DRColumnGridList();
		if (columnGrid != null) {
			columnGridList.setGap(columnGrid.getList().getGap());
			columnGridList.setType(columnGrid.getList().getType());
		}
		addColumnsToGridList(columnGridList);
		this.columnDefaultGridList = columnGridList;
		this.columnTitleGridList = columnGridList;
	}

	private boolean isTitleGroup(DRIColumnGridComponent component) {
		if (component instanceof DRIColumnGridList) {
			DRIColumnGridList list = (DRIColumnGridList) component;
			for (DRIColumnGridListCell cell : list.getListCells()) {
				if (isTitleGroup(cell.getComponent())) {
					return true;
				}
			}
		}
		else if (component instanceof DRIColumnTitleGroup) {
			return true;
		}
		return false;
	}

	private DRIColumnGridList createColumnDefaultGrid(DRIColumnGridList list) {
		DRColumnGridList columnGridList = new DRColumnGridList();
		columnGridList.setGap(list.getGap());
		columnGridList.setType(list.getType());
		for (DRIColumnGridListCell cell : list.getListCells()) {
			DRIColumnGridComponent component = cell.getComponent();
			if (component instanceof DRIColumnGridList ||
					component instanceof DRIColumnTitleGroup && !list.getType().equals(((DRIColumnTitleGroup) component).getList().getType())) {
				DRIColumnGridList newList = createColumnDefaultGrid((DRIColumnGridList) component);
				DRColumnGridListCell newCell = new DRColumnGridListCell(cell.getHorizontalAlignment(), cell.getVerticalAlignment(), newList);
				columnGridList.addCell(newCell);
			}
			else if (component instanceof DRIColumnTitleGroup) {
				for (DRIColumnGridComponent component2 : createColumnDefaultGrid((DRIColumnTitleGroup) component)) {
					DRColumnGridListCell newCell = new DRColumnGridListCell(cell.getHorizontalAlignment(), cell.getVerticalAlignment(), component2);
					columnGridList.addCell(newCell);
				}
			}
			else {
				columnGridList.addCell((DRColumnGridListCell) cell);
			}
		}
		return columnGridList;
	}

	private List<DRIColumnGridComponent> createColumnDefaultGrid(DRIColumnTitleGroup titleGroup) {
		List<DRIColumnGridComponent> components = new ArrayList<DRIColumnGridComponent>();
		for (DRIColumnGridListCell cell : titleGroup.getList().getListCells()) {
			DRIColumnGridComponent component = cell.getComponent();
			if (component instanceof DRIColumnGridList ||
					component instanceof DRIColumnTitleGroup && !titleGroup.getList().getType().equals(((DRIColumnTitleGroup) component).getList().getType())) {
				components.add(createColumnDefaultGrid((DRIColumnGridList) component));
			}
			else if (component instanceof DRIColumnTitleGroup) {
				for (DRIColumnGridComponent component2 : createColumnDefaultGrid((DRIColumnTitleGroup) component)) {
					components.add(component2);
				}
			}
			else {
				components.add(component);
			}
		}
		return components;
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
		return createColumnGrid(columnDefaultGridList, null, false);
	}

	protected ColumnGrid createColumnTitleGrid(DRDesignStyle groupPaddingStyle) throws DRException {
		return createColumnGrid(columnTitleGridList, groupPaddingStyle, true);
	}

	private ColumnGrid createColumnGrid(DRIColumnGridList columnGridList, DRDesignStyle groupPaddingStyle, boolean titleGroup) throws DRException {
		ColumnGrid columnGrid = new ColumnGrid();
		DRDesignList list = list(columnGridList, columnGrid, titleGroup);
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

	private DRDesignList list(DRIColumnGridComponent columnGridComponent, ColumnGrid columnGrid, boolean titleGroup) throws DRException {
		if (columnGridComponent instanceof DRIColumn<?>) {
			DRDesignList list = new DRDesignList(ListType.VERTICAL);
			DRIColumn<?> column = (DRIColumn<?>) columnGridComponent;
			list.setWidth(accessor.getTemplateTransform().getColumnWidth(column, accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
			columnGrid.addList(column, list);
			return list;
		}
		else if (columnGridComponent instanceof DRIColumnGridList) {
			return columnGridList((DRIColumnGridList) columnGridComponent, columnGrid, titleGroup);
		}
		else if (columnGridComponent instanceof DRIColumnTitleGroup) {
			return columnGridTitleGroup((DRIColumnTitleGroup) columnGridComponent, columnGrid, titleGroup);
		}
		else {
			throw new DRDesignReportException("Column grid component " + columnGridComponent.getClass().getName() + " not supported");
		}
	}

	private DRDesignList columnGridList(DRIColumnGridList columnGridList, ColumnGrid columnGrid, boolean titleGroup) throws DRException {
		DRDesignList list = new DRDesignList();
		list.setType(columnGridList.getType());
		list.setGap(columnGridList.getGap());
		for (DRIColumnGridListCell cell : columnGridList.getListCells()) {
			DRIColumnGridComponent component = cell.getComponent();
			HorizontalCellComponentAlignment horizontalAlignment = cell.getHorizontalAlignment();
			VerticalCellComponentAlignment verticalAlignment = cell.getVerticalAlignment();
			if (component instanceof DRIColumn<?>) {
				DRIColumn<?> column = (DRIColumn<?>) component;
				if (column instanceof DRIBooleanColumn) {
					if (horizontalAlignment == null) {
						horizontalAlignment = ConstantTransform.toHorizontalCellComponentAlignment(((DRIBooleanColumn) column).getWidthType());
					}
					if (verticalAlignment == null) {
						verticalAlignment = ConstantTransform.toVerticalCellComponentAlignment(((DRIBooleanColumn) column).getHeightType());
					}
				}
				else {
					DRIComponent columnComponent = accessor.getColumnTransform().getColumnComponent(column);
					if (columnComponent instanceof DRIDimensionComponent) {
						if (horizontalAlignment == null) {
							horizontalAlignment = ConstantTransform.toHorizontalCellComponentAlignment(((DRIDimensionComponent) columnComponent).getWidthType());
						}
						if (verticalAlignment == null) {
							verticalAlignment = ConstantTransform.toVerticalCellComponentAlignment(((DRIDimensionComponent) columnComponent).getHeightType());
						}
					}
				}
			}
			list.addComponent(horizontalAlignment, cell.getVerticalAlignment(), list(component, columnGrid, titleGroup));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private DRDesignList columnGridTitleGroup(DRIColumnTitleGroup columnGridTitleGroup, ColumnGrid columnGrid, boolean titleGroup) throws DRException {
		DRDesignList columnList = list(columnGridTitleGroup.getList(), columnGrid, titleGroup);
		if (!titleGroup || columnGridTitleGroup.getTitleExpression() == null) {
			return columnList;
		}

		@SuppressWarnings("rawtypes")
		DRTextField titleGroupField = new DRTextField();
		titleGroupField.setValueExpression(columnGridTitleGroup.getTitleExpression());
		titleGroupField.setStyle((DRStyle) columnGridTitleGroup.getTitleStyle());
		titleGroupField.setHeight(columnGridTitleGroup.getTitleHeight());
		titleGroupField.setHeightType(columnGridTitleGroup.getTitleHeightType());
		titleGroupField.setRows(columnGridTitleGroup.getTitleRows());
		DRDesignTextField designTitleGroupField = accessor.getComponentTransform().textField(titleGroupField, DefaultStyleType.COLUMN_TITLE);
		designTitleGroupField.setUniqueName("columngroup.title");

		DRDesignList list = new DRDesignList();
		list.setType(ListType.VERTICAL);
		list.addComponent(designTitleGroupField);
		list.addComponent(columnList);

		return list;
	}
}
