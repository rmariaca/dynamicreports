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

package net.sf.dynamicreports.examples.complex.dynamicreport;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.Map;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.examples.complex.ReportDesign;
import net.sf.dynamicreports.examples.complex.dynamicreport.DynamicReportData.DynamicReport;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DynamicReportDesign implements ReportDesign<DynamicReportData> {

	public void configureReport(ReportBuilder<?> rb, DynamicReportData data) {
		DynamicReport report = data.getUserReport();
		Map<String, TextColumnBuilder> columns = report.getColumns();

		for (TextColumnBuilder<?> column : columns.values()) {
			rb.columns(column);
		}

		for (String group : report.getGroups()) {
			ColumnGroupBuilder group2 = grp.group(columns.get(group));
			rb.groupBy(group2);

			for (String subtotal : report.getSubtotals()) {
				rb.subtotalsAtGroupFooter(group2, sbt.sum(columns.get(subtotal)));
			}
		}

		if (!report.getSubtotals().isEmpty()) {
			rb.subtotalsAtSummary(sbt.text("Total", (columns.get("item"))));
		}
		for (String subtotal : report.getSubtotals()) {
			rb.subtotalsAtSummary(sbt.sum(columns.get(subtotal)));
		}

		rb.setTemplate(Templates.reportTemplate)
		  .pageFooter(Templates.footerComponent);
	}
}
