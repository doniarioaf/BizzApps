package com.servlet.cargo.entity;

public class ParamCargoSearch {
    private Long from;
    private Long to;
    private Long idvendor;
    private String category;
    private String status;
    private String listIdVendor;

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getListIdVendor() {
        return listIdVendor;
    }

    public void setListIdVendor(String listIdVendor) {
        this.listIdVendor = listIdVendor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
