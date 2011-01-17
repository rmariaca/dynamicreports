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
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabGroup;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class DRCrosstabGroup implements DRICrosstabGroup {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private String name;
	private Boolean showTotal;
	private CrosstabTotalPosition totalPosition;
	private DRIExpression<?> totalHeaderExpression;
	private OrderType orderType;
	private DRIExpression<?> expression;
	private DRISimpleExpression<?> orderByExpression;
	private DRISimpleExpression<?> comparatorExpression;

	public DRCrosstabGroup() {
		this.name = ReportUtils.generateUniqueName("crosstabGroup");
	}

	public String getName() {
		return name;
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

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public DRIExpression<?> getExpression() {
		return expression;
	}

	public void setExpression(DRIExpression<?> expression) {
		this.expression = expression;
	}

	public DRISimpleExpression<?> getOrderByExpression() {
		return orderByExpression;
	}

	public void setOrderByExpression(DRISimpleExpression<?> orderByExpression) {
		this.orderByExpression = orderByExpression;
	}

	public DRISimpleExpression<?> getComparatorExpression() {
		return comparatorExpression;
	}

	public void setComparatorExpression(DRISimpleExpression<?> comparatorExpression) {
		this.comparatorExpression = comparatorExpression;
	}
}