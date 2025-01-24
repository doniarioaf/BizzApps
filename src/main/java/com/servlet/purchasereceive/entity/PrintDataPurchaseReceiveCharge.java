package com.servlet.purchasereceive.entity;

public class PrintDataPurchaseReceiveCharge {
    private long idpurchasereceive;
    private long idcharge;
    private String chargename;
    private Long qty;
    private Double price;
    private Double subtotalprice;

    public long getIdpurchasereceive() {
        return idpurchasereceive;
    }

    public void setIdpurchasereceive(long idpurchasereceive) {
        this.idpurchasereceive = idpurchasereceive;
    }

    public long getIdcharge() {
        return idcharge;
    }

    public void setIdcharge(long idcharge) {
        this.idcharge = idcharge;
    }

    public String getChargename() {
        return chargename;
    }

    public void setChargename(String chargename) {
        this.chargename = chargename;
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
