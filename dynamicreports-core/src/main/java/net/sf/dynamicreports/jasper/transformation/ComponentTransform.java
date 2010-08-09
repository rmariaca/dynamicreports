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

import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.DRIDesignHyperLink;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignBarcode;
import net.sf.dynamicreports.design.definition.chart.DRIDesignChart;
import net.sf.dynamicreports.design.definition.component.DRIDesignComponent;
import net.sf.dynamicreports.design.definition.component.DRIDesignFiller;
import net.sf.dynamicreports.design.definition.component.DRIDesignImage;
import net.sf.dynamicreports.design.definition.component.DRIDesignList;
import net.sf.dynamicreports.design.definition.component.DRIDesignTextField;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;

import org.apache.commons.lang.StringUtils;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ComponentTransform {
	private JasperTransformAccessor accessor;
	
	public ComponentTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}
	
	protected JRDesignElement component(DRIDesignComponent component) {
		JRDesignElement jrElement;
		if (component instanceof DRIDesignChart) {
			jrElement = accessor.getChartTransform().transform((DRIDesignChart) component);
		}
		else if (component instanceof DRIDesignBarcode) {
			jrElement = accessor.getBarcodeTransform().transform((DRIDesignBarcode) component);
		}
		else if (component instanceof DRIDesignList) {
			jrElement = list((DRIDesignList) component);
		}
		else if (component instanceof DRIDesignTextField) {
			jrElement = textField((DRIDesignTextField) component);
		}
		else if (component instanceof DRIDesignFiller) {
			jrElement = filler((DRIDesignFiller) component);
		}
		else if (component instanceof DRIDesignImage) {
			jrElement = image((DRIDesignImage) component);
		}
		else {
			throw new JasperDesignException("Component " + component.getClass().getName() + " not supported");
		}
		jrElement.setKey(component.getUniqueName());
		jrElement.setX(component.getX());
		jrElement.setY(component.getY());		
		jrElement.setWidth(component.getWidth());
		jrElement.setHeight(component.getHeight());
		
		if (component.getStyle() != null)
			jrElement.setStyle(accessor.getStyleTransform().getStyle(component.getStyle()));
		jrElement.setPrintWhenExpression(accessor.getExpressionTransform().getExpression(component.getPrintWhenExpression()));
		return jrElement;
	}

	//list
	private JRDesignElement list(DRIDesignList list) {
		JRDesignFrame frame = new JRDesignFrame();
		
		frame.setPositionType(JRElement.POSITION_TYPE_FLOAT);
		frame.setStretchType(JRElement.STRETCH_TYPE_NO_STRETCH);
		
		for (DRIDesignComponent element : list.getComponents()) {
			frame.addElement(component(element));
		}
		
		return frame;
	}
	
	//textField
	private JRDesignElement textField(DRIDesignTextField textField) {
		JRDesignTextField jrTextField = new JRDesignTextField();
				
		DRIDesignHyperLink hyperLink = textField.getHyperLink();
		if (hyperLink != null) {
			jrTextField.setHyperlinkReferenceExpression(accessor.getExpressionTransform().getExpression(hyperLink.getLinkExpression()));
			jrTextField.setHyperlinkType(JRHyperlink.HYPERLINK_TYPE_REFERENCE);
			jrTextField.setHyperlinkTooltipExpression(accessor.getExpressionTransform().getExpression(hyperLink.getTooltipExpression()));
		}
		
		EvaluationTime evaluationTime = textField.getEvaluationTime();
		jrTextField.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && textField.getEvaluationGroup() != null) {
			jrTextField.setEvaluationGroup(accessor.getGroupTransform().getGroup(textField.getEvaluationGroup()));
		}
		
		jrTextField.setPositionType(JRElement.POSITION_TYPE_FLOAT);
		jrTextField.setStretchType(JRElement.STRETCH_TYPE_RELATIVE_TO_TALLEST_OBJECT);
		jrTextField.setStretchWithOverflow(true);
		
		String pattern = textField.getPattern();
		if (!StringUtils.isBlank(pattern)) {			
			jrTextField.setPattern(pattern);
		}
		jrTextField.setHorizontalAlignment(ConstantTransform.horizontalAlignment(textField.getHorizontalAlignment()));
		jrTextField.setExpression(accessor.getExpressionTransform().getExpression(textField.getValueExpression()));
		jrTextField.setPrintRepeatedValues(textField.isPrintRepeatedValues());
		jrTextField.setBlankWhenNull(true);
		
		return jrTextField;
	}
	
	//filler
	private JRDesignElement filler(DRIDesignFiller filler) {
		JRDesignStaticText jrDesignStaticText = new JRDesignStaticText();
		jrDesignStaticText.setPositionType(JRElement.POSITION_TYPE_FLOAT);
		jrDesignStaticText.setStretchType(JRElement.STRETCH_TYPE_RELATIVE_TO_TALLEST_OBJECT);
		return jrDesignStaticText;
	}
	
	//image
	private JRDesignElement image(DRIDesignImage image) {
		JRDesignImage jrImage = new JRDesignImage(new JRDesignStyle().getDefaultStyleProvider());
		
		DRIDesignHyperLink hyperLink = image.getHyperLink();
		if (hyperLink != null) {
			jrImage.setHyperlinkReferenceExpression(accessor.getExpressionTransform().getExpression(hyperLink.getLinkExpression()));
			jrImage.setHyperlinkType(JRHyperlink.HYPERLINK_TYPE_REFERENCE);
			jrImage.setHyperlinkTooltipExpression(accessor.getExpressionTransform().getExpression(hyperLink.getTooltipExpression()));
		}
		
		jrImage.setPositionType(JRElement.POSITION_TYPE_FLOAT);
		jrImage.setStretchType(JRElement.STRETCH_TYPE_RELATIVE_TO_TALLEST_OBJECT);
		jrImage.setOnErrorType(JRImage.ON_ERROR_TYPE_BLANK);
		jrImage.setScaleImage(ConstantTransform.imageScale(image.getImageScale()));
		jrImage.setExpression(accessor.getExpressionTransform().getExpression(image.getImageExpression()));
		
		return jrImage;
	}
}
