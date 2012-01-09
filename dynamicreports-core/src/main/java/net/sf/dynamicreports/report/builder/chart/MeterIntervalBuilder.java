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

package net.sf.dynamicreports.report.builder.chart;

import java.awt.Color;

import net.sf.dynamicreports.report.base.chart.plot.DRMeterInterval;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class MeterIntervalBuilder extends AbstractBuilder<MeterIntervalBuilder, DRMeterInterval> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected MeterIntervalBuilder() {
		super(new DRMeterInterval());
	}

	public void setLabel(String label) {
		getObject().setLabel(label);
	}

	public void setBackgroundColor(Color backgroundColor) {
		getObject().setBackgroundColor(backgroundColor);
	}

	public void setAlpha(Double alpha) {
		getObject().setAlpha(alpha);
	}

	public void setDataRangeLowExpression(Number dataRangeLowValue) {
		getObject().setDataRangeLowExpression(Expressions.number(dataRangeLowValue));
	}

	public void setDataRangeLowExpression(DRIExpression<? extends Number> dataRangeLowExpression) {
		getObject().setDataRangeLowExpression(dataRangeLowExpression);
	}

	public void setDataRangeHighExpression(Number dataRangeHighValue) {
		getObject().setDataRangeHighExpression(Expressions.number(dataRangeHighValue));
	}

	public void setDataRangeHighExpression(DRIExpression<? extends Number> dataRangeHighExpression) {
		getObject().setDataRangeHighExpression(dataRangeHighExpression);
	}

}
