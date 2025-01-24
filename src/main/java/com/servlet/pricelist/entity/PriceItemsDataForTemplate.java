package com.servlet.pricelist.entity;

import java.sql.Date;
import java.util.List;

public class PriceItemsDataForTemplate {
    private Long id;
    private Date pricedate;
    private List<PriceListItemData> items;

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

    public List<PriceListItemData> getItems() {
        return items;
    }

    public void setItems(List<PriceListItemData> items) {
        this.items = items;
    }
}
