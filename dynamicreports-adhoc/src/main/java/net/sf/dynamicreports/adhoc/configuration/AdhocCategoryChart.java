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

package net.sf.dynamicreports.adhoc.configuration;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocCategoryChart extends AdhocChart {
	private static final long serialVersionUID = 1L;

	private AdhocCategoryChartType type;
	private String category;
	private List<AdhocCategoryChartSerie> series;
	private List<Color> seriesColors;
	private AdhocAxisFormat categoryAxis;
	private AdhocAxisFormat valueAxis;
	private AdhocOrientation orientation;
	private Boolean useSeriesAsCategory;

	public AdhocCategoryChart() {
		series = new ArrayList<AdhocCategoryChartSerie>();
		seriesColors = new ArrayList<Color>();
	}

	public AdhocCategoryChartType getType() {
		return type;
	}

	public void setType(AdhocCategoryChartType type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<AdhocCategoryChartSerie> getSeries() {
		return series;
	}

	public void addSerie(AdhocCategoryChartSerie serie) {
		this.series.add(serie);
	}

	public void setSeries(List<AdhocCategoryChartSerie> series) {
		this.series = series;
	}

	public List<Color> getSeriesColors() {
		return seriesColors;
	}

	public void addSeriesColor(Color seriesColor) {
		this.seriesColors.add(seriesColor);
	}

	public void setSeriesColors(List<Color> seriesColors) {
		this.seriesColors = seriesColors;
	}

	public AdhocAxisFormat getCategoryAxis() {
		return categoryAxis;
	}

	public void setCategoryAxis(AdhocAxisFormat categoryAxis) {
		this.categoryAxis = categoryAxis;
	}

	public AdhocAxisFormat getValueAxis() {
		return valueAxis;
	}

	public void setValueAxis(AdhocAxisFormat valueAxis) {
		this.valueAxis = valueAxis;
	}

	public AdhocOrientation getOrientation() {
		return orientation;
	}

	public void setOrientation(AdhocOrientation orientation) {
		this.orientation = orientation;
	}

	public Boolean getUseSeriesAsCategory() {
		return useSeriesAsCategory;
	}

	public void setUseSeriesAsCategory(Boolean useSeriesAsCategory) {
		this.useSeriesAsCategory = useSeriesAsCategory;
	}

}
