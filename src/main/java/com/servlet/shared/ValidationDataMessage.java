package com.servlet.shared;

public class ValidationDataMessage {
	private String messageCode;
	private String message;
	public ValidationDataMessage(String messageCode, String message) {
		super();
		this.messageCode = messageCode;
		this.message = message;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
