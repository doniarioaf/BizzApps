package com.servlet.purchasereceive.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PurchaseReceiveInventoriPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idpurchasereceive;
    private long idinventori;

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
}
