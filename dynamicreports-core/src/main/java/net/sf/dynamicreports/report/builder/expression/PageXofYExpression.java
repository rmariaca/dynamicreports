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

package net.sf.dynamicreports.report.builder.expression;

import java.util.List;
import java.util.ResourceBundle;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.Evaluation;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class PageXofYExpression extends AbstractComplexExpression<String> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	public PageXofYExpression() {
		addExpression(DynamicReports.variable(Expressions.pageNumber(), Calculation.NOTHING).setResetType(Evaluation.PAGE));
		addExpression(DynamicReports.variable(Expressions.pageNumber(), Calculation.NOTHING).setResetType(Evaluation.REPORT));
	}

	@Override
	public String evaluate(List<?> values, ReportParameters reportParameters) {
		String label = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_NAME, reportParameters.getLocale()).getString("of");
		return ((Number) values.get(0)).intValue()  + " " + label  + " " + ((Number) values.get(1)).intValue();
	}
}
