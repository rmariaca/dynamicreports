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

package net.sf.dynamicreports.examples.googlechart;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.util.Arrays;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder;
import net.sf.dynamicreports.report.components.googlecharts.GoogleCharts;
import net.sf.dynamicreports.report.components.googlecharts.geomap.GeoMapDataMode;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GeoMapReport {

	public GeoMapReport() {
		build();
	}

	private void build() {
		try {
			JasperHtmlExporterBuilder htmlExporter = export.htmlExporter("c:/report.html")
          .setImagesDirName("c:/images")
          .setOutputImagesToDir(true);

			report()
			  .setTemplate(Templates.reportTemplate)
			  .title(Templates.createTitleComponent("GeoMap"))
			  .summary(
			  	GoogleCharts.geoMap()
			  		.setDataSource(createDataSource1())
			  		.setLocation(field("location", String.class))
			  		.setValue(field("quantity", Integer.class))
			  		.setFixedHeight(350),
				  GoogleCharts.geoMap()
				  	.setDataSource(createDataSource2())
				  	.setDataMode(GeoMapDataMode.MARKERS)
				  	.setRegion("US")
				  	.setColors(Arrays.asList(Color.decode("#FF8747"), Color.decode("#FFB581"), Color.decode("#C06000")))
			  		.setLocation(field("location", String.class))
			  		.setValue(field("quantity", Integer.class))
			  		.setFixedHeight(350))
			  .pageFooter(Templates.footerComponent)
			  .toHtml(htmlExporter);
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource1() {
		DataSource dataSource = new DataSource("location", "quantity");
		dataSource.add("United States", 170);
		dataSource.add("Canada", 90);
		dataSource.add("France", 120);
		return dataSource;
	}

	private JRDataSource createDataSource2() {
		DataSource dataSource = new DataSource("location", "quantity");
		dataSource.add("New York", 110);
		dataSource.add("Boston", 140);
		dataSource.add("Miami", 80);
		dataSource.add("Chicago", 90);
		dataSource.add("Los Angeles", 120);
		dataSource.add("Houston", 100);
		return dataSource;
	}

	public static void main(String[] args) {
		new GeoMapReport();
	}
}
