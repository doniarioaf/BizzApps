package com.servlet.purchasereceive.entity;

public class BodyPurchaseReceive {
    private Long idvendor;
    private Long transactiondate;
    private String koli;
    private String notes;
    private String bank;
    private String accountnobank;
    private String accountnamebank;

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
