package com.servlet.draftpurchasereceive.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DraftPurchaseReceiveItemsPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private long iddraftpurchasereceive;
    private long boxsequence;
    private Long idproduct;
    private Long idcategoryproduct;

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }

    public Long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(Long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }
    public long getIddraftpurchasereceive() {
        return iddraftpurchasereceive;
    }

    public void setIddraftpurchasereceive(long iddraftpurchasereceive) {
        this.iddraftpurchasereceive = iddraftpurchasereceive;
    }

    public long getBoxsequence() {
        return boxsequence;
    }

    public void setBoxsequence(long boxsequence) {
        this.boxsequence = boxsequence;
    }
}
