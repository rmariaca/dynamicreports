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

package net.sf.dynamicreports.report.components.googlecharts.geomap;

import java.awt.Color;
import java.sql.Connection;
import java.util.List;

import net.sf.dynamicreports.report.builder.DatasetBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.VariableBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.component.DimensionComponentBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.jasperreports.engine.JRDataSource;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GeoMapBuilder extends DimensionComponentBuilder<GeoMapBuilder, DRGeoMap> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	public GeoMapBuilder() {
		super(new DRGeoMap());
	}

	public GeoMapBuilder setShowLegend(Boolean showLegend) {
		getObject().setShowLegend(showLegend);
		return this;
	}

	public GeoMapBuilder setDataMode(GeoMapDataMode dataMode) {
		getObject().setDataMode(dataMode);
		return this;
	}

	public GeoMapBuilder setColors(List<Color> colors) {
		getObject().setColors(colors);
		return this;
	}

	//region
	public GeoMapBuilder setRegion(String region) {
		getObject().setRegionExpression(Expressions.text(region));
		return this;
	}

	public GeoMapBuilder setRegion(DRIExpression<String> regionExpression) {
		getObject().setRegionExpression(regionExpression);
		return this;
	}

	//dataset
	public GeoMapBuilder setLocation(ValueColumnBuilder<?, String> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setLocationExpression(column.getColumn());
		return this;
	}

	public GeoMapBuilder setLocation(String fieldName, Class<String> valueClass) {
		return setLocation(DynamicReports.field(fieldName, valueClass));
	}

	public GeoMapBuilder setLocation(FieldBuilder<String> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setLocationExpression(field.build());
		return this;
	}

	public GeoMapBuilder setLocation(DRIExpression<String> expression) {
		getDataset().setLocationExpression(expression);
		return this;
	}

	public GeoMapBuilder setValue(ValueColumnBuilder<?, ? extends Number> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setValueExpression(column.getColumn());
		return this;
	}

	public GeoMapBuilder setValue(FieldBuilder<? extends Number> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setValueExpression(field.build());
		return this;
	}

	public GeoMapBuilder setValue(DRIExpression<? extends Number> valueExpression) {
		getDataset().setValueExpression(valueExpression);
		return this;
	}

	public GeoMapBuilder setValue(VariableBuilder<? extends Number> variable) {
		Validate.notNull(variable, "variable must not be null");
		getDataset().setValueExpression(variable.build());
		return this;
	}

	public GeoMapBuilder setTooltip(ValueColumnBuilder<?, String> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setTooltipExpression(column.getColumn());
		return this;
	}

	public GeoMapBuilder setTooltip(String fieldName, Class<String> valueClass) {
		return setTooltip(DynamicReports.field(fieldName, valueClass));
	}

	public GeoMapBuilder setTooltip(FieldBuilder<String> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setTooltipExpression(field.build());
		return this;
	}

	public GeoMapBuilder setTooltip(DRIExpression<String> expression) {
		getDataset().setTooltipExpression(expression);
		return this;
	}

	//subdataset
	public GeoMapBuilder setSubDataset(DatasetBuilder subDataset) {
		Validate.notNull(subDataset, "subDataset must not be null");
		getObject().getDataset().setSubDataset(subDataset.build());
		return this;
	}

	public GeoMapBuilder setDataSource(JRDataSource dataSource) {
		DatasetBuilder dataset = DynamicReports.dataset();
		dataset.setDataSource(dataSource);
		return setSubDataset(dataset);
	}

	public GeoMapBuilder setDataSource(String sql, Connection connection) {
		DatasetBuilder dataset = DynamicReports.dataset();
		dataset.setDataSource(sql, connection);
		return setSubDataset(dataset);
	}

	private DRGeoMapDataset getDataset() {
		return getObject().getDataset();
	}
}
