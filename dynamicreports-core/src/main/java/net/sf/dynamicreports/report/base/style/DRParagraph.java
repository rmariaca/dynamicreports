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

package net.sf.dynamicreports.report.base.style;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.LineSpacing;
import net.sf.dynamicreports.report.definition.style.DRIParagraph;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRParagraph implements DRIParagraph {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private LineSpacing lineSpacing;
	private Integer firstLineIndent;
	private Integer leftIndent;
	private Integer rightIndent;

	public LineSpacing getLineSpacing() {
		return lineSpacing;
	}

	public void setLineSpacing(LineSpacing lineSpacing) {
		this.lineSpacing = lineSpacing;
	}

	public Integer getFirstLineIndent() {
		return firstLineIndent;
	}

	public void setFirstLineIndent(Integer firstLineIndent) {
		this.firstLineIndent = firstLineIndent;
	}

	public Integer getLeftIndent() {
		return leftIndent;
	}

	public void setLeftIndent(Integer leftIndent) {
		this.leftIndent = leftIndent;
	}

	public Integer getRightIndent() {
		return rightIndent;
	}

	public void setRightIndent(Integer rightIndent) {
		this.rightIndent = rightIndent;
	}

}
