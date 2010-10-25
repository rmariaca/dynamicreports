package net.sf.dynamicreports.report.definition;

import java.io.Serializable;

import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.exception.DRException;

public interface DRITemplateDesign<T> extends Serializable {
	
	public String getResourceBundleName();
	
	public Boolean getIgnorePagination();
	
	public WhenNoDataType getWhenNoDataType();
	
	public Boolean getTitleOnANewPage();
	
	public Boolean getSummaryOnANewPage();
	
	public Boolean getSummaryWithPageHeaderAndFooter();
	
	public Boolean getFloatColumnFooter();

	public Integer getPageWidth();

	public Integer getPageHeight();

	public PageOrientation getPageOrientation();

	public DRIMargin getPageMargin();

	public Integer getPageColumnsPerPage();

	public Integer getPageColumnSpace();
	
	public Integer getPageColumnWidth();
	
	public int getTitleComponentsCount();
	
	public int getPageHeaderComponentsCount();
	
	public int getPageFooterComponentsCount();
	
	public int getColumnHeaderComponentsCount();
	
	public int getColumnFooterComponentsCount();
	
	public int getLastPageFooterComponentsCount();
	
	public int getSummaryComponentsCount();
	
	public int getNoDataComponentsCount();
	
	public int getBackgroundComponentsCount();
	
	public T getDesign() throws DRException;
}
