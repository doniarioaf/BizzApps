package com.servlet.invoice.entity;


public class BodyInvoice {
    private Long date;
    private Double kurs;
    private Long idpackinglist;
    private String phone;
    private Double totalamount;

    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }

    public Long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(Long idpackinglist) {
        this.idpackinglist = idpackinglist;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
