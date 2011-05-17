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

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
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

	private FieldBuilder<String> referenceField;
	private StyleBuilder titleStyle;
	private ReportBuilder<?> report;
	private int headings;
	private int levels;

	public TableOfContents() {
		init();
	}

	protected void init() {
		referenceField = field("reference", type.stringType());

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
		List<ColumnBuilder<?, ?>> columns = columns();

		report
			.title(title())
			.fields(referenceField)
			.columns(columns.toArray(new ColumnBuilder<?, ?>[columns.size()]));
	}

	protected ComponentBuilder<?, ?> title() {
		VerticalListBuilder title = cmp.verticalList();
		title.add(
			cmp.text("Table of contents").setStyle(titleStyle),
			cmp.filler().setFixedHeight(20));
		return title;
	}

	protected List<ColumnBuilder<?, ?>> columns() {
		List<ColumnBuilder<?, ?>> columns = new ArrayList<ColumnBuilder<?, ?>>();

		int digits = String.valueOf(headings).length();
		StringBuilder dots = new StringBuilder(100);
		for (int i = 0; i < dots.capacity(); i++) {
			dots.append(".");
		}

		HyperLinkBuilder hyperLink = hyperLink();
		hyperLink.setAnchor(new HyperLinkExpression());
		hyperLink.setType(HyperLinkType.LOCAL_ANCHOR);

		TextColumnBuilder<String> labelColumn = col.column("text", type.stringType())
	  	.setWidth(50)
	  	.setHyperLink(hyperLink);
		columns.add(labelColumn);

		TextColumnBuilder<String> dotsColumn = col.column(exp.text(dots.toString()))
	  	.setWidth(50)
	  	.setStretchWithOverflow(false)
	  	.setHyperLink(hyperLink);
		columns.add(dotsColumn);

		TextColumnBuilder<Integer> pageIndexColumn = col.column("pageIndex", type.integerType())
	  	.setFixedColumns(digits)
	  	.setHyperLink(hyperLink);
		columns.add(pageIndexColumn);

		return columns;
	}

	private class HyperLinkExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		public String evaluate(ReportParameters reportParameters) {
			return reportParameters.getValue(referenceField);
		}
	}
}
