package com.servlet.stockadjusment.entity;

public class ParamCalculateQtySA {
    private Long idcategoryproduct;
    private Long dateFrom;
    private Long dateThru;
    private String listidproduct;
    private String listidcategoryproduct;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
