package com.servlet.purchasereceive.entity;

public class PrintDataPurchaseReceiveInventori {
    private long idpurchasereceive;
    private long idinventori;
    private String inventoriname;
    private Long qty;
    private Double price;
    private Double subtotalprice;

    public long getIdpurchasereceive() {
        return idpurchasereceive;
    }

    public void setIdpurchasereceive(long idpurchasereceive) {
        this.idpurchasereceive = idpurchasereceive;
    }

    public long getIdinventori() {
        return idinventori;
    }

    public void setIdinventori(long idinventori) {
        this.idinventori = idinventori;
    }

    public String getInventoriname() {
        return inventoriname;
    }

    public void setInventoriname(String inventoriname) {
        this.inventoriname = inventoriname;
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
