package com.servlet.workorder.entity;

public class BodySearch {
	private String nodocument;
	private String namacustomer;
	private String namacargo;
	private Long idwo;
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
	public String getNamacargo() {
		return namacargo;
	}
	public void setNamacargo(String namacargo) {
		this.namacargo = namacargo;
	}
}
