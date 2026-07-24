package com.servlet.pelunasanpiutang.entity;

import java.sql.Date;

public class PelunasanPiutangItemDetail {
    private long idinvoice;
    private String nodocumentInvoice;
    private Double amountInvoice;
    private Double kursInvoice;
    private Double outstandingInvoice;
    private Double biayabebanudangmati;
    private Double biayabank;
    private Double pembayaran;
    private String metodepembayaran;
    private Date invoiceDate;

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public long getIdinvoice() {
        return idinvoice;
    }

    public void setIdinvoice(long idinvoice) {
        this.idinvoice = idinvoice;
    }

    public String getNodocumentInvoice() {
        return nodocumentInvoice;
    }

    public void setNodocumentInvoice(String nodocumentInvoice) {
        this.nodocumentInvoice = nodocumentInvoice;
    }

    public Double getAmountInvoice() {
        return amountInvoice;
    }

    public void setAmountInvoice(Double amountInvoice) {
        this.amountInvoice = amountInvoice;
    }

    public Double getKursInvoice() {
        return kursInvoice;
    }

    public void setKursInvoice(Double kursInvoice) {
        this.kursInvoice = kursInvoice;
    }

    public Double getOutstandingInvoice() {
        return outstandingInvoice;
    }

    public void setOutstandingInvoice(Double outstandingInvoice) {
        this.outstandingInvoice = outstandingInvoice;
    }

    public Double getBiayabebanudangmati() {
        return biayabebanudangmati;
    }

    public void setBiayabebanudangmati(Double biayabebanudangmati) {
        this.biayabebanudangmati = biayabebanudangmati;
    }

    public Double getBiayabank() {
        return biayabank;
    }

    public void setBiayabank(Double biayabank) {
        this.biayabank = biayabank;
    }

    public Double getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(Double pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getMetodepembayaran() {
        return metodepembayaran;
    }

    public void setMetodepembayaran(String metodepembayaran) {
        this.metodepembayaran = metodepembayaran;
    }
}
