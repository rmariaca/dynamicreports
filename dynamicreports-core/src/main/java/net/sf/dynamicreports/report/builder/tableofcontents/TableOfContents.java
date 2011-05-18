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
import net.sf.dynamicreports.report.definition.DRITableOfContents;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class TableOfContents implements DRITableOfContents {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private ReportBuilder<?> report;
	private int headings;
	private int levels;

	private FieldBuilder<Integer> levelField;
	private FieldBuilder<String> textField;
	private FieldBuilder<String> referenceField;
	private FieldBuilder<Integer> pageIndexField;

	private HyperLinkBuilder referenceHyperLink;
	private StyleBuilder titleStyle;
	private int pageIndexDigits;
	private String dots;

	public TableOfContents() {
		init();
	}

	protected void init() {
		StringBuilder dots = new StringBuilder(100);
		for (int i = 0; i < dots.capacity(); i++) {
			dots.append(".");
		}
		this.dots = dots.toString();

		levelField = field("level", type.integerType());
		textField = field("text", type.stringType());
		referenceField = field("reference", type.stringType());
		pageIndexField = field("pageIndex", type.integerType());

		referenceHyperLink = hyperLink();
		referenceHyperLink.setAnchor(new ReferenceExpression());
		referenceHyperLink.setType(HyperLinkType.LOCAL_ANCHOR);

		titleStyle = stl.style()
			.bold()
			.setFontSize(16)
			.setHorizontalAlignment(HorizontalAlignment.CENTER);
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

	public void configure() {
		pageIndexDigits = String.valueOf(headings).length();

		VerticalListBuilder detailComponent = cmp.verticalList();
		for (int i = 0; i < levels; i++) {
			ComponentBuilder<?, ?> headingComponent = headingComponent(i);
			headingComponent.setPrintWhenExpression(new PrintHeadingExpression(i));
			headingComponent.removeLineWhenBlank();
			detailComponent.add(headingComponent);
		}

		report
			.title(title())
			.fields(
				levelField, textField, referenceField, pageIndexField)
			.detail(detailComponent);
	}

	protected ComponentBuilder<?, ?> title() {
		VerticalListBuilder title = cmp.verticalList();
		title.add(
			cmp.text(new SystemMessageExpression("table_of_contents")).setStyle(titleStyle),
			cmp.filler().setFixedHeight(20));
		return title;
	}

	protected ComponentBuilder<?, ?> headingComponent(int level) {
		HorizontalListBuilder headingComponent = cmp.horizontalList();

		if (level > 0) {
			headingComponent.add(cmp.filler().setFixedWidth(level * 10));
		}

		TextFieldBuilder<String> labelColumn = cmp.text(textField)
  		.setWidth(50)
  		.setHyperLink(referenceHyperLink);
		headingComponent.add(labelColumn);

		if (level > 0) {
			headingComponent.add(cmp.filler().setFixedWidth(level * 10));
		}

		TextFieldBuilder<String> dotsColumn = cmp.text(dots.toString())
  		.setWidth(50)
  		.setStretchWithOverflow(false)
  		.setHyperLink(referenceHyperLink);
		headingComponent.add(dotsColumn);

		TextFieldBuilder<Integer> pageIndexColumn = cmp.text(pageIndexField)
  		.setFixedColumns(pageIndexDigits)
  		.setHyperLink(referenceHyperLink);
		headingComponent.add(pageIndexColumn);

		return headingComponent;
	}

	private class ReferenceExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		public String evaluate(ReportParameters reportParameters) {
			return reportParameters.getValue(referenceField);
		}
	}

	private class PrintHeadingExpression extends AbstractSimpleExpression<Boolean> {
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
