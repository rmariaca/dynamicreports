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

package net.sf.dynamicreports.design.base;

import java.util.Collection;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import net.sf.dynamicreports.design.definition.DRIDesignField;
import net.sf.dynamicreports.design.definition.DRIDesignParameter;
import net.sf.dynamicreports.design.definition.DRIDesignReport;
import net.sf.dynamicreports.design.definition.DRIDesignVariable;
import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.design.definition.style.DRIDesignStyle;
import net.sf.dynamicreports.design.transformation.BandTransform;
import net.sf.dynamicreports.design.transformation.BarcodeTransform;
import net.sf.dynamicreports.design.transformation.ChartTransform;
import net.sf.dynamicreports.design.transformation.ColumnGridTransform;
import net.sf.dynamicreports.design.transformation.ColumnTransform;
import net.sf.dynamicreports.design.transformation.ComponentTransform;
import net.sf.dynamicreports.design.transformation.DesignTransformAccessor;
import net.sf.dynamicreports.design.transformation.ExpressionTransform;
import net.sf.dynamicreports.design.transformation.GroupTransform;
import net.sf.dynamicreports.design.transformation.PageTransform;
import net.sf.dynamicreports.design.transformation.ReportTransform;
import net.sf.dynamicreports.design.transformation.StyleTransform;
import net.sf.dynamicreports.design.transformation.SubtotalTransform;
import net.sf.dynamicreports.design.transformation.TemplateTransform;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.definition.DRIScriptlet;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignReport implements DesignTransformAccessor, DRIDesignReport {
	private DRIReport report;
	private ReportTransform reportTransform;
	private TemplateTransform templateTransform;
	private PageTransform pageTransform;
	private ExpressionTransform expressionTransform;
	private BandTransform bandTransform;
	private ComponentTransform componentTransform;
	private GroupTransform groupTransform;
	private ColumnGridTransform columnGridTransform;
	private ColumnTransform columnTransform;
	private SubtotalTransform subtotalTransform;
	private StyleTransform styleTransform;
	private ChartTransform chartTransform;
	private BarcodeTransform barcodeTransform;

	public DRDesignReport(DRIReport report) throws DRException {
		this.report = report;
		init();
		transform();
	}
	
	private void init() {		
		reportTransform = new ReportTransform(this);
		templateTransform = new TemplateTransform(this);
		pageTransform = new PageTransform(this);
		expressionTransform = new ExpressionTransform(this);
		groupTransform = new GroupTransform(this);
		bandTransform = new BandTransform(this);	
		componentTransform = new ComponentTransform(this);
		columnGridTransform = new ColumnGridTransform(this);
		columnTransform = new ColumnTransform(this);
		subtotalTransform = new SubtotalTransform(this);
		styleTransform = new StyleTransform(this);
		chartTransform = new ChartTransform(this);
		barcodeTransform = new BarcodeTransform(this);
	}
	
	private void transform() throws DRException {		
		reportTransform.transform();
		pageTransform.transform();
		groupTransform.transform();
		expressionTransform.transform();
		bandTransform.transform();
		columnGridTransform.transform();
		columnTransform.transform();
		groupTransform.transformHeaderAndFooter();
		subtotalTransform.transform();		
		
		bandTransform.prepareBands();
	}
	
	public DRIReport getReport() {
		return report;
	}

	public TemplateTransform getTemplateTransform() {
		return templateTransform;
	}
	
	public ExpressionTransform getExpressionTransform() {
		return expressionTransform;
	}
	
	public BandTransform getBandTransform() {
		return bandTransform;
	}
	
	public ComponentTransform getComponentTransform() {
		return componentTransform;
	}
	
	public GroupTransform getGroupTransform() {
		return groupTransform;
	}
	
	public ColumnGridTransform getColumnGridTransform() {
		return columnGridTransform;
	}
	
	public StyleTransform getStyleTransform() {
		return styleTransform;
	}
	
	public ChartTransform getChartTransform() {
		return chartTransform;
	}
	
	public BarcodeTransform getBarcodeTransform() {
		return barcodeTransform;
	}
	
	public Locale getLocale() {
		return templateTransform.getLocale();
	}

	public ResourceBundle getResourceBundle() {
		return report.getResourceBundle();
	}

	public String getResourceBundleName() {
		return report.getResourceBundleName();
	}
	
	public boolean isIgnorePagination() {
		return templateTransform.isIgnorePagination();
	}

	public Properties getProperties() {
		return report.getProperties();
	}

	public DRDesignQuery getQuery() {
		return reportTransform.getQuery();
	}

	public DRDesignPage getPage() {
		return pageTransform.getPage();
	}

	public WhenNoDataType getWhenNoDataType() {
		return templateTransform.getWhenNoDataType();
	}

	public boolean isTitleOnANewPage() {
		return templateTransform.isTitleOnANewPage();
	}

	public boolean isSummaryOnANewPage() {
		return templateTransform.isSummaryOnANewPage();
	}

	public boolean isFloatColumnFooter() {
		return templateTransform.isFloatColumnFooter();
	}
	
	public Collection<DRIDesignParameter> getParameters() {
		return reportTransform.getParameters();
	}

	public Collection<DRIScriptlet> getScriptlets() {
		return report.getScriptlets();
	}
	
	public Collection<DRIDesignField> getFields() {
		return expressionTransform.getFields();
	}
	
	public Collection<DRIDesignSimpleExpression> getSimpleExpressions() {
		return expressionTransform.getSimpleExpressions();
	}
	
	public Collection<DRIDesignStyle> getStyles() {
		return styleTransform.getStyles();
	}
	
	public Collection<DRDesignGroup> getGroups() {
		return groupTransform.getGroups();
	}
	
	public Collection<DRIDesignVariable> getVariables() {
		return expressionTransform.getVariables();
	}
	
	public Collection<DRIDesignComplexExpression> getComplexExpressions() {
		return expressionTransform.getComplexExpressions();
	}	
	
	public DRDesignBand getTitleBand() {
		return bandTransform.getTitleBand();
	}
	
	public DRDesignBand getPageHeaderBand() {
		return bandTransform.getPageHeaderBand();
	}
	
	public DRDesignBand getPageFooterBand() {
		return bandTransform.getPageFooterBand();
	}
	
	public DRDesignBand getColumnHeaderBand() {
		return bandTransform.getColumnHeaderBand();
	}
	
	public DRDesignBand getColumnFooterBand() {
		return bandTransform.getColumnFooterBand();
	}
	
	public DRDesignBand getDetailBand() {
		return bandTransform.getDetailBand();
	}
	
	public DRDesignBand getLastPageFooterBand() {
		return bandTransform.getLastPageFooterBand();
	}
	
	public DRDesignBand getSummaryBand() {
		return bandTransform.getSummaryBand();
	}
	
	public DRDesignBand getNoDataBand() {
		return bandTransform.getNoDataBand();
	}
	
	public DRDesignBand getBackgroundBand() {
		return bandTransform.getBackgroundBand();
	}
}