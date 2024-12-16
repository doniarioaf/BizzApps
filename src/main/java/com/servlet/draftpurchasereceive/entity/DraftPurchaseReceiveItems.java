package com.servlet.draftpurchasereceive.entity;

import com.servlet.purchasereceive.entity.PurchaseReceiveItemsPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "draft_purchasereceive_items", schema = "public")
public class DraftPurchaseReceiveItems implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DraftPurchaseReceiveItemsPK draftPurchaseReceiveItemsPK;
    private Long idproduct;
    private Long idcategoryproduct;
    private Long ekor;
    private Long kilo;

    public DraftPurchaseReceiveItemsPK getDraftPurchaseReceiveItemsPK() {
        return draftPurchaseReceiveItemsPK;
    }

    public void setDraftPurchaseReceiveItemsPK(DraftPurchaseReceiveItemsPK draftPurchaseReceiveItemsPK) {
        this.draftPurchaseReceiveItemsPK = draftPurchaseReceiveItemsPK;
    }

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

    public Long getEkor() {
        return ekor;
    }

    public void setEkor(Long ekor) {
        this.ekor = ekor;
    }

    public Long getKilo() {
        return kilo;
    }

    public void setKilo(Long kilo) {
        this.kilo = kilo;
    }
}
