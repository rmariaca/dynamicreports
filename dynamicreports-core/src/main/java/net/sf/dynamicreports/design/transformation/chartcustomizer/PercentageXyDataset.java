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

import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class PercentageXyDataset implements XYDataset {
	private XYDataset dataset;

	public PercentageXyDataset(XYDataset dataset) {
		this.dataset = dataset;
	}

	public int getSeriesCount() {
		return dataset.getSeriesCount();
	}

	public Comparable<?> getSeriesKey(int series) {
		return dataset.getSeriesKey(series);
	}

	@SuppressWarnings("rawtypes")
	public int indexOf(Comparable seriesKey) {
		return dataset.indexOf(seriesKey);
	}

	public void addChangeListener(DatasetChangeListener listener) {
		dataset.addChangeListener(listener);
	}

	public void removeChangeListener(DatasetChangeListener listener) {
		dataset.removeChangeListener(listener);
	}

	public DatasetGroup getGroup() {
		return dataset.getGroup();
	}

	public void setGroup(DatasetGroup group) {
		dataset.setGroup(group);
	}

	public DomainOrder getDomainOrder() {
		return dataset.getDomainOrder();
	}

	public int getItemCount(int series) {
		return dataset.getItemCount(series);
	}

	public Number getX(int series, int item) {
		return dataset.getX(series, item);
	}

	public double getXValue(int series, int item) {
		return dataset.getXValue(series, item);
	}

	public Number getY(int series, int item) {
		double total = 0;
		for (int i = 0; i < getSeriesCount(); i++) {
			Number value = dataset.getYValue(i, item);
			if (value != null) {
				total += value.doubleValue();
			}
		}
		Number value = dataset.getYValue(series, item);
		if (value == null) {
			return 0;
		}
		double actual = value.doubleValue();
		if (total > 0) {
			return actual / total * 100;
		}
		return 0;
	}

	public double getYValue(int series, int item) {
		return getY(series, item).doubleValue();
	}

}
