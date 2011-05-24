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

package net.sf.dynamicreports.design.base.component;

import net.sf.dynamicreports.design.definition.component.DRIDesignSubreport;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignSubreport extends DRDesignComponent implements DRIDesignSubreport {
	private DRIDesignExpression reportExpression;
	private DRIDesignExpression connectionExpression;
	private DRIDesignExpression dataSourceExpression;
	private Boolean runToBottom;

	public DRDesignSubreport() {
		super("subreport");
	}

	public DRIDesignExpression getReportExpression() {
		return reportExpression;
	}

	public void setReportExpression(DRIDesignExpression reportExpression) {
		this.reportExpression = reportExpression;
	}

	public DRIDesignExpression getConnectionExpression() {
		return connectionExpression;
	}

	public void setConnectionExpression(DRIDesignExpression connectionExpression) {
		this.connectionExpression = connectionExpression;
	}

	public DRIDesignExpression getDataSourceExpression() {
		return dataSourceExpression;
	}

	public void setDataSourceExpression(DRIDesignExpression dataSourceExpression) {
		this.dataSourceExpression = dataSourceExpression;
	}

	public Boolean getRunToBottom() {
		return runToBottom;
	}

	public void setRunToBottom(Boolean runToBottom) {
		this.runToBottom = runToBottom;
	}
}
