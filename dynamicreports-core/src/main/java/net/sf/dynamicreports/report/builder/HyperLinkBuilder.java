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

package net.sf.dynamicreports.report.builder;

import net.sf.dynamicreports.report.base.DRHyperLink;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class HyperLinkBuilder extends AbstractBuilder<HyperLinkBuilder, DRHyperLink> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected HyperLinkBuilder(String link) {
		super(new DRHyperLink());
		setLink(link);
	}
	
	protected HyperLinkBuilder(DRISimpleExpression<String> linkExpression) {
		super(new DRHyperLink());
		setLink(linkExpression);
	}
	
	public HyperLinkBuilder setLink(String link) {
		getObject().setLinkExpression(Expressions.text(link));
		return this;
	}
	
	public HyperLinkBuilder setLink(DRISimpleExpression<String> linkExpression) {
		getObject().setLinkExpression(linkExpression);
		return this;
	}

	public HyperLinkBuilder setTooltip(String tooltip) {
		getObject().setTooltipExpression(Expressions.text(tooltip));
		return this;
	}
	
	public HyperLinkBuilder setTooltip(DRISimpleExpression<String> tooltipExpression) {
		getObject().setTooltipExpression(tooltipExpression);
		return this;
	}
	
	public DRHyperLink getHyperLink() {
		return build();
	}
}
