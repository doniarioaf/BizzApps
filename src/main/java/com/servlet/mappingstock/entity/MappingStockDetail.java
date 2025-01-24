package com.servlet.mappingstock.entity;

import java.sql.Timestamp;

public class MappingStockDetail {
    private long categoryproductid;
    private String categoryproductnama;
    private long categoryproductidmapping;
    private String categoryproductmappingnama;
    private String createdbyName;
    private Timestamp createddate;
    private String modifiedbyName;
    private Timestamp modifieddate;

    public long getCategoryproductid() {
        return categoryproductid;
    }

    public void setCategoryproductid(long categoryproductid) {
        this.categoryproductid = categoryproductid;
    }

    public String getCategoryproductnama() {
        return categoryproductnama;
    }

    public void setCategoryproductnama(String categoryproductnama) {
        this.categoryproductnama = categoryproductnama;
    }

    public long getCategoryproductidmapping() {
        return categoryproductidmapping;
    }

    public void setCategoryproductidmapping(long categoryproductidmapping) {
        this.categoryproductidmapping = categoryproductidmapping;
    }

    public String getCategoryproductmappingnama() {
        return categoryproductmappingnama;
    }

    public void setCategoryproductmappingnama(String categoryproductmappingnama) {
        this.categoryproductmappingnama = categoryproductmappingnama;
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
}
