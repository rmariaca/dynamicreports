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

package net.sf.dynamicreports.jasper.transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignReport;
import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.DRIDesignHyperLink;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignBarcode;
import net.sf.dynamicreports.design.definition.chart.DRIDesignChart;
import net.sf.dynamicreports.design.definition.component.DRIDesignBreak;
import net.sf.dynamicreports.design.definition.component.DRIDesignComponent;
import net.sf.dynamicreports.design.definition.component.DRIDesignFiller;
import net.sf.dynamicreports.design.definition.component.DRIDesignGenericElement;
import net.sf.dynamicreports.design.definition.component.DRIDesignImage;
import net.sf.dynamicreports.design.definition.component.DRIDesignLine;
import net.sf.dynamicreports.design.definition.component.DRIDesignList;
import net.sf.dynamicreports.design.definition.component.DRIDesignSubreport;
import net.sf.dynamicreports.design.definition.component.DRIDesignTextField;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstab;
import net.sf.dynamicreports.design.definition.expression.DRIDesignParameterExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.jasper.base.JasperReportDesign;
import net.sf.dynamicreports.jasper.base.JasperReportParameters;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGenericElementType;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignBreak;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JRDesignGenericElement;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ComponentTransform {
	private static final Log log = LogFactory.getLog(SubreportExpression.class);

	private JasperTransformAccessor accessor;

	public ComponentTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}

	protected JRDesignElement[] component(DRIDesignComponent component, ListType listType) {
		JRDesignElement[] jrElements;
		if (component instanceof DRIDesignChart) {
			JRDesignElement jrElement = accessor.getChartTransform().transform((DRIDesignChart) component);
			component(jrElement, component, detectStretchType(listType));
			jrElements = new JRDesignElement[] {jrElement};
		}
		else if (component instanceof DRIDesignBarcode) {
			JRDesignElement jrElement = accessor.getBarcodeTransform().transform((DRIDesignBarcode) component);
			component(jrElement, component, detectStretchType(listType));
			jrElements = new JRDesignElement[] {jrElement};
		}
		else if (component instanceof DRIDesignList) {
			jrElements = list((DRIDesignList) component);
		}
		else if (component instanceof DRIDesignTextField) {
			JRDesignElement jrElement = textField((DRIDesignTextField) component);
			component(jrElement, component, detectStretchType(listType));
			jrElements = new JRDesignElement[] {jrElement};
		}
		else if (component instanceof DRIDesignFiller) {
			JRDesignElement jrElement = filler((DRIDesignFiller) component);
			component(jrElement, component, detectStretchType(listType));
			jrElements = new JRDesignElement[] {jrElement};
		}
		else if (component instanceof DRIDesignImage) {
			JRDesignElement jrElement = image((DRIDesignImage) component);
			component(jrElement, component, detectStretchType(listType));
			jrElements = new JRDesignElement[] {jrElement};
		}
		else if (component instanceof DRIDesignSubreport) {
			JRDesignElement jrElement = subreport((DRIDesignSubreport) component, component.getWidth());
			component(jrElement, component, StretchTypeEnum.NO_STRETCH);
			jrElements = new JRDesignElement[] {jrElement};
		}
		else if (component instanceof DRIDesignLine) {
			JRDesignElement jrElement = line((DRIDesignLine) component);
			component(jrElement, component, StretchTypeEnum.NO_STRETCH);
			jrElements = new JRDesignElement[] {jrElement};
		}
		else if (component instanceof DRIDesignBreak) {
			JRDesignElement jrElement = breakComponent((DRIDesignBreak) component);
			component(jrElement, component, StretchTypeEnum.NO_STRETCH);
			jrElements = new JRDesignElement[] {jrElement};
		}
		else if (component instanceof DRIDesignGenericElement) {
			JRDesignElement jrElement = genericElement((DRIDesignGenericElement) component);
			component(jrElement, component, detectStretchType(listType));
			jrElements = new JRDesignElement[] {jrElement};
		}
		else if (component instanceof DRIDesignCrosstab) {
			JRDesignElement jrElement = accessor.getCrosstabTransform().transform((DRIDesignCrosstab) component);
			component(jrElement, component, StretchTypeEnum.NO_STRETCH);
			jrElements = new JRDesignElement[] {jrElement};
		}
		else {
			throw new JasperDesignException("Component " + component.getClass().getName() + " not supported");
		}

		return jrElements;
	}

	private void component(JRDesignElement jrElement, DRIDesignComponent component, StretchTypeEnum stretchType) {
		jrElement.setPositionType(PositionTypeEnum.FLOAT);
		jrElement.setStretchType(stretchType);
		jrElement.setKey(component.getUniqueName());
		jrElement.setX(component.getX());
		jrElement.setY(component.getY());
		jrElement.setWidth(component.getWidth());
		jrElement.setHeight(component.getHeight());

		if (component.getStyle() != null)
			jrElement.setStyle(accessor.getStyleTransform().getStyle(component.getStyle()));
		jrElement.setPrintWhenExpression(accessor.getExpressionTransform().getExpression(component.getPrintWhenExpression()));

		for (DRIDesignPropertyExpression propertyExpression : component.getPropertyExpressions()) {
			jrElement.addPropertyExpression(accessor.getExpressionTransform().getPropertyExpression(propertyExpression));
		}
	}

	private StretchTypeEnum detectStretchType(ListType listType) {
		if (listType.equals(ListType.VERTICAL)) {
			return StretchTypeEnum.NO_STRETCH;
		}

		return StretchTypeEnum.RELATIVE_TO_TALLEST_OBJECT;
	}

	//list
	private JRDesignElement[] list(DRIDesignList list) {
		switch (list.getComponentGroupType()) {
		case FRAME:
			JRDesignFrame frame = new JRDesignFrame();
			component(frame, list, ConstantTransform.stretchType(list.getStretchType()));
			for (DRIDesignComponent element : list.getComponents()) {
				JRDesignElement[] jrElements = component(element, list.getType());
				for (JRDesignElement jrElement : jrElements) {
					frame.addElement(jrElement);
				}
			}
			return new JRDesignElement[] {frame};
		case NONE:
			List<JRDesignElement> jrElementList = new ArrayList<JRDesignElement>();
			for (DRIDesignComponent element : list.getComponents()) {
				JRDesignElement[] jrElements = component(element, list.getType());
				for (JRDesignElement jrElement : jrElements) {
					jrElementList.add(jrElement);
				}
			}
			return jrElementList.toArray(new JRDesignElement[jrElementList.size()]);
		default:
			throw new JasperDesignException("ComponentGroupType " + list.getComponentGroupType().getClass().getName() + " not supported");
		}
	}

	//textField
	private JRDesignElement textField(DRIDesignTextField textField) {
		JRDesignTextField jrTextField = new JRDesignTextField();

		DRIDesignHyperLink hyperLink = textField.getHyperLink();
		if (hyperLink != null) {
			jrTextField.setAnchorNameExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorNameExpression()));
			jrTextField.setHyperlinkAnchorExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorExpression()));
			jrTextField.setHyperlinkPageExpression(accessor.getExpressionTransform().getExpression(hyperLink.getPageExpression()));
			jrTextField.setHyperlinkReferenceExpression(accessor.getExpressionTransform().getExpression(hyperLink.getReferenceExpression()));
			jrTextField.setHyperlinkTooltipExpression(accessor.getExpressionTransform().getExpression(hyperLink.getTooltipExpression()));
			HyperlinkTypeEnum hyperLinkType = ConstantTransform.hyperLinkType(hyperLink.getType());
			if (hyperLinkType != null) {
				jrTextField.setHyperlinkType(hyperLinkType);
			}
		}

		EvaluationTime evaluationTime = textField.getEvaluationTime();
		jrTextField.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && textField.getEvaluationGroup() != null) {
			jrTextField.setEvaluationGroup(accessor.getGroupTransform().getGroup(textField.getEvaluationGroup()));
		}

		jrTextField.setStretchWithOverflow(textField.isStretchWithOverflow());

		String pattern = textField.getPattern();
		if (!StringUtils.isBlank(pattern)) {
			jrTextField.setPattern(pattern);
		}
		jrTextField.setHorizontalAlignment(ConstantTransform.horizontalAlignment(textField.getHorizontalAlignment()));
		jrTextField.setExpression(accessor.getExpressionTransform().getExpression(textField.getValueExpression()));
		jrTextField.setPrintRepeatedValues(textField.isPrintRepeatedValues());
		jrTextField.setMarkup(ConstantTransform.markup(textField.getMarkup()));
		jrTextField.setBlankWhenNull(true);

		return jrTextField;
	}

	//filler
	private JRDesignElement filler(DRIDesignFiller filler) {
		JRDesignStaticText jrDesignStaticText = new JRDesignStaticText();
		return jrDesignStaticText;
	}

	//image
	private JRDesignElement image(DRIDesignImage image) {
		JRDesignImage jrImage = new JRDesignImage(new JRDesignStyle().getDefaultStyleProvider());

		DRIDesignHyperLink hyperLink = image.getHyperLink();
		if (hyperLink != null) {
			jrImage.setAnchorNameExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorNameExpression()));
			jrImage.setHyperlinkAnchorExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorExpression()));
			jrImage.setHyperlinkPageExpression(accessor.getExpressionTransform().getExpression(hyperLink.getPageExpression()));
			jrImage.setHyperlinkReferenceExpression(accessor.getExpressionTransform().getExpression(hyperLink.getReferenceExpression()));
			jrImage.setHyperlinkTooltipExpression(accessor.getExpressionTransform().getExpression(hyperLink.getTooltipExpression()));
			HyperlinkTypeEnum hyperLinkType = ConstantTransform.hyperLinkType(hyperLink.getType());
			if (hyperLinkType != null) {
				jrImage.setHyperlinkType(hyperLinkType);
			}
		}

		jrImage.setOnErrorType(OnErrorTypeEnum.BLANK);
		jrImage.setScaleImage(ConstantTransform.imageScale(image.getImageScale()));
		jrImage.setExpression(accessor.getExpressionTransform().getExpression(image.getImageExpression()));

		return jrImage;
	}

	//subreport
	private JRDesignElement subreport(DRIDesignSubreport subreport, Integer width) {
		JRDesignSubreport jrSubreport = new JRDesignSubreport(new JRDesignStyle().getDefaultStyleProvider());
		jrSubreport.setConnectionExpression(accessor.getExpressionTransform().getExpression(subreport.getConnectionExpression()));
		jrSubreport.setDataSourceExpression(accessor.getExpressionTransform().getExpression(subreport.getDataSourceExpression()));
		jrSubreport.setRunToBottom(subreport.getRunToBottom());

		if (ReportBuilder.class.isAssignableFrom(subreport.getReportExpression().getValueClass())) {
			SubreportExpression subreportExpression = new SubreportExpression(subreport.getReportExpression(), width);
			accessor.getExpressionTransform().addSimpleExpression(subreportExpression);
			jrSubreport.setExpression(accessor.getExpressionTransform().getExpression(subreportExpression));

			SubreportParametersExpression parametersExpression = new SubreportParametersExpression(subreportExpression);
			accessor.getExpressionTransform().addSimpleExpression(parametersExpression);
			jrSubreport.setParametersMapExpression(accessor.getExpressionTransform().getExpression(parametersExpression));
		}
		else {
			jrSubreport.setExpression(accessor.getExpressionTransform().getExpression(subreport.getReportExpression()));

			JasperSubreportParametersExpression parametersExpression = new JasperSubreportParametersExpression();
			accessor.getExpressionTransform().addSimpleExpression(parametersExpression);
			jrSubreport.setParametersMapExpression(accessor.getExpressionTransform().getExpression(parametersExpression));
		}

		return jrSubreport;
	}

	//line
	private JRDesignElement line(DRIDesignLine line) {
		JRDesignLine jrDesignLine = new JRDesignLine();
		jrDesignLine.setDirection(ConstantTransform.lineDirection(line.getDirection()));
		return jrDesignLine;
	}

	//break
	private JRDesignElement breakComponent(DRIDesignBreak breakComponent) {
		JRDesignBreak jrDesignBreak = new JRDesignBreak();
		jrDesignBreak.setType(ConstantTransform.breakType(breakComponent.getType()));
		return jrDesignBreak;
	}

	//generic element
	private JRDesignElement genericElement(DRIDesignGenericElement genericElement) {
		JRDesignGenericElement jrDesignGenericElement = new JRDesignGenericElement(new JRDesignStyle().getDefaultStyleProvider());
		JRGenericElementType genericType = new JRGenericElementType(genericElement.getGenericElementNamespace(), genericElement.getGenericElementName());
		jrDesignGenericElement.setGenericType(genericType);
		EvaluationTime evaluationTime = genericElement.getEvaluationTime();
		jrDesignGenericElement.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && genericElement.getEvaluationGroup() != null) {
			jrDesignGenericElement.setEvaluationGroupName(accessor.getGroupTransform().getGroup(genericElement.getEvaluationGroup()).getName());
		}
		for (DRIDesignParameterExpression parameterExpression : genericElement.getParameterExpressions()) {
			jrDesignGenericElement.addParameter(accessor.getExpressionTransform().getGenericElementParameterExpression(parameterExpression));
		}
		return jrDesignGenericElement;
	}

	private class SubreportExpression implements DRIDesignSimpleExpression {
		private String name;
		private DRIDesignSimpleExpression reportExpression;
		private Integer pageWidth;
		private JasperReportDesign reportDesign;

		public SubreportExpression(DRIDesignSimpleExpression reportExpression, Integer pageWidth) {
			this.reportExpression = reportExpression;
			this.pageWidth = pageWidth;
			this.name = ReportUtils.generateUniqueName("subreportExpression");
		}

		public Object evaluate(ReportParameters reportParameters) {
			ReportBuilder<?> reportBuilder = (ReportBuilder<?>) reportExpression.evaluate(reportParameters);
			try {
				reportDesign = new JasperReportDesign(new DRDesignReport(reportBuilder.build(), pageWidth), reportParameters);
				return JasperCompileManager.compileReport(reportDesign.getDesign());
			} catch (JRException e) {
				if (log.isErrorEnabled()) {
					log.error("Error encountered while creating subreport design", e);
				}
			} catch (DRException e) {
				if (log.isErrorEnabled()) {
					log.error("Error encountered while creating subreport design", e);
				}
			}
			return null;
		}

		public JasperReportDesign getReportDesign() {
			return reportDesign;
		}

		public String getName() {
			return name;
		}

		public Class<?> getValueClass() {
			return JasperReport.class;
		}
	}

	private class SubreportParametersExpression implements DRIDesignSimpleExpression {
		private String name;
		private SubreportExpression subreportExpression;

		public SubreportParametersExpression(SubreportExpression subreportExpression) {
			this.subreportExpression = subreportExpression;
			this.name = ReportUtils.generateUniqueName("subreportParametersExpression");
		}

		public Object evaluate(ReportParameters reportParameters) {
			return subreportExpression.getReportDesign().getParameters();
		}

		public String getName() {
			return name;
		}

		public Class<?> getValueClass() {
			return Map.class;
		}
	}

	private class JasperSubreportParametersExpression implements DRIDesignSimpleExpression {
		private String name;

		public JasperSubreportParametersExpression() {
			this.name = ReportUtils.generateUniqueName("jasperSubreportParametersExpression");
		}

		public Object evaluate(ReportParameters reportParameters) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put(JasperReportParameters.MASTER_REPORT_PARAMETERS, reportParameters);
			return map;
		}

		public String getName() {
			return name;
		}

		public Class<?> getValueClass() {
			return Map.class;
		}
	}
}
