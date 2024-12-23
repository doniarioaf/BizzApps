package com.servlet.packinglist.entity;

public class PackingListDataItemDetail {
    private Long idproduct;
    private String productName;
    private Long idcategoryproduct;
    private String categoryProductName;
    private String categoryProductSize;
    private Long categoryProductFromGr;
    private Long categoryProductThruGr;
    private Long qty;
    private Double brutoweight;
    private Double allowance;
    private Double nettoweight;
    private Double price;
    private Double totalprice;
    private Long box;

    public Long getCategoryProductFromGr() {
        return categoryProductFromGr;
    }

    public void setCategoryProductFromGr(Long categoryProductFromGr) {
        this.categoryProductFromGr = categoryProductFromGr;
    }

    public Long getCategoryProductThruGr() {
        return categoryProductThruGr;
    }

    public void setCategoryProductThruGr(Long categoryProductThruGr) {
        this.categoryProductThruGr = categoryProductThruGr;
    }

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(Long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }

    public String getCategoryProductName() {
        return categoryProductName;
    }

    public void setCategoryProductName(String categoryProductName) {
        this.categoryProductName = categoryProductName;
    }

    public String getCategoryProductSize() {
        return categoryProductSize;
    }

    public void setCategoryProductSize(String categoryProductSize) {
        this.categoryProductSize = categoryProductSize;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Double getBrutoweight() {
        return brutoweight;
    }

    public void setBrutoweight(Double brutoweight) {
        this.brutoweight = brutoweight;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public Double getNettoweight() {
        return nettoweight;
    }

    public void setNettoweight(Double nettoweight) {
        this.nettoweight = nettoweight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Long getBox() {
        return box;
    }

    public void setBox(Long box) {
        this.box = box;
    }
}
