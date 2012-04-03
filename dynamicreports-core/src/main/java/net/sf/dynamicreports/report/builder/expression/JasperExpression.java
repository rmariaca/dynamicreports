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

package net.sf.dynamicreports.report.builder.expression;

import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIJasperExpression;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperExpression<T> implements DRIJasperExpression<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private String name;
	private String expression;
	private Class<? super T> valueClass;

	protected JasperExpression(String expression, Class<? super T> valueClass) {
		Validate.notNull(expression, "expression must not be null");
		Validate.notNull(valueClass, "valueClass must not be null");
		this.expression = expression;
		this.valueClass = valueClass;
		this.name = ReportUtils.generateUniqueName("jasperExpression");
	}

	public String getName() {
		return name;
	}

	public String getExpression() {
		return expression;
	}

	public Class<? super T> getValueClass() {
		return valueClass;
	}
}
