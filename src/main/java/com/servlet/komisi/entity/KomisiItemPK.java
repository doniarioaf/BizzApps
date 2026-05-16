package com.servlet.komisi.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class KomisiItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private long idkomisi;
    private long idpurchasereceive;

    public long getIdkomisi() {
        return idkomisi;
    }

    public void setIdkomisi(long idkomisi) {
        this.idkomisi = idkomisi;
    }

    public long getIdpurchasereceive() {
        return idpurchasereceive;
    }

    public void setIdpurchasereceive(long idpurchasereceive) {
        this.idpurchasereceive = idpurchasereceive;
    }
}
