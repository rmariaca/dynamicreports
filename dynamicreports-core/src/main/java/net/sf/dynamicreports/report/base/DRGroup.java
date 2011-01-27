/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
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

import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.definition.DRIGroup;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRGroup implements DRIGroup {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private String name;	
	private DRTextField<?> valueField;	
	private DRIExpression<?> titleExpression;
	private DRStyle titleStyle;
	private Integer titleWidth;
	private GroupHeaderLayout headerLayout;
	private Boolean hideColumn;
	private Boolean groupByDataType;
	private Boolean showColumnHeaderAndFooter;
	private DRISimpleExpression<Boolean> printSubtotalsWhenExpression;
	private Integer padding;
	private Boolean startInNewPage;
	private Boolean startInNewColumn;
	private Boolean reprintHeaderOnEachPage;
	private DRBand headerBand;
	private DRBand footerBand;
	
	public DRGroup(DRTextField<?> valueField) {
		this(ReportUtils.generateUniqueName("group"), valueField);
	}
	
	public DRGroup(String name, DRTextField<?> valueField) {
		Validate.notEmpty(name, "name must not be empty");
		Validate.notNull(valueField, "valueField must not be null");
		this.name = name;
		this.valueField = valueField;
		init();
	}
	
	private void init() {
		headerBand = new DRBand();
		footerBand = new DRBand();
	}

	public String getName() {
		return name;
	}

	public DRTextField<?> getValueField() {
		return valueField;
	}
	
	public DRIExpression<?> getTitleExpression() {
		return titleExpression;
	}

	public void setTitleExpression(DRIExpression<?> titleExpression) {
		this.titleExpression = titleExpression;
	}

	public DRStyle getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(DRStyle titleStyle) {
		this.titleStyle = titleStyle;
	}
	
	public Integer getTitleWidth() {
		return titleWidth;
	}
	
	public void setTitleWidth(Integer titleWidth) {
		this.titleWidth = titleWidth;
	}
	
	public GroupHeaderLayout getHeaderLayout() {
		return headerLayout;
	}

	public void setHeaderLayout(GroupHeaderLayout headerLayout) {
		this.headerLayout = headerLayout;
	}
	
	public Boolean getHideColumn() {
		return hideColumn;
	}

	public void setHideColumn(Boolean hideColumn) {
		this.hideColumn = hideColumn;
	}
	
	public Boolean getGroupByDataType() {
		return groupByDataType;
	}
	
	public void setGroupByDataType(Boolean groupByDataType) {
		this.groupByDataType = groupByDataType;
	}
	
	public Boolean getShowColumnHeaderAndFooter() {
		return showColumnHeaderAndFooter;
	}

	public void setShowColumnHeaderAndFooter(Boolean showColumnHeaderAndFooter) {
		this.showColumnHeaderAndFooter = showColumnHeaderAndFooter;
	}

	public DRISimpleExpression<Boolean> getPrintSubtotalsWhenExpression() {
		return printSubtotalsWhenExpression;
	}

	public void setPrintSubtotalsWhenExpression(DRISimpleExpression<Boolean> printSubtotalsWhenExpression) {
		this.printSubtotalsWhenExpression = printSubtotalsWhenExpression;
	}

	public Integer getPadding() {
		return padding;
	}

	public void setPadding(Integer padding) {
		if (padding != null) {
			Validate.isTrue(padding >= 0, "padding must be >= 0");
		}
		this.padding = padding;
	}

	public Boolean getStartInNewPage() {
		return startInNewPage;
	}

	public void setStartInNewPage(Boolean startInNewPage) {
		this.startInNewPage = startInNewPage;
	}

	public Boolean getStartInNewColumn() {
		return startInNewColumn;
	}

	public void setStartInNewColumn(Boolean startInNewColumn) {
		this.startInNewColumn = startInNewColumn;
	}

	public Boolean getReprintHeaderOnEachPage() {
		return reprintHeaderOnEachPage;
	}

	public void setReprintHeaderOnEachPage(Boolean reprintHeaderOnEachPage) {
		this.reprintHeaderOnEachPage = reprintHeaderOnEachPage;
	}
	
	public DRBand getHeaderBand() {
		return headerBand;
	}
	
	public DRBand getFooterBand() {
		return footerBand;
	}
}
