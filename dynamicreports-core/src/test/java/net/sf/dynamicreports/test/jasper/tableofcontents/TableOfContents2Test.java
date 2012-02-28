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
import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintText;

import org.apache.commons.lang.StringUtils;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class TableOfContents2Test extends AbstractJasperValueTest {
	private TextColumnBuilder<String> column1;
	private DRIExpression<String> labelExpression1;
	private DRIExpression<String> labelExpression2;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		labelExpression1 = exp.text("title");
		labelExpression2 = exp.text("summary");

		rb.tableOfContents()
			.title(
	  		cmp.text("text").setTableOfContentsHeading(tableOfContentsHeading().setLabel(labelExpression1)))
	  	.columns(
	  		column1 = col.column("Column1", "field1", type.stringType()))
	  	.summary(
	  		cmp.text("text").setTableOfContentsHeading(tableOfContentsHeading().setLabel(labelExpression2)));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(2);

		elementValueTest("title.textField1", "Table of contents");

		elementCountTest("detail.textField1", 2);

		String anchorName = labelExpression1.getName() + "_0";
		elementValueTest("detail.textField1", 0, "title");
		JRPrintText text = (JRPrintText) getElementAt("detail.textField1", 0);
		Assert.assertEquals("text anchor", anchorName, text.getHyperlinkAnchor());
		JRPrintText dots = (JRPrintText) getElementAt("detail.textField2", 0);
		Assert.assertTrue("dots", StringUtils.containsOnly(dots.getText(), "."));
		Assert.assertEquals("dots anchor", anchorName, dots.getHyperlinkAnchor());
		JRPrintText pageIndex = (JRPrintText) getElementAt("detail.textField3", 0);
		Assert.assertEquals("pageIndex anchor", anchorName, pageIndex.getHyperlinkAnchor());

		anchorName = labelExpression2.getName() + "_8";
		elementValueTest("detail.textField1", 1, "summary");
		text = (JRPrintText) getElementAt("detail.textField1", 1);
		Assert.assertEquals("text anchor", anchorName, text.getHyperlinkAnchor());
		dots = (JRPrintText) getElementAt("detail.textField2", 1);
		Assert.assertTrue("dots", StringUtils.containsOnly(dots.getText(), "."));
		Assert.assertEquals("dots anchor", anchorName, dots.getHyperlinkAnchor());
		pageIndex = (JRPrintText) getElementAt("detail.textField3", 1);
		Assert.assertEquals("pageIndex anchor", anchorName, pageIndex.getHyperlinkAnchor());

		elementValueTest("detail.textField3", "1", "1");

		String name1 = labelExpression1.getName() + ".tocReference";
		String name2 = "title.textField1";
		elementCountTest(name1, 1);
		anchorName = labelExpression1.getName() + "_0";
		elementValueTest(name1, 0, "");
		JRPrintText reference = (JRPrintText) getElementAt(name1, 0);
		Assert.assertEquals("reference anchorName " + name1, anchorName, reference.getAnchorName());
		elementValueTest(name2, 1, "text");
		reference = (JRPrintText) getElementAt(name2, 1);
		Assert.assertEquals("reference anchorName " + name2, anchorName, reference.getAnchorName());

		name1 = labelExpression2.getName() + ".tocReference";
		name2 = "summary.textField1";
		elementCountTest(name1, 1);
		anchorName = labelExpression2.getName() + "_8";
		elementValueTest(name1, 0, "");
		reference = (JRPrintText) getElementAt(name1, 0);
		Assert.assertEquals("reference anchorName " + name1, anchorName, reference.getAnchorName());
		elementValueTest(name2, 0, "text");
		reference = (JRPrintText) getElementAt(name2, 0);
		Assert.assertEquals("reference anchorName " + name2, anchorName, reference.getAnchorName());

		columnTitleCountTest(column1, 1);
		columnTitleValueTest(column1, "Column1");
		columnDetailCountTest(column1, 8);
		columnDetailValueTest(column1, 7, "text");
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1");
		for (int i = 0; i < 8; i++) {
			dataSource.add("text");
		}
		return dataSource;
	}
}
