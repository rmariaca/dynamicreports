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

package net.sf.dynamicreports.test.jasper;

import java.lang.reflect.Field;

import junit.framework.Assert;
import net.sf.jasperreports.charts.util.DrawChartRenderer;
import net.sf.jasperreports.engine.JRPrintImage;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class AbstractJasperChartTest extends AbstractJasperValueTest {

	protected void chartCountTest(String name, int expectedNumberOfCharts) {
		Assert.assertEquals("chart count " + name, expectedNumberOfCharts, findElement(name).size());
	}

	protected void chartCategoryCountTest(String name, int index, int expectedNumberOfCategories) {
		Assert.assertEquals("chart category count " + name, expectedNumberOfCategories, getChart(name, index).getCategoryPlot().getDataset().getColumnCount());
	}

	protected void chartSeriesCountTest(String name, int index, int expectedNumberOfSeries) {
		Assert.assertEquals("chart series count " + name, expectedNumberOfSeries, getChart(name, index).getCategoryPlot().getDataset().getRowCount());
	}

	protected void chartTitleTest(String name, int index, String title) {
		TextTitle chartTitle = getChart(name, index).getTitle();
		if (title != null) {
			Assert.assertEquals("chart title", title, chartTitle.getText());
		}
		else {
			Assert.assertNull("chart title", chartTitle);
		}
	}

	protected void chartDataTest(String name, int index, String[] categories, String[] series, Number[][] values) {
		CategoryDataset dataset = getChart(name, index).getCategoryPlot().getDataset();
		for (int i = 0; i < categories.length; i++) {
			for (int j = 0; j < series.length; j++) {
				Assert.assertEquals("chart data", values[i][j], dataset.getValue(series[j], categories[i]));
			}
		}
	}

	protected JFreeChart getChart(String key, int index) {
		JRPrintImage image = (JRPrintImage) getElementAt(key, index);
		return getChart(image);
	}

	protected JFreeChart getChart(JRPrintImage image) {
		DrawChartRenderer renderer = (DrawChartRenderer) image.getRenderer();
		try {
			Field field = renderer.getClass().getDeclaredField("chart");
			field.setAccessible(true);
			return (JFreeChart) field.get(renderer);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return null;
	}
}
