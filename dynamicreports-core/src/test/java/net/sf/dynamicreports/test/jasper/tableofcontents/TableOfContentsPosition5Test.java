/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
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

package net.sf.dynamicreports.test.jasper.tableofcontents;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class TableOfContentsPosition5Test extends AbstractJasperPositionTest {
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.tableOfContents()
		.title(
  		cmp.text("text").setTableOfContentsHeading("title1"),
  		cmp.text("text").setTableOfContentsHeading(tableOfContentsHeading().setLabel("title2")));//.setLevel(1)));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(2);

		elementPositionTest("title.textField1", 0, 10, 10, 575, 19);

		/*elementPositionTest("detail.list3", 0, 10, 49, 575, 16);
		elementPositionTest("detail.textField1", 0, 0, 0, 281, 16);
		elementPositionTest("detail.textField2", 0, 281, 0, 281, 16);
		elementPositionTest("detail.textField3", 0, 562, 0, 13, 16);

		elementPositionTest("detail.list4", 0, 0, 0, 575, 16);
		elementPositionTest("detail.textField4", 0, 10, 0, 271, 16);
		elementPositionTest("detail.textField5", 0, 291, 0, 271, 16);
		elementPositionTest("detail.textField6", 0, 562, 0, 13, 16);*/

		elementPositionTest("detail.list3", 0, 0, 0, 575, 16);
		elementPositionTest("detail.textField1", 0, 0, 0, 281, 16);
		elementPositionTest("detail.textField2", 0, 281, 0, 281, 16);
		elementPositionTest("detail.textField3", 0, 562, 0, 13, 16);

		elementPositionTest("detail.list3", 1, 0, 0, 575, 16);
		elementPositionTest("detail.textField1", 1, 0, 0, 281, 16);
		elementPositionTest("detail.textField2", 1, 281, 0, 281, 16);
		elementPositionTest("detail.textField3", 1, 562, 0, 13, 16);
	}
}
