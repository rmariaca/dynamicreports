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

import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstab;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabBucket;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabCell;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabCellContent;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabColumnGroup;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabGroup;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabMeasure;
import net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabRowGroup;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstab;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabBucket;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabCell;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabCellContent;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabGroup;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class CrosstabTransform {
	private DesignTransformAccessor accessor;
	
	public CrosstabTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
	}
	
	protected DRDesignCrosstab transform(DRICrosstab crosstab) throws DRException {		
		DRDesignCrosstab designCrosstab = new DRDesignCrosstab();
		
		designCrosstab.setRepeatColumnHeaders(crosstab.isRepeatColumnHeaders());
		designCrosstab.setRepeatRowHeaders(crosstab.isRepeatRowHeaders());
		designCrosstab.setColumnBreakOffset(crosstab.getColumnBreakOffset());
		designCrosstab.setIgnoreWidth(crosstab.getIgnoreWidth());
		designCrosstab.setRunDirection(crosstab.getRunDirection());
		designCrosstab.setWhenNoDataCell(cellContent(crosstab.getWhenNoDataCell()));
		designCrosstab.setHeaderCell(cellContent(crosstab.getHeaderCell()));
		for (DRICrosstabColumnGroup columnGroup : crosstab.getColumnGroups()) {
			addColumnGroup(designCrosstab, columnGroup);
		}
		for (DRICrosstabRowGroup rowGroup : crosstab.getRowGroups()) {
			addRowGroup(designCrosstab, rowGroup);
		}
		for (DRICrosstabCell cell : crosstab.getCells()) {
			addCell(designCrosstab, cell);
		}
		for (DRICrosstabMeasure measure : crosstab.getMeasures()) {
			addMeasure(designCrosstab, measure);
		}
		
		return designCrosstab;
	}
	
	private DRDesignCrosstabCellContent cellContent(DRICrosstabCellContent cellContent) {
		DRDesignCrosstabCellContent designCellContents = new DRDesignCrosstabCellContent();
		return designCellContents;
	}

	private void group(DRDesignCrosstabGroup designGroup, DRICrosstabGroup group) throws DRException {
		designGroup.setName(group.getName());
		designGroup.setTotalPosition(group.getTotalPosition());
		designGroup.setBucket(bucket(group.getBucket()));
		designGroup.setHeader(cellContent(group.getHeader()));
		designGroup.setTotalHeader(cellContent(group.getTotalHeader()));		
	}
	
	private DRDesignCrosstabBucket bucket(DRICrosstabBucket bucket) throws DRException {		
		DRDesignCrosstabBucket designBucket = new DRDesignCrosstabBucket();
		designBucket.setOrderType(bucket.getOrderType());
		designBucket.setExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(bucket.getExpression()));
		designBucket.setOrderByExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(bucket.getOrderByExpression()));
		designBucket.setComparatorExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(bucket.getComparatorExpression()));
		return designBucket;
	}
	
	private void addColumnGroup(DRDesignCrosstab designCrosstab, DRICrosstabColumnGroup columnGroup) throws DRException {
		DRDesignCrosstabColumnGroup designColumnGroup = new DRDesignCrosstabColumnGroup();
		group(designColumnGroup, columnGroup);
		designColumnGroup.setHeight(columnGroup.getHeight());
		designColumnGroup.setPosition(columnGroup.getPosition());
		
		designCrosstab.getColumnGroups().add(designColumnGroup);
	}
	
	private void addRowGroup(DRDesignCrosstab designCrosstab, DRICrosstabRowGroup rowGroup) throws DRException {
		DRDesignCrosstabRowGroup designRowGroup = new DRDesignCrosstabRowGroup();
		group(designRowGroup, rowGroup);
		designRowGroup.setWidth(rowGroup.getWidth());
		designRowGroup.setPosition(rowGroup.getPosition());
		
		designCrosstab.getRowGroups().add(designRowGroup);
	}
	
	private void addCell(DRDesignCrosstab designCrosstab, DRICrosstabCell cell) {
		DRDesignCrosstabCell designCell = new DRDesignCrosstabCell();
		designCell.setWidth(cell.getWidth());
		designCell.setHeight(cell.getHeight());
		designCell.setRowTotalGroup(cell.getRowTotalGroup());
		designCell.setColumnTotalGroup(cell.getColumnTotalGroup());
		designCell.setContent(cellContent(cell.getContent()));
		
		designCrosstab.getCells().add(designCell);
	}
	
	private void addMeasure(DRDesignCrosstab designCrosstab, DRICrosstabMeasure measure) throws DRException {
		DRDesignCrosstabMeasure designMeasure = new DRDesignCrosstabMeasure();
		designMeasure.setName(measure.getName());
		designMeasure.setValueExpression(accessor.getExpressionTransform().transformExpression(measure.getValueExpression()));
		designMeasure.setValueClass(measure.getValueClass());
		designMeasure.setCalculation(measure.getCalculation());
		designMeasure.setPercentageType(measure.getPercentageType());
		
		designCrosstab.getMeasures().add(designMeasure);
	}
}
