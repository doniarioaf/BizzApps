package com.servlet.pelunasanpiutang.entity;

public class BodyPelunasanPiutang {
    private Long date;
    private Double kurs;
    private BodyPelunasanPiutangItem[] items;

    public BodyPelunasanPiutangItem[] getItems() {
        return items;
    }

    public void setItems(BodyPelunasanPiutangItem[] items) {
        this.items = items;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }
}
