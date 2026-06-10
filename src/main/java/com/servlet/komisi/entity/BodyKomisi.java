package com.servlet.komisi.entity;

public class BodyKomisi {
    private Long date;
    private String note;
    private Double additional_commission;
    private String description;
    private BodyKomisiItem[] items;

    public Double getAdditional_commission() {
        return additional_commission;
    }

    public void setAdditional_commission(Double additional_commission) {
        this.additional_commission = additional_commission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
