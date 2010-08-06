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

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.DRDesignHyperLink;
import net.sf.dynamicreports.design.base.DRDesignVariable;
import net.sf.dynamicreports.design.base.chart.DRDesignChart;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignFiller;
import net.sf.dynamicreports.design.base.component.DRDesignHyperlinkComponent;
import net.sf.dynamicreports.design.base.component.DRDesignImage;
import net.sf.dynamicreports.design.base.component.DRDesignList;
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
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.defaults.Defaults;
import net.sf.dynamicreports.report.definition.DRIHyperLink;
import net.sf.dynamicreports.report.definition.chart.DRIChart;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.component.DRIDimensionComponent;
import net.sf.dynamicreports.report.definition.component.DRIFiller;
import net.sf.dynamicreports.report.definition.component.DRIHyperLinkComponent;
import net.sf.dynamicreports.report.definition.component.DRIImage;
import net.sf.dynamicreports.report.definition.component.DRIList;
import net.sf.dynamicreports.report.definition.component.DRIListCell;
import net.sf.dynamicreports.report.definition.component.DRITextField;
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
	private DRDesignComponent component(DRIComponent component, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
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
		throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
	}

	private void component(DRDesignComponent designComponent, DRIComponent component, boolean textStyle, DefaultStyleType defaultStyleType) throws DRException {
		designComponent.setStyle(accessor.getStyleTransform().transformStyle(component.getStyle(), textStyle, defaultStyleType));
		designComponent.setPrintWhenExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(component.getPrintWhenExpression()));
	}
	
	private void hyperlink(DRDesignHyperlinkComponent designHyperlinkComponent, DRIHyperLinkComponent hyperlinkComponent, boolean textStyle, DefaultStyleType defaultStyleType) throws DRException {
		component(designHyperlinkComponent, hyperlinkComponent, textStyle, defaultStyleType);
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
		component(designList, list, false, DefaultStyleType.NONE);
		designList.setType(list.getType());
		designList.setGap(accessor.getTemplateTransform().getListGap(list));
		for (DRIListCell innerComponent : list.getListCells()) {
			DRIComponent component = innerComponent.getComponent();			
			HorizontalCellComponentAlignment horizontalAlignment = innerComponent.getHorizontalAlignment();
			VerticalCellComponentAlignment verticalAlignment = innerComponent.getVerticalAlignment();
			if (component instanceof DRIDimensionComponent) {
				DRIDimensionComponent dimComponent = (DRIDimensionComponent) component;				
				if (horizontalAlignment == null) {
					horizontalAlignment = toHorizontalCellComponentAlignment(dimComponent.getWidthType());
				}				
				if (verticalAlignment == null) {
					verticalAlignment = toVerticalCellComponentAlignment(dimComponent.getHeightType());
				}
			}
			designList.addComponent(horizontalAlignment, verticalAlignment, component(component, defaultStyleType, resetType, resetGroup));
		}
		
		return designList;
	}
	
	protected HorizontalCellComponentAlignment toHorizontalCellComponentAlignment(ComponentDimensionType widthType) {
		if (widthType == null) {
			return null;
		}
		
		switch (widthType) {
		case FIXED:
			return HorizontalCellComponentAlignment.LEFT;
		case FLOAT:
			return HorizontalCellComponentAlignment.FLOAT;
		case EXPAND:
			return HorizontalCellComponentAlignment.EXPAND;
		default:
			throw new DRDesignReportException("Component dimension type " + widthType.name() + " not supported");
		}		
	}
	
	protected VerticalCellComponentAlignment toVerticalCellComponentAlignment(ComponentDimensionType heightType) {
		if (heightType == null) {
			return null;
		}
		
		switch (heightType) {
		case FIXED:
			return VerticalCellComponentAlignment.TOP;
		case FLOAT:
		case EXPAND:
			return VerticalCellComponentAlignment.EXPAND;
		default:
			throw new DRDesignReportException("Component dimension type " + heightType.name() + " not supported");
		}	
	}
	
	//text field
	protected DRDesignTextField textField(DRITextField<?> textField, DefaultStyleType defaultStyleType) throws DRException {
		DRDesignTextField designTextField = new DRDesignTextField();
		hyperlink(designTextField, textField, true, defaultStyleType);
		TemplateTransform templateTransform = accessor.getTemplateTransform();		
		designTextField.setPrintRepeatedValues(Defaults.getDefaults().isTextFieldPrintRepeatedValues());
		DRDesignStyle style = designTextField.getStyle();
		designTextField.setWidth(templateTransform.getTextFieldWidth(textField, style));
		designTextField.setHeight(templateTransform.getTextFieldHeight(textField, style));
		designTextField.setPattern(templateTransform.getTextFieldPattern(textField, style));
		designTextField.setHorizontalAlignment(templateTransform.getTextFieldHorizontalAlignment(textField, style));	
		designTextField.setValueExpression(accessor.getExpressionTransform().transformExpression(textField.getValueExpression(), templateTransform.getTextFieldValueFormatter(textField)));
		EvaluationTime evaluationTime = detectEvaluationTime(designTextField.getValueExpression());
		designTextField.setEvaluationTime(evaluationTime);
		designTextField.setEvaluationGroup(detectEvaluationGroup(evaluationTime, designTextField.getValueExpression()));	
		return designTextField;
	}
	
	//filler
	protected DRDesignFiller filler(DRIFiller filler) throws DRException {
		DRDesignFiller designFiller = new DRDesignFiller();
		component(designFiller, filler, false, DefaultStyleType.NONE);
		designFiller.setWidth(accessor.getTemplateTransform().getFillerWidth(filler));
		designFiller.setHeight(accessor.getTemplateTransform().getFillerHeight(filler));
		return designFiller;
	}
	
	//image
	private DRDesignImage image(DRIImage image) throws DRException {
		DRDesignImage designImage = new DRDesignImage();
		hyperlink(designImage, image, false, DefaultStyleType.IMAGE);
		designImage.setImageScale(image.getImageScale());
		designImage.setImageExpression(accessor.getExpressionTransform().transformExpression(image.getImageExpression()));
		designImage.setWidth(accessor.getTemplateTransform().getImageWidth(image));
		designImage.setHeight(accessor.getTemplateTransform().getImageHeight(image));
		return designImage;
	}
	
	//chart
	private DRDesignChart chart(DRIChart chart, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignChart designChart = accessor.getChartTransform().transform(chart, resetType, resetGroup);
		hyperlink(designChart, chart, false, DefaultStyleType.CHART);
		designChart.setEvaluationTime(evaluationTimeFromResetType(resetType));
		designChart.setEvaluationGroup(resetGroup);
		return designChart;
	}
	
	private EvaluationTime detectEvaluationTime(DRIDesignExpression expression) {
		if (expression instanceof DRIDesignField || expression instanceof DRIDesignSimpleExpression) {
			return EvaluationTime.NOW;
		}
		else if (expression instanceof DRIDesignVariable) {
			return evaluationTimeFromResetType(((DRIDesignVariable) expression).getResetType());
		}	
		else if (expression instanceof DRIDesignComplexExpression) {			
			return detectComplexExpressionEvaluationTime((DRIDesignComplexExpression) expression);			
		}
		else {
			throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
		}
	}
	
	private EvaluationTime evaluationTimeFromResetType(ResetType resetType) {
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
		else if (expression instanceof DRDesignVariable) {
			return ((DRDesignVariable) expression).getResetGroup();
		}	
		else if (expression instanceof DRIDesignComplexExpression) {			
			return detectComplexExpressionEvaluationGroup((DRIDesignComplexExpression) expression);			
		}
		else {
			throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
		}
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
}
