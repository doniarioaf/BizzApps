package com.servlet.purchasereceive.entity;

public class ParamGetPrice {
    private Long idproduct;
    private Long idproductcategory;
    private Integer limitdoc;

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }

    public Long getIdproductcategory() {
        return idproductcategory;
    }

    public void setIdproductcategory(Long idproductcategory) {
        this.idproductcategory = idproductcategory;
    }

    public Integer getLimitdoc() {
        return limitdoc;
    }

    public void setLimitdoc(Integer limitdoc) {
        this.limitdoc = limitdoc;
    }
}
