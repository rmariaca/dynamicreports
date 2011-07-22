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

package net.sf.dynamicreports.design.base.chart;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignChartDataset;
import net.sf.dynamicreports.design.base.chart.plot.AbstractDesignPlot;
import net.sf.dynamicreports.design.base.component.DRDesignHyperlinkComponent;
import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.chart.DRIDesignChart;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignChart extends DRDesignHyperlinkComponent implements DRIDesignChart {
	private ChartType chartType;
	private DRDesignChartDataset dataset;
	private AbstractDesignPlot plot;
	private List<DRIChartCustomizer> customizers;
	private DRDesignChartTitle title;
	private DRDesignChartSubtitle subtitle;
	private DRDesignChartLegend legend;
	private EvaluationTime evaluationTime;
	private DRDesignGroup evaluationGroup;

	public DRDesignChart() {
		super("chart");
		customizers = new ArrayList<DRIChartCustomizer>();
	}

	public ChartType getChartType() {
		return chartType;
	}

	public void setChartType(ChartType chartType) {
		this.chartType = chartType;
	}

	public DRDesignChartDataset getDataset() {
		return dataset;
	}

	public void setDataset(DRDesignChartDataset dataset) {
		this.dataset = dataset;
	}

	public AbstractDesignPlot getPlot() {
		return plot;
	}

	public void setPlot(AbstractDesignPlot plot) {
		this.plot = plot;
	}

	public List<DRIChartCustomizer> getCustomizers() {
		return customizers;
	}

	public void setCustomizers(List<DRIChartCustomizer> customizers) {
		this.customizers = customizers;
	}

	public DRDesignChartTitle getTitle() {
		return title;
	}

	public void setTitle(DRDesignChartTitle title) {
		this.title = title;
	}

	public DRDesignChartSubtitle getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(DRDesignChartSubtitle subtitle) {
		this.subtitle = subtitle;
	}

	public DRDesignChartLegend getLegend() {
		return legend;
	}

	public void setLegend(DRDesignChartLegend legend) {
		this.legend = legend;
	}

	public EvaluationTime getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(EvaluationTime evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	public DRDesignGroup getEvaluationGroup() {
		return evaluationGroup;
	}

	public void setEvaluationGroup(DRDesignGroup evaluationGroup) {
		this.evaluationGroup = evaluationGroup;
	}
}
