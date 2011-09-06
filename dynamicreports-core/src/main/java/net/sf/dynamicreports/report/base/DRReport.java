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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import net.sf.dynamicreports.report.base.column.DRColumn;
import net.sf.dynamicreports.report.base.grid.DRColumnGrid;
import net.sf.dynamicreports.report.base.style.DRConditionalStyle;
import net.sf.dynamicreports.report.base.style.DRSimpleStyle;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.definition.DRIScriptlet;
import net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.style.DRIStyle;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRReport implements DRIReport {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private DRReportTemplate template;
	private DRITemplateDesign<?> templateDesign;
	private Locale locale;
	private ResourceBundle resourceBundle;
	private String resourceBundleName;
	private Boolean ignorePagination;
	private Boolean showColumnTitle;
	private List<DRColumn<?>> columns;
	private List<DRGroup> groups;
	private List<DRSubtotal<?>> subtotals;
	private List<DRField<?>> fields;
	private List<DRVariable<?>> variables;
	private List<DRParameter<?>> parameters;
	private List<DRIScriptlet> scriptlets;
	private Properties properties;
	private DRQuery query;
	private DRPage page;
	private WhenNoDataType whenNoDataType;
	private Boolean titleOnANewPage;
	private Boolean summaryOnANewPage;
	private Boolean summaryWithPageHeaderAndFooter;
	private Boolean floatColumnFooter;
	private Boolean useFieldNameAsDescription;
	private Boolean highlightDetailOddRows;
	private DRSimpleStyle detailOddRowStyle;
	private Boolean highlightDetailEvenRows;
	private DRSimpleStyle detailEvenRowStyle;
	private List<DRConditionalStyle> detailRowHighlighters;
	private DRColumnGrid columnGrid;
	private Boolean tableOfContents;
	private DRITableOfContentsCustomizer tableOfContentsCustomizer;
	private DRIExpression<Boolean> filterExpression;

	private DRStyle textStyle;
	private DRStyle columnTitleStyle;
	private DRStyle columnStyle;
	private DRStyle groupTitleStyle;
	private DRStyle groupStyle;
	private DRStyle subtotalStyle;
	private DRStyle imageStyle;
	private DRStyle chartStyle;
	private DRStyle barcodeStyle;

	private DRBand titleBand;
	private DRBand pageHeaderBand;
	private DRBand pageFooterBand;
	private DRBand columnHeaderBand;
	private DRBand columnFooterBand;
	private DRBand detailBand;
	private DRBand detailHeaderBand;
	private DRBand detailFooterBand;
	private DRBand lastPageFooterBand;
	private DRBand summaryBand;
	private DRBand noDataBand;
	private DRBand backgroundBand;

	public DRReport() {
		init();
	}

	private void init() {
		this.template = new DRReportTemplate();
		this.columns = new ArrayList<DRColumn<?>>();
		this.groups = new ArrayList<DRGroup>();
		this.subtotals = new ArrayList<DRSubtotal<?>>();
		this.fields = new ArrayList<DRField<?>>();
		this.variables = new ArrayList<DRVariable<?>>();
		this.parameters = new ArrayList<DRParameter<?>>();
		this.scriptlets = new ArrayList<DRIScriptlet>();
		this.detailRowHighlighters = new ArrayList<DRConditionalStyle>();
		this.properties = new Properties();
		this.page = new DRPage();

		titleBand = new DRBand();
		pageHeaderBand = new DRBand();
		pageFooterBand = new DRBand();
		columnHeaderBand = new DRBand();
		columnFooterBand = new DRBand();
		detailBand = new DRBand();
		detailHeaderBand = new DRBand();
		detailFooterBand = new DRBand();
		lastPageFooterBand = new DRBand();
		summaryBand = new DRBand();
		noDataBand = new DRBand();
		backgroundBand = new DRBand();
	}

	public DRReportTemplate getTemplate() {
		return template;
	}

	public void setTemplate(DRReportTemplate template) {
		Validate.notNull(template, "template must not be null");
		this.template = template;
	}

	public DRITemplateDesign<?> getTemplateDesign() {
		return templateDesign;
	}

	public void setTemplateDesign(DRITemplateDesign<?> templateDesign) {
		Validate.notNull(templateDesign, "templateDesign must not be null");
		this.templateDesign = templateDesign;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	public String getResourceBundleName() {
		return resourceBundleName;
	}

	public void setResourceBundleName(String resourceBundleName) {
		this.resourceBundleName = resourceBundleName;
	}

	public Boolean getShowColumnTitle() {
		return showColumnTitle;
	}

	public void setShowColumnTitle(Boolean showColumnTitle) {
		this.showColumnTitle = showColumnTitle;
	}

	public List<DRColumn<?>> getColumns() {
		return columns;
	}

	public void setColumns(List<DRColumn<?>> columns) {
		Validate.notNull(columns, "columns must not be null");
		Validate.noNullElements(columns, "columns must not contains null column");
		this.columns = columns;
	}

	public void addColumn(DRColumn<?> column) {
		Validate.notNull(column, "column must not be null");
		this.columns.add(column);
	}

	public List<DRGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<DRGroup> groups) {
		Validate.notNull(groups, "groups must not be null");
		Validate.noNullElements(groups, "groups must not contains null group");
		this.groups = groups;
	}

	public void addGroup(DRGroup group) {
		Validate.notNull(group, "group must not be null");
		this.groups.add(group);
	}

	public List<DRField<?>> getFields() {
		return fields;
	}

	public void setFields(List<DRField<?>> fields) {
		Validate.notNull(fields, "fields must not be null");
		Validate.noNullElements(fields, "fields must not contains null field");
		this.fields = fields;
	}

	public void addField(DRField<?> field) {
		Validate.notNull(field, "field must not be null");
		this.fields.add(field);
	}

	public List<DRVariable<?>> getVariables() {
		return variables;
	}

	public void setVariables(List<DRVariable<?>> variables) {
		Validate.notNull(variables, "variables must not be null");
		Validate.noNullElements(variables, "variables must not contains null variable");
		this.variables = variables;
	}

	public void addVariable(DRVariable<?> variable) {
		Validate.notNull(variable, "variable must not be null");
		this.variables.add(variable);
	}

	public List<DRSubtotal<?>> getSubtotals() {
		return subtotals;
	}

	public void setSubtotals(List<DRSubtotal<?>> subtotals) {
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		this.subtotals = subtotals;
	}

	public void addSubtotal(DRSubtotal<?> subtotal) {
		Validate.notNull(subtotal, "subtotal must not be null");
		this.subtotals.add(subtotal);
	}

	public List<DRParameter<?>> getParameters() {
		return parameters;
	}

	public void setParameters(List<DRParameter<?>> parameters) {
		Validate.notNull(parameters, "parameters must not be null");
		Validate.noNullElements(parameters, "parameters must not contains null parameter");
		this.parameters = parameters;
	}

	public void addParameter(DRParameter<?> parameter) {
		Validate.notNull(parameter, "parameter must not be null");
		this.parameters.add(parameter);
	}

	public List<DRIScriptlet> getScriptlets() {
		return scriptlets;
	}

	public void setScriptlets(List<DRIScriptlet> scriptlets) {
		Validate.notNull(scriptlets, "scriptlets must not be null");
		Validate.noNullElements(scriptlets, "scriptlets must not contains null scriptlet");
		this.scriptlets = scriptlets;
	}

	public void addScriptlet(DRIScriptlet scriptlet) {
		Validate.notNull(scriptlet, "scriptlet must not be null");
		this.scriptlets.add(scriptlet);
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		Validate.notNull(properties, "properties must not be null");
		this.properties = properties;
	}

	public void addProperty(String key, String value) {
		Validate.notNull(key, "key must not be null");
		this.properties.setProperty(key, value);
	}

	public DRQuery getQuery() {
		return query;
	}

	public void setQuery(DRQuery query) {
		this.query = query;
	}

	public DRPage getPage() {
		return page;
	}

	public void setPage(DRPage page) {
		Validate.notNull(page, "page must not be null");
		this.page = page;
	}

	public Boolean getIgnorePagination() {
		return ignorePagination;
	}

	public void setIgnorePagination(Boolean ignorePagination) {
		this.ignorePagination = ignorePagination;
	}

	public WhenNoDataType getWhenNoDataType() {
		return whenNoDataType;
	}

	public void setWhenNoDataType(WhenNoDataType whenNoDataType) {
		this.whenNoDataType = whenNoDataType;
	}

	public Boolean getTitleOnANewPage() {
		return titleOnANewPage;
	}

	public void setTitleOnANewPage(Boolean titleOnANewPage) {
		this.titleOnANewPage = titleOnANewPage;
	}

	public Boolean getSummaryOnANewPage() {
		return summaryOnANewPage;
	}

	public void setSummaryOnANewPage(Boolean summaryOnANewPage) {
		this.summaryOnANewPage = summaryOnANewPage;
	}

	public Boolean getSummaryWithPageHeaderAndFooter() {
		return summaryWithPageHeaderAndFooter;
	}

	public void setSummaryWithPageHeaderAndFooter(Boolean summaryWithPageHeaderAndFooter) {
		this.summaryWithPageHeaderAndFooter = summaryWithPageHeaderAndFooter;
	}

	public Boolean getFloatColumnFooter() {
		return floatColumnFooter;
	}

	public void setFloatColumnFooter(Boolean floatColumnFooter) {
		this.floatColumnFooter = floatColumnFooter;
	}

	public Boolean getUseFieldNameAsDescription() {
		return useFieldNameAsDescription;
	}

	public void setUseFieldNameAsDescription(Boolean useFieldNameAsDescription) {
		this.useFieldNameAsDescription = useFieldNameAsDescription;
	}

	public DRStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(DRStyle textStyle) {
		this.textStyle = textStyle;
	}

	public DRStyle getColumnTitleStyle() {
		return columnTitleStyle;
	}

	public void setColumnTitleStyle(DRStyle columnTitleStyle) {
		this.columnTitleStyle = columnTitleStyle;
	}

	public DRStyle getColumnStyle() {
		return columnStyle;
	}

	public void setColumnStyle(DRStyle columnStyle) {
		this.columnStyle = columnStyle;
	}

	public DRStyle getGroupTitleStyle() {
		return groupTitleStyle;
	}

	public void setGroupTitleStyle(DRStyle groupTitleStyle) {
		this.groupTitleStyle = groupTitleStyle;
	}

	public DRStyle getGroupStyle() {
		return groupStyle;
	}

	public void setGroupStyle(DRStyle groupStyle) {
		this.groupStyle = groupStyle;
	}

	public DRStyle getSubtotalStyle() {
		return subtotalStyle;
	}

	public void setSubtotalStyle(DRStyle subtotalStyle) {
		this.subtotalStyle = subtotalStyle;
	}

	public DRStyle getImageStyle() {
		return imageStyle;
	}

	public void setImageStyle(DRStyle imageStyle) {
		this.imageStyle = imageStyle;
	}

	public DRStyle getChartStyle() {
		return chartStyle;
	}

	public void setChartStyle(DRStyle chartStyle) {
		this.chartStyle = chartStyle;
	}

	public DRIStyle getBarcodeStyle() {
		return barcodeStyle;
	}

	public void setBarcodeStyle(DRStyle barcodeStyle) {
		this.barcodeStyle = barcodeStyle;
	}

	public Boolean getHighlightDetailOddRows() {
		return highlightDetailOddRows;
	}

	public void setHighlightDetailOddRows(Boolean highlightDetailOddRows) {
		this.highlightDetailOddRows = highlightDetailOddRows;
	}

	public DRSimpleStyle getDetailOddRowStyle() {
		return detailOddRowStyle;
	}

	public void setDetailOddRowStyle(DRSimpleStyle detailOddRowStyle) {
		this.detailOddRowStyle = detailOddRowStyle;
	}

	public Boolean getHighlightDetailEvenRows() {
		return highlightDetailEvenRows;
	}

	public void setHighlightDetailEvenRows(Boolean highlightDetailEvenRows) {
		this.highlightDetailEvenRows = highlightDetailEvenRows;
	}

	public DRSimpleStyle getDetailEvenRowStyle() {
		return detailEvenRowStyle;
	}

	public void setDetailEvenRowStyle(DRSimpleStyle detailEvenRowStyle) {
		this.detailEvenRowStyle = detailEvenRowStyle;
	}

	public List<DRConditionalStyle> getDetailRowHighlighters() {
		return detailRowHighlighters;
	}

	public void setDetailRowHighlighters(List<DRConditionalStyle> detailRowHighlighters) {
		Validate.notNull(detailRowHighlighters, "detailRowHighlighters must not be null");
		Validate.noNullElements(detailRowHighlighters, "detailRowHighlighters must not contains null detailRowHighlighter");
		this.detailRowHighlighters = detailRowHighlighters;
	}

	public void addDetailRowHighlighter(DRConditionalStyle detailRowHighlighter) {
		Validate.notNull(detailRowHighlighter, "detailRowHighlighter must not be null");
		this.detailRowHighlighters.add(detailRowHighlighter);
	}

	public DRColumnGrid getColumnGrid() {
		return columnGrid;
	}

	public void setColumnGrid(DRColumnGrid columnGrid) {
		this.columnGrid = columnGrid;
	}

	public Boolean getTableOfContents() {
		return tableOfContents;
	}

	public void setTableOfContents(Boolean tableOfContents) {
		this.tableOfContents = tableOfContents;
	}

	public DRITableOfContentsCustomizer getTableOfContentsCustomizer() {
		return tableOfContentsCustomizer;
	}

	public void setTableOfContentsCustomizer(DRITableOfContentsCustomizer tableOfContentsCustomizer) {
		this.tableOfContentsCustomizer = tableOfContentsCustomizer;
	}

	public DRIExpression<Boolean> getFilterExpression() {
		return filterExpression;
	}

	public void setFilterExpression(DRIExpression<Boolean> filterExpression) {
		this.filterExpression = filterExpression;
	}

	public DRBand getTitleBand() {
		return titleBand;
	}

	public DRBand getPageHeaderBand() {
		return pageHeaderBand;
	}

	public DRBand getPageFooterBand() {
		return pageFooterBand;
	}

	public DRBand getColumnHeaderBand() {
		return columnHeaderBand;
	}

	public DRBand getColumnFooterBand() {
		return columnFooterBand;
	}

	public DRBand getDetailBand() {
		return detailBand;
	}

	public DRBand getDetailHeaderBand() {
		return detailHeaderBand;
	}

	public DRBand getDetailFooterBand() {
		return detailFooterBand;
	}

	public DRBand getLastPageFooterBand() {
		return lastPageFooterBand;
	}

	public DRBand getSummaryBand() {
		return summaryBand;
	}

	public DRBand getNoDataBand() {
		return noDataBand;
	}

	public DRBand getBackgroundBand() {
		return backgroundBand;
	}
}
