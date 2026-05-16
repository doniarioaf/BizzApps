package com.servlet.vendor.entity;

public class VendorDataForTemplate {
    private Long id;
    private String nama;
    private String alias;
    private String type;
    private String bank;
    private String accountnobank;
    private String accountnamebank;
    private Double pricebox;
    private Double priceongkos;
    private Long idarea;

    public Long getIdarea() {
        return idarea;
    }

    public void setIdarea(Long idarea) {
        this.idarea = idarea;
    }

    public Double getPricebox() {
        return pricebox;
    }

    public void setPricebox(Double pricebox) {
        this.pricebox = pricebox;
    }

    public Double getPriceongkos() {
        return priceongkos;
    }

    public void setPriceongkos(Double priceongkos) {
        this.priceongkos = priceongkos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
