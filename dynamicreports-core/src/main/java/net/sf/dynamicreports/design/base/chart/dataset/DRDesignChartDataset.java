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

package net.sf.dynamicreports.design.base.chart.dataset;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.chart.dataset.DRIDesignChartDataset;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignChartDataset implements DRIDesignChartDataset {	
	private DRIDesignExpression valueExpression;
	private List<DRDesignChartSerie> series;
	private ResetType resetType;
	private DRDesignGroup resetGroup;	
	
	public DRDesignChartDataset() {
		series = new ArrayList<DRDesignChartSerie>();
	}
	
	public DRIDesignExpression getValueExpression() {
		return valueExpression;
	}
	
	public void setValueExpression(DRIDesignExpression valueExpression) {
		this.valueExpression = valueExpression;
	}
	
	public void addSerie(DRDesignChartSerie serie) {
		series.add(serie);
	}
	
	public List<DRDesignChartSerie> getSeries() {
		return series;
	}
	
	public ResetType getResetType() {
		return resetType;
	}
	
	public void setResetType(ResetType resetType) {
		this.resetType = resetType;
	}
	
	public DRDesignGroup getResetGroup() {
		return resetGroup;
	}
	
	public void setResetGroup(DRDesignGroup resetGroup) {
		this.resetGroup = resetGroup;
	}	
}
