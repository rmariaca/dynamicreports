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

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabBucket;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRCrosstabBucket implements DRICrosstabBucket {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private OrderType orderType;	
	private DRISimpleExpression<?> expression;	
	private DRISimpleExpression<?> orderByExpression;	
	private DRISimpleExpression<?> comparatorExpression;
	
	public OrderType getOrderType() {
		return orderType;
	}
	
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
	public DRISimpleExpression<?> getExpression() {
		return expression;
	}
	
	public void setExpression(DRISimpleExpression<?> expression) {
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