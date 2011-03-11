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

package net.sf.dynamicreports.report.builder;

import java.awt.Color;
import java.util.Locale;

import net.sf.dynamicreports.report.base.DRReportTemplate;
import net.sf.dynamicreports.report.builder.style.SimpleStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.WhenNoDataType;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class ReportTemplateBuilder extends AbstractBuilder<ReportTemplateBuilder, DRReportTemplate> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected ReportTemplateBuilder() {
		super(new DRReportTemplate());
	}

	public ReportTemplateBuilder setLocale(Locale locale) {
		getObject().setLocale(locale);
		return this;
	}

	public ReportTemplateBuilder setShowColumnTitle(Boolean showColumnTitle) {
		getObject().setShowColumnTitle(showColumnTitle);
		return this;
	}

	public ReportTemplateBuilder ignorePagination() {
		return setIgnorePagination(true);
	}

	public ReportTemplateBuilder setIgnorePagination(Boolean ignorePagination) {
		getObject().setIgnorePagination(ignorePagination);
		return this;
	}

	public ReportTemplateBuilder setWhenNoDataType(WhenNoDataType whenNoDataType) {
		getObject().setWhenNoDataType(whenNoDataType);
		return this;
	}

	public ReportTemplateBuilder titleOnANewPage() {
		return setTitleOnANewPage(true);
	}

	public ReportTemplateBuilder setTitleOnANewPage(Boolean titleOnANewPage) {
		getObject().setTitleOnANewPage(titleOnANewPage);
		return this;
	}

	public ReportTemplateBuilder summaryOnANewPage() {
		return setSummaryOnANewPage(true);
	}

	public ReportTemplateBuilder setSummaryOnANewPage(Boolean summaryOnANewPage) {
		getObject().setSummaryOnANewPage(summaryOnANewPage);
		return this;
	}

	public ReportTemplateBuilder summaryWithPageHeaderAndFooter() {
		return setSummaryWithPageHeaderAndFooter(true);
	}

	public ReportTemplateBuilder setSummaryWithPageHeaderAndFooter(Boolean summaryWithPageHeaderAndFooter) {
		getObject().setSummaryWithPageHeaderAndFooter(summaryWithPageHeaderAndFooter);
		return this;
	}

	public ReportTemplateBuilder floatColumnFooter() {
		return setFloatColumnFooter(true);
	}

	public ReportTemplateBuilder setFloatColumnFooter(Boolean floatColumnFooter) {
		getObject().setFloatColumnFooter(floatColumnFooter);
		return this;
	}

	public ReportTemplateBuilder highlightDetailOddRows() {
		return setHighlightDetailOddRows(true);
	}

	public ReportTemplateBuilder setHighlightDetailOddRows(Boolean highlightDetailOddRows) {
		getObject().setHighlightDetailOddRows(highlightDetailOddRows);
		return this;
	}

	public ReportTemplateBuilder setDetailOddRowStyle(SimpleStyleBuilder detailOddRowStyle) {
		if (detailOddRowStyle != null) {
			getObject().setDetailOddRowStyle(detailOddRowStyle.build());
		}
		else {
			getObject().setDetailOddRowStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder highlightDetailEvenRows() {
		return setHighlightDetailEvenRows(true);
	}

	public ReportTemplateBuilder setHighlightDetailEvenRows(Boolean highlightDetailEvenRows) {
		getObject().setHighlightDetailEvenRows(highlightDetailEvenRows);
		return this;
	}

	public ReportTemplateBuilder setDetailEvenRowStyle(SimpleStyleBuilder detailEvenRowStyle) {
		if (detailEvenRowStyle != null) {
			getObject().setDetailEvenRowStyle(detailEvenRowStyle.build());
		}
		else {
			getObject().setDetailEvenRowStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setTextStyle(StyleBuilder textStyle) {
		if (textStyle != null) {
			getObject().setTextStyle(textStyle.build());
		}
		else {
			getObject().setTextStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setColumnTitleStyle(StyleBuilder columnTitleStyle) {
		if (columnTitleStyle != null) {
			getObject().setColumnTitleStyle(columnTitleStyle.build());
		}
		else {
			getObject().setColumnTitleStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setColumnStyle(StyleBuilder columnStyle) {
		if (columnStyle != null) {
			getObject().setColumnStyle(columnStyle.build());
		}
		else {
			getObject().setColumnStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setGroupTitleStyle(StyleBuilder groupTitleStyle) {
		if (groupTitleStyle != null) {
			getObject().setGroupTitleStyle(groupTitleStyle.build());
		}
		else {
			getObject().setGroupTitleStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setGroupStyle(StyleBuilder groupStyle) {
		if (groupStyle != null) {
			getObject().setGroupStyle(groupStyle.build());
		}
		else {
			getObject().setGroupStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setSubtotalStyle(StyleBuilder subtotalStyle) {
		if (subtotalStyle != null) {
			getObject().setSubtotalStyle(subtotalStyle.build());
		}
		else {
			getObject().setSubtotalStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setImageStyle(StyleBuilder imageStyle) {
		if (imageStyle != null) {
			getObject().setImageStyle(imageStyle.build());
		}
		else {
			getObject().setImageStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setChartStyle(StyleBuilder chartStyle) {
		if (chartStyle != null) {
			getObject().setChartStyle(chartStyle.build());
		}
		else {
			getObject().setChartStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setBarcodeStyle(StyleBuilder barcodeStyle) {
		if (barcodeStyle != null) {
			getObject().setBarcodeStyle(barcodeStyle.build());
		}
		else {
			getObject().setBarcodeStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setPageFormat(PageType pageType) {
		return setPageFormat(pageType, PageOrientation.PORTRAIT);
	}

	public ReportTemplateBuilder setPageFormat(PageType pageType, PageOrientation orientation) {
		getObject().setPageFormat(pageType, orientation);
		return this;
	}

	public ReportTemplateBuilder setPageMargin(MarginBuilder pageMargin) {
		if (pageMargin != null) {
			getObject().setPageMargin(pageMargin.build());
		}
		else {
			getObject().setPageMargin(null);
		}
		return this;
	}

	public ReportTemplateBuilder setPageColumnsPerPage(Integer pageColumnsPerPage) {
		getObject().setPageColumnsPerPage(pageColumnsPerPage);
		return this;
	}

	public ReportTemplateBuilder setPageColumnSpace(Integer pageColumnSpace) {
		getObject().setPageColumnSpace(pageColumnSpace);
		return this;
	}

	public ReportTemplateBuilder setColumnPrintRepeatedDetailValues(Boolean columnPrintRepeatedDetailValues) {
		getObject().setColumnPrintRepeatedDetailValues(columnPrintRepeatedDetailValues);
		return this;
	}

	public ReportTemplateBuilder setColumnWidth(Integer columnWidth) {
		getObject().setColumnWidth(columnWidth);
		return this;
	}

	public ReportTemplateBuilder setGroupHeaderLayout(GroupHeaderLayout groupHeaderLayout) {
		getObject().setGroupHeaderLayout(groupHeaderLayout);
		return this;
	}

	public ReportTemplateBuilder setGroupHideColumn(Boolean groupHideColumn) {
		getObject().setGroupHideColumn(groupHideColumn);
		return this;
	}

	public ReportTemplateBuilder setGroupShowColumnHeaderAndFooter(Boolean groupShowColumnHeaderAndFooter) {
		getObject().setGroupShowColumnHeaderAndFooter(groupShowColumnHeaderAndFooter);
		return this;
	}

	public ReportTemplateBuilder setGroupPadding(Integer groupPadding) {
		getObject().setGroupPadding(groupPadding);
		return this;
	}

	public ReportTemplateBuilder setGroupStartInNewPage(Boolean groupStartInNewPage) {
		getObject().setGroupStartInNewPage(groupStartInNewPage);
		return this;
	}

	public ReportTemplateBuilder setGroupStartInNewColumn(Boolean groupStartInNewColumn) {
		getObject().setGroupStartInNewColumn(groupStartInNewColumn);
		return this;
	}

	public ReportTemplateBuilder setGroupReprintHeaderOnEachPage(Boolean groupReprintHeaderOnEachPage) {
		getObject().setGroupReprintHeaderOnEachPage(groupReprintHeaderOnEachPage);
		return this;
	}

	public ReportTemplateBuilder setTextFieldWidth(Integer textFieldWidth) {
		getObject().setTextFieldWidth(textFieldWidth);
		return this;
	}

	public ReportTemplateBuilder setImageHeight(Integer imageHeight) {
		getObject().setImageHeight(imageHeight);
		return this;
	}

	public ReportTemplateBuilder setImageWidth(Integer imageWidth) {
		getObject().setImageWidth(imageWidth);
		return this;
	}

	public ReportTemplateBuilder setListgap(Integer listgap) {
		getObject().setListgap(listgap);
		return this;
	}

	public ReportTemplateBuilder setChartHeight(Integer chartHeight) {
		getObject().setChartHeight(chartHeight);
		return this;
	}

	public ReportTemplateBuilder setChartWidth(Integer chartWidth) {
		getObject().setChartWidth(chartWidth);
		return this;
	}

	public ReportTemplateBuilder chartSeriesColors(Color ...seriesColors) {
		return addChartSeriesColor(seriesColors);
	}

	public ReportTemplateBuilder addChartSeriesColor(Color ...seriesColors) {
		Validate.notNull(seriesColors, "seriesColors must not be null");
		for (Color seriesColor : seriesColors) {
			getObject().addChartSeriesColor(seriesColor);
		}
		return this;
	}

	public ReportTemplateBuilder setBarcodeHeight(Integer barcodeHeight) {
		getObject().setBarcodeHeight(barcodeHeight);
		return this;
	}

	public ReportTemplateBuilder setBarcodeWidth(Integer barcodeWidth) {
		getObject().setBarcodeWidth(barcodeWidth);
		return this;
	}

	public ReportTemplateBuilder setSubreportHeight(Integer subreportHeight) {
		getObject().setSubreportHeight(subreportHeight);
		return this;
	}

	public ReportTemplateBuilder setSubreportWidth(Integer subreportWidth) {
		getObject().setSubreportWidth(subreportWidth);
		return this;
	}

	public ReportTemplateBuilder setCrosstabHeight(Integer crosstabHeight) {
		getObject().setCrosstabHeight(crosstabHeight);
		return this;
	}

	public ReportTemplateBuilder setCrosstabWidth(Integer crosstabWidth) {
		getObject().setCrosstabWidth(crosstabWidth);
		return this;
	}

	public ReportTemplateBuilder crosstabHighlightOddRows() {
		return setCrosstabHighlightOddRows(true);
	}

	public ReportTemplateBuilder setCrosstabHighlightOddRows(Boolean crosstabHighlightOddRows) {
		getObject().setCrosstabHighlightOddRows(crosstabHighlightOddRows);
		return this;
	}

	public ReportTemplateBuilder setCrosstabOddRowStyle(SimpleStyleBuilder crosstabOddRowStyle) {
		if (crosstabOddRowStyle != null) {
			getObject().setCrosstabOddRowStyle(crosstabOddRowStyle.build());
		}
		else {
			getObject().setCrosstabOddRowStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder crosstabHighlightEvenRows() {
		return setCrosstabHighlightEvenRows(true);
	}

	public ReportTemplateBuilder setCrosstabHighlightEvenRows(Boolean crosstabHighlightEvenRows) {
		getObject().setCrosstabHighlightEvenRows(crosstabHighlightEvenRows);
		return this;
	}

	public ReportTemplateBuilder setCrosstabEvenRowStyle(SimpleStyleBuilder crosstabEvenRowStyle) {
		if (crosstabEvenRowStyle != null) {
			getObject().setCrosstabEvenRowStyle(crosstabEvenRowStyle.build());
		}
		else {
			getObject().setCrosstabEvenRowStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setCrosstabGroupStyle(StyleBuilder crosstabGroupStyle) {
		if (crosstabGroupStyle != null) {
			getObject().setCrosstabGroupStyle(crosstabGroupStyle.build());
		}
		else {
			getObject().setCrosstabGroupStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setCrosstabGroupTotalStyle(StyleBuilder crosstabGroupTotalStyle) {
		if (crosstabGroupTotalStyle != null) {
			getObject().setCrosstabGroupTotalStyle(crosstabGroupTotalStyle.build());
		}
		else {
			getObject().setCrosstabGroupTotalStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setCrosstabGrandTotalStyle(StyleBuilder crosstabGrandTotalStyle) {
		if (crosstabGrandTotalStyle != null) {
			getObject().setCrosstabGrandTotalStyle(crosstabGrandTotalStyle.build());
		}
		else {
			getObject().setCrosstabGrandTotalStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setCrosstabCellStyle(StyleBuilder crosstabCellStyle) {
		if (crosstabCellStyle != null) {
			getObject().setCrosstabCellStyle(crosstabCellStyle.build());
		}
		else {
			getObject().setCrosstabCellStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setCrosstabMeasureTitleStyle(StyleBuilder crosstabMeasureTitleStyle) {
		if (crosstabMeasureTitleStyle != null) {
			getObject().setCrosstabMeasureTitleStyle(crosstabMeasureTitleStyle.build());
		}
		else {
			getObject().setCrosstabMeasureTitleStyle(null);
		}
		return this;
	}

	public ReportTemplateBuilder setDefaultSplitType(SplitType defaultSplitType) {
		getObject().setDefaultSplitType(defaultSplitType);
		return this;
	}

	public ReportTemplateBuilder setTitleSplitType(SplitType titleSplitType) {
		getObject().setTitleSplitType(titleSplitType);
		return this;
	}

	public ReportTemplateBuilder setPageHeaderSplitType(SplitType pageHeaderSplitType) {
		getObject().setPageHeaderSplitType(pageHeaderSplitType);
		return this;
	}

	public ReportTemplateBuilder setPageFooterSplitType(SplitType pageFooterSplitType) {
		getObject().setPageFooterSplitType(pageFooterSplitType);
		return this;
	}

	public ReportTemplateBuilder setColumnHeaderSplitType(SplitType columnHeaderSplitType) {
		getObject().setColumnHeaderSplitType(columnHeaderSplitType);
		return this;
	}

	public ReportTemplateBuilder setColumnFooterSplitType(SplitType columnFooterSplitType) {
		getObject().setColumnFooterSplitType(columnFooterSplitType);
		return this;
	}

	public ReportTemplateBuilder setGroupHeaderSplitType(SplitType groupHeaderSplitType) {
		getObject().setGroupHeaderSplitType(groupHeaderSplitType);
		return this;
	}

	public ReportTemplateBuilder setGroupFooterSplitType(SplitType groupFooterSplitType) {
		getObject().setGroupFooterSplitType(groupFooterSplitType);
		return this;
	}

	public ReportTemplateBuilder setDetailSplitType(SplitType detailSplitType) {
		getObject().setDetailSplitType(detailSplitType);
		return this;
	}

	public ReportTemplateBuilder setLastPageFooterSplitType(SplitType lastPageFooterSplitType) {
		getObject().setLastPageFooterSplitType(lastPageFooterSplitType);
		return this;
	}

	public ReportTemplateBuilder setSummarySplitType(SplitType summarySplitType) {
		getObject().setSummarySplitType(summarySplitType);
		return this;
	}

	public ReportTemplateBuilder setNoDataSplitType(SplitType noDataSplitType) {
		getObject().setNoDataSplitType(noDataSplitType);
		return this;
	}

	public ReportTemplateBuilder setBackgroundSplitType(SplitType backgroundSplitType) {
		getObject().setBackgroundSplitType(backgroundSplitType);
		return this;
	}

	public DRReportTemplate getReportTemplate() {
		return build();
	}
}
