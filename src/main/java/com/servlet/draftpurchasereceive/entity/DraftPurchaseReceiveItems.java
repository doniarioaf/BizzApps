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

    private Long ekor;
    private Double kilo;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DraftPurchaseReceiveItemsPK getDraftPurchaseReceiveItemsPK() {
        return draftPurchaseReceiveItemsPK;
    }

    public void setDraftPurchaseReceiveItemsPK(DraftPurchaseReceiveItemsPK draftPurchaseReceiveItemsPK) {
        this.draftPurchaseReceiveItemsPK = draftPurchaseReceiveItemsPK;
    }

    public Long getEkor() {
        return ekor;
    }

    public void setEkor(Long ekor) {
        this.ekor = ekor;
    }

    public Double getKilo() {
        return kilo;
    }

    public void setKilo(Double kilo) {
        this.kilo = kilo;
    }
}
