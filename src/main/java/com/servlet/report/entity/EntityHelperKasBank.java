package com.servlet.report.entity;

import java.sql.Date;

public class EntityHelperKasBank implements Comparable<EntityHelperKasBank> {
	private Long penerimaanid;
	private Date penerimaantanggalTransaksi;
	private String penerimaannoVoucher;
	private String penerimaancoa;
	private String penerimaancoaCode;
	private String penerimaannoWO;
	private String penerimaannoAju;
	private String penerimaannoInvoice;
	private String penerimaannamaCustomer;
	private String penerimaanVendorname;
	private String penerimaanEmployeename;
	private String penerimaanIdReceiveType;
	private String penerimaanketerangan;
	private double penerimaanAmount;
	private String penerimaanKategoriName;
	private Double penerimaanPenyesuain;
	private Double penerimaannilaijasa;
	private Double penerimaannilaireimbursement;
	private Double penerimaannilaibuktipotong;
	private String penerimaannobuktipotong;
	private Date penerimaantanggalbuktipotong;
	private Double penerimaannilaippn;
	private String penerimaannamabank;
	private String penerimaanbanknorek;

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
	private String pengeluaran_invItemName;
	private String pengeluaran_payItemName;
	private String pengeluaran_KategoriName;
	
	private Date tanggalTransaksi;
	private String pengeluarannamabank;
	private String pengeluaranbanknorek;

	public String getPenerimaannamabank() {
		return penerimaannamabank;
	}

	public void setPenerimaannamabank(String penerimaannamabank) {
		this.penerimaannamabank = penerimaannamabank;
	}

	public String getPenerimaanbanknorek() {
		return penerimaanbanknorek;
	}

	public void setPenerimaanbanknorek(String penerimaanbanknorek) {
		this.penerimaanbanknorek = penerimaanbanknorek;
	}

	public String getPengeluarannamabank() {
		return pengeluarannamabank;
	}

	public void setPengeluarannamabank(String pengeluarannamabank) {
		this.pengeluarannamabank = pengeluarannamabank;
	}

	public String getPengeluaranbanknorek() {
		return pengeluaranbanknorek;
	}

	public void setPengeluaranbanknorek(String pengeluaranbanknorek) {
		this.pengeluaranbanknorek = pengeluaranbanknorek;
	}

	public String getPenerimaancoaCode() {
		return penerimaancoaCode;
	}

	public void setPenerimaancoaCode(String penerimaancoaCode) {
		this.penerimaancoaCode = penerimaancoaCode;
	}

	public Double getPenerimaannilaijasa() {
		return penerimaannilaijasa;
	}

	public void setPenerimaannilaijasa(Double penerimaannilaijasa) {
		this.penerimaannilaijasa = penerimaannilaijasa;
	}

	public Double getPenerimaannilaireimbursement() {
		return penerimaannilaireimbursement;
	}

	public void setPenerimaannilaireimbursement(Double penerimaannilaireimbursement) {
		this.penerimaannilaireimbursement = penerimaannilaireimbursement;
	}

	public Double getPenerimaannilaibuktipotong() {
		return penerimaannilaibuktipotong;
	}

	public void setPenerimaannilaibuktipotong(Double penerimaannilaibuktipotong) {
		this.penerimaannilaibuktipotong = penerimaannilaibuktipotong;
	}

	public String getPenerimaannobuktipotong() {
		return penerimaannobuktipotong;
	}

	public void setPenerimaannobuktipotong(String penerimaannobuktipotong) {
		this.penerimaannobuktipotong = penerimaannobuktipotong;
	}

	public Date getPenerimaantanggalbuktipotong() {
		return penerimaantanggalbuktipotong;
	}

	public void setPenerimaantanggalbuktipotong(Date penerimaantanggalbuktipotong) {
		this.penerimaantanggalbuktipotong = penerimaantanggalbuktipotong;
	}

	public Double getPenerimaannilaippn() {
		return penerimaannilaippn;
	}

	public void setPenerimaannilaippn(Double penerimaannilaippn) {
		this.penerimaannilaippn = penerimaannilaippn;
	}

	public String getPenerimaanVendorname() {
		return penerimaanVendorname;
	}

	public void setPenerimaanVendorname(String penerimaanVendorname) {
		this.penerimaanVendorname = penerimaanVendorname;
	}

	public String getPenerimaanEmployeename() {
		return penerimaanEmployeename;
	}

	public void setPenerimaanEmployeename(String penerimaanEmployeename) {
		this.penerimaanEmployeename = penerimaanEmployeename;
	}

	public String getPenerimaanIdReceiveType() {
		return penerimaanIdReceiveType;
	}

	public void setPenerimaanIdReceiveType(String penerimaanIdReceiveType) {
		this.penerimaanIdReceiveType = penerimaanIdReceiveType;
	}

	public Double getPenerimaanPenyesuain() {
		return penerimaanPenyesuain;
	}

	public void setPenerimaanPenyesuain(Double penerimaanPenyesuain) {
		this.penerimaanPenyesuain = penerimaanPenyesuain;
	}

	public String getPenerimaanKategoriName() {
		return penerimaanKategoriName;
	}

	public void setPenerimaanKategoriName(String penerimaanKategoriName) {
		this.penerimaanKategoriName = penerimaanKategoriName;
	}

	public String getPengeluaran_KategoriName() {
		return pengeluaran_KategoriName;
	}

	public void setPengeluaran_KategoriName(String pengeluaran_KategoriName) {
		this.pengeluaran_KategoriName = pengeluaran_KategoriName;
	}

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
	
	public String getPengeluaran_invItemName() {
		return pengeluaran_invItemName;
	}
	public void setPengeluaran_invItemName(String pengeluaran_invItemName) {
		this.pengeluaran_invItemName = pengeluaran_invItemName;
	}
	public String getPengeluaran_payItemName() {
		return pengeluaran_payItemName;
	}
	public void setPengeluaran_payItemName(String pengeluaran_payItemName) {
		this.pengeluaran_payItemName = pengeluaran_payItemName;
	}
	@Override
	public int compareTo(EntityHelperKasBank o) {
		// TODO Auto-generated method stub
		if(this.tanggalTransaksi.getTime() == o.getTanggalTransaksi().getTime()) {
			if(o.getPenerimaannoVoucher() != null && o.getPengeluarannoVoucher() != null && this.getPenerimaannoVoucher() != null && this.getPengeluarannoVoucher() != null) {
				return this.getPenerimaannoVoucher().compareTo(this.getPengeluarannoVoucher());
			}
			if(o.getPengeluarannoVoucher() != null && this.getPengeluarannoVoucher() != null) {
				return this.getPengeluarannoVoucher().compareTo(o.getPengeluarannoVoucher());
			}
			else if(o.getPenerimaannoVoucher() != null && this.getPenerimaannoVoucher() != null) {
				return this.getPenerimaannoVoucher().compareTo(o.getPenerimaannoVoucher());
			}else
				return "".compareTo("");
		}
//		return new Long(this.tanggalTransaksi.getTime() - o.getTanggalTransaksi().getTime()).intValue();
		return this.tanggalTransaksi.compareTo(o.getTanggalTransaksi());
	}
	
	
	
	
}
