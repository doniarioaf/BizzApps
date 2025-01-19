package com.servlet.bank.entity;

public class BodyBank {
    private String bankname;
    private String accname;
    private String accno;
    private Long dateopen;
    private Double saldoawal;
    private String catatan1;
    private String catatan2;
    private Long[] bankbranchs;

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Long getDateopen() {
        return dateopen;
    }

    public void setDateopen(Long dateopen) {
        this.dateopen = dateopen;
    }

    public Double getSaldoawal() {
        return saldoawal;
    }

    public void setSaldoawal(Double saldoawal) {
        this.saldoawal = saldoawal;
    }

    public String getCatatan1() {
        return catatan1;
    }

    public void setCatatan1(String catatan1) {
        this.catatan1 = catatan1;
    }

    public String getCatatan2() {
        return catatan2;
    }

    public void setCatatan2(String catatan2) {
        this.catatan2 = catatan2;
    }

    public Long[] getBankbranchs() {
        return bankbranchs;
    }

    public void setBankbranchs(Long[] bankbranchs) {
        this.bankbranchs = bankbranchs;
    }
}
