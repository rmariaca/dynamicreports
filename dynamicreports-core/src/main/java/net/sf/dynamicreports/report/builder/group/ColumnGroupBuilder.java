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

package net.sf.dynamicreports.report.builder.group;

import net.sf.dynamicreports.report.base.DRColumn;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.constant.Constants;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class ColumnGroupBuilder extends GroupBuilder<ColumnGroupBuilder> {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private DRColumn<?> column;
	
	protected ColumnGroupBuilder(ColumnBuilder<?, ?> column) {
		Validate.notNull(column, "column must not be null");
		this.column = column.build();
	}
	
	protected ColumnGroupBuilder(String name, ColumnBuilder<?, ?> column) {
		super(name);
		Validate.notNull(column, "column must not be null");
		this.column = column.build();
	}
	
	public ColumnGroupBuilder setHideColumn(Boolean hideColumn) {
		getObject().setHideColumn(hideColumn);
		return this;
	}
	
	@Override
	protected void configure() {
		setValueExpression(column);
		getObject().getValueField().setStyle(column.getValueField().getStyle());
		getObject().setTitleExpression(column.getTitleExpression());
		getObject().setTitleStyle(column.getTitleStyle());
		super.configure();
	}
}
