package com.servlet.draftpurchasereceive.entity;

public class DraftPurchaseReceiveItemNotJoin {
    private long boxsequence;
    private Long idproduct;
    private Long idcategoryproduct;
    private Long ekor;
    private Long kilo;
    private String type;

    @Override
    public String toString() {
        return "DraftPurchaseReceiveItem{" +
                "boxsequence=" + boxsequence +
                ", idproduct=" + idproduct +
                ", idcategoryproduct=" + idcategoryproduct +
                ", ekor=" + ekor +
                ", kilo=" + kilo +
                ", type='" + type + '\'' +
                '}';
    }

    public long getBoxsequence() {
        return boxsequence;
    }

    public void setBoxsequence(long boxsequence) {
        this.boxsequence = boxsequence;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
