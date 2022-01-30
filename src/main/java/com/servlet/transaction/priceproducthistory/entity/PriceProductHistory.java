package com.servlet.transaction.priceproducthistory.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_price_product_history", schema = "public")
public class PriceProductHistory implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="price_product_history_id_seq")
	private long id;	
	private long idcompany;
	private long idproduct;
	private Timestamp tanggal;
	private double pricebuy;
	private double pricesell;
	private double priceselluom2;
	private double priceselluom3;
	private double priceselluom4;
	private long userid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}
	public long getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(long idproduct) {
		this.idproduct = idproduct;
	}
	public Timestamp getTanggal() {
		return tanggal;
	}
	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
	public Double getPricebuy() {
		return pricebuy;
	}
	public void setPricebuy(Double pricebuy) {
		this.pricebuy = pricebuy;
	}
	public Double getPricesell() {
		return pricesell;
	}
	public void setPricesell(Double pricesell) {
		this.pricesell = pricesell;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public double getPriceselluom2() {
		return priceselluom2;
	}
	public void setPriceselluom2(double priceselluom2) {
		this.priceselluom2 = priceselluom2;
	}
	public double getPriceselluom3() {
		return priceselluom3;
	}
	public void setPriceselluom3(double priceselluom3) {
		this.priceselluom3 = priceselluom3;
	}
	public double getPriceselluom4() {
		return priceselluom4;
	}
	public void setPriceselluom4(double priceselluom4) {
		this.priceselluom4 = priceselluom4;
	}
	
}
