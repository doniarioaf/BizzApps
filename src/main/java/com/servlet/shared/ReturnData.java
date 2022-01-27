package com.servlet.shared;

import java.util.List;

public class ReturnData {
	private long id;
	private boolean success;
	private List<ValidationDataMessage> validations;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<ValidationDataMessage> getValidations() {
		return validations;
	}

	public void setValidations(List<ValidationDataMessage> validations) {
		this.validations = validations;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
