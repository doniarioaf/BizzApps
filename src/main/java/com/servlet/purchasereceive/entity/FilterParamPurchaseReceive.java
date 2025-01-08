package com.servlet.purchasereceive.entity;

public class FilterParamPurchaseReceive {
    private Long from;
    private Long to;
    private Long idvendor;
    private String status;
    private String orderBy;
    private String listIdVendor;

    public String getListIdVendor() {
        return listIdVendor;
    }

    public void setListIdVendor(String listIdVendor) {
        this.listIdVendor = listIdVendor;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
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

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
