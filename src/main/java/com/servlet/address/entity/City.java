package com.servlet.address.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities", schema = "public")
public class City implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	private Long city_id;
	private String city_name;
	private Long prov_id;
	public Long getCity_id() {
		return city_id;
	}
	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public Long getProv_id() {
		return prov_id;
	}
	public void setProv_id(Long prov_id) {
		this.prov_id = prov_id;
	}
}
