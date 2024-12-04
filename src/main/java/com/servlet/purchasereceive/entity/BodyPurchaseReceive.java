package com.servlet.purchasereceive.entity;

import java.sql.Date;

public class BodyPurchaseReceive {
    private Long idvendor;
    private Long transactiondate;
    private String koli;
    private String notes;
    private String bank;
    private String accountnobank;
    private String accountnamebank;
    private Double totalprice;
    private BodyPurchaseReceiveItems[] items;
    private BodyPurchaseReceiveCharge[] charges;

    private boolean isdefaultvaluesetor;
    private Double setor;
    private Double tambahdeposit;

    public Double getTambahdeposit() {
        return tambahdeposit;
    }

    public void setTambahdeposit(Double tambahdeposit) {
        this.tambahdeposit = tambahdeposit;
    }

    public boolean isIsdefaultvaluesetor() {
        return isdefaultvaluesetor;
    }

    public void setIsdefaultvaluesetor(boolean isdefaultvaluesetor) {
        this.isdefaultvaluesetor = isdefaultvaluesetor;
    }

    public Double getSetor() {
        return setor;
    }

    public void setSetor(Double setor) {
        this.setor = setor;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public BodyPurchaseReceiveItems[] getItems() {
        return items;
    }

    public void setItems(BodyPurchaseReceiveItems[] items) {
        this.items = items;
    }

    public BodyPurchaseReceiveCharge[] getCharges() {
        return charges;
    }

    public void setCharges(BodyPurchaseReceiveCharge[] charges) {
        this.charges = charges;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Long getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Long transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getKoli() {
        return koli;
    }

    public void setKoli(String koli) {
        this.koli = koli;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountnobank() {
        return accountnobank;
    }

    public void setAccountnobank(String accountnobank) {
        this.accountnobank = accountnobank;
    }

    public String getAccountnamebank() {
        return accountnamebank;
    }

    public void setAccountnamebank(String accountnamebank) {
        this.accountnamebank = accountnamebank;
    }
}
