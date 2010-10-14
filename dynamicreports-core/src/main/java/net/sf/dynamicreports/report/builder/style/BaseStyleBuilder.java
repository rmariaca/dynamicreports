/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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

package net.sf.dynamicreports.report.builder.style;

import java.awt.Color;

import net.sf.dynamicreports.report.base.style.DRBaseStyle;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalAlignment;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings({"unchecked", "ucd"})
public abstract class BaseStyleBuilder<T extends BaseStyleBuilder<T, U>, U extends DRBaseStyle> extends AbstractBuilder<T, U> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	protected BaseStyleBuilder(U baseStyle) {
		super(baseStyle);
	}
	
	public T setBackgroundColor(Color backgroundColor) {
		getObject().setBackgroundColor(backgroundColor);
		return (T) this;
	}
	
	public T setBorder(BorderBuilder border) {
		if (border != null) {
			getObject().setBorder(border.build());
		}
		else {
			getObject().setBorder(null);
		}		
		return (T) this;
	}

	public T setBorder(PenBuilder pen) {
		return setBorder(pen != null ? Styles.border(pen) : null);
	}
	
	public T setTopBorder(PenBuilder topPen) {
		if (topPen != null) {
			getObject().getBorder().setTopPen(topPen.build());
		}
		else {
			getObject().getBorder().setTopPen(null);
		}
		return (T) this;
	}

	public T setLeftBorder(PenBuilder leftPen) {
		if (leftPen != null) {
			getObject().getBorder().setLeftPen(leftPen.build());
		}
		else {
			getObject().getBorder().setLeftPen(null);
		}		
		return (T) this;
	}

	public T setBottomBorder(PenBuilder bottomPen) {
		if (bottomPen != null) {
			getObject().getBorder().setBottomPen(bottomPen.build());
		}
		else {
			getObject().getBorder().setBottomPen(null);
		}		
		return (T) this;
	}

	public T setRightBorder(PenBuilder rightPen) {
		if (rightPen != null) {
			getObject().getBorder().setRightPen(rightPen.build());
		}
		else {
			getObject().getBorder().setRightPen(null);
		}		
		return (T) this;
	}
	
	public T setFont(FontBuilder font) {
		if (font != null) {
			getObject().setFont(font.build());
		}
		else {
			getObject().setFont(null);
		}		
		return (T) this;
	}

	public T bold() {
		return setBold(true);
	}
	
	public T setBold(Boolean bold) {
		getObject().getFont().setBold(bold);
		return (T) this;
	}

	public T setFontName(String fontName) {
		getObject().getFont().setFontName(fontName);
		return (T) this;
	}

	public T setFontSize(Integer fontSize) {
		getObject().getFont().setFontSize(fontSize);
		return (T) this;
	}

	public T italic() {
		return setItalic(true);
	}
	
	public T setItalic(Boolean italic) {
		getObject().getFont().setItalic(italic);
		return (T) this;
	}

	public T boldItalic() {
		setBold(true);
		return setItalic(true);
	}
	
	@Deprecated
	/**
	 * You should configure the fonts.xml file 
	 */
	public T setPdfEmbedded(Boolean pdfEmbedded) {
		getObject().getFont().setPdfEmbedded(pdfEmbedded);
		return (T) this;
	}

	@Deprecated
	/**
	 * You should configure the fonts.xml file 
	 */
	public T setPdfEncoding(String pdfEncoding) {
		getObject().getFont().setPdfEncoding(pdfEncoding);
		return (T) this;
	}

	@Deprecated
	/**
	 * You should configure the fonts.xml file 
	 */
	public T setPdfFontName(String pdfFontName) {
		getObject().getFont().setPdfFontName(pdfFontName);
		return (T) this;
	}

	public T strikeThrough() {
		return setStrikeThrough(true);
	}
	
	public T setStrikeThrough(Boolean strikeThrough) {
		getObject().getFont().setStrikeThrough(strikeThrough);
		return (T) this;
	}

	public T underline() {
		return setUnderline(true);
	}
	
	public T setUnderline(Boolean underline) {
		getObject().getFont().setUnderline(underline);
		return (T) this;
	}
	
	public T setForegroudColor(Color foregroudColor) {
		getObject().setForegroundColor(foregroudColor);
		return (T) this;
	}

	public T setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		getObject().setHorizontalAlignment(horizontalAlignment);
		return (T) this;
	}

	public T setImageScale(ImageScale imageScale) {
		getObject().setImageScale(imageScale);
		return (T) this;
	}
	
	public T setPadding(PaddingBuilder padding) {
		if (padding != null) {
			getObject().setPadding(padding.build());
		}
		else {
			getObject().setPadding(null);
		}			
		return (T) this;
	}

	public T setPadding(Integer padding) {
		return setPadding(Styles.padding(padding));
	}
	
	public T setTopPadding(Integer top) {
		getObject().getPadding().setTop(top);
		return (T) this;
	}

	public T setLeftPadding(Integer left) {
		getObject().getPadding().setLeft(left);
		return (T) this;
	}

	public T setBottomPadding(Integer bottom) {
		getObject().getPadding().setBottom(bottom);
		return (T) this;
	}

	public T setRightPadding(Integer right) {
		getObject().getPadding().setRight(right);
		return (T) this;
	}
	
	public T setPattern(String pattern) {
		getObject().setPattern(pattern);
		return (T) this;
	}

	public T setRadius(Integer radius) {
		getObject().setRadius(radius);
		return (T) this;
	}

	public T setRotation(Rotation rotation) {
		getObject().setRotation(rotation);
		return (T) this;
	}

	public T setAlignment(HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
		getObject().setHorizontalAlignment(horizontalAlignment);
		getObject().setVerticalAlignment(verticalAlignment);		
		return (T) this;
	}
	
	public T setVerticalAlignment(VerticalAlignment verticalAlignment) {
		getObject().setVerticalAlignment(verticalAlignment);
		return (T) this;
	}
	
	public T setMarkup(Markup markup) {
		getObject().setMarkup(markup);
		return (T) this;
	}
	
	public U getStyle() {
		return build();
	}
}
