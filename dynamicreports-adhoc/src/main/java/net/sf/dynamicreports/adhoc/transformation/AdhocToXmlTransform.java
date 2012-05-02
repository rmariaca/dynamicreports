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

package net.sf.dynamicreports.adhoc.transformation;

import java.awt.Color;
import java.util.List;
import java.util.Properties;

import net.sf.dynamicreports.adhoc.configuration.AdhocAxisFormat;
import net.sf.dynamicreports.adhoc.configuration.AdhocCalculation;
import net.sf.dynamicreports.adhoc.configuration.AdhocCategoryChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocCategoryChartSerie;
import net.sf.dynamicreports.adhoc.configuration.AdhocCategoryChartType;
import net.sf.dynamicreports.adhoc.configuration.AdhocChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocComponent;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocFilter;
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
import net.sf.dynamicreports.adhoc.configuration.AdhocRestriction;
import net.sf.dynamicreports.adhoc.configuration.AdhocSort;
import net.sf.dynamicreports.adhoc.configuration.AdhocStyle;
import net.sf.dynamicreports.adhoc.configuration.AdhocSubtotal;
import net.sf.dynamicreports.adhoc.configuration.AdhocTextField;
import net.sf.dynamicreports.adhoc.configuration.AdhocValueOperator;
import net.sf.dynamicreports.adhoc.configuration.AdhocValueRestriction;
import net.sf.dynamicreports.adhoc.configuration.AdhocVerticalAlignment;
import net.sf.dynamicreports.adhoc.exception.AdhocException;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocAxisFormat;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocCalculation;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocCategoryChart;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocCategoryChartSerie;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocCategoryChartType;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocChart;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocColumn;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocComponent;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocConfiguration;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocFilter;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocFont;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocGroup;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocGroupHeaderLayout;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocHorizontalAlignment;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocOrderType;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocOrientation;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocPage;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocPageOrientation;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocPen;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocProperty;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocReport;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocRestriction;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocSort;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocStyle;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocSubtotal;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocTextField;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocValueOperator;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocValueRestriction;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocVerticalAlignment;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocToXmlTransform {

	public XmlAdhocConfiguration transform(AdhocConfiguration adhocConfiguration) {
		XmlAdhocConfiguration xmlAdhocConfiguration = new XmlAdhocConfiguration();
		xmlAdhocConfiguration.setReport(report(adhocConfiguration.getReport()));
		xmlAdhocConfiguration.setFilter(filter(adhocConfiguration.getFilter()));
		return xmlAdhocConfiguration;
	}

	protected void properties(Properties properties, List<XmlAdhocProperty> xmlAdhocProperties) {
		for (Object keyObject : properties.keySet()) {
			String key = (String) keyObject;
			XmlAdhocProperty xmlAdhocProperty = new XmlAdhocProperty();
			xmlAdhocProperty.setKey(key);
			xmlAdhocProperty.setValue(properties.getProperty(key));
		}
	}

	protected XmlAdhocReport report(AdhocReport adhocReport) {
		if (adhocReport == null) {
			return null;
		}

		XmlAdhocReport xmlAdhocReport = new XmlAdhocReport();
		xmlAdhocReport.setTextStyle(style(adhocReport.getTextStyle()));
		xmlAdhocReport.setColumnStyle(style(adhocReport.getColumnStyle()));
		xmlAdhocReport.setColumnTitleStyle(style(adhocReport.getColumnTitleStyle()));
		xmlAdhocReport.setGroupStyle(style(adhocReport.getGroupStyle()));
		xmlAdhocReport.setGroupTitleStyle(style(adhocReport.getGroupTitleStyle()));
		xmlAdhocReport.setSubtotalStyle(style(adhocReport.getSubtotalStyle()));
		xmlAdhocReport.setDetailOddRowStyle(style(adhocReport.getDetailOddRowStyle()));
		xmlAdhocReport.setHighlightDetailOddRows(adhocReport.getHighlightDetailOddRows());
		xmlAdhocReport.setDetailEvenRowStyle(style(adhocReport.getDetailEvenRowStyle()));
		xmlAdhocReport.setHighlightDetailEvenRows(adhocReport.getHighlightDetailEvenRows());
		xmlAdhocReport.setIgnorePagination(adhocReport.getIgnorePagination());
		xmlAdhocReport.setTableOfContents(adhocReport.getTableOfContents());
		xmlAdhocReport.setPage(page(adhocReport.getPage()));
		if (adhocReport.getColumns() != null && !adhocReport.getColumns().isEmpty()) {
			for (AdhocColumn adhocColumn : adhocReport.getColumns()) {
				xmlAdhocReport.getColumn().add(column(adhocColumn));
			}
		}
		if (adhocReport.getGroups() != null && !adhocReport.getGroups().isEmpty()) {
			for (AdhocGroup adhocGroup : adhocReport.getGroups()) {
				xmlAdhocReport.getGroup().add(group(adhocGroup));
			}
		}
		if (adhocReport.getSorts() != null && !adhocReport.getSorts().isEmpty()) {
			for (AdhocSort adhocSort : adhocReport.getSorts()) {
				xmlAdhocReport.getSort().add(sort(adhocSort));
			}
		}
		if (adhocReport.getSubtotals() != null && !adhocReport.getSubtotals().isEmpty()) {
			for (AdhocSubtotal adhocSubtotal : adhocReport.getSubtotals()) {
				xmlAdhocReport.getSubtotal().add(subtotal(adhocSubtotal));
			}
		}
		if (adhocReport.getComponents() != null && !adhocReport.getComponents().isEmpty()) {
			for (AdhocComponent adhocComponent : adhocReport.getComponents()) {
				xmlAdhocReport.getComponent().add(component(adhocComponent));
			}
		}

		return xmlAdhocReport;
	}

	protected XmlAdhocColumn column(AdhocColumn adhocColumn) {
		XmlAdhocColumn xmlAdhocColumn = new XmlAdhocColumn();
		xmlAdhocColumn.setName(adhocColumn.getName());
		xmlAdhocColumn.setLabel(adhocColumn.getLabel());
		xmlAdhocColumn.setWidth(adhocColumn.getWidth());
		xmlAdhocColumn.setStyle(style(adhocColumn.getStyle()));
		xmlAdhocColumn.setTitleStyle(style(adhocColumn.getTitleStyle()));
		return xmlAdhocColumn;
	}

	protected XmlAdhocGroup group(AdhocGroup adhocGroup) {
		XmlAdhocGroup xmlAdhocGroup = new XmlAdhocGroup();
		xmlAdhocGroup.setName(adhocGroup.getName());
		xmlAdhocGroup.setStartInNewPage(adhocGroup.getStartInNewPage());
		xmlAdhocGroup.setHeaderLayout(groupHeaderLayout(adhocGroup.getHeaderLayout()));
		xmlAdhocGroup.setStyle(style(adhocGroup.getStyle()));
		xmlAdhocGroup.setTitleStyle(style(adhocGroup.getTitleStyle()));
		if (adhocGroup.getProperties() != null && !adhocGroup.getProperties().isEmpty()) {
			properties(adhocGroup.getProperties(), xmlAdhocGroup.getProperty());
		}
		return xmlAdhocGroup;
	}

	protected XmlAdhocGroupHeaderLayout groupHeaderLayout(AdhocGroupHeaderLayout adhocGroupHeaderLayout) {
		if (adhocGroupHeaderLayout == null) {
			return null;
		}

		switch (adhocGroupHeaderLayout) {
		case EMPTY:
			return XmlAdhocGroupHeaderLayout.EMPTY;
		case VALUE:
			return XmlAdhocGroupHeaderLayout.VALUE;
		case TITLE_AND_VALUE:
			return XmlAdhocGroupHeaderLayout.TITLE_AND_VALUE;
		default:
			throw new AdhocException("Group header layout " + adhocGroupHeaderLayout.name() + " not supported");
		}
	}

	protected XmlAdhocSubtotal subtotal(AdhocSubtotal adhocSubtotal) {
		XmlAdhocSubtotal xmlAdhocSubtotal = new XmlAdhocSubtotal();
		xmlAdhocSubtotal.setName(adhocSubtotal.getName());
		xmlAdhocSubtotal.setLabel(adhocSubtotal.getLabel());
		xmlAdhocSubtotal.setCalculation(calculation(adhocSubtotal.getCalculation()));
		xmlAdhocSubtotal.setStyle(style(adhocSubtotal.getStyle()));
		xmlAdhocSubtotal.setLabelStyle(style(adhocSubtotal.getLabelStyle()));
		return xmlAdhocSubtotal;
	}

	protected XmlAdhocCalculation calculation(AdhocCalculation adhocCalculation) {
		if (adhocCalculation == null) {
			return null;
		}

		switch (adhocCalculation) {
		case NOTHING:
			return XmlAdhocCalculation.NOTHING;
		case COUNT:
			return XmlAdhocCalculation.COUNT;
		case SUM:
			return XmlAdhocCalculation.SUM;
		case AVERAGE:
			return XmlAdhocCalculation.AVERAGE;
		case LOWEST:
			return XmlAdhocCalculation.LOWEST;
		case HIGHEST:
			return XmlAdhocCalculation.HIGHEST;
		case STANDARD_DEVIATION:
			return XmlAdhocCalculation.STANDARD_DEVIATION;
		case VARIANCE:
			return XmlAdhocCalculation.VARIANCE;
		case FIRST:
			return XmlAdhocCalculation.FIRST;
		case DISTINCT_COUNT:
			return XmlAdhocCalculation.DISTINCT_COUNT;
		default:
			throw new AdhocException("Calculation " + adhocCalculation.name() + " not supported");
		}
	}

	protected XmlAdhocSort sort(AdhocSort adhocSort) {
		XmlAdhocSort xmlAdhocSort = new XmlAdhocSort();
		xmlAdhocSort.setName(adhocSort.getName());
		xmlAdhocSort.setOrderType(orderType(adhocSort.getOrderType()));
		return xmlAdhocSort;
	}

	protected XmlAdhocOrderType orderType(AdhocOrderType adhocOrderType) {
		if (adhocOrderType == null) {
			return null;
		}

		switch (adhocOrderType) {
		case ASCENDING:
			return XmlAdhocOrderType.ASCENDING;
		case DESCENDING:
			return XmlAdhocOrderType.DESCENDING;
		default:
			throw new AdhocException("Order type " + adhocOrderType.name() + " not supported");
		}
	}

	protected XmlAdhocStyle style(AdhocStyle adhocStyle) {
		if (adhocStyle == null) {
			return null;
		}

		XmlAdhocStyle xmlAdhocStyle = new XmlAdhocStyle();
		xmlAdhocStyle.setFont(font(adhocStyle.getFont()));
		xmlAdhocStyle.setTopBorder(pen(adhocStyle.getTopBorder()));
		xmlAdhocStyle.setLeftBorder(pen(adhocStyle.getLeftBorder()));
		xmlAdhocStyle.setBottomBorder(pen(adhocStyle.getBottomBorder()));
		xmlAdhocStyle.setRightBorder(pen(adhocStyle.getRightBorder()));
		xmlAdhocStyle.setForegroundColor(color(adhocStyle.getForegroundColor()));
		xmlAdhocStyle.setBackgroundColor(color(adhocStyle.getBackgroundColor()));
		xmlAdhocStyle.setHorizontalAlignment(horizontalAlignment(adhocStyle.getHorizontalAlignment()));
		xmlAdhocStyle.setVerticalAlignment(verticalAlignment(adhocStyle.getVerticalAlignment()));
		xmlAdhocStyle.setPattern(adhocStyle.getPattern());
		return xmlAdhocStyle;
	}

	protected String color(Color color) {
		if (color == null) {
			return null;
		}
		return String.valueOf(color.getRGB());
	}

	protected XmlAdhocFont font(AdhocFont adhocFont) {
		if (adhocFont == null) {
			return null;
		}

		XmlAdhocFont xmlAdhocFont = new XmlAdhocFont();
		xmlAdhocFont.setFontName(adhocFont.getFontName());
		xmlAdhocFont.setFontSize(adhocFont.getFontSize());
		xmlAdhocFont.setBold(adhocFont.getBold());
		xmlAdhocFont.setItalic(adhocFont.getItalic());
		xmlAdhocFont.setUnderline(adhocFont.getUnderline());
		xmlAdhocFont.setStrikeThrough(adhocFont.getStrikeThrough());
		return xmlAdhocFont;
	}

	protected XmlAdhocPen pen(AdhocPen adhocPen) {
		if (adhocPen == null) {
			return null;
		}

		XmlAdhocPen xmlAdhocPen = new XmlAdhocPen();
		xmlAdhocPen.setLineWidth(adhocPen.getLineWidth());
		xmlAdhocPen.setLineColor(color(adhocPen.getLineColor()));
		return xmlAdhocPen;
	}

	protected XmlAdhocHorizontalAlignment horizontalAlignment(AdhocHorizontalAlignment adhocHorizontalAlignment) {
		if (adhocHorizontalAlignment == null) {
			return null;
		}

		switch (adhocHorizontalAlignment) {
		case LEFT:
			return XmlAdhocHorizontalAlignment.LEFT;
		case CENTER:
			return XmlAdhocHorizontalAlignment.CENTER;
		case RIGHT:
			return XmlAdhocHorizontalAlignment.RIGHT;
		case JUSTIFIED:
			return XmlAdhocHorizontalAlignment.JUSTIFIED;
		default:
			throw new AdhocException("Horizontal alignment " + adhocHorizontalAlignment.name() + " not supported");
		}
	}

	protected XmlAdhocVerticalAlignment verticalAlignment(AdhocVerticalAlignment adhocVerticalAlignment) {
		if (adhocVerticalAlignment == null) {
			return null;
		}

		switch (adhocVerticalAlignment) {
		case TOP:
			return XmlAdhocVerticalAlignment.TOP;
		case MIDDLE:
			return XmlAdhocVerticalAlignment.MIDDLE;
		case BOTTOM:
			return XmlAdhocVerticalAlignment.BOTTOM;
		case JUSTIFIED:
			return XmlAdhocVerticalAlignment.JUSTIFIED;
		default:
			throw new AdhocException("Vertical alignment " + adhocVerticalAlignment.name() + " not supported");
		}
	}

	protected XmlAdhocPage page(AdhocPage adhocPage) {
		if (adhocPage == null) {
			return null;
		}

		XmlAdhocPage xmlAdhocPage = new XmlAdhocPage();
		xmlAdhocPage.setWidth(adhocPage.getWidth());
		xmlAdhocPage.setHeight(adhocPage.getHeight());
		xmlAdhocPage.setPageOrientation(pageOrientation(adhocPage.getPageOrientation()));
		xmlAdhocPage.setTopMargin(adhocPage.getTopMargin());
		xmlAdhocPage.setBottomMargin(adhocPage.getBottomMargin());
		xmlAdhocPage.setLeftMargin(adhocPage.getLeftMargin());
		xmlAdhocPage.setRightMargin(adhocPage.getRightMargin());
		xmlAdhocPage.setIgnorePageWidth(adhocPage.getIgnorePageWidth());
		return xmlAdhocPage;
	}

	protected XmlAdhocPageOrientation pageOrientation(AdhocPageOrientation adhocPageOrientation) {
		if (adhocPageOrientation == null) {
			return null;
		}

		switch (adhocPageOrientation) {
		case PORTRAIT:
			return XmlAdhocPageOrientation.PORTRAIT;
		case LANDSCAPE:
			return XmlAdhocPageOrientation.LANDSCAPE;
		default:
			throw new AdhocException("Page orientation " + adhocPageOrientation.name() + " not supported");
		}
	}

	protected XmlAdhocComponent component(AdhocComponent adhocComponent) {
		if (adhocComponent instanceof AdhocTextField) {
			XmlAdhocTextField xmlAdhocComponent = new XmlAdhocTextField();
			textField((AdhocTextField) adhocComponent, xmlAdhocComponent);
			return xmlAdhocComponent;
		}
		if (adhocComponent instanceof AdhocCategoryChart) {
			XmlAdhocCategoryChart xmlAdhocComponent = new XmlAdhocCategoryChart();
			categoryChart((AdhocCategoryChart) adhocComponent, xmlAdhocComponent);
			return xmlAdhocComponent;
		}
		if (adhocComponent instanceof AdhocChart) {
			XmlAdhocChart xmlAdhocComponent = new XmlAdhocChart();
			chart((AdhocChart) adhocComponent, xmlAdhocComponent);
			return xmlAdhocComponent;
		}
		else {
			XmlAdhocComponent xmlAdhocComponent = new XmlAdhocComponent();
			component(adhocComponent, xmlAdhocComponent);
			return xmlAdhocComponent;
		}
	}

	protected void component(AdhocComponent adhocComponent, XmlAdhocComponent xmlAdhocComponent) {
		xmlAdhocComponent.setKey(adhocComponent.getKey());
		xmlAdhocComponent.setStyle(style(adhocComponent.getStyle()));
		xmlAdhocComponent.setWidth(adhocComponent.getWidth());
		xmlAdhocComponent.setHeight(adhocComponent.getHeight());
	}

	protected void textField(AdhocTextField adhocTextField, XmlAdhocTextField xmlAdhocTextField) {
		component(adhocTextField, xmlAdhocTextField);
		xmlAdhocTextField.setText(adhocTextField.getText());
	}

	protected void chart(AdhocChart adhocChart, XmlAdhocChart xmlAdhocChart) {
		component(adhocChart, xmlAdhocChart);
		xmlAdhocChart.setTitle(adhocChart.getTitle());
		xmlAdhocChart.setTitleFont(font(adhocChart.getTitleFont()));
		xmlAdhocChart.setTitleColor(color(adhocChart.getTitleColor()));
		xmlAdhocChart.setShowLegend(adhocChart.getShowLegend());
	}

	protected XmlAdhocOrientation orientation(AdhocOrientation adhocOrientation) {
		if (adhocOrientation == null) {
			return null;
		}

		switch (adhocOrientation) {
		case HORIZONTAL:
			return XmlAdhocOrientation.HORIZONTAL;
		case VERTICAL:
			return XmlAdhocOrientation.VERTICAL;
		default:
			throw new AdhocException("Orientation " + adhocOrientation.name() + " not supported");
		}
	}

	protected XmlAdhocAxisFormat axisFormat(AdhocAxisFormat adhocAxisFormat) {
		if (adhocAxisFormat == null) {
			return null;
		}

		XmlAdhocAxisFormat xmlAdhocAxisFormat = new XmlAdhocAxisFormat();
		xmlAdhocAxisFormat.setLabel(adhocAxisFormat.getLabel());
		xmlAdhocAxisFormat.setLabelFont(font(adhocAxisFormat.getLabelFont()));
		xmlAdhocAxisFormat.setLabelColor(color(adhocAxisFormat.getLabelColor()));
		return xmlAdhocAxisFormat;
	}

	protected void categoryChart(AdhocCategoryChart adhocCategoryChart, XmlAdhocCategoryChart xmlAdhocCategoryChart) {
		chart(adhocCategoryChart, xmlAdhocCategoryChart);
		xmlAdhocCategoryChart.setType(categoryChartType(adhocCategoryChart.getType()));
		xmlAdhocCategoryChart.setCategory(adhocCategoryChart.getCategory());
		if (adhocCategoryChart.getSeries() != null && !adhocCategoryChart.getSeries().isEmpty()) {
			for (AdhocCategoryChartSerie adhocCategoryChartSerie : adhocCategoryChart.getSeries()) {
				xmlAdhocCategoryChart.getSerie().add(categoryChartSerie(adhocCategoryChartSerie));
			}
		}
		if (adhocCategoryChart.getSeriesColors() != null && !adhocCategoryChart.getSeriesColors().isEmpty()) {
			for (Color adhocSeriesColor : adhocCategoryChart.getSeriesColors()) {
				xmlAdhocCategoryChart.getSeriesColors().add(color(adhocSeriesColor));
			}
		}
		xmlAdhocCategoryChart.setCategoryAxisFormat(axisFormat(adhocCategoryChart.getCategoryAxisFormat()));
		xmlAdhocCategoryChart.setValueAxisFormat(axisFormat(adhocCategoryChart.getValueAxisFormat()));
		xmlAdhocCategoryChart.setOrientation(orientation(adhocCategoryChart.getOrientation()));
		xmlAdhocCategoryChart.setUseSeriesAsCategory(adhocCategoryChart.getUseSeriesAsCategory());
	}

	protected XmlAdhocCategoryChartSerie categoryChartSerie(AdhocCategoryChartSerie adhocCategoryChartSerie) {
		XmlAdhocCategoryChartSerie xmlAdhocCategoryChartSerie = new XmlAdhocCategoryChartSerie();
		xmlAdhocCategoryChartSerie.setSeries(adhocCategoryChartSerie.getSeries());
		xmlAdhocCategoryChartSerie.setValue(adhocCategoryChartSerie.getValue());
		xmlAdhocCategoryChartSerie.setLabel(adhocCategoryChartSerie.getLabel());
		return xmlAdhocCategoryChartSerie;
	}

	protected XmlAdhocCategoryChartType categoryChartType(AdhocCategoryChartType adhocCategoryChartType) {
		if (adhocCategoryChartType == null) {
			return null;
		}

		switch (adhocCategoryChartType) {
		case AREA:
			return XmlAdhocCategoryChartType.AREA;
		case STACKEDAREA:
			return XmlAdhocCategoryChartType.STACKEDAREA;
		case BAR:
			return XmlAdhocCategoryChartType.BAR;
		case STACKEDBAR:
			return XmlAdhocCategoryChartType.STACKEDBAR;
		case BAR3D:
			return XmlAdhocCategoryChartType.BAR_3_D;
		case STACKEDBAR3D:
			return XmlAdhocCategoryChartType.STACKEDBAR_3_D;
		case LINE:
			return XmlAdhocCategoryChartType.LINE;
		case LAYEREDBAR:
			return XmlAdhocCategoryChartType.LAYEREDBAR;
		default:
			throw new AdhocException("Category chart type " + adhocCategoryChartType.name() + " not supported");
		}
	}

	protected XmlAdhocFilter filter(AdhocFilter adhocFilter) {
		if (adhocFilter == null) {
			return null;
		}

		XmlAdhocFilter xmlAdhocFilter = new XmlAdhocFilter();
		if (adhocFilter.getRestrictions() != null && !adhocFilter.getRestrictions().isEmpty()) {
			for (AdhocRestriction adhocRestriction : adhocFilter.getRestrictions()) {
				xmlAdhocFilter.getRestriction().add(restriction(adhocRestriction));
			}
		}
		return xmlAdhocFilter;
	}

	protected XmlAdhocRestriction restriction(AdhocRestriction adhocRestriction) {
		if (adhocRestriction instanceof AdhocValueRestriction) {
			XmlAdhocValueRestriction xmlAdhocRestriction = new XmlAdhocValueRestriction();
			valueRestriction((AdhocValueRestriction) adhocRestriction, xmlAdhocRestriction);
			return xmlAdhocRestriction;
		}
		else {
			XmlAdhocRestriction xmlAdhocRestriction = new XmlAdhocRestriction();
			restriction(adhocRestriction, xmlAdhocRestriction);
			return xmlAdhocRestriction;
		}
	}

	protected void restriction(AdhocRestriction adhocRestriction, XmlAdhocRestriction xmlAdhocRestriction) {
		xmlAdhocRestriction.setKey(adhocRestriction.getKey());
		if (adhocRestriction.getProperties() != null && !adhocRestriction.getProperties().isEmpty()) {
			properties(adhocRestriction.getProperties(), xmlAdhocRestriction.getProperty());
		}
	}

	protected void valueRestriction(AdhocValueRestriction adhocValueRestriction, XmlAdhocValueRestriction xmlAdhocValueRestriction) {
		restriction(adhocValueRestriction, xmlAdhocValueRestriction);
		xmlAdhocValueRestriction.setName(adhocValueRestriction.getName());
		xmlAdhocValueRestriction.setOperator(valueOperator(adhocValueRestriction.getOperator()));
		if (adhocValueRestriction.getValues() != null && !adhocValueRestriction.getValues().isEmpty()) {
			for (String value : adhocValueRestriction.getValues()) {
				xmlAdhocValueRestriction.getValue().add(value);
			}
		}
	}

	protected XmlAdhocValueOperator valueOperator(AdhocValueOperator adhocValueOperator) {
		if (adhocValueOperator == null) {
			return null;
		}

		switch (adhocValueOperator) {
		case EQUAL:
			return XmlAdhocValueOperator.EQUAL;
		case UNEQUAL:
			return XmlAdhocValueOperator.UNEQUAL;
		case IN:
			return XmlAdhocValueOperator.IN;
		case NOT_IN:
			return XmlAdhocValueOperator.NOT_IN;
		case LIKE:
			return XmlAdhocValueOperator.LIKE;
		case NOT_LIKE:
			return XmlAdhocValueOperator.NOT_LIKE;
		case START_WITH:
			return XmlAdhocValueOperator.START_WITH;
		case NOT_START_WITH:
			return XmlAdhocValueOperator.NOT_START_WITH;
		case END_WITH:
			return XmlAdhocValueOperator.END_WITH;
		case NOT_END_WITH:
			return XmlAdhocValueOperator.NOT_END_WITH;
		case GREATER:
			return XmlAdhocValueOperator.GREATER;
		case GREATER_OR_EQUAL:
			return XmlAdhocValueOperator.GREATER_OR_EQUAL;
		case SMALLER:
			return XmlAdhocValueOperator.SMALLER;
		case SMALLER_OR_EQUAL:
			return XmlAdhocValueOperator.SMALLER_OR_EQUAL;
		case BETWEEN:
			return XmlAdhocValueOperator.BETWEEN;
		case NOT_BETWEEN:
			return XmlAdhocValueOperator.NOT_BETWEEN;
		case IS_NOT_NULL:
			return XmlAdhocValueOperator.IS_NOT_NULL;
		case IS_NULL:
			return XmlAdhocValueOperator.IS_NULL;
		default:
			throw new AdhocException("Value operator " + adhocValueOperator.name() + " not supported");
		}
	}

}
