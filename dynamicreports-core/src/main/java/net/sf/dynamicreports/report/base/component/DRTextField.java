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

package net.sf.dynamicreports.report.base.component;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.component.DRITextField;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRTextField<T> extends DRHyperLinkComponent implements DRITextField<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private DRIExpression<T> valueExpression;
	private String pattern;	
	private HorizontalAlignment horizontalAlignment;
	private DRIValueFormatter<?, ? super T> valueFormatter;	
	private DRIDataType<? super T, T> dataType;
	private Integer columns;
	private Integer rows;
	
	public DRIExpression<T> getValueExpression() {
		return valueExpression;
	}
	
	public void setValueExpression(DRIExpression<T> valueExpression) {
		Validate.notNull(valueExpression, "valueExpression must not be null");
		this.valueExpression = valueExpression;
	}
	
	public String getPattern() {
		return pattern;
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public DRIValueFormatter<?, ? super T> getValueFormatter() {
		return valueFormatter;
	}

	public void setValueFormatter(DRIValueFormatter<?, ? super T> valueFormatter) {
		this.valueFormatter = valueFormatter;
	}

	public DRIDataType<? super T, T> getDataType() {
		return dataType;
	}

	public void setDataType(DRIDataType<? super T, T> dataType) {
		this.dataType = dataType;
	}

  /**
   * Returns the number of columns.
   *
   * @return the number of columns >= 1
   */
	public Integer getColumns() {
		return columns;
	}

  /**
   * This method is used to define the width of a column.
   * The width is set to the <code>columns</code> multiplied by width of the
   * character <em>m</em> for the font used
   * 
   * @param columns the number of columns >= 1
   * @exception IllegalArgumentException if <code>columns</code> is < 1
   */
	public void setColumns(Integer columns) {
		if (columns != null) {
			Validate.isTrue(columns >= 1, "columns must be >= 1");
		}
		this.columns = columns;
	}

  /**
   * Returns the number of rows.
   *
   * @return the number of rows >= 1
   */
	public Integer getRows() {
		return rows;
	}

  /**
   * This method is used to define the height of a column.
   * The height is set to the <code>rows</code> multiplied by height of the font
   * 
   * @param rows the number of rows >= 1
   * @exception IllegalArgumentException if <code>rows</code> is < 1
   */
	public void setRows(Integer rows) {
		if (rows != null) {
			Validate.isTrue(rows >= 1, "rows must be >= 1");
		}
		this.rows = rows;
	}		
}

