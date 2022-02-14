package com.servlet.mobile.usermobilecallplan.entity;

public class UserMobileCallPlanData {
	private long idcallplan;
	private String nama;
	private String description;
	private long idproject;
	private String projectname;
	public long getIdproject() {
		return idproject;
	}
	public void setIdproject(long idproject) {
		this.idproject = idproject;
	}
	public long getIdcallplan() {
		return idcallplan;
	}
	public void setIdcallplan(long idcallplan) {
		this.idcallplan = idcallplan;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
}
