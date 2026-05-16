package com.servlet.cancelpackinglist.entity;

public class ParamCalculateQtyCPL {
    private Long idcategoryproduct;
    private Long idproduct;
    private Long dateFrom;
    private Long dateThru;
    private String type;
    private String listidproduct;
    private String listidcategoryproduct;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(Long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
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
