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

package net.sf.dynamicreports.jasper.transformation;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.DRIDesignField;
import net.sf.dynamicreports.design.definition.DRIDesignVariable;
import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.jasper.constant.ValueType;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignVariable;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ExpressionTransform {
	private static final String VALUE = "$P'{'REPORT_SCRIPTLET'}'.getValue(\"{0}\")";
	private static final String FIELD_VALUE = "$F'{'{0}'}'";
	private static final String VARIABLE_VALUE = "$V'{'{0}'}'";
	private static final String COMPLEX_VALUE = "$P'{'REPORT_SCRIPTLET'}'.getValue(\"{0}\", new Object[]'{'{1}'}')";
	
	private JasperTransformAccessor accessor;	
	private Map<String, JRDesignExpression> expressions;
	
	public ExpressionTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
		this.expressions = new HashMap<String, JRDesignExpression>();
	}
	
	public void transform() {	
		for (DRIDesignField field : accessor.getReport().getFields()) {
			addField(field);
		}
		for (DRIDesignSimpleExpression expression : accessor.getReport().getSimpleExpressions()) {
			addSimpleExpression(expression);
		}	
		for (DRIDesignComplexExpression complexExpression : accessor.getReport().getComplexExpressions()) {
			addComplexExpression(complexExpression);
		}
		for (DRIDesignVariable variable : accessor.getReport().getVariables()) {
			addVariable(variable);
		}	
	}
	
	public void addSimpleExpression(DRIDesignSimpleExpression simpleExpression) {		
		if (simpleExpression == null)
			return;		
		accessor.getCustomValues().addSimpleExpression(simpleExpression);
		addExpression(simpleExpression);
	}
	
	private void addField(DRIDesignField field) {				
		try {
			accessor.getDesign().addField(field(field));
			accessor.getCustomValues().addValueType(field.getName(), ValueType.FIELD);
			addExpression(field);
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for field \"" + field.getName() + "\"", e);
		}		
	}
	
	private void addVariable(DRIDesignVariable variable) {		
		try {
			accessor.getDesign().addVariable(variable(variable));		
			accessor.getCustomValues().addValueType(variable.getName(), ValueType.VARIABLE);
			addExpression(variable);
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for variable \"" + variable.getName() + "\"", e);
		}
	}

	private void addComplexExpression(DRIDesignComplexExpression complexExpression) {		
		if (complexExpression == null)
			return;		
		accessor.getCustomValues().addComplexExpression(complexExpression);
		addExpression(complexExpression);
	}
	
	private void addExpression(DRIDesignExpression expression) {
		if (expressions.containsKey(expression.getName())) {
			throw new JasperDesignException("Duplicate declaration of expression \"" + expression.getName() + "\"");			
		}
		expressions.put(expression.getName(), expression(expression));		
	}
	
	//field
	private JRDesignField field(DRIDesignField field) {
		JRDesignField jrField = new JRDesignField();
		jrField.setName(field.getName());
		jrField.setValueClass(field.getValueClass());
		return jrField;
	}
	
	//variable
	private JRDesignVariable variable(DRIDesignVariable variable) {
		JRDesignExpression expression = getExpression(variable.getValueExpression());
		
		JRDesignVariable jrVariable = new JRDesignVariable();
		jrVariable.setName(variable.getName());
		jrVariable.setExpression(expression);
		jrVariable.setValueClass(variable.getValueClass());
		jrVariable.setCalculation(ConstantTransform.calculation(variable.getCalculation()));
		ResetType resetType = variable.getResetType();
		jrVariable.setResetType(ConstantTransform.variableResetType(resetType));
		if (resetType.equals(ResetType.GROUP) && variable.getResetGroup() != null) {
			jrVariable.setResetGroup(accessor.getGroupTransform().getGroup(variable.getResetGroup()));
		}
		return jrVariable;
	}
	
	//simple expression
	private JRDesignExpression expression(DRIDesignExpression simpleExpression) {	
		JRDesignExpression expression = new JRDesignExpression();
		expression.setText(getExpressionText(simpleExpression));
		expression.setValueClass(simpleExpression.getValueClass());
		return expression;
	}
	
	private static String getExpressionText(DRIDesignExpression expression) {
		if (expression instanceof DRIDesignField) {
			return MessageFormat.format(FIELD_VALUE, expression.getName());
		}
		else if (expression instanceof DRIDesignVariable) {
			return MessageFormat.format(VARIABLE_VALUE, expression.getName());
		}
		else if (expression instanceof DRIDesignComplexExpression) {
			DRIDesignComplexExpression complexExpression = (DRIDesignComplexExpression) expression;
			String values = "";
			for (DRIDesignExpression valueExpression : complexExpression.getExpressions()) {
				values +=  ", " + getExpressionText(valueExpression);
			}
			if (values.length() > 0) {
				values = values.substring(2);
			}
			return MessageFormat.format(COMPLEX_VALUE, expression.getName(), values);
		}
		else if (expression instanceof DRIDesignSimpleExpression) {
			return MessageFormat.format(VALUE, expression.getName());
		}
		else {
			throw new JasperDesignException("Expression " + expression.getClass().getName() + " not supported");
		}
	}
	
	protected JRDesignExpression getExpression(DRIDesignExpression expression) {
		if (expression == null)
			return null;
		if (!expressions.containsKey(expression.getName())) {
			throw new JasperDesignException("Expression \"" + expression.getName() + "\" is not registered");
		}			
		return expressions.get(expression.getName());
	}
}
