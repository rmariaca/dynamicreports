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

package net.sf.dynamicreports.report.builder.barcode;

import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class BarcodeBuilders {
	
	//codabar
	public CodabarBarcodeBuilder codabar(String code) {
		return Barcodes.codabar(code);
	}
	
	public CodabarBarcodeBuilder codabar(DRISimpleExpression<String> codeExpression) {
		return Barcodes.codabar(codeExpression);
	}
	
	//code128
	public Code128BarcodeBuilder code128(String code) {
		return Barcodes.code128(code);
	}
	
	public Code128BarcodeBuilder code128(DRISimpleExpression<String> codeExpression) {
		return Barcodes.code128(codeExpression);
	}
	
	//ean128
	public Ean128BarcodeBuilder ean128(String code) {
		return Barcodes.ean128(code);
	}
	
	public Ean128BarcodeBuilder ean128(DRISimpleExpression<String> codeExpression) {
		return Barcodes.ean128(codeExpression);
	}
	
	//dataMatrix
	public DataMatrixBarcodeBuilder dataMatrix(String code) {
		return Barcodes.dataMatrix(code);
	}
	
	public DataMatrixBarcodeBuilder dataMatrix(DRISimpleExpression<String> codeExpression) {
		return Barcodes.dataMatrix(codeExpression);
	}
	
	//code39
	public Code39BarcodeBuilder code39(String code) {
		return Barcodes.code39(code);
	}
	
	public Code39BarcodeBuilder code39(DRISimpleExpression<String> codeExpression) {
		return Barcodes.code39(codeExpression);
	}
	
	//interleaved2Of5
	public Interleaved2Of5BarcodeBuilder interleaved2Of5(String code) {
		return Barcodes.interleaved2Of5(code);
	}
	
	public Interleaved2Of5BarcodeBuilder interleaved2Of5(DRISimpleExpression<String> codeExpression) {
		return Barcodes.interleaved2Of5(codeExpression);
	}
	
	//upca
	public UpcaBarcodeBuilder upca(String code) {
		return Barcodes.upca(code);
	}
	
	public UpcaBarcodeBuilder upca(DRISimpleExpression<String> codeExpression) {
		return Barcodes.upca(codeExpression);
	}
	
	//upce
	public UpceBarcodeBuilder upce(String code) {
		return Barcodes.upce(code);
	}
	
	public UpceBarcodeBuilder upce(DRISimpleExpression<String> codeExpression) {
		return Barcodes.upce(codeExpression);
	}
	
	//ean13
	public Ean13BarcodeBuilder ean13(String code) {
		return Barcodes.ean13(code);
	}
	
	public Ean13BarcodeBuilder ean13(DRISimpleExpression<String> codeExpression) {
		return Barcodes.ean13(codeExpression);
	}
	
	//ean8
	public Ean8BarcodeBuilder ean8(String code) {
		return Barcodes.ean8(code);
	}
	
	public Ean8BarcodeBuilder ean8(DRISimpleExpression<String> codeExpression) {
		return Barcodes.ean8(codeExpression);
	}
	
	//uspsIntelligentMail
	public UspsIntelligentMailBarcodeBuilder uspsIntelligentMail(String code) {
		return Barcodes.uspsIntelligentMail(code);
	}
	
	public UspsIntelligentMailBarcodeBuilder uspsIntelligentMail(DRISimpleExpression<String> codeExpression) {
		return Barcodes.uspsIntelligentMail(codeExpression);
	}
	
	//royalMailCustomer
	public RoyalMailCustomerBarcodeBuilder royalMailCustomer(String code) {
		return Barcodes.royalMailCustomer(code);
	}
	
	public RoyalMailCustomerBarcodeBuilder royalMailCustomer(DRISimpleExpression<String> codeExpression) {
		return Barcodes.royalMailCustomer(codeExpression);
	}
	
	//postnet
	public PostnetBarcodeBuilder postnet(String code) {
		return Barcodes.postnet(code);
	}
	
	public PostnetBarcodeBuilder postnet(DRISimpleExpression<String> codeExpression) {
		return Barcodes.postnet(codeExpression);
	}
	
	//pdf417
	public Pdf417BarcodeBuilder pdf417(String code) {
		return Barcodes.pdf417(code);
	}
	
	public Pdf417BarcodeBuilder pdf417(DRISimpleExpression<String> codeExpression) {
		return Barcodes.pdf417(codeExpression);
	}
}
