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

import net.sf.dynamicreports.design.base.DRDesignVariable;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.constant.Evaluation;
import net.sf.dynamicreports.report.definition.DRIGroup;
import net.sf.dynamicreports.report.definition.DRIVariable;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
class VariableTransform {

	protected static DRIDesignExpression variable(DRIVariable<?> variable, DesignTransformAccessor accessor) throws DRException {
		DRDesignVariable designVariable = new DRDesignVariable(variable.getName());
		designVariable.setValueExpression(accessor.getExpressionTransform().transformExpression(variable.getExpression()));
		designVariable.setCalculation(variable.getCalculation());
		designVariable.setResetType(resetType(variable.getResetType(), variable.getResetGroup(), accessor));
		designVariable.setResetGroup(accessor.getGroupTransform().getGroup(resetGroup(variable.getName(), variable.getResetType(), variable.getResetGroup(), accessor)));		
		return designVariable;
	}
	
	private static ResetType resetType(Evaluation resetType, DRIGroup resetGroup, DesignTransformAccessor accessor) {
		if (resetType == null) {
			return ResetType.REPORT;
		}
		
		switch (resetType) {
		case REPORT:			
			return ResetType.REPORT;
		case PAGE:
			return ResetType.PAGE;
		case COLUMN:
			return ResetType.COLUMN;
		case FIRST_GROUP:
			if (accessor.getGroupTransform().getFirstGroup() == null) {
				return ResetType.REPORT; 
			}
			return ResetType.GROUP;
		case BEFORE_GROUP:
			if (accessor.getGroupTransform().getBeforeGroup(resetGroup) == null) {
				return ResetType.REPORT; 
			}
			return ResetType.GROUP;
		case LAST_GROUP:
			if (accessor.getGroupTransform().getLastGroup() == null) {
				return ResetType.REPORT; 
			}
			return ResetType.GROUP;
		case GROUP:
			return ResetType.GROUP;
		default:
			throw new DRDesignReportException("Evaluation reset type " + resetType.name() + " not supported");
		}
	}

	private static DRIGroup resetGroup(String name, Evaluation evaluation, DRIGroup resetGroup, DesignTransformAccessor accessor) throws DRException {
		if (evaluation == null) {
			return null;
		}
		
		switch (evaluation) {
		case REPORT:			
		case PAGE:
		case COLUMN:
			if (resetGroup != null) {
				throw new DRException("Reset group for variable " + name + " is required only for reset types BEFORE_GROUP or GROUP");
			}
			return null;
		case FIRST_GROUP:
			if (resetGroup != null) {
				throw new DRException("Reset group for variable " + name + " is required only for reset types BEFORE_GROUP or GROUP");
			}
			return accessor.getGroupTransform().getFirstGroup();
		case BEFORE_GROUP:
			if (resetGroup == null) {
				throw new DRException("Reset group missing for variable " + name);
			}
			return accessor.getGroupTransform().getBeforeGroup(resetGroup);
		case LAST_GROUP:
			if (resetGroup != null) {
				throw new DRException("Reset group for variable " + name + " is required only for reset types BEFORE_GROUP or GROUP");
			}
			return accessor.getGroupTransform().getLastGroup();
		case GROUP:
			if (resetGroup == null) {
				throw new DRException("Reset group missing for variable " + name);
			}
			return resetGroup;
		default:
			throw new DRDesignReportException("Evaluation group " + evaluation.name() + " not supported");
		}
	}
}
