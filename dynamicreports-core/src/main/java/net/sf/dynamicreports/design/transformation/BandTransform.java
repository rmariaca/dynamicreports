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

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.base.DRDesignBand;
import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.report.base.DRBand;
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
	private List<DRDesignBand> detailBands;
	private DRDesignBand columnDetailBand;
	private DRDesignBand lastPageFooterBand;
	private DRDesignBand summaryBand;
	private DRDesignBand noDataBand;
	private DRDesignBand backgroundBand;

	public BandTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
		this.detailBands = new ArrayList<DRDesignBand>();
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

		band = report.getDetailHeaderBand();
		detailBands.add(band("detailHeader", band, templateTransform.getDetailSplitType(band), ResetType.REPORT, null));

		columnDetailBand = band("detail", new DRBand(), templateTransform.getDetailSplitType(band), ResetType.REPORT, null);
		detailBands.add(columnDetailBand);

		band = report.getDetailFooterBand();
		detailBands.add(band("detailFooter", band, templateTransform.getDetailSplitType(band), ResetType.REPORT, null));

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
		BandComponentsTransform bandComponents = new BandComponentsTransform(accessor);
		DRITemplateDesign<?> templateDesign = accessor.getReport().getTemplateDesign();

		titleBand = bandComponents.prepareBand(titleBand, maxWidth, templateDesign.getTitleComponentsCount());
		pageHeaderBand = bandComponents.prepareBand(pageHeaderBand, maxWidth, templateDesign.getPageHeaderComponentsCount());
		pageFooterBand = bandComponents.prepareBand(pageFooterBand, maxWidth, templateDesign.getPageFooterComponentsCount());
		columnHeaderBand = bandComponents.prepareBand(columnHeaderBand, maxColumnWidth, templateDesign.getColumnHeaderComponentsCount());
		columnFooterBand = bandComponents.prepareBand(columnFooterBand, maxColumnWidth, templateDesign.getColumnFooterComponentsCount());
		List<DRDesignBand> removeDetailBands = new ArrayList<DRDesignBand>();
		for (DRDesignBand detailBand : detailBands) {
			if (bandComponents.prepareBand(detailBand, maxColumnWidth, 0) == null) {
				removeDetailBands.add(detailBand);
			}
		}
		detailBands.removeAll(removeDetailBands);
		lastPageFooterBand = bandComponents.prepareBand(lastPageFooterBand, maxWidth, templateDesign.getLastPageFooterComponentsCount());
		summaryBand = bandComponents.prepareBand(summaryBand, maxWidth, templateDesign.getSummaryComponentsCount());
		noDataBand = bandComponents.prepareBand(noDataBand, maxWidth, templateDesign.getNoDataComponentsCount());
		backgroundBand = bandComponents.prepareBand(backgroundBand, maxWidth, templateDesign.getBackgroundComponentsCount());
		for (DRDesignGroup group : accessor.getGroupTransform().getGroups()) {
			List<DRDesignBand> bands = new ArrayList<DRDesignBand>();
			for (DRDesignBand band : group.getHeaderBands()) {
				DRDesignBand newBand = bandComponents.prepareBand(band, maxColumnWidth, 0);
				if (newBand != null) {
					bands.add(newBand);
				}
			}
			group.setHeaderBands(bands);
			bands = new ArrayList<DRDesignBand>();
			for (DRDesignBand band : group.getFooterBands()) {
				DRDesignBand newBand = bandComponents.prepareBand(band, maxColumnWidth, 0);
				if (newBand != null) {
					bands.add(newBand);
				}
			}
			group.setFooterBands(bands);
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
		list.setStretchType(accessor.getTemplateTransform().getListStretchType(band.getList()));
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

	public List<DRDesignBand> getDetailBands() {
		return detailBands;
	}

	public DRDesignBand getColumnDetailBand() {
		return columnDetailBand;
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
