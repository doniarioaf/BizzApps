package com.servlet.invoice.entity;

public class BodySearch {
	private String nodocument;
	private String namacustomer;
	private Long idwo;
	private String type;
	private Long idpenerimaan;
	private Long idcustomer;
	
	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public Long getIdpenerimaan() {
		return idpenerimaan;
	}
	public void setIdpenerimaan(Long idpenerimaan) {
		this.idpenerimaan = idpenerimaan;
	}
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
