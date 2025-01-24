package com.servlet.report.entity;

public class ParamReportPelunasanPiutang {
    private Long from;
    private Long to;
    private String listidcustomer;
    private String listGroup;
    private String status;

    public String getListidcustomer() {
        return listidcustomer;
    }

    public void setListidcustomer(String listidcustomer) {
        this.listidcustomer = listidcustomer;
    }

    public String getListGroup() {
        return listGroup;
    }

    public void setListGroup(String listGroup) {
        this.listGroup = listGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
