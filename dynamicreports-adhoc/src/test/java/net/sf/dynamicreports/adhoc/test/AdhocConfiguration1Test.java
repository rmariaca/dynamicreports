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

package net.sf.dynamicreports.adhoc.test;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocAxisFormat;
import net.sf.dynamicreports.adhoc.configuration.AdhocCalculation;
import net.sf.dynamicreports.adhoc.configuration.AdhocCategoryChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocCategoryChartSerie;
import net.sf.dynamicreports.adhoc.configuration.AdhocCategoryChartType;
import net.sf.dynamicreports.adhoc.configuration.AdhocChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocFont;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroup;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroupHeaderLayout;
import net.sf.dynamicreports.adhoc.configuration.AdhocHorizontalAlignment;
import net.sf.dynamicreports.adhoc.configuration.AdhocOrderType;
import net.sf.dynamicreports.adhoc.configuration.AdhocOrientation;
import net.sf.dynamicreports.adhoc.configuration.AdhocPage;
import net.sf.dynamicreports.adhoc.configuration.AdhocPageOrientation;
import net.sf.dynamicreports.adhoc.configuration.AdhocPen;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.configuration.AdhocSort;
import net.sf.dynamicreports.adhoc.configuration.AdhocStyle;
import net.sf.dynamicreports.adhoc.configuration.AdhocSubtotal;
import net.sf.dynamicreports.adhoc.configuration.AdhocTextField;
import net.sf.dynamicreports.adhoc.configuration.AdhocVerticalAlignment;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRGroup;
import net.sf.dynamicreports.report.base.DRPage;
import net.sf.dynamicreports.report.base.DRReport;
import net.sf.dynamicreports.report.base.DRSort;
import net.sf.dynamicreports.report.base.chart.DRChart;
import net.sf.dynamicreports.report.base.chart.dataset.DRCategoryDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRAxisPlot;
import net.sf.dynamicreports.report.base.column.DRColumn;
import net.sf.dynamicreports.report.base.component.DRComponent;
import net.sf.dynamicreports.report.base.component.DRDimensionComponent;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.subtotal.BaseSubtotalBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.definition.chart.dataset.DRICategoryChartSerie;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.dynamicreports.report.exception.DRException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocConfiguration1Test {
	private AdhocConfiguration adhocConfiguration;

	@Before
	public void init() {
		adhocConfiguration = new AdhocConfiguration();
		AdhocReport adhocReport = new AdhocReport();
		adhocConfiguration.setReport(adhocReport);

		AdhocStyle adhocStyle1 = new AdhocStyle();
		adhocStyle1.setForegroundColor(Color.BLUE);
		AdhocStyle adhocStyle2 = new AdhocStyle();
		AdhocFont adhocFont = new AdhocFont();
		adhocFont.setFontName("a");
		adhocFont.setFontSize(5);
		adhocFont.setBold(true);
		adhocFont.setItalic(true);
		adhocFont.setUnderline(true);
		adhocFont.setStrikeThrough(true);
		adhocStyle2.setFont(adhocFont);
		AdhocPen adhocPen = new AdhocPen();
		adhocPen.setLineWidth(2f);
		adhocPen.setLineColor(Color.CYAN);
		adhocStyle2.setTopBorder(adhocPen);
		adhocStyle2.setLeftBorder(adhocPen);
		adhocStyle2.setBottomBorder(adhocPen);
		adhocStyle2.setRightBorder(adhocPen);
		adhocStyle2.setForegroundColor(Color.WHITE);
		adhocStyle2.setBackgroundColor(Color.DARK_GRAY);
		adhocStyle2.setHorizontalAlignment(AdhocHorizontalAlignment.CENTER);
		adhocStyle2.setVerticalAlignment(AdhocVerticalAlignment.MIDDLE);
		adhocStyle2.setPattern("#,###.00");

		AdhocColumn adhocColumn = new AdhocColumn();
		adhocColumn.setName("field1");
		adhocColumn.setLabel("Column1");
		adhocColumn.setStyle(adhocStyle1);
		adhocColumn.setTitleStyle(adhocStyle1);
		adhocColumn.setWidth(50);
		adhocReport.addColumn(adhocColumn);

		adhocReport.setTextStyle(adhocStyle2);
		adhocReport.setColumnStyle(adhocStyle1);
		adhocReport.setColumnTitleStyle(adhocStyle1);
		adhocReport.setGroupStyle(adhocStyle1);
		adhocReport.setGroupTitleStyle(adhocStyle1);
		adhocReport.setSubtotalStyle(adhocStyle1);
		adhocReport.setDetailOddRowStyle(adhocStyle1);
		adhocReport.setHighlightDetailOddRows(true);
		adhocReport.setDetailEvenRowStyle(adhocStyle1);
		adhocReport.setHighlightDetailEvenRows(true);
		adhocReport.setIgnorePagination(true);
		adhocReport.setTableOfContents(true);

		AdhocPage adhocPage = new AdhocPage();
		adhocPage.setWidth(100);
		adhocPage.setHeight(200);
		adhocPage.setPageOrientation(AdhocPageOrientation.PORTRAIT);
		adhocPage.setTopMargin(1);
		adhocPage.setBottomMargin(2);
		adhocPage.setLeftMargin(3);
		adhocPage.setRightMargin(4);
		adhocPage.setIgnorePageWidth(true);
		adhocReport.setPage(adhocPage);

		adhocColumn = new AdhocColumn();
		adhocColumn.setName("field2");
		adhocColumn.setLabel("Column2");
		adhocReport.addColumn(adhocColumn);

		adhocColumn = new AdhocColumn();
		adhocColumn.setName("field3");
		adhocReport.addColumn(adhocColumn);

		AdhocGroup adhocGroup = new AdhocGroup();
		adhocGroup.setName("field4");
		adhocGroup.setStartInNewPage(true);
		adhocGroup.setHeaderLayout(AdhocGroupHeaderLayout.TITLE_AND_VALUE);
		adhocGroup.setStyle(adhocStyle1);
		adhocGroup.setTitleStyle(adhocStyle1);
		adhocReport.addGroup(adhocGroup);

		adhocGroup = new AdhocGroup();
		adhocGroup.setName("field4");
		adhocReport.addGroup(adhocGroup);

		AdhocSort adhocSort = new AdhocSort();
		adhocSort.setName("field1");
		adhocSort.setOrderType(AdhocOrderType.DESCENDING);
		adhocReport.addSort(adhocSort);

		adhocSort = new AdhocSort();
		adhocSort.setName("field1");
		adhocReport.addSort(adhocSort);

		AdhocSubtotal adhocSubtotal = new AdhocSubtotal();
		adhocSubtotal.setName("field1");
		adhocSubtotal.setLabel("label");
		adhocSubtotal.setCalculation(AdhocCalculation.SUM);
		adhocSubtotal.setStyle(adhocStyle1);
		adhocSubtotal.setLabelStyle(adhocStyle1);
		adhocReport.addSubtotal(adhocSubtotal);

		adhocSubtotal = new AdhocSubtotal();
		adhocSubtotal.setName("field1");
		adhocReport.addSubtotal(adhocSubtotal);

		AdhocTextField adhocTextField = new AdhocTextField();
		adhocTextField.setKey("textField");
		adhocTextField.setStyle(adhocStyle1);
		adhocTextField.setWidth(150);
		adhocTextField.setHeight(200);
		adhocTextField.setText("text");
		adhocReport.addComponent(adhocTextField);

		AdhocCategoryChart adhocChart = new AdhocCategoryChart();
		adhocChart.setKey("chart1");
		adhocChart.setTitle("title");
		adhocChart.setTitleFont(new AdhocFont());
		adhocChart.setTitleColor(Color.MAGENTA);
		adhocChart.setShowLegend(true);
		adhocChart.setType(AdhocCategoryChartType.AREA);
		adhocChart.setCategory("field2");
		AdhocCategoryChartSerie serie = new AdhocCategoryChartSerie();
		serie.setValue("field1");
		serie.setSeries("field5");
		serie.setLabel("label");
		adhocChart.addSerie(serie);
		adhocChart.addSeriesColor(Color.LIGHT_GRAY);
		AdhocAxisFormat axisFormat = new AdhocAxisFormat();
		axisFormat.setLabel("label");
		axisFormat.setLabelFont(new AdhocFont());
		axisFormat.setLabelColor(Color.BLUE);
		adhocChart.setCategoryAxisFormat(axisFormat);
		adhocChart.setValueAxisFormat(axisFormat);
		adhocChart.setOrientation(AdhocOrientation.VERTICAL);
		adhocChart.setUseSeriesAsCategory(true);
		adhocReport.addComponent(adhocChart);

		adhocChart = new AdhocCategoryChart();
		adhocChart.setKey("chart2");
		adhocChart.setCategory("field2");
		serie = new AdhocCategoryChartSerie();
		serie.setValue("field1");
		adhocChart.addSerie(serie);
		adhocReport.addComponent(adhocChart);
	}

	@Test
	public void test() {
		testReportConfiguration(adhocConfiguration.getReport());
	}

	@Test
	public void testSaveAndLoad() {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			AdhocManager.saveConfiguration(adhocConfiguration, os);
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			AdhocConfiguration adhocConfiguration = AdhocManager.loadConfiguration(is);
			testReportConfiguration(adhocConfiguration.getReport());
		} catch (DRException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testEqualsAndClone() {
		AdhocConfiguration adhocConfiguration2 = adhocConfiguration.clone();
		testReportConfiguration(adhocConfiguration2.getReport());
		Assert.assertTrue("equals", adhocConfiguration.equals(adhocConfiguration));
		Assert.assertTrue("equals", adhocConfiguration.equals(adhocConfiguration2));
		AdhocColumn adhocColumn = new AdhocColumn();
		adhocColumn.setName("field3");
		adhocConfiguration2.getReport().addColumn(adhocColumn);
		Assert.assertFalse("equals", adhocConfiguration.equals(adhocConfiguration2));
		AdhocConfiguration adhocConfiguration3 = adhocConfiguration2.clone();
		Assert.assertTrue("equals", adhocConfiguration2.equals(adhocConfiguration3));
	}

	private void testReportConfiguration(AdhocReport adhocReport) {
		DRReport report = null;
		ReportCustomizer customizer = new ReportCustomizer();
		try {
			JasperReportBuilder reportBuilder = AdhocManager.createReport(adhocReport, customizer);
			report = reportBuilder.getReport();
		} catch (DRException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		Assert.assertTrue("text style", report.getTextStyle() != null);
		Assert.assertTrue("column style", report.getColumnStyle() != null);
		Assert.assertTrue("column title style", report.getColumnTitleStyle() != null);
		Assert.assertTrue("group style", report.getGroupStyle() != null);
		Assert.assertTrue("group title style", report.getGroupTitleStyle() != null);
		Assert.assertTrue("subtotal style", report.getSubtotalStyle() != null);
		Assert.assertTrue("detail odd row style", report.getDetailOddRowStyle() != null);
		Assert.assertTrue("highlight detail odd rows", report.getHighlightDetailOddRows());
		Assert.assertTrue("detail even row style", report.getDetailEvenRowStyle() != null);
		Assert.assertTrue("highlight detail even rows", report.getHighlightDetailEvenRows());

		DRIStyle style = (DRIStyle) report.getTextStyle();
		Assert.assertEquals("font name", "a", style.getFont().getFontName());
		Assert.assertEquals("font size", 5, style.getFont().getFontSize());
		Assert.assertTrue("bold", style.getFont().getBold());
		Assert.assertTrue("italic", style.getFont().getItalic());
		Assert.assertTrue("underline", style.getFont().getUnderline());
		Assert.assertTrue("strike through", style.getFont().getStrikeThrough());
		Assert.assertTrue("top border", style.getBorder().getTopPen() != null);
		Assert.assertTrue("left border", style.getBorder().getLeftPen() != null);
		Assert.assertTrue("bottom border", style.getBorder().getBottomPen() != null);
		Assert.assertTrue("right border", style.getBorder().getRightPen() != null);
		Assert.assertEquals("pen line width", 2f, style.getBorder().getTopPen().getLineWidth());
		Assert.assertEquals("pen line color", Color.CYAN, style.getBorder().getTopPen().getLineColor());
		Assert.assertEquals("foreground color", Color.WHITE, style.getForegroundColor());
		Assert.assertEquals("background color", Color.DARK_GRAY, style.getBackgroundColor());
		Assert.assertEquals("horizontal alignment", HorizontalAlignment.CENTER, style.getHorizontalAlignment());
		Assert.assertEquals("vertical alignment", VerticalAlignment.MIDDLE, style.getVerticalAlignment());
		Assert.assertEquals("pattern", "#,###.00", style.getPattern());

		DRPage page = report.getPage();
		Assert.assertEquals("page width", 100, page.getWidth());
		Assert.assertEquals("page height", 200, page.getHeight());
		Assert.assertEquals("page orientation", PageOrientation.PORTRAIT, page.getOrientation());
		Assert.assertEquals("top margin", 1, page.getMargin().getTop());
		Assert.assertEquals("bottom margin", 2, page.getMargin().getBottom());
		Assert.assertEquals("left margin", 3, page.getMargin().getLeft());
		Assert.assertEquals("right margin", 4, page.getMargin().getRight());
		Assert.assertTrue("ignore page width", page.getIgnorePageWidth());

		Assert.assertEquals("columns", 3, report.getColumns().size());
		DRColumn<?> column = report.getColumns().get(0);
		Assert.assertEquals("column name", "field1", column.getName());
		Assert.assertTrue("column label", column.getTitleExpression() != null);
		Assert.assertTrue("column style", column.getComponent().getStyle() != null);
		Assert.assertTrue("column title style", column.getTitleStyle() != null);
		Assert.assertEquals("column width", 50, ((DRDimensionComponent) column.getComponent()).getWidth());
		Assert.assertEquals("column width type", ComponentDimensionType.FIXED, ((DRDimensionComponent) column.getComponent()).getWidthType());

		Assert.assertEquals("groups", 2, report.getGroups().size());
		DRGroup group = report.getGroups().get(0);
		Assert.assertTrue("group start in new page", group.getStartInNewPage());
		Assert.assertEquals("group header layout", GroupHeaderLayout.TITLE_AND_VALUE, group.getHeaderLayout());
		Assert.assertTrue("group style", group.getValueField().getStyle() != null);
		Assert.assertTrue("group title style", group.getTitleStyle() != null);

		Assert.assertEquals("sorts", 2, report.getSorts().size());
		DRSort sort = report.getSorts().get(0);
		Assert.assertTrue("sort name", sort.getExpression() != null);
		Assert.assertEquals("sort order", OrderType.DESCENDING, sort.getOrderType());

		/*Assert.assertEquals("subtotals", 1, customizer.getSubtotals().size());
		DRSubtotal<?> subtotal = customizer.getSubtotals().get(0).getSubtotal();
		Assert.assertTrue("subtotal label", subtotal.getLabelExpression() != null);
		Assert.assertTrue("subtotal style", subtotal.getValueField().getStyle() != null);
		Assert.assertTrue("subtotal label style", subtotal.getLabelStyle() != null);*/

		Assert.assertEquals("components", 3, customizer.getComponents().size());
		Assert.assertTrue("component", adhocConfiguration.getReport().getComponent("textField") instanceof AdhocTextField);
		Assert.assertTrue("component", adhocConfiguration.getReport().getComponent("chart1") instanceof AdhocChart);
		Assert.assertTrue("component", adhocConfiguration.getReport().getComponent("chart2") instanceof AdhocChart);

		DRComponent component = customizer.getComponents().get(0).getComponent();
		Assert.assertTrue("component style", component.getStyle() != null);
		Assert.assertEquals("component width", 150, ((DRDimensionComponent) component).getWidth());
		Assert.assertEquals("component width type", ComponentDimensionType.FIXED, ((DRDimensionComponent) component).getWidthType());
		Assert.assertEquals("component height", 200, ((DRDimensionComponent) component).getHeight());
		Assert.assertEquals("component height type", ComponentDimensionType.FIXED, ((DRDimensionComponent) component).getHeightType());
		DRTextField<?> textField = (DRTextField<?>) component;
		Assert.assertTrue("textField text", textField.getValueExpression() != null);

		DRChart chart = (DRChart) customizer.getComponents().get(1).getComponent();
		Assert.assertEquals("chart type", ChartType.AREA, chart.getChartType());
		Assert.assertTrue("chart title", chart.getTitle().getTitle() != null);
		Assert.assertTrue("chart title font", chart.getTitle().getFont() != null);
		Assert.assertEquals("chart title color", Color.MAGENTA, chart.getTitle().getColor());
		Assert.assertTrue("chart show legend", chart.getLegend().getShowLegend());
		Assert.assertTrue("chart category", ((DRCategoryDataset) chart.getDataset()).getValueExpression() != null);
		DRICategoryChartSerie chartSerie = (DRICategoryChartSerie) ((DRCategoryDataset) chart.getDataset()).getSeries().get(0);
		Assert.assertTrue("chart serie series", chartSerie.getSeriesExpression() != null);
		Assert.assertTrue("chart serie value", chartSerie.getValueExpression() != null);
		Assert.assertTrue("chart serie label", chartSerie.getLabelExpression() != null);
		Assert.assertEquals("chart series color", Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0));
		Assert.assertTrue("chart category axis format", ((DRAxisPlot) chart.getPlot()).getXAxisFormat() != null);
		Assert.assertTrue("chart value axis format", ((DRAxisPlot) chart.getPlot()).getYAxisFormat() != null);
		Assert.assertTrue("axis format label", ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression() != null);
		Assert.assertTrue("axis format label font", ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont() != null);
		Assert.assertEquals("axis format label color", Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor());
		Assert.assertEquals("chart orientation", Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation());
		Assert.assertTrue("chart use series as category", ((DRCategoryDataset) chart.getDataset()).getUseSeriesAsCategory());
	}

	private class ReportCustomizer extends DefaultAdhocReportCustomizer {

		private List<BaseSubtotalBuilder<?, ?>> getSubtotals() {
			return subtotals;
		}

		private List<ComponentBuilder<?, ?>> getComponents() {
			return new ArrayList<ComponentBuilder<?, ?>>(components.values());
		}
	}
}
