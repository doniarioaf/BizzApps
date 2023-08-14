package com.servlet.report.entity;

import java.sql.Date;

public class EntityHelperKasBank implements Comparable<EntityHelperKasBank> {
	private Long penerimaanid;
	private Date penerimaantanggalTransaksi;
	private String penerimaannoVoucher;
	private String penerimaancoa;
	private String penerimaannoWO;
	private String penerimaannoAju;
	private String penerimaannoInvoice;
	private String penerimaannamaCustomer;
	private String penerimaanketerangan;
	private double penerimaanAmount;
	
	private Long pengeluaranid;
	private Date pengeluarantanggalTransaksi;
	private String pengeluarannoVoucher;
	private String pengeluarancoa;
	private String pengeluarannoWO;
	private String pengeluarannoAju;
	private String pengeluarannoInvoice;
	private String pengeluarannamaCustomer;
	private String pengeluaranketerangan;
	private String pengeluaran_paymentto;
	private String pengeluaran_customername;
	private String pengeluaran_vendorname;
	private String pengeluaran_employeename;
	private double pengeluaranAmount;
	
	private Date tanggalTransaksi;
	
	
	public Date getTanggalTransaksi() {
		return tanggalTransaksi;
	}
	public void setTanggalTransaksi(Date tanggalTransaksi) {
		this.tanggalTransaksi = tanggalTransaksi;
	}
	public Long getPenerimaanid() {
		return penerimaanid;
	}
	public void setPenerimaanid(Long penerimaanid) {
		this.penerimaanid = penerimaanid;
	}
	public Long getPengeluaranid() {
		return pengeluaranid;
	}
	public void setPengeluaranid(Long pengeluaranid) {
		this.pengeluaranid = pengeluaranid;
	}
	public String getPengeluaran_paymentto() {
		return pengeluaran_paymentto;
	}
	public void setPengeluaran_paymentto(String pengeluaran_paymentto) {
		this.pengeluaran_paymentto = pengeluaran_paymentto;
	}
	public String getPengeluaran_customername() {
		return pengeluaran_customername;
	}
	public void setPengeluaran_customername(String pengeluaran_customername) {
		this.pengeluaran_customername = pengeluaran_customername;
	}
	public String getPengeluaran_vendorname() {
		return pengeluaran_vendorname;
	}
	public void setPengeluaran_vendorname(String pengeluaran_vendorname) {
		this.pengeluaran_vendorname = pengeluaran_vendorname;
	}
	public String getPengeluaran_employeename() {
		return pengeluaran_employeename;
	}
	public void setPengeluaran_employeename(String pengeluaran_employeename) {
		this.pengeluaran_employeename = pengeluaran_employeename;
	}
	public Date getPenerimaantanggalTransaksi() {
		return penerimaantanggalTransaksi;
	}
	public void setPenerimaantanggalTransaksi(Date penerimaantanggalTransaksi) {
		this.penerimaantanggalTransaksi = penerimaantanggalTransaksi;
	}
	public String getPenerimaannoVoucher() {
		return penerimaannoVoucher;
	}
	public void setPenerimaannoVoucher(String penerimaannoVoucher) {
		this.penerimaannoVoucher = penerimaannoVoucher;
	}
	public String getPenerimaancoa() {
		return penerimaancoa;
	}
	public void setPenerimaancoa(String penerimaancoa) {
		this.penerimaancoa = penerimaancoa;
	}
	public String getPenerimaannoWO() {
		return penerimaannoWO;
	}
	public void setPenerimaannoWO(String penerimaannoWO) {
		this.penerimaannoWO = penerimaannoWO;
	}
	public String getPenerimaannoAju() {
		return penerimaannoAju;
	}
	public void setPenerimaannoAju(String penerimaannoAju) {
		this.penerimaannoAju = penerimaannoAju;
	}
	public String getPenerimaannoInvoice() {
		return penerimaannoInvoice;
	}
	public void setPenerimaannoInvoice(String penerimaannoInvoice) {
		this.penerimaannoInvoice = penerimaannoInvoice;
	}
	public String getPenerimaannamaCustomer() {
		return penerimaannamaCustomer;
	}
	public void setPenerimaannamaCustomer(String penerimaannamaCustomer) {
		this.penerimaannamaCustomer = penerimaannamaCustomer;
	}
	public String getPenerimaanketerangan() {
		return penerimaanketerangan;
	}
	public void setPenerimaanketerangan(String penerimaanketerangan) {
		this.penerimaanketerangan = penerimaanketerangan;
	}
	public double getPenerimaanAmount() {
		return penerimaanAmount;
	}
	public void setPenerimaanAmount(double penerimaanAmount) {
		this.penerimaanAmount = penerimaanAmount;
	}
	public Date getPengeluarantanggalTransaksi() {
		return pengeluarantanggalTransaksi;
	}
	public void setPengeluarantanggalTransaksi(Date pengeluarantanggalTransaksi) {
		this.pengeluarantanggalTransaksi = pengeluarantanggalTransaksi;
	}
	public String getPengeluarannoVoucher() {
		return pengeluarannoVoucher;
	}
	public void setPengeluarannoVoucher(String pengeluarannoVoucher) {
		this.pengeluarannoVoucher = pengeluarannoVoucher;
	}
	public String getPengeluarancoa() {
		return pengeluarancoa;
	}
	public void setPengeluarancoa(String pengeluarancoa) {
		this.pengeluarancoa = pengeluarancoa;
	}
	public String getPengeluarannoWO() {
		return pengeluarannoWO;
	}
	public void setPengeluarannoWO(String pengeluarannoWO) {
		this.pengeluarannoWO = pengeluarannoWO;
	}
	public String getPengeluarannoAju() {
		return pengeluarannoAju;
	}
	public void setPengeluarannoAju(String pengeluarannoAju) {
		this.pengeluarannoAju = pengeluarannoAju;
	}
	public String getPengeluarannoInvoice() {
		return pengeluarannoInvoice;
	}
	public void setPengeluarannoInvoice(String pengeluarannoInvoice) {
		this.pengeluarannoInvoice = pengeluarannoInvoice;
	}
	public String getPengeluarannamaCustomer() {
		return pengeluarannamaCustomer;
	}
	public void setPengeluarannamaCustomer(String pengeluarannamaCustomer) {
		this.pengeluarannamaCustomer = pengeluarannamaCustomer;
	}
	public String getPengeluaranketerangan() {
		return pengeluaranketerangan;
	}
	public void setPengeluaranketerangan(String pengeluaranketerangan) {
		this.pengeluaranketerangan = pengeluaranketerangan;
	}
	public double getPengeluaranAmount() {
		return pengeluaranAmount;
	}
	public void setPengeluaranAmount(double pengeluaranAmount) {
		this.pengeluaranAmount = pengeluaranAmount;
	}
	@Override
	public int compareTo(EntityHelperKasBank o) {
		// TODO Auto-generated method stub
		return new Long(this.tanggalTransaksi.getTime() - o.getTanggalTransaksi().getTime()).intValue();
	}
	
	
	
	
}
