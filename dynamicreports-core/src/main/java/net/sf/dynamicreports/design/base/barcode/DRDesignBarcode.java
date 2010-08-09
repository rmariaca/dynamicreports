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

package net.sf.dynamicreports.design.base.barcode;

import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.DRIDesignGroup;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignBarcode;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.report.constant.BarcodeOrientation;
import net.sf.dynamicreports.report.constant.BarcodeTextPosition;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class DRDesignBarcode extends DRDesignComponent implements DRIDesignBarcode {
	private DRIDesignSimpleExpression codeExpression;
	private DRIDesignSimpleExpression patternExpression;
	private Double moduleWidth;
	private BarcodeOrientation orientation;
	private BarcodeTextPosition textPosition;
	private Double quietZone;
	private Double verticalQuietZone;	
	private EvaluationTime evaluationTime;
	private DRIDesignGroup evaluationGroup;
	
	public DRDesignBarcode(String name) {
		super(name);
	}

	public DRIDesignSimpleExpression getCodeExpression() {
		return codeExpression;
	}

	public void setCodeExpression(DRIDesignSimpleExpression codeExpression) {
		this.codeExpression = codeExpression;
	}

	public DRIDesignSimpleExpression getPatternExpression() {
		return patternExpression;
	}

	public void setPatternExpression(DRIDesignSimpleExpression patternExpression) {
		this.patternExpression = patternExpression;
	}

	public Double getModuleWidth() {
		return moduleWidth;
	}

	public void setModuleWidth(Double moduleWidth) {
		this.moduleWidth = moduleWidth;
	}

	public BarcodeOrientation getOrientation() {
		return orientation;
	}

	public void setOrientation(BarcodeOrientation orientation) {
		this.orientation = orientation;
	}

	public BarcodeTextPosition getTextPosition() {
		return textPosition;
	}

	public void setTextPosition(BarcodeTextPosition textPosition) {
		this.textPosition = textPosition;
	}

	public Double getQuietZone() {
		return quietZone;
	}

	public void setQuietZone(Double quietZone) {
		this.quietZone = quietZone;
	}

	public Double getVerticalQuietZone() {
		return verticalQuietZone;
	}

	public void setVerticalQuietZone(Double verticalQuietZone) {
		this.verticalQuietZone = verticalQuietZone;
	}

	public EvaluationTime getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(EvaluationTime evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	public DRIDesignGroup getEvaluationGroup() {
		return evaluationGroup;
	}

	public void setEvaluationGroup(DRIDesignGroup evaluationGroup) {
		this.evaluationGroup = evaluationGroup;
	}	
}
