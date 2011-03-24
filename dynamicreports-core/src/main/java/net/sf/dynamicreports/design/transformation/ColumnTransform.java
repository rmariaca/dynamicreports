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

package net.sf.dynamicreports.design.transformation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.base.component.DRComponent;
import net.sf.dynamicreports.report.base.component.DRFiller;
import net.sf.dynamicreports.report.base.component.DRImage;
import net.sf.dynamicreports.report.base.component.DRList;
import net.sf.dynamicreports.report.base.component.DRListCell;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.base.style.DRConditionalStyle;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.constant.StretchType;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.column.DRIBooleanColumn;
import net.sf.dynamicreports.report.definition.column.DRIColumn;
import net.sf.dynamicreports.report.definition.column.DRIValueColumn;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.style.DRIConditionalStyle;
import net.sf.dynamicreports.report.definition.style.DRISimpleStyle;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.renderers.BatikRenderer;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ColumnTransform {
	private DesignTransformAccessor accessor;
	private Map<DRIColumn<?>, DRIComponent> columnComponents;

	public ColumnTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
	}

	//columns
	public void transform() throws DRException {
		columnComponents = getColumnComponents();
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
				else if (column instanceof DRIBooleanColumn) {
					detailComponent = detailBooleanComponent((DRIBooleanColumn) column);
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

	private Map<DRIColumn<?>, DRIComponent> getColumnComponents() throws DRException {
		Map<DRIColumn<?>, DRIComponent> columnComponents = new HashMap<DRIColumn<?>, DRIComponent>();
		for (DRIColumn<?> column : accessor.getReport().getColumns()) {
			if (!accessor.getGroupTransform().getHideGroupColumns().contains(column)) {
				DRIComponent component = column.getComponent();
				if (column instanceof DRIBooleanColumn) {
					component = createBooleanComponent((DRIBooleanColumn) column);
				}
				columnComponents.put(column, component);
			}
		}
		return columnComponents;
	}

	private DRIComponent createBooleanComponent(DRIBooleanColumn column) throws DRException {
		BooleanComponentType componentType = accessor.getTemplateTransform().getBooleanComponentType(column);
		DRComponent component = null;

		switch (componentType) {
		case TEXT_TRUE_FALSE:
		case TEXT_YES_NO:
			String keyTrue;
			String keyFalse;
			if (componentType.equals(BooleanComponentType.TEXT_TRUE_FALSE)) {
				keyTrue = "true";
				keyFalse = "false";
			}
			else {
				keyTrue = "yes";
				keyFalse = "no";
			}
			DRTextField<Boolean> textField = new DRTextField<Boolean>();
			textField.setValueExpression(column);
			textField.setDataType(DataTypes.booleanType());
			textField.setValueFormatter(new BooleanTextValueFormatter(keyTrue, keyFalse));
			textField.setWidth(column.getWidth());
			textField.setWidthType(column.getWidthType());
			textField.setHeight(column.getHeight());
			textField.setHeightType(column.getHeightType());
			component = textField;
			break;
		case IMAGE_STYLE_1:
		case IMAGE_STYLE_2:
		case IMAGE_STYLE_3:
		case IMAGE_STYLE_4:
		case IMAGE_STYLE_5:
		case IMAGE_STYLE_6:
		case IMAGE_STYLE_7:
			DRList hList = new DRList();
			DRFiller filler = new DRFiller();
			filler.setWidth(1);
			filler.setHeight(column.getHeight());
			filler.setHeightType(column.getHeightType());
			hList.addComponent(filler);

			DRImage image = new DRImage();
			image.setWidth(accessor.getTemplateTransform().getBooleanImageWidth(column));
			image.setHeight(accessor.getTemplateTransform().getBooleanImageHeight(column));
			image.setWidthType(ComponentDimensionType.FIXED);
			image.setHeightType(ComponentDimensionType.FIXED);
			image.setImageExpression(new BooleanImageExpression(column));
			DRListCell cell = new DRListCell(image);
			cell.setVerticalAlignment(VerticalCellComponentAlignment.MIDDLE);
			hList.addCell(cell);

			filler = new DRFiller();
			filler.setWidth(1);
			hList.addComponent(filler);

			DRList vList = new DRList(ListType.VERTICAL);
			filler = new DRFiller();
			filler.setWidth(accessor.getTemplateTransform().getBooleanImageColumnWidth(column));
			filler.setWidthType(column.getWidthType());
			vList.addComponent(filler);
			vList.addComponent(hList);
			vList.setStretchType(StretchType.RELATIVE_TO_TALLEST_OBJECT);
			component = vList;
			break;
		default:
			throw new DRDesignReportException("Boolean component type " + componentType.name() + " not supported");
		}

		component.setStyle((DRStyle) accessor.getTemplateTransform().getBooleanStyle(column));
		component.setPrintWhenExpression(column.getPrintWhenExpression());

		return component;
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

	private DRDesignComponent detailBooleanComponent(DRIBooleanColumn column) throws DRException {
		DRDesignComponent detailComponent = detailComponent(column);

		return detailComponent;
	}

	private DRDesignComponent detailComponent(DRIColumn<?> column) throws DRException {
		DRDesignComponent designComponent = accessor.getComponentTransform().component(getColumnComponent(column), DefaultStyleType.COLUMN, null, null);
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
			DRIStyle style = getColumnComponent(column).getStyle();
			if (style == null) {
				style = accessor.getTemplateTransform().getColumnStyle();
			}
			DRStyle newStyle = new DRStyle();
			newStyle.setParentStyle((DRStyle) style);
			List<DRIConditionalStyle> conditionalStyles = new ArrayList<DRIConditionalStyle>();
			for (DRIConditionalStyle conditionalStyle : style.getConditionalStyles()) {
				conditionalStyles.add(conditionalStyle);
			}
			for (DRIConditionalStyle conditionalStyle : rowHighlighters) {
				conditionalStyles.add(conditionalStyle);
			}
			Color backgroundColor = StyleResolver.getBackgroundColor(style);
			for (DRIConditionalStyle conditionalStyle : conditionalStyles) {
				if (backgroundColor != null) {
					DRConditionalStyle newConditionalStyle = new DRConditionalStyle(conditionalStyle.getConditionExpression());
					accessor.getStyleTransform().copyStyle(newConditionalStyle, conditionalStyle);
					Color mergedColor = StyleResolver.mergeColors(backgroundColor, conditionalStyle.getBackgroundColor(), 0.25f);
					newConditionalStyle.setBackgroundColor(mergedColor);
					newStyle.addConditionalStyle(newConditionalStyle);
				}
				else {
					newStyle.addConditionalStyle((DRConditionalStyle) conditionalStyle);
				}
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
		accessor.getStyleTransform().copyStyle(conditionalStyle, style);
		return conditionalStyle;
	}

	public DRIComponent getColumnComponent(DRIColumn<?> column) {
		return columnComponents.get(column);
	}

	private class BooleanTextValueFormatter extends AbstractValueFormatter<String, Boolean> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
		private String keyTrue;
		private String keyFalse;

		private BooleanTextValueFormatter(String keyTrue, String keyFalse) {
			this.keyTrue = keyTrue;
			this.keyFalse = keyFalse;
		}

		public String format(Boolean value, ReportParameters reportParameters) {
			String key;
			if (value != null && value) {
				key = keyTrue;
			} else {
				key = keyFalse;
			}
			return ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_NAME, reportParameters.getLocale()).getString(key);
		}
	}

	private class BooleanImageExpression extends AbstractComplexExpression<JRRenderable> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
		private JRRenderable imageTrue;
		private JRRenderable imageFalse;

		private BooleanImageExpression(DRIBooleanColumn column) throws DRException {
			addExpression(column);
			String fileNameTrue = column.getComponentType().name().toLowerCase();
			String fileNameFalse = column.getComponentType().name().toLowerCase();
			switch (column.getComponentType()) {
			case IMAGE_STYLE_5:
				fileNameFalse = BooleanComponentType.IMAGE_STYLE_4.name().toLowerCase();
				break;
			case IMAGE_STYLE_7:
				fileNameTrue = BooleanComponentType.IMAGE_STYLE_1.name().toLowerCase();
				break;
			}
			fileNameTrue = "boolean_" + fileNameTrue + "_true";
			fileNameFalse = "boolean_" + fileNameFalse + "_false";
			try {
				imageTrue = BatikRenderer.getInstance(ReportUtils.class.getResource("images/" + fileNameTrue + ".svg"));
				imageFalse = BatikRenderer.getInstance(ReportUtils.class.getResource("images/" + fileNameFalse + ".svg"));
			} catch (JRException e) {
				throw new DRException(e);
			}
		}

		@Override
		public JRRenderable evaluate(List<?> values, ReportParameters reportParameters) {
			Boolean value = (Boolean) values.get(0);
			if (value != null && value) {
				return imageTrue;
			} else {
				return imageFalse;
			}
		}
	}
}
