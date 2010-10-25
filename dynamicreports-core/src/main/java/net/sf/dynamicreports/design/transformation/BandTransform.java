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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignBand;
import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignFiller;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.constant.ComponentGroupType;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.definition.DRIBand;
import net.sf.dynamicreports.report.definition.DRIGroup;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class BandTransform {
	private DesignTransformAccessor accessor;
	private int maxWidth;
	private int maxColumnWidth;
	
	private DRDesignBand titleBand;
	private DRDesignBand pageHeaderBand;
	private DRDesignBand pageFooterBand;
	private DRDesignBand columnHeaderBand;
	private DRDesignBand columnHeaderForGroupBand;
	private DRDesignBand columnFooterBand;
	private DRDesignBand detailBand;
	private DRDesignBand lastPageFooterBand;
	private DRDesignBand summaryBand;
	private DRDesignBand noDataBand;
	private DRDesignBand backgroundBand;	
	
	private Map<String, Integer> componentNames;
	
	public BandTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
		componentNames = new HashMap<String, Integer>();		
	}
	
	public void transform() throws DRException {
		TemplateTransform templateTransform = accessor.getTemplateTransform();
		maxWidth = templateTransform.getPageWidth() - templateTransform.getPageMargin().getLeft() - templateTransform.getPageMargin().getRight();
		maxColumnWidth = accessor.getPage().getColumnWidth();
		
		DRIReport report = accessor.getReport();
		
		DRIBand band = report.getTitleBand();		
		titleBand = band("title", band, templateTransform.getTitleSplitType(band), ResetType.REPORT, null);
		
		band = report.getPageHeaderBand();
		pageHeaderBand = band("pageHeader", band, templateTransform.getPageHeaderSplitType(band), ResetType.PAGE, null);
		
		band = report.getPageFooterBand();
		pageFooterBand = band("pageFooter", band, templateTransform.getPageFooterSplitType(band), ResetType.PAGE, null);
		
		band = report.getColumnHeaderBand();
		columnHeaderBand = band("columnHeader", band, templateTransform.getColumnHeaderSplitType(band), ResetType.COLUMN, null);
		
		for (DRIGroup group : report.getGroups()) {
			if (templateTransform.isGroupShowColumnHeaderAndFooter(group)) {
				band = report.getColumnHeaderBand();
				columnHeaderForGroupBand = band("columnHeaderForGroup", band, templateTransform.getColumnHeaderSplitType(band), ResetType.COLUMN, null);
				break;
			}
		}
		
		band = report.getColumnFooterBand();
		columnFooterBand = band("columnFooter", band, templateTransform.getColumnFooterSplitType(band), ResetType.COLUMN, null);
		
		band = report.getDetailBand();
		detailBand = band("detail", band, templateTransform.getDetailSplitType(band), ResetType.REPORT, null);
		
		band = report.getLastPageFooterBand();
		lastPageFooterBand = band("lastPageFooter", band, templateTransform.getLastPageFooterSplitType(band), ResetType.PAGE, null);
		
		band = report.getSummaryBand();
		summaryBand = band("summary", band, templateTransform.getSummarySplitType(band), ResetType.REPORT, null);
		
		band = report.getNoDataBand();
		noDataBand = band("noData", band, templateTransform.getNoDataSplitType(band), ResetType.REPORT, null);
		
		band = report.getBackgroundBand();
		backgroundBand = band("background", band, templateTransform.getBackgroundSplitType(band), ResetType.REPORT, null);	
	}
	
	public void prepareBands() throws DRException {
		DRITemplateDesign<?> templateDesign = accessor.getReport().getTemplateDesign();
		
		titleBand = prepareBand(titleBand, maxWidth, templateDesign.getTitleComponentsCount());
		pageHeaderBand = prepareBand(pageHeaderBand, maxWidth, templateDesign.getPageHeaderComponentsCount());
		pageFooterBand = prepareBand(pageFooterBand, maxWidth, templateDesign.getPageFooterComponentsCount());
		columnHeaderBand = prepareBand(columnHeaderBand, maxColumnWidth, templateDesign.getColumnHeaderComponentsCount());
		columnFooterBand = prepareBand(columnFooterBand, maxColumnWidth, templateDesign.getColumnFooterComponentsCount());
		detailBand = prepareBand(detailBand, maxColumnWidth, 0);
		lastPageFooterBand = prepareBand(lastPageFooterBand, maxWidth, templateDesign.getLastPageFooterComponentsCount());
		summaryBand = prepareBand(summaryBand, maxWidth, templateDesign.getSummaryComponentsCount());
		noDataBand = prepareBand(noDataBand, maxWidth, templateDesign.getNoDataComponentsCount());
		backgroundBand = prepareBand(backgroundBand, maxWidth, templateDesign.getBackgroundComponentsCount());
		for (DRDesignGroup group : accessor.getGroupTransform().getGroups()) {
			List<DRDesignBand> bands = new ArrayList<DRDesignBand>();
			for (DRDesignBand band : group.getHeaderBands()) {
				DRDesignBand newBand = prepareBand(band, maxColumnWidth, 0);
				if (newBand != null) {
					bands.add(newBand);
				}
			}
			group.setHeaderBands(bands);
			bands = new ArrayList<DRDesignBand>();
			for (DRDesignBand band : group.getFooterBands()) {
				DRDesignBand newBand = prepareBand(band, maxColumnWidth, 0);
				if (newBand != null) {
					bands.add(newBand);
				}
			}
			group.setFooterBands(bands);
		}				
	}
	
	private DRDesignBand prepareBand(DRDesignBand band, int maxWidth, int templateDesignComponents) throws DRException {
		if (band == null) {
			return null;
		}
		if (band.getBandComponent() != null) {
			return band;
		}
		if (band.getList().isEmpty()) {
			return null;
		}

		ComponentPosition.component(band.getName(), band.getList(), maxWidth);
		
		DRDesignComponent component = removeEmptyComponents(band.getList());
		if (component == null) {
			return null;
		}
		componentGroupType(component);
		
		generateComponentNames(component, band.getName());		
		band.setBandComponent(component);
		
		if (band.getBandComponent() != null && templateDesignComponents > 0) {
			throw new DRException("Band " + band.getName() + " must not be defined at once in jrxml template design and in dynamic design");
		}
		
		return band;
	}
	
	private void generateComponentNames(DRDesignComponent component, String bandName) {
		String componentName = bandName + "." + component.getUniqueName();
		if (!componentNames.containsKey(componentName)) {
			componentNames.put(componentName, new Integer(1));
		}
		else {
			componentNames.put(componentName, componentNames.get(componentName) + 1);
		}
		component.setUniqueName(componentName + componentNames.get(componentName));
		if (component instanceof DRDesignList) {
			for (DRDesignComponent lComponent : ((DRDesignList) component).getComponents()) {
				generateComponentNames(lComponent, bandName);
			}
		}
	}

	private DRDesignComponent removeEmptyComponents(DRDesignComponent component) {
		if (component instanceof DRDesignList) {
			DRDesignList list = (DRDesignList) component;
			if (list.getComponents().isEmpty()) {
				return null;
			}
			else if (list.getComponents().size() == 1) {
				DRDesignComponent lComponent = list.getComponents().get(0);
				DRDesignComponent elm = removeEmptyComponents(lComponent);
				if (elm == null) {
					return null;
				}
				elm.setX(lComponent.getX() + elm.getX());
				elm.setY(lComponent.getY() + elm.getY());

				if (list.getStyle() == null && list.getPrintWhenExpression() == null) {
					elm.setX(list.getX() + elm.getX());
					elm.setY(list.getY() + elm.getY());	
					return elm;
				}
				else {
					list.getComponents().clear();
					list.getComponents().add(elm);
					return list;
				}
			}
			else {
				List<DRDesignComponent> components = new ArrayList<DRDesignComponent>();
				for (DRDesignComponent listComponent : list.getComponents()) {
					DRDesignComponent comp = removeEmptyComponents(listComponent);
					if (comp != null) {
						components.add(comp);
					}
				}
				if (components.isEmpty()) {
					return null;
				}
				list.getComponents().clear();
				list.getComponents().addAll(components);
				return list;
			}
		}
		else if (component instanceof DRDesignFiller && component.getStyle() == null) {
			return null;
		}
		return component;
	}
	
	private void componentGroupType(DRDesignComponent component) {
		if (component instanceof DRDesignList) {
			DRDesignList list = (DRDesignList) component;
			if (list.getType().equals(ListType.VERTICAL) && list.getStyle() == null && list.getPrintWhenExpression() == null) {
				list.setComponentGroupType(ComponentGroupType.NONE);
				for (DRDesignComponent listComponent : list.getComponents()) {
					listComponent.setX(list.getX() + listComponent.getX());
					listComponent.setY(list.getY() + listComponent.getY());
				}
			}
			else {
				list.setComponentGroupType(ComponentGroupType.FRAME);
			}
			
			for (DRDesignComponent listComponent : list.getComponents()) {
				componentGroupType(listComponent);
			}
		}
	}
	
	//band
	protected DRDesignBand band(String bandName, DRIBand band, SplitType splitType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignBand designBand = new DRDesignBand(bandName);
		designBand.setSplitType(splitType);
		designBand.setList(accessor.getComponentTransform().list(band.getList(), DefaultStyleType.TEXT, resetType, resetGroup));		
		return designBand;
	}
	
	protected DRDesignBand band(String bandName, DRIBand band, SplitType splitType) throws DRException {
		DRDesignBand designBand = new DRDesignBand(bandName);
		designBand.setSplitType(splitType);
		DRDesignList list = new DRDesignList();
		list.setType(band.getList().getType());
		list.setGap(accessor.getTemplateTransform().getListGap(band.getList()));
		list.setPrintWhenExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(band.getList().getPrintWhenExpression()));
		designBand.setList(list);		
		return designBand;
	}
	
	protected int getMaxWidth() {
		return maxWidth;
	}
	
	protected int getMaxColumnWidth() {
		return maxColumnWidth;
	}
	
	public DRDesignBand getTitleBand() {
		return titleBand;
	}

	public DRDesignBand getPageHeaderBand() {
		return pageHeaderBand;
	}
	
	public DRDesignBand getPageFooterBand() {
		return pageFooterBand;
	}
	
	public DRDesignBand getColumnHeaderBand() {
		return columnHeaderBand;
	}
	
	public DRDesignBand getColumnHeaderForGroupBand() {
		return columnHeaderForGroupBand;
	}
	
	public DRDesignBand getColumnFooterBand() {
		return columnFooterBand;
	}
	
	public DRDesignBand getDetailBand() {
		return detailBand;
	}
	
	public DRDesignBand getLastPageFooterBand() {
		return lastPageFooterBand;
	}
	
	public DRDesignBand getSummaryBand() {
		return summaryBand;
	}
	
	public DRDesignBand getNoDataBand() {
		return noDataBand;
	}
	
	public DRDesignBand getBackgroundBand() {
		return backgroundBand;
	}
}
