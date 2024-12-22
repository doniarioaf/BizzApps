package com.servlet.pricelist.entity;

public class PriceListItemData {
    private long idproduct;
    private String productName;
    private long categoryproductid;
    private String categoryproductidName;
    private Double amount;
    private Double allowance;

    @Override
    public String toString() {
        return "PriceListItemData{" +
                "categoryproductid=" + categoryproductid +
                ", categoryproductidName='" + categoryproductidName + '\'' +
                ", amount=" + amount +
                ", allowance=" + allowance +
                ", idproduct=" + idproduct +
                ", productName=" + productName +
                '}';
    }

    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long idproduct) {
        this.idproduct = idproduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
