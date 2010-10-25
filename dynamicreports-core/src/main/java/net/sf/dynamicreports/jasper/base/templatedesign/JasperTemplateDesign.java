package net.sf.dynamicreports.jasper.base.templatedesign;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import net.sf.dynamicreports.jasper.transformation.ConstantTransform;
import net.sf.dynamicreports.report.base.DRMargin;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.definition.DRIMargin;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

import org.apache.commons.lang.Validate;

public class JasperTemplateDesign implements DRITemplateDesign<JasperDesign> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private JasperDesign jasperDesign;
	private ByteArrayOutputStream templateDesign;

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
	
	private void init(JasperDesign jasperDesign) throws DRException {
		Validate.notNull(jasperDesign, "jasperDesign must not be null");
		this.jasperDesign = jasperDesign;
		templateDesign = new ByteArrayOutputStream();
		try {
			JRXmlWriter.writeReport(jasperDesign, templateDesign, "UTF-8");
		} catch (JRException e) {
			throw new DRException(e);
		}
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
		DRMargin margin = new DRMargin();
		margin.setTop(jasperDesign.getTopMargin());
		margin.setLeft(jasperDesign.getLeftMargin());
		margin.setBottom(jasperDesign.getBottomMargin());
		margin.setRight(jasperDesign.getRightMargin());
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
		return jasperDesign.getTitle().getElements().length;
	}

	public int getPageHeaderComponentsCount() {
		return jasperDesign.getPageHeader().getElements().length;
	}

	public int getPageFooterComponentsCount() {
		return jasperDesign.getPageFooter().getElements().length;
	}

	public int getColumnHeaderComponentsCount() {
		return jasperDesign.getColumnHeader().getElements().length;
	}

	public int getColumnFooterComponentsCount() {
		return jasperDesign.getColumnFooter().getElements().length;
	}

	public int getLastPageFooterComponentsCount() {
		return jasperDesign.getLastPageFooter().getElements().length;
	}

	public int getSummaryComponentsCount() {
		return jasperDesign.getSummary().getElements().length;
	}

	public int getNoDataComponentsCount() {
		return jasperDesign.getNoData().getElements().length;
	}

	public int getBackgroundComponentsCount() {
		return jasperDesign.getBackground().getElements().length;
	}
	
	public JasperDesign getDesign() throws DRException {
		try {
			return JRXmlLoader.load(new ByteArrayInputStream(templateDesign.toByteArray()));
		} catch (JRException e) {
			throw new DRException(e);
		}
	}
}
