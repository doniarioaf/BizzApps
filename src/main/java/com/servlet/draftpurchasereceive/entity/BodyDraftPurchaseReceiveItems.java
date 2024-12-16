package com.servlet.draftpurchasereceive.entity;

public class BodyDraftPurchaseReceiveItems {
    private Long idproduct;
    private Long idcategoryproduct;
    private Long ekor;
    private Long kilo;
    private Long boxsequence;

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

    public Long getBoxsequence() {
        return boxsequence;
    }

    public void setBoxsequence(Long boxsequence) {
        this.boxsequence = boxsequence;
    }
}
