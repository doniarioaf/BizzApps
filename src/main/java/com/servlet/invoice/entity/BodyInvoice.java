package com.servlet.invoice.entity;


public class BodyInvoice {
	private Long tanggal;
	private Long idcustomer;
	private String refno;
	private String deliveredto;
	private Long deliverydate;
	private Long idwo;
	private Long idsuratjalan;
	private Long idinvoicetype;
	private Double totalinvoice;
	private Double diskonnota;
	private boolean isactive;
	private BodyDetailInvoicePrice[] detailsprice;
	public Long getTanggal() {
		return tanggal;
	}
	public void setTanggal(Long tanggal) {
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
	public Long getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(Long deliverydate) {
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
	public BodyDetailInvoicePrice[] getDetailsprice() {
		return detailsprice;
	}
	public void setDetailsprice(BodyDetailInvoicePrice[] detailsprice) {
		this.detailsprice = detailsprice;
	}
	public Double getDiskonnota() {
		return diskonnota;
	}
	public void setDiskonnota(Double diskonnota) {
		this.diskonnota = diskonnota;
	}
}
