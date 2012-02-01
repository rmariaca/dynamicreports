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

package net.sf.dynamicreports.test.jasper.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.util.List;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.type.ModeEnum;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ReportBackgroundTest extends AbstractJasperPositionTest {

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
			.background(cmp.text("text"))
			.setBackgroundColor(Color.LIGHT_GRAY);
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		elementPositionTest("background.textField1", 0, 10, 10, 822, 16);
		elementPositionTest("background.backgroundColor", 0, 10, 10, 822, 575);

		List<JRPrintElement> elements = getJasperPrint().getPages().get(0).getElements();
		Assert.assertEquals("background order", "background.backgroundColor", elements.get(0).getKey());
		Assert.assertEquals("background order", "background.textField1", elements.get(1).getKey());

		Assert.assertEquals("background color", Color.LIGHT_GRAY, elements.get(0).getBackcolor());
		Assert.assertEquals("background color", ModeEnum.OPAQUE, elements.get(0).getModeValue());
	}
}
