package com.servlet.invoice.entity;

public class ParamReportInvoice {
    private Long from;
    private Long to;
    private String listCustomerID;
    private String showALL; //LUNAS or BELUMLUNAS or ALL

    public String getShowALL() {
        return showALL;
    }

    public void setShowALL(String showALL) {
        this.showALL = showALL;
    }

    public String getListCustomerID() {
        return listCustomerID;
    }

    public void setListCustomerID(String listCustomerID) {
        this.listCustomerID = listCustomerID;
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
