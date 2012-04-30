package net.sf.dynamicreports.adhoc.configuration;

import java.awt.Color;

public class AdhocAxisFormat {
	private String label;
	private AdhocFont labelFont;
	private Color labelColor;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public AdhocFont getLabelFont() {
		return labelFont;
	}

	public void setLabelFont(AdhocFont labelFont) {
		this.labelFont = labelFont;
	}

	public Color getLabelColor() {
		return labelColor;
	}

	public void setLabelColor(Color labelColor) {
		this.labelColor = labelColor;
	}

}
