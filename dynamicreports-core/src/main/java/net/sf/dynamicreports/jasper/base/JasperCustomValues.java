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

import java.util.HashMap;
import java.util.Map;

import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.jasper.constant.ValueType;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;

public class JasperCustomValues {
	public static final String CUSTOM_VALUES = "CUSTOM_VALUES";

	private Map<String, ValueType> valueTypes;
	private Map<String, DRIDesignSimpleExpression> simpleExpressions;
	private Map<String, DRIDesignComplexExpression> complexExpressions;
	private Map<String, DRIChartCustomizer> chartCustomizers;

	public JasperCustomValues() {
		init();
	}

	private void init() {
		valueTypes = new HashMap<String, ValueType>();
		simpleExpressions = new HashMap<String, DRIDesignSimpleExpression>();
		complexExpressions = new HashMap<String, DRIDesignComplexExpression>();
		chartCustomizers = new HashMap<String, DRIChartCustomizer>();
	}

	public void addSimpleExpression(DRIDesignSimpleExpression simpleExpression) {
		simpleExpressions.put(simpleExpression.getName(), simpleExpression);
		addValueType(simpleExpression.getName(), ValueType.SIMPLE_EXPRESSION);
	}

	public void addComplexExpression(DRIDesignComplexExpression complexExpression) {
		complexExpressions.put(complexExpression.getName(), complexExpression);
		addValueType(complexExpression.getName(), ValueType.COMPLEX_EXPRESSION);
	}

	public void addChartCustomizer(String name, DRIChartCustomizer chartCustomizer) {
		chartCustomizers.put(name, chartCustomizer);
	}

	public void addValueType(String name, ValueType valueType) {
		if (valueTypes.containsKey(name)) {
			throw new JasperDesignException("Duplicate value name \"" + name + "\"");
		}
		valueTypes.put(name, valueType);
	}

	protected ValueType getValueType(String name) {
		return valueTypes.get(name);
	}

	protected DRIDesignSimpleExpression getSimpleExpression(String name) {
		return simpleExpressions.get(name);
	}

	protected DRIDesignComplexExpression getComplexExpression(String name) {
		return complexExpressions.get(name);
	}

	protected DRIChartCustomizer getChartCustomizer(String name) {
		return chartCustomizers.get(name);
	}

	public boolean isEmpty() {
		if (!simpleExpressions.isEmpty()) {
			return false;
		}
		if (!complexExpressions.isEmpty()) {
			return false;
		}
		if (!chartCustomizers.isEmpty()) {
			return false;
		}
		return true;
	}
}
