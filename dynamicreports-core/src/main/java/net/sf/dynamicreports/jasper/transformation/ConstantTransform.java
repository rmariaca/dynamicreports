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

package net.sf.dynamicreports.jasper.transformation;

import java.util.List;

import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.jasper.constant.PdfPermission;
import net.sf.dynamicreports.jasper.constant.PdfVersion;
import net.sf.dynamicreports.jasper.constant.SizeUnit;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.constant.BarcodeBaselinePosition;
import net.sf.dynamicreports.report.constant.BarcodeChecksumMode;
import net.sf.dynamicreports.report.constant.BarcodeOrientation;
import net.sf.dynamicreports.report.constant.BarcodeShape;
import net.sf.dynamicreports.report.constant.BarcodeTextPosition;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.LineDirection;
import net.sf.dynamicreports.report.constant.LineStyle;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.Position;
import net.sf.dynamicreports.report.constant.QueryLanguage;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.jasperreports.charts.type.EdgeEnum;
import net.sf.jasperreports.components.barcode4j.BarcodeComponent;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.LineDirectionEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.SplitTypeEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.Second;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import org.krysalis.barcode4j.BaselineAlignment;
import org.krysalis.barcode4j.ChecksumMode;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.datamatrix.SymbolShapeHint;

import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ConstantTransform {
	
	protected static LineStyleEnum lineStyle(LineStyle lineStyle) {
		if (lineStyle == null) {
			return null;
		}
		
		switch (lineStyle) {
		case SOLID:			
			return LineStyleEnum.SOLID;
		case DASHED:			
			return LineStyleEnum.DASHED;
		case DOTTED:			
			return LineStyleEnum.DOTTED;
		case DOUBLE:			
			return LineStyleEnum.DOUBLE;
		default:
			throw new JasperDesignException("Line style " + lineStyle.name() + " not supported");
		}		
	}

	protected static ScaleImageEnum imageScale(ImageScale imageScale) {
		if (imageScale == null) {
			return null;
		}
		
		switch (imageScale) {
		case NO_RESIZE:			
			return ScaleImageEnum.CLIP;
		case FILL:			
			return ScaleImageEnum.FILL_FRAME;
		case FILL_PROPORTIONALLY:			
			return ScaleImageEnum.RETAIN_SHAPE;
		default:
			throw new JasperDesignException("Image scale " + imageScale.name() + " not supported");
		}
	}

	protected static HorizontalAlignEnum horizontalAlignment(HorizontalAlignment horizontalAlignment) {
		if (horizontalAlignment == null) {
			return null;
		}
		
		switch (horizontalAlignment) {
		case LEFT:			
			return HorizontalAlignEnum.LEFT;
		case CENTER:			
			return HorizontalAlignEnum.CENTER;
		case RIGHT:			
			return HorizontalAlignEnum.RIGHT;
		case JUSTIFIED:			
			return HorizontalAlignEnum.JUSTIFIED;
		default:
			throw new JasperDesignException("Horizontal alignment " + horizontalAlignment.name() + " not supported");
		}
	}

	protected static VerticalAlignEnum verticalAlignment(VerticalAlignment verticalAlignment) {
		if (verticalAlignment == null) {
			return null;
		}
		
		switch (verticalAlignment) {
		case TOP:			
			return VerticalAlignEnum.TOP;
		case MIDDLE:			
			return VerticalAlignEnum.MIDDLE;
		case BOTTOM:			
			return VerticalAlignEnum.BOTTOM;
		case JUSTIFIED:			
			return VerticalAlignEnum.JUSTIFIED;
		default:
			throw new JasperDesignException("Vertical alignment " + verticalAlignment.name() + " not supported");
		}
	}

	protected static RotationEnum rotation(Rotation rotation) {
		if (rotation == null) {
			return null;
		}
		
		switch (rotation) {
		case NONE:			
			return RotationEnum.NONE;
		case LEFT:			
			return RotationEnum.LEFT;
		case RIGHT:			
			return RotationEnum.RIGHT;
		case UPSIDE_DOWN:			
			return RotationEnum.UPSIDE_DOWN;
		default:
			throw new JasperDesignException("Rotation " + rotation.name() + " not supported");
		}
	}

	protected static String queryLanguage(QueryLanguage language) {
		if (language == null) {
			return null;
		}
		
		switch (language) {
		case SQL:			
			return JRJdbcQueryExecuterFactory.QUERY_LANGUAGE_SQL;
		case HQL:			
			return JRHibernateQueryExecuterFactory.QUERY_LANGUAGE_HQL;
		case JPA:
			return JRJpaQueryExecuterFactory.QUERY_LANGUAGE_EJBQL;
		default:
			throw new JasperDesignException("Query language " + language.name() + " not supported");
		}
	}

	protected static Byte chartType(ChartType chartType) {
		switch (chartType) {
		case AREA:
			return JRDesignChart.CHART_TYPE_AREA;
		case STACKEDAREA:
			return JRDesignChart.CHART_TYPE_STACKEDAREA;
		case BAR:
			return JRDesignChart.CHART_TYPE_BAR;
		case BAR3D:
			return JRDesignChart.CHART_TYPE_BAR3D;
		case STACKEDBAR:
			return JRDesignChart.CHART_TYPE_STACKEDBAR;
		case STACKEDBAR3D:
			return JRDesignChart.CHART_TYPE_STACKEDBAR3D;
		case LINE:
			return JRDesignChart.CHART_TYPE_LINE;
		case PIE:
			return JRDesignChart.CHART_TYPE_PIE;
		case PIE3D:
			return JRDesignChart.CHART_TYPE_PIE3D;
		case TIMESERIES:
			return JRDesignChart.CHART_TYPE_TIMESERIES;
		case XYAREA:
			return JRDesignChart.CHART_TYPE_XYAREA;
		case XYBAR:
			return JRDesignChart.CHART_TYPE_XYBAR;
		case XYLINE:
			return JRDesignChart.CHART_TYPE_XYLINE;
		case SCATTER:
			return JRDesignChart.CHART_TYPE_SCATTER;
		default:
			throw new JasperDesignException("Chart " + chartType.name() + " not supported");
		}
	}
	
	protected static WhenNoDataTypeEnum whenNoDataType(WhenNoDataType whenNoDataType) {		
		switch (whenNoDataType) {
		case NO_PAGES:			
			return WhenNoDataTypeEnum.NO_PAGES;
		case BLANK_PAGE:			
			return WhenNoDataTypeEnum.BLANK_PAGE;
		case ALL_SECTIONS_NO_DETAIL:
			return WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL;
		case NO_DATA_SECTION:
			return WhenNoDataTypeEnum.NO_DATA_SECTION;
		default:
			throw new JasperDesignException("When no data type " + whenNoDataType.name() + " not supported");
		}
	}
	
	public static WhenNoDataType whenNoDataType(WhenNoDataTypeEnum whenNoDataType) {		
		switch (whenNoDataType) {
		case NO_PAGES:			
			return WhenNoDataType.NO_PAGES;
		case BLANK_PAGE:			
			return WhenNoDataType.BLANK_PAGE;
		case ALL_SECTIONS_NO_DETAIL:
			return WhenNoDataType.ALL_SECTIONS_NO_DETAIL;
		case NO_DATA_SECTION:
			return WhenNoDataType.NO_DATA_SECTION;
		default:
			throw new JasperDesignException("When no data type " + whenNoDataType.name() + " not supported");
		}
	}
	
	protected static OrientationEnum pageOrientation(PageOrientation orientation) {
		switch (orientation) {
		case PORTRAIT:			
			return OrientationEnum.PORTRAIT;
		case LANDSCAPE:
			return OrientationEnum.LANDSCAPE;
		default:
			throw new JasperDesignException("Page orientation " + orientation.name() + " not supported");
		}
	}

	public static PageOrientation pageOrientation(OrientationEnum orientation) {
		switch (orientation) {
		case PORTRAIT:			
			return PageOrientation.PORTRAIT;
		case LANDSCAPE:
			return PageOrientation.LANDSCAPE;
		default:
			throw new JasperDesignException("Page orientation " + orientation.name() + " not supported");
		}
	}
	
	protected static ResetTypeEnum variableResetType(ResetType resetType) {
		switch (resetType) {
		case REPORT:			
			return ResetTypeEnum.REPORT;
		case PAGE:
			return ResetTypeEnum.PAGE;
		case COLUMN:
			return ResetTypeEnum.COLUMN;
		case GROUP:
			return ResetTypeEnum.GROUP;
		default:
			throw new JasperDesignException("Variable reset type " + resetType.name() + " not supported");
		}
	}

	protected static EvaluationTimeEnum evaluationTime(EvaluationTime evaluationTime) {
		if (evaluationTime == null) {
			return EvaluationTimeEnum.NOW;
		}
		
		switch (evaluationTime) {
		case NOW:
			return EvaluationTimeEnum.NOW;
		case REPORT:
			return EvaluationTimeEnum.REPORT;
		case PAGE:
			return EvaluationTimeEnum.PAGE;
		case COLUMN:
			return EvaluationTimeEnum.COLUMN;
		case GROUP:
			return EvaluationTimeEnum.GROUP;
		case BAND:
			return EvaluationTimeEnum.BAND;
		case AUTO:
			return EvaluationTimeEnum.AUTO;
		default:
			throw new JasperDesignException("Evaluation time " + evaluationTime.name() + " not supported");
		}
	}
	
	protected static SplitTypeEnum splitType(SplitType splitType) {
		if (splitType == null) {
			return null;
		}
		switch (splitType) {
		case IMMEDIATE:			
			return SplitTypeEnum.IMMEDIATE;
		case PREVENT:
			return SplitTypeEnum.PREVENT;
		case STRETCH:
			return SplitTypeEnum.STRETCH;
		default:
			throw new JasperDesignException("Split type " + splitType.name() + " not supported");
		}
	}

	protected static Class<?> timePeriodType(TimePeriod timePeriodType) {
		switch (timePeriodType) {
		case YEAR:			
			return Year.class;
		case QUARTER:
			return Quarter.class;
		case MONTH:
			return Month.class;
		case WEEK:
			return Week.class;
		case DAY:
			return Day.class;
		case HOUR:
			return Hour.class;
		case MINUTE:
			return Minute.class;
		case SECOND:
			return Second.class;
		case MILLISECOND:
			return Millisecond.class;
		default:
			throw new JasperDesignException("Time period type " + timePeriodType.name() + " not supported");
		}
	}

	protected static PlotOrientation chartPlotOrientation(Orientation orientation) {
		switch (orientation) {
		case HORIZONTAL:			
			return PlotOrientation.HORIZONTAL;
		case VERTICAL:
			return PlotOrientation.VERTICAL;
		default:
			throw new JasperDesignException("Chart plot orientation " + orientation.name() + " not supported");
		}
	}

	protected static EdgeEnum chartPosition(Position position) {
		if (position == null) {
			return null;
		}
		
		switch (position) {
		case TOP:			
			return EdgeEnum.TOP;
		case BOTTOM:
			return EdgeEnum.BOTTOM;
		case LEFT:			
			return EdgeEnum.LEFT;
		case RIGHT:
			return EdgeEnum.RIGHT;
		default:
			throw new JasperDesignException("Position " + position.name() + " not supported");
		}
	}

	protected static CalculationEnum calculation(Calculation calculation) {
		switch (calculation) {
		case NOTHING:
			return CalculationEnum.NOTHING;
		case COUNT:
			return CalculationEnum.COUNT;
		case SUM:
			return CalculationEnum.SUM;			
		case AVERAGE:			
			return CalculationEnum.AVERAGE;
		case LOWEST:
			return CalculationEnum.LOWEST;
		case HIGHEST:
			return CalculationEnum.HIGHEST;
		case STANDARD_DEVIATION:
			return CalculationEnum.STANDARD_DEVIATION;
		case VARIANCE:
			return CalculationEnum.VARIANCE;		
		case FIRST:
			return CalculationEnum.FIRST;
		case DISTINCT_COUNT:
			return CalculationEnum.DISTINCT_COUNT;
		default:
			throw new JasperDesignException("Calculation " + calculation.name() + " not supported");
		}
	}
	
	protected static String sizeUnit(SizeUnit sizeUnit) {
		switch (sizeUnit) {
		case PIXEL:
			return JRHtmlExporterParameter.SIZE_UNIT_PIXEL;
		case POINT:
			return JRHtmlExporterParameter.SIZE_UNIT_POINT;
		default:
			throw new JasperDesignException("SizeUnit " + sizeUnit.name() + " not supported");
		}
	}
	
	protected static Character pdfVersion(PdfVersion pdfVersion) {
		switch (pdfVersion) {
		case VERION_1_2:
			return JRPdfExporterParameter.PDF_VERSION_1_2;
		case VERION_1_3:
			return JRPdfExporterParameter.PDF_VERSION_1_3;
		case VERION_1_4:
			return JRPdfExporterParameter.PDF_VERSION_1_4;
		case VERION_1_5:
			return JRPdfExporterParameter.PDF_VERSION_1_5;
		case VERION_1_6:
			return JRPdfExporterParameter.PDF_VERSION_1_6;
		case VERION_1_7:
			return JRPdfExporterParameter.PDF_VERSION_1_7;
		default:
			throw new JasperDesignException("PdfVersion " + pdfVersion.name() + " not supported");
		}
	}

	protected static Integer pdfPermission(List<PdfPermission> permissions) {
		int permission = 0;
		for (PdfPermission pdfPermission : permissions) {
			switch (pdfPermission) {
			case PRINTING:
				return permission | PdfWriter.ALLOW_PRINTING;
			case MODIFY_CONTENTS:
				return permission | PdfWriter.ALLOW_MODIFY_CONTENTS;
			case COPY:
				return permission | PdfWriter.ALLOW_COPY;
			case MODIFY_ANNOTATIONS:
				return permission | PdfWriter.ALLOW_MODIFY_ANNOTATIONS;
			case FILL_IN:
				return permission | PdfWriter.ALLOW_FILL_IN;
			case SCREEN_READERS:
				return permission | PdfWriter.ALLOW_SCREENREADERS;
			case ASSEMBLY:
				return permission | PdfWriter.ALLOW_ASSEMBLY;
			case DEGRADED_PRINTING:
				return permission | PdfWriter.ALLOW_DEGRADED_PRINTING;
			default:
				throw new JasperDesignException("PdfPermission " + pdfPermission.name() + " not supported");
			}
		}
		return permission;
	}

	public static int barcodeOrientation(BarcodeOrientation orientation) {
		switch (orientation) {
		case NONE:
			return BarcodeComponent.ORIENTATION_UP;
		case LEFT:
			return BarcodeComponent.ORIENTATION_LEFT;
		case RIGHT:
			return BarcodeComponent.ORIENTATION_RIGHT;
		case UPSIDE_DOWN:
			return BarcodeComponent.ORIENTATION_DOWN;
		default:
			throw new JasperDesignException("BarcodeOrientation " + orientation.name() + " not supported");
		}
	}

	public static HumanReadablePlacement barcodeTextPosition(BarcodeTextPosition textPosition) {
		if (textPosition == null) {
			return null;
		}
		
		switch (textPosition) {
		case NONE:
			return HumanReadablePlacement.HRP_NONE;
		case BOTTOM:
			return HumanReadablePlacement.HRP_BOTTOM;
		case TOP:
			return HumanReadablePlacement.HRP_TOP;			
		default:
			throw new JasperDesignException("BarcodeTextPosition " + textPosition.name() + " not supported");
		}
	}

	public static ChecksumMode barcodeChecksumMode(BarcodeChecksumMode checksumMode) {
		if (checksumMode == null) {
			return null;
		}
		
		switch (checksumMode) {
		case AUTO:
			return ChecksumMode.CP_AUTO;
		case IGNORE:
			return ChecksumMode.CP_IGNORE;
		case ADD:
			return ChecksumMode.CP_ADD;
		case CHECK:
			return ChecksumMode.CP_CHECK;
		default:
			throw new JasperDesignException("BarcodeChecksumMode " + checksumMode.name() + " not supported");
		}
	}

	public static SymbolShapeHint barcodeShape(BarcodeShape shape) {
		if (shape == null) {
			return null;
		}
		
		switch (shape) {
		case NONE:
			return SymbolShapeHint.FORCE_NONE;
		case SQUARE:
			return SymbolShapeHint.FORCE_SQUARE;
		case RECTANGLE:
			return SymbolShapeHint.FORCE_RECTANGLE;
		default:
			throw new JasperDesignException("BarcodeShape " + shape.name() + " not supported");
		}
	}

	public static BaselineAlignment barcodeBaselinePosition(BarcodeBaselinePosition baselinePosition) {
		if (baselinePosition == null) {
			return null;
		}
		
		switch (baselinePosition) {
		case TOP:
			return BaselineAlignment.ALIGN_TOP;
		case BOTTOM:
			return BaselineAlignment.ALIGN_BOTTOM;
		default:
			throw new JasperDesignException("BarcodeBaselinePosition " + baselinePosition.name() + " not supported");
		}
	}
	
	public static LineDirectionEnum lineDirection(LineDirection lineDirection) {
		if (lineDirection == null) {
			return null;
		}
		
		switch (lineDirection) {
		case TOP_DOWN:
			return LineDirectionEnum.TOP_DOWN;
		case BOTTOM_UP:
			return LineDirectionEnum.BOTTOM_UP;
		default:
			throw new JasperDesignException("LineDirection " + lineDirection.name() + " not supported");
		}
	}
	
	public static String markup(Markup markup) {
		if (markup == null) {
			return null;
		}
		
		switch (markup) {
		case NONE:
			return "none";
		case STYLED:
			return "styled";
		case RTF:
			return "rtf";
		case HTML:
			return "html";
		default:
			throw new JasperDesignException("Markup " + markup.name() + " not supported");
		}
	}
}