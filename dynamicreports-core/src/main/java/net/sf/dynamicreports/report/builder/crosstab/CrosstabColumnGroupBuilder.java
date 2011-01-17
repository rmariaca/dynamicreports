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

package net.sf.dynamicreports.report.builder.crosstab;

import net.sf.dynamicreports.report.base.crosstab.DRCrosstabColumnGroup;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.CrosstabColumnPosition;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class CrosstabColumnGroupBuilder extends AbstractCrosstabGroupBuilder<CrosstabColumnGroupBuilder, DRCrosstabColumnGroup> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected CrosstabColumnGroupBuilder() {
		super(new DRCrosstabColumnGroup());
	}

	public CrosstabColumnGroupBuilder setHeaderHeight(Integer headerHeight) {
		getObject().setHeaderHeight(headerHeight);
		return this;
	}

	public CrosstabColumnGroupBuilder setTotalHeaderWidth(Integer totalHeaderWidth) {
		getObject().setTotalHeaderWidth(totalHeaderWidth);
		return this;
	}

	public CrosstabColumnGroupBuilder setPosition(CrosstabColumnPosition position) {
		getObject().setPosition(position);
		return this;
	}
}
