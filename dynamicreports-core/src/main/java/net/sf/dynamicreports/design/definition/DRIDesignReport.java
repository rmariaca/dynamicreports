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

package net.sf.dynamicreports.design.definition;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import net.sf.dynamicreports.design.definition.style.DRIDesignStyle;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.definition.DRIScriptlet;
import net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DRIDesignReport {

	public DRIDesignTemplateDesign getTemplateDesign();

	public Locale getLocale();

	public ResourceBundle getResourceBundle();

	public String getResourceBundleName();

	public boolean isIgnorePagination();

	public Properties getProperties();

	public DRIDesignQuery getQuery();

	public DRIDesignPage getPage();

	public WhenNoDataType getWhenNoDataType();

	public boolean isTitleOnANewPage();

	public boolean isSummaryOnANewPage();

	public boolean isSummaryWithPageHeaderAndFooter();

	public boolean isFloatColumnFooter();

	public boolean isTableOfContents();

	public DRITableOfContentsCustomizer getTableOfContentsCustomizer();

	public Collection<DRIDesignParameter> getParameters();

	public Collection<DRIScriptlet> getScriptlets();

	public Collection<DRIDesignField> getFields();

	public Collection<DRIDesignSystemExpression> getSystemExpressions();

	public Collection<DRIDesignJasperExpression> getJasperExpressions();

	public Collection<DRIDesignSimpleExpression> getSimpleExpressions();

	public Collection<DRIDesignStyle> getStyles();

	public Collection<? extends DRIDesignGroup> getGroups();

	public Collection<DRIDesignVariable> getVariables();

	public Collection<DRIDesignComplexExpression> getComplexExpressions();

	public Collection<DRIDesignDataset> getDatasets();

	public DRIDesignBand getTitleBand();

	public DRIDesignBand getPageHeaderBand();

	public DRIDesignBand getPageFooterBand();

	public DRIDesignBand getColumnHeaderBand();

	public DRIDesignBand getColumnFooterBand();

	public List<? extends DRIDesignBand> getDetailBands();

	public DRIDesignBand getLastPageFooterBand();

	public DRIDesignBand getSummaryBand();

	public DRIDesignBand getNoDataBand();

	public DRIDesignBand getBackgroundBand();
}
