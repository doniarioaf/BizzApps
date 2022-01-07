package com.servlet.security.entity;

import java.util.List;

import com.servlet.shared.ValidationDataMessage;

public class AuthorizationEntity {
	private boolean isvalid;
	private String messageCode;
	private String message;
	private List<ValidationDataMessage> validations;

	private String username;
	private long idcompany;
	private long idbranch;
	
	public List<ValidationDataMessage> getValidations() {
		return validations;
	}

	public void setValidations(List<ValidationDataMessage> validations) {
		this.validations = validations;
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

	public boolean isIsvalid() {
		return isvalid;
	}

	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getIdcompany() {
		return idcompany;
	}

	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}

	public long getIdbranch() {
		return idbranch;
	}

	public void setIdbranch(long idbranch) {
		this.idbranch = idbranch;
	}
}
