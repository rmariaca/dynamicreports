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

package net.sf.dynamicreports.report.base;

import net.sf.dynamicreports.report.base.column.DRColumn;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.SubtotalPosition;
import net.sf.dynamicreports.report.definition.DRISubtotal;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRSubtotal<T> implements DRISubtotal<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private DRColumn<?> showInColumn;
	private DRTextField<T> valueField;
	private SubtotalPosition position;
	private DRGroup group;
	
	private DRIExpression<?> labelExpression;
	private DRStyle labelStyle;
	
	public DRSubtotal(DRColumn<?> showInColumn) {		
		setShowInColumn(showInColumn);
		init();
	}
	
	private void init() {
		valueField = new DRTextField<T>();
	}
	
	public void setShowInColumn(DRColumn<?> showInColumn) {
		Validate.notNull(showInColumn, "showInColumn must not be null");
		this.showInColumn = showInColumn;
	}
	
	public DRColumn<?> getShowInColumn() {
		return showInColumn;
	}

	public DRTextField<T> getValueField() {
		return valueField;
	}
	
	public DRIExpression<?> getLabelExpression() {
		return labelExpression;
	}

	public void setLabelExpression(DRIExpression<?> labelExpression) {
		this.labelExpression = labelExpression;
	}

	public DRStyle getLabelStyle() {
		return labelStyle;
	}

	public void setLabelStyle(DRStyle labelStyle) {
		this.labelStyle = labelStyle;
	}
	
	public SubtotalPosition getPosition() {
		return position;
	}

	public void setPosition(SubtotalPosition position) {
		Validate.notNull(position, "position must not be null");
		this.position = position;
	}

	public DRGroup getGroup() {
		return group;
	}

	public void setGroup(DRGroup group) {
		this.group = group;
	}

	public String getName() {
		return valueField.getValueExpression().getName();
	}
	
	public Class<? super T> getValueClass() {
		return valueField.getValueExpression().getValueClass();
	}
}