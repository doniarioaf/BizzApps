package com.servlet.cancelpackinglist.entity;

public class BodyCancelPackingList {
    private Long idpackinglist;
    private String nodocumentPL;
    private String nodocument;
    private Long datecancel;

    public String getNodocumentPL() {
        return nodocumentPL;
    }

    public void setNodocumentPL(String nodocumentPL) {
        this.nodocumentPL = nodocumentPL;
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

    public Long getDatecancel() {
        return datecancel;
    }

    public void setDatecancel(Long datecancel) {
        this.datecancel = datecancel;
    }
}
