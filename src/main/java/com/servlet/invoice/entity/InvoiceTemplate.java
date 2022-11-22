package com.servlet.invoice.entity;

import java.util.List;

import com.servlet.invoicetype.entity.InvoiceTypeData;

public class InvoiceTemplate {
	private List<InvoiceTypeData> invoiceTypeOptions;

	public List<InvoiceTypeData> getInvoiceTypeOptions() {
		return invoiceTypeOptions;
	}

	public void setInvoiceTypeOptions(List<InvoiceTypeData> invoiceTypeOptions) {
		this.invoiceTypeOptions = invoiceTypeOptions;
	}
}
