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
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignLinePlot;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignPie3DPlot;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignPiePlot;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignPlot;
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
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.base.JRBaseChartPlot;
import net.sf.jasperreports.engine.base.JRBaseChartPlot.JRBaseSeriesColor;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
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
		JRDesignChart jrChart = new JRDesignChart(new JRDesignStyle().getDefaultStyleProvider(), ConstantTransform.chartType(chart.getChartType()));
		EvaluationTime evaluationTime = chart.getEvaluationTime();
		jrChart.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && chart.getEvaluationGroup() != null) {
			jrChart.setEvaluationGroup(accessor.getGroupTransform().getGroup(chart.getEvaluationGroup()));
		}

		if (chart.getChartType().equals(ChartType.XYBAR)) {
			jrChart.setDataset(new JRDesignXyDataset(null));
		}

		chart(chart, jrChart);
		dataset(chart.getDataset(), (JRDesignChartDataset) jrChart.getDataset());
		plot(chart.getPlot(), jrChart.getPlot());
		return jrChart;
	}

	private void chart(DRIDesignChart chart, JRDesignChart jrChart) {
		DRIDesignHyperLink hyperLink = chart.getHyperLink();
		if (hyperLink != null) {
			jrChart.setHyperlinkReferenceExpression(accessor.getExpressionTransform().getExpression(hyperLink.getLinkExpression()));
			jrChart.setHyperlinkType(HyperlinkTypeEnum.REFERENCE);
			jrChart.setHyperlinkTooltipExpression(accessor.getExpressionTransform().getExpression(hyperLink.getTooltipExpression()));
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

		if (chart.getCustomizer() != null) {
			jrChart.setCustomizerClass(JasperChartCustomizer.class.getName());
			addChartCustomizer(chart.getUniqueName(), chart.getCustomizer());
		}
	}

	private void addChartCustomizer(String name, DRIChartCustomizer chartCustomizer) {
		accessor.getCustomValues().addChartCustomizer(name, chartCustomizer);
	}

	//dataset
	private void dataset(DRIDesignChartDataset dataset, JRDesignChartDataset jrDataset) {
		ResetType resetType = dataset.getResetType();
		jrDataset.setResetType(ConstantTransform.variableResetType(resetType));
		if (resetType.equals(ResetType.GROUP) && dataset.getResetGroup() != null) {
			jrDataset.setResetGroup(accessor.getGroupTransform().getGroup(dataset.getResetGroup()));
		}

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
		else {
			throw new JasperDesignException("Dataset " + dataset.getClass().getName() + " not supported");
		}
	}

	private void categoryDataset(DRIDesignCategoryDataset dataset, JRDesignCategoryDataset jrDataset) {
		JRDesignExpression exp1 = accessor.getExpressionTransform().getExpression(dataset.getValueExpression());
		for (DRIDesignChartSerie serie : dataset.getSeries()) {
			JRDesignCategorySeries jrSerie = new JRDesignCategorySeries();
			jrSerie.setValueExpression(accessor.getExpressionTransform().getExpression(serie.getValueExpression()));

			JRDesignExpression exp2 = accessor.getExpressionTransform().getExpression(serie.getLabelExpression());
			JRDesignExpression seriesExpression = accessor.getExpressionTransform().getExpression(serie.getSeriesExpression());
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
			jrDataset.addCategorySeries(jrSerie);
		}
	}

	private void pieDataset(DRIDesignChartDataset dataset, JRDesignPieDataset jrDataset) {
		JRDesignExpression exp1 = accessor.getExpressionTransform().getExpression(dataset.getValueExpression());
		for (DRIDesignChartSerie serie : dataset.getSeries()) {
			JRDesignPieSeries jrSerie = new JRDesignPieSeries();
			jrSerie.setKeyExpression(exp1);
			jrSerie.setValueExpression(accessor.getExpressionTransform().getExpression(serie.getValueExpression()));
			jrDataset.addPieSeries(jrSerie);
		}
	}

	private void timeSeriesDataset(DRIDesignTimeSeriesDataset dataset, JRDesignTimeSeriesDataset jrDataset) {
		if (dataset.getTimePeriodType() != null) {
			jrDataset.setTimePeriod(ConstantTransform.timePeriodType(dataset.getTimePeriodType()));
		}

		JRDesignExpression exp1 = accessor.getExpressionTransform().getExpression(dataset.getValueExpression());
		for (DRIDesignChartSerie serie : dataset.getSeries()) {
			JRDesignTimeSeries jrSerie = new JRDesignTimeSeries();
			jrSerie.setTimePeriodExpression(exp1);
			jrSerie.setValueExpression(accessor.getExpressionTransform().getExpression(serie.getValueExpression()));
			JRDesignExpression seriesExpression = accessor.getExpressionTransform().getExpression(serie.getSeriesExpression());
			JRDesignExpression labelExpression = accessor.getExpressionTransform().getExpression(serie.getLabelExpression());
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
		JRDesignExpression exp1 = accessor.getExpressionTransform().getExpression(dataset.getValueExpression());
		for (DRIDesignChartSerie serie : dataset.getSeries()) {
			JRDesignXySeries jrSerie = new JRDesignXySeries();
			jrSerie.setXValueExpression(exp1);
			jrSerie.setYValueExpression(accessor.getExpressionTransform().getExpression(serie.getValueExpression()));
			JRDesignExpression seriesExpression = accessor.getExpressionTransform().getExpression(serie.getSeriesExpression());
			JRDesignExpression labelExpression = accessor.getExpressionTransform().getExpression(serie.getLabelExpression());
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

	//plot
	private void plot(DRIDesignPlot plot, JRChartPlot jrPlot) {
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
}
