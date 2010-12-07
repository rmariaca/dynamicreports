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

import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabBucket;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.report.constant.OrderType;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignCrosstabBucket implements DRIDesignCrosstabBucket {	
	private OrderType orderType;	
	private DRIDesignSimpleExpression expression;	
	private DRIDesignSimpleExpression orderByExpression;	
	private DRIDesignSimpleExpression comparatorExpression;
	
	public OrderType getOrderType() {
		return orderType;
	}
	
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
	public DRIDesignSimpleExpression getExpression() {
		return expression;
	}
	
	public void setExpression(DRIDesignSimpleExpression expression) {
		this.expression = expression;
	}
	
	public DRIDesignSimpleExpression getOrderByExpression() {
		return orderByExpression;
	}
	
	public void setOrderByExpression(DRIDesignSimpleExpression orderByExpression) {
		this.orderByExpression = orderByExpression;
	}
	
	public DRIDesignSimpleExpression getComparatorExpression() {
		return comparatorExpression;
	}
	
	public void setComparatorExpression(DRIDesignSimpleExpression comparatorExpression) {
		this.comparatorExpression = comparatorExpression;
	}	
}