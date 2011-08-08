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

package net.sf.dynamicreports.jasper.transformation;

import java.util.HashMap;
import java.util.Map;

import net.sf.dynamicreports.design.definition.DRIDesignDataset;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.jasper.base.JasperCustomValues;
import net.sf.dynamicreports.jasper.base.JasperReportParameters;
import net.sf.dynamicreports.jasper.base.JasperScriptlet;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignParameter;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DatasetTransform {
	private JasperTransformAccessor accessor;
	private Map<DRIDesignDataset, Map<String, Object>> datasetParameters;
	private Map<DRIDesignDataset, DatasetExpressionTransform> datasetExpressions;

	public DatasetTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
		datasetParameters = new HashMap<DRIDesignDataset, Map<String,Object>>();
		datasetExpressions = new HashMap<DRIDesignDataset, DatasetExpressionTransform>();
	}

	public void transform() {
		for (DRIDesignDataset dataset : accessor.getReport().getDatasets()) {
			addDataset(dataset);
		}
	}

	private void addDataset(DRIDesignDataset dataset) {
		try {
			accessor.getDesign().addDataset(dataset(dataset));
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for dataset \"" + dataset.getName() + "\"", e);
		}
	}

	//dataset
	private JRDesignDataset dataset(DRIDesignDataset dataset) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		datasetParameters.put(dataset, parameters);
		JRDesignDataset jrDataset = new JRDesignDataset(false);
		jrDataset.setName(dataset.getName());
		if (dataset.getQuery() != null) {
			jrDataset.setQuery(accessor.getReportTransform().query(dataset.getQuery()));
		}
		JasperCustomValues customValues = new JasperCustomValues();
		DatasetExpressionTransform datasetExpressionTransform = new DatasetExpressionTransform(dataset, jrDataset, customValues);
		datasetExpressionTransform.transform();
		datasetExpressions.put(dataset, datasetExpressionTransform);
		if (!customValues.isEmpty()) {
			addParameter(jrDataset, parameters, JasperCustomValues.CUSTOM_VALUES, JasperCustomValues.class, customValues);
			JasperScriptlet scriptlet = new JasperScriptlet();
			addScriptlet(jrDataset, parameters, JasperScriptlet.NAME, scriptlet);
		}
		return jrDataset;
	}

	private <T> void addParameter(JRDesignDataset jrDataset, Map<String, Object> parameters, String name, Class<T> parameterClass, T value) {
		JRDesignParameter jrParameter = new JRDesignParameter();
		jrParameter.setName(name);
		jrParameter.setValueClass(parameterClass);
		try {
			jrDataset.addParameter(jrParameter);
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for parameter \"" + name + "\"", e);
		}
		parameters.put(jrParameter.getName(), value);
	}

	private void addScriptlet(JRDesignDataset jrDataset, Map<String, Object> parameters, String name, JRAbstractScriptlet scriptlet) {
		try {
			jrDataset.addScriptlet(accessor.getReportTransform().scriptlet(name, scriptlet));
			parameters.put(name + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX, scriptlet);
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for scriptlet \"" + name + "\"", e);
		}
	}

	protected JRDesignDatasetRun datasetRun(DRIDesignDataset dataset) {
		if (dataset == null) {
			return null;
		}

		JRDesignDatasetRun jrDatasetRun = new JRDesignDatasetRun();
		jrDatasetRun.setDatasetName(dataset.getName());
		jrDatasetRun.setConnectionExpression(accessor.getExpressionTransform().getExpression(dataset.getConnectionExpression()));
		jrDatasetRun.setDataSourceExpression(accessor.getExpressionTransform().getExpression(dataset.getDataSourceExpression()));
		DatasetParametersExpression parametersExpression = new DatasetParametersExpression(datasetParameters.get(dataset));
		accessor.getExpressionTransform().addSimpleExpression(parametersExpression);
		jrDatasetRun.setParametersMapExpression(accessor.getExpressionTransform().getExpression(parametersExpression));
		return jrDatasetRun;
	}

	protected Map<String, Object> getDatasetParameters(DRIDesignDataset dataset) {
		return datasetParameters.get(dataset);
	}

	public DatasetExpressionTransform getDatasetExpressionTransform(DRIDesignDataset dataset) {
		return datasetExpressions.get(dataset);
	}

	private class DatasetParametersExpression implements DRIDesignSimpleExpression {
		private Map<String, Object> parameters;
		private String name;

		public DatasetParametersExpression(Map<String, Object> parameters) {
			this.parameters = parameters;
			this.name = ReportUtils.generateUniqueName("datasetParametersExpression");
		}

		public Object evaluate(ReportParameters reportParameters) {
			Map<String, Object> parameters = new HashMap<String, Object>(this.parameters);
			parameters.put(JasperReportParameters.MASTER_REPORT_PARAMETERS, reportParameters);
			return parameters;
		}

		public String getName() {
			return name;
		}

		public Class<?> getValueClass() {
			return Map.class;
		}
	}
}
