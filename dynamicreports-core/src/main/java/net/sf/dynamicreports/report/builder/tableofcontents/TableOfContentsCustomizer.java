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

package net.sf.dynamicreports.report.builder.tableofcontents;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.HashMap;
import java.util.Map;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.expression.SystemMessageExpression;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HyperLinkType;
import net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class TableOfContentsCustomizer implements DRITableOfContentsCustomizer {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected static String dots;

	protected ReportBuilder<?> report;
	protected int headings;
	protected int levels;

	protected FieldBuilder<Integer> levelField;
	protected FieldBuilder<String> textField;
	protected FieldBuilder<String> referenceField;
	protected FieldBuilder<Integer> pageIndexField;

	protected HyperLinkBuilder referenceHyperLink;
	protected int pageIndexDigits;

	protected StyleBuilder titleStyle;
	protected StyleBuilder headingStyle;
	protected Map<Integer, StyleBuilder> headingStyles;
	protected Integer textFixedWidth;
	protected Integer dotsFixedWidth;
	protected Integer pageIndexFixedWidth;

	static {
		StringBuilder dots = new StringBuilder(200);
		for (int i = 0; i < dots.capacity(); i++) {
			dots.append(".");
		}
		TableOfContentsCustomizer.dots = dots.toString();
	}

	public TableOfContentsCustomizer() {
		headingStyles = new HashMap<Integer, StyleBuilder>();
	}

	protected void init() {
		levelField = field("level", type.integerType());
		textField = field("text", type.stringType());
		referenceField = field("reference", type.stringType());
		pageIndexField = field("pageIndex", type.integerType());

		referenceHyperLink = hyperLink();
		referenceHyperLink.setAnchor(new ReferenceExpression());
		referenceHyperLink.setType(HyperLinkType.LOCAL_ANCHOR);

		pageIndexDigits = String.valueOf(headings).length();

		if (titleStyle == null) {
			titleStyle = stl.style()
				.bold()
				.setFontSize(16)
				.setHorizontalAlignment(HorizontalAlignment.CENTER);
		}
	}

	public void setReport(ReportBuilder<?> report) {
		this.report = report;
	}

	public void setHeadings(int headings) {
		this.headings = headings;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public void customize() {
		init();

		VerticalListBuilder detailComponent = cmp.verticalList();
		for (int i = 0; i < levels; i++) {
			ComponentBuilder<?, ?> headingComponent = headingComponent(i);
			headingComponent.setPrintWhenExpression(new PrintHeadingExpression(i));
			headingComponent.removeLineWhenBlank();
			detailComponent.add(headingComponent);
		}

		report
			.title(
				title(),
				cmp.filler().setFixedHeight(20))
			.fields(
				levelField, textField, referenceField, pageIndexField)
			.detail(detailComponent);
	}

	protected ComponentBuilder<?, ?> title() {
		return cmp.text(new SystemMessageExpression("table_of_contents")).setStyle(titleStyle);
	}

	protected ComponentBuilder<?, ?> headingComponent(int level) {
		HorizontalListBuilder headingComponent = cmp.horizontalList();

		if (level > 0) {
			headingComponent.add(cmp.filler().setFixedWidth(level * 10));
		}

		TextFieldBuilder<String> textComponent = cmp.text(textField)
  		.setHyperLink(referenceHyperLink);
		if (textFixedWidth != null) {
			textComponent.setFixedWidth(textFixedWidth);
		}
		headingComponent.add(textComponent);

		if (level > 0) {
			headingComponent.add(cmp.filler().setFixedWidth(level * 10));
		}

		TextFieldBuilder<String> dotsComponent = cmp.text(dots.toString())
  		.setStretchWithOverflow(false)
  		.setHyperLink(referenceHyperLink);
		if (dotsFixedWidth != null) {
			dotsComponent.setFixedWidth(dotsFixedWidth);
		}
		headingComponent.add(dotsComponent);

		TextFieldBuilder<Integer> pageIndexComponent = cmp.text(pageIndexField)
  		.setHyperLink(referenceHyperLink);
		if (pageIndexFixedWidth != null) {
			pageIndexComponent.setFixedWidth(pageIndexFixedWidth);
		}
		else {
			pageIndexComponent.setFixedColumns(pageIndexDigits);
		}
		headingComponent.add(pageIndexComponent);

		StyleBuilder headingStyle = headingStyles.get(level);
		if (headingStyle == null) {
			headingStyle = this.headingStyle;
		}
		if (headingStyle != null) {
			textComponent.setStyle(headingStyle);
			dotsComponent.setStyle(headingStyle);
			pageIndexComponent.setStyle(headingStyle);
		}

		return headingComponent;
	}

	public void setTitleStyle(StyleBuilder titleStyle) {
		this.titleStyle = titleStyle;
	}

	public void setHeadingStyle(StyleBuilder headingStyle) {
		this.headingStyle = headingStyle;
	}

	public void setHeadingStyle(int level, StyleBuilder headingStyle) {
		this.headingStyles.put(level, headingStyle);
	}

	public void setTextFixedWidth(Integer textFixedWidth) {
		this.textFixedWidth = textFixedWidth;
	}

	public void setDotsFixedWidth(Integer dotsFixedWidth) {
		this.dotsFixedWidth = dotsFixedWidth;
	}

	public void setPageIndexFixedWidth(Integer pageIndexFixedWidth) {
		this.pageIndexFixedWidth = pageIndexFixedWidth;
	}

	protected class ReferenceExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		public String evaluate(ReportParameters reportParameters) {
			return reportParameters.getValue(referenceField);
		}
	}

	protected class PrintHeadingExpression extends AbstractSimpleExpression<Boolean> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		private int level;

		private PrintHeadingExpression(int level) {
			this.level = level;
		}

		public Boolean evaluate(ReportParameters reportParameters) {
			return reportParameters.getValue(levelField) == level;
		}
	}
}
