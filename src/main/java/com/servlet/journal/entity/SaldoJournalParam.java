package com.servlet.journal.entity;

public class SaldoJournalParam {
    private Long idcompany;
    private Long idbranch;
    private Long idvendor;
    private String listVendor;
    private String accountCode;

    public String getListVendor() {
        return listVendor;
    }

    public void setListVendor(String listVendor) {
        this.listVendor = listVendor;
    }

    public Long getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(Long idcompany) {
        this.idcompany = idcompany;
    }

    public Long getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(Long idbranch) {
        this.idbranch = idbranch;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
}
