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

package net.sf.dynamicreports.design.base.crosstab;

import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabMeasure;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.CrosstabPercentageType;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignCrosstabMeasure implements DRIDesignCrosstabMeasure {
	private String name;
	private DRIDesignExpression valueExpression;
	private Calculation calculation;
	private CrosstabPercentageType percentageType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DRIDesignExpression getValueExpression() {
		return valueExpression;
	}

	public void setValueExpression(DRIDesignExpression valueExpression) {
		this.valueExpression = valueExpression;
	}

	public Calculation getCalculation() {
		return calculation;
	}

	public void setCalculation(Calculation calculation) {
		this.calculation = calculation;
	}

	public CrosstabPercentageType getPercentageType() {
		return percentageType;
	}

	public void setPercentageType(CrosstabPercentageType percentageType) {
		this.percentageType = percentageType;
	}

	public Class<?> getValueClass() {
		if (percentageType != null && percentageType.equals(CrosstabPercentageType.GRAND_TOTAL) &&
				!calculation.equals(Calculation.COUNT) && !calculation.equals(Calculation.DISTINCT_COUNT)) {
			return Double.class;
		}
		return ReportUtils.getVariableValueClass(calculation, valueExpression.getValueClass());
	}
}