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

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jfree.data.category.CategoryDataset;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SeriesOrderByNameGroupedCategoryDataset extends SeriesOrderByNameCategoryDataset {

	public SeriesOrderByNameGroupedCategoryDataset(CategoryDataset dataset, List<String> seriesOrderByName) {
		super(dataset, seriesOrderByName);
	}

	@Override
	protected Comparator<String> getSeriesComparator() {
		return new SeriesComparator();
	}

	private class SeriesComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			String group1 = StringUtils.substringBefore(o1, GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY);
			String group2 = StringUtils.substringBefore(o2, GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY);
			int compare = group1.compareTo(group2);
			if (compare == 0) {
				String row1 = StringUtils.substringAfter(o1, GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY);
				String row2 = StringUtils.substringAfter(o2, GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY);
				int index1 = seriesOrderByName.indexOf(row1);
				int index2 = seriesOrderByName.indexOf(row2);
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
			return compare;
		}

	}
}
