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

package net.sf.dynamicreports.jasper.base.tableofcontents;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.List;

import net.sf.dynamicreports.design.definition.DRIDesignPage;
import net.sf.dynamicreports.jasper.base.JasperReportDesign;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperTocReport {

	public static void createTocReport(JasperReportDesign jasperReportDesign, JasperPrint jasperPrint) throws DRException, JRException {
		JasperTocScriptlet scriptlet = (JasperTocScriptlet) jasperReportDesign.getScriptlet();
		List<JasperTocHeading> headings = scriptlet.getHeadings();
		if (headings != null && !headings.isEmpty()) {
			JasperReportBuilder tocReport = report();

			int levels = 0;
			for (JasperTocHeading heading : headings) {
				if (heading.getLevel() > levels) {
					levels = heading.getLevel();
				}
			}
			levels++;

			DRITableOfContentsCustomizer tableOfContents = jasperReportDesign.getReport().getTableOfContentsCustomizer();
			tableOfContents.setReport(tocReport);
			tableOfContents.setHeadings(headings.size());
			tableOfContents.setLevels(levels);
			tableOfContents.customize();

			DRIDesignPage designPage = jasperReportDesign.getReport().getPage();
			tocReport.setPageFormat(designPage.getWidth(), designPage.getHeight(), designPage.getOrientation());
			tocReport.setDataSource(new JRBeanCollectionDataSource(headings));

			JasperPrint tocJasperPrint = tocReport.toJasperPrint();
			for (int i = 0; i < tocJasperPrint.getPages().size(); i++) {
				JRPrintPage page = (JRPrintPage) tocJasperPrint.getPages().get(i);
				jasperPrint.addPage(i, page);
			}
			for (JRStyle style : tocJasperPrint.getStyles()) {
				jasperPrint.addStyle(style);
			}
		}
	}
}