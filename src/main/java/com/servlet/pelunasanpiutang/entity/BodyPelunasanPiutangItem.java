package com.servlet.pelunasanpiutang.entity;

public class BodyPelunasanPiutangItem {
    private Long idinvoice;
    private Double biayabebanudangmati;
    private Double biayabank;
    private Double pembayaran;
    private String metodepembayaran;

    @Override
    public String toString() {
        return "PelunasanPiutangItem{" +
                "idinvoice=" + idinvoice +
                ", biayabebanudangmati=" + biayabebanudangmati +
                ", biayabank=" + biayabank +
                ", pembayaran=" + pembayaran +
                ", metodepembayaran='" + metodepembayaran + '\'' +
                '}';
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
