/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
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

package net.sf.dynamicreports.report.base.style;

import java.awt.Color;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.definition.style.DRIBaseStyle;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class DRBaseStyle implements DRIBaseStyle {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Color foregroundColor;
	private Color backgroundColor;
	private Integer radius;
	private ImageScale imageScale;
	private HorizontalAlignment horizontalAlignment;
	private VerticalAlignment verticalAlignment;
	private DRBorder border;
	private DRPadding padding;
	private DRFont font;
	private Rotation rotation;
	private String pattern;
	private Markup markup;
	private DRParagraph paragraph;
	private DRPen linePen;

	public DRBaseStyle() {
		init();
	}

	protected void init() {
		font = new DRFont();
		border = new DRBorder();
		padding = new DRPadding();
		paragraph = new DRParagraph();
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public ImageScale getImageScale() {
		return imageScale;
	}

	public void setImageScale(ImageScale imageScale) {
		this.imageScale = imageScale;
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public DRBorder getBorder() {
		return border;
	}

	public void setBorder(DRBorder border) {
		this.border = border;
	}

	public DRPadding getPadding() {
		return padding;
	}

	public void setPadding(DRPadding padding) {
		this.padding = padding;
	}

	public DRFont getFont() {
		return font;
	}

	public void setFont(DRFont font) {
		this.font = font;
	}

	public Rotation getRotation() {
		return rotation;
	}

	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Markup getMarkup() {
		return markup;
	}

	public void setMarkup(Markup markup) {
		this.markup = markup;
	}

	public DRParagraph getParagraph() {
		return paragraph;
	}

	public void setParagraph(DRParagraph paragraph) {
		this.paragraph = paragraph;
	}

	public DRPen getLinePen() {
		return linePen;
	}

	public void setLinePen(DRPen linePen) {
		this.linePen = linePen;
	}

}
