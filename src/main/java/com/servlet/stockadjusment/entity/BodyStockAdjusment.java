package com.servlet.stockadjusment.entity;

import java.sql.Date;

public class BodyStockAdjusment {
    private Long date;
    private Long pricedate;
    private String note;
    private String type;
    private Long idpricelist;
    private BodyStockAdjusmentItem[] items;

    public Long getIdpricelist() {
        return idpricelist;
    }

    public void setIdpricelist(Long idpricelist) {
        this.idpricelist = idpricelist;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getPricedate() {
        return pricedate;
    }

    public void setPricedate(Long pricedate) {
        this.pricedate = pricedate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BodyStockAdjusmentItem[] getItems() {
        return items;
    }

    public void setItems(BodyStockAdjusmentItem[] items) {
        this.items = items;
    }
}
