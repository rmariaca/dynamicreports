package net.sf.dynamicreports.adhoc.configuration;

public class AdhocPage {
	private Integer width;
	private Integer height;
	private AdhocPageOrientation pageOrientation;
	private Integer topMargin;
	private Integer bottomMargin;
	private Integer leftMargin;
	private Integer rightMargin;
	private Boolean ignorePageWidth;

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public AdhocPageOrientation getPageOrientation() {
		return pageOrientation;
	}

	public void setPageOrientation(AdhocPageOrientation pageOrientation) {
		this.pageOrientation = pageOrientation;
	}

	public Integer getTopMargin() {
		return topMargin;
	}

	public void setTopMargin(Integer topMargin) {
		this.topMargin = topMargin;
	}

	public Integer getBottomMargin() {
		return bottomMargin;
	}

	public void setBottomMargin(Integer bottomMargin) {
		this.bottomMargin = bottomMargin;
	}

	public Integer getLeftMargin() {
		return leftMargin;
	}

	public void setLeftMargin(Integer leftMargin) {
		this.leftMargin = leftMargin;
	}

	public Integer getRightMargin() {
		return rightMargin;
	}

	public void setRightMargin(Integer rightMargin) {
		this.rightMargin = rightMargin;
	}

	public Boolean getIgnorePageWidth() {
		return ignorePageWidth;
	}

	public void setIgnorePageWidth(Boolean ignorePageWidth) {
		this.ignorePageWidth = ignorePageWidth;
	}

}
