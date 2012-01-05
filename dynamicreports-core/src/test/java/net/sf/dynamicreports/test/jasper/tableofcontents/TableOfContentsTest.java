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
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintText;

import org.apache.commons.lang.StringUtils;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class TableOfContentsTest extends AbstractJasperValueTest {
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<String> column3;
	private ColumnGroupBuilder group1;
	private ColumnGroupBuilder group2;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.tableOfContents()
	  	.columns(
	  		column1 = col.column("Column1", "field1", type.stringType()),
	  		column2 = col.column("Column2", "field2", type.stringType()),
	  		column3 = col.column("Column3", "field3", type.stringType()))
		  .groupBy(
		  	group1 = grp.group(column1),
		  	group2 = grp.group(column2));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(3);

		elementValueTest("title.textField1", "Table of contents");

		elementCountTest("detail.textField1", 3);
		for (int i = 0; i < 3; i++) {
			String anchorName = group1.getGroup().getName() + "_" + i * 24;

			elementValueTest("detail.textField1", i, "value" + (i + 1));
			JRPrintText text = (JRPrintText) getElementAt("detail.textField1", i);
			Assert.assertEquals("text anchor", anchorName, text.getHyperlinkAnchor());

			JRPrintText dots = (JRPrintText) getElementAt("detail.textField2", i);
			Assert.assertTrue("dots", StringUtils.containsOnly(dots.getText(), "."));
			Assert.assertEquals("dots anchor", anchorName, dots.getHyperlinkAnchor());

			JRPrintText pageIndex = (JRPrintText) getElementAt("detail.textField3", i);
			Assert.assertEquals("pageIndex anchor", anchorName, pageIndex.getHyperlinkAnchor());
		}
		elementValueTest("detail.textField3", "1", "1", "2");

		elementCountTest("detail.textField4", 9);
		for (int i = 0; i < 9; i++) {
			String anchorName = group2.getGroup().getName() + "_" + i * 8;

			JRPrintText text = (JRPrintText) getElementAt("detail.textField4", i);
			Assert.assertEquals("text anchor", anchorName, text.getHyperlinkAnchor());

			JRPrintText dots = (JRPrintText) getElementAt("detail.textField5", i);
			Assert.assertTrue("dots", StringUtils.containsOnly(dots.getText(), "."));
			Assert.assertEquals("dots anchor", anchorName, dots.getHyperlinkAnchor());

			JRPrintText pageIndex = (JRPrintText) getElementAt("detail.textField6", i);
			Assert.assertEquals("pageIndex anchor", anchorName, pageIndex.getHyperlinkAnchor());
		}
		elementValueTest("detail.textField4", "value1", "value2", "value3", "value1", "value2", "value3", "value1", "value2", "value3");
		elementValueTest("detail.textField6", "1", "1", "1", "1", "1", "1", "2", "2", "2");

		String name1 = "groupHeaderTitleAndValue.group_" + group1.getGroup().getName() + ".tocReference1";
		String name2 = "groupHeaderTitleAndValue.group_" + group1.getGroup().getName() + "1";
		elementCountTest(name1, 3);
		for (int i = 0; i < 3; i++) {
			String anchorName = group1.getGroup().getName() + "_" + i * 24;

			elementValueTest(name1, i, "");
			JRPrintText reference = (JRPrintText) getElementAt(name1, i);
			Assert.assertEquals("reference anchorName " + name1, anchorName, reference.getAnchorName());

			groupHeaderValueTest(group1, i, "value" + (i + 1));
			reference = (JRPrintText) getElementAt(name2, i);
			Assert.assertEquals("reference anchorName " + name2, anchorName, reference.getAnchorName());
		}

		name1 = "groupHeaderTitleAndValue.group_" + group2.getGroup().getName() + ".tocReference1";
		name2 = "groupHeaderTitleAndValue.group_" + group2.getGroup().getName() + "1";
		elementCountTest(name1, 9);
		for (int i = 0; i < 9; i++) {
			String anchorName = group2.getGroup().getName() + "_" + i * 8;

			elementValueTest(name1, i, "");
			JRPrintText reference = (JRPrintText) getElementAt(name1, i);
			Assert.assertEquals("reference anchorName " + name1, anchorName, reference.getAnchorName());

			reference = (JRPrintText) getElementAt(name2, i);
			Assert.assertEquals("reference anchorName " + name2, anchorName, reference.getAnchorName());
		}
		groupHeaderValueTest(group2, "value1", "value2", "value3", "value1", "value2", "value3", "value1", "value2", "value3");

		columnTitleCountTest(column3, 2);
		columnTitleValueTest(column3, "Column3", "Column3");
		columnDetailCountTest(column3, 72);
		columnDetailValueTest(column3, 71, "text");
	}

	@Override
	protected JRDataSource createDataSource() {
		String[] values = new String[]{"value1", "value2", "value3"};
		DataSource dataSource = new DataSource("field1", "field2", "field3");
		for (String field1 : values) {
			for (String field2 : values) {
				for (int i = 0; i < 8; i++) {
					dataSource.add(field1, field2, "text");
				}
			}
		}
		return dataSource;
	}
}
