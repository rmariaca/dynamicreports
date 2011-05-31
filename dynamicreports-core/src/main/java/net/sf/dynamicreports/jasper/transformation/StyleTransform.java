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

import net.sf.dynamicreports.design.definition.style.DRIDesignBaseStyle;
import net.sf.dynamicreports.design.definition.style.DRIDesignBorder;
import net.sf.dynamicreports.design.definition.style.DRIDesignConditionalStyle;
import net.sf.dynamicreports.design.definition.style.DRIDesignFont;
import net.sf.dynamicreports.design.definition.style.DRIDesignPadding;
import net.sf.dynamicreports.design.definition.style.DRIDesignPen;
import net.sf.dynamicreports.design.definition.style.DRIDesignStyle;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignFont;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.ModeEnum;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class StyleTransform {
	private JasperTransformAccessor accessor;

	public StyleTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}

	public void transform() {
		for (DRIDesignStyle style : accessor.getReport().getStyles()) {
			addStyle(style);
		}
	}

	private void addStyle(DRIDesignStyle style) {
		try {
			accessor.getDesign().addStyle(style(style));
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for style \"" + style.getName() + "\"", e);
		}
	}

	//style
	private JRDesignStyle style(DRIDesignStyle style) {
		JRDesignStyle jrStyle = new JRDesignStyle();
		abstractStyle(jrStyle, style);

		jrStyle.setName(style.getName());
		DRIDesignStyle parentStyle = style.getParentStyle();
		if (parentStyle != null) {
			style(parentStyle);
			jrStyle.setParentStyleNameReference(parentStyle.getName());
		}
		for (DRIDesignConditionalStyle conditionalStyle : style.getConditionalStyles()) {
			jrStyle.addConditionalStyle(conditionalStyle(conditionalStyle));
		}
		return jrStyle;
	}

	private JRDesignConditionalStyle conditionalStyle(DRIDesignConditionalStyle conditionalStyle) {
		JRDesignConditionalStyle jrConditionalStyle = new JRDesignConditionalStyle();
		abstractStyle(jrConditionalStyle, conditionalStyle);

		jrConditionalStyle.setConditionExpression(accessor.getExpressionTransform().getExpression(conditionalStyle.getConditionExpression()));

		return jrConditionalStyle;
	}

	private void abstractStyle(JRBaseStyle baseStyle, DRIDesignBaseStyle style) {
		baseStyle.setForecolor(style.getForegroundColor());
		baseStyle.setBackcolor(style.getBackgroundColor());
		if (style.getBackgroundColor() != null) {
			baseStyle.setMode(ModeEnum.OPAQUE);
		}
		baseStyle.setRadius(style.getRadius());
		baseStyle.setScaleImage(ConstantTransform.imageScale(style.getImageScale()));
		baseStyle.setHorizontalAlignment(ConstantTransform.horizontalAlignment(style.getHorizontalAlignment()));
		baseStyle.setVerticalAlignment(ConstantTransform.verticalAlignment(style.getVerticalAlignment()));
		border(baseStyle.getLineBox(), style.getBorder());
		padding(baseStyle.getLineBox(), style.getPadding());
		font(baseStyle, style.getFont());
		baseStyle.setRotation(ConstantTransform.rotation(style.getRotation()));
		baseStyle.setPattern(style.getPattern());
		baseStyle.setMarkup(ConstantTransform.markup(style.getMarkup()));
		baseStyle.setBlankWhenNull(true);
		baseStyle.setLineSpacing(ConstantTransform.lineSpacing(style.getLineSpacing()));
		pen(baseStyle.getLinePen(), style.getLinePen());
	}

	protected void pen(JRPen jrPen, DRIDesignPen pen) {
		if (pen == null)
			return;

		jrPen.setLineColor(pen.getLineColor());
		jrPen.setLineStyle(ConstantTransform.lineStyle(pen.getLineStyle()));
		jrPen.setLineWidth(pen.getLineWidth());
	}

	private void border(JRLineBox lineBox, DRIDesignBorder border) {
		if (border == null)
			return;

		pen(lineBox.getLeftPen(), border.getLeftPen());
		pen(lineBox.getRightPen(), border.getRightPen());
		pen(lineBox.getTopPen(), border.getTopPen());
		pen(lineBox.getBottomPen(), border.getBottomPen());
	}

	private void padding(JRLineBox lineBox, DRIDesignPadding padding) {
		if (padding == null)
			return;

		lineBox.setLeftPadding(padding.getLeft());
		lineBox.setRightPadding(padding.getRight());
		lineBox.setTopPadding(padding.getTop());
		lineBox.setBottomPadding(padding.getBottom());
	}

	private void font(JRBaseStyle baseStyle, DRIDesignFont font) {
		if (font == null)
			return;

		baseStyle.setFontName(font.getFontName());
		baseStyle.setBold(font.getBold());
		baseStyle.setItalic(font.getItalic());
		baseStyle.setFontSize(font.getFontSize());
		baseStyle.setStrikeThrough(font.getStrikeThrough());
		baseStyle.setUnderline(font.getUnderline());
		baseStyle.setPdfFontName(font.getPdfFontName());
		baseStyle.setPdfEncoding(font.getPdfEncoding());
		baseStyle.setPdfEmbedded(font.getPdfEmbedded());
	}

	protected JRDesignFont font(DRIDesignFont font) {
		if (font == null)
			return null;

		JRDesignFont jrFont = new JRDesignFont();
		jrFont.setFontName(font.getFontName());
		jrFont.setBold(font.getBold());
		jrFont.setItalic(font.getItalic());
		jrFont.setFontSize(font.getFontSize());
		jrFont.setStrikeThrough(font.getStrikeThrough());
		jrFont.setUnderline(font.getUnderline());
		jrFont.setPdfFontName(font.getPdfFontName());
		jrFont.setPdfEncoding(font.getPdfEncoding());
		jrFont.setPdfEmbedded(font.getPdfEmbedded());
		return jrFont;
	}

	protected JRDesignStyle getStyle(DRIDesignStyle style) {
		if (style == null)
			return null;
		if (!accessor.getDesign().getStylesMap().containsKey(style.getName())) {
			throw new JasperDesignException("Style \"" + style.getName() + "\" is not registered");
		}
		return (JRDesignStyle) accessor.getDesign().getStylesMap().get(style.getName());
	}
}
