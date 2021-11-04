package com.servlet.admin.role.entity;

public class BodyRole {
	private String nama;
	private String descriptions;
	private Long[] permissions;
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public Long[] getPermissions() {
		return permissions;
	}
	public void setPermissions(Long[] permissions) {
		this.permissions = permissions;
	}
}
