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

package net.sf.dynamicreports.report.base.crosstab;

import java.util.Comparator;

import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.CrosstabTotalPosition;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabGroup;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;
import net.sf.dynamicreports.report.definition.style.DRIReportStyle;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public abstract class DRCrosstabGroup<T> implements DRICrosstabGroup<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private String name;
	private String headerPattern;
	private HorizontalAlignment headerHorizontalAlignment;
	private DRIValueFormatter<?, ? super T> headerValueFormatter;
	private Boolean headerStretchWithOverflow;
	private DRIReportStyle headerStyle;
	private Boolean showTotal;
	private CrosstabTotalPosition totalPosition;
	private DRIExpression<?> totalHeaderExpression;
	private DRIReportStyle totalHeaderStyle;
	private DRIExpression<T> expression;
	private DRIDataType<? super T, T> dataType;
	private DRIExpression<? extends Comparable<?>> orderByExpression;
	private OrderType orderType;
	private DRIExpression<? extends Comparator<?>> comparatorExpression;

	public DRCrosstabGroup() {
		this.name = ReportUtils.generateUniqueName("crosstabGroup");
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getHeaderPattern() {
		return headerPattern;
	}

	public void setHeaderPattern(String headerPattern) {
		this.headerPattern = headerPattern;
	}

	@Override
	public HorizontalAlignment getHeaderHorizontalAlignment() {
		return headerHorizontalAlignment;
	}

	public void setHeaderHorizontalAlignment(HorizontalAlignment headerHorizontalAlignment) {
		this.headerHorizontalAlignment = headerHorizontalAlignment;
	}

	@Override
	public DRIValueFormatter<?, ? super T> getHeaderValueFormatter() {
		return headerValueFormatter;
	}

	public void setHeaderValueFormatter(DRIValueFormatter<?, ? super T> headerValueFormatter) {
		this.headerValueFormatter = headerValueFormatter;
	}

	@Override
	public Boolean getHeaderStretchWithOverflow() {
		return headerStretchWithOverflow;
	}

	public void setHeaderStretchWithOverflow(Boolean headerStretchWithOverflow) {
		this.headerStretchWithOverflow = headerStretchWithOverflow;
	}

	@Override
	public DRIReportStyle getHeaderStyle() {
		return headerStyle;
	}

	public void setHeaderStyle(DRIReportStyle headerStyle) {
		this.headerStyle = headerStyle;
	}

	@Override
	public Boolean getShowTotal() {
		return showTotal;
	}

	public void setShowTotal(Boolean showTotal) {
		this.showTotal = showTotal;
	}

	@Override
	public CrosstabTotalPosition getTotalPosition() {
		return totalPosition;
	}

	public void setTotalPosition(CrosstabTotalPosition totalPosition) {
		this.totalPosition = totalPosition;
	}

	@Override
	public DRIExpression<?> getTotalHeaderExpression() {
		return totalHeaderExpression;
	}

	public void setTotalHeaderExpression(DRIExpression<?> totalHeaderExpression) {
		this.totalHeaderExpression = totalHeaderExpression;
	}

	@Override
	public DRIReportStyle getTotalHeaderStyle() {
		return totalHeaderStyle;
	}

	public void setTotalHeaderStyle(DRIReportStyle totalHeaderStyle) {
		this.totalHeaderStyle = totalHeaderStyle;
	}

	@Override
	public DRIExpression<T> getExpression() {
		return expression;
	}

	public void setExpression(DRIExpression<T> expression) {
		Validate.notNull(expression, "expression must not be null");
		this.expression = expression;
	}

	@Override
	public DRIDataType<? super T, T> getDataType() {
		return dataType;
	}

	public void setDataType(DRIDataType<? super T, T> dataType) {
		this.dataType = dataType;
	}

	@Override
	public DRIExpression<? extends Comparable<?>> getOrderByExpression() {
		return orderByExpression;
	}

	public void setOrderByExpression(DRIExpression<? extends Comparable<?>> orderByExpression) {
		this.orderByExpression = orderByExpression;
	}

	@Override
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@Override
	public DRIExpression<? extends Comparator<?>> getComparatorExpression() {
		return comparatorExpression;
	}

	public void setComparatorExpression(DRIExpression<? extends Comparator<?>> comparatorExpression) {
		this.comparatorExpression = comparatorExpression;
	}

	@Override
	public Class<? super T> getValueClass() {
		return getExpression().getValueClass();
	}
}