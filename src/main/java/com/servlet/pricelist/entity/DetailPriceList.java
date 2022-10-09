package com.servlet.pricelist.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "detail_price_list", schema = "public")
public class DetailPriceList implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailPriceListPK detailPriceListPK;
	private Double price;
	private String ismandatory;
	public DetailPriceListPK getDetailPriceListPK() {
		return detailPriceListPK;
	}
	public void setDetailPriceListPK(DetailPriceListPK detailPriceListPK) {
		this.detailPriceListPK = detailPriceListPK;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getIsmandatory() {
		return ismandatory;
	}
	public void setIsmandatory(String ismandatory) {
		this.ismandatory = ismandatory;
	}
}
