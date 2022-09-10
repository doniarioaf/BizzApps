package com.servlet.address.entity;

public class SubDistrictData {
	
	private Long subdis_id;
	private String subdis_name;
	private Long dis_id;
	
	public SubDistrictData() {
		
	}
	public SubDistrictData(Long subdis_id,String subdis_name,Long dis_id) {
		this.subdis_id = subdis_id;
		this.subdis_name = subdis_name;
		this.dis_id = dis_id;
	}
	public Long getSubdis_id() {
		return subdis_id;
	}
	public void setSubdis_id(Long subdis_id) {
		this.subdis_id = subdis_id;
	}
	public String getSubdis_name() {
		return subdis_name;
	}
	public void setSubdis_name(String subdis_name) {
		this.subdis_name = subdis_name;
	}
	public Long getDis_id() {
		return dis_id;
	}
	public void setDis_id(Long dis_id) {
		this.dis_id = dis_id;
	}
}
