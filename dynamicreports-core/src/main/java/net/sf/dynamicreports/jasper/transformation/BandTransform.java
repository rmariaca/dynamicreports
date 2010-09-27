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

package net.sf.dynamicreports.jasper.transformation;

import net.sf.dynamicreports.design.definition.DRIDesignBand;
import net.sf.dynamicreports.design.definition.DRIDesignGroup;
import net.sf.dynamicreports.design.definition.DRIDesignReport;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class BandTransform {
	private JasperTransformAccessor accessor;
	
	public BandTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}
	
	public void transform() {
		DRIDesignReport report = accessor.getReport();
		JasperDesign design = accessor.getDesign();
		
		design.setTitle(band(report.getTitleBand()));	
		design.setPageHeader(band(report.getPageHeaderBand()));
		design.setPageFooter(band(report.getPageFooterBand()));
		design.setColumnHeader(band(report.getColumnHeaderBand()));
		design.setColumnFooter(band(report.getColumnFooterBand()));
		for (DRIDesignGroup group : report.getGroups()) {
			if (group.getHeaderBands() != null) {
				for (DRIDesignBand band : group.getHeaderBands()) {
					JRDesignBand jrBand = band(band);
					if (jrBand != null) {
						((JRDesignSection) accessor.getGroupTransform().getGroup(group).getGroupHeaderSection()).addBand(jrBand);
					}
				}				
			}
			if (group.getFooterBands() != null) {
				for (DRIDesignBand band : group.getFooterBands()) {
					JRDesignBand jrBand = band(band);
					if (jrBand != null) {
						((JRDesignSection) accessor.getGroupTransform().getGroup(group).getGroupFooterSection()).addBand(jrBand);
					}
				}				
			}			
		}
		JRDesignBand jrBand = band(report.getDetailBand());
		if (jrBand != null) {
			((JRDesignSection) design.getDetailSection()).addBand(jrBand);
		}
		design.setLastPageFooter(band(report.getLastPageFooterBand()));
		design.setSummary(band(report.getSummaryBand()));
		design.setNoData(band(report.getNoDataBand()));		
		design.setBackground(band(report.getBackgroundBand()));		
	}
	
	//band
	private JRDesignBand band(DRIDesignBand band) {
		if (band == null) {
			return null;
		}
		JRDesignBand jrBand = new JRDesignBand();
		jrBand.setPrintWhenExpression(accessor.getExpressionTransform().getExpression(band.getBandComponent().getPrintWhenExpression()));
		jrBand.setSplitType(ConstantTransform.splitType(band.getSplitType()));
		jrBand.addElement(accessor.getComponentTransform().component(band.getBandComponent(), ListType.VERTICAL));
		jrBand.setHeight(band.getBandComponent().getHeight());
		return jrBand;
	}
}
