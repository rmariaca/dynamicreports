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

package net.sf.dynamicreports.design.base;

import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.DRIDesignVariable;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Calculation;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignVariable implements DRIDesignVariable {
	private String name;
	private DRIDesignExpression valueExpression;
	private DRIDesignExpression initialValueExpression;
	private Calculation calculation;
	private ResetType resetType;
	private DRDesignGroup resetGroup;

	public DRDesignVariable() {
		this.name = ReportUtils.generateUniqueName("variable");
	}

	public DRDesignVariable(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public DRIDesignExpression getValueExpression() {
		return valueExpression;
	}

	public void setValueExpression(DRIDesignExpression valueExpression) {
		this.valueExpression = valueExpression;
	}

	public DRIDesignExpression getInitialValueExpression() {
		return initialValueExpression;
	}

	public void setInitialValueExpression(DRIDesignExpression initialValueExpression) {
		this.initialValueExpression = initialValueExpression;
	}

	public Calculation getCalculation() {
		return calculation;
	}

	public void setCalculation(Calculation calculation) {
		this.calculation = calculation;
	}

	public ResetType getResetType() {
		return resetType;
	}

	public void setResetType(ResetType resetType) {
		this.resetType = resetType;
	}

	public DRDesignGroup getResetGroup() {
		return resetGroup;
	}

	public void setResetGroup(DRDesignGroup resetGroup) {
		this.resetGroup = resetGroup;
	}

	public Class<?> getValueClass() {
		return ReportUtils.getVariableValueClass(calculation, valueExpression.getValueClass());
	}
}
