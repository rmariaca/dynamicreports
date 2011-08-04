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

package net.sf.dynamicreports.design.transformation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignDataset;
import net.sf.dynamicreports.design.definition.DRIDesignDataset;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.definition.DRIDataset;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DatasetTransform {
	private DesignTransformAccessor accessor;
	private Map<String, DRIDesignDataset> datasets;
	private Map<DRIDataset, DRDesignDataset> designDatasets;

	public DatasetTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
		datasets = new HashMap<String, DRIDesignDataset>();
		designDatasets = new HashMap<DRIDataset, DRDesignDataset>();
	}

	protected DRDesignDataset transform(DRIDataset dataset) throws DRException {
		if (dataset == null) {
			return null;
		}
		if (designDatasets.containsKey(dataset)) {
			return designDatasets.get(dataset);
		}

		DatasetExpressionTransform datasetExpressionTransform = new DatasetExpressionTransform(dataset);
		datasetExpressionTransform.transform();
		DRDesignDataset designDataset = new DRDesignDataset(datasetExpressionTransform);
		if (dataset.getQuery() != null) {
			designDataset.setQuery(accessor.getReportTransform().query(dataset.getQuery()));
		}
		designDataset.setConnectionExpression(accessor.getExpressionTransform().transformExpression(dataset.getConnectionExpression()));
		designDataset.setDataSourceExpression(accessor.getExpressionTransform().transformExpression(dataset.getDataSourceExpression()));

		addDataset(dataset, designDataset);

		return designDataset;
	}

	protected DRIDesignExpression transformExpression(DRIDataset dataset, DRIExpression<?> expression) throws DRException {
		if (dataset != null) {
			return designDatasets.get(dataset).getDatasetExpressionTransform().transformExpression(expression);
		}
		else {
			return accessor.getExpressionTransform().transformExpression(expression);
		}
	}

	private void addDataset(DRIDataset dataset, DRDesignDataset designDataset) {
		if (datasets.containsKey(designDataset.getName())) {
			throw new DRDesignReportException("Duplicate declaration of dataset \"" + designDataset.getName() + "\"");
		}
		datasets.put(designDataset.getName(), designDataset);
		designDatasets.put(dataset, designDataset);
	}

	public Collection<DRIDesignDataset> getDatasets() {
		return datasets.values();
	}
}
