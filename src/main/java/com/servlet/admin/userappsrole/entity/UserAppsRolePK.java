package com.servlet.admin.userappsrole.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserAppsRolePK implements Serializable{
	private long iduserapps;
    private long idrole;
	public long getIduserapps() {
		return iduserapps;
	}
	public void setIduserapps(long iduserapps) {
		this.iduserapps = iduserapps;
	}
	public long getIdrole() {
		return idrole;
	}
	public void setIdrole(long idrole) {
		this.idrole = idrole;
	}

}
