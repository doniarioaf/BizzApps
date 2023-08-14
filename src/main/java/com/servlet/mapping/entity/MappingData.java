package com.servlet.mapping.entity;

public class MappingData {
	private Long id;
	private String mappingcode;
	private String mappinggrup;
	private Long idmaster;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMappingcode() {
		return mappingcode;
	}
	public void setMappingcode(String mappingcode) {
		this.mappingcode = mappingcode;
	}
	public String getMappinggrup() {
		return mappinggrup;
	}
	public void setMappinggrup(String mappinggrup) {
		this.mappinggrup = mappinggrup;
	}
	public Long getIdmaster() {
		return idmaster;
	}
	public void setIdmaster(Long idmaster) {
		this.idmaster = idmaster;
	}
}
