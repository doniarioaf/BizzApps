package com.servlet.pricelist.entity;

public class BodyPriceList {
    private long pricedate;
    private BodyPriceItem[] items;

    public long getPricedate() {
        return pricedate;
    }

    public void setPricedate(long pricedate) {
        this.pricedate = pricedate;
    }

    public BodyPriceItem[] getItems() {
        return items;
    }

    public void setItems(BodyPriceItem[] items) {
        this.items = items;
    }
}
