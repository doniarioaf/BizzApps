package com.servlet.draftpurchasereceive.entity;

public class BodyDraftPurchaseReceiveItems {
    private Long idproduct;
    private Long idcategoryproduct;
    private Long ekor;
    private Double kilo;
    private Long boxsequence;
    private String type;

    @Override
    public String toString() {
        return "DraftPurchaseReceiveItems{" +
                "idproduct=" + idproduct +
                ", idcategoryproduct=" + idcategoryproduct +
                ", ekor=" + ekor +
                ", kilo=" + kilo +
                ", boxsequence=" + boxsequence +
                ", type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Double getKilo() {
        return kilo;
    }

    public void setKilo(Double kilo) {
        this.kilo = kilo;
    }

    public Long getBoxsequence() {
        return boxsequence;
    }

    public void setBoxsequence(Long boxsequence) {
        this.boxsequence = boxsequence;
    }
}
