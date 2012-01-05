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

package net.sf.dynamicreports.jasper.base.templatedesign;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.jasper.transformation.ConstantTransform;
import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.base.DRMargin;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.definition.DRIField;
import net.sf.dynamicreports.report.definition.DRIMargin;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperTemplateDesign implements DRITemplateDesign<JasperDesign> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private JasperDesign jasperDesign;
	private List<DRIField<?>> fields;
	private DRMargin margin;
	private transient ByteArrayOutputStream templateDesign;

	public JasperTemplateDesign(JasperDesign jasperDesign) throws DRException {
		init(jasperDesign);
	}

	public JasperTemplateDesign(File file) throws DRException {
		Validate.notNull(file, "file must not be null");
		try {
			init(JRXmlLoader.load(file));
		} catch (JRException e) {
			throw new DRException(e);
		}
	}

	public JasperTemplateDesign(String fileName) throws DRException {
		Validate.notNull(fileName, "fileName must not be null");
		try {
			init(JRXmlLoader.load(fileName));
		} catch (JRException e) {
			throw new DRException(e);
		}
	}

	public JasperTemplateDesign(InputStream inputStream) throws DRException {
		Validate.notNull(inputStream, "inputStream must not be null");
		try {
			init(JRXmlLoader.load(inputStream));
		} catch (JRException e) {
			throw new DRException(e);
		}
	}

	public JasperTemplateDesign(URL url) throws DRException {
		Validate.notNull(url, "url must not be null");
		try {
			init(JRXmlLoader.load(url.openStream()));
		} catch (JRException e) {
			throw new DRException(e);
		} catch (IOException e) {
			throw new DRException(e);
		}
	}

	private void init(JasperDesign jasperDesign) throws DRException {
		Validate.notNull(jasperDesign, "jasperDesign must not be null");
		this.jasperDesign = jasperDesign;

		this.fields = new ArrayList<DRIField<?>>();
		for (JRField jrField : jasperDesign.getFields()) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			DRField<?> field = new DRField(jrField.getName(), jrField.getValueClass());
			fields.add(field);
		}

		this.margin = new DRMargin();
		margin.setTop(jasperDesign.getTopMargin());
		margin.setLeft(jasperDesign.getLeftMargin());
		margin.setBottom(jasperDesign.getBottomMargin());
		margin.setRight(jasperDesign.getRightMargin());
	}

	public List<DRIField<?>> getFields() {
		return fields;
	}

	public boolean isDefinedParameter(String name) {
		JRParameter parameter = jasperDesign.getParametersMap().get(name);
		return parameter != null;
	}

	public String getResourceBundleName() {
		return jasperDesign.getResourceBundle();
	}

	public Boolean getIgnorePagination() {
		return jasperDesign.isIgnorePagination();
	}

	public WhenNoDataType getWhenNoDataType() {
		return ConstantTransform.whenNoDataType(jasperDesign.getWhenNoDataTypeValue());
	}

	public Boolean getTitleOnANewPage() {
		return jasperDesign.isTitleNewPage();
	}

	public Boolean getSummaryOnANewPage() {
		return jasperDesign.isSummaryNewPage();
	}

	public Boolean getSummaryWithPageHeaderAndFooter() {
		return jasperDesign.isSummaryWithPageHeaderAndFooter();
	}

	public Boolean getFloatColumnFooter() {
		return jasperDesign.isFloatColumnFooter();
	}

	public Integer getPageWidth() {
		return jasperDesign.getPageWidth();
	}

	public Integer getPageHeight() {
		return jasperDesign.getPageHeight();
	}

	public PageOrientation getPageOrientation() {
		return ConstantTransform.pageOrientation(jasperDesign.getOrientationValue());
	}

	public DRIMargin getPageMargin() {
		return margin;
	}

	public Integer getPageColumnsPerPage() {
		return jasperDesign.getColumnCount();
	}

	public Integer getPageColumnSpace() {
		return jasperDesign.getColumnSpacing();
	}

	public Integer getPageColumnWidth() {
		return jasperDesign.getColumnWidth();
	}

	public int getTitleComponentsCount() {
		return getBandComponentsCount(jasperDesign.getTitle());
	}

	public int getPageHeaderComponentsCount() {
		return getBandComponentsCount(jasperDesign.getPageHeader());
	}

	public int getPageFooterComponentsCount() {
		return getBandComponentsCount(jasperDesign.getPageFooter());
	}

	public int getColumnHeaderComponentsCount() {
		return getBandComponentsCount(jasperDesign.getColumnHeader());
	}

	public int getColumnFooterComponentsCount() {
		return getBandComponentsCount(jasperDesign.getColumnFooter());
	}

	public int getLastPageFooterComponentsCount() {
		return getBandComponentsCount(jasperDesign.getLastPageFooter());
	}

	public int getSummaryComponentsCount() {
		return getBandComponentsCount(jasperDesign.getSummary());
	}

	public int getNoDataComponentsCount() {
		return getBandComponentsCount(jasperDesign.getNoData());
	}

	public int getBackgroundComponentsCount() {
		return getBandComponentsCount(jasperDesign.getBackground());
	}

	private int getBandComponentsCount(JRBand band) {
		if (band != null && band.getElements() != null) {
			return band.getElements().length;
		}
		return 0;
	}

	public JasperDesign getDesign() throws DRException {
		try {
			if (templateDesign == null) {
				templateDesign = new ByteArrayOutputStream();
				JRXmlWriter.writeReport(jasperDesign, templateDesign, "UTF-8");
			}

			return JRXmlLoader.load(new ByteArrayInputStream(templateDesign.toByteArray()));
		} catch (JRException e) {
			throw new DRException(e);
		}
	}
}
