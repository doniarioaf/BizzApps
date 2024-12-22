package com.servlet.pricelist.entity;

public class BodyPriceItem {
    private long idproduct;
    private long categoryproductid;
    private Double amount;
    private Double allowance;

    @Override
    public String toString() {
        return "BodyPriceItem{" +
                "idproduct=" + idproduct +
                "categoryproductid=" + categoryproductid +
                ", amount=" + amount +
                ", allowance=" + allowance +
                '}';
    }

    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long idproduct) {
        this.idproduct = idproduct;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
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
