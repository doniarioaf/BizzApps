package com.servlet.packinglist.entity;

public class ParamCalculateQtyPL {
    private Long idcategoryproduct;
    private Long dateFrom;
    private Long dateThru;
    private String listidcategoryproduct;

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
