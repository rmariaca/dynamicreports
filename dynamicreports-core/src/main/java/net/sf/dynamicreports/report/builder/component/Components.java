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

package net.sf.dynamicreports.report.builder.component;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.definition.expression.DRIComplexExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class Components {

	//horizontal
	public static HorizontalListBuilder horizontalList() {
		return new HorizontalListBuilder();
	}

	public static HorizontalListBuilder horizontalList(ComponentBuilder<?, ?> ...components) {
		return new HorizontalListBuilder().add(components);
	}

	public static HorizontalListBuilder horizontalList(HorizontalListCellBuilder ...cells) {
		return new HorizontalListBuilder().add(cells);
	}
	
	public static HorizontalListCellBuilder hListCell(ComponentBuilder<?, ?> component) {
		Validate.notNull(component, "component must not be null");
		return new HorizontalListCellBuilder(component);
	}
	
	//horizontal flow
	public static HorizontalListBuilder horizontalFlowList() {
		return new HorizontalFlowListBuilder();
	}

	public static HorizontalListBuilder horizontalFlowList(ComponentBuilder<?, ?> ...components) {
		return new HorizontalFlowListBuilder().add(components);
	}

	public static HorizontalListBuilder horizontalFlowList(HorizontalListCellBuilder ...cells) {
		return new HorizontalFlowListBuilder().add(cells);
	}
	
	//vertical	
	public static VerticalListBuilder verticalList() {
		return new VerticalListBuilder();
	}

	public static VerticalListBuilder verticalList(ComponentBuilder<?, ?> ...components) {
		return new VerticalListBuilder().add(components);
	}

	public static VerticalListBuilder verticalList(VerticalListCellBuilder ...cells) {
		return new VerticalListBuilder().add(cells);
	}
	
	public static VerticalListCellBuilder vListCell(ComponentBuilder<?, ?> component) {
		Validate.notNull(component, "component must not be null");
		return new VerticalListCellBuilder(component);
	}
	
	public static CurrentDateBuilder currentDate() {
		return new CurrentDateBuilder();
	}
	
	public static PageNumberBuilder pageNumber() {
		return new PageNumberBuilder();
	}
	
	public static PageXofYBuilder pageXofY() {
		return new PageXofYBuilder();
	}
	
	public static PageXslashYBuilder pageXslashY() {
		return new PageXslashYBuilder();
	}

	public static TotalPagesBuilder totalPages() {
		return new TotalPagesBuilder();
	}
	
	//text
	public static TextFieldBuilder<String> text(String text) {
		return new TextFieldBuilder<String>().setText(text);
	}

	public static <T extends Number> TextFieldBuilder<T> text(T number) {
		return new TextFieldBuilder<T>().setText(number);
	}
	
	public static <T> TextFieldBuilder<T> text(DRISimpleExpression<T> textExpression) {
		return new TextFieldBuilder<T>().setText(textExpression);
	}
	
	public static <T> TextFieldBuilder<T> text(DRIComplexExpression<T> textExpression) {
		return new TextFieldBuilder<T>().setText(textExpression);
	}
	
	//filler
	public static FillerBuilder filler() {
		return new FillerBuilder();
	}
	
	//image
	public static ImageBuilder image(DRISimpleExpression<?> imageExpression) {
		return new ImageBuilder().setImage(imageExpression);
	}
	
	public static ImageBuilder image(String imagePath) {
		return new ImageBuilder().setImage(imagePath);
	}
	
	public static ImageBuilder image(Image image) {
		return new ImageBuilder().setImage(image);
	}
	
	public static ImageBuilder image(InputStream imageInputStream) {
		return new ImageBuilder().setImage(imageInputStream);
	}
	
	public static ImageBuilder image(URL imageUrl) {
		return new ImageBuilder().setImage(imageUrl);
	}
	
	//subreport
	public static SubreportBuilder subreport(JasperReportBuilder reportBuilder) {
		SubreportBuilder subreport = new SubreportBuilder();
		subreport.setReport(reportBuilder);
		if (reportBuilder.getConnection() != null) {
			subreport.setConnection(reportBuilder.getConnection());
		}
		if (reportBuilder.getDataSource() != null) {
			subreport.setDataSource(reportBuilder.getDataSource());
		}
		return subreport;
	}

	public static SubreportBuilder subreport(JasperReport jasperReport) {
		return new SubreportBuilder().setReport(jasperReport);
	}
	
	public static SubreportBuilder subreport(DRISimpleExpression<?> reportExpression) {
		return new SubreportBuilder().setReport(reportExpression);
	}
}
