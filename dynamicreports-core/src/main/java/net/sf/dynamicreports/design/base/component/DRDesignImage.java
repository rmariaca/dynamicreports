/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
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

package net.sf.dynamicreports.design.base.component;

import net.sf.dynamicreports.design.definition.component.DRIDesignImage;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.constant.ImageScale;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignImage extends DRDesignHyperlinkComponent implements DRIDesignImage {
	private ImageScale imageScale;
	private DRIDesignExpression imageExpression;
	
	public DRDesignImage() {
		super("image");
	}
	
	public DRIDesignExpression getImageExpression() {
		return imageExpression;
	}
	
	public void setImageExpression(DRIDesignExpression imageExpression) {
		this.imageExpression = imageExpression;
	}

	public ImageScale getImageScale() {
		return imageScale;
	}
	
	public void setImageScale(ImageScale imageScale) {
		this.imageScale = imageScale;
	}
}
