package com.servlet.address.entity;

public class DistrictData {
	private Long dis_id;
	private String dis_name;
	private Long city_id;
	
	public DistrictData() {
		
	}
	public DistrictData(Long dis_id,String dis_name,Long city_id) {
		this.dis_id = dis_id;
		this.dis_name = dis_name;
		this.city_id = city_id;
	}
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
