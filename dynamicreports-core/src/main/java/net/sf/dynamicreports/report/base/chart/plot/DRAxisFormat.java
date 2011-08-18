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

package net.sf.dynamicreports.report.base.chart.plot;

import java.awt.Color;

import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.chart.plot.DRIAxisFormat;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRAxisFormat implements DRIAxisFormat {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private DRIExpression<String> labelExpression;
	private DRFont labelFont;
	private Color labelColor;
	private DRFont tickLabelFont;
	private Color tickLabelColor;
	private String tickLabelMask;
	private Boolean verticalTickLabels;
	private Double tickLabelRotation;
	private Color lineColor;
	private DRIExpression<? extends Number> rangeMinValueExpression;
	private DRIExpression<? extends Number> rangeMaxValueExpression;

	public DRIExpression<String> getLabelExpression() {
		return labelExpression;
	}

	public void setLabelExpression(DRIExpression<String> labelExpression) {
		this.labelExpression = labelExpression;
	}

	public DRFont getLabelFont() {
		return labelFont;
	}

	public void setLabelFont(DRFont labelFont) {
		this.labelFont = labelFont;
	}

	public Color getLabelColor() {
		return labelColor;
	}

	public void setLabelColor(Color labelColor) {
		this.labelColor = labelColor;
	}

	public DRFont getTickLabelFont() {
		return tickLabelFont;
	}

	public void setTickLabelFont(DRFont tickLabelFont) {
		this.tickLabelFont = tickLabelFont;
	}

	public Color getTickLabelColor() {
		return tickLabelColor;
	}

	public void setTickLabelColor(Color tickLabelColor) {
		this.tickLabelColor = tickLabelColor;
	}

	public String getTickLabelMask() {
		return tickLabelMask;
	}

	public void setTickLabelMask(String tickLabelMask) {
		this.tickLabelMask = tickLabelMask;
	}

	public Boolean getVerticalTickLabels() {
		return verticalTickLabels;
	}

	public void setVerticalTickLabels(Boolean verticalTickLabels) {
		this.verticalTickLabels = verticalTickLabels;
	}

	public Double getTickLabelRotation() {
		return tickLabelRotation;
	}

	public void setTickLabelRotation(Double tickLabelRotation) {
		this.tickLabelRotation = tickLabelRotation;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public void setRangeMinValueExpression(DRIExpression<? extends Number> rangeMinValueExpression) {
		this.rangeMinValueExpression = rangeMinValueExpression;
	}

	public DRIExpression<? extends Number> getRangeMinValueExpression() {
		return rangeMinValueExpression;
	}

	public void setRangeMaxValueExpression(DRIExpression<? extends Number> rangeMaxValueExpression) {
		this.rangeMaxValueExpression = rangeMaxValueExpression;
	}

	public DRIExpression<? extends Number> getRangeMaxValueExpression() {
		return rangeMaxValueExpression;
	}
}
