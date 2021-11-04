package com.servlet.admin.usermobilerole.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserMobileRolePK implements Serializable{
	private long idusermobile;
    private long idrole;
	public long getIdusermobile() {
		return idusermobile;
	}
	public void setIdusermobile(long idusermobile) {
		this.idusermobile = idusermobile;
	}
	public long getIdrole() {
		return idrole;
	}
	public void setIdrole(long idrole) {
		this.idrole = idrole;
	}

}
