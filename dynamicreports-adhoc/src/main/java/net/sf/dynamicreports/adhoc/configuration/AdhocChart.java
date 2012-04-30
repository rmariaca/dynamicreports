package net.sf.dynamicreports.adhoc.configuration;

import java.awt.Color;

public class AdhocChart extends AdhocComponent {
	private String title;
	private AdhocFont titleFont;
	private Color titleColor;
	private Boolean showLegend;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AdhocFont getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(AdhocFont titleFont) {
		this.titleFont = titleFont;
	}

	public Color getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(Color titleColor) {
		this.titleColor = titleColor;
	}

	public Boolean getShowLegend() {
		return showLegend;
	}

	public void setShowLegend(Boolean showLegend) {
		this.showLegend = showLegend;
	}

}
