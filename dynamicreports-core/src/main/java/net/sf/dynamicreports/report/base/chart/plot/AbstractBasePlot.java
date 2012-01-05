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

package net.sf.dynamicreports.report.base.chart.plot;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.definition.chart.plot.DRIBasePlot;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class AbstractBasePlot implements DRIBasePlot {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Orientation orientation;
	private List<Color> seriesColors;

	protected AbstractBasePlot() {
		init();
	}

	protected void init() {
		this.seriesColors = new ArrayList<Color>();
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void addSeriesColor(Color color) {
		Validate.notNull(color, "color must not be null");
		this.seriesColors.add(color);
	}

	public void setSeriesColors(List<Color> seriesColors) {
		Validate.notNull(seriesColors, "seriesColors must not be null");
		Validate.noNullElements(seriesColors, "seriesColors must not contain null color");
		this.seriesColors = seriesColors;
	}

	public List<Color> getSeriesColors() {
		return seriesColors;
	}
}
