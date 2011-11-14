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

package net.sf.dynamicreports.examples.complex.invoice;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.examples.complex.ReportDesign;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class InvoiceDesign implements ReportDesign<InvoiceData> {
	private Invoice invoice;
	private AggregationSubtotalBuilder<BigDecimal> totalSum;

	public void configureReport(ReportBuilder<?> rb, InvoiceData invoiceData) throws DRException {
		invoice = invoiceData.getInvoice();
		//init styles
		StyleBuilder columnStyle   = stl.style(Templates.columnStyle).setBorder(stl.pen1Point());
		StyleBuilder subtotalStyle = stl.style(columnStyle).bold();
		StyleBuilder shippingStyle = stl.style(Templates.boldStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT);
		//init columns
		TextColumnBuilder<Integer>    rowNumberColumn   = col.reportRowNumberColumn()
		                                                     .setFixedColumns(2)
		                                                     .setHorizontalAlignment(HorizontalAlignment.CENTER);
		TextColumnBuilder<String>     descriptionColumn = col.column("Description", "description", type.stringType())
		                                                     .setFixedWidth(250);
		TextColumnBuilder<Integer>    quantityColumn    = col.column("Quantity", "quantity", type.integerType())
		                                                     .setHorizontalAlignment(HorizontalAlignment.CENTER);
		TextColumnBuilder<BigDecimal> unitPriceColumn   = col.column("Unit Price", "unitprice", Templates.currencyType);
		TextColumnBuilder<String>     taxColumn         = col.column("Tax", exp.text("20%"))
		                                                     .setFixedColumns(3);
		//price = unitPrice * quantity
		TextColumnBuilder<BigDecimal> priceColumn       = unitPriceColumn.multiply(quantityColumn).setTitle("Price")
		                                                                 .setDataType(Templates.currencyType);
		//vat = price * tax
		TextColumnBuilder<BigDecimal> vatColumn         = priceColumn.multiply(invoice.getTax()).setTitle("VAT")
		                                                             .setDataType(Templates.currencyType);
		//total = price + vat
		TextColumnBuilder<BigDecimal> totalColumn       = priceColumn.add(vatColumn).setTitle("Total Price")
		                                                             .setDataType(Templates.currencyType)
		                                                             .setRows(2)
		                                                             .setStyle(subtotalStyle);
		//init subtotals
		totalSum = sbt.sum(totalColumn).setLabel("Total:").setLabelStyle(Templates.boldStyle);

		//configure report
		rb.setTemplate(Templates.reportTemplate)
		  .setColumnStyle(columnStyle)
		  .setSubtotalStyle(subtotalStyle)
		  //columns
		  .columns(
		  	rowNumberColumn, descriptionColumn, quantityColumn, unitPriceColumn, totalColumn, priceColumn, taxColumn, vatColumn)
		  .columnGrid(
		  	rowNumberColumn, descriptionColumn, quantityColumn, unitPriceColumn,
		  	grid.horizontalColumnGridList()
		  		.add(totalColumn).newRow()
		  		.add(priceColumn, taxColumn, vatColumn))
		  //subtotals
		  .subtotalsAtSummary(
		  	totalSum, sbt.sum(priceColumn), sbt.sum(vatColumn))
		  //band components
		  .title(
		  	Templates.createTitleComponent("Invoice No.: " + invoice.getId()),
		  	cmp.horizontalList().setStyle(stl.style(10)).setGap(50).add(
		  		cmp.hListCell(createCustomerComponent("Bill To", invoice.getBillTo())).heightFixedOnTop(),
		  		cmp.hListCell(createCustomerComponent("Ship To", invoice.getShipTo())).heightFixedOnTop()),
		  	cmp.verticalGap(10))
		  .pageFooter(
		  	Templates.footerComponent)
		  .summary(
		  	cmp.text(invoice.getShipping()).setValueFormatter(Templates.createCurrencyValueFormatter("Shipping:")).setStyle(shippingStyle),
		  	cmp.horizontalList(
		  		cmp.text("Payment terms: 30 days").setStyle(Templates.bold12CenteredStyle),
		  		cmp.text(new TotalPaymentExpression()).setStyle(Templates.bold12CenteredStyle)),
		  	cmp.verticalGap(30),
		  	cmp.text("Thank you for your business").setStyle(Templates.bold12CenteredStyle));
	}

	private ComponentBuilder<?, ?> createCustomerComponent(String label, Customer customer) {
		HorizontalListBuilder list = cmp.horizontalList().setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));
		addCustomerAttribute(list, "Name", customer.getName());
		addCustomerAttribute(list, "Address", customer.getAddress());
		addCustomerAttribute(list, "City", customer.getCity());
		addCustomerAttribute(list, "Email", customer.getEmail());
		return cmp.verticalList(
							cmp.text(label).setStyle(Templates.boldStyle),
							list);
	}

	private void addCustomerAttribute(HorizontalListBuilder list, String label, String value) {
		if (value != null) {
			list.add(cmp.text(label + ":").setFixedColumns(8).setStyle(Templates.boldStyle), cmp.text(value)).newRow();
		}
	}

	private class TotalPaymentExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		public String evaluate(ReportParameters reportParameters) {
			BigDecimal total = reportParameters.getValue(totalSum);
			BigDecimal shipping = total.add(invoice.getShipping());
			return "Total payment: " + Templates.currencyType.valueToString(shipping, reportParameters.getLocale());
		}
	}
}