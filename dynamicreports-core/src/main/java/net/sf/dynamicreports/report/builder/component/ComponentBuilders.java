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

import net.sf.dynamicreports.report.definition.expression.DRIComplexExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class ComponentBuilders {

	//horizontal
	public HorizontalListBuilder horizontalList() {
		return Components.horizontalList();
	}

	public HorizontalListBuilder horizontalList(ComponentBuilder<?, ?> ...components) {
		return Components.horizontalList(components);
	}

	public HorizontalListBuilder horizontalList(HorizontalListCellBuilder ...cells) {
		return Components.horizontalList(cells);
	}
	
	public HorizontalListCellBuilder hListCell(ComponentBuilder<?, ?> component) {
		return Components.hListCell(component);
	}
	
	//horizontal flow
	public HorizontalListBuilder horizontalFlowList() {
		return Components.horizontalFlowList();
	}

	public HorizontalListBuilder horizontalFlowList(ComponentBuilder<?, ?> ...components) {
		return Components.horizontalFlowList(components);
	}

	public HorizontalListBuilder horizontalFlowList(HorizontalListCellBuilder ...cells) {
		return Components.horizontalFlowList(cells);
	}
	
	//vertical	
	public VerticalListBuilder verticalList() {
		return Components.verticalList();
	}

	public VerticalListBuilder verticalList(ComponentBuilder<?, ?> ...components) {
		return Components.verticalList(components);
	}

	public VerticalListBuilder verticalList(VerticalListCellBuilder ...cells) {
		return Components.verticalList(cells);
	}
	
	public VerticalListCellBuilder vListCell(ComponentBuilder<?, ?> component) {
		return Components.vListCell(component);
	}
	
	public CurrentDateBuilder currentDate() {
		return Components.currentDate();
	}
	
	public PageNumberBuilder pageNumber() {
		return Components.pageNumber();
	}
	
	public PageXofYBuilder pageXofY() {
		return Components.pageXofY();
	}
	
	public PageXslashYBuilder pageXslashY() {
		return Components.pageXslashY();
	}

	public TotalPagesBuilder totalPages() {
		return Components.totalPages();
	}
	
	public TextFieldBuilder<String> text(String text) {
		return Components.text(text);
	}

	public TextFieldBuilder<Number> text(Number number) {
		return Components.text(number);
	}
	
	public <T> TextFieldBuilder<T> text(DRISimpleExpression<T> textExpression) {
		return Components.text(textExpression);
	}
	
	public <T> TextFieldBuilder<T> text(DRIComplexExpression<T> textExpression) {
		return Components.text(textExpression);
	}
	
	public FillerBuilder filler() {
		return Components.filler();
	}
	
	public ImageBuilder image(DRISimpleExpression<?> imageExpression) {
		return Components.image(imageExpression);
	}
	
	public ImageBuilder image(String imagePath) {
		return Components.image(imagePath);
	}
	
	public ImageBuilder image(Image image) {
		return Components.image(image);
	}
	
	public ImageBuilder image(InputStream image) {
		return Components.image(image);
	}
}
