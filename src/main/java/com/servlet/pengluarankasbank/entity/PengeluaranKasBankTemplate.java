package com.servlet.pengluarankasbank.entity;

import java.util.List;

import com.servlet.bankaccount.entity.BankAccountData;
import com.servlet.coa.entity.CoaData;
import com.servlet.invoicetype.entity.InvoiceTypeData;
import com.servlet.workorder.entity.WorkOrderDropDownData;

public class PengeluaranKasBankTemplate {
	private List<CoaData> coaOptions;
	private List<BankAccountData> bankOptions;
	private List<WorkOrderDropDownData> woOptions;
	private List<InvoiceTypeData> invoiceItemOptions;
	
	
	public List<InvoiceTypeData> getInvoiceItemOptions() {
		return invoiceItemOptions;
	}
	public void setInvoiceItemOptions(List<InvoiceTypeData> invoiceItemOptions) {
		this.invoiceItemOptions = invoiceItemOptions;
	}
	public List<WorkOrderDropDownData> getWoOptions() {
		return woOptions;
	}
	public void setWoOptions(List<WorkOrderDropDownData> woOptions) {
		this.woOptions = woOptions;
	}
	public List<CoaData> getCoaOptions() {
		return coaOptions;
	}
	public void setCoaOptions(List<CoaData> coaOptions) {
		this.coaOptions = coaOptions;
	}
	public List<BankAccountData> getBankOptions() {
		return bankOptions;
	}
	public void setBankOptions(List<BankAccountData> bankOptions) {
		this.bankOptions = bankOptions;
	}
}
