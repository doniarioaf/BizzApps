package com.servlet.pengluarankasbank.entity;

import java.util.List;

import com.servlet.asset.entity.AssetData;
import com.servlet.bankaccount.entity.BankAccountData;
import com.servlet.coa.entity.CoaData;
import com.servlet.invoicetype.entity.InvoiceTypeData;
import com.servlet.parameter.entity.ParameterData;
import com.servlet.paymenttype.entity.PaymentTypeData;
import com.servlet.workorder.entity.WorkOrderDropDownData;

public class PengeluaranKasBankTemplate {
	private List<CoaData> coaOptions;
	private List<BankAccountData> bankOptions;
	private List<WorkOrderDropDownData> woOptions;
	private List<InvoiceTypeData> invoiceItemOptions;
	private List<AssetData> assetOptions;
	private List<ParameterData> paymenttypeOptions;
	private List<ParameterData> spareparttypeOptions;
	private List<PaymentTypeData> paymentItemOptions;
	private List<AssetData> assetSparePartOptions;
	
	public List<ParameterData> getSpareparttypeOptions() {
		return spareparttypeOptions;
	}
	public void setSpareparttypeOptions(List<ParameterData> spareparttypeOptions) {
		this.spareparttypeOptions = spareparttypeOptions;
	}
	public List<AssetData> getAssetSparePartOptions() {
		return assetSparePartOptions;
	}
	public void setAssetSparePartOptions(List<AssetData> assetSparePartOptions) {
		this.assetSparePartOptions = assetSparePartOptions;
	}
	public List<PaymentTypeData> getPaymentItemOptions() {
		return paymentItemOptions;
	}
	public void setPaymentItemOptions(List<PaymentTypeData> paymentItemOptions) {
		this.paymentItemOptions = paymentItemOptions;
	}
	public List<ParameterData> getPaymenttypeOptions() {
		return paymenttypeOptions;
	}
	public void setPaymenttypeOptions(List<ParameterData> paymenttypeOptions) {
		this.paymenttypeOptions = paymenttypeOptions;
	}
	public List<AssetData> getAssetOptions() {
		return assetOptions;
	}
	public void setAssetOptions(List<AssetData> assetOptions) {
		this.assetOptions = assetOptions;
	}
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
