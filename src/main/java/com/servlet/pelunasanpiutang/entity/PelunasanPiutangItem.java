package com.servlet.pelunasanpiutang.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pelunasanpiutang_item", schema = "public")
public class PelunasanPiutangItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PelunasanPiutangItemPK pelunasanPiutangItemPK;
    private Double biayabebanudangmati;
    private Double biayabank;
    private Double pembayaran;
    private String metodepembayaran;

    public PelunasanPiutangItemPK getPelunasanPiutangItemPK() {
        return pelunasanPiutangItemPK;
    }

    public void setPelunasanPiutangItemPK(PelunasanPiutangItemPK pelunasanPiutangItemPK) {
        this.pelunasanPiutangItemPK = pelunasanPiutangItemPK;
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
