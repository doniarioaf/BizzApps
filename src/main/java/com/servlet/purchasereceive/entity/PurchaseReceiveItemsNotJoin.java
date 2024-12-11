package com.servlet.purchasereceive.entity;

public class PurchaseReceiveItemsNotJoin {
    private long qty;
    private long qtyBonus;
    private long idcategoryproduct;
    private long idproduct;
    private String type;
    private Double price;
    private Double subtotalprice;


    @Override
    public String toString() {
        return "PurchaseReceiveItems{" +
                "qty=" + qty +
                ", idcategoryproduct=" + idcategoryproduct +
                ", idproduct=" + idproduct +
                ", type='" + type + '\'' +
                ", qtyBonus='" + qtyBonus + '\'' +
                ", price='" + price + '\'' +
                ", subtotalprice='" + subtotalprice + '\'' +
                '}';
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

    public long getQtyBonus() {
        return qtyBonus;
    }

    public void setQtyBonus(long qtyBonus) {
        this.qtyBonus = qtyBonus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long idproduct) {
        this.idproduct = idproduct;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }
}
