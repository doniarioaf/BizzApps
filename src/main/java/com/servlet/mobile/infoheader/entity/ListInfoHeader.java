package com.servlet.mobile.infoheader.entity;

public class ListInfoHeader {
	private long id;
	private String question;
	private String type;
	private long sequence;
	private long idproject;
	private String projectname;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	public long getIdproject() {
		return idproject;
	}
	public void setIdproject(long idproject) {
		this.idproject = idproject;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
}
