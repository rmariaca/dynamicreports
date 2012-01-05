/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
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

package net.sf.dynamicreports.examples.complex;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class AbstractReportMain<T extends ReportDesign<U>, U extends ReportData> {
	
	public AbstractReportMain() {
		build();
	}
	
	protected void build() {
		try {
			JasperReportBuilder reportBuilder = DynamicReports.report();			
			U data = getReportData();
			if (data != null) {
				reportBuilder.setDataSource(data.createDataSource());
			}
			getReportDesign().configureReport(reportBuilder, data);
			reportBuilder.show();						
		} catch (DRException e) {
			e.printStackTrace();	
		}
	}
	
	protected U getReportData() {
		return null;
	}
	
	protected abstract T getReportDesign();	
}
