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

package net.sf.dynamicreports.report.builder.component;

import net.sf.dynamicreports.report.base.component.DRPageXofY;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.expression.SystemMessageExpression;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class PageXofYBuilder extends HyperLinkComponentBuilder<PageXofYBuilder, DRPageXofY> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	protected PageXofYBuilder() {
		super(new DRPageXofY());
	}

	public PageXofYBuilder setFormatExpression(String format) {
		getObject().setFormatExpression(Expressions.text(format));
		return this;
	}
	
	public PageXofYBuilder setFormatExpression(DRISimpleExpression<String> formatExpression) {
		getObject().setFormatExpression(formatExpression);
		return this;
	}
	
	public PageXofYBuilder setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		getObject().setHorizontalAlignment(horizontalAlignment);
		return this;
	}
	
	@Override
	protected void configure() {
		if (getObject().getFormatExpression() == null) {
			setFormatExpression(new SystemMessageExpression("page_x_of_y"));
		}
		super.configure();
	}
}
