package com.servlet.pricelist.entity;

public class BodyPriceItem {
    private long categoryproductid;
    private Double amount;

    @Override
    public String toString() {
        return "BodyPriceItem{" +
                "categoryproductid=" + categoryproductid +
                ", amount=" + amount +
                '}';
    }

    public long getCategoryproductid() {
        return categoryproductid;
    }

    public void setCategoryproductid(long categoryproductid) {
        this.categoryproductid = categoryproductid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
