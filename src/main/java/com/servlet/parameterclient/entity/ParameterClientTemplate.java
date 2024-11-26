package com.servlet.parameterclient.entity;

import java.util.List;

import com.servlet.parameter.entity.ParameterData;

public class ParameterClientTemplate {
	private List<ParameterData> parameterTypeOptions;

	public List<ParameterData> getParameterTypeOptions() {
		return parameterTypeOptions;
	}

	public void setParameterTypeOptions(List<ParameterData> parameterTypeOptions) {
		this.parameterTypeOptions = parameterTypeOptions;
	}
}
