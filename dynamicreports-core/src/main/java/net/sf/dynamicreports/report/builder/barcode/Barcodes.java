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

package net.sf.dynamicreports.report.builder.barcode;

import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class Barcodes {
	
	//codabar
	public static CodabarBarcodeBuilder codabar(String code) {
		return new CodabarBarcodeBuilder(code);
	}
	
	public static CodabarBarcodeBuilder codabar(DRISimpleExpression<String> codeExpression) {
		return new CodabarBarcodeBuilder(codeExpression);
	}
	
	//code128
	public static Code128BarcodeBuilder code128(String code) {
		return new Code128BarcodeBuilder(code);
	}
	
	public static Code128BarcodeBuilder code128(DRISimpleExpression<String> codeExpression) {
		return new Code128BarcodeBuilder(codeExpression);
	}
	
	//ean128
	public static Ean128BarcodeBuilder ean128(String code) {
		return new Ean128BarcodeBuilder(code);
	}
	
	public static Ean128BarcodeBuilder ean128(DRISimpleExpression<String> codeExpression) {
		return new Ean128BarcodeBuilder(codeExpression);
	}
	
	//dataMatrix
	public static DataMatrixBarcodeBuilder dataMatrix(String code) {
		return new DataMatrixBarcodeBuilder(code);
	}
	
	public static DataMatrixBarcodeBuilder dataMatrix(DRISimpleExpression<String> codeExpression) {
		return new DataMatrixBarcodeBuilder(codeExpression);
	}
	
	//code39
	public static Code39BarcodeBuilder code39(String code) {
		return new Code39BarcodeBuilder(code);
	}
	
	public static Code39BarcodeBuilder code39(DRISimpleExpression<String> codeExpression) {
		return new Code39BarcodeBuilder(codeExpression);
	}
	
	//interleaved2Of5
	public static Interleaved2Of5BarcodeBuilder interleaved2Of5(String code) {
		return new Interleaved2Of5BarcodeBuilder(code);
	}
	
	public static Interleaved2Of5BarcodeBuilder interleaved2Of5(DRISimpleExpression<String> codeExpression) {
		return new Interleaved2Of5BarcodeBuilder(codeExpression);
	}
	
	//upca
	public static UpcaBarcodeBuilder upca(String code) {
		return new UpcaBarcodeBuilder(code);
	}
	
	public static UpcaBarcodeBuilder upca(DRISimpleExpression<String> codeExpression) {
		return new UpcaBarcodeBuilder(codeExpression);
	}
	
	//upce
	public static UpceBarcodeBuilder upce(String code) {
		return new UpceBarcodeBuilder(code);
	}
	
	public static UpceBarcodeBuilder upce(DRISimpleExpression<String> codeExpression) {
		return new UpceBarcodeBuilder(codeExpression);
	}
	
	//ean13
	public static Ean13BarcodeBuilder ean13(String code) {
		return new Ean13BarcodeBuilder(code);
	}
	
	public static Ean13BarcodeBuilder ean13(DRISimpleExpression<String> codeExpression) {
		return new Ean13BarcodeBuilder(codeExpression);
	}
	
	//ean8
	public static Ean8BarcodeBuilder ean8(String code) {
		return new Ean8BarcodeBuilder(code);
	}
	
	public static Ean8BarcodeBuilder ean8(DRISimpleExpression<String> codeExpression) {
		return new Ean8BarcodeBuilder(codeExpression);
	}
	
	//uspsIntelligentMail
	public static UspsIntelligentMailBarcodeBuilder uspsIntelligentMail(String code) {
		return new UspsIntelligentMailBarcodeBuilder(code);
	}
	
	public static UspsIntelligentMailBarcodeBuilder uspsIntelligentMail(DRISimpleExpression<String> codeExpression) {
		return new UspsIntelligentMailBarcodeBuilder(codeExpression);
	}
	
	//royalMailCustomer
	public static RoyalMailCustomerBarcodeBuilder royalMailCustomer(String code) {
		return new RoyalMailCustomerBarcodeBuilder(code);
	}
	
	public static RoyalMailCustomerBarcodeBuilder royalMailCustomer(DRISimpleExpression<String> codeExpression) {
		return new RoyalMailCustomerBarcodeBuilder(codeExpression);
	}
	
	//postnet
	public static PostnetBarcodeBuilder postnet(String code) {
		return new PostnetBarcodeBuilder(code);
	}
	
	public static PostnetBarcodeBuilder postnet(DRISimpleExpression<String> codeExpression) {
		return new PostnetBarcodeBuilder(codeExpression);
	}
	
	//pdf417
	public static Pdf417BarcodeBuilder pdf417(String code) {
		return new Pdf417BarcodeBuilder(code);
	}
	
	public static Pdf417BarcodeBuilder pdf417(DRISimpleExpression<String> codeExpression) {
		return new Pdf417BarcodeBuilder(codeExpression);
	}
}
