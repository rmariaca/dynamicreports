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

package net.sf.dynamicreports.examples.complex.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.examples.complex.ReportData;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class InvoiceData implements ReportData {
	private Invoice invoice;
	
	public InvoiceData() {
		invoice = createInvoice();
	}
	
	private Invoice createInvoice() {
		Invoice invoice = new Invoice();
		invoice.setId(5);
		invoice.setShipping(new BigDecimal(10));
		invoice.setTax(0.2);
		
		invoice.setBillTo(createCustomer("Mary Patterson", "151 Pompton St.", "Washington", "mpatterson@noemail.com"));
		invoice.setShipTo(createCustomer("Peter Marsh", "23 Baden Av.", "New York", null));
		
		List<Item> items = new ArrayList<Item>();
		items.add(createItem("Notebook", 1, new BigDecimal(1000)));
		items.add(createItem("DVD", 5, new BigDecimal(40)));
		items.add(createItem("Book", 2, new BigDecimal(10)));
		items.add(createItem("Phone", 1, new BigDecimal(200)));
		invoice.setItems(items);
		
		return invoice;
	}

	private Customer createCustomer(String name, String address, String city, String email) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setEmail(email);
		return customer;
	}
	
	private Item createItem(String description, Integer quantity, BigDecimal unitprice) {
		Item item = new Item();
		item.setDescription(description);
		item.setQuantity(quantity);
		item.setUnitprice(unitprice);
		return item;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}
	
	public JRDataSource createDataSource() {
		return new JRBeanCollectionDataSource(invoice.getItems());
	}
	
	public class Invoice {
		private Integer id;
		private BigDecimal shipping;
		private Double tax;
		private Customer billTo;
		private Customer shipTo;
		private List<Item> items;
		
		public Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		
		public BigDecimal getShipping() {
			return shipping;
		}
		
		public void setShipping(BigDecimal shipping) {
			this.shipping = shipping;
		}
		
		public Double getTax() {
			return tax;
		}
		
		public void setTax(Double tax) {
			this.tax = tax;
		}
		
		public Customer getBillTo() {
			return billTo;
		}
		
		public void setBillTo(Customer billTo) {
			this.billTo = billTo;
		}
		
		public Customer getShipTo() {
			return shipTo;
		}
		
		public void setShipTo(Customer shipTo) {
			this.shipTo = shipTo;
		}	
		
		public List<Item> getItems() {
			return items;
		}
		
		public void setItems(List<Item> items) {
			this.items = items;
		}
	}
	
	public class Customer {
		private String name;
		private String address;
		private String city;
		private String email;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getAddress() {
			return address;
		}
		
		public void setAddress(String address) {
			this.address = address;
		}
		
		public String getCity() {
			return city;
		}
		
		public void setCity(String city) {
			this.city = city;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}	
	}
	
	public class Item {
		private String description;
		private Integer quantity;
		private BigDecimal unitprice;
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		public Integer getQuantity() {
			return quantity;
		}
		
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		
		public BigDecimal getUnitprice() {
			return unitprice;
		}
		
		public void setUnitprice(BigDecimal unitprice) {
			this.unitprice = unitprice;
		}	
	}
}
