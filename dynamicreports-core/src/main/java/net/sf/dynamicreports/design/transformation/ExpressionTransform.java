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

package net.sf.dynamicreports.design.transformation;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignField;
import net.sf.dynamicreports.design.base.DRDesignVariable;
import net.sf.dynamicreports.design.base.expression.DRDesignComplexExpression;
import net.sf.dynamicreports.design.base.expression.DRDesignSimpleExpression;
import net.sf.dynamicreports.design.base.expression.DRDesignSystemExpression;
import net.sf.dynamicreports.design.base.expression.DRDesignValueFormatter;
import net.sf.dynamicreports.design.definition.DRIDesignField;
import net.sf.dynamicreports.design.definition.DRIDesignVariable;
import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.definition.DRIColumn;
import net.sf.dynamicreports.report.definition.DRIField;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.definition.DRISubtotal;
import net.sf.dynamicreports.report.definition.DRIVariable;
import net.sf.dynamicreports.report.definition.expression.DRIComplexExpression;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.expression.DRISystemExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ExpressionTransform {	
	private DesignTransformAccessor accessor;
	private Map<String, DRIDesignField> fields;
	private Map<String, DRIDesignVariable> variables;
	private Map<String, DRIDesignSystemExpression> systemExpressions;
	private Map<String, DRIDesignSimpleExpression> simpleExpressions;
	private Map<String, DRIDesignComplexExpression> complexExpressions;
	private Map<DRIExpression<?>, DRIDesignExpression> expressions;
	
	public ExpressionTransform(DesignTransformAccessor accessor) {		
		this.accessor = accessor;
		init();
	}
	
	private void init() {
		fields = new LinkedHashMap<String, DRIDesignField>();
		variables = new LinkedHashMap<String, DRIDesignVariable>();
		systemExpressions = new HashMap<String, DRIDesignSystemExpression>();
		simpleExpressions = new HashMap<String, DRIDesignSimpleExpression>();
		complexExpressions = new HashMap<String, DRIDesignComplexExpression>();
		expressions = new HashMap<DRIExpression<?>, DRIDesignExpression>();
	}
	
	public void transform() throws DRException {
		DRIReport report = accessor.getReport();
		for (DRIField<?> field : report.getFields()) {
			transformExpression(field);
		}
		for (DRIVariable<?> variable : report.getVariables()) {
			transformExpression(variable);
		}	
	}
	
	protected DRIDesignExpression transformExpression(DRIExpression<?> expression) throws DRException {
		return transformExpression(expression, null);
	}
	
	protected DRIDesignExpression transformExpression(DRIExpression<?> expression, DRIValueFormatter<?, ?> valueFormatter) throws DRException {
		if (expression == null) {
			return null;
		}
				
		if (valueFormatter != null) {
			return addExpression(new DRDesignValueFormatter(valueFormatter, transformExpression(expression)));
		}
		if (expressions.containsKey(expression)) {
			return expressions.get(expression);
		}		
		
		DRIDesignExpression express;
		if (expression instanceof DRISystemExpression<?>) {
			express = new DRDesignSystemExpression((DRISystemExpression<?>) expression);
		}
		else if (expression instanceof DRISimpleExpression<?>) {
			express = new DRDesignSimpleExpression((DRISimpleExpression<?>) expression);
		}
		else if (expression instanceof DRIComplexExpression<?>) {
			express = transformComplexExpression((DRIComplexExpression<?>) expression);
		}
		else if (expression instanceof DRIField<?>) {
			express = transformField((DRIField<?>) expression);
		}
		else if (expression instanceof DRIVariable<?>) {
			express = transformVariable((DRIVariable<?>) expression);
		}
		else if (expression instanceof DRIColumn<?>) {
			express = transformExpression(((DRIColumn<?>) expression).getValueField().getValueExpression());
		}
		else if (expression instanceof DRISubtotal<?>) {
			express = transformExpression(((DRISubtotal<?>) expression).getValueField().getValueExpression());
		}
		else {
			throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
		}
		express = addExpression(express);
		if (valueFormatter == null) {
			expressions.put(expression, express);
		}
		return express;
	}
	
	private DRDesignField transformField(DRIField<?> field) {
		DRDesignField designField = new DRDesignField();
		designField.setName(field.getName());
		designField.setValueClass(field.getValueClass());
		return designField;
	}
	
	private DRIDesignExpression transformComplexExpression(DRIComplexExpression<?> complexExpression) throws DRException {
		DRDesignComplexExpression designComplexExpression = new DRDesignComplexExpression(complexExpression);
		for (DRIExpression<?> expression : complexExpression.getExpressions()) {
			designComplexExpression.addExpression(transformExpression(expression));
		}
		return designComplexExpression;
	}

	private DRIDesignExpression transformVariable(DRIVariable<?> variable) throws DRException {
		DRDesignVariable designVariable = new DRDesignVariable(variable.getName());
		designVariable.setValueExpression(accessor.getExpressionTransform().transformExpression(variable.getExpression()));
		designVariable.setCalculation(variable.getCalculation());
		designVariable.setResetType(ConstantTransform.variableResetType(variable.getResetType(), variable.getResetGroup(), accessor));
		designVariable.setResetGroup(accessor.getGroupTransform().getGroup(ConstantTransform.variableResetGroup(variable.getName(), variable.getResetType(), variable.getResetGroup(), accessor)));		
		return designVariable;
	}
	
	private DRIDesignExpression addExpression(DRIDesignExpression expression) {
		if (expression == null) {
			return null;
		}
		if (expression instanceof DRIDesignField) {
			return addField((DRIDesignField) expression);
		}
		else if (expression instanceof DRIDesignVariable) {
			addVariable((DRDesignVariable) expression);
		}
		else if (expression instanceof DRIDesignSystemExpression) {
			addSystemExpression((DRIDesignSystemExpression) expression);
		}
		else if (expression instanceof DRIDesignSimpleExpression) {
			addSimpleExpression((DRIDesignSimpleExpression) expression);
		}
		else if (expression instanceof DRIDesignComplexExpression) {
			addComplexExpression((DRIDesignComplexExpression) expression);			
		}
		else {
			throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
		}
		return expression;
	}
	
	private void addVariable(DRDesignVariable variable) {
		if (variables.containsKey(variable.getName())) {
			if (!variables.get(variable.getName()).equals(variable)) {
				throw new DRDesignReportException("Duplicate declaration of variable \"" + variable.getName() + "\"");	
			}
			return;				
		}	
		variables.put(variable.getName(), variable);	
	}
	
	private DRIDesignField addField(DRIDesignField field) {
		if (fields.containsKey(field.getName())) {
			DRIDesignField fld = fields.get(field.getName());
			if (!fld.getValueClass().equals(field.getValueClass())) {
				throw new DRDesignReportException("Duplicate declaration of field \"" + field.getName() + "\"");	
			}
			return fld;
		}
		fields.put(field.getName(), field);
		return field;
	}
	
	private void addSystemExpression(DRIDesignSystemExpression systemExpression) {
		if (systemExpressions.containsKey(systemExpression.getName())) {
			return;
		}
		systemExpressions.put(systemExpression.getName(), systemExpression);
	}
	
	private void addSimpleExpression(DRIDesignSimpleExpression simpleExpression) {
		if (simpleExpressions.containsKey(simpleExpression.getName())) {
			if (!simpleExpressions.get(simpleExpression.getName()).equals(simpleExpression)) {
				throw new DRDesignReportException("Duplicate declaration of simple expression \"" + simpleExpression.getName() + "\"");	
			}
			return;
		}
		simpleExpressions.put(simpleExpression.getName(), simpleExpression);
	}
	
	private void addComplexExpression(DRIDesignComplexExpression complexExpression) {
		if (complexExpressions.containsKey(complexExpression.getName())) {
			if (!complexExpressions.get(complexExpression.getName()).equals(complexExpression)) {
				throw new DRDesignReportException("Duplicate declaration of complex expression \"" + complexExpression.getName() + "\"");
			}
			return;
		}	
		complexExpressions.put(complexExpression.getName(), complexExpression);
	}
	
	public Collection<DRIDesignField> getFields() {
		return fields.values();
	}
	
	public Collection<DRIDesignVariable> getVariables() {
		return variables.values();
	}

	public Collection<DRIDesignSystemExpression> getSystemExpressions() {
		return systemExpressions.values();
	}
	
	public Collection<DRIDesignSimpleExpression> getSimpleExpressions() {
		return simpleExpressions.values();
	}
	
	public Collection<DRIDesignComplexExpression> getComplexExpressions() {
		return complexExpressions.values();
	}
}
