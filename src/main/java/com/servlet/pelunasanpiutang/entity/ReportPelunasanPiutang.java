package com.servlet.pelunasanpiutang.entity;

import java.sql.Date;

public class ReportPelunasanPiutang {
    private String nodocument;
    private Date date;
    private Double kurs;
    private Long idinvoice;
    private Double biayabebanudangmati;
    private Double biayabank;
    private Double pembayaran;
    private String metodepembayaran;

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }

    public Long getIdinvoice() {
        return idinvoice;
    }

    public void setIdinvoice(Long idinvoice) {
        this.idinvoice = idinvoice;
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
