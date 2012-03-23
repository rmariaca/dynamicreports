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

package net.sf.dynamicreports.design.base.component;

import net.sf.dynamicreports.design.base.DRDesignHyperLink;
import net.sf.dynamicreports.design.definition.component.DRIDesignHyperLinkComponent;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class DRDesignHyperlinkComponent extends DRDesignComponent implements DRIDesignHyperLinkComponent {
	private DRIDesignExpression anchorNameExpression;
	private Integer bookmarkLevel;
	private DRDesignHyperLink hyperLink;

	public DRDesignHyperlinkComponent(String name) {
		super(name);
	}

	public DRIDesignExpression getAnchorNameExpression() {
		return anchorNameExpression;
	}

	public void setAnchorNameExpression(DRIDesignExpression anchorNameExpression) {
		this.anchorNameExpression = anchorNameExpression;
	}

	public Integer getBookmarkLevel() {
		return bookmarkLevel;
	}

	public void setBookmarkLevel(Integer bookmarkLevel) {
		this.bookmarkLevel = bookmarkLevel;
	}

	public DRDesignHyperLink getHyperLink() {
		return hyperLink;
	}

	public void setHyperLink(DRDesignHyperLink hyperLink) {
		this.hyperLink = hyperLink;
	}
}
