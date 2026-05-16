package com.servlet.draftpurchasereceive.entity;

public class ParamCalculateQtyDPR {
    private Long idcategoryproduct;
    private Long idproduct;
    private Long dateFrom;
    private Long dateThru;
    private Long idvendor;
    private String listidcategoryproduct;
    private String listidproduct;

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }

    public String getListidproduct() {
        return listidproduct;
    }

    public void setListidproduct(String listidproduct) {
        this.listidproduct = listidproduct;
    }

    public String getListidcategoryproduct() {
        return listidcategoryproduct;
    }

    public void setListidcategoryproduct(String listidcategoryproduct) {
        this.listidcategoryproduct = listidcategoryproduct;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(Long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }

    public Long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Long getDateThru() {
        return dateThru;
    }

    public void setDateThru(Long dateThru) {
        this.dateThru = dateThru;
    }
}
