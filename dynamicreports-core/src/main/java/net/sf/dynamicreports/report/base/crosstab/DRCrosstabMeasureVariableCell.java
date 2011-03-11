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

package net.sf.dynamicreports.report.base.crosstab;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.CrosstabPercentageType;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabCellStyle;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabMeasureCell;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabMeasureVariable;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRCrosstabMeasureVariableCell<T> implements DRICrosstabMeasureVariable<T>, DRICrosstabMeasureCell<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private String name;
	private DRIExpression<?> valueExpression;
	private DRIDataType<? super T, T> dataType;
	private Calculation calculation;
	private CrosstabPercentageType percentageType;
	private String pattern;
	private HorizontalAlignment horizontalAlignment;
	private DRIValueFormatter<?, ? super T> valueFormatter;
	private Boolean stretchWithOverflow;
	private List<DRICrosstabCellStyle> styles;
	private DRIExpression<?> titleExpression;
	private DRStyle titleStyle;

	public DRCrosstabMeasureVariableCell(DRIExpression<?> valueExpression, Calculation calculation) {
		Validate.notNull(valueExpression, "valueExpression must not be null");
		Validate.notNull(calculation, "calculation must not be null");
		this.valueExpression = valueExpression;
		this.calculation = calculation;
		this.name = ReportUtils.generateUniqueName("crosstabMeasure");
		this.styles = new ArrayList<DRICrosstabCellStyle>();
	}

	public String getName() {
		return name;
	}

	public DRIExpression<?> getValueExpression() {
		return valueExpression;
	}

	public DRISimpleExpression<?> getCellExpression() {
		return null;
	}

	public DRIDataType<? super T, T> getDataType() {
		return dataType;
	}

	public void setDataType(DRIDataType<? super T, T> dataType) {
		this.dataType = dataType;
	}

	public Calculation getCalculation() {
		return calculation;
	}

	public CrosstabPercentageType getPercentageType() {
		return percentageType;
	}

	public void setPercentageType(CrosstabPercentageType percentageType) {
		this.percentageType = percentageType;
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

	public Boolean getStretchWithOverflow() {
		return stretchWithOverflow;
	}

	public void setStretchWithOverflow(Boolean stretchWithOverflow) {
		this.stretchWithOverflow = stretchWithOverflow;
	}

	public List<DRICrosstabCellStyle> getStyles() {
		return styles;
	}

	public void setStyle(List<DRICrosstabCellStyle> styles) {
		this.styles = styles;
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

	@SuppressWarnings("unchecked")
	public Class<? super T> getValueClass() {
		return (Class<? super T>) ReportUtils.getVariableValueClass(getCalculation(), valueExpression.getValueClass());
	}
}