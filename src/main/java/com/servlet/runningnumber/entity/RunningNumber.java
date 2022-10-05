package com.servlet.runningnumber.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_running_number", schema = "public")
public class RunningNumber implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RunningNumberPK runningNumberPK;
	private Long value;
	public RunningNumberPK getRunningNumberPK() {
		return runningNumberPK;
	}
	public void setRunningNumberPK(RunningNumberPK runningNumberPK) {
		this.runningNumberPK = runningNumberPK;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
}
