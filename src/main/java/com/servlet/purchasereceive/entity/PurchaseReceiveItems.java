package com.servlet.purchasereceive.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchasereceive_item", schema = "public")
public class PurchaseReceiveItems implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PurchaseReceiveItemsPK purchaseReceiveItemsPK;
    private Long qty;
    private Long qtybonus;
    private Double price;
    private Double subtotalprice;

    public PurchaseReceiveItemsPK getPurchaseReceiveItemsPK() {
        return purchaseReceiveItemsPK;
    }

    public void setPurchaseReceiveItemsPK(PurchaseReceiveItemsPK purchaseReceiveItemsPK) {
        this.purchaseReceiveItemsPK = purchaseReceiveItemsPK;
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
