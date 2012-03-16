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

package net.sf.dynamicreports.report.base.chart;

import net.sf.dynamicreports.report.base.chart.dataset.DRCategoryDataset;
import net.sf.dynamicreports.report.base.chart.dataset.DRChartDataset;
import net.sf.dynamicreports.report.base.chart.dataset.DRHighLowDataset;
import net.sf.dynamicreports.report.base.chart.dataset.DRSeriesDataset;
import net.sf.dynamicreports.report.base.chart.dataset.DRTimeSeriesDataset;
import net.sf.dynamicreports.report.base.chart.dataset.DRValueDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRAxisPlot;
import net.sf.dynamicreports.report.base.chart.plot.DRBar3DPlot;
import net.sf.dynamicreports.report.base.chart.plot.DRBarPlot;
import net.sf.dynamicreports.report.base.chart.plot.DRBubblePlot;
import net.sf.dynamicreports.report.base.chart.plot.DRCandlestickPlot;
import net.sf.dynamicreports.report.base.chart.plot.DRHighLowPlot;
import net.sf.dynamicreports.report.base.chart.plot.DRLayeredBarPlot;
import net.sf.dynamicreports.report.base.chart.plot.DRLinePlot;
import net.sf.dynamicreports.report.base.chart.plot.DRMeterPlot;
import net.sf.dynamicreports.report.base.chart.plot.DRMultiAxisPlot;
import net.sf.dynamicreports.report.base.chart.plot.DRPie3DPlot;
import net.sf.dynamicreports.report.base.chart.plot.DRPiePlot;
import net.sf.dynamicreports.report.base.chart.plot.DRSpiderPlot;
import net.sf.dynamicreports.report.base.chart.plot.DRThermometerPlot;
import net.sf.dynamicreports.report.base.component.DRHyperLinkComponent;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.chart.DRIChart;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import net.sf.dynamicreports.report.definition.chart.plot.DRIPlot;
import net.sf.dynamicreports.report.exception.DRReportException;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRChart extends DRHyperLinkComponent implements DRIChart {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private ChartType chartType;
	private DRChartDataset dataset;
	private DRIPlot plot;
	private DRIChartCustomizer customizer;
	private DRChartTitle title;
	private DRChartSubtitle subtitle;
	private DRChartLegend legend;
	private String theme;

	public DRChart(ChartType chartType) {
		setChartType(chartType);
	}

	@Override
	protected void init() {
		super.init();
		this.title = new DRChartTitle();
		this.subtitle = new DRChartSubtitle();
		this.legend = new DRChartLegend();
	}

	private void setChartType(ChartType chartType)	{
		Validate.notNull(chartType, "chartType must not be null");
		this.chartType = chartType;

		switch(chartType) {
			case AREA:
			case STACKEDAREA:
				dataset = new DRCategoryDataset();
				plot = new DRAxisPlot();
				break;
			case BAR:
			case STACKEDBAR:
				dataset = new DRCategoryDataset();
				plot = new DRBarPlot();
				break;
			case LAYEREDBAR:
				dataset = new DRCategoryDataset();
				plot = new DRLayeredBarPlot();
				break;
			case BAR3D:
			case STACKEDBAR3D:
				dataset = new DRCategoryDataset();
				plot = new DRBar3DPlot();
				break;
			case LINE:
				dataset = new DRCategoryDataset();
				plot = new DRLinePlot();
				break;
			case PIE:
				dataset = new DRSeriesDataset();
				plot = new DRPiePlot();
				break;
			case PIE3D:
				dataset = new DRSeriesDataset();
				plot = new DRPie3DPlot();
				break;
			case TIMESERIES:
				dataset = new DRTimeSeriesDataset();
				plot = new DRLinePlot();
				break;
			case XYAREA:
				dataset = new DRSeriesDataset();
				plot = new DRAxisPlot();
				break;
			case XYBAR:
				dataset = new DRSeriesDataset();
				plot = new DRBarPlot();
				break;
			case XYLINE:
				dataset = new DRSeriesDataset();
				plot = new DRLinePlot();
				break;
			case SCATTER:
				dataset = new DRSeriesDataset();
				plot = new DRLinePlot();
				break;
			case SPIDER:
				dataset = new DRCategoryDataset();
				plot = new DRSpiderPlot();
				break;
			case MULTI_AXIS:
				dataset = new DRChartDataset();
				plot = new DRMultiAxisPlot();
				break;
			case BUBBLE:
				dataset = new DRSeriesDataset();
				plot = new DRBubblePlot();
				break;
			case CANDLESTICK:
				dataset = new DRHighLowDataset();
				plot = new DRCandlestickPlot();
				break;
			case HIGHLOW:
				dataset = new DRHighLowDataset();
				plot = new DRHighLowPlot();
				break;
			case METER:
				dataset = new DRValueDataset();
				plot = new DRMeterPlot();
				break;
			case THERMOMETER:
				dataset = new DRValueDataset();
				plot = new DRThermometerPlot();
				break;
			case GANTT:
				dataset = new DRSeriesDataset();
				plot = new DRBarPlot();
				break;
			default:
				throw new DRReportException("Chart type not supported.");
		}
	}

	public ChartType getChartType() {
		return chartType;
	}

	public DRChartDataset getDataset() {
		return dataset;
	}

	public DRIPlot getPlot() {
		return plot;
	}

	public DRIChartCustomizer getCustomizer() {
		return customizer;
	}

	public void setCustomizer(DRIChartCustomizer customizer) {
		this.customizer = customizer;
	}

	public DRChartTitle getTitle() {
		return title;
	}

	public void setTitle(DRChartTitle title) {
		Validate.notNull(title, "title must not be null");
		this.title = title;
	}

	public DRChartSubtitle getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(DRChartSubtitle subtitle) {
		Validate.notNull(subtitle, "subtitle must not be null");
		this.subtitle = subtitle;
	}

	public DRChartLegend getLegend() {
		return legend;
	}

	public void setLegend(DRChartLegend legend) {
		Validate.notNull(legend, "legend must not be null");
		this.legend = legend;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}
