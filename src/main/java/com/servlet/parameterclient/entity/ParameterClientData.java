package com.servlet.parameterclient.entity;

import java.sql.Date;

public class ParameterClientData {
	private Long id;
	private String paramname;
	private String paramvalue;
	private boolean isactive;
	private String paramtype;
	private Date paramdate;
	private ParameterClientTemplate template;
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
	public String getParamtype() {
		return paramtype;
	}
	public void setParamtype(String paramtype) {
		this.paramtype = paramtype;
	}
	public Date getParamdate() {
		return paramdate;
	}
	public void setParamdate(Date paramdate) {
		this.paramdate = paramdate;
	}
	public ParameterClientTemplate getTemplate() {
		return template;
	}
	public void setTemplate(ParameterClientTemplate template) {
		this.template = template;
	}
}
