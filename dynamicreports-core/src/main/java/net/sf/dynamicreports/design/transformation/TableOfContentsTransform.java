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

package net.sf.dynamicreports.design.transformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignTableOfContentsHeading;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRICustomValues;
import net.sf.dynamicreports.report.definition.DRIGroup;
import net.sf.dynamicreports.report.definition.DRITableOfContentsHeading;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.component.DRITextField;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class TableOfContentsTransform {
	private DesignTransformAccessor accessor;
	private Map<DRITableOfContentsHeading, Integer> levels;

	public TableOfContentsTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
		this.levels = new HashMap<DRITableOfContentsHeading, Integer>();
	}

	protected DRDesignTableOfContentsHeading componentHeading(DRIComponent component) throws DRException {
		DRITableOfContentsHeading tocHeading = component.getTableOfContentsHeading();
		boolean tableOfContents = accessor.isTableOfContents();
		if (tableOfContents && tocHeading != null) {
			DRTextField<String> referenceField = new DRTextField<String>();
			int level = getLevel(tocHeading);
			DRIExpression<?> labelExpression = tocHeading.getLabelExpression();
			if (labelExpression == null && component instanceof DRITextField) {
				labelExpression = ((DRITextField<?>) component).getValueExpression();
			}
			if (labelExpression == null) {
				labelExpression = Expressions.text("");
			}
			String expressionName = labelExpression.getName();
			referenceField.setValueExpression(new TocReferenceExpression(level, expressionName, labelExpression));
			referenceField.setAnchorNameExpression(new TocReferenceLinkExpression(expressionName));
			DRDesignTextField designReferenceField = accessor.getComponentTransform().textField(referenceField, DefaultStyleType.TEXT);
			designReferenceField.setWidth(1);
			designReferenceField.setHeight(1);
			designReferenceField.setUniqueName(expressionName + ".tocReference");

			DRDesignTableOfContentsHeading designTocHeading = new DRDesignTableOfContentsHeading();
			designTocHeading.setReferenceField(designReferenceField);
			return designTocHeading;
		}

		return null;
	}

	private int getLevel(DRITableOfContentsHeading tocHeading) {
		if (levels.containsKey(tocHeading)) {
			return levels.get(tocHeading);
		}
		int level = 0;
		if (tocHeading.getParentHeading() != null) {
			level = getLevel(tocHeading.getParentHeading()) + 1;
		}
		levels.put(tocHeading, level);
		return level;
	}

	protected DRDesignTableOfContentsHeading groupHeading(DRIGroup group, int level) throws DRException {
		boolean tableOfContents = accessor.isTableOfContents();
		if (tableOfContents) {
			DRTextField<String> referenceField = new DRTextField<String>();
			referenceField.setValueExpression(new TocReferenceExpression(level, group.getName(), group.getValueField().getValueExpression()));
			referenceField.setAnchorNameExpression(new TocReferenceLinkExpression(group.getName()));
			DRDesignTextField designReferenceField = accessor.getComponentTransform().textField(referenceField, DefaultStyleType.TEXT);
			designReferenceField.setWidth(0);
			designReferenceField.setHeight(0);
			designReferenceField.setUniqueName("group_" + group.getName() + ".tocReference");

			DRDesignTableOfContentsHeading designTocHeading = new DRDesignTableOfContentsHeading();
			designTocHeading.setReferenceField(designReferenceField);
			return designTocHeading;
		}

		return null;
	}

	private class TocReferenceExpression extends AbstractComplexExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		private int level;
		private String expressionName;

		private TocReferenceExpression(int level, String expressionName, DRIExpression<?> labelExpression) {
			this.level = level;
			this.expressionName = expressionName;
			addExpression(labelExpression);
		}

		@Override
		public String evaluate(List<?> values, ReportParameters reportParameters) {
			String id = expressionName + "_" + reportParameters.getReportRowNumber();
			DRICustomValues customValues = (DRICustomValues) reportParameters.getParameterValue(DRICustomValues.NAME);
			String text = String.valueOf(values.get(0));
			customValues.addTocHeading(level, id, text);
			return null;
		}
	}

	private class TocReferenceLinkExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		private String expressionName;

		private TocReferenceLinkExpression(String expressionName) {
			this.expressionName = expressionName;
		}

		public String evaluate(ReportParameters reportParameters) {
			String id = expressionName + "_" + reportParameters.getReportRowNumber();
			return id;
		}
	}
}
