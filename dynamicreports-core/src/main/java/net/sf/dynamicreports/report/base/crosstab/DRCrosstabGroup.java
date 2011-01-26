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

import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.CrosstabTotalPosition;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabGroup;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;
import net.sf.dynamicreports.report.definition.style.DRIStyle;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class DRCrosstabGroup<T> implements DRICrosstabGroup<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private String name;
	private String headerPattern;
	private HorizontalAlignment headerHorizontalAlignment;
	private DRIValueFormatter<?, ? super T> headerValueFormatter;
	private Boolean headerStretchWithOverflow;
	private DRIStyle headerStyle;
	private Boolean showTotal;
	private CrosstabTotalPosition totalPosition;
	private DRIExpression<?> totalHeaderExpression;
	private DRIStyle totalHeaderStyle;
	private DRIExpression<T> expression;
	private DRIDataType<? super T, T> dataType;
	private DRISimpleExpression<T> orderByExpression;
	private OrderType orderType;
	private DRISimpleExpression<T> comparatorExpression;

	public DRCrosstabGroup() {
		this.name = ReportUtils.generateUniqueName("crosstabGroup");
	}

	public String getName() {
		return name;
	}

	public String getHeaderPattern() {
		return headerPattern;
	}

	public void setHeaderPattern(String headerPattern) {
		this.headerPattern = headerPattern;
	}

	public HorizontalAlignment getHeaderHorizontalAlignment() {
		return headerHorizontalAlignment;
	}

	public void setHeaderHorizontalAlignment(HorizontalAlignment headerHorizontalAlignment) {
		this.headerHorizontalAlignment = headerHorizontalAlignment;
	}

	public DRIValueFormatter<?, ? super T> getHeaderValueFormatter() {
		return headerValueFormatter;
	}

	public void setHeaderValueFormatter(DRIValueFormatter<?, ? super T> headerValueFormatter) {
		this.headerValueFormatter = headerValueFormatter;
	}

	public Boolean getHeaderStretchWithOverflow() {
		return headerStretchWithOverflow;
	}

	public void setHeaderStretchWithOverflow(Boolean headerStretchWithOverflow) {
		this.headerStretchWithOverflow = headerStretchWithOverflow;
	}

	public DRIStyle getHeaderStyle() {
		return headerStyle;
	}

	public void setHeaderStyle(DRIStyle headerStyle) {
		this.headerStyle = headerStyle;
	}

	public Boolean getShowTotal() {
		return showTotal;
	}

	public void setShowTotal(Boolean showTotal) {
		this.showTotal = showTotal;
	}

	public CrosstabTotalPosition getTotalPosition() {
		return totalPosition;
	}

	public void setTotalPosition(CrosstabTotalPosition totalPosition) {
		this.totalPosition = totalPosition;
	}

	public DRIExpression<?> getTotalHeaderExpression() {
		return totalHeaderExpression;
	}

	public void setTotalHeaderExpression(DRIExpression<?> totalHeaderExpression) {
		this.totalHeaderExpression = totalHeaderExpression;
	}

	public DRIStyle getTotalHeaderStyle() {
		return totalHeaderStyle;
	}

	public void setTotalHeaderStyle(DRIStyle totalHeaderStyle) {
		this.totalHeaderStyle = totalHeaderStyle;
	}

	public DRIExpression<T> getExpression() {
		return expression;
	}

	public void setExpression(DRIExpression<T> expression) {
		Validate.notNull(expression, "expression must not be null");
		this.expression = expression;
	}

	public DRIDataType<? super T, T> getDataType() {
		return dataType;
	}

	public void setDataType(DRIDataType<? super T, T> dataType) {
		this.dataType = dataType;
	}

	public DRISimpleExpression<T> getOrderByExpression() {
		return orderByExpression;
	}

	public void setOrderByExpression(DRISimpleExpression<T> orderByExpression) {
		this.orderByExpression = orderByExpression;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public DRISimpleExpression<T> getComparatorExpression() {
		return comparatorExpression;
	}

	public void setComparatorExpression(DRISimpleExpression<T> comparatorExpression) {
		this.comparatorExpression = comparatorExpression;
	}
}