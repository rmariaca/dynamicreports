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

package net.sf.dynamicreports.jasper.transformation;

import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignBarbecue;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignBarcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignCodabarBarcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignCode128Barcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignCode39Barcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignDataMatrixBarcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignEan128Barcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignEan13Barcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignEan8Barcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignInterleaved2Of5Barcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignPdf417Barcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignPostnetBarcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignRoyalMailCustomerBarcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignUpcaBarcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignUpceBarcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignUspsIntelligentMailBarcode;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.jasperreports.components.ComponentsExtensionsRegistryFactory;
import net.sf.jasperreports.components.barbecue.BarbecueComponent;
import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;
import net.sf.jasperreports.components.barcode4j.BarcodeComponent;
import net.sf.jasperreports.components.barcode4j.CodabarComponent;
import net.sf.jasperreports.components.barcode4j.Code128Component;
import net.sf.jasperreports.components.barcode4j.Code39Component;
import net.sf.jasperreports.components.barcode4j.DataMatrixComponent;
import net.sf.jasperreports.components.barcode4j.EAN128Component;
import net.sf.jasperreports.components.barcode4j.EAN13Component;
import net.sf.jasperreports.components.barcode4j.EAN8Component;
import net.sf.jasperreports.components.barcode4j.Interleaved2Of5Component;
import net.sf.jasperreports.components.barcode4j.PDF417Component;
import net.sf.jasperreports.components.barcode4j.POSTNETComponent;
import net.sf.jasperreports.components.barcode4j.RoyalMailCustomerComponent;
import net.sf.jasperreports.components.barcode4j.UPCAComponent;
import net.sf.jasperreports.components.barcode4j.UPCEComponent;
import net.sf.jasperreports.components.barcode4j.USPSIntelligentMailComponent;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class BarcodeTransform {
	private JasperTransformAccessor accessor;

	public BarcodeTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}

	protected JRDesignElement transform(DRIDesignBarcode barcode) {
		JRDesignComponentElement jrComponent = new JRDesignComponentElement();
		jrComponent.setComponent(barcodeComponent(barcode));
		jrComponent.setComponentKey(new ComponentKey(ComponentsExtensionsRegistryFactory.NAMESPACE, "jr", barcode.getName()));
		return jrComponent;
	}

	protected JRDesignElement transform(DRIDesignBarbecue barbecue) {
		JRDesignComponentElement jrComponent = new JRDesignComponentElement();
		jrComponent.setComponent(barbecueComponent(barbecue));
		jrComponent.setComponentKey(new ComponentKey(ComponentsExtensionsRegistryFactory.NAMESPACE, "jr", barbecue.getName()));
		return jrComponent;
	}

	private BarcodeComponent barcodeComponent(DRIDesignBarcode barcode) {
		if (barcode instanceof DRIDesignCodabarBarcode) {
			return codabar((DRIDesignCodabarBarcode) barcode);
		}
		else if (barcode instanceof DRIDesignCode128Barcode) {
			return code128((DRIDesignCode128Barcode) barcode);
		}
		else if (barcode instanceof DRIDesignEan128Barcode) {
			return ean128((DRIDesignEan128Barcode) barcode);
		}
		else if (barcode instanceof DRIDesignDataMatrixBarcode) {
			return dataMatrix((DRIDesignDataMatrixBarcode) barcode);
		}
		else if (barcode instanceof DRIDesignCode39Barcode) {
			return code39((DRIDesignCode39Barcode) barcode);
		}
		else if (barcode instanceof DRIDesignInterleaved2Of5Barcode) {
			return interleaved2Of5((DRIDesignInterleaved2Of5Barcode) barcode);
		}
		else if (barcode instanceof DRIDesignUpcaBarcode) {
			return upca((DRIDesignUpcaBarcode) barcode);
		}
		else if (barcode instanceof DRIDesignUpceBarcode) {
			return upce((DRIDesignUpceBarcode) barcode);
		}
		else if (barcode instanceof DRIDesignEan13Barcode) {
			return ean13((DRIDesignEan13Barcode) barcode);
		}
		else if (barcode instanceof DRIDesignEan8Barcode) {
			return ean8((DRIDesignEan8Barcode) barcode);
		}
		else if (barcode instanceof DRIDesignUspsIntelligentMailBarcode) {
			return uspsIntelligentMail((DRIDesignUspsIntelligentMailBarcode) barcode);
		}
		else if (barcode instanceof DRIDesignRoyalMailCustomerBarcode) {
			return royalMailCustomer((DRIDesignRoyalMailCustomerBarcode) barcode);
		}
		else if (barcode instanceof DRIDesignPostnetBarcode) {
			return postnet((DRIDesignPostnetBarcode) barcode);
		}
		else if (barcode instanceof DRIDesignPdf417Barcode) {
			return pdf417((DRIDesignPdf417Barcode) barcode);
		}
		else {
			throw new JasperDesignException("Barcode " + barcode.getClass().getName() + " not supported");
		}
	}

	private BarbecueComponent barbecueComponent(DRIDesignBarbecue barbecue) {
		StandardBarbecueComponent jrBarbecue = new StandardBarbecueComponent();
		EvaluationTime evaluationTime = barbecue.getEvaluationTime();
		jrBarbecue.setEvaluationTimeValue(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && barbecue.getEvaluationGroup() != null) {
			jrBarbecue.setEvaluationGroup(accessor.getGroupTransform().getGroup(barbecue.getEvaluationGroup()).getName());
		}
		jrBarbecue.setType(ConstantTransform.barbecueType(barbecue.getType()));
		jrBarbecue.setCodeExpression(accessor.getExpressionTransform().getExpression(barbecue.getCodeExpression()));
		jrBarbecue.setApplicationIdentifierExpression(accessor.getExpressionTransform().getExpression(barbecue.getApplicationIdentifierExpression()));
		if (barbecue.getDrawText() != null) {
			jrBarbecue.setDrawText(barbecue.getDrawText());
		}
		if (barbecue.getChecksumRequired() != null) {
			jrBarbecue.setChecksumRequired(barbecue.getChecksumRequired());
		}
		jrBarbecue.setBarWidth(barbecue.getBarWidth());
		jrBarbecue.setBarHeight(barbecue.getBarHeight());
		if (barbecue.getOrientation() != null) {
			jrBarbecue.setRotation(ConstantTransform.barbecueRotation(barbecue.getOrientation()));
		}
		return jrBarbecue;
	}

	private void barcode(BarcodeComponent jrBarcode, DRIDesignBarcode barcode) {
		EvaluationTime evaluationTime = barcode.getEvaluationTime();
		jrBarcode.setEvaluationTimeValue(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && barcode.getEvaluationGroup() != null) {
			jrBarcode.setEvaluationGroup(accessor.getGroupTransform().getGroup(barcode.getEvaluationGroup()).getName());
		}
		jrBarcode.setCodeExpression(accessor.getExpressionTransform().getExpression(barcode.getCodeExpression()));
		jrBarcode.setPatternExpression(accessor.getExpressionTransform().getExpression(barcode.getPatternExpression()));
		jrBarcode.setModuleWidth(barcode.getModuleWidth());
		if (barcode.getOrientation() != null) {
			jrBarcode.setOrientation(ConstantTransform.barcodeOrientation(barcode.getOrientation()));
		}
		jrBarcode.setTextPosition(ConstantTransform.barcodeTextPosition(barcode.getTextPosition()));
		jrBarcode.setQuietZone(barcode.getQuietZone());
		jrBarcode.setVerticalQuietZone(barcode.getVerticalQuietZone());
	}

	private BarcodeComponent codabar(DRIDesignCodabarBarcode barcode) {
		CodabarComponent jrBarcode = new CodabarComponent();
		barcode(jrBarcode, barcode);
		jrBarcode.setWideFactor(barcode.getWideFactor());
		return jrBarcode;
	}

	private BarcodeComponent code128(DRIDesignCode128Barcode barcode) {
		Code128Component jrBarcode = new Code128Component();
		barcode(jrBarcode, barcode);
		return jrBarcode;
	}

	private BarcodeComponent ean128(DRIDesignEan128Barcode barcode) {
		EAN128Component jrBarcode = new EAN128Component();
		barcode(jrBarcode, barcode);
		jrBarcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()));
		return jrBarcode;
	}

	private BarcodeComponent dataMatrix(DRIDesignDataMatrixBarcode barcode) {
		DataMatrixComponent jrBarcode = new DataMatrixComponent();
		barcode(jrBarcode, barcode);
		jrBarcode.setShape(ConstantTransform.barcodeShape(barcode.getShape()));
		return jrBarcode;
	}

	private BarcodeComponent code39(DRIDesignCode39Barcode barcode) {
		Code39Component jrBarcode = new Code39Component();
		barcode(jrBarcode, barcode);
		jrBarcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()));
		jrBarcode.setDisplayChecksum(barcode.getDisplayChecksum());
		jrBarcode.setDisplayStartStop(barcode.getDisplayStartStop());
		jrBarcode.setExtendedCharSetEnabled(barcode.getExtendedCharSetEnabled());
		jrBarcode.setIntercharGapWidth(barcode.getIntercharGapWidth());
		jrBarcode.setWideFactor(barcode.getWideFactor());
		return jrBarcode;
	}

	private BarcodeComponent interleaved2Of5(DRIDesignInterleaved2Of5Barcode barcode) {
		Interleaved2Of5Component jrBarcode = new Interleaved2Of5Component();
		barcode(jrBarcode, barcode);
		jrBarcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()));
		jrBarcode.setDisplayChecksum(barcode.getDisplayChecksum());
		jrBarcode.setWideFactor(barcode.getWideFactor());
		return jrBarcode;
	}

	private BarcodeComponent upca(DRIDesignUpcaBarcode barcode) {
		UPCAComponent jrBarcode = new UPCAComponent();
		barcode(jrBarcode, barcode);
		jrBarcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()));
		return jrBarcode;
	}

	private BarcodeComponent upce(DRIDesignUpceBarcode barcode) {
		UPCEComponent jrBarcode = new UPCEComponent();
		barcode(jrBarcode, barcode);
		jrBarcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()));
		return jrBarcode;
	}

	private BarcodeComponent ean13(DRIDesignEan13Barcode barcode) {
		EAN13Component jrBarcode = new EAN13Component();
		barcode(jrBarcode, barcode);
		jrBarcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()));
		return jrBarcode;
	}

	private BarcodeComponent ean8(DRIDesignEan8Barcode barcode) {
		EAN8Component jrBarcode = new EAN8Component();
		barcode(jrBarcode, barcode);
		jrBarcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()));
		return jrBarcode;
	}

	private BarcodeComponent uspsIntelligentMail(DRIDesignUspsIntelligentMailBarcode barcode) {
		USPSIntelligentMailComponent jrBarcode = new USPSIntelligentMailComponent();
		barcode(jrBarcode, barcode);
		jrBarcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()));
		jrBarcode.setAscenderHeight(barcode.getAscenderHeight());
		jrBarcode.setIntercharGapWidth(barcode.getIntercharGapWidth());
		jrBarcode.setTrackHeight(barcode.getTrackHeight());
		return jrBarcode;
	}

	private BarcodeComponent royalMailCustomer(DRIDesignRoyalMailCustomerBarcode barcode) {
		RoyalMailCustomerComponent jrBarcode = new RoyalMailCustomerComponent();
		barcode(jrBarcode, barcode);
		jrBarcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()));
		jrBarcode.setAscenderHeight(barcode.getAscenderHeight());
		jrBarcode.setIntercharGapWidth(barcode.getIntercharGapWidth());
		jrBarcode.setTrackHeight(barcode.getTrackHeight());
		return jrBarcode;
	}

	private BarcodeComponent postnet(DRIDesignPostnetBarcode barcode) {
		POSTNETComponent jrBarcode = new POSTNETComponent();
		barcode(jrBarcode, barcode);
		if (barcode.getChecksumMode() != null) {
			jrBarcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()).getName());
		}
		jrBarcode.setDisplayChecksum(barcode.getDisplayChecksum());
		jrBarcode.setShortBarHeight(barcode.getShortBarHeight());
		if (barcode.getBaselinePosition() != null) {
			jrBarcode.setBaselinePosition(ConstantTransform.barcodeBaselinePosition(barcode.getBaselinePosition()).getName());
		}
		jrBarcode.setIntercharGapWidth(barcode.getIntercharGapWidth());
		return jrBarcode;
	}

	private BarcodeComponent pdf417(DRIDesignPdf417Barcode barcode) {
		PDF417Component jrBarcode = new PDF417Component();
		barcode(jrBarcode, barcode);
		jrBarcode.setMinColumns(barcode.getMinColumns());
		jrBarcode.setMaxColumns(barcode.getMaxColumns());
		jrBarcode.setMinRows(barcode.getMinRows());
		jrBarcode.setMaxRows(barcode.getMaxRows());
		jrBarcode.setWidthToHeightRatio(barcode.getWidthToHeightRatio());
		jrBarcode.setErrorCorrectionLevel(barcode.getErrorCorrectionLevel());
		return jrBarcode;
	}
}
