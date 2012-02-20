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

package net.sf.dynamicreports.report.base;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.sf.dynamicreports.report.base.component.DRComponent;
import net.sf.dynamicreports.report.base.style.DRSimpleStyle;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.GroupFooterPosition;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.RunDirection;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.definition.DRIReportTemplate;
import net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import net.sf.dynamicreports.report.definition.style.DRIStyle;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRReportTemplate implements DRIReportTemplate {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Locale locale;
	private Boolean showColumnTitle;
	private Boolean ignorePagination;
	private WhenNoDataType whenNoDataType;
	private Boolean titleOnANewPage;
	private Boolean summaryOnANewPage;
	private Boolean summaryWithPageHeaderAndFooter;
	private Boolean floatColumnFooter;
	private Orientation printOrder;
	private RunDirection columnDirection;
	private String language;
	private Boolean useFieldNameAsDescription;
	//style
	private Boolean highlightDetailOddRows;
	private DRSimpleStyle detailOddRowStyle;
	private Boolean highlightDetailEvenRows;
	private DRSimpleStyle detailEvenRowStyle;
	private DRStyle textStyle;
	private DRStyle columnTitleStyle;
	private DRStyle columnStyle;
	private DRStyle groupTitleStyle;
	private DRStyle groupStyle;
	private DRStyle subtotalStyle;
	private DRStyle imageStyle;
	private DRStyle chartStyle;
	private DRStyle barcodeStyle;
	//page
	private Integer pageWidth;
	private Integer pageHeight;
	private PageOrientation pageOrientation;
	private DRMargin pageMargin;
	private Integer pageColumnsPerPage;
	private Integer pageColumnSpace;
	private Boolean ignorePageWidth;
	//column
	private Boolean columnPrintRepeatedDetailValues;
	private Integer columnWidth;
	//group
	private GroupHeaderLayout groupHeaderLayout;
	private Boolean groupHideColumn;
	private Boolean groupShowColumnHeaderAndFooter;
	private Integer groupPadding;
	private Boolean groupStartInNewPage;
	private Boolean groupStartInNewColumn;
	private Boolean groupReprintHeaderOnEachPage;
	private Boolean groupResetPageNumber;
	private GroupFooterPosition groupFooterPosition;
	private Boolean groupKeepTogether;
	//table of contents
	public Boolean tableOfContents;
	public DRITableOfContentsCustomizer tableOfContentsCustomizer;
	//text field
	private Integer textFieldWidth;
	//image
	private Integer imageWidth;
	private Integer imageHeight;
	//list
	private Integer listgap;
	//chart
	private Integer chartWidth;
	private Integer chartHeight;
	private List<Color> chartSeriesColors;
	//barcode
	private Integer barcodeWidth;
	private Integer barcodeHeight;
	//subreport
	private Integer subreportWidth;
	private Integer subreportHeight;
	//crosstab
	private Integer crosstabWidth;
	private Integer crosstabHeight;
	private Boolean crosstabHighlightOddRows;
	private DRSimpleStyle crosstabOddRowStyle;
	private Boolean crosstabHighlightEvenRows;
	private DRSimpleStyle crosstabEvenRowStyle;
	private DRStyle crosstabGroupStyle;
	private DRStyle crosstabGroupTotalStyle;
	private DRStyle crosstabGrandTotalStyle;
	private DRStyle crosstabCellStyle;
	private DRStyle crosstabMeasureTitleStyle;
	//boolean
	private BooleanComponentType booleanComponentType;
	private Integer booleanColumnImageWidth;
	private Integer booleanColumnImageHeight;
	private DRIStyle booleanColumnStyle;
	//split
	private SplitType defaultSplitType;
	private SplitType titleSplitType;
	private SplitType pageHeaderSplitType;
	private SplitType pageFooterSplitType;
	private SplitType columnHeaderSplitType;
	private SplitType columnFooterSplitType;
	private SplitType groupHeaderSplitType;
	private SplitType groupFooterSplitType;
	private SplitType detailHeaderSplitType;
	private SplitType detailSplitType;
	private SplitType detailFooterSplitType;
	private SplitType lastPageFooterSplitType;
	private SplitType summarySplitType;
	private SplitType noDataSplitType;
	private SplitType backgroundSplitType;
	//band style
	private DRStyle titleStyle;
	private DRStyle pageHeaderStyle;
	private DRStyle pageFooterStyle;
	private DRStyle columnHeaderStyle;
	private DRStyle columnFooterStyle;
	private DRStyle groupHeaderStyle;
	private DRStyle groupFooterStyle;
	private DRStyle detailHeaderStyle;
	private DRStyle detailStyle;
	private DRStyle detailFooterStyle;
	private DRStyle lastPageFooterStyle;
	private DRStyle summaryStyle;
	private DRStyle noDataStyle;
	private DRStyle backgroundStyle;
	//band background component
	private DRComponent titleBackgroundComponent;
	private DRComponent pageHeaderBackgroundComponent;
	private DRComponent pageFooterBackgroundComponent;
	private DRComponent columnHeaderBackgroundComponent;
	private DRComponent columnFooterBackgroundComponent;
	private DRComponent groupHeaderBackgroundComponent;
	private DRComponent groupFooterBackgroundComponent;
	private DRComponent detailHeaderBackgroundComponent;
	private DRComponent detailBackgroundComponent;
	private DRComponent detailFooterBackgroundComponent;
	private DRComponent lastPageFooterBackgroundComponent;
	private DRComponent summaryBackgroundComponent;
	private DRComponent noDataBackgroundComponent;
	private DRComponent backgroundBackgroundComponent;

	public DRReportTemplate() {
		this.chartSeriesColors = new ArrayList<Color>();
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Boolean getShowColumnTitle() {
		return showColumnTitle;
	}

	public void setShowColumnTitle(Boolean showColumnTitle) {
		this.showColumnTitle = showColumnTitle;
	}

	public Boolean getIgnorePagination() {
		return ignorePagination;
	}

	public void setIgnorePagination(Boolean ignorePagination) {
		this.ignorePagination = ignorePagination;
	}

	public WhenNoDataType getWhenNoDataType() {
		return whenNoDataType;
	}

	public void setWhenNoDataType(WhenNoDataType whenNoDataType) {
		this.whenNoDataType = whenNoDataType;
	}

	public Boolean getTitleOnANewPage() {
		return titleOnANewPage;
	}

	public void setTitleOnANewPage(Boolean titleOnANewPage) {
		this.titleOnANewPage = titleOnANewPage;
	}

	public Boolean getSummaryOnANewPage() {
		return summaryOnANewPage;
	}

	public void setSummaryOnANewPage(Boolean summaryOnANewPage) {
		this.summaryOnANewPage = summaryOnANewPage;
	}

	public Boolean getSummaryWithPageHeaderAndFooter() {
		return summaryWithPageHeaderAndFooter;
	}

	public void setSummaryWithPageHeaderAndFooter(Boolean summaryWithPageHeaderAndFooter) {
		this.summaryWithPageHeaderAndFooter = summaryWithPageHeaderAndFooter;
	}

	public Boolean getFloatColumnFooter() {
		return floatColumnFooter;
	}

	public void setFloatColumnFooter(Boolean floatColumnFooter) {
		this.floatColumnFooter = floatColumnFooter;
	}

	public Orientation getPrintOrder() {
		return printOrder;
	}

	public void setPrintOrder(Orientation printOrder) {
		this.printOrder = printOrder;
	}

	public RunDirection getColumnDirection() {
		return columnDirection;
	}

	public void setColumnDirection(RunDirection columnDirection) {
		this.columnDirection = columnDirection;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getUseFieldNameAsDescription() {
		return useFieldNameAsDescription;
	}

	public void setUseFieldNameAsDescription(Boolean useFieldNameAsDescription) {
		this.useFieldNameAsDescription = useFieldNameAsDescription;
	}

	public Boolean getHighlightDetailOddRows() {
		return highlightDetailOddRows;
	}

	public void setHighlightDetailOddRows(Boolean highlightDetailOddRows) {
		this.highlightDetailOddRows = highlightDetailOddRows;
	}

	public DRSimpleStyle getDetailOddRowStyle() {
		return detailOddRowStyle;
	}

	public void setDetailOddRowStyle(DRSimpleStyle detailOddRowStyle) {
		this.detailOddRowStyle = detailOddRowStyle;
	}

	public Boolean getHighlightDetailEvenRows() {
		return highlightDetailEvenRows;
	}

	public void setHighlightDetailEvenRows(Boolean highlightDetailEvenRows) {
		this.highlightDetailEvenRows = highlightDetailEvenRows;
	}

	public DRSimpleStyle getDetailEvenRowStyle() {
		return detailEvenRowStyle;
	}

	public void setDetailEvenRowStyle(DRSimpleStyle detailEvenRowStyle) {
		this.detailEvenRowStyle = detailEvenRowStyle;
	}

	public DRStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(DRStyle textStyle) {
		this.textStyle = textStyle;
	}

	public DRStyle getColumnTitleStyle() {
		return columnTitleStyle;
	}

	public void setColumnTitleStyle(DRStyle columnTitleStyle) {
		this.columnTitleStyle = columnTitleStyle;
	}

	public DRStyle getColumnStyle() {
		return columnStyle;
	}

	public void setColumnStyle(DRStyle columnStyle) {
		this.columnStyle = columnStyle;
	}

	public DRStyle getGroupTitleStyle() {
		return groupTitleStyle;
	}

	public void setGroupTitleStyle(DRStyle groupTitleStyle) {
		this.groupTitleStyle = groupTitleStyle;
	}

	public DRStyle getGroupStyle() {
		return groupStyle;
	}

	public void setGroupStyle(DRStyle groupStyle) {
		this.groupStyle = groupStyle;
	}

	public DRStyle getSubtotalStyle() {
		return subtotalStyle;
	}

	public void setSubtotalStyle(DRStyle subtotalStyle) {
		this.subtotalStyle = subtotalStyle;
	}

	public DRStyle getImageStyle() {
		return imageStyle;
	}

	public void setImageStyle(DRStyle imageStyle) {
		this.imageStyle = imageStyle;
	}

	public DRStyle getChartStyle() {
		return chartStyle;
	}

	public void setChartStyle(DRStyle chartStyle) {
		this.chartStyle = chartStyle;
	}

	public DRIStyle getBarcodeStyle() {
		return barcodeStyle;
	}

	public void setBarcodeStyle(DRStyle barcodeStyle) {
		this.barcodeStyle = barcodeStyle;
	}

	public void setPageFormat(PageType pageType, PageOrientation orientation) {
		Validate.notNull(pageType, "pageType must not be null");
		Validate.notNull(orientation, "orientation must not be null");
		if (orientation.equals(PageOrientation.PORTRAIT)) {
			setPageWidth(pageType.getWidth());
			setPageHeight(pageType.getHeight());
		}
		else {
			setPageWidth(pageType.getHeight());
			setPageHeight(pageType.getWidth());
		}
		setPageOrientation(orientation);
	}

	public Integer getPageWidth() {
		return pageWidth;
	}

	private void setPageWidth(Integer pageWidth) {
		if (pageWidth != null) {
			Validate.isTrue(pageWidth >= 0, "pageWidth must be >= 0");
		}
		this.pageWidth = pageWidth;
	}

	public Integer getPageHeight() {
		return pageHeight;
	}

	private void setPageHeight(Integer pageHeight) {
		if (pageHeight != null) {
			Validate.isTrue(pageHeight >= 0, "pageHeight must be >= 0");
		}
		this.pageHeight = pageHeight;
	}

	public PageOrientation getPageOrientation() {
		return pageOrientation;
	}

	private void setPageOrientation(PageOrientation pageOrientation) {
		this.pageOrientation = pageOrientation;
	}

	public DRMargin getPageMargin() {
		return pageMargin;
	}

	public void setPageMargin(DRMargin pageMargin) {
		this.pageMargin = pageMargin;
	}

	public Integer getPageColumnsPerPage() {
		return pageColumnsPerPage;
	}

	public void setPageColumnsPerPage(Integer pageColumnsPerPage) {
		if (pageColumnsPerPage != null) {
			Validate.isTrue(pageColumnsPerPage >= 1, "pageColumnsPerPage must be >= 1");
		}
		this.pageColumnsPerPage = pageColumnsPerPage;
	}

	public Integer getPageColumnSpace() {
		return pageColumnSpace;
	}

	public void setPageColumnSpace(Integer pageColumnSpace) {
		if (pageColumnSpace != null) {
			Validate.isTrue(pageColumnSpace >= 0, "pageColumnSpace must be >= 0");
		}
		this.pageColumnSpace = pageColumnSpace;
	}

	public Boolean getIgnorePageWidth() {
		return ignorePageWidth;
	}

	public void setIgnorePageWidth(Boolean ignorePageWidth) {
		this.ignorePageWidth = ignorePageWidth;
	}

	public Boolean getColumnPrintRepeatedDetailValues() {
		return columnPrintRepeatedDetailValues;
	}

	public void setColumnPrintRepeatedDetailValues(Boolean columnPrintRepeatedDetailValues) {
		this.columnPrintRepeatedDetailValues = columnPrintRepeatedDetailValues;
	}

	public Integer getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(Integer columnWidth) {
		if (columnWidth != null) {
			Validate.isTrue(columnWidth >= 0, "columnWidth must be >= 0");
		}
		this.columnWidth = columnWidth;
	}

	public GroupHeaderLayout getGroupHeaderLayout() {
		return groupHeaderLayout;
	}

	public void setGroupHeaderLayout(GroupHeaderLayout groupHeaderLayout) {
		this.groupHeaderLayout = groupHeaderLayout;
	}

	public Boolean getGroupHideColumn() {
		return groupHideColumn;
	}

	public void setGroupHideColumn(Boolean groupHideColumn) {
		this.groupHideColumn = groupHideColumn;
	}

	public Boolean getGroupShowColumnHeaderAndFooter() {
		return groupShowColumnHeaderAndFooter;
	}

	public void setGroupShowColumnHeaderAndFooter(Boolean groupShowColumnHeaderAndFooter) {
		this.groupShowColumnHeaderAndFooter = groupShowColumnHeaderAndFooter;
	}

	public Integer getGroupPadding() {
		return groupPadding;
	}

	public void setGroupPadding(Integer groupPadding) {
		if (groupPadding != null) {
			Validate.isTrue(groupPadding >= 0, "groupPadding must be >= 0");
		}
		this.groupPadding = groupPadding;
	}

	public Boolean getGroupStartInNewPage() {
		return groupStartInNewPage;
	}

	public void setGroupStartInNewPage(Boolean groupStartInNewPage) {
		this.groupStartInNewPage = groupStartInNewPage;
	}

	public Boolean getGroupStartInNewColumn() {
		return groupStartInNewColumn;
	}

	public void setGroupStartInNewColumn(Boolean groupStartInNewColumn) {
		this.groupStartInNewColumn = groupStartInNewColumn;
	}

	public Boolean getGroupReprintHeaderOnEachPage() {
		return groupReprintHeaderOnEachPage;
	}

	public void setGroupReprintHeaderOnEachPage(Boolean groupReprintHeaderOnEachPage) {
		this.groupReprintHeaderOnEachPage = groupReprintHeaderOnEachPage;
	}

	public Boolean getGroupResetPageNumber() {
		return groupResetPageNumber;
	}

	public void setGroupResetPageNumber(Boolean groupResetPageNumber) {
		this.groupResetPageNumber = groupResetPageNumber;
	}

	public GroupFooterPosition getGroupFooterPosition() {
		return groupFooterPosition;
	}

	public void setGroupFooterPosition(GroupFooterPosition groupFooterPosition) {
		this.groupFooterPosition = groupFooterPosition;
	}

	public Boolean getGroupKeepTogether() {
		return groupKeepTogether;
	}

	public void setGroupKeepTogether(Boolean groupKeepTogether) {
		this.groupKeepTogether = groupKeepTogether;
	}

	public Boolean getTableOfContents() {
		return tableOfContents;
	}

	public void setTableOfContents(Boolean tableOfContents) {
		this.tableOfContents = tableOfContents;
	}

	public DRITableOfContentsCustomizer getTableOfContentsCustomizer() {
		return tableOfContentsCustomizer;
	}

	public void setTableOfContentsCustomizer(DRITableOfContentsCustomizer tableOfContentsCustomizer) {
		this.tableOfContentsCustomizer = tableOfContentsCustomizer;
	}

	public Integer getTextFieldWidth() {
		return textFieldWidth;
	}

	public void setTextFieldWidth(Integer textFieldWidth) {
		if (textFieldWidth != null) {
			Validate.isTrue(textFieldWidth >= 0, "textFieldWidth must be >= 0");
		}
		this.textFieldWidth = textFieldWidth;
	}

	public Integer getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(Integer imageHeight) {
		if (imageHeight != null) {
			Validate.isTrue(imageHeight >= 0, "imageHeight must be >= 0");
		}
		this.imageHeight = imageHeight;
	}

	public Integer getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(Integer imageWidth) {
		if (imageWidth != null) {
			Validate.isTrue(imageWidth >= 0, "imageWidth must be >= 0");
		}
		this.imageWidth = imageWidth;
	}

	public Integer getListgap() {
		return listgap;
	}

	public void setListgap(Integer listgap) {
		if (listgap != null) {
			Validate.isTrue(listgap >= 0, "listgap must be >= 0");
		}
		this.listgap = listgap;
	}

	public Integer getChartHeight() {
		return chartHeight;
	}

	public void setChartHeight(Integer chartHeight) {
		if (chartHeight != null) {
			Validate.isTrue(chartHeight >= 0, "chartHeight must be >= 0");
		}
		this.chartHeight = chartHeight;
	}

	public Integer getChartWidth() {
		return chartWidth;
	}

	public void setChartWidth(Integer chartWidth) {
		if (chartWidth != null) {
			Validate.isTrue(chartWidth >= 0, "chartWidth must be >= 0");
		}
		this.chartWidth = chartWidth;
	}

	public List<Color> getChartSeriesColors() {
		return chartSeriesColors;
	}

	public void addChartSeriesColor(Color color) {
		Validate.notNull(color, "color must not be null");
		this.chartSeriesColors.add(color);
	}

	public void setChartSeriesColors(List<Color> chartSeriesColors) {
		if (chartSeriesColors != null) {
			Validate.noNullElements(chartSeriesColors, "chartSeriesColors must not contains null chartSeriesColor");
		}
		this.chartSeriesColors = chartSeriesColors;
	}

	public Integer getBarcodeHeight() {
		return barcodeHeight;
	}

	public void setBarcodeHeight(Integer barcodeHeight) {
		if (barcodeHeight != null) {
			Validate.isTrue(barcodeHeight >= 0, "barcodeHeight must be >= 0");
		}
		this.barcodeHeight = barcodeHeight;
	}

	public Integer getBarcodeWidth() {
		return barcodeWidth;
	}

	public void setBarcodeWidth(Integer barcodeWidth) {
		if (barcodeWidth != null) {
			Validate.isTrue(barcodeWidth >= 0, "barcodeWidth must be >= 0");
		}
		this.barcodeWidth = barcodeWidth;
	}

	public Integer getSubreportHeight() {
		return subreportHeight;
	}

	public void setSubreportHeight(Integer subreportHeight) {
		if (subreportHeight != null) {
			Validate.isTrue(subreportHeight >= 0, "subreportHeight must be >= 0");
		}
		this.subreportHeight = subreportHeight;
	}

	public Integer getSubreportWidth() {
		return subreportWidth;
	}

	public void setSubreportWidth(Integer subreportWidth) {
		if (subreportWidth != null) {
			Validate.isTrue(subreportWidth >= 0, "subreportWidth must be >= 0");
		}
		this.subreportWidth = subreportWidth;
	}

	public Integer getCrosstabHeight() {
		return crosstabHeight;
	}

	public void setCrosstabHeight(Integer crosstabHeight) {
		if (crosstabHeight != null) {
			Validate.isTrue(crosstabHeight >= 0, "crosstabHeight must be >= 0");
		}
		this.crosstabHeight = crosstabHeight;
	}

	public Integer getCrosstabWidth() {
		return crosstabWidth;
	}

	public void setCrosstabWidth(Integer crosstabWidth) {
		if (crosstabWidth != null) {
			Validate.isTrue(crosstabWidth >= 0, "crosstabWidth must be >= 0");
		}
		this.crosstabWidth = crosstabWidth;
	}

	public Boolean getCrosstabHighlightOddRows() {
		return crosstabHighlightOddRows;
	}

	public void setCrosstabHighlightOddRows(Boolean crosstabHighlightOddRows) {
		this.crosstabHighlightOddRows = crosstabHighlightOddRows;
	}

	public DRSimpleStyle getCrosstabOddRowStyle() {
		return crosstabOddRowStyle;
	}

	public void setCrosstabOddRowStyle(DRSimpleStyle crosstabOddRowStyle) {
		this.crosstabOddRowStyle = crosstabOddRowStyle;
	}

	public Boolean getCrosstabHighlightEvenRows() {
		return crosstabHighlightEvenRows;
	}

	public void setCrosstabHighlightEvenRows(Boolean crosstabHighlightEvenRows) {
		this.crosstabHighlightEvenRows = crosstabHighlightEvenRows;
	}

	public DRSimpleStyle getCrosstabEvenRowStyle() {
		return crosstabEvenRowStyle;
	}

	public void setCrosstabEvenRowStyle(DRSimpleStyle crosstabEvenRowStyle) {
		this.crosstabEvenRowStyle = crosstabEvenRowStyle;
	}

	public DRStyle getCrosstabGroupStyle() {
		return crosstabGroupStyle;
	}

	public void setCrosstabGroupStyle(DRStyle crosstabGroupStyle) {
		this.crosstabGroupStyle = crosstabGroupStyle;
	}

	public DRStyle getCrosstabGroupTotalStyle() {
		return crosstabGroupTotalStyle;
	}

	public void setCrosstabGroupTotalStyle(DRStyle crosstabGroupTotalStyle) {
		this.crosstabGroupTotalStyle = crosstabGroupTotalStyle;
	}

	public DRStyle getCrosstabGrandTotalStyle() {
		return crosstabGrandTotalStyle;
	}

	public void setCrosstabGrandTotalStyle(DRStyle crosstabGrandTotalStyle) {
		this.crosstabGrandTotalStyle = crosstabGrandTotalStyle;
	}

	public DRStyle getCrosstabCellStyle() {
		return crosstabCellStyle;
	}

	public void setCrosstabCellStyle(DRStyle crosstabCellStyle) {
		this.crosstabCellStyle = crosstabCellStyle;
	}

	public DRStyle getCrosstabMeasureTitleStyle() {
		return crosstabMeasureTitleStyle;
	}

	public void setCrosstabMeasureTitleStyle(DRStyle crosstabMeasureTitleStyle) {
		this.crosstabMeasureTitleStyle = crosstabMeasureTitleStyle;
	}

	public BooleanComponentType getBooleanComponentType() {
		return booleanComponentType;
	}

	public void setBooleanComponentType(BooleanComponentType booleanComponentType) {
		this.booleanComponentType = booleanComponentType;
	}

	public Integer getBooleanColumnImageWidth() {
		return booleanColumnImageWidth;
	}

	public void setBooleanColumnImageWidth(Integer booleanColumnImageWidth) {
		this.booleanColumnImageWidth = booleanColumnImageWidth;
	}

	public Integer getBooleanColumnImageHeight() {
		return booleanColumnImageHeight;
	}

	public void setBooleanColumnImageHeight(Integer booleanColumnImageHeight) {
		this.booleanColumnImageHeight = booleanColumnImageHeight;
	}

	public DRIStyle getBooleanColumnStyle() {
		return booleanColumnStyle;
	}

	public void setBooleanColumnStyle(DRIStyle booleanColumnStyle) {
		this.booleanColumnStyle = booleanColumnStyle;
	}

	public SplitType getDefaultSplitType() {
		return defaultSplitType;
	}

	public void setDefaultSplitType(SplitType defaultSplitType) {
		this.defaultSplitType = defaultSplitType;
	}

	public SplitType getTitleSplitType() {
		return titleSplitType;
	}

	public void setTitleSplitType(SplitType titleSplitType) {
		this.titleSplitType = titleSplitType;
	}

	public SplitType getPageHeaderSplitType() {
		return pageHeaderSplitType;
	}

	public void setPageHeaderSplitType(SplitType pageHeaderSplitType) {
		this.pageHeaderSplitType = pageHeaderSplitType;
	}

	public SplitType getPageFooterSplitType() {
		return pageFooterSplitType;
	}

	public void setPageFooterSplitType(SplitType pageFooterSplitType) {
		this.pageFooterSplitType = pageFooterSplitType;
	}

	public SplitType getColumnHeaderSplitType() {
		return columnHeaderSplitType;
	}

	public void setColumnHeaderSplitType(SplitType columnHeaderSplitType) {
		this.columnHeaderSplitType = columnHeaderSplitType;
	}

	public SplitType getColumnFooterSplitType() {
		return columnFooterSplitType;
	}

	public void setColumnFooterSplitType(SplitType columnFooterSplitType) {
		this.columnFooterSplitType = columnFooterSplitType;
	}

	public SplitType getGroupHeaderSplitType() {
		return groupHeaderSplitType;
	}

	public void setGroupHeaderSplitType(SplitType groupHeaderSplitType) {
		this.groupHeaderSplitType = groupHeaderSplitType;
	}

	public SplitType getGroupFooterSplitType() {
		return groupFooterSplitType;
	}

	public void setGroupFooterSplitType(SplitType groupFooterSplitType) {
		this.groupFooterSplitType = groupFooterSplitType;
	}

	public SplitType getDetailHeaderSplitType() {
		return detailHeaderSplitType;
	}

	public void setDetailHeaderSplitType(SplitType detailHeaderSplitType) {
		this.detailHeaderSplitType = detailHeaderSplitType;
	}

	public SplitType getDetailSplitType() {
		return detailSplitType;
	}

	public void setDetailSplitType(SplitType detailSplitType) {
		this.detailSplitType = detailSplitType;
	}

	public SplitType getDetailFooterSplitType() {
		return detailFooterSplitType;
	}

	public void setDetailFooterSplitType(SplitType detailFooterSplitType) {
		this.detailFooterSplitType = detailFooterSplitType;
	}

	public SplitType getLastPageFooterSplitType() {
		return lastPageFooterSplitType;
	}

	public void setLastPageFooterSplitType(SplitType lastPageFooterSplitType) {
		this.lastPageFooterSplitType = lastPageFooterSplitType;
	}

	public SplitType getSummarySplitType() {
		return summarySplitType;
	}

	public void setSummarySplitType(SplitType summarySplitType) {
		this.summarySplitType = summarySplitType;
	}

	public SplitType getNoDataSplitType() {
		return noDataSplitType;
	}

	public void setNoDataSplitType(SplitType noDataSplitType) {
		this.noDataSplitType = noDataSplitType;
	}

	public SplitType getBackgroundSplitType() {
		return backgroundSplitType;
	}

	public void setBackgroundSplitType(SplitType backgroundSplitType) {
		this.backgroundSplitType = backgroundSplitType;
	}

	public DRStyle getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(DRStyle titleStyle) {
		this.titleStyle = titleStyle;
	}

	public DRStyle getPageHeaderStyle() {
		return pageHeaderStyle;
	}

	public void setPageHeaderStyle(DRStyle pageHeaderStyle) {
		this.pageHeaderStyle = pageHeaderStyle;
	}

	public DRStyle getPageFooterStyle() {
		return pageFooterStyle;
	}

	public void setPageFooterStyle(DRStyle pageFooterStyle) {
		this.pageFooterStyle = pageFooterStyle;
	}

	public DRStyle getColumnHeaderStyle() {
		return columnHeaderStyle;
	}

	public void setColumnHeaderStyle(DRStyle columnHeaderStyle) {
		this.columnHeaderStyle = columnHeaderStyle;
	}

	public DRStyle getColumnFooterStyle() {
		return columnFooterStyle;
	}

	public void setColumnFooterStyle(DRStyle columnFooterStyle) {
		this.columnFooterStyle = columnFooterStyle;
	}

	public DRStyle getGroupHeaderStyle() {
		return groupHeaderStyle;
	}

	public void setGroupHeaderStyle(DRStyle groupHeaderStyle) {
		this.groupHeaderStyle = groupHeaderStyle;
	}

	public DRStyle getGroupFooterStyle() {
		return groupFooterStyle;
	}

	public void setGroupFooterStyle(DRStyle groupFooterStyle) {
		this.groupFooterStyle = groupFooterStyle;
	}

	public DRStyle getDetailHeaderStyle() {
		return detailHeaderStyle;
	}

	public void setDetailHeaderStyle(DRStyle detailHeaderStyle) {
		this.detailHeaderStyle = detailHeaderStyle;
	}

	public DRStyle getDetailStyle() {
		return detailStyle;
	}

	public void setDetailStyle(DRStyle detailStyle) {
		this.detailStyle = detailStyle;
	}

	public DRStyle getDetailFooterStyle() {
		return detailFooterStyle;
	}

	public void setDetailFooterStyle(DRStyle detailFooterStyle) {
		this.detailFooterStyle = detailFooterStyle;
	}

	public DRStyle getLastPageFooterStyle() {
		return lastPageFooterStyle;
	}

	public void setLastPageFooterStyle(DRStyle lastPageFooterStyle) {
		this.lastPageFooterStyle = lastPageFooterStyle;
	}

	public DRStyle getSummaryStyle() {
		return summaryStyle;
	}

	public void setSummaryStyle(DRStyle summaryStyle) {
		this.summaryStyle = summaryStyle;
	}

	public DRStyle getNoDataStyle() {
		return noDataStyle;
	}

	public void setNoDataStyle(DRStyle noDataStyle) {
		this.noDataStyle = noDataStyle;
	}

	public DRStyle getBackgroundStyle() {
		return backgroundStyle;
	}

	public void setBackgroundStyle(DRStyle backgroundStyle) {
		this.backgroundStyle = backgroundStyle;
	}

	public DRComponent getTitleBackgroundComponent() {
		return titleBackgroundComponent;
	}

	public void setTitleBackgroundComponent(DRComponent titleBackgroundComponent) {
		this.titleBackgroundComponent = titleBackgroundComponent;
	}

	public DRComponent getPageHeaderBackgroundComponent() {
		return pageHeaderBackgroundComponent;
	}

	public void setPageHeaderBackgroundComponent(DRComponent pageHeaderBackgroundComponent) {
		this.pageHeaderBackgroundComponent = pageHeaderBackgroundComponent;
	}

	public DRComponent getPageFooterBackgroundComponent() {
		return pageFooterBackgroundComponent;
	}

	public void setPageFooterBackgroundComponent(DRComponent pageFooterBackgroundComponent) {
		this.pageFooterBackgroundComponent = pageFooterBackgroundComponent;
	}

	public DRComponent getColumnHeaderBackgroundComponent() {
		return columnHeaderBackgroundComponent;
	}

	public void setColumnHeaderBackgroundComponent(DRComponent columnHeaderBackgroundComponent) {
		this.columnHeaderBackgroundComponent = columnHeaderBackgroundComponent;
	}

	public DRComponent getColumnFooterBackgroundComponent() {
		return columnFooterBackgroundComponent;
	}

	public void setColumnFooterBackgroundComponent(DRComponent columnFooterBackgroundComponent) {
		this.columnFooterBackgroundComponent = columnFooterBackgroundComponent;
	}

	public DRComponent getGroupHeaderBackgroundComponent() {
		return groupHeaderBackgroundComponent;
	}

	public void setGroupHeaderBackgroundComponent(DRComponent groupHeaderBackgroundComponent) {
		this.groupHeaderBackgroundComponent = groupHeaderBackgroundComponent;
	}

	public DRComponent getGroupFooterBackgroundComponent() {
		return groupFooterBackgroundComponent;
	}

	public void setGroupFooterBackgroundComponent(DRComponent groupFooterBackgroundComponent) {
		this.groupFooterBackgroundComponent = groupFooterBackgroundComponent;
	}

	public DRComponent getDetailHeaderBackgroundComponent() {
		return detailHeaderBackgroundComponent;
	}

	public void setDetailHeaderBackgroundComponent(DRComponent detailHeaderBackgroundComponent) {
		this.detailHeaderBackgroundComponent = detailHeaderBackgroundComponent;
	}

	public DRComponent getDetailBackgroundComponent() {
		return detailBackgroundComponent;
	}

	public void setDetailBackgroundComponent(DRComponent detailBackgroundComponent) {
		this.detailBackgroundComponent = detailBackgroundComponent;
	}

	public DRComponent getDetailFooterBackgroundComponent() {
		return detailFooterBackgroundComponent;
	}

	public void setDetailFooterBackgroundComponent(DRComponent detailFooterBackgroundComponent) {
		this.detailFooterBackgroundComponent = detailFooterBackgroundComponent;
	}

	public DRComponent getLastPageFooterBackgroundComponent() {
		return lastPageFooterBackgroundComponent;
	}

	public void setLastPageFooterBackgroundComponent(DRComponent lastPageFooterBackgroundComponent) {
		this.lastPageFooterBackgroundComponent = lastPageFooterBackgroundComponent;
	}

	public DRComponent getSummaryBackgroundComponent() {
		return summaryBackgroundComponent;
	}

	public void setSummaryBackgroundComponent(DRComponent summaryBackgroundComponent) {
		this.summaryBackgroundComponent = summaryBackgroundComponent;
	}

	public DRComponent getNoDataBackgroundComponent() {
		return noDataBackgroundComponent;
	}

	public void setNoDataBackgroundComponent(DRComponent noDataBackgroundComponent) {
		this.noDataBackgroundComponent = noDataBackgroundComponent;
	}

	public DRComponent getBackgroundBackgroundComponent() {
		return backgroundBackgroundComponent;
	}

	public void setBackgroundBackgroundComponent(DRComponent backgroundBackgroundComponent) {
		this.backgroundBackgroundComponent = backgroundBackgroundComponent;
	}

}
