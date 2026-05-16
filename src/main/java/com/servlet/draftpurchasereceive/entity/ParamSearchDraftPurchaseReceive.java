package com.servlet.draftpurchasereceive.entity;

public class ParamSearchDraftPurchaseReceive {
    private Long from;
    private Long to;
    private Boolean onlyShowNotInLinkedPR;
    private String listIdProduct;
    private String listIdCategoryProduct;
    private String type;

    public String getListIdProduct() {
        return listIdProduct;
    }

    public void setListIdProduct(String listIdProduct) {
        this.listIdProduct = listIdProduct;
    }

    public String getListIdCategoryProduct() {
        return listIdCategoryProduct;
    }

    public void setListIdCategoryProduct(String listIdCategoryProduct) {
        this.listIdCategoryProduct = listIdCategoryProduct;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getOnlyShowNotInLinkedPR() {
        return onlyShowNotInLinkedPR;
    }

    public void setOnlyShowNotInLinkedPR(Boolean onlyShowNotInLinkedPR) {
        this.onlyShowNotInLinkedPR = onlyShowNotInLinkedPR;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }
}
