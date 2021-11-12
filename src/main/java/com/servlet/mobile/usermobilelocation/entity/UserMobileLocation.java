package com.servlet.mobile.usermobilelocation.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_user_mobile_location", schema = "public")
public class UserMobileLocation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="usermobilelocation_id_seq")
	private long id;	
	private long idcompany;
	private long idbranch;
	private long idusermobile;
	private Timestamp tanggal;
	private String latitude;
	private String longitude;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public long getIdusermobile() {
		return idusermobile;
	}
	public void setIdusermobile(long idusermobile) {
		this.idusermobile = idusermobile;
	}
	public Timestamp getTanggal() {
		return tanggal;
	}
	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
