package com.servlet.pricelist.entity;

public class BodyPriceList {
	private Long idcustomer;
	private String nodocument;
	private boolean isactive;
	private BodyDetailPriceList[] detailpricelist;
	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getNodocument() {
		return nodocument;
	}
	public void setNodocument(String nodocument) {
		this.nodocument = nodocument;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public BodyDetailPriceList[] getDetailpricelist() {
		return detailpricelist;
	}
	public void setDetailpricelist(BodyDetailPriceList[] detailpricelist) {
		this.detailpricelist = detailpricelist;
	}
}
