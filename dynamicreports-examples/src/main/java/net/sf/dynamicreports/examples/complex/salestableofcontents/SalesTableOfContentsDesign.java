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

package net.sf.dynamicreports.examples.complex.salestableofcontents;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.util.List;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.examples.complex.ReportDesign;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.VariableBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizer;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Evaluation;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SalesTableOfContentsDesign implements ReportDesign<SalesTableOfContentsData> {

	public void configureReport(ReportBuilder<?> rb, SalesTableOfContentsData invoiceData) {
		TextColumnBuilder<String> countryColumn = col.column("Country", "country", type.stringType());
		TextColumnBuilder<String> itemColumn    = col.column("Item",    "item",    type.stringType());

		StyleBuilder headingToc1Style = stl.style(Templates.rootStyle)
			.italic();

		CustomTableOfContentsCustomizer tableOfContentsCustomizer = new CustomTableOfContentsCustomizer();
		tableOfContentsCustomizer.setHeadingStyle(1, headingToc1Style);
		tableOfContentsCustomizer.setTextFixedWidth(100);
		tableOfContentsCustomizer.setPageIndexFixedWidth(30);

		rb.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
			.setTemplate(Templates.reportTemplate)
			.setTableOfContents(tableOfContentsCustomizer)
			.columns(
			 	countryColumn,
			 	itemColumn,
			 	col.column("Order date", "orderdate", type.dateType()),
			 	col.column("Quantity",   "quantity",  type.integerType()),
			 	col.column("Unit price", "unitprice", type.bigDecimalType()))
			.groupBy(countryColumn, itemColumn)
			.title(
				Templates.createTitleComponent("SalesTableOfContents"))
			.pageFooter(
				Templates.footerComponent);
	}

	private class CustomTableOfContentsCustomizer extends TableOfContentsCustomizer {
		private static final long serialVersionUID = 1L;

		@Override
		public void customize() {
			super.customize();

			CustomGroupBuilder countryGroup = grp.group(new CountryGroupExpression())
				.setHeaderLayout(GroupHeaderLayout.EMPTY)
				.header(countryHeadingComponent())
				.footer(cmp.filler().setFixedHeight(5));

			report
				.setPageColumnsPerPage(2)
				.setPageColumnSpace(10)
				.groupBy(countryGroup);
		}

		private ComponentBuilder<?, ?> countryHeadingComponent() {
			HorizontalListBuilder headingComponent = cmp.horizontalList();

			StyleBuilder style = stl.style(Templates.rootStyle)
				.setFontSize(12)
				.bold()
				.setBackgroundColor(Color.LIGHT_GRAY);

			TextFieldBuilder<String> textComponent = cmp.text(textField)
  			.setHyperLink(referenceHyperLink)
  			.setStyle(style);
			headingComponent.add(textComponent);

			TextFieldBuilder<String> pageIndexComponent = cmp.text(new CountryHeadingExpression())
				.setHyperLink(referenceHyperLink)
				.setStyle(style)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			headingComponent.add(pageIndexComponent);

			return headingComponent;
		}

		@Override
		protected ComponentBuilder<?, ?> headingComponent(int level) {
			if (level == 0) {
				return cmp.filler();
			}

			ComponentBuilder<?, ?> headingComponent = super.headingComponent(level);

			ConditionalStyleBuilder conditionalStyle = stl.conditionalStyle(exp.printInOddRow())
				.setBackgroundColor(new Color(245, 245, 245));
			StyleBuilder rowStyle = stl.style()
				.conditionalStyles(conditionalStyle);
			headingComponent.setStyle(rowStyle);

			return headingComponent;
		}

		private class CountryGroupExpression extends AbstractSimpleExpression<String> {
			private static final long serialVersionUID = 1L;

			private String country;

			public String evaluate(ReportParameters reportParameters) {
				int level = reportParameters.getValue(levelField);
				if (level == 0) {
					country = reportParameters.getValue(textField);
				}
				return country;
			}
		}

		private class CountryHeadingExpression extends AbstractComplexExpression<String> {
			private static final long serialVersionUID = 1L;

			private CountryHeadingExpression() {
				VariableBuilder<Integer> minCountryPage = variable(pageIndexField, Calculation.LOWEST)
					.setResetType(Evaluation.FIRST_GROUP);
				addExpression(minCountryPage);

				VariableBuilder<Integer> maxCountryPage = variable(pageIndexField, Calculation.HIGHEST)
					.setResetType(Evaluation.FIRST_GROUP);
				addExpression(maxCountryPage);
			}

			@Override
			public String evaluate(List<?> values, ReportParameters reportParameters) {
				return values.get(0) + " - " + values.get(1);
			}
		}
	}
}