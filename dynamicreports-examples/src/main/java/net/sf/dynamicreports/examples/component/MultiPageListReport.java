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

package net.sf.dynamicreports.examples.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class MultiPageListReport {

	public MultiPageListReport() {
		build();
	}

	private void build() {
		StyleBuilder style = stl.style(stl.pen1Point())
			.setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);

		MultiPageListBuilder multiPageList = cmp.multiPageList();
		for (int i = 0; i < 10; i++) {
			TextFieldBuilder<String> textField = cmp.text("Title component " + (i + 1))
				.setFixedHeight(100)
				.setStyle(style);
			multiPageList.add(textField);
		}

		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .setTextStyle(stl.style())
			  .title(
			  		Templates.createTitleComponent("MultiPageList"))
			  	.summary(
			  	multiPageList)
			  .pageFooter(Templates.footerComponent)
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MultiPageListReport();
	}
}