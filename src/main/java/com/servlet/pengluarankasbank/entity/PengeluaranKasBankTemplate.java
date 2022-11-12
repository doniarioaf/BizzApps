package com.servlet.pengluarankasbank.entity;

import java.util.List;

import com.servlet.bankaccount.entity.BankAccountData;
import com.servlet.coa.entity.CoaData;

public class PengeluaranKasBankTemplate {
	private List<CoaData> coaOptions;
	private List<BankAccountData> bankOptions;
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
