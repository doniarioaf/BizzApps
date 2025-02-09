package com.servlet.deposit.entity;

public class ParamCalculateDeposit {
    private Long idvendor;
    private Long date;
    private String listNotSUMIdDeposit;

    public String getListNotSUMIdDeposit() {
        return listNotSUMIdDeposit;
    }

    public void setListNotSUMIdDeposit(String listNotSUMIdDeposit) {
        this.listNotSUMIdDeposit = listNotSUMIdDeposit;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
