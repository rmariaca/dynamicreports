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

package net.sf.dynamicreports.examples.adhoc;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SimpleAdhocReport {

	public SimpleAdhocReport() {
		build();
	}

	private void build() {
		AdhocConfiguration configuration = new AdhocConfiguration();
		AdhocReport report = new AdhocReport();
		configuration.setReport(report);

		AdhocColumn column = new AdhocColumn();
		column.setName("item");
		report.addColumn(column);

		column = new AdhocColumn();
		column.setName("quantity");
		report.addColumn(column);

		//AdhocManager.saveConfiguration(configuration, outputStream);
		//AdhocConfiguration configuration = AdhocManager.loadConfiguration(configuration, inputStream);

	  try {
			JasperReportBuilder reportBuilder = AdhocManager.createReport(configuration.getReport());
			reportBuilder.setDataSource(createDataSource());
			reportBuilder.show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "orderdate", "quantity", "unitprice");
		for (int i = 0; i < 20; i++) {
			dataSource.add("Book", new Date(), (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
		}
		return dataSource;
	}

	public static void main(String[] args) {
		new SimpleAdhocReport();
	}
}