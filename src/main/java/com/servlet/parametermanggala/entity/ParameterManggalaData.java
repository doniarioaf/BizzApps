package com.servlet.parametermanggala.entity;

public class ParameterManggalaData {
	private Long id;
	private String paramname;
	private String paramvalue;
	private boolean isactive;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParamname() {
		return paramname;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
}
