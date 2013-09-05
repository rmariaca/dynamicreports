/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
 * http://www.dynamicreports.org
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

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.DRDesignHyperLink;
import net.sf.dynamicreports.design.base.DRDesignTableOfContentsHeading;
import net.sf.dynamicreports.design.base.DRDesignVariable;
import net.sf.dynamicreports.design.base.barcode.DRDesignBarbecue;
import net.sf.dynamicreports.design.base.barcode.DRDesignBarcode;
import net.sf.dynamicreports.design.base.chart.DRDesignChart;
import net.sf.dynamicreports.design.base.component.DRDesignBreak;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignEllipse;
import net.sf.dynamicreports.design.base.component.DRDesignFiller;
import net.sf.dynamicreports.design.base.component.DRDesignGenericElement;
import net.sf.dynamicreports.design.base.component.DRDesignHyperlinkComponent;
import net.sf.dynamicreports.design.base.component.DRDesignImage;
import net.sf.dynamicreports.design.base.component.DRDesignLine;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.base.component.DRDesignMap;
import net.sf.dynamicreports.design.base.component.DRDesignRectangle;
import net.sf.dynamicreports.design.base.component.DRDesignSubreport;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstab;
import net.sf.dynamicreports.design.base.style.DRDesignStyle;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.DRIDesignField;
import net.sf.dynamicreports.design.definition.DRIDesignVariable;
import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.base.DRBand;
import net.sf.dynamicreports.report.base.DRGroup;
import net.sf.dynamicreports.report.base.DRHyperLink;
import net.sf.dynamicreports.report.base.component.DRComponent;
import net.sf.dynamicreports.report.base.component.DRHyperLinkComponent;
import net.sf.dynamicreports.report.base.component.DRImage;
import net.sf.dynamicreports.report.base.component.DRList;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.base.style.DRPen;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.component.CustomComponentTransform;
import net.sf.dynamicreports.report.component.CustomComponents;
import net.sf.dynamicreports.report.component.DRICustomComponent;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.Evaluation;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.defaults.Defaults;
import net.sf.dynamicreports.report.definition.DRIGroup;
import net.sf.dynamicreports.report.definition.DRIHyperLink;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.barcode.DRIBarbecue;
import net.sf.dynamicreports.report.definition.barcode.DRIBarcode;
import net.sf.dynamicreports.report.definition.chart.DRIChart;
import net.sf.dynamicreports.report.definition.component.DRIBooleanField;
import net.sf.dynamicreports.report.definition.component.DRIBreak;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.component.DRICurrentDate;
import net.sf.dynamicreports.report.definition.component.DRIDimensionComponent;
import net.sf.dynamicreports.report.definition.component.DRIEllipse;
import net.sf.dynamicreports.report.definition.component.DRIFiller;
import net.sf.dynamicreports.report.definition.component.DRIFormatField;
import net.sf.dynamicreports.report.definition.component.DRIGenericElement;
import net.sf.dynamicreports.report.definition.component.DRIHyperLinkComponent;
import net.sf.dynamicreports.report.definition.component.DRIImage;
import net.sf.dynamicreports.report.definition.component.DRILine;
import net.sf.dynamicreports.report.definition.component.DRIList;
import net.sf.dynamicreports.report.definition.component.DRIListCell;
import net.sf.dynamicreports.report.definition.component.DRIMap;
import net.sf.dynamicreports.report.definition.component.DRIMultiPageList;
import net.sf.dynamicreports.report.definition.component.DRIPageNumber;
import net.sf.dynamicreports.report.definition.component.DRIPageXofY;
import net.sf.dynamicreports.report.definition.component.DRIRectangle;
import net.sf.dynamicreports.report.definition.component.DRISubreport;
import net.sf.dynamicreports.report.definition.component.DRITextField;
import net.sf.dynamicreports.report.definition.component.DRITotalPages;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstab;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRIParameterExpression;
import net.sf.dynamicreports.report.definition.expression.DRIPropertyExpression;
import net.sf.dynamicreports.report.definition.style.DRIReportStyle;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.Renderable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
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
		if (component instanceof DRIMultiPageList) {
			return multiPageList((DRIMultiPageList) component);
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
		if (component instanceof DRIBarbecue) {
			return barbecue((DRIBarbecue) component, resetType, resetGroup);
		}
		if (component instanceof DRISubreport) {
			return subreport((DRISubreport) component);
		}
		if (component instanceof DRIPageXofY) {
			return pageXofY((DRIPageXofY) component, defaultStyleType);
		}
		if (component instanceof DRITotalPages) {
			return totalPages((DRITotalPages) component, defaultStyleType);
		}
		if (component instanceof DRIPageNumber) {
			return pageNumber((DRIPageNumber) component, defaultStyleType);
		}
		if (component instanceof DRICurrentDate) {
			return currentDate((DRICurrentDate) component, defaultStyleType);
		}
		if (component instanceof DRILine) {
			return line((DRILine) component);
		}
		if (component instanceof DRIEllipse) {
			return ellipse((DRIEllipse) component);
		}
		if (component instanceof DRIRectangle) {
			return rectangle((DRIRectangle) component);
		}
		if (component instanceof DRIBooleanField) {
			return booleanField((DRIBooleanField) component, defaultStyleType, resetType, resetGroup);
		}
		if (component instanceof DRIBreak) {
			return breakComponent((DRIBreak) component);
		}
		if (component instanceof DRIGenericElement) {
			return genericElement((DRIGenericElement) component, resetType, resetGroup);
		}
		if (component instanceof DRICrosstab) {
			return crosstab((DRICrosstab) component, resetType, resetGroup);
		}
		if (component instanceof DRIMap) {
			return map((DRIMap) component, resetType, resetGroup);
		}
		if (component instanceof DRICustomComponent) {
			return customComponent((DRICustomComponent) component, resetType, resetGroup);
		}
		throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
	}

	private void component(DRDesignComponent designComponent, DRIComponent component, DRIReportStyle style, boolean textStyle, DefaultStyleType defaultStyleType) throws DRException {
		designComponent.setStyle(accessor.getStyleTransform().transformStyle(style, textStyle, defaultStyleType));
		designComponent.setPrintWhenExpression(accessor.getExpressionTransform().transformExpression(component.getPrintWhenExpression()));
		designComponent.setRemoveLineWhenBlank(accessor.getTemplateTransform().getRemoveLineWhenBlank(component));
		designComponent.setPositionType(accessor.getTemplateTransform().getPositionType(component));
		designComponent.setStretchType(accessor.getTemplateTransform().getStretchType(component));
		designComponent.setPrintInFirstWholeBand(accessor.getTemplateTransform().getPrintInFirstWholeBand(component));
		designComponent.setPrintWhenDetailOverflows(accessor.getTemplateTransform().getPrintWhenDetailOverflows(component));
		designComponent.setPrintWhenGroupChanges(accessor.getTemplateTransform().getPrintWhenGroupChanges(component));
		for (DRIPropertyExpression propertyExpression : component.getPropertyExpressions()) {
			designComponent.getPropertyExpressions().add(accessor.getExpressionTransform().transformPropertyExpression(propertyExpression));
		}
		DRDesignTableOfContentsHeading designTocHeading = accessor.getTableOfContentsTransform().componentHeading(component);
		if (designTocHeading != null) {
			designComponent.setTableOfContentsHeading(designTocHeading);
			DRIDesignExpression anchorNameExpression = designTocHeading.getReferenceField().getAnchorNameExpression();
			Integer bookmarkLevel = designTocHeading.getReferenceField().getBookmarkLevel();
			DRDesignHyperLink designHyperLink = designTocHeading.getReferenceField().getHyperLink();
			if (designComponent instanceof DRDesignHyperlinkComponent) {
				((DRDesignHyperlinkComponent) designComponent).setAnchorNameExpression(anchorNameExpression);
				((DRDesignHyperlinkComponent) designComponent).setBookmarkLevel(bookmarkLevel);
				((DRDesignHyperlinkComponent) designComponent).setHyperLink(designHyperLink);
			}
		}
	}

	private void hyperlink(DRDesignHyperlinkComponent designHyperlinkComponent, DRIHyperLinkComponent hyperlinkComponent, DRIReportStyle style, boolean textStyle, DefaultStyleType defaultStyleType) throws DRException {
		component(designHyperlinkComponent, hyperlinkComponent, style, textStyle, defaultStyleType);

		if (hyperlinkComponent.getAnchorNameExpression() != null) {
			designHyperlinkComponent.setAnchorNameExpression(accessor.getExpressionTransform().transformExpression(hyperlinkComponent.getAnchorNameExpression()));
		}
		if (hyperlinkComponent.getBookmarkLevel() != null) {
			designHyperlinkComponent.setBookmarkLevel(hyperlinkComponent.getBookmarkLevel());
		}

		DRIHyperLink hyperLink = hyperlinkComponent.getHyperLink();
		DRDesignHyperLink designHyperLink = accessor.getReportTransform().hyperlink(hyperLink);
		if (designHyperLink != null) {
			designHyperlinkComponent.setHyperLink(designHyperLink);
		}
	}

	//list
	protected DRDesignList list(DRIList list, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignList designList = new DRDesignList();
		component(designList, list, list.getStyle(), false, DefaultStyleType.NONE);
		designList.setType(list.getType());
		designList.setGap(accessor.getTemplateTransform().getListGap(list));
		designList.setWidth(accessor.getTemplateTransform().getListWidth(list));
		designList.setHeight(accessor.getTemplateTransform().getListHeight(list));
		designList.setCalculateComponents(designList.getWidth() == null && designList.getHeight() == null);
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
		designList.setBackgroundComponent(listBackgroundComponent(list.getBackgroundComponent(), defaultStyleType, resetType, resetGroup));

		return designList;
	}

	protected DRDesignComponent listBackgroundComponent(DRIComponent backgroundComponent, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		if (backgroundComponent != null) {
			if (backgroundComponent instanceof DRIRectangle ||
					backgroundComponent instanceof DRIImage ||
					backgroundComponent instanceof DRITextField) {
				return component(backgroundComponent, defaultStyleType, resetType, resetGroup);
			}
			else {
				throw new DRDesignReportException("List background component not supported. Only rectangle, image and textfield are supported");
			}
		}
		return null;
	}

	//multi page list
	private DRDesignSubreport multiPageList(DRIMultiPageList multiPageList) throws DRException {
		DRDesignSubreport designSubreport = new DRDesignSubreport();
		component(designSubreport, multiPageList, multiPageList.getStyle(), false, DefaultStyleType.NONE);
		designSubreport.setWidth(accessor.getTemplateTransform().getMultiPageListWidth(multiPageList));
		designSubreport.setHeight(accessor.getTemplateTransform().getMultiPageListHeight(multiPageList));
    JasperReportBuilder multiPageReport = DynamicReports.report();
    MultiPageListSubreportExpression subreportExpression = new MultiPageListSubreportExpression(multiPageList.getComponents());
    multiPageReport.detail(Components.subreport(subreportExpression));
    multiPageReport.setDetailSplitType(multiPageList.getSplitType());
    DRIDesignExpression reportExpression = accessor.getExpressionTransform().transformExpression(Expressions.value(multiPageReport));
    DRIDesignExpression dataSourceExpression = accessor.getExpressionTransform().transformExpression(Expressions.dataSource(new JREmptyDataSource(multiPageList.getComponents().size())));
    designSubreport.setReportExpression(reportExpression);
    designSubreport.setDataSourceExpression(dataSourceExpression);
		return designSubreport;
	}

	//text field
	protected DRDesignTextField textField(DRITextField<?> textField, DefaultStyleType defaultStyleType) throws DRException {
		DRDesignTextField designTextField = new DRDesignTextField();
		hyperlink(designTextField, textField, textField.getStyle(), true, defaultStyleType);
		TemplateTransform templateTransform = accessor.getTemplateTransform();
		designTextField.setPrintRepeatedValues(Defaults.getDefaults().isTextFieldPrintRepeatedValues());
		designTextField.setStretchWithOverflow(templateTransform.getTextFieldStretchWithOverflow(textField));
		DRDesignStyle style = designTextField.getStyle();
		designTextField.setWidth(templateTransform.getTextFieldWidth(textField, style));
		designTextField.setHeight(templateTransform.getTextFieldHeight(textField, style));
		designTextField.setPattern(templateTransform.getTextFieldPattern(textField, style));
		designTextField.setPatternExpression(accessor.getExpressionTransform().transformExpression(textField.getPatternExpression()));
		designTextField.setHorizontalAlignment(templateTransform.getTextFieldHorizontalAlignment(textField, style));
		designTextField.setValueExpression(accessor.getExpressionTransform().transformExpression(textField.getValueExpression(), templateTransform.getTextFieldValueFormatter(textField), null));
		designTextField.setMarkup(textField.getMarkup());
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
		return image(image, null, DefaultStyleType.IMAGE);
	}

	private DRDesignImage image(DRIImage image, Integer imageHeight, DefaultStyleType defaultStyleType) throws DRException {
		DRDesignImage designImage = new DRDesignImage();
		hyperlink(designImage, image, image.getStyle(), false, defaultStyleType);
		designImage.setImageScale(image.getImageScale());
		designImage.setImageExpression(accessor.getExpressionTransform().transformExpression(image.getImageExpression()));
		designImage.setUsingCache(image.getUsingCache());
		designImage.setLazy(image.getLazy());
		designImage.setHorizontalAlignment(image.getHorizontalAlignment());
		designImage.setWidth(accessor.getTemplateTransform().getImageWidth(image));
		DRDesignStyle style = designImage.getStyle();
		designImage.setHeight(accessor.getTemplateTransform().getImageHeight(image, imageHeight, style));
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

	//barbecue
	private DRDesignBarbecue barbecue(DRIBarbecue barbecue, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignBarbecue designBarbecue = accessor.getBarcodeTransform().transform(barbecue);
		component(designBarbecue, barbecue, barbecue.getStyle(), false, DefaultStyleType.BARCODE);
		designBarbecue.setEvaluationTime(evaluationTimeFromResetType(resetType));
		designBarbecue.setEvaluationGroup(resetGroup);
		return designBarbecue;
	}

	//subreport
	private DRDesignSubreport subreport(DRISubreport subreport) throws DRException {
		DRDesignSubreport designSubreport = new DRDesignSubreport();
		component(designSubreport, subreport, subreport.getStyle(), false, DefaultStyleType.NONE);
		designSubreport.setWidth(accessor.getTemplateTransform().getSubreportWidth(subreport));
		designSubreport.setHeight(accessor.getTemplateTransform().getSubreportHeight(subreport));
		designSubreport.setReportExpression(accessor.getExpressionTransform().transformExpression(subreport.getReportExpression()));
		designSubreport.setParametersExpression(accessor.getExpressionTransform().transformExpression(subreport.getParametersExpression()));
		designSubreport.setConnectionExpression(accessor.getExpressionTransform().transformExpression(subreport.getConnectionExpression()));
		designSubreport.setDataSourceExpression(accessor.getExpressionTransform().transformExpression(subreport.getDataSourceExpression()));
		designSubreport.setRunToBottom(subreport.getRunToBottom());
		return designSubreport;
	}

	//page x of y
	private DRDesignList pageXofY(DRIPageXofY pageXofY, DefaultStyleType defaultStyleType) throws DRException {
		TemplateTransform templateTransform = accessor.getTemplateTransform();
		DRIReportStyle pageXofYStyle = pageXofY.getStyle();
		if (pageXofYStyle == null) {
			pageXofYStyle = accessor.getTemplateTransform().getTextStyle();
		}
		DRDesignStyle style = accessor.getStyleTransform().transformStyle(pageXofYStyle, true, defaultStyleType);
		Integer height = templateTransform.getPageXofYHeight(pageXofY, style);
		HorizontalAlignment horizontalAlignment = templateTransform.getPageXofYHorizontalAlignment(pageXofY, style);

		DRStyle newStylePageX = new DRStyle();
		newStylePageX.setParentStyle(pageXofYStyle);
		newStylePageX.getPadding().setRight(0);
		DRPen pen = new DRPen();
		pen.setLineWidth(0f);
		newStylePageX.getBorder().setRightPen(pen);
		DRStyle newStylePageY = new DRStyle();
		newStylePageY.setParentStyle(pageXofYStyle);
		newStylePageY.getPadding().setLeft(0);
		newStylePageY.getBorder().setLeftPen(pen);

		DRTextField<String> pageXField = new DRTextField<String>();
		pageXField.setAnchorNameExpression(pageXofY.getAnchorNameExpression());
		pageXField.setBookmarkLevel(pageXofY.getBookmarkLevel());
		pageXField.setHyperLink((DRHyperLink) pageXofY.getHyperLink());
		pageXField.setPrintWhenExpression(pageXofY.getPrintWhenExpression());
		pageXField.setStyle(newStylePageX);
		pageXField.setHeight(height);
		pageXField.setHeightType(pageXofY.getHeightType());
		pageXField.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		pageXField.setValueExpression(new PageXofYNumberExpression(pageXofY.getFormatExpression(), 0));

		DRTextField<String> pageYField = new DRTextField<String>();
		pageYField.setAnchorNameExpression(pageXofY.getAnchorNameExpression());
		pageYField.setBookmarkLevel(pageXofY.getBookmarkLevel());
		pageYField.setHyperLink((DRHyperLink) pageXofY.getHyperLink());
		pageYField.setPrintWhenExpression(pageXofY.getPrintWhenExpression());
		pageYField.setStyle(newStylePageY);
		pageYField.setHeight(height);
		pageYField.setHeightType(pageXofY.getHeightType());
		pageYField.setHorizontalAlignment(HorizontalAlignment.LEFT);
		pageYField.setValueExpression(new PageXofYNumberExpression(pageXofY.getFormatExpression(), 1));
		DRIGroup pageYEvaluationGroup = accessor.getGroupTransform().getFirstResetPageNumberGroup();
		if (pageYEvaluationGroup == null) {
			pageYField.setEvaluationTime(Evaluation.REPORT);
		}
		else {
			pageYField.setEvaluationTime(Evaluation.GROUP);
			pageYField.setEvaluationGroup((DRGroup) pageYEvaluationGroup);
		}

		int pageXofYWidth = templateTransform.getPageXofYWidth(pageXofY);
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
			pageYWidth = StyleResolver.getFontWidth(style, 6);
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

		if (pageXofY.getPageXWidth() != null) {
			pageXField.setWidth(pageXofY.getPageXWidth());
		}
		if (pageXofY.getPageXWidthType() != null) {
			pageXField.setWidthType(pageXofY.getPageXWidthType());
		}
		if (pageXofY.getPageYWidth() != null) {
			pageYField.setWidth(pageXofY.getPageYWidth());
		}
		if (pageXofY.getPageYWidthType() != null) {
			pageYField.setWidthType(pageXofY.getPageYWidthType());
		}

		DRList listPageXofY = new DRList();
		listPageXofY.addComponent(pageXField);
		listPageXofY.addComponent(pageYField);
		return list(listPageXofY, DefaultStyleType.TEXT, null, null);
	}

	//total pages
	private DRDesignTextField totalPages(DRITotalPages totalPages, DefaultStyleType defaultStyleType) throws DRException {
		PageNumberExpression expression = new PageNumberExpression(totalPages.getFormatExpression());
		DRTextField<String> totalPagesField = formatField(totalPages, expression);
		DRIGroup pageEvaluationGroup = accessor.getGroupTransform().getFirstResetPageNumberGroup();
		if (pageEvaluationGroup == null) {
			totalPagesField.setEvaluationTime(Evaluation.REPORT);
		}
		else {
			totalPagesField.setEvaluationTime(Evaluation.GROUP);
			totalPagesField.setEvaluationGroup((DRGroup) pageEvaluationGroup);
		}

		return textField(totalPagesField, defaultStyleType);
	}

	//page number
	private DRDesignTextField pageNumber(DRIPageNumber pageNumber, DefaultStyleType defaultStyleType) throws DRException {
		PageNumberExpression expression = new PageNumberExpression(pageNumber.getFormatExpression());
		return textField(formatField(pageNumber, expression), defaultStyleType);
	}

	//current date
	private DRDesignTextField currentDate(DRICurrentDate currentDate, DefaultStyleType defaultStyleType) throws DRException {
		CurrentDateExpression expression = new CurrentDateExpression(currentDate.getFormatExpression(), currentDate.getPattern());
		return textField(formatField(currentDate, expression), defaultStyleType);
	}

	//format field
	private DRTextField<String> formatField(DRIFormatField formatField, DRIExpression<String> expression) throws DRException {
		DRTextField<String> formatFieldTextField = new DRTextField<String>();
		formatFieldTextField.setAnchorNameExpression(formatField.getAnchorNameExpression());
		formatFieldTextField.setBookmarkLevel(formatField.getBookmarkLevel());
		formatFieldTextField.setHyperLink((DRHyperLink) formatField.getHyperLink());
		formatFieldTextField.setPrintWhenExpression(formatField.getPrintWhenExpression());
		formatFieldTextField.setStyle(formatField.getStyle());
		formatFieldTextField.setWidth(formatField.getWidth());
		formatFieldTextField.setWidthType(formatField.getWidthType());
		formatFieldTextField.setHeight(formatField.getHeight());
		formatFieldTextField.setHeightType(formatField.getHeightType());
		formatFieldTextField.setHorizontalAlignment(formatField.getHorizontalAlignment());
		formatFieldTextField.setValueExpression(expression);
		return formatFieldTextField;
	}

	//line
	protected DRDesignLine line(DRILine line) throws DRException {
		DRDesignLine designLine = new DRDesignLine();
		component(designLine, line, line.getStyle(), false, DefaultStyleType.NONE);
		designLine.setDirection(line.getDirection());
		designLine.setPen(accessor.getStyleTransform().pen(line.getPen()));
		designLine.setWidth(accessor.getTemplateTransform().getLineWidth(line));
		designLine.setHeight(accessor.getTemplateTransform().getLineHeight(line));
		return designLine;
	}

	//ellipse
	protected DRDesignEllipse ellipse(DRIEllipse ellipse) throws DRException {
		DRDesignEllipse designEllipse = new DRDesignEllipse();
		component(designEllipse, ellipse, ellipse.getStyle(), false, DefaultStyleType.NONE);
		designEllipse.setPen(accessor.getStyleTransform().pen(ellipse.getPen()));
		designEllipse.setWidth(accessor.getTemplateTransform().getEllipseWidth(ellipse));
		designEllipse.setHeight(accessor.getTemplateTransform().getEllipseHeight(ellipse));
		return designEllipse;
	}

	//rectangle
	protected DRDesignRectangle rectangle(DRIRectangle rectangle) throws DRException {
		DRDesignRectangle designRectangle = new DRDesignRectangle();
		component(designRectangle, rectangle, rectangle.getStyle(), false, DefaultStyleType.NONE);
		designRectangle.setRadius(accessor.getTemplateTransform().getRectangleRadius(rectangle));
		designRectangle.setPen(accessor.getStyleTransform().pen(rectangle.getPen()));
		designRectangle.setWidth(accessor.getTemplateTransform().getRectangleWidth(rectangle));
		designRectangle.setHeight(accessor.getTemplateTransform().getRectangleHeight(rectangle));
		return designRectangle;
	}

	//boolean
	protected DRDesignComponent booleanField(DRIBooleanField booleanField, DefaultStyleType defaultStyleType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		BooleanComponentType componentType = accessor.getTemplateTransform().getBooleanComponentType(booleanField);
		boolean emptyWhenNullValue = accessor.getTemplateTransform().getBooleanEmptyWhenNullValue(booleanField);;
		DRHyperLinkComponent component = null;

		switch (componentType) {
		case TEXT_TRUE_FALSE:
		case TEXT_YES_NO:
			String keyTrue;
			String keyFalse;
			if (componentType.equals(BooleanComponentType.TEXT_TRUE_FALSE)) {
				keyTrue = "true";
				keyFalse = "false";
			}
			else {
				keyTrue = "yes";
				keyFalse = "no";
			}
			DRTextField<Boolean> textField = new DRTextField<Boolean>();
			textField.setValueExpression(booleanField.getValueExpression());
			textField.setDataType(DataTypes.booleanType());
			textField.setHorizontalAlignment(booleanField.getHorizontalAlignment());
			textField.setValueFormatter(new BooleanTextValueFormatter(keyTrue, keyFalse, emptyWhenNullValue));
			component = textField;
			break;
		case IMAGE_STYLE_1:
		case IMAGE_STYLE_2:
		case IMAGE_STYLE_3:
		case IMAGE_STYLE_4:
		case IMAGE_CHECKBOX_1:
		case IMAGE_CHECKBOX_2:
		case IMAGE_BALL:
			DRImage image = new DRImage();
			image.setImageScale(ImageScale.NO_RESIZE);
			image.setImageExpression(new BooleanImageExpression(booleanField, emptyWhenNullValue));
			component = image;
			break;
		default:
			throw new DRDesignReportException("Boolean component type " + componentType.name() + " not supported");
		}

		component.setWidth(booleanField.getWidth());
		component.setWidthType(booleanField.getWidthType());
		component.setHeight(booleanField.getHeight());
		component.setHeightType(booleanField.getHeightType());
		component.setAnchorNameExpression(booleanField.getAnchorNameExpression());
		component.setBookmarkLevel(booleanField.getBookmarkLevel());
		component.setHyperLink((DRHyperLink) booleanField.getHyperLink());
		component.setStyle(booleanField.getStyle());
		component.setPrintWhenExpression(booleanField.getPrintWhenExpression());
		component.setPropertyExpressions(booleanField.getPropertyExpressions());

		DRDesignComponent designComponent;
		if (component instanceof DRIImage) {
			int imageHeight = accessor.getTemplateTransform().getBooleanImageHeight(booleanField);
			designComponent = image((DRIImage) component, imageHeight, defaultStyleType);
			TemplateTransform templateTransform = accessor.getTemplateTransform();
			((DRDesignImage) designComponent).setHorizontalAlignment(templateTransform.getBooleanHorizontalAlignment(booleanField, designComponent.getStyle()));
		}
		else {
			designComponent = component(component, defaultStyleType, resetType, resetGroup);
		}
		return designComponent;
	}

	//break
	protected DRDesignBreak breakComponent(DRIBreak breakComponent) throws DRException {
		DRDesignBreak designBreak = new DRDesignBreak();
		component(designBreak, breakComponent, null, false, DefaultStyleType.NONE);
		designBreak.setType(breakComponent.getType());
		designBreak.setWidth(accessor.getTemplateTransform().getBreakWidth(breakComponent));
		designBreak.setHeight(accessor.getTemplateTransform().getBreakHeight(breakComponent));
		return designBreak;
	}

	//generic element
	protected DRDesignGenericElement genericElement(DRIGenericElement genericElement, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignGenericElement designGenericElement = new DRDesignGenericElement();
		component(designGenericElement, genericElement, genericElement.getStyle(), false, DefaultStyleType.NONE);
		designGenericElement.setGenericElementNamespace(genericElement.getGenericElementNamespace());
		designGenericElement.setGenericElementName(genericElement.getGenericElementName());
		designGenericElement.setEvaluationTime(evaluationTimeFromResetType(resetType));
		designGenericElement.setEvaluationGroup(resetGroup);
		designGenericElement.setWidth(accessor.getTemplateTransform().getGenericElementWidth(genericElement));
		designGenericElement.setHeight(accessor.getTemplateTransform().getGenericElementHeight(genericElement));
		for (DRIParameterExpression parameterExpression : genericElement.getParameterExpressions()) {
			designGenericElement.getParameterExpressions().add(accessor.getExpressionTransform().transformParameterExpression(parameterExpression));
		}
		return designGenericElement;
	}

	//crosstab
	private DRDesignCrosstab crosstab(DRICrosstab crosstab, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignCrosstab designCrosstab = accessor.getCrosstabTransform().transform(crosstab, resetType, resetGroup);
		component(designCrosstab, crosstab, crosstab.getStyle(), false, DefaultStyleType.NONE);
		return designCrosstab;
	}

	//map
	private DRDesignMap map(DRIMap map, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignMap designMap = new DRDesignMap();
		component(designMap, map, map.getStyle(), false, DefaultStyleType.NONE);
		designMap.setLatitudeExpression(accessor.getExpressionTransform().transformExpression(map.getLatitudeExpression()));
		designMap.setLongitudeExpression(accessor.getExpressionTransform().transformExpression(map.getLongitudeExpression()));
		designMap.setZoomExpression(accessor.getExpressionTransform().transformExpression(map.getZoomExpression()));
		designMap.setWidth(accessor.getTemplateTransform().getMapWidth(map));
		designMap.setHeight(accessor.getTemplateTransform().getMapHeight(map));
		designMap.setEvaluationTime(evaluationTimeFromResetType(resetType));
		designMap.setEvaluationGroup(resetGroup);
		return designMap;
	}

	//custom component
	private DRDesignComponent customComponent(DRICustomComponent component, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		@SuppressWarnings("rawtypes")
		CustomComponentTransform componentTransfom = CustomComponents.getComponentTransform(component);
		if (componentTransfom == null) {
			throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
		}
		@SuppressWarnings("unchecked")
		DRDesignComponent designComponent = (DRDesignComponent) componentTransfom.designComponent(accessor, component, resetType, resetGroup);
		component(designComponent, component, component.getStyle(), false, DefaultStyleType.NONE);
		DRIDimensionComponent dimensionComponent = component;
		if (designComponent.getWidth() == null) {
			designComponent.setWidth(accessor.getTemplateTransform().getCustomComponentWidth(dimensionComponent));
		}
		if (designComponent.getHeight() == null) {
			designComponent.setHeight(accessor.getTemplateTransform().getCustomComponentHeight(dimensionComponent));
		}
		return designComponent;
	}

	protected EvaluationTime detectEvaluationTime(DRIDesignExpression expression) {
		if (expression == null) {
			return null;
		}

		if (expression instanceof DRIDesignField || expression instanceof DRIDesignSystemExpression ||
				expression instanceof DRIDesignSimpleExpression || expression instanceof DRIDesignJasperExpression) {
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

	public EvaluationTime evaluationTimeFromResetType(ResetType resetType) {
		if (resetType == null) {
			return null;
		}

		switch (resetType) {
		case NONE:
			return EvaluationTime.NOW;
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
		if (expression != null && evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP)) {
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

	private class PageXofYNumberExpression extends AbstractComplexExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		private int index;

		public PageXofYNumberExpression(DRIExpression<String> pageNumberFormatExpression, int index) {
			addExpression(pageNumberFormatExpression);
			this.index = index;
		}

		@Override
		public String evaluate(List<?> values, ReportParameters reportParameters) {
			String pattern = (String) values.get(0);
			Validate.isTrue(StringUtils.contains(pattern, "{0}"), "Wrong format pattern \"" + pattern + "\", missing argument {0}");
			Validate.isTrue(StringUtils.contains(pattern, "{1}"), "Wrong format pattern \"" + pattern + "\", missing argument {1}");
			Validate.isTrue(pattern.indexOf("{0}") < pattern.indexOf("{1}"), "Wrong format pattern \"" + pattern + "\", argument {0} must be before {1}");
			int index1 = pattern.indexOf("{0}");
			if (index == 0) {
				pattern = pattern.substring(0, index1 + 3);
			}
			else {
				pattern = pattern.substring(index1 + 3);
			}
			MessageFormat format = new MessageFormat(pattern, reportParameters.getLocale());
			String result = format.format(new Object[]{reportParameters.getPageNumber(), reportParameters.getPageNumber()});
			return result;
		}
	}

	private class PageNumberExpression extends AbstractComplexExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		public PageNumberExpression(DRIExpression<String> pageNumberFormatExpression) {
			addExpression(pageNumberFormatExpression);
		}

		@Override
		public String evaluate(List<?> values, ReportParameters reportParameters) {
			String pattern = (String) values.get(0);
			MessageFormat format = new MessageFormat(pattern, reportParameters.getLocale());
			String result = format.format(new Object[]{reportParameters.getPageNumber()});
			return result;
		}
	}

	private class CurrentDateExpression extends AbstractComplexExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
		private String datePattern;

		public CurrentDateExpression(DRIExpression<String> currentDateExpression, String datePattern) {
			this.datePattern = datePattern;
			addExpression(currentDateExpression);
		}

		@Override
		public String evaluate(List<?> values, ReportParameters reportParameters) {
			String pattern = (String) values.get(0);
			Locale locale = reportParameters.getLocale();
			MessageFormat format = new MessageFormat(pattern, locale);
			String date;
			if (datePattern == null) {
				date = DataTypes.dateType().valueToString(new Date(), locale);
			}
			else {
				date = new SimpleDateFormat(datePattern, locale).format(new Date());
			}
			String result = format.format(new Object[]{date});
			return result;
		}
	}

	private class BooleanTextValueFormatter extends AbstractValueFormatter<String, Boolean> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
		private String keyTrue;
		private String keyFalse;
		private boolean emptyWhenNullValue;

		private BooleanTextValueFormatter(String keyTrue, String keyFalse, boolean emptyWhenNullValue) {
			this.keyTrue = keyTrue;
			this.keyFalse = keyFalse;
			this.emptyWhenNullValue = emptyWhenNullValue;
		}

		@Override
		public String format(Boolean value, ReportParameters reportParameters) {
			if (emptyWhenNullValue && value == null) {
				return "";
			}
			String key;
			if (value != null && value) {
				key = keyTrue;
			} else {
				key = keyFalse;
			}
			return ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_NAME, reportParameters.getLocale()).getString(key);
		}
	}

	private class BooleanImageExpression extends AbstractComplexExpression<Renderable> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
		private Renderable imageTrue;
		private Renderable imageFalse;
		private boolean emptyWhenNullValue;

		private BooleanImageExpression(DRIBooleanField booleanField, boolean emptyWhenNullValue) throws DRException {
			this.emptyWhenNullValue = emptyWhenNullValue;
			addExpression(booleanField.getValueExpression());
			String fileNameTrue;
			String fileNameFalse;
			switch (booleanField.getComponentType()) {
			case IMAGE_STYLE_1:
				fileNameTrue = "boolean1_true";
				fileNameFalse = "boolean1_false";
				break;
			case IMAGE_STYLE_2:
				fileNameTrue = "boolean2_true";
				fileNameFalse = "boolean2_false";
				break;
			case IMAGE_STYLE_3:
				fileNameTrue = "boolean3_true";
				fileNameFalse = "boolean3_false";
				break;
			case IMAGE_STYLE_4:
				fileNameTrue = "boolean1_true";
				fileNameFalse = "boolean4_false";
				break;
			case IMAGE_CHECKBOX_1:
				fileNameTrue = "checkbox1_true";
				fileNameFalse = "checkbox_false";
				break;
			case IMAGE_CHECKBOX_2:
				fileNameTrue = "checkbox2_true";
				fileNameFalse = "checkbox_false";
				break;
			case IMAGE_BALL:
				fileNameTrue = "ball_green";
				fileNameFalse = "ball_red";
				break;
			default:
				throw new DRDesignReportException("BooleanComponentType " + booleanField.getComponentType().name() + " not supported");
			}
			try {
				int width = accessor.getTemplateTransform().getBooleanImageWidth(booleanField);
				int height = accessor.getTemplateTransform().getBooleanImageHeight(booleanField);
				imageTrue = new CustomBatikRenderer(ReportUtils.class.getResource("images/" + fileNameTrue + ".svg"), width, height);
				imageFalse = new CustomBatikRenderer(ReportUtils.class.getResource("images/" + fileNameFalse + ".svg"), width, height);
			} catch (JRException e) {
				throw new DRException(e);
			}
		}

		@Override
		public Renderable evaluate(List<?> values, ReportParameters reportParameters) {
			Boolean value = (Boolean) values.get(0);
			if (emptyWhenNullValue && value == null) {
				return null;
			}
			if (value != null && value) {
				return imageTrue;
			} else {
				return imageFalse;
			}
		}
	}

	private class MultiPageListSubreportExpression extends AbstractSimpleExpression<JasperReportBuilder> {
		private static final long serialVersionUID = 1L;

		private List<DRIComponent> detailComponents;

		public MultiPageListSubreportExpression(List<DRIComponent> detailComponents) {
			this.detailComponents = detailComponents;
		}

		@Override
		public JasperReportBuilder evaluate(ReportParameters reportParameters) {
      JasperReportBuilder report = report();
      DRBand titleBand = report.getReport().getTitleBand();
      DRComponent detailComponent = (DRComponent) detailComponents.get(reportParameters.getReportRowNumber() - 1);
      titleBand.addComponent(detailComponent);
			return report;
		}
	}

}
