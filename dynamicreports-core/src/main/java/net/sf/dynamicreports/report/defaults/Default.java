/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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

package net.sf.dynamicreports.report.defaults;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.sf.dynamicreports.report.base.DRMargin;
import net.sf.dynamicreports.report.base.datatype.DRDataType;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.base.style.DRPadding;
import net.sf.dynamicreports.report.base.style.DRSimpleStyle;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.WhenNoDataType;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Default {  
	private Locale locale;
	private boolean showColumnTitle;
	private boolean ignorePagination;
	private WhenNoDataType whenNoDataType;
	private boolean titleOnANewPage;
	private boolean summaryOnANewPage;
	private boolean summaryWithPageHeaderAndFooter;
	private boolean floatColumnFooter;	
	//style
	private boolean highlightDetailOddRows;
	private DRSimpleStyle detailOddRowStyle;
	private boolean highlightDetailEvenRows;
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
	private int pageWidth;
	private int pageHeight;
	private PageOrientation pageOrientation;
	private DRMargin pageMargin;
	private int pageColumnsPerPage;
	private int pageColumnSpace;
	//column
	private boolean columnPrintRepeatedDetailValues;
	private int columnWidth;
	//group
	private GroupHeaderLayout groupHeaderLayout;
	private boolean groupHideColumn;
	private boolean groupShowColumnHeaderAndFooter;
	private int groupPadding;
	private boolean groupStartInNewPage;
	private boolean groupStartInNewColumn;
	private boolean groupReprintHeaderOnEachPage;
	private boolean groupByDataType;
	//text field
	private int textFieldWidth;
	private boolean textFieldPrintRepeatedValues;
	//image
	private int imageWidth;
	private int imageHeight;	
	//filler
	private int fillerWidth;
	private int fillerHeight;
	//list
	private int listgap;
	private HorizontalCellComponentAlignment horizontalCellComponentAlignment;
	private VerticalCellComponentAlignment verticalCellComponentAlignment;
	//chart
	private int chartWidth;
	private int chartHeight;	
	private List<Color> chartSeriesColors;
	private boolean chartCategoryDatasetUseSeriesAsCategory;
	private TimePeriod chartTimeSeriesDatasetTimePeriodType;
	//barcode
	private int barcodeWidth;
	private int barcodeHeight;	
	//subreport
	private int subreportWidth;
	private int subreportHeight;	
	//split
	private SplitType defaultSplitType;
	private SplitType titleSplitType;
	private SplitType pageHeaderSplitType;
	private SplitType pageFooterSplitType;
	private SplitType columnHeaderSplitType;
	private SplitType columnFooterSplitType;
	private SplitType groupHeaderSplitType;
	private SplitType groupFooterSplitType;
	private SplitType detailSplitType;
	private SplitType lastPageFooterSplitType;
	private SplitType summarySplitType;
	private SplitType noDataSplitType;
	private SplitType backgroundSplitType;
	//font
  private DRFont font;
  //datatype
  private DRDataType<Number, BigDecimal> bigDecimalType;
  private DRDataType<Number, BigInteger> bigIntegerType;
  private DRDataType<Number, Byte> byteType;
  private DRDataType<Number, Double> doubleType;
  private DRDataType<Number, Float> floatType;
  private DRDataType<Number, Integer> integerType;
  private DRDataType<Number, Long> longType;
  private DRDataType<Number, Short> shortType;
  private DRDataType<Date, Date> dateType;
  private DRDataType<Date, Date> dateYearToMonthType;
  private DRDataType<Date, Date> dateYearToHourType;
  private DRDataType<Date, Date> dateYearToMinuteType;
  private DRDataType<Date, Date> dateYearToSecondType;
  private DRDataType<Date, Date> dateYearToFractionType;
  private DRDataType<Date, Date> dateYearType;
  private DRDataType<Date, Date> dateMonthType;
  private DRDataType<Date, Date> dateDayType;
  private DRDataType<Date, Date> timeHourToMinuteType;
  private DRDataType<Date, Date> timeHourToSecondType;
  private DRDataType<Date, Date> timeHourToFractionType;
  private DRDataType<Number, Double> percentageType;
  private DRDataType<Boolean, Boolean> booleanType;
  private DRDataType<Character, Character> characterType;
  private DRDataType<String, String> stringType;
	private HorizontalAlignment pageXofYHorizontalAlignment;
	
	public Default() {
		init();
	}
	
	private void init() {
		this.locale = Locale.getDefault();
		this.showColumnTitle = true;
		this.ignorePagination =  false;
		this.whenNoDataType = WhenNoDataType.NO_PAGES;		
		this.titleOnANewPage = false;
		this.summaryOnANewPage = false;
		this.summaryWithPageHeaderAndFooter = false;
		this.floatColumnFooter = false;
		
		this.highlightDetailOddRows = false;
		this.detailOddRowStyle = new DRSimpleStyle();
		detailOddRowStyle.setBackgroundColor(new Color(200,200,200));
		this.highlightDetailEvenRows = false;
		this.detailEvenRowStyle = new DRSimpleStyle();
		detailEvenRowStyle.setBackgroundColor(new Color(240,240,240));
				
		this.textStyle = new DRStyle();
		textStyle.setForegroundColor(Color.BLACK);
		textStyle.setPadding(new DRPadding(2));
		
		this.columnTitleStyle = null;
		this.columnStyle = null;
		this.groupTitleStyle = null;
		this.groupStyle = null;
		this.subtotalStyle = null;
		this.imageStyle = new DRStyle();
		imageStyle.setImageScale(ImageScale.FILL_PROPORTIONALLY);
		this.chartStyle = null;
		this.barcodeStyle = null;
		
		this.pageWidth = PageType.A4.getWidth();
		this.pageHeight = PageType.A4.getHeight();
		this.pageOrientation = PageOrientation.PORTRAIT;
		this.pageMargin = new DRMargin(10);
		this.pageColumnsPerPage = 1;
		this.pageColumnSpace = 0;
				
		this.columnPrintRepeatedDetailValues = true;
		this.columnWidth = 100;
		
		this.groupHeaderLayout = GroupHeaderLayout.VALUE;
		this.groupHideColumn = true;
		this.groupShowColumnHeaderAndFooter = false;
		this.groupPadding = 10;
		this.groupStartInNewPage = false;
		this.groupStartInNewColumn = false;
		this.groupReprintHeaderOnEachPage = false;		
		this.groupByDataType = false;
		
		this.textFieldWidth = 100;
		this.textFieldPrintRepeatedValues = true;
		
		this.imageWidth = 100;
		this.imageHeight = 100;		
		
		this.fillerWidth = 0;
		this.fillerHeight = 0;	
		
		this.listgap = 0;
		this.horizontalCellComponentAlignment = HorizontalCellComponentAlignment.FLOAT;
		this.verticalCellComponentAlignment = VerticalCellComponentAlignment.EXPAND;

		this.chartWidth = 200;
		this.chartHeight = 200;
		this.chartSeriesColors = new ArrayList<Color>();
		this.chartCategoryDatasetUseSeriesAsCategory = false;
		this.chartTimeSeriesDatasetTimePeriodType = TimePeriod.DAY;
		
		this.barcodeWidth = 100;
		this.barcodeHeight = 100;
		
		this.subreportWidth = 200;
		this.subreportHeight = 0;
		
		this.pageXofYHorizontalAlignment = HorizontalAlignment.CENTER;
		
		this.defaultSplitType = null;		
		this.titleSplitType = null;
		this.pageHeaderSplitType = null;	
		this.pageFooterSplitType = null;
		this.columnHeaderSplitType = null;
		this.columnFooterSplitType = null;		
		this.groupHeaderSplitType = null;		
		this.groupFooterSplitType = null;		
		this.detailSplitType = null;
		this.lastPageFooterSplitType = null;		
		this.summarySplitType = null;
		this.noDataSplitType = null;
		this.backgroundSplitType = null;
		
		this.font = new DRFont("SansSerif", 10);
		
		this.bigDecimalType = new DRDataType<Number, BigDecimal>("#,##0.00#", HorizontalAlignment.RIGHT);
		this.bigIntegerType = new DRDataType<Number, BigInteger>("#,##0", HorizontalAlignment.RIGHT);
		this.byteType = new DRDataType<Number, Byte>("#,##0", HorizontalAlignment.RIGHT);
		this.doubleType = new DRDataType<Number, Double>("#,##0.#", HorizontalAlignment.RIGHT);
		this.floatType = new DRDataType<Number, Float>("#,##0.#", HorizontalAlignment.RIGHT);
		this.integerType = new DRDataType<Number, Integer>("#,##0", HorizontalAlignment.RIGHT);
		this.longType = new DRDataType<Number, Long>("#,##0", HorizontalAlignment.RIGHT);
		this.shortType = new DRDataType<Number, Short>("#,##0", HorizontalAlignment.RIGHT);
		this.dateType = new DRDataType<Date, Date>("MM/dd/yyyy", HorizontalAlignment.RIGHT);
		this.dateYearToMonthType = new DRDataType<Date, Date>("MM/yyyy", HorizontalAlignment.RIGHT);
		this.dateYearToHourType = new DRDataType<Date, Date>("MM/dd/yyyy h a", HorizontalAlignment.RIGHT);
		this.dateYearToMinuteType = new DRDataType<Date, Date>("MM/dd/yyyy h:mm a", HorizontalAlignment.RIGHT);
		this.dateYearToSecondType = new DRDataType<Date, Date>("MM/dd/yyyy h:mm:ss a", HorizontalAlignment.RIGHT);
		this.dateYearToFractionType = new DRDataType<Date, Date>("MM/dd/yyyy h:mm:ss,SSS a", HorizontalAlignment.RIGHT);
		this.dateYearType = new DRDataType<Date, Date>("yyyy", HorizontalAlignment.RIGHT);
		this.dateMonthType = new DRDataType<Date, Date>("MMMM", HorizontalAlignment.RIGHT);
		this.dateDayType = new DRDataType<Date, Date>("dd", HorizontalAlignment.RIGHT);
		this.timeHourToMinuteType = new DRDataType<Date, Date>("h:mm a", HorizontalAlignment.RIGHT);
		this.timeHourToSecondType = new DRDataType<Date, Date>("h:mm:ss a", HorizontalAlignment.RIGHT);
		this.timeHourToFractionType = new DRDataType<Date, Date>("h:mm:ss,SSS a", HorizontalAlignment.RIGHT);
		this.percentageType = new DRDataType<Number, Double>("#,##0.00%", HorizontalAlignment.RIGHT);
		this.booleanType = new DRDataType<Boolean, Boolean>(null, HorizontalAlignment.CENTER);
		this.characterType = new DRDataType<Character, Character>(null, HorizontalAlignment.LEFT);
		this.stringType = new DRDataType<String, String>(null, HorizontalAlignment.LEFT);
	}

	public Locale getLocale() {
		return locale;
	}

	public boolean isShowColumnTitle() {
		return showColumnTitle;
	}

	public boolean isIgnorePagination() {
		return ignorePagination;
	}

	public WhenNoDataType getWhenNoDataType() {
		return whenNoDataType;
	}

	public boolean isTitleOnANewPage() {
		return titleOnANewPage;
	}

	public boolean isSummaryOnANewPage() {
		return summaryOnANewPage;
	}

	public boolean isSummaryWithPageHeaderAndFooter() {
		return summaryWithPageHeaderAndFooter;
	}
	
	public boolean isFloatColumnFooter() {
		return floatColumnFooter;
	}

	public boolean isHighlightDetailOddRows() {
		return highlightDetailOddRows;
	}

	public DRSimpleStyle getDetailOddRowStyle() {
		return detailOddRowStyle;
	}

	public boolean isHighlightDetailEvenRows() {
		return highlightDetailEvenRows;
	}

	public DRSimpleStyle getDetailEvenRowStyle() {
		return detailEvenRowStyle;
	}

	public DRStyle getTextStyle() {
		return textStyle;
	}

	public DRStyle getColumnTitleStyle() {
		return columnTitleStyle;
	}

	public DRStyle getColumnStyle() {
		return columnStyle;
	}

	public DRStyle getGroupTitleStyle() {
		return groupTitleStyle;
	}

	public DRStyle getGroupStyle() {
		return groupStyle;
	}
	
	public DRStyle getSubtotalStyle() {
		return subtotalStyle;
	}

	public DRStyle getImageStyle() {
		return imageStyle;
	}

	public DRStyle getChartStyle() {
		return chartStyle;
	}

	public DRStyle getBarcodeStyle() {
		return barcodeStyle;
	}
	
	public int getPageWidth() {
		return pageWidth;
	}

	public int getPageHeight() {
		return pageHeight;
	}

	public PageOrientation getPageOrientation() {
		return pageOrientation;
	}

	public DRMargin getPageMargin() {
		return pageMargin;
	}

	public int getPageColumnsPerPage() {
		return pageColumnsPerPage;
	}

	public int getPageColumnSpace() {
		return pageColumnSpace;
	}

	public boolean isColumnPrintRepeatedDetailValues() {
		return columnPrintRepeatedDetailValues;
	}

	public int getColumnWidth() {
		return columnWidth;
	}

	public GroupHeaderLayout getGroupHeaderLayout() {
		return groupHeaderLayout;
	}

	public boolean isGroupHideColumn() {
		return groupHideColumn;
	}

	public boolean isGroupShowColumnHeaderAndFooter() {
		return groupShowColumnHeaderAndFooter;
	}

	public int getGroupPadding() {
		return groupPadding;
	}

	public boolean isGroupStartInNewPage() {
		return groupStartInNewPage;
	}

	public boolean isGroupStartInNewColumn() {
		return groupStartInNewColumn;
	}

	public boolean isGroupReprintHeaderOnEachPage() {
		return groupReprintHeaderOnEachPage;
	}

	public boolean isGroupByDataType() {
		return groupByDataType;
	}
	
	public int getTextFieldWidth() {
		return textFieldWidth;
	}

	public boolean isTextFieldPrintRepeatedValues() {
		return textFieldPrintRepeatedValues;
	}
	
	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getFillerWidth() {
		return fillerWidth;
	}
	
	public int getFillerHeight() {
		return fillerHeight;
	}
	
	public int getListgap() {
		return listgap;
	}
	
	public HorizontalCellComponentAlignment getHorizontalCellComponentAlignment() {
		return horizontalCellComponentAlignment;
	}
	
	public VerticalCellComponentAlignment getVerticalCellComponentAlignment() {
		return verticalCellComponentAlignment;
	}

	public int getChartWidth() {
		return chartWidth;
	}

	public int getChartHeight() {
		return chartHeight;
	}

	public List<Color> getChartSeriesColors() {
		return chartSeriesColors;
	}

	public boolean isChartCategoryDatasetUseSeriesAsCategory() {
		return chartCategoryDatasetUseSeriesAsCategory;
	}
	
	public TimePeriod getChartTimeSeriesDatasetTimePeriodType() {
		return chartTimeSeriesDatasetTimePeriodType;
	}
	
	public int getBarcodeWidth() {
		return barcodeWidth;
	}

	public int getBarcodeHeight() {
		return barcodeHeight;
	}
	
	public int getSubreportWidth() {
		return subreportWidth;
	}

	public int getSubreportHeight() {
		return subreportHeight;
	}
	
	public SplitType getDefaultSplitType() {
		return defaultSplitType;
	}

	public SplitType getTitleSplitType() {
		return titleSplitType;
	}

	public SplitType getPageHeaderSplitType() {
		return pageHeaderSplitType;
	}

	public SplitType getPageFooterSplitType() {
		return pageFooterSplitType;
	}

	public SplitType getColumnHeaderSplitType() {
		return columnHeaderSplitType;
	}

	public SplitType getColumnFooterSplitType() {
		return columnFooterSplitType;
	}

	public SplitType getGroupHeaderSplitType() {
		return groupHeaderSplitType;
	}

	public SplitType getGroupFooterSplitType() {
		return groupFooterSplitType;
	}

	public SplitType getDetailSplitType() {
		return detailSplitType;
	}

	public SplitType getLastPageFooterSplitType() {
		return lastPageFooterSplitType;
	}

	public SplitType getSummarySplitType() {
		return summarySplitType;
	}

	public SplitType getNoDataSplitType() {
		return noDataSplitType;
	}

	public SplitType getBackgroundSplitType() {
		return backgroundSplitType;
	}

	public DRFont getFont() {
		return font;
	}

	public DRDataType<Number, BigDecimal> getBigDecimalType() {
		return bigDecimalType;
	}

	public DRDataType<Number, BigInteger> getBigIntegerType() {
		return bigIntegerType;
	}

	public DRDataType<Number, Byte> getByteType() {
		return byteType;
	}

	public DRDataType<Number, Double> getDoubleType() {
		return doubleType;
	}

	public DRDataType<Number, Float> getFloatType() {
		return floatType;
	}

	public DRDataType<Number, Integer> getIntegerType() {
		return integerType;
	}

	public DRDataType<Number, Long> getLongType() {
		return longType;
	}

	public DRDataType<Number, Short> getShortType() {
		return shortType;
	}

	public DRDataType<Date, Date> getDateType() {
		return dateType;
	}

	public DRDataType<Date, Date> getDateYearToMonthType() {
		return dateYearToMonthType;
	}

	public DRDataType<Date, Date> getDateYearToHourType() {
		return dateYearToHourType;
	}

	public DRDataType<Date, Date> getDateYearToMinuteType() {
		return dateYearToMinuteType;
	}

	public DRDataType<Date, Date> getDateYearToSecondType() {
		return dateYearToSecondType;
	}

	public DRDataType<Date, Date> getDateYearToFractionType() {
		return dateYearToFractionType;
	}

	public DRDataType<Date, Date> getDateYearType() {
		return dateYearType;
	}
	
	public DRDataType<Date, Date> getDateMonthType() {
		return dateMonthType;
	}
	
	public DRDataType<Date, Date> getDateDayType() {
		return dateDayType;
	}
	
	public DRDataType<Date, Date> getTimeHourToMinuteType() {
		return timeHourToMinuteType;
	}

	public DRDataType<Date, Date> getTimeHourToSecondType() {
		return timeHourToSecondType;
	}

	public DRDataType<Date, Date> getTimeHourToFractionType() {
		return timeHourToFractionType;
	}

	public DRDataType<Number, Double> getPercentageType() {
		return percentageType;
	}

	public DRDataType<Boolean, Boolean> getBooleanType() {
		return booleanType;
	}

	public DRDataType<Character, Character> getCharacterType() {
		return characterType;
	}

	public DRDataType<String, String> getStringType() {
		return stringType;
	}

	public HorizontalAlignment getPageXofYHorizontalAlignment() {
		return pageXofYHorizontalAlignment;
	}	
}
