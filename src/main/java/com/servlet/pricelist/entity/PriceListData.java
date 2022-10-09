package com.servlet.pricelist.entity;

import java.util.List;

public class PriceListData {
	private Long id;
	private Long idcustomer;
	private String namacustomer;
	private String nodocument;
	private boolean isactive;
	private PriceListTemplate template;
	private List<DetailPriceListData> details;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public PriceListTemplate getTemplate() {
		return template;
	}
	public void setTemplate(PriceListTemplate template) {
		this.template = template;
	}
	public String getNamacustomer() {
		return namacustomer;
	}
	public void setNamacustomer(String namacustomer) {
		this.namacustomer = namacustomer;
	}
	public List<DetailPriceListData> getDetails() {
		return details;
	}
	public void setDetails(List<DetailPriceListData> details) {
		this.details = details;
	}
}
