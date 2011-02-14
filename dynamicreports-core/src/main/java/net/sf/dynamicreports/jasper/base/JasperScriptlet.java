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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.jasper.constant.ValueType;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.definition.DRIReportScriptlet;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.fill.JRFillGroup;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperScriptlet extends JRDefaultScriptlet implements DRIReportScriptlet {
	public static final String SCRIPTLET_NAME = NAME + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX;

	private JasperReportParameters reportParameters;
	private Map<String, Object> systemValues;

	public JasperScriptlet() {
		systemValues = new HashMap<String, Object>();
	}

	@SuppressWarnings("ucd")
	public Object getValue(String valueName) {
		return reportParameters.getValue(valueName);
	}

	@SuppressWarnings("ucd")
	public Object getValue(String name, Object[] values) {
		return getComplexExpression(name).evaluate(Arrays.asList(values), reportParameters);
	}

	protected JasperReportParameters getReportParameters() {
		return reportParameters;
	}

	private JasperCustomValues getCustomValues() {
		try {
			return (JasperCustomValues) getParameterValue(JasperCustomValues.CUSTOM_VALUES);
		} catch (JRScriptletException e) {
			throw new JasperDesignException("Custom values not found", e);
		}
	}

	protected ValueType getValueType(String name) {
		return getCustomValues().getValueType(name);
	}

	protected DRIDesignSimpleExpression getSimpleExpression(String name) {
		return getCustomValues().getSimpleExpression(name);
	}

	protected DRIDesignComplexExpression getComplexExpression(String name) {
		return getCustomValues().getComplexExpression(name);
	}

	protected DRIChartCustomizer getChartCustomizer(String name) {
		return getCustomValues().getChartCustomizer(name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setData(Map parsm, Map fldsm, Map varsm, JRFillGroup[] grps) {
		super.setData(parsm, fldsm, varsm, grps);
		reportParameters = new JasperReportParameters(this);
	}

	public void setSystemValue(String name, Object value) {
		systemValues.put(name, value);
	}

	public Object getSystemValue(String name) {
		return systemValues.get(name);
	}
}
