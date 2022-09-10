package com.servlet.address.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "provinces", schema = "public")
public class Province implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long prov_id;
	private String prov_name;
	private Long locationid;
	private Long status;
	public Long getProv_id() {
		return prov_id;
	}
	public void setProv_id(Long prov_id) {
		this.prov_id = prov_id;
	}
	public String getProv_name() {
		return prov_name;
	}
	public void setProv_name(String prov_name) {
		this.prov_name = prov_name;
	}
	public Long getLocationid() {
		return locationid;
	}
	public void setLocationid(Long locationid) {
		this.locationid = locationid;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
}
