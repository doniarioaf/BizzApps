package com.servlet.vendor.entity;

public class BodyVendor {
    private String nama;
    private String alias;
    private String type;
    private String bank;
    private String accountnobank;
    private String accountnamebank;
    private Long[] idcategoryproduct;
    private Double pricebox;
    private Double priceongkos;
    private Double packing;
    private Double kurir;
    private Double komisi;
    private Double profit;
    private Double value1;
    private Boolean isparent;
    private Long idvendorparent;
    private Long idvendorbroker;
    private Long idarea;
    private String address1;
    private String address2;
    private String npwp;
    private String phone;
    private String limittransaction;

    public String getLimittransaction() {
        return limittransaction;
    }

    public void setLimittransaction(String limittransaction) {
        this.limittransaction = limittransaction;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getIdarea() {
        return idarea;
    }

    public void setIdarea(Long idarea) {
        this.idarea = idarea;
    }

    public Long getIdvendorbroker() {
        return idvendorbroker;
    }

    public void setIdvendorbroker(Long idvendorbroker) {
        this.idvendorbroker = idvendorbroker;
    }

    public Long getIdvendorparent() {
        return idvendorparent;
    }

    public void setIdvendorparent(Long idvendorparent) {
        this.idvendorparent = idvendorparent;
    }

    public Boolean getIsparent() {
        return isparent;
    }

    public void setIsparent(Boolean isparent) {
        this.isparent = isparent;
    }

    public Double getPacking() {
        return packing;
    }

    public void setPacking(Double packing) {
        this.packing = packing;
    }

    public Double getKurir() {
        return kurir;
    }

    public void setKurir(Double kurir) {
        this.kurir = kurir;
    }

    public Double getKomisi() {
        return komisi;
    }

    public void setKomisi(Double komisi) {
        this.komisi = komisi;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
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

    public Long[] getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(Long[] idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
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
