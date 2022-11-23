package com.servlet.invoice.entity;

public class BodySearch {
	private String nodocument;
	private String namacustomer;
	private Long idwo;
	private String type;
	public String getNodocument() {
		return nodocument;
	}
	public void setNodocument(String nodocument) {
		this.nodocument = nodocument;
	}
	public String getNamacustomer() {
		return namacustomer;
	}
	public void setNamacustomer(String namacustomer) {
		this.namacustomer = namacustomer;
	}
	public Long getIdwo() {
		return idwo;
	}
	public void setIdwo(Long idwo) {
		this.idwo = idwo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
