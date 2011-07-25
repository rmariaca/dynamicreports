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

package net.sf.dynamicreports.test.jasper.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.LineDirection;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRPrintLine;
import net.sf.jasperreports.engine.JRPrintRectangle;
import net.sf.jasperreports.engine.type.LineDirectionEnum;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Component2Test extends AbstractJasperPositionTest {

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.title(
			  cmp.horizontalList(
			  		cmp.line(), cmp.filler().setFixedWidth(10), cmp.line()),
			  cmp.horizontalList(
			  		cmp.line().setFixedDimension(1, 50), cmp.line().setDirection(LineDirection.TOP_DOWN), cmp.line().setDirection(LineDirection.BOTTOM_UP)),
			  cmp.ellipse(),
			  cmp.rectangle(),
			  cmp.roundRectangle(),
			  cmp.roundRectangle(20));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		elementPositionTest("title.line1", 0, 0, 0, 282, 1);
		elementPositionTest("title.line2", 0, 292, 0, 283, 1);
		elementPositionTest("title.line3", 0, 0, 0, 1, 50);
		elementPositionTest("title.line4", 0, 1, 0, 287, 50);
		elementPositionTest("title.line5", 0, 288, 0, 287, 50);

		JRPrintLine line = (JRPrintLine) getElementAt("title.line4", 0);
		Assert.assertEquals("Line direction", LineDirectionEnum.TOP_DOWN, line.getDirectionValue());
		line = (JRPrintLine) getElementAt("title.line5", 0);
		Assert.assertEquals("Line direction", LineDirectionEnum.BOTTOM_UP, line.getDirectionValue());

		elementPositionTest("title.ellipse1", 0, 10, 61, 575, 100);
		elementPositionTest("title.rectangle1", 0, 10, 161, 575, 100);
		elementPositionTest("title.rectangle2", 0, 10, 261, 575, 100);
		elementPositionTest("title.rectangle3", 0, 10, 361, 575, 100);

		JRPrintRectangle rectangle = (JRPrintRectangle) getElementAt("title.rectangle1", 0);
		Assert.assertEquals("Rectangle radius", 0, rectangle.getRadius());
		rectangle = (JRPrintRectangle) getElementAt("title.rectangle2", 0);
		Assert.assertEquals("Rectangle radius", 10, rectangle.getRadius());
		rectangle = (JRPrintRectangle) getElementAt("title.rectangle3", 0);
		Assert.assertEquals("Rectangle radius", 20, rectangle.getRadius());
	}
}
