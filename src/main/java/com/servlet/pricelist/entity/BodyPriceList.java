package com.servlet.pricelist.entity;

public class BodyPriceList {
    private long pricedate;
    private long pricedatethru;
    private String notes;
    private Long idcustomer;
    private BodyPriceItem[] items;

    public Long getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Long idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getPricedatethru() {
        return pricedatethru;
    }

    public void setPricedatethru(long pricedatethru) {
        this.pricedatethru = pricedatethru;
    }

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
