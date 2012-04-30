package net.sf.dynamicreports.adhoc.configuration;

public class AdhocSubtotal {
	private String name;
	private String label;
	private AdhocCalculation calculation;
	private AdhocStyle style;
	private AdhocStyle labelStyle;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public AdhocCalculation getCalculation() {
		return calculation;
	}

	public void setCalculation(AdhocCalculation calculation) {
		this.calculation = calculation;
	}

	public AdhocStyle getStyle() {
		return style;
	}

	public void setStyle(AdhocStyle style) {
		this.style = style;
	}

	public AdhocStyle getLabelStyle() {
		return labelStyle;
	}

	public void setLabelStyle(AdhocStyle labelStyle) {
		this.labelStyle = labelStyle;
	}

}
