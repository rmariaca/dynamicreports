package net.sf.dynamicreports.adhoc.configuration;

import java.awt.Color;

public class AdhocStyle {
	private AdhocFont font;
	private AdhocPen topBorder;
	private AdhocPen leftBorder;
	private AdhocPen bottomBorder;
	private AdhocPen rightBorder;
	private Color foregroundColor;
	private Color backgroundColor;
	private AdhocHorizontalAlignment horizontalAlignment;
	private AdhocVerticalAlignment verticalAlignment;
	private String pattern;

	public AdhocFont getFont() {
		return font;
	}

	public void setFont(AdhocFont font) {
		this.font = font;
	}

	public AdhocPen getTopBorder() {
		return topBorder;
	}

	public void setTopBorder(AdhocPen topBorder) {
		this.topBorder = topBorder;
	}

	public AdhocPen getLeftBorder() {
		return leftBorder;
	}

	public void setLeftBorder(AdhocPen leftBorder) {
		this.leftBorder = leftBorder;
	}

	public AdhocPen getBottomBorder() {
		return bottomBorder;
	}

	public void setBottomBorder(AdhocPen bottomBorder) {
		this.bottomBorder = bottomBorder;
	}

	public AdhocPen getRightBorder() {
		return rightBorder;
	}

	public void setRightBorder(AdhocPen rightBorder) {
		this.rightBorder = rightBorder;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public AdhocHorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(AdhocHorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public AdhocVerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(AdhocVerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
