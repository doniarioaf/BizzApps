package com.servlet.invoice.entity;

import java.util.List;
import com.servlet.parameter.entity.ParameterData;

public class InvoiceTemplate {
	private List<ParameterData> invoiceTypeOptions;

	public List<ParameterData> getInvoiceTypeOptions() {
		return invoiceTypeOptions;
	}

	public void setInvoiceTypeOptions(List<ParameterData> invoiceTypeOptions) {
		this.invoiceTypeOptions = invoiceTypeOptions;
	}
}
