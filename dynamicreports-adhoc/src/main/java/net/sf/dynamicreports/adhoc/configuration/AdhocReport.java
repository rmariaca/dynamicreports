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

package net.sf.dynamicreports.adhoc.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocReport {
	private AdhocStyle textStyle;
	private AdhocStyle columnStyle;
	private AdhocStyle columnTitleStyle;
	private AdhocStyle groupStyle;
	private AdhocStyle groupTitleStyle;
	private AdhocStyle subtotalStyle;
	private AdhocStyle detailOddRowStyle;
	private Boolean highlightDetailOddRows;
	private AdhocStyle detailEvenRowStyle;
	private Boolean highlightDetailEvenRows;
	private Boolean ignorePagination;
	private Boolean tableOfContents;
	private AdhocPage page;
	private List<AdhocColumn> columns;
	private List<AdhocGroup> groups;
	private List<AdhocSort> sorts;
	private List<AdhocSubtotal> subtotals;
	private List<AdhocComponent> components;

	public AdhocReport() {
		columns = new ArrayList<AdhocColumn>();
		groups = new ArrayList<AdhocGroup>();
		sorts = new ArrayList<AdhocSort>();
		subtotals = new ArrayList<AdhocSubtotal>();
		components = new ArrayList<AdhocComponent>();
	}

	public AdhocStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(AdhocStyle textStyle) {
		this.textStyle = textStyle;
	}

	public AdhocStyle getColumnStyle() {
		return columnStyle;
	}

	public void setColumnStyle(AdhocStyle columnStyle) {
		this.columnStyle = columnStyle;
	}

	public AdhocStyle getColumnTitleStyle() {
		return columnTitleStyle;
	}

	public void setColumnTitleStyle(AdhocStyle columnTitleStyle) {
		this.columnTitleStyle = columnTitleStyle;
	}

	public AdhocStyle getGroupStyle() {
		return groupStyle;
	}

	public void setGroupStyle(AdhocStyle groupStyle) {
		this.groupStyle = groupStyle;
	}

	public AdhocStyle getGroupTitleStyle() {
		return groupTitleStyle;
	}

	public void setGroupTitleStyle(AdhocStyle groupTitleStyle) {
		this.groupTitleStyle = groupTitleStyle;
	}

	public AdhocStyle getSubtotalStyle() {
		return subtotalStyle;
	}

	public void setSubtotalStyle(AdhocStyle subtotalStyle) {
		this.subtotalStyle = subtotalStyle;
	}

	public AdhocStyle getDetailOddRowStyle() {
		return detailOddRowStyle;
	}

	public void setDetailOddRowStyle(AdhocStyle detailOddRowStyle) {
		this.detailOddRowStyle = detailOddRowStyle;
	}

	public Boolean getHighlightDetailOddRows() {
		return highlightDetailOddRows;
	}

	public void setHighlightDetailOddRows(Boolean highlightDetailOddRows) {
		this.highlightDetailOddRows = highlightDetailOddRows;
	}

	public AdhocStyle getDetailEvenRowStyle() {
		return detailEvenRowStyle;
	}

	public void setDetailEvenRowStyle(AdhocStyle detailEvenRowStyle) {
		this.detailEvenRowStyle = detailEvenRowStyle;
	}

	public Boolean getHighlightDetailEvenRows() {
		return highlightDetailEvenRows;
	}

	public void setHighlightDetailEvenRows(Boolean highlightDetailEvenRows) {
		this.highlightDetailEvenRows = highlightDetailEvenRows;
	}

	public Boolean getIgnorePagination() {
		return ignorePagination;
	}

	public void setIgnorePagination(Boolean ignorePagination) {
		this.ignorePagination = ignorePagination;
	}

	public Boolean getTableOfContents() {
		return tableOfContents;
	}

	public void setTableOfContents(Boolean tableOfContents) {
		this.tableOfContents = tableOfContents;
	}

	public AdhocPage getPage() {
		return page;
	}

	public void setPage(AdhocPage page) {
		this.page = page;
	}

	public List<AdhocColumn> getColumns() {
		return columns;
	}

	public void addColumn(AdhocColumn column) {
		this.columns.add(column);
	}

	public void setColumns(List<AdhocColumn> columns) {
		this.columns = columns;
	}

	public List<AdhocGroup> getGroups() {
		return groups;
	}

	public void addGroup(AdhocGroup group) {
		this.groups.add(group);
	}

	public void setGroups(List<AdhocGroup> groups) {
		this.groups = groups;
	}

	public List<AdhocSort> getSorts() {
		return sorts;
	}

	public void addSort(AdhocSort sort) {
		this.sorts.add(sort);
	}

	public void setSorts(List<AdhocSort> sorts) {
		this.sorts = sorts;
	}

	public List<AdhocSubtotal> getSubtotals() {
		return subtotals;
	}

	public void addSubtotal(AdhocSubtotal subtotal) {
		this.subtotals.add(subtotal);
	}

	public void setSubtotals(List<AdhocSubtotal> subtotals) {
		this.subtotals = subtotals;
	}

	public List<AdhocComponent> getComponents() {
		return components;
	}

	public void addComponent(AdhocComponent component) {
		this.components.add(component);
	}

	public void setComponents(List<AdhocComponent> components) {
		this.components = components;
	}

}
