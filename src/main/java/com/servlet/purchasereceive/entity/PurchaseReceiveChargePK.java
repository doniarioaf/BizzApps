package com.servlet.purchasereceive.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PurchaseReceiveChargePK implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idpurchasereceive;
    private long idcharge;

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
}
