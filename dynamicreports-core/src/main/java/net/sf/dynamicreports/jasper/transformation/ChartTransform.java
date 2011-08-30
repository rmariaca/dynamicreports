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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.DRIDesignHyperLink;
import net.sf.dynamicreports.design.definition.chart.DRIDesignChart;
import net.sf.dynamicreports.design.definition.chart.DRIDesignChartLegend;
import net.sf.dynamicreports.design.definition.chart.DRIDesignChartSubtitle;
import net.sf.dynamicreports.design.definition.chart.DRIDesignChartTitle;
import net.sf.dynamicreports.design.definition.chart.dataset.DRIDesignCategoryDataset;
import net.sf.dynamicreports.design.definition.chart.dataset.DRIDesignChartDataset;
import net.sf.dynamicreports.design.definition.chart.dataset.DRIDesignChartSerie;
import net.sf.dynamicreports.design.definition.chart.dataset.DRIDesignTimeSeriesDataset;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignAxisFormat;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignAxisPlot;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignBar3DPlot;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignBarPlot;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignBasePlot;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignLinePlot;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignPie3DPlot;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignPiePlot;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignSpiderPlot;
import net.sf.dynamicreports.jasper.base.JasperChartCustomizer;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import net.sf.jasperreports.charts.design.JRDesignAreaPlot;
import net.sf.jasperreports.charts.design.JRDesignBar3DPlot;
import net.sf.jasperreports.charts.design.JRDesignBarPlot;
import net.sf.jasperreports.charts.design.JRDesignCategoryDataset;
import net.sf.jasperreports.charts.design.JRDesignCategorySeries;
import net.sf.jasperreports.charts.design.JRDesignLinePlot;
import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;
import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.charts.design.JRDesignPiePlot;
import net.sf.jasperreports.charts.design.JRDesignPieSeries;
import net.sf.jasperreports.charts.design.JRDesignScatterPlot;
import net.sf.jasperreports.charts.design.JRDesignTimeSeries;
import net.sf.jasperreports.charts.design.JRDesignTimeSeriesDataset;
import net.sf.jasperreports.charts.design.JRDesignTimeSeriesPlot;
import net.sf.jasperreports.charts.design.JRDesignXyDataset;
import net.sf.jasperreports.charts.design.JRDesignXySeries;
import net.sf.jasperreports.components.ComponentsExtensionsRegistryFactory;
import net.sf.jasperreports.components.charts.ChartSettings;
import net.sf.jasperreports.components.spiderchart.SpiderChartComponent;
import net.sf.jasperreports.components.spiderchart.StandardChartSettings;
import net.sf.jasperreports.components.spiderchart.StandardSpiderDataset;
import net.sf.jasperreports.components.spiderchart.StandardSpiderPlot;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.JRHyperlinkHelper;
import net.sf.jasperreports.engine.base.JRBaseChartPlot;
import net.sf.jasperreports.engine.base.JRBaseChartPlot.JRBaseSeriesColor;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ChartTransform {
	private JasperTransformAccessor accessor;

	public ChartTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}

	//chart
	protected JRDesignElement transform(DRIDesignChart chart) {
		if(chart.getChartType().equals(ChartType.SPIDER)) {
			return spiderChart(chart);
		}
		else {
			return chart(chart);
		}
	}

	private JRDesignElement chart(DRIDesignChart chart) {
		JRDesignChart jrChart = new JRDesignChart(new JRDesignStyle().getDefaultStyleProvider(), ConstantTransform.chartType(chart.getChartType()));
		EvaluationTime evaluationTime = chart.getEvaluationTime();
		jrChart.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && chart.getEvaluationGroup() != null) {
			jrChart.setEvaluationGroup(accessor.getGroupTransform().getGroup(chart.getEvaluationGroup()));
		}

		if (chart.getChartType().equals(ChartType.XYBAR)) {
			jrChart.setDataset(new JRDesignXyDataset(null));
		}

		DRIDesignHyperLink hyperLink = chart.getHyperLink();
		if (hyperLink != null) {
			jrChart.setAnchorNameExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorNameExpression()));
			jrChart.setHyperlinkAnchorExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorExpression()));
			jrChart.setHyperlinkPageExpression(accessor.getExpressionTransform().getExpression(hyperLink.getPageExpression()));
			jrChart.setHyperlinkReferenceExpression(accessor.getExpressionTransform().getExpression(hyperLink.getReferenceExpression()));
			jrChart.setHyperlinkTooltipExpression(accessor.getExpressionTransform().getExpression(hyperLink.getTooltipExpression()));
			HyperlinkTypeEnum hyperLinkType = ConstantTransform.hyperLinkType(hyperLink.getType());
			if (hyperLinkType != null) {
				jrChart.setHyperlinkType(hyperLinkType);
			}
		}

		DRIDesignChartTitle title = chart.getTitle();
		jrChart.setTitleColor(title.getColor());
		if (title.getFont() != null)
			jrChart.setTitleFont(accessor.getStyleTransform().font(title.getFont()));
		jrChart.setTitleExpression(accessor.getExpressionTransform().getExpression(title.getTitle()));
		jrChart.setTitlePosition(ConstantTransform.chartPosition(title.getPosition()));

		DRIDesignChartSubtitle subtitle = chart.getSubtitle();
		jrChart.setSubtitleColor(subtitle.getColor());
		if (subtitle.getFont() != null)
			jrChart.setSubtitleFont(accessor.getStyleTransform().font(subtitle.getFont()));
		jrChart.setSubtitleExpression(accessor.getExpressionTransform().getExpression(subtitle.getTitle()));

		DRIDesignChartLegend legend = chart.getLegend();
		jrChart.setLegendColor(legend.getColor());
		jrChart.setLegendBackgroundColor(legend.getBackgroundColor());
		jrChart.setShowLegend(legend.getShowLegend());
		if (legend.getFont() != null)
			jrChart.setLegendFont(accessor.getStyleTransform().font(legend.getFont()));
		jrChart.setLegendPosition(ConstantTransform.chartPosition(legend.getPosition()));

		if (!chart.getCustomizers().isEmpty()) {
			jrChart.setCustomizerClass(JasperChartCustomizer.class.getName());
			addChartCustomizer(chart.getUniqueName(), chart.getCustomizers());
		}

		dataset(chart.getDataset(), (JRDesignChartDataset) jrChart.getDataset());
		plot((DRIDesignBasePlot) chart.getPlot(), jrChart.getPlot());

		return jrChart;
	}

	private JRDesignElement spiderChart(DRIDesignChart chart) {
		SpiderChartComponent jrChart = new SpiderChartComponent();

		EvaluationTime evaluationTime = chart.getEvaluationTime();
		jrChart.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && chart.getEvaluationGroup() != null) {
			jrChart.setEvaluationGroup(accessor.getGroupTransform().getGroup(chart.getEvaluationGroup()).getName());
		}

		jrChart.setChartSettings(spiderSettings(chart));
		StandardSpiderDataset jrDataset = new StandardSpiderDataset();
		dataset(chart.getDataset(), jrDataset);
		jrChart.setDataset(jrDataset);
		StandardSpiderPlot jrPlot = new StandardSpiderPlot();
		jrChart.setPlot(jrPlot);
		spiderPlot((DRIDesignSpiderPlot) chart.getPlot(), jrPlot);

		JRDesignComponentElement jrComponent = new JRDesignComponentElement();
		jrComponent.setComponent(jrChart);
		jrComponent.setComponentKey(new ComponentKey(ComponentsExtensionsRegistryFactory.NAMESPACE, "jr", "spiderChart"));

		return jrComponent;
	}

	private void addChartCustomizer(String name, List<DRIChartCustomizer> chartCustomizers) {
		accessor.getCustomValues().addChartCustomizers(name, chartCustomizers);
	}

	//dataset
	private void dataset(DRIDesignChartDataset dataset, JRDesignElementDataset jrDataset) {
		jrDataset.setDatasetRun(accessor.getDatasetTransform().datasetRun(dataset.getSubDataset()));
		ResetType resetType = dataset.getResetType();
		jrDataset.setResetType(ConstantTransform.variableResetType(resetType));
		if (resetType.equals(ResetType.GROUP) && dataset.getResetGroup() != null) {
			jrDataset.setResetGroup(accessor.getGroupTransform().getGroup(dataset.getResetGroup()));
		}

		accessor.transformToDataset(dataset.getSubDataset());
		if (jrDataset instanceof JRDesignCategoryDataset) {
			categoryDataset((DRIDesignCategoryDataset) dataset, (JRDesignCategoryDataset) jrDataset);
		}
		else if (jrDataset instanceof JRDesignPieDataset) {
			pieDataset(dataset, (JRDesignPieDataset) jrDataset);
		}
		else if (jrDataset instanceof JRDesignTimeSeriesDataset) {
			timeSeriesDataset((DRIDesignTimeSeriesDataset) dataset, (JRDesignTimeSeriesDataset) jrDataset);
		}
		else if (jrDataset instanceof JRDesignXyDataset) {
			xyDataset(dataset, (JRDesignXyDataset) jrDataset);
		}
		else if (jrDataset instanceof StandardSpiderDataset) {
			spiderDataset((DRIDesignCategoryDataset) dataset, (StandardSpiderDataset) jrDataset);
		}
		else {
			throw new JasperDesignException("Dataset " + dataset.getClass().getName() + " not supported");
		}
		accessor.transformToMainDataset();
	}

	private void categoryDataset(DRIDesignCategoryDataset dataset, JRDesignCategoryDataset jrDataset) {
		List<JRDesignCategorySeries> jrSeries = categorySeries(dataset);
		for (JRDesignCategorySeries jrSerie : jrSeries) {
			jrDataset.addCategorySeries(jrSerie);
		}
	}

	private List<JRDesignCategorySeries> categorySeries(DRIDesignCategoryDataset dataset) {
		List<JRDesignCategorySeries> series = new ArrayList<JRDesignCategorySeries>();

		AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
		JRDesignExpression exp1 = expressionTransform.getExpression(dataset.getValueExpression());
		for (DRIDesignChartSerie serie : dataset.getSeries()) {
			JRDesignCategorySeries jrSerie = new JRDesignCategorySeries();
			jrSerie.setValueExpression(expressionTransform.getExpression(serie.getValueExpression()));

			JRDesignExpression exp2 = expressionTransform.getExpression(serie.getLabelExpression());
			JRDesignExpression seriesExpression = expressionTransform.getExpression(serie.getSeriesExpression());
			if (dataset.isUseSeriesAsCategory()) {
				jrSerie.setCategoryExpression(exp2);
				if (seriesExpression != null) {
					jrSerie.setSeriesExpression(seriesExpression);
				}
				else {
					jrSerie.setSeriesExpression(exp1);
				}
				jrSerie.setLabelExpression(exp1);
			} else {
				jrSerie.setCategoryExpression(exp1);
				if (seriesExpression != null) {
					jrSerie.setSeriesExpression(seriesExpression);
				}
				else {
					jrSerie.setSeriesExpression(exp2);
				}
				jrSerie.setLabelExpression(exp2);
			}
			series.add(jrSerie);
		}

		return series;
	}

	private void pieDataset(DRIDesignChartDataset dataset, JRDesignPieDataset jrDataset) {
		AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
		JRDesignExpression exp1 = expressionTransform.getExpression(dataset.getValueExpression());
		for (DRIDesignChartSerie serie : dataset.getSeries()) {
			JRDesignPieSeries jrSerie = new JRDesignPieSeries();
			jrSerie.setKeyExpression(exp1);
			jrSerie.setValueExpression(expressionTransform.getExpression(serie.getValueExpression()));
			jrDataset.addPieSeries(jrSerie);
		}
	}

	private void timeSeriesDataset(DRIDesignTimeSeriesDataset dataset, JRDesignTimeSeriesDataset jrDataset) {
		AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
		if (dataset.getTimePeriodType() != null) {
			jrDataset.setTimePeriod(ConstantTransform.timePeriodType(dataset.getTimePeriodType()));
		}

		JRDesignExpression exp1 = expressionTransform.getExpression(dataset.getValueExpression());
		for (DRIDesignChartSerie serie : dataset.getSeries()) {
			JRDesignTimeSeries jrSerie = new JRDesignTimeSeries();
			jrSerie.setTimePeriodExpression(exp1);
			jrSerie.setValueExpression(expressionTransform.getExpression(serie.getValueExpression()));
			JRDesignExpression seriesExpression = expressionTransform.getExpression(serie.getSeriesExpression());
			JRDesignExpression labelExpression = expressionTransform.getExpression(serie.getLabelExpression());
			if (seriesExpression != null) {
				jrSerie.setSeriesExpression(seriesExpression);
			}
			else {
				jrSerie.setSeriesExpression(labelExpression);
			}
			jrSerie.setLabelExpression(labelExpression);
			jrDataset.addTimeSeries(jrSerie);
		}
	}

	private void xyDataset(DRIDesignChartDataset dataset, JRDesignXyDataset jrDataset) {
		AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
		JRDesignExpression exp1 = expressionTransform.getExpression(dataset.getValueExpression());
		for (DRIDesignChartSerie serie : dataset.getSeries()) {
			JRDesignXySeries jrSerie = new JRDesignXySeries();
			jrSerie.setXValueExpression(exp1);
			jrSerie.setYValueExpression(expressionTransform.getExpression(serie.getValueExpression()));
			JRDesignExpression seriesExpression = expressionTransform.getExpression(serie.getSeriesExpression());
			JRDesignExpression labelExpression = expressionTransform.getExpression(serie.getLabelExpression());
			if (seriesExpression != null) {
				jrSerie.setSeriesExpression(seriesExpression);
			}
			else {
				jrSerie.setSeriesExpression(labelExpression);
			}
			jrSerie.setLabelExpression(labelExpression);
			jrDataset.addXySeries(jrSerie);
		}
	}

	private void spiderDataset(DRIDesignCategoryDataset dataset, StandardSpiderDataset jrDataset) {
		List<JRDesignCategorySeries> jrSeries = categorySeries(dataset);
		for (JRDesignCategorySeries jrSerie : jrSeries) {
			jrDataset.addCategorySeries(jrSerie);
		}
	}

	//plot
	private void plot(DRIDesignBasePlot plot, JRChartPlot jrPlot) {
		if (plot.getOrientation() != null) {
			jrPlot.setOrientation(ConstantTransform.chartPlotOrientation(plot.getOrientation()));
		}
		if (plot.getSeriesColors() != null) {
			List<JRBaseSeriesColor> colors = new ArrayList<JRBaseSeriesColor>();
			int i = 1;
			for (Color color : plot.getSeriesColors()) {
				colors.add(new JRBaseChartPlot.JRBaseSeriesColor(i++, color));
			}
			jrPlot.setSeriesColors(colors);
		}

		if (jrPlot instanceof JRDesignAreaPlot) {
			areaPlot((DRIDesignAxisPlot) plot, (JRDesignAreaPlot) jrPlot);
		}
		else if (jrPlot instanceof JRDesignBar3DPlot) {
			bar3DPlot((DRIDesignBar3DPlot) plot, (JRDesignBar3DPlot) jrPlot);
		}
		else if (jrPlot instanceof JRDesignBarPlot) {
			barPlot((DRIDesignBarPlot) plot, (JRDesignBarPlot) jrPlot);
		}
		else if (jrPlot instanceof JRDesignLinePlot) {
			linePlot((DRIDesignLinePlot) plot, (JRDesignLinePlot) jrPlot);
		}
		else if (jrPlot instanceof JRDesignPiePlot) {
			piePlot((DRIDesignPiePlot) plot, (JRDesignPiePlot) jrPlot);
		}
		else if (jrPlot instanceof JRDesignPie3DPlot) {
			pie3DPlot((DRIDesignPie3DPlot) plot, (JRDesignPie3DPlot) jrPlot);
		}
		else if (jrPlot instanceof JRDesignScatterPlot) {
			scatterPlot((DRIDesignLinePlot) plot, (JRDesignScatterPlot) jrPlot);
		}
		else if (jrPlot instanceof JRDesignTimeSeriesPlot) {
			timeSeriesPlot((DRIDesignLinePlot) plot, (JRDesignTimeSeriesPlot) jrPlot);
		}
		else {
			throw new JasperDesignException("Plot " + plot.getClass().getName() + " not supported");
		}
	}

	private void areaPlot(DRIDesignAxisPlot plot, JRDesignAreaPlot jrPlot) {
		//category
		DRIDesignAxisFormat categoryAxisFormat = plot.getXAxisFormat();
		jrPlot.setCategoryAxisLabelExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getLabelExpression()));
		jrPlot.setCategoryAxisTickLabelMask(categoryAxisFormat.getTickLabelMask());
		jrPlot.setCategoryAxisVerticalTickLabels(categoryAxisFormat.getVerticalTickLabels());
		jrPlot.setCategoryAxisTickLabelRotation(categoryAxisFormat.getTickLabelRotation());
		jrPlot.setCategoryAxisLabelColor(categoryAxisFormat.getLabelColor());
		if (categoryAxisFormat.getLabelFont() != null) {
			jrPlot.setCategoryAxisLabelFont(accessor.getStyleTransform().font(categoryAxisFormat.getLabelFont()));
		}
		jrPlot.setCategoryAxisLineColor(categoryAxisFormat.getLineColor());
		jrPlot.setCategoryAxisTickLabelColor(categoryAxisFormat.getTickLabelColor());
		if (categoryAxisFormat.getTickLabelFont() != null) {
			jrPlot.setCategoryAxisTickLabelFont(accessor.getStyleTransform().font(categoryAxisFormat.getTickLabelFont()));
		}
		jrPlot.setDomainAxisMinValueExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getRangeMinValueExpression()));
		jrPlot.setDomainAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getRangeMaxValueExpression()));

		//value
		DRIDesignAxisFormat valueAxisFormat = plot.getYAxisFormat();
		jrPlot.setValueAxisLabelExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getLabelExpression()));
		jrPlot.setValueAxisTickLabelMask(valueAxisFormat.getTickLabelMask());
		jrPlot.setValueAxisVerticalTickLabels(valueAxisFormat.getVerticalTickLabels());
		jrPlot.setValueAxisLabelColor(valueAxisFormat.getLabelColor());
		if (valueAxisFormat.getLabelFont() != null) {
			jrPlot.setValueAxisLabelFont(accessor.getStyleTransform().font(valueAxisFormat.getLabelFont()));
		}
		jrPlot.setValueAxisLineColor(valueAxisFormat.getLineColor());
		jrPlot.setValueAxisTickLabelColor(valueAxisFormat.getTickLabelColor());
		if (valueAxisFormat.getTickLabelFont() != null) {
			jrPlot.setValueAxisTickLabelFont(accessor.getStyleTransform().font(valueAxisFormat.getTickLabelFont()));
		}
		jrPlot.setRangeAxisMinValueExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getRangeMinValueExpression()));
		jrPlot.setRangeAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getRangeMaxValueExpression()));
	}

	private void bar3DPlot(DRIDesignBar3DPlot plot, JRDesignBar3DPlot jrPlot) {
		//category
		DRIDesignAxisFormat categoryAxisFormat = plot.getXAxisFormat();
		jrPlot.setCategoryAxisLabelExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getLabelExpression()));
		jrPlot.setCategoryAxisTickLabelMask(categoryAxisFormat.getTickLabelMask());
		jrPlot.setCategoryAxisVerticalTickLabels(categoryAxisFormat.getVerticalTickLabels());
		jrPlot.setCategoryAxisTickLabelRotation(categoryAxisFormat.getTickLabelRotation());
		jrPlot.setCategoryAxisLabelColor(categoryAxisFormat.getLabelColor());
		if (categoryAxisFormat.getLabelFont() != null) {
			jrPlot.setCategoryAxisLabelFont(accessor.getStyleTransform().font(categoryAxisFormat.getLabelFont()));
		}
		jrPlot.setCategoryAxisLineColor(categoryAxisFormat.getLineColor());
		jrPlot.setCategoryAxisTickLabelColor(categoryAxisFormat.getTickLabelColor());
		if (categoryAxisFormat.getTickLabelFont() != null) {
			jrPlot.setCategoryAxisTickLabelFont(accessor.getStyleTransform().font(categoryAxisFormat.getTickLabelFont()));
		}
		jrPlot.setDomainAxisMinValueExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getRangeMinValueExpression()));
		jrPlot.setDomainAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getRangeMaxValueExpression()));

		//value
		DRIDesignAxisFormat valueAxisFormat = plot.getYAxisFormat();
		jrPlot.setValueAxisLabelExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getLabelExpression()));
		jrPlot.setValueAxisTickLabelMask(valueAxisFormat.getTickLabelMask());
		jrPlot.setValueAxisVerticalTickLabels(valueAxisFormat.getVerticalTickLabels());
		jrPlot.setValueAxisLabelColor(valueAxisFormat.getLabelColor());
		if (valueAxisFormat.getLabelFont() != null) {
			jrPlot.setValueAxisLabelFont(accessor.getStyleTransform().font(valueAxisFormat.getLabelFont()));
		}
		jrPlot.setValueAxisLineColor(valueAxisFormat.getLineColor());
		jrPlot.setValueAxisTickLabelColor(valueAxisFormat.getTickLabelColor());
		if (valueAxisFormat.getTickLabelFont() != null) {
			jrPlot.setValueAxisTickLabelFont(accessor.getStyleTransform().font(valueAxisFormat.getTickLabelFont()));
		}
		jrPlot.setRangeAxisMinValueExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getRangeMinValueExpression()));
		jrPlot.setRangeAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getRangeMaxValueExpression()));

		jrPlot.setShowLabels(plot.getShowLabels());
		jrPlot.setXOffset(plot.getXOffset());
		jrPlot.setYOffset(plot.getYOffset());
	}

	private void barPlot(DRIDesignBarPlot plot, JRDesignBarPlot jrPlot) {
		//category
		DRIDesignAxisFormat categoryAxisFormat = plot.getXAxisFormat();
		jrPlot.setCategoryAxisLabelExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getLabelExpression()));
		jrPlot.setCategoryAxisTickLabelMask(categoryAxisFormat.getTickLabelMask());
		jrPlot.setCategoryAxisVerticalTickLabels(categoryAxisFormat.getVerticalTickLabels());
		jrPlot.setCategoryAxisTickLabelRotation(categoryAxisFormat.getTickLabelRotation());
		jrPlot.setCategoryAxisLabelColor(categoryAxisFormat.getLabelColor());
		if (categoryAxisFormat.getLabelFont() != null) {
			jrPlot.setCategoryAxisLabelFont(accessor.getStyleTransform().font(categoryAxisFormat.getLabelFont()));
		}
		jrPlot.setCategoryAxisLineColor(categoryAxisFormat.getLineColor());
		jrPlot.setCategoryAxisTickLabelColor(categoryAxisFormat.getTickLabelColor());
		if (categoryAxisFormat.getTickLabelFont() != null) {
			jrPlot.setCategoryAxisTickLabelFont(accessor.getStyleTransform().font(categoryAxisFormat.getTickLabelFont()));
		}
		jrPlot.setDomainAxisMinValueExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getRangeMinValueExpression()));
		jrPlot.setDomainAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getRangeMaxValueExpression()));

		//value
		DRIDesignAxisFormat valueAxisFormat = plot.getYAxisFormat();
		jrPlot.setValueAxisLabelExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getLabelExpression()));
		jrPlot.setValueAxisTickLabelMask(valueAxisFormat.getTickLabelMask());
		jrPlot.setValueAxisVerticalTickLabels(valueAxisFormat.getVerticalTickLabels());
		jrPlot.setValueAxisLabelColor(valueAxisFormat.getLabelColor());
		if (valueAxisFormat.getLabelFont() != null) {
			jrPlot.setValueAxisLabelFont(accessor.getStyleTransform().font(valueAxisFormat.getLabelFont()));
		}
		jrPlot.setValueAxisLineColor(valueAxisFormat.getLineColor());
		jrPlot.setValueAxisTickLabelColor(valueAxisFormat.getTickLabelColor());
		if (valueAxisFormat.getTickLabelFont() != null) {
			jrPlot.setValueAxisTickLabelFont(accessor.getStyleTransform().font(valueAxisFormat.getTickLabelFont()));
		}
		jrPlot.setRangeAxisMinValueExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getRangeMinValueExpression()));
		jrPlot.setRangeAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getRangeMaxValueExpression()));

		jrPlot.setShowLabels(plot.getShowLabels());
		jrPlot.setShowTickLabels(plot.getShowTickLabels());
		jrPlot.setShowTickMarks(plot.getShowTickMarks());
	}

	private void linePlot(DRIDesignLinePlot plot, JRDesignLinePlot jrPlot) {
		//category
		DRIDesignAxisFormat categoryAxisFormat = plot.getXAxisFormat();
		jrPlot.setCategoryAxisLabelExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getLabelExpression()));
		jrPlot.setCategoryAxisTickLabelMask(categoryAxisFormat.getTickLabelMask());
		jrPlot.setCategoryAxisVerticalTickLabels(categoryAxisFormat.getVerticalTickLabels());
		jrPlot.setCategoryAxisTickLabelRotation(categoryAxisFormat.getTickLabelRotation());
		jrPlot.setCategoryAxisLabelColor(categoryAxisFormat.getLabelColor());
		if (categoryAxisFormat.getLabelFont() != null) {
			jrPlot.setCategoryAxisLabelFont(accessor.getStyleTransform().font(categoryAxisFormat.getLabelFont()));
		}
		jrPlot.setCategoryAxisLineColor(categoryAxisFormat.getLineColor());
		jrPlot.setCategoryAxisTickLabelColor(categoryAxisFormat.getTickLabelColor());
		if (categoryAxisFormat.getTickLabelFont() != null) {
			jrPlot.setCategoryAxisTickLabelFont(accessor.getStyleTransform().font(categoryAxisFormat.getTickLabelFont()));
		}
		jrPlot.setDomainAxisMinValueExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getRangeMinValueExpression()));
		jrPlot.setDomainAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getRangeMaxValueExpression()));

		//value
		DRIDesignAxisFormat valueAxisFormat = plot.getYAxisFormat();
		jrPlot.setValueAxisLabelExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getLabelExpression()));
		jrPlot.setValueAxisTickLabelMask(valueAxisFormat.getTickLabelMask());
		jrPlot.setValueAxisVerticalTickLabels(valueAxisFormat.getVerticalTickLabels());
		jrPlot.setValueAxisLabelColor(valueAxisFormat.getLabelColor());
		if (valueAxisFormat.getLabelFont() != null) {
			jrPlot.setValueAxisLabelFont(accessor.getStyleTransform().font(valueAxisFormat.getLabelFont()));
		}
		jrPlot.setValueAxisLineColor(valueAxisFormat.getLineColor());
		jrPlot.setValueAxisTickLabelColor(valueAxisFormat.getTickLabelColor());
		if (valueAxisFormat.getTickLabelFont() != null) {
			jrPlot.setValueAxisTickLabelFont(accessor.getStyleTransform().font(valueAxisFormat.getTickLabelFont()));
		}
		jrPlot.setRangeAxisMinValueExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getRangeMinValueExpression()));
		jrPlot.setRangeAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getRangeMaxValueExpression()));

		jrPlot.setShowShapes(plot.getShowShapes());
		jrPlot.setShowLines(plot.getShowLines());
	}

	private void piePlot(DRIDesignPiePlot plot, JRDesignPiePlot jrPlot) {
		jrPlot.setCircular(plot.getCircular());
		jrPlot.setLabelFormat(plot.getLabelFormat());
		jrPlot.setLegendLabelFormat(plot.getLegendLabelFormat());
	}

	private void pie3DPlot(DRIDesignPie3DPlot plot, JRDesignPie3DPlot jrPlot) {
		jrPlot.setCircular(plot.getCircular());
		jrPlot.setLabelFormat(plot.getLabelFormat());
		jrPlot.setLegendLabelFormat(plot.getLegendLabelFormat());
		jrPlot.setDepthFactor(plot.getDepthFactor());
	}

	private void scatterPlot(DRIDesignLinePlot plot, JRDesignScatterPlot jrPlot) {
		//x
		DRIDesignAxisFormat xAxisFormat = plot.getXAxisFormat();
		jrPlot.setXAxisLabelExpression(accessor.getExpressionTransform().getExpression(xAxisFormat.getLabelExpression()));
		jrPlot.setXAxisTickLabelMask(xAxisFormat.getTickLabelMask());
		jrPlot.setXAxisVerticalTickLabels(xAxisFormat.getVerticalTickLabels());
		jrPlot.setXAxisLabelColor(xAxisFormat.getLabelColor());
		if (xAxisFormat.getLabelFont() != null) {
			jrPlot.setXAxisLabelFont(accessor.getStyleTransform().font(xAxisFormat.getLabelFont()));
		}
		jrPlot.setXAxisLineColor(xAxisFormat.getLineColor());
		jrPlot.setXAxisTickLabelColor(xAxisFormat.getTickLabelColor());
		if (xAxisFormat.getTickLabelFont() != null) {
			jrPlot.setXAxisTickLabelFont(accessor.getStyleTransform().font(xAxisFormat.getTickLabelFont()));
		}
		jrPlot.setDomainAxisMinValueExpression(accessor.getExpressionTransform().getExpression(xAxisFormat.getRangeMinValueExpression()));
		jrPlot.setDomainAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(xAxisFormat.getRangeMaxValueExpression()));

		//y
		DRIDesignAxisFormat yAxisFormat = plot.getYAxisFormat();
		jrPlot.setYAxisLabelExpression(accessor.getExpressionTransform().getExpression(yAxisFormat.getLabelExpression()));
		jrPlot.setYAxisTickLabelMask(yAxisFormat.getTickLabelMask());
		jrPlot.setYAxisVerticalTickLabels(yAxisFormat.getVerticalTickLabels());
		jrPlot.setYAxisLabelColor(yAxisFormat.getLabelColor());
		if (yAxisFormat.getLabelFont() != null) {
			jrPlot.setYAxisLabelFont(accessor.getStyleTransform().font(yAxisFormat.getLabelFont()));
		}
		jrPlot.setYAxisLineColor(yAxisFormat.getLineColor());
		jrPlot.setYAxisTickLabelColor(yAxisFormat.getTickLabelColor());
		if (yAxisFormat.getTickLabelFont() != null) {
			jrPlot.setYAxisTickLabelFont(accessor.getStyleTransform().font(yAxisFormat.getTickLabelFont()));
		}
		jrPlot.setRangeAxisMinValueExpression(accessor.getExpressionTransform().getExpression(yAxisFormat.getRangeMinValueExpression()));
		jrPlot.setRangeAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(yAxisFormat.getRangeMaxValueExpression()));

		jrPlot.setShowShapes(plot.getShowShapes());
		jrPlot.setShowLines(plot.getShowLines());
	}

	private void timeSeriesPlot(DRIDesignLinePlot plot, JRDesignTimeSeriesPlot jrPlot) {
		//time
		DRIDesignAxisFormat categoryAxisFormat = plot.getXAxisFormat();
		jrPlot.setTimeAxisLabelExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getLabelExpression()));
		jrPlot.setTimeAxisTickLabelMask(categoryAxisFormat.getTickLabelMask());
		jrPlot.setTimeAxisVerticalTickLabels(categoryAxisFormat.getVerticalTickLabels());
		jrPlot.setTimeAxisLabelColor(categoryAxisFormat.getLabelColor());
		if (categoryAxisFormat.getLabelFont() != null) {
			jrPlot.setTimeAxisLabelFont(accessor.getStyleTransform().font(categoryAxisFormat.getLabelFont()));
		}
		jrPlot.setTimeAxisLineColor(categoryAxisFormat.getLineColor());
		jrPlot.setTimeAxisTickLabelColor(categoryAxisFormat.getTickLabelColor());
		if (categoryAxisFormat.getTickLabelFont() != null) {
			jrPlot.setTimeAxisTickLabelFont(accessor.getStyleTransform().font(categoryAxisFormat.getTickLabelFont()));
		}
		jrPlot.setDomainAxisMinValueExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getRangeMinValueExpression()));
		jrPlot.setDomainAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(categoryAxisFormat.getRangeMaxValueExpression()));

		//value
		DRIDesignAxisFormat valueAxisFormat = plot.getYAxisFormat();
		jrPlot.setValueAxisLabelExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getLabelExpression()));
		jrPlot.setValueAxisTickLabelMask(valueAxisFormat.getTickLabelMask());
		jrPlot.setValueAxisVerticalTickLabels(valueAxisFormat.getVerticalTickLabels());
		jrPlot.setValueAxisLabelColor(valueAxisFormat.getLabelColor());
		if (valueAxisFormat.getLabelFont() != null) {
			jrPlot.setValueAxisLabelFont(accessor.getStyleTransform().font(valueAxisFormat.getLabelFont()));
		}
		jrPlot.setValueAxisLineColor(valueAxisFormat.getLineColor());
		jrPlot.setValueAxisTickLabelColor(valueAxisFormat.getTickLabelColor());
		if (valueAxisFormat.getTickLabelFont() != null) {
			jrPlot.setValueAxisTickLabelFont(accessor.getStyleTransform().font(valueAxisFormat.getTickLabelFont()));
		}
		jrPlot.setRangeAxisMinValueExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getRangeMinValueExpression()));
		jrPlot.setRangeAxisMaxValueExpression(accessor.getExpressionTransform().getExpression(valueAxisFormat.getRangeMaxValueExpression()));

		jrPlot.setShowShapes(plot.getShowShapes());
		jrPlot.setShowLines(plot.getShowLines());
	}

	//spider
	private ChartSettings spiderSettings(DRIDesignChart chart) {
		StandardChartSettings settings = new StandardChartSettings();

		DRIDesignHyperLink hyperLink = chart.getHyperLink();
		if (hyperLink != null) {
			settings.setAnchorNameExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorNameExpression()));
			settings.setHyperlinkAnchorExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorExpression()));
			settings.setHyperlinkPageExpression(accessor.getExpressionTransform().getExpression(hyperLink.getPageExpression()));
			settings.setHyperlinkReferenceExpression(accessor.getExpressionTransform().getExpression(hyperLink.getReferenceExpression()));
			settings.setHyperlinkTooltipExpression(accessor.getExpressionTransform().getExpression(hyperLink.getTooltipExpression()));
			HyperlinkTypeEnum hyperLinkType = ConstantTransform.hyperLinkType(hyperLink.getType());
			if (hyperLinkType != null) {
				settings.setLinkType(JRHyperlinkHelper.getLinkType(hyperLinkType));
			}
		}

		DRIDesignChartTitle title = chart.getTitle();
		settings.setTitleColor(title.getColor());
		if (title.getFont() != null)
			settings.setTitleFont(accessor.getStyleTransform().font(title.getFont()));
		settings.setTitleExpression(accessor.getExpressionTransform().getExpression(title.getTitle()));
		settings.setTitlePosition(ConstantTransform.chartPosition(title.getPosition()));

		DRIDesignChartSubtitle subtitle = chart.getSubtitle();
		settings.setSubtitleColor(subtitle.getColor());
		if (subtitle.getFont() != null)
			settings.setSubtitleFont(accessor.getStyleTransform().font(subtitle.getFont()));
		settings.setSubtitleExpression(accessor.getExpressionTransform().getExpression(subtitle.getTitle()));

		DRIDesignChartLegend legend = chart.getLegend();
		settings.setLegendColor(legend.getColor());
		settings.setLegendBackgroundColor(legend.getBackgroundColor());
		settings.setShowLegend(legend.getShowLegend());
		if (legend.getFont() != null)
			settings.setLegendFont(accessor.getStyleTransform().font(legend.getFont()));
		settings.setLegendPosition(ConstantTransform.chartPosition(legend.getPosition()));

		if (!chart.getCustomizers().isEmpty()) {
			settings.setCustomizerClass(JasperChartCustomizer.class.getName());
			addChartCustomizer(chart.getUniqueName(), chart.getCustomizers());
		}

		return settings;
	}

	private void spiderPlot(DRIDesignSpiderPlot plot, StandardSpiderPlot jrPlot) {
		jrPlot.setMaxValueExpression(accessor.getExpressionTransform().getExpression(plot.getMaxValueExpression()));
		jrPlot.setRotation(ConstantTransform.spiderRotation(plot.getRotation()));
		jrPlot.setTableOrder(ConstantTransform.tableOrder(plot.getTableOrder()));
		jrPlot.setWebFilled(plot.getWebFilled());
		jrPlot.setStartAngle(plot.getStartAngle());
		jrPlot.setHeadPercent(plot.getHeadPercent());
		jrPlot.setInteriorGap(plot.getInteriorGap());
		jrPlot.setAxisLineColor(plot.getAxisLineColor());
		jrPlot.setAxisLineWidth(plot.getAxisLineWidth());
		if (plot.getLabelFont() != null) {
			jrPlot.setLabelFont(accessor.getStyleTransform().font(plot.getLabelFont()));
		}
		jrPlot.setLabelGap(plot.getLabelGap());
		jrPlot.setLabelColor(plot.getLabelColor());
	}
}
