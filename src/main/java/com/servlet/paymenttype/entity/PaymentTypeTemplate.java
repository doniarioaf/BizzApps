package com.servlet.paymenttype.entity;

import java.util.List;

import com.servlet.parameter.entity.ParameterData;

public class PaymentTypeTemplate {
	private List<ParameterData> paymentTypeOptions;

	public List<ParameterData> getPaymentTypeOptions() {
		return paymentTypeOptions;
	}

	public void setPaymentTypeOptions(List<ParameterData> paymentTypeOptions) {
		this.paymentTypeOptions = paymentTypeOptions;
	}

}
