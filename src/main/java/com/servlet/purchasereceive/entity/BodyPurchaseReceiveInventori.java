package com.servlet.purchasereceive.entity;

public class BodyPurchaseReceiveInventori {
    private Long idinventori;
    private Long qty;
    private Double price;
    private Double subtotalprice;

    @Override
    public String toString() {
        return "PurchaseReceiveInventori{" +
                "idinventori=" + idinventori +
                ", qty=" + qty +
                ", price=" + price +
                ", subtotalprice=" + subtotalprice +
                '}';
    }

    public long getIdinventori() {
        return idinventori;
    }

    public void setIdinventori(long idinventori) {
        this.idinventori = idinventori;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
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
