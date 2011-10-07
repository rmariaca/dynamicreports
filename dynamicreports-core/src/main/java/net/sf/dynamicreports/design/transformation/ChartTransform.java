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

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.chart.DRDesignChart;
import net.sf.dynamicreports.design.base.chart.DRDesignChartLegend;
import net.sf.dynamicreports.design.base.chart.DRDesignChartSubtitle;
import net.sf.dynamicreports.design.base.chart.DRDesignChartTitle;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignCategoryDataset;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignChartDataset;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignChartSerie;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignTimeSeriesDataset;
import net.sf.dynamicreports.design.base.chart.plot.AbstractDesignBasePlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignAxisFormat;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignAxisPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignBar3DPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignBarPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignChartAxis;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignLinePlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignMultiAxisPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignPie3DPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignPiePlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignSpiderPlot;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.DRIDesignVariable;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignPlot;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIDataset;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChart;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import net.sf.dynamicreports.report.definition.chart.DRIChartLegend;
import net.sf.dynamicreports.report.definition.chart.DRIChartSubtitle;
import net.sf.dynamicreports.report.definition.chart.DRIChartTitle;
import net.sf.dynamicreports.report.definition.chart.dataset.DRICategoryDataset;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIChartDataset;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRITimeSeriesDataset;
import net.sf.dynamicreports.report.definition.chart.plot.DRIAxisFormat;
import net.sf.dynamicreports.report.definition.chart.plot.DRIAxisPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIBar3DPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIBarPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIBasePlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIChartAxis;
import net.sf.dynamicreports.report.definition.chart.plot.DRILinePlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIMultiAxisPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIPie3DPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIPiePlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRISpiderPlot;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.exception.DRException;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ChartTransform {
	private DesignTransformAccessor accessor;

	public ChartTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
	}

	//chart
	protected DRDesignChart transform(DRIChart chart, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignChart designChart = new DRDesignChart();
		designChart.setWidth(accessor.getTemplateTransform().getChartWidth(chart));
		designChart.setHeight(accessor.getTemplateTransform().getChartHeight(chart));
		designChart.setChartType(chart.getChartType());
		designChart.setDataset(dataset(chart.getDataset(), resetType, resetGroup));
		designChart.setPlot(plot(chart.getPlot(), designChart.getCustomizers(), resetType, resetGroup));
		designChart.setTitle(title(chart.getTitle()));
		designChart.setSubtitle(subtitle(chart.getSubtitle()));
		designChart.setLegend(legend(chart.getLegend()));

		if (chart.getCustomizer() != null) {
			designChart.getCustomizers().add(chart.getCustomizer());
		}

		return designChart;
	}

	//plot
	private DRIDesignPlot plot(DRIPlot plot, List<DRIChartCustomizer> chartCustomizers, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRIDesignPlot designPlot;

		if (plot instanceof DRIBar3DPlot) {
			designPlot = bar3DPlot((DRIBar3DPlot) plot);
		}
		else if (plot instanceof DRIBarPlot) {
			designPlot = barPlot((DRIBarPlot) plot);
		}
		else if (plot instanceof DRILinePlot) {
			designPlot = linePlot((DRILinePlot) plot);
		}
		else if (plot instanceof DRIMultiAxisPlot) {
			designPlot = multiAxisPlot((DRIMultiAxisPlot) plot, resetType, resetGroup);
		}
		else if (plot instanceof DRIPie3DPlot) {
			designPlot = pie3DPlot((DRIPie3DPlot) plot, chartCustomizers);
		}
		else if (plot instanceof DRIPiePlot) {
			designPlot = piePlot((DRIPiePlot) plot, chartCustomizers);
		}
		else if (plot instanceof DRISpiderPlot) {
			designPlot = spiderPlot((DRISpiderPlot) plot);
		}
		else if (plot instanceof DRIAxisPlot) {
			designPlot = axisPlot((DRIAxisPlot) plot);
		}
		else {
			throw new DRDesignReportException("Chart plot " + plot.getClass().getName() + " not supported");
		}

		if (plot instanceof DRIBasePlot) {
			AbstractDesignBasePlot designBasePlot = ((AbstractDesignBasePlot) designPlot);
			DRIBasePlot basePlot = (DRIBasePlot) plot;
			designBasePlot.setOrientation(basePlot.getOrientation());
			designBasePlot.setSeriesColors(accessor.getTemplateTransform().getChartSeriesColors(basePlot));
		}

		return designPlot;
	}

	private DRDesignBar3DPlot bar3DPlot(DRIBar3DPlot bar3DPlot) throws DRException {
		DRDesignBar3DPlot designBar3DPlot = new DRDesignBar3DPlot();
		axisPlot(designBar3DPlot, bar3DPlot);
		designBar3DPlot.setXOffset(bar3DPlot.getXOffset());
		designBar3DPlot.setYOffset(bar3DPlot.getYOffset());
		designBar3DPlot.setShowLabels(bar3DPlot.getShowLabels());
		return designBar3DPlot;
	}

	private DRDesignBarPlot barPlot(DRIBarPlot barPlot) throws DRException {
		DRDesignBarPlot designBarPlot = new DRDesignBarPlot();
		axisPlot(designBarPlot, barPlot);
		designBarPlot.setShowTickMarks(barPlot.getShowTickMarks());
		designBarPlot.setShowTickLabels(barPlot.getShowTickLabels());
		designBarPlot.setShowLabels(barPlot.getShowLabels());
		return designBarPlot;
	}

	private DRDesignLinePlot linePlot(DRILinePlot linePlot) throws DRException {
		DRDesignLinePlot designLinePlot = new DRDesignLinePlot();
		axisPlot(designLinePlot, linePlot);
		designLinePlot.setShowShapes(linePlot.getShowShapes());
		designLinePlot.setShowLines(linePlot.getShowLines());
		return designLinePlot;
	}

	private DRDesignMultiAxisPlot multiAxisPlot(DRIMultiAxisPlot multiAxisPlot, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignMultiAxisPlot designMultiAxisPlot = new DRDesignMultiAxisPlot();
		axisPlot(designMultiAxisPlot, multiAxisPlot);
		for (DRIChartAxis axis : multiAxisPlot.getAxes()) {
			DRDesignChartAxis designAxis = new DRDesignChartAxis();
			designAxis.setPosition(axis.getPosition());
			designAxis.setChart(transform(axis.getChart(), resetType, resetGroup));
			designMultiAxisPlot.getAxes().add(designAxis);
		}
		return designMultiAxisPlot;
	}

	private DRDesignPie3DPlot pie3DPlot(DRIPie3DPlot pie3DPlot, List<DRIChartCustomizer> chartCustomizers) {
		DRDesignPie3DPlot designPie3DPlot = new DRDesignPie3DPlot();
		piePlot(designPie3DPlot, pie3DPlot, chartCustomizers);
		designPie3DPlot.setDepthFactor(pie3DPlot.getDepthFactor());
		return designPie3DPlot;
	}

	private DRDesignPiePlot piePlot(DRIPiePlot piePlot, List<DRIChartCustomizer> chartCustomizers) {
		DRDesignPiePlot designPiePlot = new DRDesignPiePlot();
		piePlot(designPiePlot, piePlot, chartCustomizers);
		return designPiePlot;
	}

	private void piePlot(DRDesignPiePlot designPiePlot, DRIPiePlot piePlot, List<DRIChartCustomizer> chartCustomizers) {
		designPiePlot.setCircular(piePlot.getCircular());
		designPiePlot.setLabelFormat(piePlot.getLabelFormat());
		designPiePlot.setLegendLabelFormat(piePlot.getLegendLabelFormat());
		if (piePlot.getShowLabels() != null && !piePlot.getShowLabels()) {
			chartCustomizers.add(new PieChartShowLabelsCustomizer());
		}
	}

	private DRDesignSpiderPlot spiderPlot(DRISpiderPlot spiderPlot) throws DRException {
		DRDesignSpiderPlot designSpiderPlot = new DRDesignSpiderPlot();
		designSpiderPlot.setMaxValueExpression(accessor.getExpressionTransform().transformExpression(spiderPlot.getMaxValueExpression()));
		designSpiderPlot.setRotation(spiderPlot.getRotation());
		designSpiderPlot.setTableOrder(spiderPlot.getTableOrder());
		designSpiderPlot.setWebFilled(spiderPlot.getWebFilled());
		designSpiderPlot.setStartAngle(spiderPlot.getStartAngle());
		designSpiderPlot.setHeadPercent(spiderPlot.getHeadPercent());
		designSpiderPlot.setInteriorGap(spiderPlot.getInteriorGap());
		designSpiderPlot.setAxisLineColor(spiderPlot.getAxisLineColor());
		designSpiderPlot.setAxisLineWidth(spiderPlot.getAxisLineWidth());
		designSpiderPlot.setLabelFont(accessor.getStyleTransform().transformFont(spiderPlot.getLabelFont()));
		designSpiderPlot.setLabelGap(spiderPlot.getLabelGap());
		designSpiderPlot.setLabelColor(spiderPlot.getLabelColor());
		return designSpiderPlot;
	}

	private DRDesignAxisPlot axisPlot(DRIAxisPlot axisPlot) throws DRException {
		DRDesignAxisPlot designAxisPlot = new DRDesignAxisPlot();
		axisPlot(designAxisPlot, axisPlot);
		return designAxisPlot;
	}

	private void axisPlot(DRDesignAxisPlot designAxisPlot, DRIAxisPlot axisPlot) throws DRException {
		designAxisPlot.setXAxisFormat(axisFormat(axisPlot.getXAxisFormat()));
		designAxisPlot.setYAxisFormat(axisFormat(axisPlot.getYAxisFormat()));
	}

	//axis format
	private DRDesignAxisFormat axisFormat(DRIAxisFormat axisFormat) throws DRException {
		DRDesignAxisFormat designAxisFormat = new DRDesignAxisFormat();
		designAxisFormat.setLabelExpression(accessor.getExpressionTransform().transformExpression(axisFormat.getLabelExpression()));
		designAxisFormat.setLabelFont(accessor.getStyleTransform().transformFont(axisFormat.getLabelFont()));
		designAxisFormat.setLabelColor(axisFormat.getLabelColor());
		designAxisFormat.setTickLabelFont(accessor.getStyleTransform().transformFont(axisFormat.getTickLabelFont()));
		designAxisFormat.setTickLabelColor(axisFormat.getTickLabelColor());
		designAxisFormat.setTickLabelMask(axisFormat.getTickLabelMask());
		designAxisFormat.setVerticalTickLabels(axisFormat.getVerticalTickLabels());
		designAxisFormat.setTickLabelRotation(axisFormat.getTickLabelRotation());
		designAxisFormat.setLineColor(axisFormat.getLineColor());
		designAxisFormat.setRangeMinValueExpression(accessor.getExpressionTransform().transformExpression(axisFormat.getRangeMinValueExpression()));
		designAxisFormat.setRangeMaxValueExpression(accessor.getExpressionTransform().transformExpression(axisFormat.getRangeMaxValueExpression()));
		return designAxisFormat;
	}

	//title
	private DRDesignChartTitle title(DRIChartTitle title) throws DRException {
		DRDesignChartTitle designTitle = new DRDesignChartTitle();
		subtitle(designTitle, title);
		designTitle.setPosition(title.getPosition());
		return designTitle;
	}

	//subtitle
	private DRDesignChartSubtitle subtitle(DRIChartSubtitle subtitle) throws DRException {
		DRDesignChartSubtitle designSubtitle = new DRDesignChartSubtitle();
		subtitle(designSubtitle, subtitle);
		return designSubtitle;
	}

	private void subtitle(DRDesignChartSubtitle designSubtitle, DRIChartSubtitle subtitle) throws DRException {
		designSubtitle.setColor(subtitle.getColor());
		designSubtitle.setFont(accessor.getStyleTransform().transformFont(subtitle.getFont()));
		designSubtitle.setTitle(accessor.getExpressionTransform().transformExpression(subtitle.getTitle()));
	}

	//legend
	private DRDesignChartLegend legend(DRIChartLegend legend) {
		DRDesignChartLegend designLegend = new DRDesignChartLegend();
		designLegend.setColor(legend.getColor());
		designLegend.setBackgroundColor(legend.getBackgroundColor());
		designLegend.setShowLegend(legend.getShowLegend());
		designLegend.setFont(accessor.getStyleTransform().transformFont(legend.getFont()));
		designLegend.setPosition(legend.getPosition());
		return designLegend;
	}

	//dataset
	private DRDesignChartDataset dataset(DRIChartDataset dataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		if (dataset == null) {
			return null;
		}

		DRDesignChartDataset designDataset;
		if (dataset instanceof DRICategoryDataset) {
			designDataset = categoryDataset((DRICategoryDataset) dataset);
		}
		else if (dataset instanceof DRITimeSeriesDataset) {
			designDataset = timeSeriesDataset((DRITimeSeriesDataset) dataset);
		}
		else {
			designDataset = new DRDesignChartDataset();
		}

		designDataset.setSubDataset(accessor.getDatasetTransform().transform(dataset.getSubDataset()));
		accessor.transformToDataset(dataset.getSubDataset());
		DRIDesignExpression valueExpression = accessor.getExpressionTransform().transformExpression(dataset.getValueExpression());
		designDataset.setValueExpression(valueExpression);
		int index = 0;
		for (DRIChartSerie serie : dataset.getSeries()) {
			designDataset.addSerie(serie(dataset.getSubDataset(), serie, valueExpression, resetType, resetGroup, index++));
		}
		designDataset.setResetType(resetType);
		designDataset.setResetGroup(resetGroup);
		accessor.transformToMainDataset();

		return designDataset;
	}

	private DRDesignCategoryDataset categoryDataset(DRICategoryDataset dataset) {
		DRDesignCategoryDataset designDataset = new DRDesignCategoryDataset();
		designDataset.setUseSeriesAsCategory(accessor.getTemplateTransform().isChartCategoryDatasetUseSeriesAsCategory(dataset));
		return designDataset;
	}

	private DRDesignTimeSeriesDataset timeSeriesDataset(DRITimeSeriesDataset dataset) {
		DRDesignTimeSeriesDataset designDataset = new DRDesignTimeSeriesDataset();
		designDataset.setTimePeriodType(accessor.getTemplateTransform().getChartTimeSeriesDatasetTimePeriodType(dataset));
		return designDataset;
	}

	//serie
	private DRDesignChartSerie serie(DRIDataset dataset, DRIChartSerie serie, DRIDesignExpression valueExpression, ResetType resetType, DRDesignGroup resetGroup, int index) throws DRException {
		DRDesignChartSerie designSerie = new DRDesignChartSerie();

		AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
		DRIDesignExpression seriesExpression = expressionTransform.transformExpression(serie.getSeriesExpression());
		designSerie.setSeriesExpression(seriesExpression);
		DRIDesignExpression serieValueExpression = expressionTransform.transformExpression(serie.getValueExpression());
		if (serieValueExpression instanceof DRIDesignVariable) {
			designSerie.setValueExpression(serieValueExpression);
		}
		else {
			if (seriesExpression == null) {
				designSerie.setValueExpression(expressionTransform.transformExpression(new SerieValueExpression(valueExpression, serieValueExpression, resetType, resetGroup, null)));
			}
			else {
				designSerie.setValueExpression(expressionTransform.transformExpression(new SerieValueExpression(valueExpression, serieValueExpression, resetType, resetGroup, seriesExpression.getName())));
			}
		}
		DRIExpression<?> labelExpression = serie.getLabelExpression();
		if (labelExpression == null) {
			labelExpression = Expressions.text("serie" + index);
		}
		designSerie.setLabelExpression(expressionTransform.transformExpression(labelExpression));

		return designSerie;
	}

	private class SerieValueExpression extends AbstractSimpleExpression<Number> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		private DRIDesignExpression valueExpression;
		private DRIDesignExpression serieExpression;
		private ResetType resetType;
		private DRDesignGroup resetGroup;
		private String key;
		private Object resetValue;
		private Map<Object, Double> values;

		public SerieValueExpression(DRIDesignExpression valueExpression, DRIDesignExpression serieExpression, ResetType resetType, DRDesignGroup resetGroup, String key) {
			this.valueExpression = valueExpression;
			this.serieExpression = serieExpression;
			this.resetType = resetType;
			this.resetGroup = resetGroup;
			this.key = key;
			this.values = new HashMap<Object, Double>();
		}

		public Number evaluate(ReportParameters reportParameters) {
			Object resetValue = null;
			switch (resetType) {
			case REPORT:
				break;
			case PAGE:
				resetValue = reportParameters.getPageNumber();
				break;
			case COLUMN:
				resetValue = reportParameters.getColumnNumber();
				break;
			case GROUP:
				resetValue = reportParameters.getValue(resetGroup.getGroupExpression().getName());
				break;
			default:
				throw new DRDesignReportException("Reset type " + resetType.name() + " not supported");
			}
			if (this.resetValue != null && !this.resetValue.equals(resetValue)) {
				this.values = new HashMap<Object, Double>();
			}
			this.resetValue = resetValue;

			Object keyValue;
			if (key != null) {
				keyValue = reportParameters.getValue(valueExpression.getName()) + "_" + reportParameters.getValue(key);
			}
			else {
				keyValue = reportParameters.getValue(valueExpression.getName());
			}
			if (!values.containsKey(keyValue)) {
				values.put(keyValue, 0d);
			}
			double value = values.get(keyValue).doubleValue() + ((Number) reportParameters.getValue(serieExpression.getName())).doubleValue();
			values.put(keyValue, value);

			return value;
		}
	}

	private class PieChartShowLabelsCustomizer implements DRIChartCustomizer, Serializable {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		public void customize(JFreeChart chart, ReportParameters reportParameters) {
	    PiePlot plot = (PiePlot) chart.getPlot();
	    plot.setLabelGenerator(null);
	  }
	}
}
