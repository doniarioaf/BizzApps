package com.servlet.invoicetype.entity;

import java.util.List;

import com.servlet.coa.entity.CoaData;
import com.servlet.parameter.entity.ParameterData;

public class InvoiceTypeTemplate {
	private List<ParameterData> invoiceTypeOptions;
	private List<CoaData> coaOptions;

	public List<ParameterData> getInvoiceTypeOptions() {
		return invoiceTypeOptions;
	}

	public void setInvoiceTypeOptions(List<ParameterData> invoiceTypeOptions) {
		this.invoiceTypeOptions = invoiceTypeOptions;
	}

	public List<CoaData> getCoaOptions() {
		return coaOptions;
	}

	public void setCoaOptions(List<CoaData> coaOptions) {
		this.coaOptions = coaOptions;
	}
}
