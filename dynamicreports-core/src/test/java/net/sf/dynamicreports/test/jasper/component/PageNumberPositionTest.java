/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
 * http://www.dynamicreports.org
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

package net.sf.dynamicreports.test.jasper.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.PageXofYBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class PageNumberPositionTest extends AbstractJasperPositionTest {

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		StyleBuilder style = stl.style().setHorizontalAlignment(HorizontalAlignment.LEFT);
		PageXofYBuilder pageXofYLeft = cmp.pageXofY().setStyle(stl.style(style));
		PageXofYBuilder pageXofYRight = cmp.pageXofY().setStyle(stl.style(style).setHorizontalAlignment(HorizontalAlignment.RIGHT));

		rb.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
		  .pageFooter(
					cmp.pageXofY(),
					cmp.pageXofY().setHorizontalAlignment(HorizontalAlignment.LEFT),
					cmp.pageXofY().setHorizontalAlignment(HorizontalAlignment.RIGHT),
					cmp.pageXofY().setHorizontalAlignment(HorizontalAlignment.JUSTIFIED),
					cmp.pageXofY().setHorizontalAlignment(HorizontalAlignment.CENTER),
					cmp.horizontalList(pageXofYLeft, pageXofYRight, pageXofYLeft, pageXofYRight));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);
		elementPositionTest("pageFooter.textField1", 0, 0, 0, 287, 16);
		elementPositionTest("pageFooter.textField2", 0, 287, 0, 288, 16);

		elementPositionTest("pageFooter.textField3", 0, 0, 0, 40, 16);
		elementPositionTest("pageFooter.textField4", 0, 40, 0, 535, 16);

		elementPositionTest("pageFooter.textField5", 0, 0, 0, 517, 16);
		elementPositionTest("pageFooter.textField6", 0, 517, 0, 58, 16);

		elementPositionTest("pageFooter.textField7", 0, 0, 0, 287, 16);
		elementPositionTest("pageFooter.textField8", 0, 287, 0, 288, 16);

		elementPositionTest("pageFooter.textField9", 0, 0, 0, 287, 16);
		elementPositionTest("pageFooter.textField10", 0, 287, 0, 288, 16);

		elementPositionTest("pageFooter.textField11", 0, 0, 0, 36, 12);
		elementPositionTest("pageFooter.textField12", 0, 36, 0, 107, 12);

		elementPositionTest("pageFooter.textField13", 0, 0, 0, 90, 12);
		elementPositionTest("pageFooter.textField14", 0, 90, 0, 54, 12);

		elementPositionTest("pageFooter.textField15", 0, 0, 0, 36, 12);
		elementPositionTest("pageFooter.textField16", 0, 36, 0, 108, 12);

		elementPositionTest("pageFooter.textField17", 0, 0, 0, 90, 12);
		elementPositionTest("pageFooter.textField18", 0, 90, 0, 54, 12);
	}
}
