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

package net.sf.dynamicreports.design.transformation;

import java.awt.Paint;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignDataset;
import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.chart.DRDesignChart;
import net.sf.dynamicreports.design.base.chart.DRDesignChartLegend;
import net.sf.dynamicreports.design.base.chart.DRDesignChartSubtitle;
import net.sf.dynamicreports.design.base.chart.DRDesignChartTitle;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignCategoryChartSerie;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignCategoryDataset;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignChartDataset;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignGanttChartSerie;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignHighLowDataset;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignSeriesDataset;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignTimeSeriesDataset;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignValueDataset;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignXyChartSerie;
import net.sf.dynamicreports.design.base.chart.dataset.DRDesignXyzChartSerie;
import net.sf.dynamicreports.design.base.chart.plot.AbstractDesignBasePlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignAxisFormat;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignAxisPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignBar3DPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignBarPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignBubblePlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignCandlestickPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignChartAxis;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignHighLowPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignLinePlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignMeterInterval;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignMeterPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignMultiAxisPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignPie3DPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignPiePlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignSpiderPlot;
import net.sf.dynamicreports.design.base.chart.plot.DRDesignThermometerPlot;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.DRIDesignVariable;
import net.sf.dynamicreports.design.definition.chart.dataset.DRIDesignChartSerie;
import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignPlot;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.ReportUtils;
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
import net.sf.dynamicreports.report.definition.chart.dataset.DRICategoryChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRICategoryDataset;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIChartDataset;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIGanttChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIHighLowDataset;
import net.sf.dynamicreports.report.definition.chart.dataset.DRISeriesDataset;
import net.sf.dynamicreports.report.definition.chart.dataset.DRITimeSeriesDataset;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIValueDataset;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIXyChartSerie;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIXyzChartSerie;
import net.sf.dynamicreports.report.definition.chart.plot.DRIAxisFormat;
import net.sf.dynamicreports.report.definition.chart.plot.DRIAxisPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIBar3DPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIBarPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIBasePlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIBubblePlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRICandlestickPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIChartAxis;
import net.sf.dynamicreports.report.definition.chart.plot.DRIHighLowPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRILayeredBarPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRILinePlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIMeterInterval;
import net.sf.dynamicreports.report.definition.chart.plot.DRIMeterPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIMultiAxisPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIPie3DPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIPiePlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRISpiderPlot;
import net.sf.dynamicreports.report.definition.chart.plot.DRIThermometerPlot;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.exception.DRException;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LayeredBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.GradientPaintTransformer;

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
		return transform(chart, null, resetType, resetGroup);
	}

	private DRDesignChart transform(DRIChart chart, DRIDataset subDataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignChart designChart = new DRDesignChart();
		designChart.setWidth(accessor.getTemplateTransform().getChartWidth(chart));
		designChart.setHeight(accessor.getTemplateTransform().getChartHeight(chart));
		designChart.setChartType(chart.getChartType());
		designChart.setTheme(accessor.getTemplateTransform().getChartTheme(chart));
		if (!(chart.getPlot() instanceof DRIMultiAxisPlot)) {
			designChart.setDataset(dataset(chart.getDataset(), subDataset, resetType, resetGroup));
		}
		designChart.setPlot(plot(chart.getPlot(), designChart.getCustomizers(), chart.getDataset().getSubDataset(), resetType, resetGroup));
		designChart.setTitle(title(chart.getTitle()));
		designChart.setSubtitle(subtitle(chart.getSubtitle()));
		designChart.setLegend(legend(chart.getLegend()));

		if (chart.getCustomizer() != null) {
			designChart.getCustomizers().add(chart.getCustomizer());
		}

		return designChart;
	}

	//plot
	private DRIDesignPlot plot(DRIPlot plot, List<DRIChartCustomizer> chartCustomizers, DRIDataset subDataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRIDesignPlot designPlot;

		if (plot instanceof DRIBar3DPlot) {
			designPlot = bar3DPlot((DRIBar3DPlot) plot);
		}
		else if (plot instanceof DRILayeredBarPlot) {
			designPlot = layeredBarPlot((DRILayeredBarPlot) plot, chartCustomizers);
		}
		else if (plot instanceof DRIBarPlot) {
			designPlot = barPlot((DRIBarPlot) plot);
		}
		else if (plot instanceof DRILinePlot) {
			designPlot = linePlot((DRILinePlot) plot);
		}
		else if (plot instanceof DRIMultiAxisPlot) {
			designPlot = multiAxisPlot((DRIMultiAxisPlot) plot, subDataset, resetType, resetGroup);
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
		else if (plot instanceof DRIBubblePlot) {
			designPlot = bubblePlot((DRIBubblePlot) plot);
		}
		else if (plot instanceof DRICandlestickPlot) {
			designPlot = candlestickPlot((DRICandlestickPlot) plot);
		}
		else if (plot instanceof DRIHighLowPlot) {
			designPlot = highLowPlot((DRIHighLowPlot) plot);
		}
		else if (plot instanceof DRIMeterPlot) {
			designPlot = meterPlot((DRIMeterPlot) plot);
		}
		else if (plot instanceof DRIThermometerPlot) {
			designPlot = thermometerPlot((DRIThermometerPlot) plot);
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

	private DRDesignBarPlot layeredBarPlot(DRILayeredBarPlot layeredBarPlot, List<DRIChartCustomizer> chartCustomizers) throws DRException {
		DRDesignBarPlot designBarPlot = barPlot(layeredBarPlot);
		chartCustomizers.add(new LayeredBarRendererCustomizer(layeredBarPlot.getSeriesBarWidths()));
		return designBarPlot;
	}

	private DRDesignLinePlot linePlot(DRILinePlot linePlot) throws DRException {
		DRDesignLinePlot designLinePlot = new DRDesignLinePlot();
		axisPlot(designLinePlot, linePlot);
		designLinePlot.setShowShapes(linePlot.getShowShapes());
		designLinePlot.setShowLines(linePlot.getShowLines());
		return designLinePlot;
	}

	private DRDesignMultiAxisPlot multiAxisPlot(DRIMultiAxisPlot multiAxisPlot, DRIDataset subDataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignMultiAxisPlot designMultiAxisPlot = new DRDesignMultiAxisPlot();
		axisPlot(designMultiAxisPlot, multiAxisPlot);
		for (DRIChartAxis axis : multiAxisPlot.getAxes()) {
			DRDesignChartAxis designAxis = new DRDesignChartAxis();
			designAxis.setPosition(axis.getPosition());
			DRDesignChart chart = transform(axis.getChart(), subDataset, resetType, resetGroup);
			chart.setUniqueName(ReportUtils.generateUniqueName("chart"));
			designAxis.setChart(chart);
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

	private DRDesignBubblePlot bubblePlot(DRIBubblePlot bubblePlot) throws DRException {
		DRDesignBubblePlot designBubblePlot = new DRDesignBubblePlot();
		axisPlot(designBubblePlot, bubblePlot);
		designBubblePlot.setScaleType(bubblePlot.getScaleType());
		return designBubblePlot;
	}

	private DRDesignCandlestickPlot candlestickPlot(DRICandlestickPlot candlestickPlot) throws DRException {
		DRDesignCandlestickPlot designCandlestickPlot = new DRDesignCandlestickPlot();
		axisPlot(designCandlestickPlot, candlestickPlot);
		designCandlestickPlot.setShowVolume(candlestickPlot.getShowVolume());
		return designCandlestickPlot;
	}

	private DRDesignHighLowPlot highLowPlot(DRIHighLowPlot highLowPlot) throws DRException {
		DRDesignHighLowPlot designHighLowPlot = new DRDesignHighLowPlot();
		axisPlot(designHighLowPlot, highLowPlot);
		designHighLowPlot.setShowOpenTicks(highLowPlot.getShowOpenTicks());
		designHighLowPlot.setShowCloseTicks(highLowPlot.getShowCloseTicks());
		return designHighLowPlot;
	}

	private DRDesignMeterPlot meterPlot(DRIMeterPlot meterPlot) throws DRException {
		DRDesignMeterPlot designMeterPlot = new DRDesignMeterPlot();
		designMeterPlot.setDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(meterPlot.getDataRangeLowExpression()));
		designMeterPlot.setDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(meterPlot.getDataRangeHighExpression()));
		designMeterPlot.setValueColor(meterPlot.getValueColor());
		designMeterPlot.setValueMask(meterPlot.getValueMask());
		designMeterPlot.setValueFont(accessor.getStyleTransform().transformFont(meterPlot.getValueFont()));
		designMeterPlot.setShape(meterPlot.getShape());
		for (DRIMeterInterval interval : meterPlot.getIntervals()) {
			designMeterPlot.getIntervals().add(meterInterval(interval));
		}
		designMeterPlot.setMeterAngle(meterPlot.getMeterAngle());
		designMeterPlot.setUnits(meterPlot.getUnits());
		designMeterPlot.setTickInterval(meterPlot.getTickInterval());
		designMeterPlot.setMeterBackgroundColor(meterPlot.getMeterBackgroundColor());
		designMeterPlot.setNeedleColor(meterPlot.getNeedleColor());
		designMeterPlot.setTickColor(meterPlot.getTickColor());
		designMeterPlot.setTickLabelFont(accessor.getStyleTransform().transformFont(meterPlot.getTickLabelFont()));
		return designMeterPlot;
	}

	private DRDesignThermometerPlot thermometerPlot(DRIThermometerPlot thermometerPlot) throws DRException {
		DRDesignThermometerPlot designThermometerPlot = new DRDesignThermometerPlot();
		designThermometerPlot.setDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getDataRangeLowExpression()));
		designThermometerPlot.setDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getDataRangeHighExpression()));
		designThermometerPlot.setValueColor(thermometerPlot.getValueColor());
		designThermometerPlot.setValueMask(thermometerPlot.getValueMask());
		designThermometerPlot.setValueFont(accessor.getStyleTransform().transformFont(thermometerPlot.getValueFont()));
		designThermometerPlot.setValueLocation(accessor.getTemplateTransform().getChartThermometerPlotValueLocation(thermometerPlot));
		designThermometerPlot.setMercuryColor(thermometerPlot.getMercuryColor());
		designThermometerPlot.setLowDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getLowDataRangeLowExpression()));
		designThermometerPlot.setLowDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getLowDataRangeHighExpression()));
		designThermometerPlot.setMediumDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getMediumDataRangeLowExpression()));
		designThermometerPlot.setMediumDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getMediumDataRangeHighExpression()));
		designThermometerPlot.setHighDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getHighDataRangeLowExpression()));
		designThermometerPlot.setHighDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(thermometerPlot.getHighDataRangeHighExpression()));
		return designThermometerPlot;
	}

	private DRDesignMeterInterval meterInterval(DRIMeterInterval interval) throws DRException {
		DRDesignMeterInterval designInterval = new DRDesignMeterInterval();
		designInterval.setLabel(interval.getLabel());
		designInterval.setBackgroundColor(interval.getBackgroundColor());
		designInterval.setAlpha(interval.getAlpha());
		designInterval.setDataRangeLowExpression(accessor.getExpressionTransform().transformExpression(interval.getDataRangeLowExpression()));
		designInterval.setDataRangeHighExpression(accessor.getExpressionTransform().transformExpression(interval.getDataRangeHighExpression()));
		return designInterval;
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
	private DRDesignChartDataset dataset(DRIChartDataset dataset, DRIDataset subDataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignDataset designSubDataset = null;
		if (dataset.getSubDataset() != null) {
			designSubDataset = accessor.getDatasetTransform().transform(dataset.getSubDataset());
			accessor.transformToDataset(dataset.getSubDataset());
		}
		else {
			designSubDataset = accessor.getDatasetTransform().transform(subDataset);
			accessor.transformToDataset(subDataset);
		}

		DRDesignChartDataset designDataset;
		if (dataset instanceof DRICategoryDataset) {
			designDataset = categoryDataset((DRICategoryDataset) dataset, resetType, resetGroup);
		}
		else if (dataset instanceof DRITimeSeriesDataset) {
			designDataset = timeSeriesDataset((DRITimeSeriesDataset) dataset, resetType, resetGroup);
		}
		else if (dataset instanceof DRISeriesDataset) {
			designDataset = seriesDataset((DRISeriesDataset) dataset, resetType, resetGroup);
		}
		else if (dataset instanceof DRIHighLowDataset) {
			designDataset = highLowDataset((DRIHighLowDataset) dataset);
		}
		else if (dataset instanceof DRIValueDataset) {
			designDataset = valueDataset((DRIValueDataset) dataset);
		}
		else {
			throw new DRDesignReportException("Dataset " + dataset.getClass().getName() + " not supported");
		}

		designDataset.setSubDataset(designSubDataset);
		designDataset.setResetType(resetType);
		designDataset.setResetGroup(resetGroup);
		accessor.transformToMainDataset();

		return designDataset;
	}

	private void seriesDataset(DRISeriesDataset dataset, DRDesignSeriesDataset designDataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRIDesignExpression valueExpression = accessor.getExpressionTransform().transformExpression(dataset.getValueExpression());
		designDataset.setItemHyperLink(accessor.getReportTransform().hyperlink(dataset.getItemHyperLink()));
		designDataset.setValueExpression(valueExpression);
		int index = 0;
		for (DRIChartSerie serie : dataset.getSeries()) {
			DRIDesignChartSerie designSerie;
			if (serie instanceof DRICategoryChartSerie) {
				designSerie = categorySerie(dataset.getSubDataset(), (DRICategoryChartSerie) serie, valueExpression, resetType, resetGroup, index++);
			}
			else if (serie instanceof DRIXyChartSerie) {
				designSerie = xySerie(dataset.getSubDataset(), (DRIXyChartSerie) serie, valueExpression, resetType, resetGroup, index++);
			}
			else if (serie instanceof DRIXyzChartSerie) {
				designSerie = xyzSerie(dataset.getSubDataset(), (DRIXyzChartSerie) serie, valueExpression, resetType, resetGroup, index++);
			}
			else if (serie instanceof DRIGanttChartSerie) {
				designSerie = ganttSerie(dataset.getSubDataset(), (DRIGanttChartSerie) serie, valueExpression, resetType, resetGroup, index++);
			}
			else {
				throw new DRDesignReportException("Chart serie " + serie.getClass().getName() + " not supported");
			}
			designDataset.addSerie(designSerie);
		}
	}

	private DRDesignSeriesDataset seriesDataset(DRISeriesDataset dataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignSeriesDataset designDataset = new DRDesignSeriesDataset();
		seriesDataset(dataset, designDataset, resetType, resetGroup);
		return designDataset;
	}

	private DRDesignCategoryDataset categoryDataset(DRICategoryDataset dataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCategoryDataset designDataset = new DRDesignCategoryDataset();
		seriesDataset(dataset, designDataset, resetType, resetGroup);
		designDataset.setUseSeriesAsCategory(accessor.getTemplateTransform().isChartCategoryDatasetUseSeriesAsCategory(dataset));
		return designDataset;
	}

	private DRDesignTimeSeriesDataset timeSeriesDataset(DRITimeSeriesDataset dataset, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignTimeSeriesDataset designDataset = new DRDesignTimeSeriesDataset();
		seriesDataset(dataset, designDataset, resetType, resetGroup);
		designDataset.setTimePeriodType(accessor.getTemplateTransform().getChartTimeSeriesDatasetTimePeriodType(dataset));
		return designDataset;
	}

	private DRDesignHighLowDataset highLowDataset(DRIHighLowDataset dataset) throws DRException {
		DRDesignHighLowDataset designDataset = new DRDesignHighLowDataset();
		AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
		designDataset.setItemHyperLink(accessor.getReportTransform().hyperlink(dataset.getItemHyperLink()));
		designDataset.setSeriesExpression(expressionTransform.transformExpression(dataset.getSeriesExpression()));
		designDataset.setDateExpression(expressionTransform.transformExpression(dataset.getDateExpression()));
		designDataset.setHighExpression(expressionTransform.transformExpression(dataset.getHighExpression()));
		designDataset.setLowExpression(expressionTransform.transformExpression(dataset.getLowExpression()));
		designDataset.setOpenExpression(expressionTransform.transformExpression(dataset.getOpenExpression()));
		designDataset.setCloseExpression(expressionTransform.transformExpression(dataset.getCloseExpression()));
		designDataset.setVolumeExpression(expressionTransform.transformExpression(dataset.getVolumeExpression()));
		return designDataset;
	}

	private DRDesignValueDataset valueDataset(DRIValueDataset dataset) throws DRException {
		DRDesignValueDataset designDataset = new DRDesignValueDataset();
		AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
		designDataset.setValueExpression(expressionTransform.transformExpression(dataset.getValueExpression()));
		return designDataset;
	}

	//design serie
	private DRDesignCategoryChartSerie categorySerie(DRIDataset dataset, DRICategoryChartSerie serie, DRIDesignExpression valueExpression, ResetType resetType, DRDesignGroup resetGroup, int index) throws DRException {
		DRDesignCategoryChartSerie designSerie = new DRDesignCategoryChartSerie();

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

	//xy serie
	private DRDesignXyChartSerie xySerie(DRIDataset dataset, DRIXyChartSerie serie, DRIDesignExpression valueExpression, ResetType resetType, DRDesignGroup resetGroup, int index) throws DRException {
		DRDesignXyChartSerie designSerie = new DRDesignXyChartSerie();

		AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
		designSerie.setSeriesExpression(expressionTransform.transformExpression(serie.getSeriesExpression()));
		designSerie.setXValueExpression(expressionTransform.transformExpression(serie.getXValueExpression()));
		designSerie.setYValueExpression(expressionTransform.transformExpression(serie.getYValueExpression()));
		DRIExpression<?> labelExpression = serie.getLabelExpression();
		if (labelExpression == null) {
			labelExpression = Expressions.text("serie" + index);
		}
		designSerie.setLabelExpression(expressionTransform.transformExpression(labelExpression));

		return designSerie;
	}

	//xyz serie
	private DRDesignXyzChartSerie xyzSerie(DRIDataset dataset, DRIXyzChartSerie serie, DRIDesignExpression valueExpression, ResetType resetType, DRDesignGroup resetGroup, int index) throws DRException {
		DRDesignXyzChartSerie designSerie = new DRDesignXyzChartSerie();
		AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
		DRIExpression<?> seriesExpression = serie.getSeriesExpression();
		if (seriesExpression == null) {
			seriesExpression = Expressions.text("serie" + index);
		}
		designSerie.setSeriesExpression(expressionTransform.transformExpression(seriesExpression));
		designSerie.setXValueExpression(expressionTransform.transformExpression(serie.getXValueExpression()));
		designSerie.setYValueExpression(expressionTransform.transformExpression(serie.getYValueExpression()));
		designSerie.setZValueExpression(expressionTransform.transformExpression(serie.getZValueExpression()));
		return designSerie;
	}

	//gantt serie
	private DRDesignGanttChartSerie ganttSerie(DRIDataset dataset, DRIGanttChartSerie serie, DRIDesignExpression valueExpression, ResetType resetType, DRDesignGroup resetGroup, int index) throws DRException {
		DRDesignGanttChartSerie designSerie = new DRDesignGanttChartSerie();
		AbstractExpressionTransform expressionTransform = accessor.getExpressionTransform();
		designSerie.setSeriesExpression(expressionTransform.transformExpression(serie.getSeriesExpression()));
		designSerie.setStartDateExpression(expressionTransform.transformExpression(serie.getStartDateExpression()));
		designSerie.setEndDateExpression(expressionTransform.transformExpression(serie.getEndDateExpression()));
		designSerie.setPercentExpression(expressionTransform.transformExpression(serie.getPercentExpression()));
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
		}

		public Number evaluate(ReportParameters reportParameters) {
			if (reportParameters.getReportRowNumber() <= 1) {
				resetValue = null;
				values = new HashMap<Object, Double>();
			}

			Object resetValue = null;
			switch (resetType) {
			case NONE:
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
			Number serieValue = reportParameters.getValue(serieExpression.getName());
			double value = values.get(keyValue).doubleValue();
			if (serieValue != null) {
				value += serieValue.doubleValue();
				values.put(keyValue, value);
			}

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

	private class LayeredBarRendererCustomizer implements DRIChartCustomizer, Serializable {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		private List<Double> seriesBarWidths;

		private LayeredBarRendererCustomizer(List<Double> seriesBarWidths) {
			this.seriesBarWidths = seriesBarWidths;
		}

		public void customize(JFreeChart chart, ReportParameters reportParameters) {
			BarRenderer categoryRenderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
	    LayeredBarRenderer renderer = new LayeredBarRenderer();

	    renderer.setBaseItemLabelsVisible(categoryRenderer.getBaseItemLabelsVisible());
	    renderer.setBaseItemLabelFont(categoryRenderer.getBaseItemLabelFont());
	    renderer.setBaseItemLabelPaint(categoryRenderer.getBaseItemLabelPaint());
	    renderer.setBaseItemLabelGenerator(categoryRenderer.getBaseItemLabelGenerator());
			renderer.setShadowVisible(categoryRenderer.getShadowsVisible());
			CategoryDataset categoryDataset = chart.getCategoryPlot().getDataset();
			if(categoryDataset != null)	{
				for(int i = 0; i < categoryDataset.getRowCount(); i++) {
					Paint seriesOutlinePaint = categoryRenderer.getSeriesOutlinePaint(i);
					if (seriesOutlinePaint != null) {
						renderer.setSeriesOutlinePaint(i, seriesOutlinePaint);
					}
					Paint seriesPaint = categoryRenderer.getSeriesPaint(i);
					if (seriesPaint != null) {
						renderer.setSeriesPaint(i, seriesPaint);
					}
				}
			}
			renderer.setItemMargin(categoryRenderer.getItemMargin());
			GradientPaintTransformer gradientPaintTransformer = categoryRenderer.getGradientPaintTransformer();
			if (gradientPaintTransformer != null) {
				renderer.setGradientPaintTransformer(gradientPaintTransformer);
			}

	    if (seriesBarWidths != null) {
	    	for (int i = 0; i < seriesBarWidths.size(); i++) {
	    		renderer.setSeriesBarWidth(i, seriesBarWidths.get(i));
				}
	    }

	    chart.getCategoryPlot().setRenderer(renderer);
	  }
	}
}
