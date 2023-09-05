package com.servlet.invoice.entity;

import java.sql.Date;
import java.util.List;

import com.servlet.customermanggala.entity.CustomerManggalaData;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankData;
import com.servlet.penerimaankasbank.entity.PenerimaanKasBankData;

public class PrintInvoiceData {
	private Long id;
	private String nodocument;
	private Date tanggal;
	private Long idcustomer;
	private Long idwo;
	private String refno;
	private String nobl;
	private String deliveredto;
	private String idinvoicetype;
	private Date deliverydate;
	private Double totalinvoice;
	private Double diskonnota;
	private CustomerManggalaData customer;
	private List<DetailInvoicePriceData> detailsprice;
	private List<PenerimaanKasBankData> listpenerimaan;
	private List<DetailPenerimaanKasBankData> detailspenerimaan;
	private Double summarypenerimaanDP;
	private String keteranganinvoice1;
	private String keteranganinvoice2;
	private String keteranganinvoice3;
	private String namapenagih;
	private String ppn;
	private Double nilaippn;
	private List<InvoiceDPData> listDP;
	
	public List<InvoiceDPData> getListDP() {
		return listDP;
	}
	public void setListDP(List<InvoiceDPData> listDP) {
		this.listDP = listDP;
	}
	public Double getNilaippn() {
		return nilaippn;
	}
	public void setNilaippn(Double nilaippn) {
		this.nilaippn = nilaippn;
	}
	public Double getSummarypenerimaanDP() {
		return summarypenerimaanDP;
	}
	public void setSummarypenerimaanDP(Double summarypenerimaanDP) {
		this.summarypenerimaanDP = summarypenerimaanDP;
	}
	public String getNobl() {
		return nobl;
	}
	public void setNobl(String nobl) {
		this.nobl = nobl;
	}
	public Long getIdwo() {
		return idwo;
	}
	public void setIdwo(Long idwo) {
		this.idwo = idwo;
	}
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
	public Double getTotalinvoice() {
		return totalinvoice;
	}
	public void setTotalinvoice(Double totalinvoice) {
		this.totalinvoice = totalinvoice;
	}
	public Double getDiskonnota() {
		return diskonnota;
	}
	public void setDiskonnota(Double diskonnota) {
		this.diskonnota = diskonnota;
	}
	public CustomerManggalaData getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerManggalaData customer) {
		this.customer = customer;
	}
	public List<DetailInvoicePriceData> getDetailsprice() {
		return detailsprice;
	}
	public void setDetailsprice(List<DetailInvoicePriceData> detailsprice) {
		this.detailsprice = detailsprice;
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
	public String getKeteranganinvoice1() {
		return keteranganinvoice1;
	}
	public void setKeteranganinvoice1(String keteranganinvoice1) {
		this.keteranganinvoice1 = keteranganinvoice1;
	}
	public String getKeteranganinvoice2() {
		return keteranganinvoice2;
	}
	public void setKeteranganinvoice2(String keteranganinvoice2) {
		this.keteranganinvoice2 = keteranganinvoice2;
	}
	public String getKeteranganinvoice3() {
		return keteranganinvoice3;
	}
	public void setKeteranganinvoice3(String keteranganinvoice3) {
		this.keteranganinvoice3 = keteranganinvoice3;
	}
	public String getNamapenagih() {
		return namapenagih;
	}
	public void setNamapenagih(String namapenagih) {
		this.namapenagih = namapenagih;
	}
	public String getIdinvoicetype() {
		return idinvoicetype;
	}
	public void setIdinvoicetype(String idinvoicetype) {
		this.idinvoicetype = idinvoicetype;
	}
	public String getPpn() {
		return ppn;
	}
	public void setPpn(String ppn) {
		this.ppn = ppn;
	}
	
}
