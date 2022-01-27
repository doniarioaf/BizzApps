package com.servlet.shared;

import java.util.List;

public class ProcessReturn {
	private Object data;
	private boolean success;
	private List<ValidationDataMessage> validations;
	private int httpcode;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getHttpcode() {
		return httpcode;
	}
	public void setHttpcode(int httpcode) {
		this.httpcode = httpcode;
	}
	public List<ValidationDataMessage> getValidations() {
		return validations;
	}
	public void setValidations(List<ValidationDataMessage> validations) {
		this.validations = validations;
	}
}
