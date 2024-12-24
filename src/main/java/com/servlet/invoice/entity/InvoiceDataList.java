package com.servlet.invoice.entity;

import java.sql.Date;

public class InvoiceDataList {
    private Long id;
    private String nodocument;
    private Date date;
    private Double kurs;
    private Long idpackinglist;
    private String nodocumentPackingList;
    private String custNama;
    private String custAlias;

    public String getCustNama() {
        return custNama;
    }

    public void setCustNama(String custNama) {
        this.custNama = custNama;
    }

    public String getCustAlias() {
        return custAlias;
    }

    public void setCustAlias(String custAlias) {
        this.custAlias = custAlias;
    }

    public String getNodocumentPackingList() {
        return nodocumentPackingList;
    }

    public void setNodocumentPackingList(String nodocumentPackingList) {
        this.nodocumentPackingList = nodocumentPackingList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }

    public Long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(Long idpackinglist) {
        this.idpackinglist = idpackinglist;
    }
}
