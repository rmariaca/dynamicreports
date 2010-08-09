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
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.LineStyle;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.Position;
import net.sf.dynamicreports.report.constant.QueryLanguage;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.jasperreports.engine.JRAlignment;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

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

import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
class ConstantTransform {
	
	protected static Byte lineStyle(LineStyle lineStyle) {
		if (lineStyle == null) {
			return null;
		}
		
		switch (lineStyle) {
		case SOLID:			
			return JRPen.LINE_STYLE_SOLID;
		case DASHED:			
			return JRPen.LINE_STYLE_DASHED;
		case DOTTED:			
			return JRPen.LINE_STYLE_DOTTED;
		case DOUBLE:			
			return JRPen.LINE_STYLE_DOUBLE;
		default:
			throw new JasperDesignException("Line style " + lineStyle.name() + " not supported");
		}		
	}

	protected static Byte imageScale(ImageScale imageScale) {
		if (imageScale == null) {
			return null;
		}
		
		switch (imageScale) {
		case NO_RESIZE:			
			return JRImage.SCALE_IMAGE_CLIP;
		case FILL:			
			return JRImage.SCALE_IMAGE_FILL_FRAME;
		case FILL_PROPORTIONALLY:			
			return JRImage.SCALE_IMAGE_RETAIN_SHAPE;
		default:
			throw new JasperDesignException("Image scale " + imageScale.name() + " not supported");
		}
	}

	protected static Byte horizontalAlignment(HorizontalAlignment horizontalAlignment) {
		if (horizontalAlignment == null) {
			return null;
		}
		
		switch (horizontalAlignment) {
		case LEFT:			
			return JRAlignment.HORIZONTAL_ALIGN_LEFT;
		case CENTER:			
			return JRAlignment.HORIZONTAL_ALIGN_CENTER;
		case RIGHT:			
			return JRAlignment.HORIZONTAL_ALIGN_RIGHT;
		case JUSTIFIED:			
			return JRAlignment.HORIZONTAL_ALIGN_JUSTIFIED;
		default:
			throw new JasperDesignException("Horizontal alignment " + horizontalAlignment.name() + " not supported");
		}
	}

	protected static Byte verticalAlignment(VerticalAlignment verticalAlignment) {
		if (verticalAlignment == null) {
			return null;
		}
		
		switch (verticalAlignment) {
		case TOP:			
			return JRAlignment.VERTICAL_ALIGN_TOP;
		case MIDDLE:			
			return JRAlignment.VERTICAL_ALIGN_MIDDLE;
		case BOTTOM:			
			return JRAlignment.VERTICAL_ALIGN_BOTTOM;
		case JUSTIFIED:			
			return JRAlignment.VERTICAL_ALIGN_JUSTIFIED;
		default:
			throw new JasperDesignException("Vertical alignment " + verticalAlignment.name() + " not supported");
		}
	}

	protected static Byte rotation(Rotation rotation) {
		if (rotation == null) {
			return null;
		}
		
		switch (rotation) {
		case NONE:			
			return JRTextElement.ROTATION_NONE;
		case LEFT:			
			return JRTextElement.ROTATION_LEFT;
		case RIGHT:			
			return JRTextElement.ROTATION_RIGHT;
		case UPSIDE_DOWN:			
			return JRTextElement.ROTATION_UPSIDE_DOWN;
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
	
	protected static byte whenNoDataType(WhenNoDataType whenNoDataType) {		
		switch (whenNoDataType) {
		case NO_PAGES:			
			return JRReport.WHEN_NO_DATA_TYPE_NO_PAGES;
		case BLANK_PAGE:			
			return JRReport.WHEN_NO_DATA_TYPE_BLANK_PAGE;
		case ALL_SECTIONS_NO_DETAIL:
			return JRReport.WHEN_NO_DATA_TYPE_ALL_SECTIONS_NO_DETAIL;
		case NO_DATA_SECTION:
			return JRReport.WHEN_NO_DATA_TYPE_NO_DATA_SECTION;
		default:
			throw new JasperDesignException("When no data type " + whenNoDataType.name() + " not supported");
		}
	}
	
	protected static byte pageOrientation(PageOrientation orientation) {
		switch (orientation) {
		case PORTRAIT:			
			return JRReport.ORIENTATION_PORTRAIT;
		case LANDSCAPE:
			return JRReport.ORIENTATION_LANDSCAPE;
		default:
			throw new JasperDesignException("Page orientation " + orientation.name() + " not supported");
		}
	}

	protected static byte variableResetType(ResetType resetType) {
		switch (resetType) {
		case REPORT:			
			return JRVariable.RESET_TYPE_REPORT;
		case PAGE:
			return JRVariable.RESET_TYPE_PAGE;
		case COLUMN:
			return JRVariable.RESET_TYPE_COLUMN;
		case GROUP:
			return JRVariable.RESET_TYPE_GROUP;
		default:
			throw new JasperDesignException("Variable reset type " + resetType.name() + " not supported");
		}
	}

	protected static byte evaluationTime(EvaluationTime evaluationTime) {
		if (evaluationTime == null) {
			return JRExpression.EVALUATION_TIME_NOW;
		}
		
		switch (evaluationTime) {
		case NOW:
			return JRExpression.EVALUATION_TIME_NOW;
		case REPORT:
			return JRExpression.EVALUATION_TIME_REPORT;
		case PAGE:
			return JRExpression.EVALUATION_TIME_PAGE;
		case COLUMN:
			return JRExpression.EVALUATION_TIME_COLUMN;
		case GROUP:
			return JRExpression.EVALUATION_TIME_GROUP;
		case BAND:
			return JRExpression.EVALUATION_TIME_BAND;
		case AUTO:
			return JRExpression.EVALUATION_TIME_AUTO;
		default:
			throw new JasperDesignException("Evaluation time " + evaluationTime.name() + " not supported");
		}
	}
	
	protected static Byte splitType(SplitType splitType) {
		if (splitType == null) {
			return null;
		}
		switch (splitType) {
		case IMMEDIATE:			
			return JRBand.SPLIT_TYPE_IMMEDIATE;
		case PREVENT:
			return JRBand.SPLIT_TYPE_PREVENT;
		case STRETCH:
			return JRBand.SPLIT_TYPE_STRETCH;
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

	protected static Byte chartPosition(Position position) {
		if (position == null)
			return null;
		
		switch (position) {
		case TOP:			
			return JRChart.EDGE_TOP;
		case BOTTOM:
			return JRChart.EDGE_BOTTOM;
		case LEFT:			
			return JRChart.EDGE_LEFT;
		case RIGHT:
			return JRChart.EDGE_RIGHT;
		default:
			throw new JasperDesignException("Position " + position.name() + " not supported");
		}
	}

	protected static byte calculation(Calculation calculation) {
		switch (calculation) {
		case NOTHING:
			return JRVariable.CALCULATION_NOTHING;
		case COUNT:
			return JRVariable.CALCULATION_COUNT;
		case SUM:
			return JRVariable.CALCULATION_SUM;			
		case AVERAGE:			
			return JRVariable.CALCULATION_AVERAGE;
		case LOWEST:
			return JRVariable.CALCULATION_LOWEST;
		case HIGHEST:
			return JRVariable.CALCULATION_HIGHEST;
		case STANDARD_DEVIATION:
			return JRVariable.CALCULATION_STANDARD_DEVIATION;
		case VARIANCE:
			return JRVariable.CALCULATION_VARIANCE;		
		case FIRST:
			return JRVariable.CALCULATION_FIRST;
		case DISTINCT_COUNT:
			return JRVariable.CALCULATION_DISTINCT_COUNT;
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
}