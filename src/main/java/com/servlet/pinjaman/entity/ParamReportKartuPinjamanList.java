package com.servlet.pinjaman.entity;

public class ParamReportKartuPinjamanList {
    private Long from;
    private Long to;
    private Long idvendor;
    private String listIdVendor;
    private String isactive;
    private String transaksiTime;

    public String getTransaksiTime() {
        return transaksiTime;
    }

    public void setTransaksiTime(String transaksiTime) {
        this.transaksiTime = transaksiTime;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
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

    public String getListIdVendor() {
        return listIdVendor;
    }

    public void setListIdVendor(String listIdVendor) {
        this.listIdVendor = listIdVendor;
    }
}
