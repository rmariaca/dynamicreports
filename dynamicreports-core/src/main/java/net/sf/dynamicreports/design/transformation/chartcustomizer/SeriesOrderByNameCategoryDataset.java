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

package net.sf.dynamicreports.design.transformation.chartcustomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SeriesOrderByNameCategoryDataset implements CategoryDataset {
	protected List<String> seriesOrderByName;
	protected List<String> rowKeys;
	protected CategoryDataset dataset;

	public SeriesOrderByNameCategoryDataset(CategoryDataset dataset, List<String> seriesOrderByName) {
		this.dataset = dataset;
		this.seriesOrderByName = seriesOrderByName;
		this.rowKeys = new ArrayList<String>();
		for (int i = 0; i < dataset.getRowCount(); i++) {
			String serieName = (String) dataset.getRowKey(i);
			this.rowKeys.add(serieName);
		}
		Collections.sort(this.rowKeys, getSeriesComparator());
	}

	@Override
	public Comparable<?> getRowKey(int row) {
		return rowKeys.get(row);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public int getRowIndex(Comparable key) {
		return rowKeys.indexOf(key);
	}

	@Override
	public List<?> getRowKeys() {
		return rowKeys;
	}

	@Override
	public Comparable<?> getColumnKey(int column) {
		return dataset.getColumnKey(column);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public int getColumnIndex(Comparable key) {
		return dataset.getColumnIndex(key);
	}

	@Override
	public List<?> getColumnKeys() {
		return dataset.getColumnKeys();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Number getValue(Comparable rowKey, Comparable columnKey) {
		return getValue(getRowIndex(rowKey), getColumnIndex(columnKey));
	}

	@Override
	public int getRowCount() {
		return dataset.getRowCount();
	}

	@Override
	public int getColumnCount() {
		return dataset.getColumnCount();
	}

	@Override
	public Number getValue(int row, int column) {
		int rowIndex = dataset.getRowIndex(rowKeys.get(row));
		return dataset.getValue(rowIndex, column);
	}

	@Override
	public void addChangeListener(DatasetChangeListener listener) {
		dataset.addChangeListener(listener);
	}

	@Override
	public void removeChangeListener(DatasetChangeListener listener) {
		dataset.removeChangeListener(listener);
	}

	@Override
	public DatasetGroup getGroup() {
		return dataset.getGroup();
	}

	@Override
	public void setGroup(DatasetGroup group) {
		dataset.setGroup(group);
	}

	protected Comparator<String> getSeriesComparator() {
		return new SeriesComparator();
	}

	private class SeriesComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			int index1 = seriesOrderByName.indexOf(o1);
			int index2 = seriesOrderByName.indexOf(o2);
			if (index1 == index2) {
				return 0;
			}
			if (index1 < 0) {
				return index1;
			}
			if (index2 < 0) {
				return index2;
			}
			return index1 - index2;
		}

	}

}
