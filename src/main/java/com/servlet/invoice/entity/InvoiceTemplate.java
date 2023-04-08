package com.servlet.invoice.entity;

import java.util.HashMap;
import java.util.List;
import com.servlet.parameter.entity.ParameterData;
import com.servlet.pengluarankasbank.entity.PengeluaranHeaderAndDetail;
import com.servlet.pricelist.entity.PriceListData;
import com.servlet.suratjalan.entity.SuratJalanDropDown;

public class InvoiceTemplate {
	private List<ParameterData> invoiceTypeOptions;
	private HashMap<String, Object> suratJalanOptions;
	private List<SuratJalanDropDown> searchSuratJalanOptions;
	private PengeluaranHeaderAndDetail searchPengeluaranOptions;
	private List<PriceListData> searchPricelistOptions;
	private Double defaultPPN;
	
	public Double getDefaultPPN() {
		return defaultPPN;
	}

	public void setDefaultPPN(Double defaultPPN) {
		this.defaultPPN = defaultPPN;
	}

	public List<PriceListData> getSearchPricelistOptions() {
		return searchPricelistOptions;
	}

	public void setSearchPricelistOptions(List<PriceListData> searchPricelistOptions) {
		this.searchPricelistOptions = searchPricelistOptions;
	}

	public PengeluaranHeaderAndDetail getSearchPengeluaranOptions() {
		return searchPengeluaranOptions;
	}

	public void setSearchPengeluaranOptions(PengeluaranHeaderAndDetail searchPengeluaranOptions) {
		this.searchPengeluaranOptions = searchPengeluaranOptions;
	}

	public List<SuratJalanDropDown> getSearchSuratJalanOptions() {
		return searchSuratJalanOptions;
	}

	public void setSearchSuratJalanOptions(List<SuratJalanDropDown> searchSuratJalanOptions) {
		this.searchSuratJalanOptions = searchSuratJalanOptions;
	}

	public HashMap<String, Object> getSuratJalanOptions() {
		return suratJalanOptions;
	}

	public void setSuratJalanOptions(HashMap<String, Object> suratJalanOptions) {
		this.suratJalanOptions = suratJalanOptions;
	}

	public List<ParameterData> getInvoiceTypeOptions() {
		return invoiceTypeOptions;
	}

	public void setInvoiceTypeOptions(List<ParameterData> invoiceTypeOptions) {
		this.invoiceTypeOptions = invoiceTypeOptions;
	}
}
