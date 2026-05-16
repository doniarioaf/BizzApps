package com.servlet.cancelpackinglist.entity;

import java.sql.Date;

public class QueryNotJoinCancelPackingListData {
    private Long id;
    private Long idpackinglist;
    private String nodocument;
    private String keterangan;
    private Date datecancel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(Long idpackinglist) {
        this.idpackinglist = idpackinglist;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getDatecancel() {
        return datecancel;
    }

    public void setDatecancel(Date datecancel) {
        this.datecancel = datecancel;
    }
}
