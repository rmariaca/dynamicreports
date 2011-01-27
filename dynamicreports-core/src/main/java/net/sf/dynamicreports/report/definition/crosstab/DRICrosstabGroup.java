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

package net.sf.dynamicreports.report.definition.crosstab;

import java.io.Serializable;

import net.sf.dynamicreports.report.constant.CrosstabTotalPosition;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;
import net.sf.dynamicreports.report.definition.style.DRIStyle;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DRICrosstabGroup<T> extends Serializable {

	public String getName();

	public String getHeaderPattern();

	public HorizontalAlignment getHeaderHorizontalAlignment();

	public DRIValueFormatter<?, ? super T> getHeaderValueFormatter();

	public Boolean getHeaderStretchWithOverflow();

	public DRIStyle getHeaderStyle();

	public Boolean getShowTotal();

	public CrosstabTotalPosition getTotalPosition();

	public DRIExpression<?> getTotalHeaderExpression();

	public DRIStyle getTotalHeaderStyle();

	public DRIExpression<T> getExpression();

	public DRIDataType<? super T, T> getDataType();

	public DRISimpleExpression<T> getOrderByExpression();

	public OrderType getOrderType();

	public DRISimpleExpression<T> getComparatorExpression();
}
