package com.servlet.pricelist.entity;

import java.sql.Date;

public class PriceListData {
    private Long id;
    private Date pricedate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPricedate() {
        return pricedate;
    }

    public void setPricedate(Date pricedate) {
        this.pricedate = pricedate;
    }
}
