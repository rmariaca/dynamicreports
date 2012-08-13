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

package net.sf.dynamicreports.design.transformation.chartcustomizer;

import java.io.Serializable;
import java.util.List;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SeriesOrderByNameCustomizer implements DRIChartCustomizer, Serializable {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private List<String> seriesOrderByName;

	public SeriesOrderByNameCustomizer(List<String> seriesOrderByName) {
		this.seriesOrderByName = seriesOrderByName;
	}

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		if (chart.getPlot() instanceof CategoryPlot) {
			CategoryDataset dataset = new SeriesOrderByNameCategoryDataset(chart.getCategoryPlot().getDataset(), seriesOrderByName);
			chart.getCategoryPlot().setDataset(dataset);
		}
	}
}
