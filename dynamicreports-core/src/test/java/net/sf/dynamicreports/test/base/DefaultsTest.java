/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca
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

package net.sf.dynamicreports.test.base;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import junit.framework.Assert;
import net.sf.dynamicreports.report.base.datatype.DRDataType;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.defaults.Default;
import net.sf.dynamicreports.report.defaults.DefaultBinder;
import net.sf.dynamicreports.report.defaults.xml.XmlDynamicReports;

import org.junit.Test;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DefaultsTest {
	
	private Default load() {
		InputStream is = DefaultsTest.class.getResourceAsStream("dynamicreports-defaults.xml");
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(XmlDynamicReports.class).createUnmarshaller();						
			JAXBElement<XmlDynamicReports> root = unmarshaller.unmarshal(new StreamSource(is), XmlDynamicReports.class);
			return DefaultBinder.bind(root.getValue()); 
		} catch (JAXBException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return null;
	}
	
	@Test
	public void test() {
		DefaultBinder.bind(null);
		Default defaults = load();
		
		DRFont font = defaults.getFont();
		Assert.assertEquals("Font name", "Arial", font.getFontName());
		Assert.assertEquals("Font size", new Integer(15), font.getFontSize());
		Assert.assertEquals("Pdf font name", "Courier New", font.getPdfFontName());
		Assert.assertEquals("Pdf encoding", "Cp1250", font.getPdfEncoding());
		Assert.assertTrue("Pdf embedded", font.getPdfEmbedded());
		
		testDataType("BigDecimal", defaults.getBigDecimalType(), "#,###.00#", HorizontalAlignment.LEFT);
		testDataType("BigInteger", defaults.getBigIntegerType(), "#,###", HorizontalAlignment.LEFT);
		testDataType("Byte", defaults.getByteType(), "#,###", HorizontalAlignment.LEFT);
		testDataType("Double", defaults.getDoubleType(), "#,###.#", HorizontalAlignment.LEFT);
		testDataType("Float", defaults.getFloatType(), "#,###.#", HorizontalAlignment.LEFT);
		testDataType("Integer", defaults.getIntegerType(), "#,###", HorizontalAlignment.LEFT);
		testDataType("Long", defaults.getLongType(), "#,###", HorizontalAlignment.LEFT);
		testDataType("Short", defaults.getShortType(), "#,###", HorizontalAlignment.LEFT);
		testDataType("Date", defaults.getDateType(), "MM.dd.yyyy", HorizontalAlignment.LEFT);
		testDataType("DateYearToMonth", defaults.getDateYearToMonthType(), "MM.yyyy", HorizontalAlignment.LEFT);
		testDataType("DateYearToHour", defaults.getDateYearToHourType(), "MM.dd.yyyy h a", HorizontalAlignment.LEFT);
		testDataType("DateYearToMinute", defaults.getDateYearToMinuteType(), "MM.dd.yyyy h:mm a", HorizontalAlignment.LEFT);
		testDataType("DateYearToSecond", defaults.getDateYearToSecondType(), "MM.dd.yyyy h:mm:ss a", HorizontalAlignment.LEFT);
		testDataType("DateYearToFraction", defaults.getDateYearToFractionType(), "MM.dd.yyyy h:mm:ss,SSS a", HorizontalAlignment.LEFT);
		testDataType("DateYear", defaults.getDateYearType(), "yy", HorizontalAlignment.LEFT);
		testDataType("DateMonth", defaults.getDateMonthType(), "MM", HorizontalAlignment.LEFT);
		testDataType("DateDay", defaults.getDateDayType(), "d", HorizontalAlignment.LEFT);
		testDataType("TimeHourToMinute", defaults.getTimeHourToMinuteType(), "hh:mm a", HorizontalAlignment.LEFT);
		testDataType("TimeHourToSecond", defaults.getTimeHourToSecondType(), "hh:mm:ss a", HorizontalAlignment.LEFT);
		testDataType("TimeHourToFraction", defaults.getTimeHourToFractionType(), "hh:mm:ss,SSS a", HorizontalAlignment.LEFT);
		testDataType("Percentage", defaults.getPercentageType(), "#,###.00%", HorizontalAlignment.LEFT);
		testDataType("Boolean", defaults.getBooleanType(), null, HorizontalAlignment.RIGHT);
		testDataType("Character", defaults.getCharacterType(), null, HorizontalAlignment.RIGHT);
		testDataType("String", defaults.getStringType(), null, HorizontalAlignment.RIGHT);		
	}
	
	private void testDataType(String name, DRDataType<?, ?> dataType, String pattern, HorizontalAlignment horizontalAlignment) {
		Assert.assertEquals(name + " pattern", pattern, dataType.getPattern());
		Assert.assertEquals(name + " horizontal alignment", horizontalAlignment, dataType.getHorizontalAlignment());
	}
}
