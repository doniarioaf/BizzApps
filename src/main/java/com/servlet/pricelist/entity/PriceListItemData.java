package com.servlet.pricelist.entity;

public class PriceListItemData {
    private long categoryproductid;
    private String categoryproductidName;
    private Double amount;

    public long getCategoryproductid() {
        return categoryproductid;
    }

    public void setCategoryproductid(long categoryproductid) {
        this.categoryproductid = categoryproductid;
    }

    public String getCategoryproductidName() {
        return categoryproductidName;
    }

    public void setCategoryproductidName(String categoryproductidName) {
        this.categoryproductidName = categoryproductidName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
