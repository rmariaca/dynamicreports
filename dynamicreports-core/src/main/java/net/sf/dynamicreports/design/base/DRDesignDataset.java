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

import java.util.Collection;

import net.sf.dynamicreports.design.definition.DRIDesignDataset;
import net.sf.dynamicreports.design.definition.DRIDesignField;
import net.sf.dynamicreports.design.definition.DRIDesignSort;
import net.sf.dynamicreports.design.definition.DRIDesignVariable;
import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import net.sf.dynamicreports.design.transformation.DatasetExpressionTransform;
import net.sf.dynamicreports.report.ReportUtils;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignDataset implements DRIDesignDataset {
	private String name;
	private DatasetExpressionTransform datasetExpressionTransform;
	private DRDesignQuery query;
	private DRIDesignExpression connectionExpression;
	private DRIDesignExpression dataSourceExpression;
	private DRIDesignExpression filterExpression;

	public DRDesignDataset(DatasetExpressionTransform datasetExpressionTransform) {
		this.datasetExpressionTransform = datasetExpressionTransform;
		this.name = ReportUtils.generateUniqueName("dataset");
	}

	public String getName() {
		return name;
	}

	public DatasetExpressionTransform getDatasetExpressionTransform() {
		return datasetExpressionTransform;
	}

	public Collection<DRIDesignField> getFields() {
		return datasetExpressionTransform.getFields();
	}

	public Collection<DRIDesignVariable> getVariables() {
		return datasetExpressionTransform.getVariables();
	}

	public Collection<DRIDesignSystemExpression> getSystemExpressions() {
		return datasetExpressionTransform.getSystemExpressions();
	}

	public Collection<DRIDesignJasperExpression> getJasperExpressions() {
		return datasetExpressionTransform.getJasperExpressions();
	}

	public Collection<DRIDesignSimpleExpression> getSimpleExpressions() {
		return datasetExpressionTransform.getSimpleExpressions();
	}

	public Collection<DRIDesignComplexExpression> getComplexExpressions() {
		return datasetExpressionTransform.getComplexExpressions();
	}

	public Collection<DRIDesignSort> getSorts() {
		return datasetExpressionTransform.getSorts();
	}

	public DRDesignQuery getQuery() {
		return query;
	}

	public void setQuery(DRDesignQuery query) {
		this.query = query;
	}

	public DRIDesignExpression getConnectionExpression() {
		return connectionExpression;
	}

	public void setConnectionExpression(DRIDesignExpression connectionExpression) {
		this.connectionExpression = connectionExpression;
	}

	public DRIDesignExpression getDataSourceExpression() {
		return dataSourceExpression;
	}

	public void setDataSourceExpression(DRIDesignExpression dataSourceExpression) {
		this.dataSourceExpression = dataSourceExpression;
	}

	public DRIDesignExpression getFilterExpression() {
		return filterExpression;
	}

	public void setFilterExpression(DRIDesignExpression filterExpression) {
		this.filterExpression = filterExpression;
	}
}