package com.servlet.draftpurchasereceive.entity;

public class DraftPurchaseReceiveItemsDetailData {
    private long boxsequence;
    private Long idproduct;
    private Long idcategoryproduct;
    private String namacategoryproduct;
    private String sizecategoryproduct;
    private Long weightfromingramcategoryproduct;
    private Long weighttoingramcategoryproduct;
    private Long ekor;
    private Double kilo;
    private String type;

    public Long getWeightfromingramcategoryproduct() {
        return weightfromingramcategoryproduct;
    }

    public void setWeightfromingramcategoryproduct(Long weightfromingramcategoryproduct) {
        this.weightfromingramcategoryproduct = weightfromingramcategoryproduct;
    }

    public Long getWeighttoingramcategoryproduct() {
        return weighttoingramcategoryproduct;
    }

    public void setWeighttoingramcategoryproduct(Long weighttoingramcategoryproduct) {
        this.weighttoingramcategoryproduct = weighttoingramcategoryproduct;
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

    public String getNamacategoryproduct() {
        return namacategoryproduct;
    }

    public void setNamacategoryproduct(String namacategoryproduct) {
        this.namacategoryproduct = namacategoryproduct;
    }

    public String getSizecategoryproduct() {
        return sizecategoryproduct;
    }

    public void setSizecategoryproduct(String sizecategoryproduct) {
        this.sizecategoryproduct = sizecategoryproduct;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
