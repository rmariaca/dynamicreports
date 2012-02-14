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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.sf.dynamicreports.report.exception.DRReportException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRSwapFile;
import net.sf.jasperreports.engine.util.JRSwapFile.SwapHandle;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperSwapFileHandler extends AbstractPrintListHandler {
	private List<JasperPrint> printList;
	private JRSwapFile swapFile;
	private List<SwapHandle> handles;

	public JasperSwapFileHandler(JRSwapFile swapFile) {
		this.swapFile = swapFile;
		this.handles = new ArrayList<SwapHandle>();
		printList = new PrintList();
	}

	@Override
	protected void add(JasperPrint jasperPrint) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(jasperPrint);
			oos.close();
			bos.close();
			byte[] data = bos.toByteArray();
			SwapHandle handle = swapFile.write(data);
			handles.add(handle);
		} catch (IOException e) {
			throw new DRReportException(e);
		}
	}

	public List<JasperPrint> getPrintList() {
		return printList;
	}

	private class PrintList implements List<JasperPrint> {

		public int size() {
			return handles.size();
		}

		public boolean isEmpty() {
			return handles.isEmpty();
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
			for (SwapHandle handle : handles) {
				swapFile.free(handle);
			}
			handles.clear();
		}

		public JasperPrint get(int index) {
			SwapHandle handle = handles.get(index);
			try {
				byte[] data = swapFile.read(handle, false);
				ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
				JasperPrint jasperPrint = (JasperPrint) ois.readObject();
				ois.close();
				return jasperPrint;
			} catch (IOException e) {
				throw new DRReportException(e);
			} catch (ClassNotFoundException e) {
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
