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

import net.sf.dynamicreports.design.definition.DRIDesignDataset;
import net.sf.dynamicreports.design.definition.DRIDesignReport;
import net.sf.dynamicreports.jasper.transformation.AbstractExpressionTransform;
import net.sf.dynamicreports.jasper.transformation.BandTransform;
import net.sf.dynamicreports.jasper.transformation.BarcodeTransform;
import net.sf.dynamicreports.jasper.transformation.ChartTransform;
import net.sf.dynamicreports.jasper.transformation.ComponentTransform;
import net.sf.dynamicreports.jasper.transformation.CrosstabTransform;
import net.sf.dynamicreports.jasper.transformation.DatasetTransform;
import net.sf.dynamicreports.jasper.transformation.GroupTransform;
import net.sf.dynamicreports.jasper.transformation.JasperTransformAccessor;
import net.sf.dynamicreports.jasper.transformation.MainDatasetExpressionTransform;
import net.sf.dynamicreports.jasper.transformation.ReportTransform;
import net.sf.dynamicreports.jasper.transformation.StyleTransform;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperReportDesign implements JasperTransformAccessor {
	private DRIDesignReport report;
	private ReportTransform reportTransform;
	private MainDatasetExpressionTransform mainDatasetExpressionTransform;
	private BandTransform bandTransform;
	private ComponentTransform componentTransform;
	private GroupTransform groupTransform;
	private StyleTransform styleTransform;
	private ChartTransform chartTransform;
	private BarcodeTransform barcodeTransform;
	private CrosstabTransform crosstabTransform;
	private DatasetTransform datasetTransform;
	private AbstractExpressionTransform expressionTransform;

	private JasperDesign design;
	private Map<String, Object> parameters;
	private ReportParameters masterReportParameters;
	private Integer startPageNumber;

	public JasperReportDesign(DRIDesignReport report, Integer startPageNumber) throws DRException {
		this(report, null, startPageNumber);
	}

	public JasperReportDesign(DRIDesignReport report, ReportParameters masterReportParameters, Integer startPageNumber) throws DRException {
		this.report = report;
		this.masterReportParameters = masterReportParameters;
		this.startPageNumber = startPageNumber;
		init();
		transform();
	}

	private void init() throws DRException {
		this.design = (JasperDesign) report.getTemplateDesign().getDesign();
		reportTransform = new ReportTransform(this);
		mainDatasetExpressionTransform = new MainDatasetExpressionTransform(this);
		groupTransform = new GroupTransform(this);
		bandTransform = new BandTransform(this);
		componentTransform = new ComponentTransform(this);
		styleTransform = new StyleTransform(this);
		chartTransform = new ChartTransform(this);
		barcodeTransform = new BarcodeTransform(this);
		crosstabTransform = new CrosstabTransform(this);
		datasetTransform = new DatasetTransform(this);
		transformToMainDataset();

		this.parameters = new HashMap<String, Object>();
	}

	private void transform() {
		reportTransform.transform();
		datasetTransform.transform();
		groupTransform.transform();
		mainDatasetExpressionTransform.transform();
		groupTransform.transformExpressions();
		styleTransform.transform();
		bandTransform.transform();
		reportTransform.addDependencies();
	}

	public ReportTransform getReportTransform() {
		return reportTransform;
	}

	public ChartTransform getChartTransform() {
		return chartTransform;
	}

	public BarcodeTransform getBarcodeTransform() {
		return barcodeTransform;
	}

	public CrosstabTransform getCrosstabTransform() {
		return crosstabTransform;
	}

	public ComponentTransform getComponentTransform() {
		return componentTransform;
	}

	public void transformToMainDataset() {
		transformToDataset(null);
	}

	public void transformToDataset(DRIDesignDataset dataset) {
		if (dataset != null) {
			expressionTransform = datasetTransform.getDatasetExpressionTransform(dataset);
		}
		else {
			expressionTransform = mainDatasetExpressionTransform;
		}
	}

	public AbstractExpressionTransform getExpressionTransform() {
		return expressionTransform;
	}

	public AbstractExpressionTransform getExpressionTransform(DRIDesignDataset dataset) {
		if (dataset == null) {
			return mainDatasetExpressionTransform;
		}
		else {
			return getDatasetTransform().getDatasetExpressionTransform(dataset);
		}
	}

	public GroupTransform getGroupTransform() {
		return groupTransform;
	}

	public StyleTransform getStyleTransform() {
		return styleTransform;
	}

	public DatasetTransform getDatasetTransform() {
		return datasetTransform;
	}

	public DRIDesignReport getReport() {
		return report;
	}

	public JasperCustomValues getCustomValues() {
		return reportTransform.getCustomValues();
	}

	public JasperDesign getDesign() {
		return design;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public Integer getStartPageNumber() {
		return startPageNumber;
	}

	public ReportParameters getMasterReportParameters() {
		return masterReportParameters;
	}
}
