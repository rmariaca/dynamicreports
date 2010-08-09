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

package net.sf.dynamicreports.report.builder.component;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;

import net.sf.dynamicreports.report.base.component.DRImage;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class ImageBuilder extends HyperLinkComponentBuilder<ImageBuilder, DRImage> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	protected ImageBuilder() {
		super(new DRImage());
	}
	
	public ImageBuilder setImage(DRISimpleExpression<?> imageExpression) {
		getObject().setImageExpression(imageExpression);
		return this;
	}
	
	public ImageBuilder setImage(String imagePath) {
		getObject().setImageExpression(Expressions.text(imagePath));
		return this;
	}
	
	public ImageBuilder setImage(Image image) {
		getObject().setImageExpression(Expressions.image(image));
		return this;
	}
	
	public ImageBuilder setImage(InputStream imageInputStream) {
		getObject().setImageExpression(Expressions.inputStream(imageInputStream));
		return this;
	}

	public ImageBuilder setImage(URL imageUrl) {
		getObject().setImageExpression(Expressions.value(imageUrl));
		return this;
	}
	
	public ImageBuilder setImageScale(ImageScale imageScale) {
		getObject().setImageScale(imageScale);
		return this;
	}
}
