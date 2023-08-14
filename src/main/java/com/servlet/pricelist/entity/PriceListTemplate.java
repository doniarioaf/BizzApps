package com.servlet.pricelist.entity;

import java.util.List;

import com.servlet.customermanggala.entity.CustomerManggalaData;
import com.servlet.invoicetype.entity.InvoiceTypeData;

public class PriceListTemplate {
	List<CustomerManggalaData> customerOptions;
	List<InvoiceTypeData> biayaJasaOptions;

	public List<InvoiceTypeData> getBiayaJasaOptions() {
		return biayaJasaOptions;
	}

	public void setBiayaJasaOptions(List<InvoiceTypeData> biayaJasaOptions) {
		this.biayaJasaOptions = biayaJasaOptions;
	}

	public List<CustomerManggalaData> getCustomerOptions() {
		return customerOptions;
	}

	public void setCustomerOptions(List<CustomerManggalaData> customerOptions) {
		this.customerOptions = customerOptions;
	}
}
