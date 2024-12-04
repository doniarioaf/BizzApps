package com.servlet.purchasereceive.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "purchasereceive_charge", schema = "public")
public class PurchaseReceiveCharge implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PurchaseReceiveChargePK purchaseReceiveChargePK;

    private Long qty;
    private Double price;
    private Double subtotalprice;

    public PurchaseReceiveChargePK getPurchaseReceiveChargePK() {
        return purchaseReceiveChargePK;
    }

    public void setPurchaseReceiveChargePK(PurchaseReceiveChargePK purchaseReceiveChargePK) {
        this.purchaseReceiveChargePK = purchaseReceiveChargePK;
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
