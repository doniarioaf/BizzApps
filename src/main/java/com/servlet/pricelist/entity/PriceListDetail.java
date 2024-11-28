package com.servlet.pricelist.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class PriceListDetail {
    private Long id;
    private Date pricedate;
    private String createdbyName;
    private Timestamp createddate;
    private String modifiedbyName;
    private Timestamp modifieddate;
    private String deletebyName;
    private Timestamp deletedate;
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

    public String getCreatedbyName() {
        return createdbyName;
    }

    public void setCreatedbyName(String createdbyName) {
        this.createdbyName = createdbyName;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public String getModifiedbyName() {
        return modifiedbyName;
    }

    public void setModifiedbyName(String modifiedbyName) {
        this.modifiedbyName = modifiedbyName;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public String getDeletebyName() {
        return deletebyName;
    }

    public void setDeletebyName(String deletebyName) {
        this.deletebyName = deletebyName;
    }

    public Timestamp getDeletedate() {
        return deletedate;
    }

    public void setDeletedate(Timestamp deletedate) {
        this.deletedate = deletedate;
    }

    public List<PriceListItemData> getItems() {
        return items;
    }

    public void setItems(List<PriceListItemData> items) {
        this.items = items;
    }
}
