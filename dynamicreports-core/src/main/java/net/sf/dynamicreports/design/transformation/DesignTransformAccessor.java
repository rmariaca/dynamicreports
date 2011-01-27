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

import net.sf.dynamicreports.design.base.DRDesignPage;
import net.sf.dynamicreports.report.definition.DRIReport;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DesignTransformAccessor {

	public DRIReport getReport();
	
	public Integer getPageWidth();
	
	public TemplateTransform getTemplateTransform();
	
	public ExpressionTransform getExpressionTransform();
	
	public BandTransform getBandTransform();
	
	public ComponentTransform getComponentTransform();
	
	public GroupTransform getGroupTransform();
	
	public ColumnGridTransform getColumnGridTransform();
	
	public StyleTransform getStyleTransform();
	
	public ChartTransform getChartTransform();

	public BarcodeTransform getBarcodeTransform();
	
	public CrosstabTransform getCrosstabTransform();
	
	public DRDesignPage getPage();	
}
