package com.servlet.parameter.entity;

public class ParameterData {
	
	private String code;
	private String codename;
	
	public ParameterData() {}
	
	public ParameterData(String code,String codename) {
		this.code = code;
		this.codename = codename;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	
}
