package com.servlet.mobile.infoheader.entity;

import java.util.List;

public class BodyInfoHeaderUpdate {
	private String question;
	private String type;
	private long sequence;
	private long idcustomertype;
	private boolean isactive;
	private ListAnswerUpdate[] answers;
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
	public ListAnswerUpdate[] getAnswers() {
		return answers;
	}
	public void setAnswers(ListAnswerUpdate[] answers) {
		this.answers = answers;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
}
