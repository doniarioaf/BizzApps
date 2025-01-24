package com.servlet.purchasereceive.entity;

public class BodyPurchaseReceiveCharge {
    private long idcharge;
    private long qty;
    private Double price;
    private Double subtotalprice;

    @Override
    public String toString() {
        return "PurchaseReceiveCharge{" +
                "idcharge=" + idcharge +
                ", qty=" + qty +
                ", price=" + price +
                ", subtotalprice=" + subtotalprice +
                '}';
    }

    public long getIdcharge() {
        return idcharge;
    }

    public void setIdcharge(long idcharge) {
        this.idcharge = idcharge;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
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
