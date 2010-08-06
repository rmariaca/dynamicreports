/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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

package net.sf.dynamicreports.test.jasper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperPrint;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class AbstractJasperTest {	
	private JasperPrint jasperPrint;	
	
	@Before
	public void init() {
		try {
			JasperReportBuilder reportBuilder = DynamicReports.report();
			configureReport(reportBuilder);		
			serializableTest(reportBuilder);
			jasperPrint = reportBuilder
											.setDataSource(createDataSource())
											.toJasperPrint();						
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());	
		}
	}
	
	@Test
	public void test() {		
	}
	
	private void serializableTest(JasperReportBuilder report) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(report);
		oos.flush();
		oos.close();
	}
	
	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}
	
	protected void numberOfPagesTest(int expectedNumberOfPages) {
		Assert.assertEquals("pages", expectedNumberOfPages, getNumberOfPages());
	}
	
	private int getNumberOfPages() {
		return jasperPrint.getPages().size();
	}

	protected JRPrintElement getElementAt(String key, int index) {
		List<JRPrintElement> elements = findElement(key);
		if (elements.size() - 1 < index) {
			Assert.fail("Element " + key + " at index " + index + " not found");
			return null;
		}
		return elements.get(index);
	}
	
	protected List<JRPrintElement> findElement(String key) {
		List<JRPrintElement> elements = new ArrayList<JRPrintElement>();
		for (Iterator<?> iterator = jasperPrint.getPages().iterator(); iterator.hasNext();) {
			JRPrintPage page = (JRPrintPage) iterator.next();
			for (Iterator<?> iterator2 = page.getElements().iterator(); iterator2.hasNext();) {
				JRPrintElement element = (JRPrintElement) iterator2.next();			
				findElement(key, elements, element);
			}
		}		
		return elements;
	}
	
	private void findElement(String key, List<JRPrintElement> elements, JRPrintElement element) {
		if (key.equals(element.getKey())) {
			elements.add(element);
		}
		if (element instanceof JRPrintFrame) {
			for (Iterator<?> iterator = ((JRPrintFrame) element).getElements().iterator(); iterator.hasNext();) {
				JRPrintElement element2 = (JRPrintElement) iterator.next();
				findElement(key, elements, element2);
			}
		}
	}
	
	protected JRDataSource createDataSource() {
		return null;
	}
	
	protected abstract void configureReport(JasperReportBuilder rb);	
}
