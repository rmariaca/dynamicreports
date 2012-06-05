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

import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class PercentageGroupedCategoryDataset extends PercentageCategoryDataset {
	private KeyToGroupMap map;

	public PercentageGroupedCategoryDataset(CategoryDataset dataset, KeyToGroupMap map) {
		super(dataset);
		this.map = map;
	}

	@Override
	public Number getValue(int row, int column) {
		double total = 0;
		for (int i = 0; i < getRowCount(); i++) {
			Number value = dataset.getValue(i, column);
			if (value != null) {
				if (map.getGroup(getRowKey(row)).equals(map.getGroup(getRowKey(i)))) {
					total += value.doubleValue();
				}
			}
		}
		Number value = dataset.getValue(row, column);
		if (value == null) {
			return 0;
		}
		double actual = value.doubleValue();
		if (total > 0) {
			return actual / total * 100;
		}
		return 0;
	}

}
