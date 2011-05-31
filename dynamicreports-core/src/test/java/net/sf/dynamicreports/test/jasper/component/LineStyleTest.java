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

import java.awt.Color;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRPrintLine;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.type.LineStyleEnum;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class LineStyleTest extends AbstractJasperStyleTest {

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.title(
			cmp.line().setStyle(stl.style().setLinePen(stl.penDotted())),
			cmp.filler().setFixedHeight(10),
			cmp.line().setPen(stl.pen2Point()));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		JRPrintLine line = (JRPrintLine) getElementAt("title.line1", 0);
		JRStyle style = line.getStyle();
		JRPen pen = style.getLinePen();
		Assert.assertEquals(new Float(1), pen.getLineWidth());
		Assert.assertEquals(null, pen.getLineColor());
		Assert.assertEquals(LineStyleEnum.DOTTED, pen.getLineStyleValue());

		line = (JRPrintLine) getElementAt("title.line2", 0);
		pen = line.getLinePen();
		Assert.assertEquals(new Float(2), pen.getLineWidth());
		Assert.assertEquals(Color.BLACK, pen.getLineColor());
		Assert.assertEquals(LineStyleEnum.SOLID, pen.getLineStyleValue());
	}
}
