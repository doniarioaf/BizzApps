package com.servlet.purchasereceive.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PurchaseReceiveDepositPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idpurchasereceive;
    private long iddeposit;

    public long getIdpurchasereceive() {
        return idpurchasereceive;
    }

    public void setIdpurchasereceive(long idpurchasereceive) {
        this.idpurchasereceive = idpurchasereceive;
    }

    public long getIddeposit() {
        return iddeposit;
    }

    public void setIddeposit(long iddeposit) {
        this.iddeposit = iddeposit;
    }
}
