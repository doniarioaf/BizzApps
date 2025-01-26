package com.servlet.komisi.entity;

public class BodyKomisi {
    private Long date;
    private String note;
    private BodyKomisiItem[] items;

    public BodyKomisiItem[] getItems() {
        return items;
    }

    public void setItems(BodyKomisiItem[] items) {
        this.items = items;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
