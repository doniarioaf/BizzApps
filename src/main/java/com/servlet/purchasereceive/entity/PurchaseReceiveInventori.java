package com.servlet.purchasereceive.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "purchasereceive_inventori", schema = "public")
public class PurchaseReceiveInventori {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PurchaseReceiveInventoriPK purchaseReceiveInventoriPK;
    private Long qty;
    private Double price;
    private Double subtotalprice;

    public PurchaseReceiveInventoriPK getPurchaseReceiveInventoriPK() {
        return purchaseReceiveInventoriPK;
    }

    public void setPurchaseReceiveInventoriPK(PurchaseReceiveInventoriPK purchaseReceiveInventoriPK) {
        this.purchaseReceiveInventoriPK = purchaseReceiveInventoriPK;
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
