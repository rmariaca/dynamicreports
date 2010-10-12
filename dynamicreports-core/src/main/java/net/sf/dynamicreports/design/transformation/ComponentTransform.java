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

package net.sf.dynamicreports.design.transformation;

import java.text.MessageFormat;
import java.util.List;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.DRDesignHyperLink;
import net.sf.dynamicreports.design.base.DRDesignVariable;
import net.sf.dynamicreports.design.base.barcode.DRDesignBarcode;
import net.sf.dynamicreports.design.base.chart.DRDesignChart;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignFiller;
import net.sf.dynamicreports.design.base.component.DRDesignHyperlinkComponent;
import net.sf.dynamicreports.design.base.component.DRDesignImage;
import net.sf.dynamicreports.design.base.component.DRDesignLine;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.base.component.DRDesignSubreport;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.base.style.DRDesignStyle;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.DRIDesignField;
import net.sf.dynamicreports.design.definition.DRIDesignVariable;
import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.base.DRHyperLink;
import net.sf.dynamicreports.report.base.component.DRList;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.style.DRPen;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.Evaluation;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.defaults.Defaults;
import net.sf.dynamicreports.report.definition.DRIHyperLink;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.barcode.DRIBarcode;
import net.sf.dynamicreports.report.definition.chart.DRIChart;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.component.DRIDimensionComponent;
import net.sf.dynamicreports.report.definition.component.DRIFiller;
import net.sf.dynamicreports.report.definition.component.DRIHyperLinkComponent;
import net.sf.dynamicreports.report.definition.component.DRIImage;
import net.sf.dynamicreports.report.definition.component.DRILine;
import net.sf.dynamicreports.report.definition.component.DRIList;
import net.sf.dynamicreports.report.definition.component.DRIListCell;
import net.sf.dynamicreports.report.definition.component.DRIPageXofY;
import net.sf.dynamicreports.report.definition.component.DRISubreport;
import net.sf.dynamicreports.report.definition.component.DRITextField;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ComponentTransform {
	private DesignTransformAccessor accessor;
	
	public ComponentTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
	}
	
	//component
	protected DRDesignComponent component(DRIComponent component, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		if (component instanceof DRITextField<?>) {
			return textField((DRITextField<?>) component, defaultStyleType);
		}
		if (component instanceof DRIList) {
			return list((DRIList) component, defaultStyleType, resetType, resetGroup);
		}
		if (component instanceof DRIFiller) {
			return filler((DRIFiller) component);
		}
		if (component instanceof DRIImage) {
			return image((DRIImage) component);
		}
		if (component instanceof DRIChart) {
			return chart((DRIChart) component, resetType, resetGroup);
		}
		if (component instanceof DRIBarcode) {
			return barcode((DRIBarcode) component, resetType, resetGroup);
		}
		if (component instanceof DRISubreport) {
			return subreport((DRISubreport) component);
		}
		if (component instanceof DRIPageXofY) {
			return pageXofY((DRIPageXofY) component, defaultStyleType);
		}
		if (component instanceof DRILine) {
			return line((DRILine) component);
		}
		throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
	}

	private void component(DRDesignComponent designComponent, DRIComponent component, DRIStyle style, boolean textStyle, DefaultStyleType defaultStyleType) throws DRException {
		designComponent.setStyle(accessor.getStyleTransform().transformStyle(style, textStyle, defaultStyleType));
		designComponent.setPrintWhenExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(component.getPrintWhenExpression()));
	}
	
	private void hyperlink(DRDesignHyperlinkComponent designHyperlinkComponent, DRIHyperLinkComponent hyperlinkComponent, DRIStyle style, boolean textStyle, DefaultStyleType defaultStyleType) throws DRException {
		component(designHyperlinkComponent, hyperlinkComponent, style, textStyle, defaultStyleType);
		DRIHyperLink hyperLink = hyperlinkComponent.getHyperLink();
		if (hyperLink != null) {
			DRDesignHyperLink designHyperLink = new DRDesignHyperLink();
			designHyperLink.setLinkExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(hyperLink.getLinkExpression()));
			designHyperLink.setTooltipExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(hyperLink.getTooltipExpression()));
			designHyperlinkComponent.setHyperLink(designHyperLink);
		}		
	}
	
	//list
	protected DRDesignList list(DRIList list, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignList designList = new DRDesignList();
		component(designList, list, list.getStyle(), false, DefaultStyleType.NONE);
		designList.setType(list.getType());
		designList.setGap(accessor.getTemplateTransform().getListGap(list));
		for (DRIListCell innerComponent : list.getListCells()) {
			DRIComponent component = innerComponent.getComponent();			
			HorizontalCellComponentAlignment horizontalAlignment = innerComponent.getHorizontalAlignment();
			VerticalCellComponentAlignment verticalAlignment = innerComponent.getVerticalAlignment();
			if (component instanceof DRIDimensionComponent) {
				DRIDimensionComponent dimComponent = (DRIDimensionComponent) component;				
				if (horizontalAlignment == null) {
					horizontalAlignment = ConstantTransform.toHorizontalCellComponentAlignment(dimComponent.getWidthType());
				}				
				if (verticalAlignment == null) {
					verticalAlignment = ConstantTransform.toVerticalCellComponentAlignment(dimComponent.getHeightType());
				}
			}
			designList.addComponent(horizontalAlignment, verticalAlignment, component(component, defaultStyleType, resetType, resetGroup));
		}
		
		return designList;
	}
	
	//text field
	protected DRDesignTextField textField(DRITextField<?> textField, DefaultStyleType defaultStyleType) throws DRException {
		DRDesignTextField designTextField = new DRDesignTextField();
		hyperlink(designTextField, textField, textField.getStyle(), true, defaultStyleType);
		TemplateTransform templateTransform = accessor.getTemplateTransform();		
		designTextField.setPrintRepeatedValues(Defaults.getDefaults().isTextFieldPrintRepeatedValues());
		DRDesignStyle style = designTextField.getStyle();
		designTextField.setWidth(templateTransform.getTextFieldWidth(textField, style));
		designTextField.setHeight(templateTransform.getTextFieldHeight(textField, style));
		designTextField.setPattern(templateTransform.getTextFieldPattern(textField, style));
		designTextField.setHorizontalAlignment(templateTransform.getTextFieldHorizontalAlignment(textField, style));	
		designTextField.setValueExpression(accessor.getExpressionTransform().transformExpression(textField.getValueExpression(), templateTransform.getTextFieldValueFormatter(textField)));
		if (textField.getEvaluationTime() != null) {
			designTextField.setEvaluationTime(ConstantTransform.textFieldEvaluationTime(textField.getEvaluationTime(), textField.getEvaluationGroup(), accessor));
			designTextField.setEvaluationGroup(accessor.getGroupTransform().getGroup(ConstantTransform.textFieldEvaluationGroup(textField.getEvaluationTime(), textField.getEvaluationGroup(), accessor)));
		}
		else {
			if (textField.getEvaluationGroup() != null) {
				throw new DRException("Evaluation group for textField is required only for evaluation time BEFORE_GROUP or GROUP");
			}
			EvaluationTime evaluationTime = detectEvaluationTime(designTextField.getValueExpression());
			designTextField.setEvaluationTime(evaluationTime);
			designTextField.setEvaluationGroup(detectEvaluationGroup(evaluationTime, designTextField.getValueExpression()));
		}
		return designTextField;
	}
	
	//filler
	protected DRDesignFiller filler(DRIFiller filler) throws DRException {
		DRDesignFiller designFiller = new DRDesignFiller();
		component(designFiller, filler, filler.getStyle(), false, DefaultStyleType.NONE);
		designFiller.setWidth(accessor.getTemplateTransform().getFillerWidth(filler));
		designFiller.setHeight(accessor.getTemplateTransform().getFillerHeight(filler));
		return designFiller;
	}
	
	//image
	private DRDesignImage image(DRIImage image) throws DRException {
		DRDesignImage designImage = new DRDesignImage();
		hyperlink(designImage, image, image.getStyle(), false, DefaultStyleType.IMAGE);
		designImage.setImageScale(image.getImageScale());
		designImage.setImageExpression(accessor.getExpressionTransform().transformExpression(image.getImageExpression()));
		designImage.setWidth(accessor.getTemplateTransform().getImageWidth(image));
		designImage.setHeight(accessor.getTemplateTransform().getImageHeight(image));
		return designImage;
	}
	
	//chart
	private DRDesignChart chart(DRIChart chart, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignChart designChart = accessor.getChartTransform().transform(chart, resetType, resetGroup);
		hyperlink(designChart, chart, chart.getStyle(), false, DefaultStyleType.CHART);
		designChart.setEvaluationTime(evaluationTimeFromResetType(resetType));
		designChart.setEvaluationGroup(resetGroup);
		return designChart;
	}
	
	//barcode
	private DRDesignBarcode barcode(DRIBarcode barcode, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignBarcode designBarcode = accessor.getBarcodeTransform().transform(barcode);
		component(designBarcode, barcode, barcode.getStyle(), false, DefaultStyleType.BARCODE);
		designBarcode.setEvaluationTime(evaluationTimeFromResetType(resetType));
		designBarcode.setEvaluationGroup(resetGroup);
		return designBarcode;
	}
	
	//subreport
	private DRDesignSubreport subreport(DRISubreport subreport) throws DRException {
		DRDesignSubreport designSubreport = new DRDesignSubreport();
		component(designSubreport, subreport, subreport.getStyle(), false, DefaultStyleType.NONE);
		designSubreport.setWidth(accessor.getTemplateTransform().getSubreportWidth(subreport));
		designSubreport.setHeight(accessor.getTemplateTransform().getSubreportHeight(subreport));
		designSubreport.setReportExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(subreport.getReportExpression()));
		designSubreport.setConnectionExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(subreport.getConnectionExpression()));
		designSubreport.setDataSourceExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(subreport.getDataSourceExpression()));
		designSubreport.setRunToBottom(subreport.getRunToBottom());
		return designSubreport;
	}
	
	//page x of y
	private DRDesignList pageXofY(DRIPageXofY pageXofY, DefaultStyleType defaultStyleType) throws DRException {		
		TemplateTransform templateTransform = accessor.getTemplateTransform();
		DRIStyle pageXofYStyle = pageXofY.getStyle();
		if (pageXofYStyle == null) {
			pageXofYStyle = accessor.getTemplateTransform().getTextStyle();
		}
		DRDesignStyle style = accessor.getStyleTransform().transformStyle(pageXofYStyle, true, defaultStyleType);
		Integer height = templateTransform.getPageXofYHeight(pageXofY, style);
		HorizontalAlignment horizontalAlignment = templateTransform.getPageXofYHorizontalAlignment(pageXofY, style);
		
		DRStyle newStylePageX = new DRStyle();
		newStylePageX.setParentStyle((DRStyle) pageXofYStyle);
		newStylePageX.getPadding().setRight(0);
		DRPen pen = new DRPen();
		pen.setLineWidth(0f);
		newStylePageX.getBorder().setRightPen(pen);
		DRStyle newStylePageY = new DRStyle();
		newStylePageY.setParentStyle((DRStyle) pageXofYStyle);
		newStylePageY.getPadding().setLeft(0);
		newStylePageY.getBorder().setLeftPen(pen);		
		
		DRTextField<String> pageXField = new DRTextField<String>();
		pageXField.setHyperLink((DRHyperLink) pageXofY.getHyperLink());
		pageXField.setPrintWhenExpression(pageXofY.getPrintWhenExpression());
		pageXField.setStyle(newStylePageX);
		pageXField.setHeight(height);
		pageXField.setHeightType(pageXofY.getHeightType());
		pageXField.setHorizontalAlignment(HorizontalAlignment.RIGHT);	
		pageXField.setValueExpression(new PageNumberExpression(pageXofY.getFormatExpression(), horizontalAlignment, 0));

		DRTextField<String> pageYField = new DRTextField<String>();
		pageYField.setHyperLink((DRHyperLink) pageXofY.getHyperLink());
		pageYField.setPrintWhenExpression(pageXofY.getPrintWhenExpression());
		pageYField.setStyle(newStylePageY);
		pageYField.setHeight(height);
		pageYField.setHeightType(pageXofY.getHeightType());
		pageYField.setHorizontalAlignment(HorizontalAlignment.LEFT);	
		pageYField.setValueExpression(new PageNumberExpression(pageXofY.getFormatExpression(), horizontalAlignment, 1));
		pageYField.setEvaluationTime(Evaluation.REPORT);
		
		int pageXofYWidth = templateTransform.getPageXofYWidth(pageXofY, style);
		switch (horizontalAlignment) {
		case LEFT:
			int pageXWidth = StyleResolver.getFontWidth(style, 4);
			int pageYWidth = pageXofYWidth - pageXWidth;
			if (pageYWidth <= 0) {
				pageYWidth = 10;
			}
			pageXField.setWidth(pageXWidth);
			pageXField.setWidthType(ComponentDimensionType.FIXED);
			pageYField.setWidth(pageYWidth);
			pageYField.setWidthType(pageXofY.getWidthType());
			break;
		case RIGHT:
			pageYWidth = StyleResolver.getFontWidth(style, 4);
			pageXWidth = pageXofYWidth - pageYWidth;
			if (pageXWidth <= 0) {
				pageXWidth = 10;
			}
			pageXField.setWidth(pageXWidth);
			pageXField.setWidthType(pageXofY.getWidthType());
			pageYField.setWidth(pageYWidth);
			pageYField.setWidthType(ComponentDimensionType.FIXED);
			break;
		default:
			pageXField.setWidth(pageXofYWidth / 2);
			pageXField.setWidthType(pageXofY.getWidthType());
			pageYField.setWidth(pageXofYWidth / 2);
			pageYField.setWidthType(pageXofY.getWidthType());
			break;
		}
		
		DRList listPageXofY = new DRList();
		listPageXofY.addComponent(pageXField);
		listPageXofY.addComponent(pageYField);
		return list(listPageXofY, DefaultStyleType.TEXT, null, null);
	}
	
	//line
	protected DRDesignLine line(DRILine line) throws DRException {
		DRDesignLine designLine = new DRDesignLine();
		component(designLine, line, line.getStyle(), false, DefaultStyleType.NONE);
		designLine.setDirection(line.getDirection());
		designLine.setWidth(accessor.getTemplateTransform().getLineWidth(line));
		designLine.setHeight(accessor.getTemplateTransform().getLineHeight(line));
		return designLine;
	}
	
	private EvaluationTime detectEvaluationTime(DRIDesignExpression expression) {
		if (expression instanceof DRIDesignField || expression instanceof DRIDesignSystemExpression || 
				expression instanceof DRIDesignSimpleExpression) {
			return EvaluationTime.NOW;
		}
		if (expression instanceof DRIDesignVariable) {
			return evaluationTimeFromResetType(((DRIDesignVariable) expression).getResetType());
		}	
		if (expression instanceof DRIDesignComplexExpression) {			
			return detectComplexExpressionEvaluationTime((DRIDesignComplexExpression) expression);			
		}
		throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
	}
	
	private EvaluationTime evaluationTimeFromResetType(ResetType resetType) {
		if (resetType == null) {
			return null;
		}
		
		switch (resetType) {
		case REPORT:
			return EvaluationTime.REPORT;
		case PAGE:
			return EvaluationTime.PAGE;
		case COLUMN:
			return EvaluationTime.COLUMN;		
		case GROUP:
			return EvaluationTime.GROUP;
		default:
			throw new DRDesignReportException("Reset type " + resetType.name() + " not supported");
		}
	}
	
	private EvaluationTime detectComplexExpressionEvaluationTime(DRIDesignComplexExpression complexExpression) {
		EvaluationTime evaluationTime = null;
		for (DRIDesignExpression expression : complexExpression.getExpressions()) {
			EvaluationTime evalTime = detectEvaluationTime(expression);
			if (evaluationTime == null) {
				evaluationTime = evalTime;
			}
			else if (evaluationTime != evalTime || evaluationTime.equals(EvaluationTime.GROUP) && evalTime.equals(EvaluationTime.GROUP)) {
				return EvaluationTime.AUTO;
			}			
		}
		return evaluationTime;
	}
	
	private DRDesignGroup detectEvaluationGroup(EvaluationTime evaluationTime, DRIDesignExpression expression) {
		if (evaluationTime.equals(EvaluationTime.GROUP)) {
			DRDesignGroup evaluationGroup = detectEvaluationGroup(expression);
			if (evaluationGroup == null) {
				throw new DRDesignReportException("Can not detect evaluation group");
			}
			return evaluationGroup;
		}
		return null;
	}
	
	private DRDesignGroup detectEvaluationGroup(DRIDesignExpression expression) {
		if (expression instanceof DRIDesignField || expression instanceof DRIDesignSimpleExpression) {
			return null;
		}
		if (expression instanceof DRDesignVariable) {
			return ((DRDesignVariable) expression).getResetGroup();
		}	
		if (expression instanceof DRIDesignComplexExpression) {			
			return detectComplexExpressionEvaluationGroup((DRIDesignComplexExpression) expression);			
		}
		throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
	}
	
	private DRDesignGroup detectComplexExpressionEvaluationGroup(DRIDesignComplexExpression complexExpression) {
		DRDesignGroup evaluationGroup = null;
		for (DRIDesignExpression expression : complexExpression.getExpressions()) {
			DRDesignGroup group = detectEvaluationGroup(expression);
			if (evaluationGroup == null) {
				evaluationGroup = group;
			}
			else if (evaluationGroup != group) {
				throw new DRDesignReportException("Can not detect evaluation group");
			}			
		}
		return evaluationGroup;
	}
	
	private class PageNumberExpression extends AbstractComplexExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		private HorizontalAlignment horizontalAlignment;
		private int index;		
		
		public PageNumberExpression(DRISimpleExpression<String> pageNumberFormatExpression, HorizontalAlignment horizontalAlignment, int index) {
			addExpression(pageNumberFormatExpression);
			this.horizontalAlignment = horizontalAlignment;
			this.index = index;
		}

		@Override
		public String evaluate(List<?> values, ReportParameters reportParameters) {
			String pattern = (String) values.get(0);
			int index1 = pattern.indexOf("{0}");
			int index2 = pattern.indexOf("{1}");
			if (index == 0) {
				if (horizontalAlignment.equals(HorizontalAlignment.RIGHT)) {
					pattern = pattern.substring(0, index2);
				}
				else {
					pattern = pattern.substring(0, index1 + 3);
				}
			}
			else {
				if (horizontalAlignment.equals(HorizontalAlignment.RIGHT)) {
					pattern = pattern.substring(index2);
				}
				else {
					pattern = pattern.substring(index1 + 3);
				}				
			}
			MessageFormat format = new MessageFormat(pattern, reportParameters.getLocale());
			String result = format.format(new Object[]{reportParameters.getPageNumber(), reportParameters.getPageNumber()});
			return result;
		}
	}
}
