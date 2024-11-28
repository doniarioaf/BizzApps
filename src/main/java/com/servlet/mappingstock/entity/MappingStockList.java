package com.servlet.mappingstock.entity;

public class MappingStockList {
    private long categoryproductid;
    private String categoryproductnama;
    private long categoryproductidmapping;
    private String categoryproductmappingnama;

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
}
