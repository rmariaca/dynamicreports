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
import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SeriesOrderByNameCategoryDataset implements CategoryDataset {
	protected List<String> seriesOrderByName;
	protected CategoryDataset dataset;

	public SeriesOrderByNameCategoryDataset(CategoryDataset dataset, List<String> seriesOrderByName) {
		this.dataset = dataset;
		this.seriesOrderByName = new ArrayList<String>();
		for (String serieName : seriesOrderByName) {
			if (dataset.getColumnIndex(serieName) != -1) {
				this.seriesOrderByName.add(serieName);
			}
		}
		for (int i = 0; i < dataset.getColumnCount(); i++) {
			String serieName = (String) dataset.getColumnKey(i);
			if (!this.seriesOrderByName.contains(serieName)) {
				this.seriesOrderByName.add(serieName);
			}
		}
	}

	@Override
	public Comparable<?> getRowKey(int row) {
		return dataset.getRowKey(row);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public int getRowIndex(Comparable key) {
		return dataset.getRowIndex(key);
	}

	@Override
	public List<?> getRowKeys() {
		return dataset.getRowKeys();
	}

	@Override
	public Comparable<?> getColumnKey(int column) {
		return seriesOrderByName.get(column);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public int getColumnIndex(Comparable key) {
		return seriesOrderByName.indexOf(key);
	}

	@Override
	public List<?> getColumnKeys() {
		return seriesOrderByName;
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
		int columnIndex = dataset.getColumnIndex(seriesOrderByName.get(column));
		return dataset.getValue(row, columnIndex);
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

}
