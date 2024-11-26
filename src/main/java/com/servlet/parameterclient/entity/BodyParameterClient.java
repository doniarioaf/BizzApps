package com.servlet.parameterclient.entity;


public class BodyParameterClient {
	private String paramname;
	private String paramvalue;
	private String paramtype;
	private Long paramdate;
	private boolean isactive;
	
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
	public String getParamtype() {
		return paramtype;
	}
	public void setParamtype(String paramtype) {
		this.paramtype = paramtype;
	}
	public Long getParamdate() {
		return paramdate;
	}
	public void setParamdate(Long paramdate) {
		this.paramdate = paramdate;
	}
}
