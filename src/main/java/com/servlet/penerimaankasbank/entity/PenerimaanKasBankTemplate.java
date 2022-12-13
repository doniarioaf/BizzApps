package com.servlet.penerimaankasbank.entity;

import java.util.List;

import com.servlet.bankaccount.entity.BankAccountData;
import com.servlet.coa.entity.CoaData;
import com.servlet.workorder.entity.WorkOrderDropDownData;

public class PenerimaanKasBankTemplate {
	private List<CoaData> coaOptions;
	private List<BankAccountData> bankOptions;
	private List<WorkOrderDropDownData> woOptions;
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
	public List<WorkOrderDropDownData> getWoOptions() {
		return woOptions;
	}
	public void setWoOptions(List<WorkOrderDropDownData> woOptions) {
		this.woOptions = woOptions;
	}
}
