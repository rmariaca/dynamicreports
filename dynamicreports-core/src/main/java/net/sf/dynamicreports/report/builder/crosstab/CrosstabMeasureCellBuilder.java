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

import net.sf.dynamicreports.report.base.crosstab.DRCrosstabCellStyle;
import net.sf.dynamicreports.report.base.crosstab.DRCrosstabMeasureCell;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class CrosstabMeasureCellBuilder<T> extends CrosstabMeasureBuilder<CrosstabMeasureCellBuilder<T>, DRCrosstabMeasureCell<T>> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected CrosstabMeasureCellBuilder(DRISimpleExpression<?> expression) {
		super(new DRCrosstabMeasureCell<T>(expression));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CrosstabMeasureCellBuilder<T> setDataType(DRIDataType dataType) {
		getObject().setDataType(dataType);
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setPattern(String pattern) {
		getObject().setPattern(pattern);
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		getObject().setHorizontalAlignment(horizontalAlignment);
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setValueFormatter(DRIValueFormatter<?, ? super T> valueFormatter) {
		getObject().setValueFormatter(valueFormatter);
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setStretchWithOverflow(Boolean stretchWithOverflow) {
		getObject().setStretchWithOverflow(stretchWithOverflow);
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setStyle(StyleBuilder style) {
		Validate.notNull(style, "style must not be null");
		getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle()));
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setStyle(StyleBuilder style, CrosstabRowGroupBuilder<?> rowGroup) {
		Validate.notNull(style, "style must not be null");
		Validate.notNull(rowGroup, "rowGroup must not be null");
		getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), rowGroup.build(), null));
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setStyle(StyleBuilder style, CrosstabColumnGroupBuilder<?> columnGroup) {
		Validate.notNull(style, "style must not be null");
		Validate.notNull(columnGroup, "columnGroup must not be null");
		getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), null, columnGroup.build()));
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setStyle(StyleBuilder style, CrosstabRowGroupBuilder<?> rowGroup, CrosstabColumnGroupBuilder<?> columnGroup) {
		Validate.notNull(style, "style must not be null");
		Validate.notNull(rowGroup, "rowGroup must not be null");
		Validate.notNull(columnGroup, "columnGroup must not be null");
		getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), rowGroup.build(), columnGroup.build()));
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setTitle(DRISimpleExpression<?> titleExpression) {
		getObject().setTitleExpression(titleExpression);
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setTitle(String title) {
		getObject().setTitleExpression(Expressions.text(title));
		return this;
	}

	public CrosstabMeasureCellBuilder<T> setTitleStyle(StyleBuilder titleStyle) {
		if (titleStyle != null) {
			getObject().setTitleStyle(titleStyle.getStyle());
		}
		else {
			getObject().setTitleStyle(null);
		}
		return this;
	}

	public String getName() {
		return build().getName();
	}
}
