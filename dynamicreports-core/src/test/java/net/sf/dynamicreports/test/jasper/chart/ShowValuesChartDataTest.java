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

package net.sf.dynamicreports.test.jasper.chart;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.junit.Assert;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ShowValuesChartDataTest extends AbstractJasperChartTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		TextColumnBuilder<String> column1;
		TextColumnBuilder<Integer> column2;
		TextColumnBuilder<Integer> column3;
		TextColumnBuilder<Date> column4;

		rb.setPageFormat(PageType.A2, PageOrientation.PORTRAIT)
			.columns(
				column1 = col.column("Column1", "field1", String.class),
				column2 = col.column("Column2", "field2", Integer.class),
				column3 = col.column("Column3", "field3", Integer.class),
				column4 = col.column("Column4", "field4", Date.class))
			.summary(
				cmp.horizontalList(
					cht.barChart()
						.setShowValues(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.bar3DChart()
						.setShowValues(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.stackedBarChart()
						.setShowValues(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.stackedBar3DChart()
						.setShowValues(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3))),
				cmp.horizontalList(
					cht.areaChart()
						.setShowValues(true)
						.setCategory(column1)
						.series(cht.serie(column3), cht.serie(column2)),
					cht.lineChart()
						.setShowValues(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.pieChart()
						.setShowValues(true)
						.setKey(column1)
						.series(cht.serie(column2))),
				cmp.horizontalList(
					cht.xyBarChart()
						.setShowValues(true)
						.setXValue(column2)
						.series(cht.xySerie(column3)),
					cht.xyLineChart()
						.setShowValues(true)
						.setXValue(column2)
						.series(cht.xySerie(column3)),
					cht.scatterChart()
						.setShowValues(true)
						.setXValue(column2)
						.series(cht.xySerie(column3)),
				cmp.horizontalList(
					cht.timeSeriesChart()
						.setShowValues(true)
						.setTimePeriod(column4)
						.series(cht.serie(column2), cht.serie(column3))
						.setTimePeriodType(TimePeriod.DAY)),
					cht.differenceChart()
						.setShowValues(true)
						.setTimePeriod(column4)
						.series(cht.serie(column2), cht.serie(column3))
						.setTimePeriodType(TimePeriod.DAY)));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		JFreeChart chart = getChart("summary.chart1", 0);
		CategoryItemRenderer renderer1 = chart.getCategoryPlot().getRenderer();
		Assert.assertNotNull(renderer1.getBaseItemLabelGenerator());

		chart = getChart("summary.chart2", 0);
		renderer1 = chart.getCategoryPlot().getRenderer();
		Assert.assertNotNull(renderer1.getBaseItemLabelGenerator());

		chart = getChart("summary.chart3", 0);
		renderer1 = chart.getCategoryPlot().getRenderer();
		Assert.assertNotNull(renderer1.getBaseItemLabelGenerator());

		chart = getChart("summary.chart4", 0);
		renderer1 = chart.getCategoryPlot().getRenderer();
		Assert.assertNotNull(renderer1.getBaseItemLabelGenerator());

		chart = getChart("summary.chart5", 0);
		renderer1 = chart.getCategoryPlot().getRenderer();
		Assert.assertNotNull(renderer1.getBaseItemLabelGenerator());

		chart = getChart("summary.chart6", 0);
		renderer1 = chart.getCategoryPlot().getRenderer();
		Assert.assertNotNull(renderer1.getBaseItemLabelGenerator());

		chart = getChart("summary.chart7", 0);
		String labelFormat = ((StandardPieSectionLabelGenerator) ((PiePlot) chart.getPlot()).getLabelGenerator()).getLabelFormat();
		Assert.assertEquals("Label format", "{0} = {1}", labelFormat);

		chart = getChart("summary.chart8", 0);
		XYItemRenderer renderer2 = chart.getXYPlot().getRenderer();
		Assert.assertNotNull(renderer2.getBaseItemLabelGenerator());

		chart = getChart("summary.chart9", 0);
		renderer2 = chart.getXYPlot().getRenderer();
		Assert.assertNotNull(renderer2.getBaseItemLabelGenerator());

		chart = getChart("summary.chart10", 0);
		renderer2 = chart.getXYPlot().getRenderer();
		Assert.assertNotNull(renderer2.getBaseItemLabelGenerator());

		chart = getChart("summary.chart11", 0);
		renderer2 = chart.getXYPlot().getRenderer();
		Assert.assertNotNull(renderer2.getBaseItemLabelGenerator());

		chart = getChart("summary.chart12", 0);
		renderer2 = chart.getXYPlot().getRenderer();
		Assert.assertNotNull(renderer2.getBaseItemLabelGenerator());
	}

	@Override
	protected JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		for (int i = 0; i < 4; i++) {
			dataSource.add("value" + (i + 1), i + 1, i + 2, c.getTime());
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dataSource;
	}
}
