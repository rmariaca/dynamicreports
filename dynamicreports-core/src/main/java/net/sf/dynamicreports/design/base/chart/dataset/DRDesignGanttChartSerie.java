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

package net.sf.dynamicreports.design.base.chart.dataset;

import net.sf.dynamicreports.design.definition.chart.dataset.DRIDesignGanttChartSerie;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignGanttChartSerie extends AbstractDesignChartSerie implements DRIDesignGanttChartSerie {
	private DRIDesignExpression startDateExpression;
	private DRIDesignExpression endDateExpression;
	private DRIDesignExpression percentExpression;
	private DRIDesignExpression labelExpression;

	@Override
	public DRIDesignExpression getStartDateExpression() {
		return startDateExpression;
	}

	public void setStartDateExpression(DRIDesignExpression startDateExpression) {
		this.startDateExpression = startDateExpression;
	}

	@Override
	public DRIDesignExpression getEndDateExpression() {
		return endDateExpression;
	}

	public void setEndDateExpression(DRIDesignExpression endDateExpression) {
		this.endDateExpression = endDateExpression;
	}

	@Override
	public DRIDesignExpression getPercentExpression() {
		return percentExpression;
	}

	public void setPercentExpression(DRIDesignExpression percentExpression) {
		this.percentExpression = percentExpression;
	}

	@Override
	public DRIDesignExpression getLabelExpression() {
		return labelExpression;
	}

	public void setLabelExpression(DRIDesignExpression labelExpression) {
		this.labelExpression = labelExpression;
	}

}
