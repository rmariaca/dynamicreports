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

package net.sf.dynamicreports.report.builder;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import net.sf.dynamicreports.report.base.DRGroup;
import net.sf.dynamicreports.report.base.DRReport;
import net.sf.dynamicreports.report.base.grid.DRColumnGrid;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnGridComponentBuilder;
import net.sf.dynamicreports.report.builder.group.GroupBuilder;
import net.sf.dynamicreports.report.builder.group.Groups;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.builder.style.SimpleStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.PercentageSubtotalBuilder;
import net.sf.dynamicreports.report.builder.subtotal.SubtotalBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.QueryLanguage;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.SubtotalPosition;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.definition.DRIScriptlet;
import net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("unchecked")
public class ReportBuilder<T extends ReportBuilder<T>> extends AbstractBuilder<T, DRReport> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	public ReportBuilder() {
		super(new DRReport());
	}

	public T setLocale(Locale locale) {
		getObject().setLocale(locale);
		return (T) this;
	}

	public T setResourceBundle(ResourceBundle resourceBundle) {
		getObject().setResourceBundle(resourceBundle);
		return (T) this;
	}

	public T setResourceBundle(String resourceBundleName) {
		getObject().setResourceBundleName(resourceBundleName);
		return (T) this;
	}

	public T setShowColumnTitle(Boolean showColumnTitle) {
		getObject().setShowColumnTitle(showColumnTitle);
		return (T) this;
	}

	public T setPageFormat(PageType pageType) {
		return setPageFormat(pageType, PageOrientation.PORTRAIT);
	}

	public T setPageFormat(PageType pageType, PageOrientation orientation) {
		getObject().getPage().setPageFormat(pageType, orientation);
		return (T) this;
	}

	public T setPageFormat(Integer width, Integer height, PageOrientation orientation) {
		getObject().getPage().setPageFormat(width, height, orientation);
		return (T) this;
	}

	public T setPageMargin(MarginBuilder margin) {
		Validate.notNull(margin, "margin must not be null");
		getObject().getPage().setMargin(margin.build());
		return (T) this;
	}

	public T setPageColumnsPerPage(Integer columnsPerPage) {
		getObject().getPage().setColumnsPerPage(columnsPerPage);
		return (T) this;
	}

	public T setPageColumnSpace(Integer columnSpace) {
		getObject().getPage().setColumnSpace(columnSpace);
		return (T) this;
	}

	public T ignorePagination() {
		return setIgnorePagination(true);
	}

	public T setIgnorePagination(Boolean ignorePagination) {
		getObject().setIgnorePagination(ignorePagination);
		return (T) this;
	}

	public T setWhenNoDataType(WhenNoDataType whenNoDataType) {
		getObject().setWhenNoDataType(whenNoDataType);
		return (T) this;
	}

	public T titleOnANewPage() {
		return setTitleOnANewPage(true);
	}

	public T setTitleOnANewPage(Boolean titleOnANewPage) {
		getObject().setTitleOnANewPage(titleOnANewPage);
		return (T) this;
	}

	public T summaryOnANewPage() {
		return setSummaryOnANewPage(true);
	}

	public T setSummaryOnANewPage(Boolean summaryOnANewPage) {
		getObject().setSummaryOnANewPage(summaryOnANewPage);
		return (T) this;
	}

	public T summaryWithPageHeaderAndFooter() {
		return setSummaryWithPageHeaderAndFooter(true);
	}

	public T setSummaryWithPageHeaderAndFooter(Boolean summaryWithPageHeaderAndFooter) {
		getObject().setSummaryWithPageHeaderAndFooter(summaryWithPageHeaderAndFooter);
		return (T) this;
	}

	public T floatColumnFooter() {
		return setFloatColumnFooter(true);
	}

	public T setFloatColumnFooter(Boolean floatColumnFooter) {
		getObject().setFloatColumnFooter(floatColumnFooter);
		return (T) this;
	}

	public T setUseFieldNameAsDescription(Boolean useFieldNameAsDescription) {
		getObject().setUseFieldNameAsDescription(useFieldNameAsDescription);
		return (T) this;
	}

	public T scriptlets(DRIScriptlet ...scriptlets) {
		return addScriptlet(scriptlets);
	}

	public T addScriptlet(DRIScriptlet ...scriptlets) {
		Validate.notNull(scriptlets, "scriptlets must not be null");
		Validate.noNullElements(scriptlets, "scriptlets must not contains null scriptlet");
		for (DRIScriptlet scriptlet : scriptlets) {
			getObject().addScriptlet(scriptlet);
		}
		return (T) this;
	}

	public T setProperties(Properties properties) {
		getObject().setProperties(properties);
		return (T) this;
	}

	public T addProperty(String key, String value) {
		getObject().addProperty(key, value);
		return (T) this;
	}

	public T setQuery(String text, String language) {
		Validate.notNull(text, "text must not be null");
		Validate.notNull(language, "language must not be null");
		return setQuery(DynamicReports.query(text, language));
	}

	public T setQuery(String sql) {
		Validate.notNull(sql, "sql must not be null");
		return setQuery(DynamicReports.query(sql, QueryLanguage.SQL));
	}

	public T setQuery(QueryBuilder query) {
		Validate.notNull(query, "query must not be null");
		getObject().setQuery(query.build());
		return (T) this;
	}

	public T columnGrid(ListType type) {
		getObject().setColumnGrid(new DRColumnGrid(type));
		return (T) this;
	}

	public T columnGrid(ColumnGridComponentBuilder ...components) {
		return columnGrid(ListType.HORIZONTAL, components);
	}

	public T columnGrid(ListType type, ColumnGridComponentBuilder ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		DRColumnGrid columnGrid = new DRColumnGrid(type);
		for (ColumnGridComponentBuilder component : components) {
			columnGrid.addComponent(component.build());
		}
		getObject().setColumnGrid(columnGrid);
		return (T) this;
	}

	public T setTemplate(ReportTemplateBuilder template) {
		Validate.notNull(template, "template must not be null");
		getObject().setTemplate(template.build());
		return (T) this;
	}

	//parameter
	public T parameters(ParameterBuilder<?> ...parameters) {
		return addParameter(parameters);
	}

	public T addParameter(String name, Object value) {
		return addParameter(DynamicReports.parameter(name, value));
	}

	public T addParameter(String name, Class<?> valueClass) {
		return addParameter(DynamicReports.parameter(name, valueClass));
	}

	public T addParameter(ParameterBuilder<?> ...parameters) {
		Validate.notNull(parameters, "parameters must not be null");
		Validate.noNullElements(parameters, "parameters must not contains null parameter");
		for (ParameterBuilder<?> parameter : parameters) {
			getObject().addParameter(parameter.build());
		}
		return (T) this;
	}

	//field
	public T fields(FieldBuilder<?> ...fields) {
		return addField(fields);
	}

	public T addField(String name, Class<?> valueClass) {
		return addField(DynamicReports.field(name, valueClass));
	}

	public <U> T addField(String name, DRIDataType<? super U, U> dataType) {
		return addField(DynamicReports.field(name, dataType));
	}

	public T addField(FieldBuilder<?> ...fields) {
		Validate.notNull(fields, "fields must not be null");
		Validate.noNullElements(fields, "fields must not contains null field");
		for (FieldBuilder<?> field : fields) {
			getObject().addField(field.build());
		}
		return (T) this;
	}

	//variable
	public T variables(VariableBuilder<?> ...variables) {
		return addVariable(variables);
	}

	public T addVariable(VariableBuilder<?> ...variables) {
		Validate.notNull(variables, "variables must not be null");
		Validate.noNullElements(variables, "variables must not contains null variable");
		for (VariableBuilder<?> variable : variables) {
			getObject().addVariable(variable.getVariable());
		}
		return (T) this;
	}

	//column
	public T columns(ColumnBuilder<?, ?> ...columns) {
		return addColumn(columns);
	}

	public T addColumn(ColumnBuilder<?, ?> ...columns) {
		Validate.notNull(columns, "columns must not be null");
		Validate.noNullElements(columns, "columns must not contains null column");
		for (ColumnBuilder<?, ?> column : columns) {
			getObject().addColumn(column.build());
		}
		return (T) this;
	}

	//style
	public T setTextStyle(StyleBuilder textStyle) {
		if (textStyle != null) {
			getObject().setTextStyle(textStyle.build());
		}
		else {
			getObject().setTextStyle(null);
		}
		return (T) this;
	}

	public T setColumnTitleStyle(StyleBuilder columnTitleStyle) {
		if (columnTitleStyle != null) {
			getObject().setColumnTitleStyle(columnTitleStyle.build());
		}
		else {
			getObject().setColumnTitleStyle(null);
		}
		return (T) this;
	}

	public T setColumnStyle(StyleBuilder columnStyle) {
		if (columnStyle != null) {
			getObject().setColumnStyle(columnStyle.build());
		}
		else {
			getObject().setColumnStyle(null);
		}
		return (T) this;
	}

	public T setGroupTitleStyle(StyleBuilder groupTitleStyle) {
		if (groupTitleStyle != null) {
			getObject().setGroupTitleStyle(groupTitleStyle.build());
		}
		else {
			getObject().setGroupTitleStyle(null);
		}
		return (T) this;
	}

	public T setGroupStyle(StyleBuilder groupStyle) {
		if (groupStyle != null) {
			getObject().setGroupStyle(groupStyle.build());
		}
		else {
			getObject().setGroupStyle(null);
		}
		return (T) this;
	}

	public T setSubtotalStyle(StyleBuilder subtotalStyle) {
		if (subtotalStyle != null) {
			getObject().setSubtotalStyle(subtotalStyle.build());
		}
		else {
			getObject().setSubtotalStyle(null);
		}
		return (T) this;
	}

	public T setImageStyle(StyleBuilder imageStyle) {
		if (imageStyle != null) {
			getObject().setImageStyle(imageStyle.build());
		}
		else {
			getObject().setImageStyle(null);
		}
		return (T) this;
	}

	public T setChartStyle(StyleBuilder chartStyle) {
		if (chartStyle != null) {
			getObject().setChartStyle(chartStyle.build());
		}
		else {
			getObject().setChartStyle(null);
		}
		return (T) this;
	}

	public T setBarcodeStyle(StyleBuilder barcodeStyle) {
		if (barcodeStyle != null) {
			getObject().setBarcodeStyle(barcodeStyle.build());
		}
		else {
			getObject().setBarcodeStyle(null);
		}
		return (T) this;
	}

	//row highlighter
	public T highlightDetailOddRows() {
		return setHighlightDetailOddRows(true);
	}

	public T setHighlightDetailOddRows(Boolean highlightDetailOddRows) {
		getObject().setHighlightDetailOddRows(highlightDetailOddRows);
		return (T) this;
	}

	public T setDetailOddRowStyle(SimpleStyleBuilder detailOddRowStyle) {
		if (detailOddRowStyle != null) {
			getObject().setDetailOddRowStyle(detailOddRowStyle.build());
		}
		else {
			getObject().setDetailOddRowStyle(null);
		}
		return (T) this;
	}

	public T highlightDetailEvenRows() {
		return setHighlightDetailEvenRows(true);
	}

	public T setHighlightDetailEvenRows(Boolean highlightDetailEvenRows) {
		getObject().setHighlightDetailEvenRows(highlightDetailEvenRows);
		return (T) this;
	}

	public T setDetailEvenRowStyle(SimpleStyleBuilder detailEvenRowStyle) {
		if (detailEvenRowStyle != null) {
			getObject().setDetailEvenRowStyle(detailEvenRowStyle.build());
		}
		else {
			getObject().setDetailEvenRowStyle(null);
		}
		return (T) this;
	}

	public T detailRowHighlighters(ConditionalStyleBuilder ...detailRowHighlighters) {
		return addDetailRowHighlighter(detailRowHighlighters);
	}

	public T addDetailRowHighlighter(ConditionalStyleBuilder ...detailRowHighlighters) {
		Validate.notNull(detailRowHighlighters, "detailRowHighlighters must not be null");
		Validate.noNullElements(detailRowHighlighters, "detailRowHighlighters must not contains null detailRowHighlighter");
		for (ConditionalStyleBuilder conditionalStyleBuilder : detailRowHighlighters) {
			getObject().addDetailRowHighlighter(conditionalStyleBuilder.build());
		}
		return (T) this;
	}

	//subtotal
	public T subtotalsAtTitle(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtTitle(subtotals);
	}

	public T addSubtotalAtTitle(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.TITLE).build());
		}
		return (T) this;
	}

	public T subtotalsAtPageHeader(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtPageHeader(subtotals);
	}

	public T addSubtotalAtPageHeader(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.PAGE_HEADER).build());
		}
		return (T) this;
	}

	public T subtotalsAtPageFooter(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtPageFooter(subtotals);
	}

	public T addSubtotalAtPageFooter(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.PAGE_FOOTER).build());
		}
		return (T) this;
	}

	public T subtotalsAtColumnHeader(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtColumnHeader(subtotals);
	}

	public T addSubtotalAtColumnHeader(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.COLUMN_HEADER).build());
		}
		return (T) this;
	}

	public T subtotalsAtColumnFooter(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtColumnFooter(subtotals);
	}

	public T addSubtotalAtColumnFooter(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.COLUMN_FOOTER).build());
		}
		return (T) this;
	}

	public T subtotalsAtGroupHeader(GroupBuilder<?> group, SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtGroupHeader(group, subtotals);
	}

	public T addSubtotalAtGroupHeader(GroupBuilder<?> group, SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(group, "group must not be null");
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.GROUP_HEADER).setGroup(group).build());
		}
		return (T) this;
	}

	public T subtotalsAtGroupFooter(GroupBuilder<?> group, SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtGroupFooter(group, subtotals);
	}

	public T addSubtotalAtGroupFooter(GroupBuilder<?> group, SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(group, "group must not be null");
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.GROUP_FOOTER).setGroup(group).build());
		}
		return (T) this;
	}

	public T subtotalsAtFirstGroupHeader(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtFirstGroupHeader(subtotals);
	}

	public T addSubtotalAtFirstGroupHeader(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.FIRST_GROUP_HEADER).build());
		}
		return (T) this;
	}

	public T subtotalsAtFirstGroupFooter(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtFirstGroupFooter(subtotals);
	}

	public T addSubtotalAtFirstGroupFooter(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.FIRST_GROUP_FOOTER).build());
		}
		return (T) this;
	}

	public T subtotalsAtLastGroupHeader(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtLastGroupHeader(subtotals);
	}

	public T addSubtotalAtLastGroupHeader(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.LAST_GROUP_HEADER).build());
		}
		return (T) this;
	}

	public T subtotalsAtLastGroupFooter(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtLastGroupFooter(subtotals);
	}

	public T addSubtotalAtLastGroupFooter(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.LAST_GROUP_FOOTER).build());
		}
		return (T) this;
	}

	public T subtotalsAtLastPageFooter(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtLastPageFooter(subtotals);
	}

	public T addSubtotalAtLastPageFooter(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.LAST_PAGE_FOOTER).build());
		}
		return (T) this;
	}

	public T subtotalsAtSummary(SubtotalBuilder<?, ?> ...subtotals) {
		return addSubtotalAtSummary(subtotals);
	}

	public T addSubtotalAtSummary(SubtotalBuilder<?, ?> ...subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (SubtotalBuilder<?, ?> subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.SUMMARY).build());
		}
		return (T) this;
	}

	public T subtotalsOfPercentageAtGroupHeader(GroupBuilder<?> group, PercentageSubtotalBuilder ...subtotals) {
		return addSubtotalOfPercentageAtGroupHeader(group, subtotals);
	}

	public T addSubtotalOfPercentageAtGroupHeader(GroupBuilder<?> group, PercentageSubtotalBuilder ...subtotals) {
		Validate.notNull(group, "group must not be null");
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (PercentageSubtotalBuilder subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.GROUP_HEADER).setGroup(group).build());
		}
		return (T) this;
	}

	public T subtotalsOfPercentageAtGroupFooter(GroupBuilder<?> group, PercentageSubtotalBuilder ...subtotals) {
		return addSubtotalOfPercentageAtGroupFooter(group, subtotals);
	}

	public T addSubtotalOfPercentageAtGroupFooter(GroupBuilder<?> group, PercentageSubtotalBuilder ...subtotals) {
		Validate.notNull(group, "group must not be null");
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for (PercentageSubtotalBuilder subtotal : subtotals) {
			getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.GROUP_FOOTER).setGroup(group).build());
		}
		return (T) this;
	}

	//group
	public T groupBy(ValueColumnBuilder<?, ?> ...groupColumns) {
		Validate.notNull(groupColumns, "groupColumns must not be null");
		Validate.noNullElements(groupColumns, "groupColumns must not contains null groupColumn");
		for (ValueColumnBuilder<?, ?> groupColumn : groupColumns) {
			addGroup(Groups.group(groupColumn));
		}
		return (T) this;
	}

	public T groupBy(GroupBuilder<?> ...groups) {
		return addGroup(groups);
	}

	public T addGroup(GroupBuilder<?> ...groups) {
		Validate.notNull(groups, "groups must not be null");
		Validate.noNullElements(groups, "groups must not contains null group");
		for (GroupBuilder<?> group : groups) {
			getObject().addGroup(group.build());
		}
		return (T) this;
	}

	//table of contents
	public T tableOfContents() {
		return setTableOfContents(true);
	}

	public T tableOfContents(TableOfContentsCustomizerBuilder tableOfContentsCustomizer) {
		return setTableOfContents(tableOfContentsCustomizer);
	}

	public T tableOfContents(DRITableOfContentsCustomizer tableOfContentsCustomizer) {
		return setTableOfContents(tableOfContentsCustomizer);
	}

	public T setTableOfContents(Boolean tableOfContents) {
		getObject().setTableOfContents(tableOfContents);
		return (T) this;
	}

	public T setTableOfContents(TableOfContentsCustomizerBuilder tableOfContentsCustomizer) {
		getObject().setTableOfContentsCustomizer(tableOfContentsCustomizer.build());
		return setTableOfContents(true);
	}

	public T setTableOfContents(DRITableOfContentsCustomizer tableOfContentsCustomizer) {
		getObject().setTableOfContentsCustomizer(tableOfContentsCustomizer);
		return setTableOfContents(true);
	}

	//title band
	public T setTitleSplitType(SplitType splitType) {
		getObject().getTitleBand().setSplitType(splitType);
		return (T) this;
	}

	public T setTitleStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getTitleBand().getList().setStyle(style.build());
		}
		else {
			getObject().getTitleBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T title(ComponentBuilder<?, ?> ...components) {
		return addTitle(components);
	}

	public T addTitle(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getTitleBand().addComponent(component.build());
		}
		return (T) this;
	}

	//page header band
	public T setPageHeaderSplitType(SplitType splitType) {
		getObject().getPageHeaderBand().setSplitType(splitType);
		return (T) this;
	}

	public T setPageHeaderStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getPageHeaderBand().getList().setStyle(style.build());
		}
		else {
			getObject().getPageHeaderBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T pageHeader(ComponentBuilder<?, ?> ...components) {
		return addPageHeader(components);
	}

	public T addPageHeader(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getPageHeaderBand().addComponent(component.build());
		}
		return (T) this;
	}

	//page footer band
	public T setPageFooterSplitType(SplitType splitType) {
		getObject().getPageFooterBand().setSplitType(splitType);
		return (T) this;
	}

	public T setPageFooterStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getPageFooterBand().getList().setStyle(style.build());
		}
		else {
			getObject().getPageFooterBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T pageFooter(ComponentBuilder<?, ?> ...components) {
		return addPageFooter(components);
	}

	public T addPageFooter(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getPageFooterBand().addComponent(component.build());
		}
		return (T) this;
	}

	//column header band
	public T setColumnHeaderSplitType(SplitType splitType) {
		getObject().getColumnHeaderBand().setSplitType(splitType);
		return (T) this;
	}

	public T setColumnHeaderStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getColumnHeaderBand().getList().setStyle(style.build());
		}
		else {
			getObject().getColumnHeaderBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T columnHeader(ComponentBuilder<?, ?> ...components) {
		return addColumnHeader(components);
	}

	public T addColumnHeader(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getColumnHeaderBand().addComponent(component.build());
		}
		return (T) this;
	}

	//column footer band
	public T setColumnFooterSplitType(SplitType splitType) {
		getObject().getColumnFooterBand().setSplitType(splitType);
		return (T) this;
	}

	public T setColumnFooterStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getColumnFooterBand().getList().setStyle(style.build());
		}
		else {
			getObject().getColumnFooterBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T columnFooter(ComponentBuilder<?, ?> ...components) {
		return addColumnFooter(components);
	}

	public T addColumnFooter(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getColumnFooterBand().addComponent(component.build());
		}
		return (T) this;
	}

	//group header band
	public T setGroupHeaderSplitType(GroupBuilder<?> group, SplitType splitType) {
		Validate.notNull(group, "group must not be null");
		int index = getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		DRGroup drGroup = getObject().getGroups().get(index);
		drGroup.getHeaderBand().setSplitType(splitType);
		return (T) this;
	}

	public T setGroupHeaderStyle(GroupBuilder<?> group, StyleBuilder style) {
		Validate.notNull(group, "group must not be null");
		int index = getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		DRGroup drGroup = getObject().getGroups().get(index);
		if (style != null) {
			drGroup.getHeaderBand().getList().setStyle(style.build());
		}
		else {
			drGroup.getHeaderBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T groupHeader(GroupBuilder<?> group, ComponentBuilder<?, ?> ...components) {
		return addGroupHeader(group, components);
	}

	public T addGroupHeader(GroupBuilder<?> group, ComponentBuilder<?, ?> ...components) {
		Validate.notNull(group, "group must not be null");
		int index = getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		DRGroup drGroup = getObject().getGroups().get(index);
		for (ComponentBuilder<?, ?> component : components) {
			drGroup.getHeaderBand().addComponent(component.build());
		}
		return (T) this;
	}

	//group footer band
	public T setGroupFooterSplitType(GroupBuilder<?> group, SplitType splitType) {
		Validate.notNull(group, "group must not be null");
		int index = getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		DRGroup drGroup = getObject().getGroups().get(index);
		drGroup.getFooterBand().setSplitType(splitType);
		return (T) this;
	}

	public T setGroupFooterStyle(GroupBuilder<?> group, StyleBuilder style) {
		Validate.notNull(group, "group must not be null");
		int index = getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		DRGroup drGroup = getObject().getGroups().get(index);
		if (style != null) {
			drGroup.getFooterBand().getList().setStyle(style.build());
		}
		else {
			drGroup.getFooterBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T groupFooter(GroupBuilder<?> group, ComponentBuilder<?, ?> ...components) {
		return addGroupFooter(group, components);
	}

	public T addGroupFooter(GroupBuilder<?> group, ComponentBuilder<?, ?> ...components) {
		Validate.notNull(group, "group must not be null");
		int index = getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		DRGroup drGroup = getObject().getGroups().get(index);
		for (ComponentBuilder<?, ?> component : components) {
			drGroup.getFooterBand().addComponent(component.build());
		}
		return (T) this;
	}

	//detail band
	public T setDetailSplitType(SplitType splitType) {
		getObject().getDetailBand().setSplitType(splitType);
		return (T) this;
	}

	public T setDetailStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getDetailBand().getList().setStyle(style.build());
		}
		else {
			getObject().getDetailBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T detail(ComponentBuilder<?, ?> ...components) {
		return addDetail(components);
	}

	public T addDetail(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getDetailBand().addComponent(component.build());
		}
		return (T) this;
	}

	//detail header band
	public T setDetailHeaderSplitType(SplitType splitType) {
		getObject().getDetailHeaderBand().setSplitType(splitType);
		return (T) this;
	}

	public T setDetailHeaderStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getDetailHeaderBand().getList().setStyle(style.build());
		}
		else {
			getObject().getDetailHeaderBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T detailHeader(ComponentBuilder<?, ?> ...components) {
		return addDetailHeader(components);
	}

	public T addDetailHeader(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getDetailHeaderBand().addComponent(component.build());
		}
		return (T) this;
	}

	//detail footer band
	public T setDetailFooterSplitType(SplitType splitType) {
		getObject().getDetailFooterBand().setSplitType(splitType);
		return (T) this;
	}

	public T setDetailFooterStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getDetailFooterBand().getList().setStyle(style.build());
		}
		else {
			getObject().getDetailFooterBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T detailFooter(ComponentBuilder<?, ?> ...components) {
		return addDetailFooter(components);
	}

	public T addDetailFooter(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getDetailFooterBand().addComponent(component.build());
		}
		return (T) this;
	}

	//last page footer band
	public T setLastPageFooterSplitType(SplitType splitType) {
		getObject().getLastPageFooterBand().setSplitType(splitType);
		return (T) this;
	}

	public T setLastPageFooterStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getLastPageFooterBand().getList().setStyle(style.build());
		}
		else {
			getObject().getLastPageFooterBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T lastPageFooter(ComponentBuilder<?, ?> ...components) {
		return addLastPageFooter(components);
	}

	public T addLastPageFooter(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getLastPageFooterBand().addComponent(component.build());
		}
		return (T) this;
	}

	//summary band
	public T setSummarySplitType(SplitType splitType) {
		getObject().getSummaryBand().setSplitType(splitType);
		return (T) this;
	}

	public T setSummaryStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getSummaryBand().getList().setStyle(style.build());
		}
		else {
			getObject().getSummaryBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T summary(ComponentBuilder<?, ?> ...components) {
		return addSummary(components);
	}

	public T addSummary(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getSummaryBand().addComponent(component.build());
		}
		return (T) this;
	}

	//no data band
	public T setNoDataSplitType(SplitType splitType) {
		getObject().getNoDataBand().setSplitType(splitType);
		return (T) this;
	}

	public T setNoDataStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getNoDataBand().getList().setStyle(style.build());
		}
		else {
			getObject().getNoDataBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T noData(ComponentBuilder<?, ?> ...components) {
		return addNoData(components);
	}

	public T addNoData(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getNoDataBand().addComponent(component.build());
		}
		return (T) this;
	}

	//background band
	public T setBackgroundSplitType(SplitType splitType) {
		getObject().getBackgroundBand().setSplitType(splitType);
		return (T) this;
	}

	public T setBackgroundStyle(StyleBuilder style) {
		if (style != null) {
			getObject().getBackgroundBand().getList().setStyle(style.build());
		}
		else {
			getObject().getBackgroundBand().getList().setStyle(null);
		}
		return (T) this;
	}

	public T background(ComponentBuilder<?, ?> ...components) {
		return addBackground(components);
	}

	public T addBackground(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().getBackgroundBand().addComponent(component.build());
		}
		return (T) this;
	}

	public DRReport getReport() {
		return build();
	}
}
