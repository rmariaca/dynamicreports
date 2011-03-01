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

package net.sf.dynamicreports.test.jasper.crosstab;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.io.Serializable;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabMeasureVariableCellBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.test.jasper.AbstractJasperCrosstabStyleTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.fill.JRTemplatePrintFrame;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class CrosstabStyle3Test extends AbstractJasperCrosstabStyleTest implements Serializable {
	private static final long serialVersionUID = 1L;

	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabMeasureVariableCellBuilder<Integer> measure1;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		FieldBuilder<String> field1 = field("field1", String.class);
		FieldBuilder<String> field2 = field("field2", String.class);

		rowGroup = ctab.rowGroup(field1).setShowTotal(false);
		columnGroup = ctab.columnGroup(field2).setShowTotal(false);

		measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);

		CrosstabBuilder crosstab = ctab.crosstab()
			.highlightEvenRows()
			.highlightOddRows()
			.rowGroups(
				rowGroup)
			.columnGroups(
				columnGroup)
			.measures(
				measure1);

		rb.summary(crosstab);
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		setCrosstabBand("summary");

		Color color1 = new Color(240, 240, 240);
		Color color2 = new Color(200, 200, 200);

		JRPrintPage page = (JRPrintPage) getJasperPrint().getPages().get(0);

		JRTemplatePrintFrame element = (JRTemplatePrintFrame) page.getElements().get(3);
		styleTest(element.getStyle(), null, color2, "SansSerif", null, null, null);

		element = (JRTemplatePrintFrame) page.getElements().get(5);
		styleTest(element.getStyle(), null, color1, "SansSerif", null, null, null);

		element = (JRTemplatePrintFrame) page.getElements().get(7);
		styleTest(element.getStyle(), null, color2, "SansSerif", null, null, null);
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2", "field3");
		dataSource.add("a", "c", 1);
		dataSource.add("b", "c", 1);
		dataSource.add("c", "c", 1);
		return dataSource;
	}
}
