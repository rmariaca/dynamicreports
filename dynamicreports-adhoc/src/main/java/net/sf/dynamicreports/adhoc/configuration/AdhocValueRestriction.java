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

package net.sf.dynamicreports.adhoc.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocValueRestriction extends AdhocRestriction {
	private static final long serialVersionUID = 1L;

	private String name;
	private AdhocValueOperator operator;
	private List<String> values;

	public AdhocValueRestriction() {
		values = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdhocValueOperator getOperator() {
		return operator;
	}

	public void setOperator(AdhocValueOperator operator) {
		this.operator = operator;
	}

	public List<String> getValues() {
		return values;
	}

	public void addValue(String value) {
		this.values.add(value);
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
