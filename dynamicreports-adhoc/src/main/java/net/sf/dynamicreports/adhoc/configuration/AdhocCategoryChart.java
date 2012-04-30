package net.sf.dynamicreports.adhoc.configuration;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class AdhocCategoryChart extends AdhocChart {
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
