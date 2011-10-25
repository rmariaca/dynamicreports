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

package net.sf.dynamicreports.report.definition;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.GroupFooterPosition;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.RunDirection;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.definition.style.DRISimpleStyle;
import net.sf.dynamicreports.report.definition.style.DRIStyle;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DRIReportTemplate extends Serializable {

	public Locale getLocale();

	public Boolean getShowColumnTitle();

	public Boolean getIgnorePagination();

	public WhenNoDataType getWhenNoDataType();

	public Boolean getTitleOnANewPage();

	public Boolean getSummaryOnANewPage();

	public Boolean getSummaryWithPageHeaderAndFooter();

	public Boolean getFloatColumnFooter();

	public Orientation getPrintOrder();

	public RunDirection getColumnDirection();

	public Boolean getUseFieldNameAsDescription();

	public Boolean getHighlightDetailOddRows();

	public DRISimpleStyle getDetailOddRowStyle();

	public Boolean getHighlightDetailEvenRows();

	public DRISimpleStyle getDetailEvenRowStyle();

	public DRIStyle getTextStyle();

	public DRIStyle getColumnTitleStyle();

	public DRIStyle getColumnStyle();

	public DRIStyle getGroupTitleStyle();

	public DRIStyle getGroupStyle();

	public DRIStyle getSubtotalStyle();

	public DRIStyle getImageStyle();

	public DRIStyle getChartStyle();

	public DRIStyle getBarcodeStyle();

	public Integer getPageWidth();

	public Integer getPageHeight();

	public PageOrientation getPageOrientation();

	public DRIMargin getPageMargin();

	public Integer getPageColumnsPerPage();

	public Integer getPageColumnSpace();

	public Boolean getIgnorePageWidth();

	public Boolean getColumnPrintRepeatedDetailValues();

	public Integer getColumnWidth();

	public GroupHeaderLayout getGroupHeaderLayout();

	public Boolean getGroupHideColumn();

	public Boolean getGroupShowColumnHeaderAndFooter();

	public Integer getGroupPadding();

	public Boolean getGroupStartInNewPage();

	public Boolean getGroupStartInNewColumn();

	public Boolean getGroupReprintHeaderOnEachPage();

	public Boolean getGroupResetPageNumber();

	public GroupFooterPosition getGroupFooterPosition();

	public Boolean getGroupKeepTogether();

	public Boolean getTableOfContents();

	public DRITableOfContentsCustomizer getTableOfContentsCustomizer();

	public Integer getTextFieldWidth();

	public Integer getImageHeight();

	public Integer getImageWidth();

	public Integer getListgap();

	public Integer getChartHeight();

	public Integer getChartWidth();

	public Integer getBarcodeHeight();

	public Integer getBarcodeWidth();

	public Integer getSubreportHeight();

	public Integer getSubreportWidth();

	public Integer getCrosstabHeight();

	public Integer getCrosstabWidth();

	public Boolean getCrosstabHighlightOddRows();

	public DRISimpleStyle getCrosstabOddRowStyle();

	public Boolean getCrosstabHighlightEvenRows();

	public DRISimpleStyle getCrosstabEvenRowStyle();

	public DRIStyle getCrosstabGroupStyle();

	public DRIStyle getCrosstabGroupTotalStyle();

	public DRIStyle getCrosstabGrandTotalStyle();

	public DRIStyle getCrosstabCellStyle();

	public DRIStyle getCrosstabMeasureTitleStyle();

	public List<Color> getChartSeriesColors();

	public BooleanComponentType getBooleanComponentType();

	public Integer getBooleanColumnImageWidth();

	public Integer getBooleanColumnImageHeight();

	public DRIStyle getBooleanColumnStyle();

	public SplitType getDefaultSplitType();

	public SplitType getTitleSplitType();

	public SplitType getPageHeaderSplitType();

	public SplitType getPageFooterSplitType();

	public SplitType getColumnHeaderSplitType();

	public SplitType getColumnFooterSplitType();

	public SplitType getGroupHeaderSplitType();

	public SplitType getGroupFooterSplitType();

	public SplitType getDetailHeaderSplitType();

	public SplitType getDetailSplitType();

	public SplitType getDetailFooterSplitType();

	public SplitType getLastPageFooterSplitType();

	public SplitType getSummarySplitType();

	public SplitType getNoDataSplitType();

	public SplitType getBackgroundSplitType();

	public DRIStyle getTitleStyle();

	public DRIStyle getPageHeaderStyle();

	public DRIStyle getPageFooterStyle();

	public DRIStyle getColumnHeaderStyle();

	public DRIStyle getColumnFooterStyle();

	public DRIStyle getGroupHeaderStyle();

	public DRIStyle getGroupFooterStyle();

	public DRIStyle getDetailHeaderStyle();

	public DRIStyle getDetailStyle();

	public DRIStyle getDetailFooterStyle();

	public DRIStyle getLastPageFooterStyle();

	public DRIStyle getSummaryStyle();

	public DRIStyle getNoDataStyle();

	public DRIStyle getBackgroundStyle();
}
