package com.servlet.purchasereceive.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PurchaseReceiveItemsPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idpurchasereceive;
    private long idproduct;
    private long idcategoryproduct;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getIdpurchasereceive() {
        return idpurchasereceive;
    }

    public void setIdpurchasereceive(long idpurchasereceive) {
        this.idpurchasereceive = idpurchasereceive;
    }

    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long idproduct) {
        this.idproduct = idproduct;
    }

    public long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }
}
