package com.servlet.admin.rolepermissions.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RolePermissionsPK implements Serializable{
	private long idcompany;
    private long idbranch;
    private long idrole;
    private long idpermissions;
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}
	public long getIdbranch() {
		return idbranch;
	}
	public void setIdbranch(long idbranch) {
		this.idbranch = idbranch;
	}
	public long getIdrole() {
		return idrole;
	}
	public void setIdrole(long idrole) {
		this.idrole = idrole;
	}
	public long getIdpermissions() {
		return idpermissions;
	}
	public void setIdpermissions(long idpermissions) {
		this.idpermissions = idpermissions;
	}
    
}
