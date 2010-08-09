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

package net.sf.dynamicreports.examples.complex.shippinglabel;

import java.util.Date;

import net.sf.dynamicreports.examples.complex.ReportData;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ShippingLabelData implements ReportData {
	private ShippingLabel shippingLabel;
	
	public ShippingLabelData() {
		shippingLabel = createShippingLabel();
	}
	
	private ShippingLabel createShippingLabel() {
		ShippingLabel shippingLabel = new ShippingLabel();
		
		shippingLabel.setFrom(createCustomer("Mary Patterson", "151 Pompton St.", "Washington"));
		shippingLabel.setTo(createCustomer("Peter Marsh", "23 Baden Av.", "New York"));
		shippingLabel.setPriority(1);
		shippingLabel.setPod("RJX");
		shippingLabel.setCarrier("849263");
		shippingLabel.setDateShipped(new Date());
		shippingLabel.setWeight(1290);
		shippingLabel.setQuantity(26);
		shippingLabel.setShipToPostalCode("09820");
		shippingLabel.setPo("8492640276542");
		shippingLabel.setSerialShippingContainer("100264835710351");
		
		return shippingLabel;
	}

	private Customer createCustomer(String name, String address, String city) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setCity(city);
		return customer;
	}
	
	public ShippingLabel getShippingLabel() {
		return shippingLabel;
	}
	
	public JRDataSource createDataSource() {
		return null;
	}
	
	public class ShippingLabel {
		private Customer from;
		private Customer to;
		private Integer priority;
		private String pod;
		private String carrier;
		private Date dateShipped;
		private Integer weight;
		private Integer quantity;
		private String shipToPostalCode;
		private String po;
		private String serialShippingContainer;
		
		public Customer getFrom() {
			return from;
		}
		
		public void setFrom(Customer from) {
			this.from = from;
		}
		
		public Customer getTo() {
			return to;
		}
		
		public void setTo(Customer to) {
			this.to = to;
		}

		public Integer getPriority() {
			return priority;
		}

		public void setPriority(Integer priority) {
			this.priority = priority;
		}

		public String getPod() {
			return pod;
		}

		public void setPod(String pod) {
			this.pod = pod;
		}

		public String getCarrier() {
			return carrier;
		}

		public void setCarrier(String carrier) {
			this.carrier = carrier;
		}

		public Date getDateShipped() {
			return dateShipped;
		}

		public void setDateShipped(Date dateShipped) {
			this.dateShipped = dateShipped;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getShipToPostalCode() {
			return shipToPostalCode;
		}

		public void setShipToPostalCode(String shipToPostalCode) {
			this.shipToPostalCode = shipToPostalCode;
		}

		public String getPo() {
			return po;
		}

		public void setPo(String po) {
			this.po = po;
		}

		public String getSerialShippingContainer() {
			return serialShippingContainer;
		}

		public void setSerialShippingContainer(String serialShippingContainer) {
			this.serialShippingContainer = serialShippingContainer;
		}		
	}
	
	public class Customer {
		private String name;
		private String address;
		private String city;
		
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
	}
}
