package com.servlet.product.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_product", schema = "public")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="m_product_id_seq")
	private Long id;
	private long idcompany;
	private long idbranch;
	private String code;
	private String nama;
	private Double hargabeli;
	private Double hargajual;
	private Double conv1to4;
	private Double conv2to4;
	private Double conv3to4;
	private Double conv4to4;
	private String createdby;
	private Timestamp createddate;
	private String updateby;
	private Timestamp updatedate;
	private String deleteby;
	private Timestamp deletedate;
	private boolean isdelete;
	private String image;
	private String barcode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}
	public long getIdbranch() {
		return idbranch;
	}
	public void setIdbranch(long idbranch) {
		this.idbranch = idbranch;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public Double getHargabeli() {
		return hargabeli;
	}
	public void setHargabeli(Double hargabeli) {
		this.hargabeli = hargabeli;
	}
	public Double getHargajual() {
		return hargajual;
	}
	public void setHargajual(Double hargajual) {
		this.hargajual = hargajual;
	}
	public Double getConv1to4() {
		return conv1to4;
	}
	public void setConv1to4(Double conv1to4) {
		this.conv1to4 = conv1to4;
	}
	public Double getConv2to4() {
		return conv2to4;
	}
	public void setConv2to4(Double conv2to4) {
		this.conv2to4 = conv2to4;
	}
	public Double getConv3to4() {
		return conv3to4;
	}
	public void setConv3to4(Double conv3to4) {
		this.conv3to4 = conv3to4;
	}
	public Double getConv4to4() {
		return conv4to4;
	}
	public void setConv4to4(Double conv4to4) {
		this.conv4to4 = conv4to4;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public Timestamp getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
	public String getDeleteby() {
		return deleteby;
	}
	public void setDeleteby(String deleteby) {
		this.deleteby = deleteby;
	}
	public Timestamp getDeletedate() {
		return deletedate;
	}
	public void setDeletedate(Timestamp deletedate) {
		this.deletedate = deletedate;
	}
	public boolean isIsdelete() {
		return isdelete;
	}
	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
