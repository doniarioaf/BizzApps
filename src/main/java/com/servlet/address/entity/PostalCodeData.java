package com.servlet.address.entity;

public class PostalCodeData {
	
	private Long postal_id;
	private Long subdis_id;
	private Long dis_id;
	private Long city_id;
	private Long prov_id;
	private Long postal_code;
	
	public PostalCodeData() {}
	public PostalCodeData(Long postal_id,Long subdis_id,Long dis_id,Long city_id,Long prov_id,Long postal_code) {
		this.postal_id = postal_id;
		this.subdis_id = subdis_id;
		this.dis_id = dis_id;
		this.city_id = city_id;
		this.prov_id = prov_id;
		this.postal_code = postal_code;
	}
	public Long getPostal_id() {
		return postal_id;
	}
	public void setPostal_id(Long postal_id) {
		this.postal_id = postal_id;
	}
	public Long getSubdis_id() {
		return subdis_id;
	}
	public void setSubdis_id(Long subdis_id) {
		this.subdis_id = subdis_id;
	}
	public Long getDis_id() {
		return dis_id;
	}
	public void setDis_id(Long dis_id) {
		this.dis_id = dis_id;
	}
	public Long getCity_id() {
		return city_id;
	}
	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}
	public Long getProv_id() {
		return prov_id;
	}
	public void setProv_id(Long prov_id) {
		this.prov_id = prov_id;
	}
	public Long getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(Long postal_code) {
		this.postal_code = postal_code;
	}
}
