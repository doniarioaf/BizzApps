package com.servlet.address.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "districts", schema = "public")
public class District implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long dis_id;
	private String dis_name;
	private Long city_id;
	public Long getDis_id() {
		return dis_id;
	}
	public void setDis_id(Long dis_id) {
		this.dis_id = dis_id;
	}
	public String getDis_name() {
		return dis_name;
	}
	public void setDis_name(String dis_name) {
		this.dis_name = dis_name;
	}
	public Long getCity_id() {
		return city_id;
	}
	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}
}
