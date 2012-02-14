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

package net.sf.dynamicreports.design.base.crosstab;

import net.sf.dynamicreports.design.base.DRDesignDataset;
import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabDataset;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignCrosstabDataset implements DRIDesignCrosstabDataset {
	private DRDesignDataset subDataset;
	private Boolean dataPreSorted;
	private ResetType resetType;
	private DRDesignGroup resetGroup;

	public DRDesignDataset getSubDataset() {
		return subDataset;
	}

	public void setSubDataset(DRDesignDataset subDataset) {
		this.subDataset = subDataset;
	}

	public Boolean getDataPreSorted() {
		return dataPreSorted;
	}

	public void setDataPreSorted(Boolean dataPreSorted) {
		this.dataPreSorted = dataPreSorted;
	}

	public ResetType getResetType() {
		return resetType;
	}

	public void setResetType(ResetType resetType) {
		this.resetType = resetType;
	}

	public DRDesignGroup getResetGroup() {
		return resetGroup;
	}

	public void setResetGroup(DRDesignGroup resetGroup) {
		this.resetGroup = resetGroup;
	}
}
