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

package net.sf.dynamicreports.report.base.datatype;

import java.util.Locale;

import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public abstract class AbstractDataType<T> implements DRIDataType<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	public String getPattern() {
		return null;
	}
	
	public DRIValueFormatter<?, ? super T> getValueFormatter() {
		return null;
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return null;
	}
	
	public String toString(Object value, Locale locale) {
		if (value != null) {
			return value.toString();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getValueClass() {
		return (Class<T>) ReportUtils.getGenericClass(this);
	}
}
