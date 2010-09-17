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

package net.sf.dynamicreports.design.transformation;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.style.DRBorder;
import net.sf.dynamicreports.report.base.style.DRConditionalStyle;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.base.style.DRPadding;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.definition.DRIColumn;
import net.sf.dynamicreports.report.definition.DRIValueColumn;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.style.DRIConditionalStyle;
import net.sf.dynamicreports.report.definition.style.DRISimpleStyle;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ColumnTransform {
	private DesignTransformAccessor accessor;
	
	public ColumnTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
	}
	
	//columns
	public void transform() throws DRException {
		boolean showColumnTitle = accessor.getTemplateTransform().isShowColumnTitle();
		boolean showColumnTitleForGroup = accessor.getBandTransform().getColumnHeaderForGroupBand() != null;
		
		ColumnGrid columnTitle = null;
		if (showColumnTitle) {
			columnTitle = accessor.getColumnGridTransform().createColumnGrid(accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN_TITLE));
		}
		ColumnGrid columnTitleForGroup = null;
		if (showColumnTitleForGroup) {
			columnTitleForGroup = accessor.getColumnGridTransform().createColumnGrid(accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN_TITLE));
		}
		ColumnGrid detail = accessor.getColumnGridTransform().createColumnGrid();
		
		for (DRIColumn<?> column : accessor.getReport().getColumns()) {
			if (!accessor.getGroupTransform().getHideGroupColumns().contains(column)) {
				if (column.getTitleExpression() != null) {
					if (showColumnTitle) {
						columnTitle.addComponent(column, titleComponent(column));
					}
					if (showColumnTitleForGroup) {
						columnTitleForGroup.addComponent(column, titleComponent(column));
					}
				}
				DRDesignComponent detailComponent = null;
				if (column instanceof DRIValueColumn<?>) {
					detailComponent = detailValueComponent((DRIValueColumn<?>) column);
				}
				else {
					detailComponent = detailComponent(column);
				}
				detail.addComponent(column, detailComponent);
			}
		}		
		
		if (showColumnTitle && !columnTitle.isEmpty()) {
			accessor.getBandTransform().getColumnHeaderBand().addComponent(0, columnTitle.getList());
		}		
		if (showColumnTitleForGroup && !columnTitleForGroup.isEmpty()) {
			accessor.getBandTransform().getColumnHeaderForGroupBand().addComponent(0, columnTitleForGroup.getList());
		}
		accessor.getBandTransform().getDetailBand().addComponent(detail.getList());
	}
	
	//title
	@SuppressWarnings("unchecked")
	private DRDesignComponent titleComponent(DRIColumn<?> column) throws DRException {
		@SuppressWarnings("rawtypes")
		DRTextField titleField = new DRTextField();
		titleField.setValueExpression(column.getTitleExpression());
		titleField.setStyle((DRStyle) column.getTitleStyle());
		titleField.setWidth(accessor.getTemplateTransform().getColumnWidth(column, accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
		titleField.setHeight(column.getTitleHeight());
		titleField.setHeightType(column.getTitleHeightType());
		titleField.setRows(column.getTitleRows());
		DRDesignTextField designTitleField = accessor.getComponentTransform().textField(titleField, DefaultStyleType.COLUMN_TITLE);
		designTitleField.setUniqueName("column_" + column.getName() + ".title");		
		return designTitleField;	
	}
	
	//detail
	private DRDesignComponent detailValueComponent(DRIValueColumn<?> column) throws DRException {
		DRDesignComponent detailComponent = detailComponent(column);
		((DRDesignTextField) detailComponent).setPrintRepeatedValues(accessor.getTemplateTransform().isColumnPrintRepeatedDetailValues(column));
		return detailComponent;
	}
	
	private DRDesignComponent detailComponent(DRIColumn<?> column) throws DRException {
		DRDesignComponent designComponent = accessor.getComponentTransform().component(column.getComponent(), DefaultStyleType.COLUMN, null, null);	
		designComponent.setUniqueName("column_" + column.getName());		

		List<DRIConditionalStyle> rowHighlighters = new ArrayList<DRIConditionalStyle>();
		rowHighlighters.addAll(getDetailRowHighlighters());
		DRISimpleStyle detailOddRowStyle = accessor.getTemplateTransform().getDetailOddRowStyle();
		if (detailOddRowStyle != null) {			
			rowHighlighters.add(detailRowConditionalStyle(detailOddRowStyle, Expressions.printInOddRow()));
		}
		DRISimpleStyle detailEvenRowStyle = accessor.getTemplateTransform().getDetailEvenRowStyle();
		if (detailEvenRowStyle != null) {			
			rowHighlighters.add(detailRowConditionalStyle(detailEvenRowStyle, Expressions.printInEvenRow()));
		}
		if (!rowHighlighters.isEmpty()) {
			DRIStyle style = column.getComponent().getStyle();
			if (style == null) {
				style = accessor.getTemplateTransform().getColumnStyle();
			}
			DRStyle newStyle = new DRStyle();
			newStyle.setParentStyle((DRStyle) style);			
			for (DRIConditionalStyle conditionalStyle : style.getConditionalStyles()) {
				newStyle.addConditionalStyle((DRConditionalStyle) conditionalStyle);
			}
			for (DRIConditionalStyle conditionalStyle : rowHighlighters) {
				newStyle.addConditionalStyle((DRConditionalStyle) conditionalStyle);
			}			
			designComponent.setStyle(accessor.getStyleTransform().transformStyle(newStyle, true, DefaultStyleType.COLUMN));
		}
		
		return designComponent;
	}
	
	private List<? extends DRIConditionalStyle> getDetailRowHighlighters() {
		return accessor.getReport().getDetailRowHighlighters();
	}
	
	private DRConditionalStyle detailRowConditionalStyle(DRISimpleStyle style, DRISimpleExpression<Boolean> expression) {
		DRConditionalStyle conditionalStyle = new DRConditionalStyle(expression);
		conditionalStyle.setBackgroundColor(style.getBackgroundColor());
		conditionalStyle.setBorder((DRBorder) style.getBorder());
		conditionalStyle.setFont((DRFont) style.getFont());
		conditionalStyle.setForegroundColor(style.getForegroundColor());
		conditionalStyle.setHorizontalAlignment(style.getHorizontalAlignment());
		conditionalStyle.setImageScale(style.getImageScale());
		conditionalStyle.setPadding((DRPadding) style.getPadding());
		conditionalStyle.setPattern(style.getPattern());
		conditionalStyle.setRadius(style.getRadius());
		conditionalStyle.setRotation(style.getRotation());
		conditionalStyle.setVerticalAlignment(style.getVerticalAlignment());
		return conditionalStyle;
	}
}
