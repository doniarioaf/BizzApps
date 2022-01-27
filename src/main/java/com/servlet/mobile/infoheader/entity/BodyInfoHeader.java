package com.servlet.mobile.infoheader.entity;

public class BodyInfoHeader {
	private String question;
	private String type;
	private long sequence;
	private long idcustomertype;
	private boolean isactive;
	private String[] answer;
	public String[] getAnswer() {
		return answer;
	}
	public void setAnswer(String[] answer) {
		this.answer = answer;
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
	public long getIdcustomertype() {
		return idcustomertype;
	}
	public void setIdcustomertype(long idcustomertype) {
		this.idcustomertype = idcustomertype;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
}
