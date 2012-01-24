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

import net.sf.dynamicreports.report.base.chart.dataset.DRGanttChartSerie;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GanttChartSerieBuilder extends AbstractChartSerieBuilder<GanttChartSerieBuilder, DRGanttChartSerie> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected GanttChartSerieBuilder() {
		super(new DRGanttChartSerie());
	}

	public GanttChartSerieBuilder setTaskExpression(DRIExpression<?> taskExpression) {
		getObject().setTaskExpression(taskExpression);
		return this;
	}

	public GanttChartSerieBuilder setSubtaskExpression(DRIExpression<?> subtaskExpression) {
		getObject().setSubtaskExpression(subtaskExpression);
		return this;
	}

	public GanttChartSerieBuilder setStartDateExpression(DRIExpression<?> startDateExpression) {
		getObject().setStartDateExpression(startDateExpression);
		return this;
	}

	public GanttChartSerieBuilder setEndDateExpression(DRIExpression<?> endDateExpression) {
		getObject().setEndDateExpression(endDateExpression);
		return this;
	}

	public GanttChartSerieBuilder setPercentExpression(DRIExpression<?> percentExpression) {
		getObject().setPercentExpression(percentExpression);
		return this;
	}

	public GanttChartSerieBuilder setLabelExpression(DRIExpression<?> labelExpression) {
		getObject().setLabelExpression(labelExpression);
		return this;
	}

	public DRGanttChartSerie getChartSerie() {
		return build();
	}
}
