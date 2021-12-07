package com.servlet.security.entity;

import java.sql.Timestamp;

public class LicenseData {
	private long idcompany;
	private long limitusermobile;
	private long limituserweb;
	private Timestamp expired;
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}
	public long getLimitusermobile() {
		return limitusermobile;
	}
	public void setLimitusermobile(long limitusermobile) {
		this.limitusermobile = limitusermobile;
	}
	public long getLimituserweb() {
		return limituserweb;
	}
	public void setLimituserweb(long limituserweb) {
		this.limituserweb = limituserweb;
	}
	public Timestamp getExpired() {
		return expired;
	}
	public void setExpired(Timestamp expired) {
		this.expired = expired;
	}
}
