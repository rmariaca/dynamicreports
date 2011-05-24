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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.dynamicreports.design.base.DRDesignBand;
import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.base.component.DRFiller;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.constant.SubtotalPosition;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.definition.DRIBand;
import net.sf.dynamicreports.report.definition.DRIGroup;
import net.sf.dynamicreports.report.definition.DRISubtotal;
import net.sf.dynamicreports.report.definition.column.DRIColumn;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SubtotalTransform {
	private DesignTransformAccessor accessor;

	public SubtotalTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
	}

	//subtotals
	public void transform() throws DRException {
		ColumnGrid title = accessor.getColumnGridTransform().createColumnGrid();
		ColumnGrid pageHeader = accessor.getColumnGridTransform().createColumnGrid();
		ColumnGrid pageFooter = accessor.getColumnGridTransform().createColumnGrid();
		ColumnGrid columnHeader = accessor.getColumnGridTransform().createColumnGrid();
		ColumnGrid columnFooter = accessor.getColumnGridTransform().createColumnGrid();
		Map<DRIGroup, ColumnGrid> groupHeader = new HashMap<DRIGroup, ColumnGrid>();
		Map<DRIGroup, ColumnGrid> groupFooter = new HashMap<DRIGroup, ColumnGrid>();
		ColumnGrid lastPageFooter = accessor.getColumnGridTransform().createColumnGrid();
		ColumnGrid summary = accessor.getColumnGridTransform().createColumnGrid();

		HorizontalCellComponentAlignment horizontalAlignment = HorizontalCellComponentAlignment.FLOAT;
		VerticalCellComponentAlignment verticalAlignment = VerticalCellComponentAlignment.TOP;
		for (DRISubtotal<?> subtotal : accessor.getReport().getSubtotals()) {
			SubtotalPosition position = subtotal.getPosition();
			DRIColumn<?> showInColumn = subtotal.getShowInColumn();
			DRDesignComponent subtotalComponent = valueComponent(subtotal);
			if (subtotal.getLabelExpression() != null) {
				DRDesignList list = new DRDesignList(ListType.VERTICAL);
				list.addComponent(horizontalAlignment, verticalAlignment, labelComponent(subtotal));
				list.addComponent(horizontalAlignment, verticalAlignment, subtotalComponent);
				subtotalComponent = list;
			}
			switch (position) {
			case TITLE:
				title.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				break;
			case PAGE_HEADER:
				pageHeader.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				break;
			case PAGE_FOOTER:
				pageFooter.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				break;
			case COLUMN_HEADER:
				columnHeader.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				break;
			case COLUMN_FOOTER:
				columnFooter.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				break;
			case GROUP_HEADER:
				getGroupGrid(subtotal.getGroup(), groupHeader).addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				break;
			case GROUP_FOOTER:
				getGroupGrid(subtotal.getGroup(), groupFooter).addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				break;
			case FIRST_GROUP_HEADER:
				DRIGroup firstGroup = accessor.getGroupTransform().getFirstGroup();
				if (firstGroup != null) {
					getGroupGrid(firstGroup, groupHeader).addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				}
				break;
			case FIRST_GROUP_FOOTER:
				firstGroup = accessor.getGroupTransform().getFirstGroup();
				if (firstGroup != null) {
					getGroupGrid(firstGroup, groupFooter).addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				}
				break;
			case LAST_GROUP_HEADER:
				DRIGroup lastGroup = accessor.getGroupTransform().getLastGroup();
				if (lastGroup != null) {
					getGroupGrid(lastGroup, groupHeader).addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				}
				break;
			case LAST_GROUP_FOOTER:
				lastGroup = accessor.getGroupTransform().getLastGroup();
				if (lastGroup != null) {
					getGroupGrid(lastGroup, groupFooter).addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				}
				break;
			case LAST_PAGE_FOOTER:
				lastPageFooter.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				break;
			case SUMMARY:
				summary.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
				break;
			default:
				throw new DRDesignReportException("Subtotal position " + position.name() + " not supported");
			}
		}

		DRFiller filler = null;
		if (accessor.getTemplateTransform().getPageColumnsPerPage() > 1) {
			int fillerWidth = accessor.getBandTransform().getMaxWidth() - accessor.getBandTransform().getMaxColumnWidth();
			filler= new DRFiller();
			filler.setWidth(fillerWidth);
		}

		addAfterBandComponent(accessor.getBandTransform().getTitleBand(), title, filler);
		addAfterBandComponent(accessor.getBandTransform().getPageHeaderBand(), pageHeader, filler);
		addBeforeBandComponent(accessor.getBandTransform().getPageFooterBand(), pageFooter, filler);
		addAfterBandComponent(accessor.getBandTransform().getColumnHeaderBand(), columnHeader, null);
		addBeforeBandComponent(accessor.getBandTransform().getColumnFooterBand(), columnFooter, null);
		for (Entry<DRIGroup, ColumnGrid> entry : groupHeader.entrySet()) {
			DRIGroup group = entry.getKey();
			DRIBand bnd = group.getHeaderBand();
			DRDesignGroup designGroup = accessor.getGroupTransform().getGroup(group);
			DRDesignBand band = accessor.getBandTransform().band("subtotalGroupHeader", bnd, accessor.getTemplateTransform().getGroupHeaderSplitType(bnd));
			addAfterBandComponent(band, entry.getValue(), null);
			setPrintGroupSubtotalsWhenExpression(group, entry.getValue());
			designGroup.addHeaderBand(band);
		}
		for (Entry<DRIGroup, ColumnGrid> entry : groupFooter.entrySet()) {
			DRIGroup group = entry.getKey();
			DRIBand bnd = group.getFooterBand();
			DRDesignGroup designGroup = accessor.getGroupTransform().getGroup(group);
			DRDesignBand band = accessor.getBandTransform().band("subtotalGroupFooter", bnd, accessor.getTemplateTransform().getGroupFooterSplitType(bnd));
			addBeforeBandComponent(band, entry.getValue(), null);
			setPrintGroupSubtotalsWhenExpression(group, entry.getValue());
			designGroup.addFooterBand(0, band);
		}
		addBeforeBandComponent(accessor.getBandTransform().getLastPageFooterBand(), lastPageFooter, filler);
		addBeforeBandComponent(accessor.getBandTransform().getSummaryBand(), summary, filler);
	}

	private ColumnGrid getGroupGrid(DRIGroup group, Map<DRIGroup, ColumnGrid> groupList) throws DRException {
		if (!groupList.containsKey(group)) {
			groupList.put(group, accessor.getColumnGridTransform().createColumnGrid());
		}
		return groupList.get(group);
	}

	private void setPrintGroupSubtotalsWhenExpression(DRIGroup group, ColumnGrid grid) throws DRException {
		DRIExpression<Boolean> printSubtotalsWhenExpression = group.getPrintSubtotalsWhenExpression();
		if (grid.isEmpty() || printSubtotalsWhenExpression == null) {
			return;
		}
		grid.getList().setPrintWhenExpression(accessor.getExpressionTransform().transformExpression(printSubtotalsWhenExpression));
	}

	private void addAfterBandComponent(DRDesignBand band, ColumnGrid grid, DRFiller filler) throws DRException {
		if (grid.isEmpty()) {
			return;
		}
		DRDesignList list = grid.getList();
		if (filler != null) {
			list = new DRDesignList();
			list.addComponent(grid.getList());
			list.addComponent(HorizontalCellComponentAlignment.LEFT, null, accessor.getComponentTransform().filler(filler));
		}

		band.addComponent(list);
	}

	private void addBeforeBandComponent(DRDesignBand band, ColumnGrid grid, DRFiller filler) throws DRException {
		if (grid.isEmpty()) {
			return;
		}
		DRDesignList list = grid.getList();
		if (filler != null) {
			list = new DRDesignList();
			list.addComponent(grid.getList());
			list.addComponent(HorizontalCellComponentAlignment.LEFT, null, accessor.getComponentTransform().filler(filler));
		}
		band.addComponent(0, list);
	}

	//label
	@SuppressWarnings("unchecked")
	private DRDesignComponent labelComponent(DRISubtotal<?> subtotal) throws DRException {
		@SuppressWarnings("rawtypes")
		DRTextField labelField = new DRTextField();
		labelField.setValueExpression(subtotal.getLabelExpression());
		labelField.setStyle((DRStyle) subtotal.getLabelStyle());
		labelField.setWidth(accessor.getTemplateTransform().getColumnWidth(subtotal.getShowInColumn(), accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
		DRDesignTextField designLabelField = accessor.getComponentTransform().textField(labelField, DefaultStyleType.TEXT);
		designLabelField.setUniqueName("column_" + subtotal.getShowInColumn().getName() + ".subtotal.label");
		return designLabelField;
	}

	//value
	private DRDesignComponent valueComponent(DRISubtotal<?> subtotal) throws DRException {
		DRDesignTextField designValueField = accessor.getComponentTransform().textField(subtotal.getValueField(), DefaultStyleType.SUBTOTAL);
		designValueField.setUniqueName("column_" + subtotal.getShowInColumn().getName() + ".subtotal");
		designValueField.setWidth(accessor.getTemplateTransform().getColumnWidth(subtotal.getShowInColumn(), accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
		return designValueField;
	}
}
