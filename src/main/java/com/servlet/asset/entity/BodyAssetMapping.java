package com.servlet.asset.entity;

public class BodyAssetMapping {
	private Long id;
	private Long idasset;
	private Long idasset_mapping;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getIdasset() {
		return idasset;
	}
	public void setIdasset(Long idasset) {
		this.idasset = idasset;
	}
	public Long getIdasset_mapping() {
		return idasset_mapping;
	}
	public void setIdasset_mapping(Long idasset_mapping) {
		this.idasset_mapping = idasset_mapping;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
