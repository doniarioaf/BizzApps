package com.servlet.purchasereceive.entity;

public class PrintDataPurchaseReceiveItems {
    private long idpurchasereceive;
    private long idproduct;
    private String productName;
    private long idcategoryproduct;
    private String categoryProductName;
    private String size;
    private Long weightto;
    private Long weightfrom;
    private String type;
    private Long qty;
    private Long qtybonus;
    private Double price;
    private Double subtotalprice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryProductName() {
        return categoryProductName;
    }

    public void setCategoryProductName(String categoryProductName) {
        this.categoryProductName = categoryProductName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getWeightto() {
        return weightto;
    }

    public void setWeightto(Long weightto) {
        this.weightto = weightto;
    }

    public Long getWeightfrom() {
        return weightfrom;
    }

    public void setWeightfrom(Long weightfrom) {
        this.weightfrom = weightfrom;
    }

    public long getIdpurchasereceive() {
        return idpurchasereceive;
    }

    public void setIdpurchasereceive(long idpurchasereceive) {
        this.idpurchasereceive = idpurchasereceive;
    }

    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long idproduct) {
        this.idproduct = idproduct;
    }

    public long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getQtybonus() {
        return qtybonus;
    }

    public void setQtybonus(Long qtybonus) {
        this.qtybonus = qtybonus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubtotalprice() {
        return subtotalprice;
    }

    public void setSubtotalprice(Double subtotalprice) {
        this.subtotalprice = subtotalprice;
    }
}
