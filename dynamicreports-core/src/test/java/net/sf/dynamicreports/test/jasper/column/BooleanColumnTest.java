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

package net.sf.dynamicreports.test.jasper.column;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;
import java.util.Locale;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.renderers.BatikRenderer;

import org.junit.Assert;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class BooleanColumnTest extends AbstractJasperValueTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.setLocale(Locale.ENGLISH)
			.columns(
					col.booleanColumn("field1").setComponentType(BooleanComponentType.TEXT_TRUE_FALSE),
					col.booleanColumn("field1").setComponentType(BooleanComponentType.TEXT_YES_NO),
					col.booleanColumn("field1").setComponentType(BooleanComponentType.IMAGE_STYLE_1),
					col.booleanColumn("field1").setComponentType(BooleanComponentType.IMAGE_STYLE_2),
					col.booleanColumn("field1").setComponentType(BooleanComponentType.IMAGE_STYLE_3),
					col.booleanColumn("field1").setComponentType(BooleanComponentType.IMAGE_STYLE_4),
					col.booleanColumn("field1").setComponentType(BooleanComponentType.IMAGE_CHECKBOX_1),
					col.booleanColumn("field1").setComponentType(BooleanComponentType.IMAGE_CHECKBOX_2),
					col.booleanColumn("field1").setComponentType(BooleanComponentType.IMAGE_BALL));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		elementCountTest("detail.column_field11", 2);
		elementValueTest("detail.column_field11", "True", "False");

		elementCountTest("detail.column_field12", 2);
		elementValueTest("detail.column_field12", "Yes", "No");

		testImage("detail.image1", 0);
		testImage("detail.image1", 1);

		testImage("detail.image2", 0);
		testImage("detail.image2", 1);

		testImage("detail.image3", 0);
		testImage("detail.image3", 1);

		testImage("detail.image4", 0);
		testImage("detail.image4", 1);

		testImage("detail.image5", 0);
		testImage("detail.image5", 1);

		testImage("detail.image6", 0);
		testImage("detail.image6", 1);

		testImage("detail.image7", 0);
		testImage("detail.image7", 1);
	}

	private void testImage(String name, int index) {
		BatikRenderer batikRenderer = ((BatikRenderer) ((JRPrintImage) getElementAt(name, index)).getRenderer());
		try {
			byte[] data = batikRenderer.getImageData();
			Assert.assertNotNull(data);
		} catch (JRException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1");
		dataSource.add(true);
		dataSource.add(false);
		return dataSource;
	}
}
