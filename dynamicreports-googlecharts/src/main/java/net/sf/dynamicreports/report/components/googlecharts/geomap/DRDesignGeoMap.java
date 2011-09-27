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

package net.sf.dynamicreports.report.components.googlecharts.geomap;

import java.awt.Color;
import java.util.List;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.jasper.components.googlecharts.geomap.GeoMapPrintElement;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignGeoMap extends DRDesignComponent implements DRIDesignGeoMap {
	private EvaluationTime evaluationTime;
	private DRDesignGroup evaluationGroup;
	private Boolean showLegend;
	private GeoMapDataMode dataMode;
	private DRIDesignExpression regionExpression;
	private List<Color> colors;
	private DRDesignGeoMapDataset dataset;

	public DRDesignGeoMap() {
		super(GeoMapPrintElement.GEOMAP_ELEMENT_NAME);
	}

	public EvaluationTime getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(EvaluationTime evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	public DRDesignGroup getEvaluationGroup() {
		return evaluationGroup;
	}

	public void setEvaluationGroup(DRDesignGroup evaluationGroup) {
		this.evaluationGroup = evaluationGroup;
	}

	public Boolean getShowLegend() {
		return showLegend;
	}

	public void setShowLegend(Boolean showLegend) {
		this.showLegend = showLegend;
	}

	public GeoMapDataMode getDataMode() {
		return dataMode;
	}

	public void setDataMode(GeoMapDataMode dataMode) {
		this.dataMode = dataMode;
	}

	public DRIDesignExpression getRegionExpression() {
		return regionExpression;
	}

	public void setRegionExpression(DRIDesignExpression regionExpression) {
		this.regionExpression = regionExpression;
	}

	public List<Color> getColors() {
		return colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}

	public DRDesignGeoMapDataset getDataset() {
		return dataset;
	}

	public void setDataset(DRDesignGeoMapDataset dataset) {
		this.dataset = dataset;
	}
}
