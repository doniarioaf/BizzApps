package com.servlet.report.entity;

public class MonitoringInfoData {
	private long infoid;
	private String question;
	private String type;
	private String answer;
	public long getInfoid() {
		return infoid;
	}
	public void setInfoid(long infoid) {
		this.infoid = infoid;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
