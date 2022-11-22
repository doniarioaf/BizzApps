package com.servlet.invoice.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_invoice", schema = "public")
public class Invoice implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="m_invoice_id_seq")
	private Long id;
	private Long idcompany;
	private Long idbranch;
	private String nodocument;
	private Date tanggal;
	private Long idcustomer;
	private String refno;
	private String deliveredto;
	private Date deliverydate;
	private Long idwo;
	private Long idsuratjalan;
	private Long idinvoicetype;
	private Double totalinvoice;
	private boolean isactive;
	private boolean isdelete;
	private String createdby;
	private Timestamp createddate;
	private String updateby;
	private Timestamp updatedate;
	private String deleteby;
	private Timestamp deletedate;
	private Double diskonnota;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(Long idcompany) {
		this.idcompany = idcompany;
	}
	public Long getIdbranch() {
		return idbranch;
	}
	public void setIdbranch(Long idbranch) {
		this.idbranch = idbranch;
	}
	public String getNodocument() {
		return nodocument;
	}
	public void setNodocument(String nodocument) {
		this.nodocument = nodocument;
	}
	public Date getTanggal() {
		return tanggal;
	}
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}
	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getRefno() {
		return refno;
	}
	public void setRefno(String refno) {
		this.refno = refno;
	}
	public String getDeliveredto() {
		return deliveredto;
	}
	public void setDeliveredto(String deliveredto) {
		this.deliveredto = deliveredto;
	}
	public Date getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}
	public Long getIdwo() {
		return idwo;
	}
	public void setIdwo(Long idwo) {
		this.idwo = idwo;
	}
	public Long getIdsuratjalan() {
		return idsuratjalan;
	}
	public void setIdsuratjalan(Long idsuratjalan) {
		this.idsuratjalan = idsuratjalan;
	}
	public Long getIdinvoicetype() {
		return idinvoicetype;
	}
	public void setIdinvoicetype(Long idinvoicetype) {
		this.idinvoicetype = idinvoicetype;
	}
	public Double getTotalinvoice() {
		return totalinvoice;
	}
	public void setTotalinvoice(Double totalinvoice) {
		this.totalinvoice = totalinvoice;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public boolean isIsdelete() {
		return isdelete;
	}
	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
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
	public Double getDiskonnota() {
		return diskonnota;
	}
	public void setDiskonnota(Double diskonnota) {
		this.diskonnota = diskonnota;
	}
}
