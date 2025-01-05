package com.servlet.report.entity;

public class ParamReportHutang {
    private Long from;
    private Long to;
    private String idvendors;
    private String status;
    private String vendorType;

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

    public String getIdvendors() {
        return idvendors;
    }

    public void setIdvendors(String idvendors) {
        this.idvendors = idvendors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }
}
