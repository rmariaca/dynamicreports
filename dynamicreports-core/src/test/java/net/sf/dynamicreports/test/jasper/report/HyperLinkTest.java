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
import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.constant.HyperLinkType;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class HyperLinkTest extends AbstractJasperValueTest {

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		HyperLinkBuilder hyperLink = hyperLink()
			.setReference("reference")
			.setTooltip("tooltip")
			.setAnchor("anchor")
			.setAnchorName("anchorName")
			.setPage(1)
			.setType(HyperLinkType.NONE);

		rb.title(
			cmp.text("title 1").setHyperLink(hyperLink));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		JRPrintText textField = (JRPrintText) getElementAt("title.textField1", 0);
		Assert.assertEquals("hyperlink reference", "reference", textField.getHyperlinkReference());
		Assert.assertEquals("hyperlink tooltip", "tooltip", textField.getHyperlinkTooltip());
		Assert.assertEquals("hyperlink anchor", "anchor", textField.getHyperlinkAnchor());
		Assert.assertEquals("hyperlink anchorName", "anchorName", textField.getAnchorName());
		Assert.assertEquals("hyperlink page", new Integer(1), textField.getHyperlinkPage());
		Assert.assertEquals("hyperlink type reference", HyperlinkTypeEnum.NONE, textField.getHyperlinkTypeValue());
	}
}
