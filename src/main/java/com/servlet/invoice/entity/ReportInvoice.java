package com.servlet.invoice.entity;

import java.sql.Date;

public class ReportInvoice {
    private Long idinvoice;
    private Date tanggalInvoice;
    private String noInvoice;
    private String customerName;
    private String aju;
    private Double nilaireimbursement;
    private Double nilaippn;
    private Double nilaijasa;
    private Double totalinvoice;
    private String nofakturpajak;

    //ambil dari penerimaan
    private Long idpenerimaan;
    private Date tanggalPelunasan;
    private String bankName;
    private Double pelunasanjasa;
    private Double nilaibuktipotong;
    private String nobuktipotong;
    private Date tanggalbuktipotong;
    private Double penyesuaian;
    private String ketpenyesuaian;

    public String getNofakturpajak() {
        return nofakturpajak;
    }

    public void setNofakturpajak(String nofakturpajak) {
        this.nofakturpajak = nofakturpajak;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getIdinvoice() {
        return idinvoice;
    }

    public void setIdinvoice(Long idinvoice) {
        this.idinvoice = idinvoice;
    }

    public Long getIdpenerimaan() {
        return idpenerimaan;
    }

    public void setIdpenerimaan(Long idpenerimaan) {
        this.idpenerimaan = idpenerimaan;
    }

    public Date getTanggalInvoice() {
        return tanggalInvoice;
    }

    public void setTanggalInvoice(Date tanggalInvoice) {
        this.tanggalInvoice = tanggalInvoice;
    }

    public String getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public String getAju() {
        return aju;
    }

    public void setAju(String aju) {
        this.aju = aju;
    }

    public Double getNilaireimbursement() {
        return nilaireimbursement;
    }

    public void setNilaireimbursement(Double nilaireimbursement) {
        this.nilaireimbursement = nilaireimbursement;
    }

    public Double getNilaippn() {
        return nilaippn;
    }

    public void setNilaippn(Double nilaippn) {
        this.nilaippn = nilaippn;
    }

    public Double getNilaijasa() {
        return nilaijasa;
    }

    public void setNilaijasa(Double nilaijasa) {
        this.nilaijasa = nilaijasa;
    }

    public Double getTotalinvoice() {
        return totalinvoice;
    }

    public void setTotalinvoice(Double totalinvoice) {
        this.totalinvoice = totalinvoice;
    }

    public Date getTanggalPelunasan() {
        return tanggalPelunasan;
    }

    public void setTanggalPelunasan(Date tanggalPelunasan) {
        this.tanggalPelunasan = tanggalPelunasan;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getPelunasanjasa() {
        return pelunasanjasa;
    }

    public void setPelunasanjasa(Double pelunasanjasa) {
        this.pelunasanjasa = pelunasanjasa;
    }

    public Double getNilaibuktipotong() {
        return nilaibuktipotong;
    }

    public void setNilaibuktipotong(Double nilaibuktipotong) {
        this.nilaibuktipotong = nilaibuktipotong;
    }

    public String getNobuktipotong() {
        return nobuktipotong;
    }

    public void setNobuktipotong(String nobuktipotong) {
        this.nobuktipotong = nobuktipotong;
    }

    public Date getTanggalbuktipotong() {
        return tanggalbuktipotong;
    }

    public void setTanggalbuktipotong(Date tanggalbuktipotong) {
        this.tanggalbuktipotong = tanggalbuktipotong;
    }

    public Double getPenyesuaian() {
        return penyesuaian;
    }

    public void setPenyesuaian(Double penyesuaian) {
        this.penyesuaian = penyesuaian;
    }

    public String getKetpenyesuaian() {
        return ketpenyesuaian;
    }

    public void setKetpenyesuaian(String ketpenyesuaian) {
        this.ketpenyesuaian = ketpenyesuaian;
    }
}
