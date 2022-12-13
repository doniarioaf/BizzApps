package com.servlet.invoice.entity;

import java.sql.Date;
import java.util.List;

import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankData;
import com.servlet.penerimaankasbank.entity.PenerimaanKasBankData;

public class InvoiceData {
	private Long id;
	private String nodocument;
	private Date tanggal;
	private Long idcustomer;
	private String namaCustomer;
	private String refno;
	private String deliveredto;
	private Date deliverydate;
	private Long idwo;
	private String noocumentwo;
	private String jalurwo;
	private Long idsuratjalan;
	private String noocumentsuratjalan;
	private Long idwarehousesuratjalan;
	private String idinvoicetype;
	private String namainvoicetype;
	private Double totalinvoice;
	private Double diskonnota;
	private boolean isactive;
	private InvoiceTemplate template;
	private List<DetailInvoicePriceData> detailsprice;
	private List<PenerimaanKasBankData> listpenerimaan;
	private List<DetailPenerimaanKasBankData> detailspenerimaan;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getNamaCustomer() {
		return namaCustomer;
	}
	public void setNamaCustomer(String namaCustomer) {
		this.namaCustomer = namaCustomer;
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
	public String getNoocumentwo() {
		return noocumentwo;
	}
	public void setNoocumentwo(String noocumentwo) {
		this.noocumentwo = noocumentwo;
	}
	public Long getIdsuratjalan() {
		return idsuratjalan;
	}
	public void setIdsuratjalan(Long idsuratjalan) {
		this.idsuratjalan = idsuratjalan;
	}
	public String getNoocumentsuratjalan() {
		return noocumentsuratjalan;
	}
	public void setNoocumentsuratjalan(String noocumentsuratjalan) {
		this.noocumentsuratjalan = noocumentsuratjalan;
	}
	public String getIdinvoicetype() {
		return idinvoicetype;
	}
	public void setIdinvoicetype(String idinvoicetype) {
		this.idinvoicetype = idinvoicetype;
	}
	public String getNamainvoicetype() {
		return namainvoicetype;
	}
	public void setNamainvoicetype(String namainvoicetype) {
		this.namainvoicetype = namainvoicetype;
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
	public InvoiceTemplate getTemplate() {
		return template;
	}
	public void setTemplate(InvoiceTemplate template) {
		this.template = template;
	}
	public List<DetailInvoicePriceData> getDetailsprice() {
		return detailsprice;
	}
	public void setDetailsprice(List<DetailInvoicePriceData> detailsprice) {
		this.detailsprice = detailsprice;
	}
	public Double getDiskonnota() {
		return diskonnota;
	}
	public void setDiskonnota(Double diskonnota) {
		this.diskonnota = diskonnota;
	}
	public List<PenerimaanKasBankData> getListpenerimaan() {
		return listpenerimaan;
	}
	public void setListpenerimaan(List<PenerimaanKasBankData> listpenerimaan) {
		this.listpenerimaan = listpenerimaan;
	}
	public List<DetailPenerimaanKasBankData> getDetailspenerimaan() {
		return detailspenerimaan;
	}
	public void setDetailspenerimaan(List<DetailPenerimaanKasBankData> detailspenerimaan) {
		this.detailspenerimaan = detailspenerimaan;
	}
	public String getJalurwo() {
		return jalurwo;
	}
	public void setJalurwo(String jalurwo) {
		this.jalurwo = jalurwo;
	}
	public Long getIdwarehousesuratjalan() {
		return idwarehousesuratjalan;
	}
	public void setIdwarehousesuratjalan(Long idwarehousesuratjalan) {
		this.idwarehousesuratjalan = idwarehousesuratjalan;
	}
}
