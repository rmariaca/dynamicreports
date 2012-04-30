package net.sf.dynamicreports.adhoc.configuration;

public class AdhocColumn {
	private String name;
	private String label;
	private Integer width;
	private AdhocStyle style;
	private AdhocStyle titleStyle;

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

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public AdhocStyle getStyle() {
		return style;
	}

	public void setStyle(AdhocStyle style) {
		this.style = style;
	}

	public AdhocStyle getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(AdhocStyle titleStyle) {
		this.titleStyle = titleStyle;
	}

}
