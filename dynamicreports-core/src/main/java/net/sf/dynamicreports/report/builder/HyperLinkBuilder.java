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

package net.sf.dynamicreports.report.builder;

import net.sf.dynamicreports.report.base.DRHyperLink;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HyperLinkType;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class HyperLinkBuilder extends AbstractBuilder<HyperLinkBuilder, DRHyperLink> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected HyperLinkBuilder() {
		super(new DRHyperLink());
	}

	protected HyperLinkBuilder(String link) {
		super(new DRHyperLink());
		setLink(link);
	}

	protected HyperLinkBuilder(DRISimpleExpression<String> linkExpression) {
		super(new DRHyperLink());
		setLink(linkExpression);
	}

	public HyperLinkBuilder setLink(String link) {
		Validate.notNull(link, "link must not be null");
		return setLink(Expressions.text(link));
	}

	public HyperLinkBuilder setLink(DRISimpleExpression<String> linkExpression) {
		Validate.notNull(linkExpression, "linkExpression must not be null");
		getObject().setReferenceExpression(linkExpression);
		getObject().setType(HyperLinkType.REFERENCE);
		return this;
	}

	public HyperLinkBuilder setAnchorName(String anchorName) {
		getObject().setAnchorNameExpression(Expressions.text(anchorName));
		return this;
	}

	public HyperLinkBuilder setAnchorName(DRISimpleExpression<String> anchorNameExpression) {
		getObject().setAnchorNameExpression(anchorNameExpression);
		return this;
	}

	public HyperLinkBuilder setAnchor(String anchor) {
		getObject().setAnchorExpression(Expressions.text(anchor));
		return this;
	}

	public HyperLinkBuilder setAnchor(DRISimpleExpression<String> anchorExpression) {
		getObject().setAnchorExpression(anchorExpression);
		return this;
	}

	public HyperLinkBuilder setPage(String page) {
		getObject().setPageExpression(Expressions.text(page));
		return this;
	}

	public HyperLinkBuilder setPage(DRISimpleExpression<String> pageExpression) {
		getObject().setPageExpression(pageExpression);
		return this;
	}

	public HyperLinkBuilder setReference(String reference) {
		getObject().setReferenceExpression(Expressions.text(reference));
		return this;
	}

	public HyperLinkBuilder setReference(DRISimpleExpression<String> referenceExpression) {
		getObject().setReferenceExpression(referenceExpression);
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

	public HyperLinkBuilder setType(HyperLinkType hyperLinkType) {
		getObject().setType(hyperLinkType);
		return this;
	}

	public DRHyperLink getHyperLink() {
		return build();
	}
}
