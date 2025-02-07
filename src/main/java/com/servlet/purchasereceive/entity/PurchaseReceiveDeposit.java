package com.servlet.purchasereceive.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "purchasereceive_deposit", schema = "public")
public class PurchaseReceiveDeposit implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PurchaseReceiveDepositPK purchaseReceiveDepositPK;

    public PurchaseReceiveDepositPK getPurchaseReceiveDepositPK() {
        return purchaseReceiveDepositPK;
    }

    public void setPurchaseReceiveDepositPK(PurchaseReceiveDepositPK purchaseReceiveDepositPK) {
        this.purchaseReceiveDepositPK = purchaseReceiveDepositPK;
    }
}
