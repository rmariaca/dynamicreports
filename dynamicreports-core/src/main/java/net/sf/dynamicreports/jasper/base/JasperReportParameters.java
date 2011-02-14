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

package net.sf.dynamicreports.jasper.base;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.jasper.constant.ValueType;
import net.sf.dynamicreports.report.definition.DRIReportScriptlet;
import net.sf.dynamicreports.report.definition.DRIScriptlet;
import net.sf.dynamicreports.report.definition.DRIValue;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JRVariable;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperReportParameters implements ReportParameters {
	public static final String MASTER_REPORT_PARAMETERS = "MASTER_REPORT_PARAMETERS";

	private JasperScriptlet jasperScriptlet;

	protected JasperReportParameters(JasperScriptlet jasperScriptlet) {
		this.jasperScriptlet = jasperScriptlet;
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(String name) {
		ValueType type = jasperScriptlet.getValueType(name);
		if (type != null) {
			switch (type) {
			case FIELD:
				return (T) getFieldValue(name);
			case VARIABLE:
				return (T) getVariableValue(name);
			case PARAMETER:
				return (T) getParameterValue(name);
			case SIMPLE_EXPRESSION:
				return (T) getSimpleExpressionValue(name);
			case COMPLEX_EXPRESSION:
				return (T) getComplexExpressionValue(name);
			case SYSTEM_EXPRESSION:
				return (T) getSystemExpressionValue(name);
			default:
				break;
			}
		}

		if (name.equals(DRIReportScriptlet.NAME)) {
			return (T) getParameterValue(DRIReportScriptlet.NAME + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(DRIValue<T> value) {
		return (T) getValue(value.getName());
	}

	//field
	private Object getFieldValue(String name) {
		try {
			return jasperScriptlet.getFieldValue(name);
		} catch (JRScriptletException e) {
		}
		return null;
	}

	//variable
	private Object getVariableValue(String name) {
		try {
			return jasperScriptlet.getVariableValue(name);
		} catch (JRScriptletException e) {
		}
		return null;
	}

	public Integer getPageNumber() {
		return (Integer) getVariableValue(JRVariable.PAGE_NUMBER);
	}

	public Integer getColumnNumber() {
		return (Integer) getVariableValue(JRVariable.COLUMN_NUMBER);
	}

	public Integer getReportRowNumber() {
		return (Integer) getVariableValue(JRVariable.REPORT_COUNT);
	}

	public Integer getPageRowNumber() {
		return (Integer) getVariableValue(JRVariable.PAGE_COUNT);
	}

	public Integer getColumnRowNumber() {
		return (Integer) getVariableValue(JRVariable.COLUMN_COUNT);
	}

	public Integer getGroupCount(String groupName) {
		return (Integer) getVariableValue(groupName + "_COUNT");
	}

	//parameter
	private Object getParameterValue(String name) {
		try {
			return ((Map<?, ?>) jasperScriptlet.getParameterValue(JRParameter.REPORT_PARAMETERS_MAP)).get(name);
		} catch (JRScriptletException e) {
		}
		return null;
	}

	public Connection getConnection() {
		return (Connection) getParameterValue(JRParameter.REPORT_CONNECTION);
	}

	public Locale getLocale() {
		return (Locale) getParameterValue(JRParameter.REPORT_LOCALE);
	}

	public DRIScriptlet getScriptlet(String name) {
		return ((CustomScriptlet) getParameterValue(name + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX)).getScriptlet();
	}

	public String getMessage(String key) {
		return ((ResourceBundle) getParameterValue(JRParameter.REPORT_RESOURCE_BUNDLE)).getString(key);
	}

	//simple expression
	private Object getSimpleExpressionValue(String name) {
		return jasperScriptlet.getSimpleExpression(name).evaluate(this);
	}

	//complex expression
	private Object getComplexExpressionValue(String name) {
		List<Object> values = new ArrayList<Object>();
		DRIDesignComplexExpression complexExpression = jasperScriptlet.getComplexExpression(name);
		for (DRIDesignExpression valueExpression : complexExpression.getExpressions()) {
			values.add(getValue(valueExpression.getName()));
		}
		return complexExpression.evaluate(values, this);
	}

	//system expression
	private Object getSystemExpressionValue(String name) {
		return jasperScriptlet.getSystemValue(name);
	}

	public ReportParameters getMasterParameters() {
		return (ReportParameters) getParameterValue(MASTER_REPORT_PARAMETERS);
	}
}
