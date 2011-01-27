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

package net.sf.dynamicreports.report.builder.crosstab;

import net.sf.dynamicreports.report.base.crosstab.DRCrosstabMeasure;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.CrosstabPercentageType;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.component.DRITextField;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class CrosstabMeasureBuilder<T> extends AbstractBuilder<CrosstabMeasureBuilder<T>, DRCrosstabMeasure<T>> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected CrosstabMeasureBuilder(ValueColumnBuilder<?, ?> column, Calculation calculation) {
		super(new DRCrosstabMeasure<T>(column.build(), calculation));
		if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
			setDataType(DataTypes.longType());
		}
		else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) ||
				calculation.equals(Calculation.VARIANCE)) {
			setDataType(DataTypes.doubleType());
		}
		else {
			DRITextField<?> columnComponent = column.getColumn().getComponent();
			setDataType(columnComponent.getDataType());
			setPattern(columnComponent.getPattern());
		}
	}

	protected CrosstabMeasureBuilder(FieldBuilder<?> field, Calculation calculation) {
		super(new DRCrosstabMeasure<T>(field.getField(), calculation));
		if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
			setDataType(DataTypes.longType());
		}
		else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) ||
				calculation.equals(Calculation.VARIANCE)) {
			setDataType(DataTypes.doubleType());
		}
		else {
			setDataType(field.getField().getDataType());
		}
	}

	protected CrosstabMeasureBuilder(DRISimpleExpression<?> expression, Calculation calculation) {
		super(new DRCrosstabMeasure<T>(expression, calculation));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CrosstabMeasureBuilder<T> setDataType(DRIDataType dataType) {
		getObject().setDataType(dataType);
		return this;
	}

	public CrosstabMeasureBuilder<T> setPercentageType(CrosstabPercentageType percentageType) {
		getObject().setPercentageType(percentageType);
		return this;
	}

	public CrosstabMeasureBuilder<T> setPattern(String pattern) {
		getObject().setPattern(pattern);
		return this;
	}

	public CrosstabMeasureBuilder<T> setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		getObject().setHorizontalAlignment(horizontalAlignment);
		return this;
	}

	public CrosstabMeasureBuilder<T> setValueFormatter(DRIValueFormatter<?, ? super T> valueFormatter) {
		getObject().setValueFormatter(valueFormatter);
		return this;
	}

	public CrosstabMeasureBuilder<T> setStretchWithOverflow(Boolean stretchWithOverflow) {
		getObject().setStretchWithOverflow(stretchWithOverflow);
		return this;
	}

	public CrosstabMeasureBuilder<T> setStyle(StyleBuilder style) {
		if (style != null) {
			getObject().setStyle(style.getStyle());
		}
		else {
			getObject().setStyle(null);
		}
		return this;
	}
}
