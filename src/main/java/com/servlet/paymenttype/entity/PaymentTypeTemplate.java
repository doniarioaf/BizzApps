package com.servlet.paymenttype.entity;

import java.util.List;

import com.servlet.coa.entity.CoaData;
import com.servlet.parameter.entity.ParameterData;

public class PaymentTypeTemplate {
	private List<ParameterData> paymentTypeOptions;
	private List<CoaData> coaOptions;

	public List<CoaData> getCoaOptions() {
		return coaOptions;
	}

	public void setCoaOptions(List<CoaData> coaOptions) {
		this.coaOptions = coaOptions;
	}

	public List<ParameterData> getPaymentTypeOptions() {
		return paymentTypeOptions;
	}

	public void setPaymentTypeOptions(List<ParameterData> paymentTypeOptions) {
		this.paymentTypeOptions = paymentTypeOptions;
	}

}
