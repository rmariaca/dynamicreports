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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignBand;
import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignFiller;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.base.DRVariable;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.definition.DRIBand;
import net.sf.dynamicreports.report.definition.DRIColumn;
import net.sf.dynamicreports.report.definition.DRIGroup;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GroupTransform {
	private DesignTransformAccessor accessor;
	private List<? extends DRIGroup> groups;
	private Map<DRIGroup, DRDesignGroup> designGroups;
	private List<DRIColumn<?>> hideGroupColumns;
	private int groupPadding;	
	
	public GroupTransform(DesignTransformAccessor accessor) {		
		this.accessor = accessor;
		init();
	}
	
	private void init() {
		DRIReport report = accessor.getReport();
		groups = report.getGroups();
		designGroups = new LinkedHashMap<DRIGroup, DRDesignGroup>();
		hideGroupColumns = new ArrayList<DRIColumn<?>>();
		groupPadding = 0;		
	}
	
	public void transform() throws DRException {
		for (DRIGroup group : groups) {	
			DRDesignGroup designGroup = group(group);			
			addGroup(group, designGroup);
		}
	}
	
	private void addGroup(DRIGroup group, DRDesignGroup designGroup) {		
		if (accessor.getTemplateTransform().isGroupHideColumn(group) && group.getValueField().getValueExpression() instanceof DRIColumn<?>) {
			hideGroupColumns.add((DRIColumn<?>) group.getValueField().getValueExpression());
		}
		designGroups.put(group, designGroup);
		groupPadding += accessor.getTemplateTransform().getGroupPadding(group);
	}
	
	public void transformHeaderAndFooter() throws DRException {
		int groupPadding = 0;	
		for (DRIGroup group : groups) {
			headerAndFooter(group, designGroups.get(group), groupPadding);
			groupPadding += accessor.getTemplateTransform().getGroupPadding(group);
		}
	}
	
	private void headerAndFooter(DRIGroup group, DRDesignGroup designGroup, int groupPadding) throws DRException {			
		DRDesignList header = new DRDesignList();
		TemplateTransform templateTransform = accessor.getTemplateTransform();
		switch (templateTransform.getGroupHeaderLayout(group)) {
		case EMPTY:			
			break;
		case TITLE_AND_VALUE:
			if (groupPadding > 0) {
				DRDesignFiller filler = new DRDesignFiller();
				filler.setWidth(groupPadding);
				filler.setHeight(0);
				header.addComponent(HorizontalCellComponentAlignment.CENTER, null, filler);
			}
			header.addComponent(HorizontalCellComponentAlignment.LEFT, null, titleComponent(group));
			header.addComponent(valueComponent(group));
			break;
		case VALUE:			
			if (groupPadding > 0) {
				DRDesignFiller filler = new DRDesignFiller();
				filler.setWidth(groupPadding);
				filler.setHeight(0);
				header.addComponent(HorizontalCellComponentAlignment.CENTER, null, filler);
			}
			header.addComponent(valueComponent(group));
			break;
		default:
			throw new DRDesignReportException("Group header layout " + templateTransform.getGroupHeaderLayout(group).name() + " not supported");
		}
		if (!header.isEmpty()) {
			DRIBand bnd = group.getHeaderBand();
			DRDesignBand band = accessor.getBandTransform().band("groupHeaderTitleAndValue", bnd, templateTransform.getGroupHeaderSplitType(bnd));
			band.addComponent(header);
			designGroup.addHeaderBand(band);
		}
		
		DRIBand band = group.getHeaderBand();
		designGroup.addHeaderBand(groupBand("groupHeader", band, templateTransform.getGroupHeaderSplitType(band), groupPadding, designGroup));
		band = group.getFooterBand();
		designGroup.addFooterBand(groupBand("groupFooter", band, templateTransform.getGroupFooterSplitType(band), groupPadding, designGroup));
		if (templateTransform.isGroupShowColumnHeaderAndFooter(group)) {
			designGroup.addHeaderBand(accessor.getBandTransform().getColumnHeaderForGroupBand());
			designGroup.addFooterBand(accessor.getBandTransform().getColumnFooterBand());
		}
	}
	
	private DRDesignBand groupBand(String name, DRIBand band, SplitType splitType, int groupPadding, DRDesignGroup resetGroup) throws DRException {
		DRDesignBand designBand = accessor.getBandTransform().band(name, band, splitType, ResetType.GROUP, resetGroup);
		if (groupPadding > 0) {
			DRDesignFiller filler = new DRDesignFiller();
			filler.setWidth(groupPadding);
			filler.setHeight(0);
			DRDesignList list = new DRDesignList();
			list.addComponent(HorizontalCellComponentAlignment.CENTER, null, filler);
			list.addComponent(designBand.getList());
			designBand.setList(list);
		}
		return designBand;
	}
	
	//title
	@SuppressWarnings("unchecked")
	private DRDesignComponent titleComponent(DRIGroup group) throws DRException {
		@SuppressWarnings("rawtypes")
		DRTextField titleField = new DRTextField();
		titleField.setValueExpression(group.getTitleExpression());
		titleField.setStyle((DRStyle) group.getTitleStyle());
		titleField.setWidth(group.getTitleWidth());
		DRDesignTextField designTitleField = accessor.getComponentTransform().textField(titleField, DefaultStyleType.GROUP_TITLE);
		designTitleField.setUniqueName("group_" + group.getName() + ".title");
		return designTitleField;	
	}
	
	//value
	private DRDesignComponent valueComponent(DRIGroup group) throws DRException {
		DRDesignTextField designValueField = accessor.getComponentTransform().textField(group.getValueField(), DefaultStyleType.GROUP);
		designValueField.setUniqueName("group_" + group.getName());		
		return designValueField;
	}
	
	//group
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private DRDesignGroup group(DRIGroup group) throws DRException {
		TemplateTransform templateTransform = accessor.getTemplateTransform();
		DRDesignGroup designGroup = new DRDesignGroup(group.getName());
		designGroup.setStartInNewPage(templateTransform.isGroupStartInNewPage(group));
		designGroup.setStartInNewColumn(templateTransform.isGroupStartInNewColumn(group));
		designGroup.setReprintHeaderOnEachPage(templateTransform.isGroupReprintHeaderOnEachPage(group));
		DRIExpression<?> groupExpression = group.getValueField().getValueExpression();
		if (templateTransform.isGroupByDataType(group) && group.getValueField().getDataType() != null) {
			accessor.getExpressionTransform().transformExpression(groupExpression);
			DRIDataType<?, ?> dataType = group.getValueField().getDataType();
			groupExpression = new GroupByDataTypeExpression(groupExpression, dataType);
		}
		groupExpression = new DRVariable(groupExpression, Calculation.NOTHING);
		designGroup.setGroupExpression(accessor.getExpressionTransform().transformExpression(groupExpression));		
		return designGroup;
	}
	
	protected DRDesignGroup getGroup(DRIGroup group) {
		return designGroups.get(group);
	}

	protected DRIGroup getFirstGroup() {
		if (!groups.isEmpty()) {
			return groups.get(0);
		}
		return null;
	}

	protected DRIGroup getBeforeGroup(DRIGroup group) {
		if (groups.size() > 1 && groups.indexOf(group) > 0) {
			return groups.get(groups.indexOf(group) - 1);
		}
		return null;
	}

	protected DRIGroup getLastGroup() {
		if (!groups.isEmpty()) {
			return groups.get(groups.size() - 1);
		}
		return null;
	}
	
	protected int getGroupPadding() {
		return groupPadding;
	}
	
	protected List<DRIColumn<?>> getHideGroupColumns() {
		return hideGroupColumns;
	}
	
	public Collection<DRDesignGroup> getGroups() {
		return designGroups.values();
	}
	
	private class GroupByDataTypeExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
		
		private DRIExpression<?> valueExpression;
		DRIDataType<?, ?> dataType;
				
		public GroupByDataTypeExpression(DRIExpression<?> valueExpression, DRIDataType<?, ?> dataType) {
			this.valueExpression = valueExpression;
			this.dataType = dataType;
		}

		public String evaluate(ReportParameters reportParameters) {
			return dataType.valueToString(valueExpression.getName(), reportParameters);
		}		
	}
}
