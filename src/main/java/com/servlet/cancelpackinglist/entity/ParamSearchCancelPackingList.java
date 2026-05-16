package com.servlet.cancelpackinglist.entity;

public class ParamSearchCancelPackingList {
    private Long from;
    private Long to;
    private String listIdProduct;
    private String listIdCategoryProduct;

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
