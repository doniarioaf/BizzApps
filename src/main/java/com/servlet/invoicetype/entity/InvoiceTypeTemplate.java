package com.servlet.invoicetype.entity;

import java.util.List;

import com.servlet.parameter.entity.ParameterData;

public class InvoiceTypeTemplate {
	private List<ParameterData> invoiceTypeOptions;

	public List<ParameterData> getInvoiceTypeOptions() {
		return invoiceTypeOptions;
	}

	public void setInvoiceTypeOptions(List<ParameterData> invoiceTypeOptions) {
		this.invoiceTypeOptions = invoiceTypeOptions;
	}
}
