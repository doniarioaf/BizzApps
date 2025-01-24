package com.servlet.report.entity;

public class ParamReportPembelian {
    private Long from;
    private Long to;
    private Long idvendor;
    private Long idarea;
    private String listidvendor;

    public void setFrom(Long from) {
        this.from = from;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getListidvendor() {
        return listidvendor;
    }

    public void setListidvendor(String listidvendor) {
        this.listidvendor = listidvendor;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Long getIdarea() {
        return idarea;
    }

    public void setIdarea(Long idarea) {
        this.idarea = idarea;
    }
}
