package com.servlet.mobile.monitorusermobileinfo.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MonitorUserMobileInfoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long idmonitorusermobile;
    private long infoid;
    private long idinfodetail;
	public long getIdmonitorusermobile() {
		return idmonitorusermobile;
	}
	public void setIdmonitorusermobile(long idmonitorusermobile) {
		this.idmonitorusermobile = idmonitorusermobile;
	}
	public long getInfoid() {
		return infoid;
	}
	public void setInfoid(long infoid) {
		this.infoid = infoid;
	}
	public long getIdinfodetail() {
		return idinfodetail;
	}
	public void setIdinfodetail(long idinfodetail) {
		this.idinfodetail = idinfodetail;
	}
}
