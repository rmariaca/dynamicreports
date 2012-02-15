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

package net.sf.dynamicreports.jasper.base.reporthandler;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.sf.dynamicreports.report.exception.DRReportException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRVirtualizationHelper;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperPrintListFileHandler extends AbstractPrintListHandler {
	private List<JasperPrint> printList;
	private final String directory;
	private JRVirtualizer virtualizer;
	private int index = 0;

	public JasperPrintListFileHandler(String directory) {
		this(directory, null);
	}

	public JasperPrintListFileHandler(String directory, JRVirtualizer virtualizer) {
		this.directory = directory;
		this.virtualizer = virtualizer;
		printList = new PrintList();
	}

	@Override
	protected void add(JasperPrint jasperPrint) {
		try {
			JRSaver.saveObject(jasperPrint, directory + "/jrprint" + index++);
		} catch (JRException e) {
			throw new DRReportException(e);
		}
	}

	public List<JasperPrint> getPrintList() {
		return printList;
	}

	private class PrintList implements List<JasperPrint> {

		public int size() {
			return index;
		}

		public boolean isEmpty() {
			return index == 0;
		}

		public boolean contains(Object o) {
			throw new UnsupportedOperationException();
		}

		public Iterator<JasperPrint> iterator() {
			throw new UnsupportedOperationException();
		}

		public Object[] toArray() {
			throw new UnsupportedOperationException();
		}

		public <T> T[] toArray(T[] a) {
			throw new UnsupportedOperationException();
		}

		public boolean add(JasperPrint o) {
			throw new UnsupportedOperationException();
		}

		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		public boolean containsAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		public boolean addAll(Collection<? extends JasperPrint> c) {
			throw new UnsupportedOperationException();
		}

		public boolean addAll(int index, Collection<? extends JasperPrint> c) {
			throw new UnsupportedOperationException();
		}

		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		public void clear() {
			throw new UnsupportedOperationException();
		}

		public JasperPrint get(int index) {
			try {
				if (virtualizer != null) {
					JRVirtualizationHelper.setThreadVirtualizer(virtualizer);
				}
				JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObjectFromFile(directory + "/jrprint" + index);
				if (virtualizer != null) {
					JRVirtualizationHelper.clearThreadVirtualizer();
				}
				return jasperPrint;
			} catch (JRException e) {
				throw new DRReportException(e);
			}
		}

		public JasperPrint set(int index, JasperPrint element) {
			throw new UnsupportedOperationException();
		}

		public void add(int index, JasperPrint element) {
			throw new UnsupportedOperationException();
		}

		public JasperPrint remove(int index) {
			throw new UnsupportedOperationException();
		}

		public int indexOf(Object o) {
			throw new UnsupportedOperationException();
		}

		public int lastIndexOf(Object o) {
			throw new UnsupportedOperationException();
		}

		public ListIterator<JasperPrint> listIterator() {
			throw new UnsupportedOperationException();
		}

		public ListIterator<JasperPrint> listIterator(int index) {
			throw new UnsupportedOperationException();
		}

		public List<JasperPrint> subList(int fromIndex, int toIndex) {
			throw new UnsupportedOperationException();
		}
	}
}
