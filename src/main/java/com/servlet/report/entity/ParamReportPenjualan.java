package com.servlet.report.entity;

public class ParamReportPenjualan {
    private Long from;
    private Long to;
    private String listidcustomer;
    private String listGroup;

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
}
