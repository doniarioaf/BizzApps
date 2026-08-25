package com.servlet.vendor.entity;

import java.sql.Timestamp;
import java.util.List;

public class VendorData {
    private Long id;
    private String nama;
    private String alias;
    private String type;
    private String bank;
    private String accountnobank;
    private String accountnamebank;
    private String createdbyName;
    private Timestamp createddate;
    private String modifiedbyName;
    private Timestamp modifieddate;
    private String deletebyName;
    private Timestamp deletedate;
    private Double pricebox;
    private Double priceongkos;
    private Double packing;
    private Double kurir;
    private Double komisi;
    private Double profit;
    private Double value1;
    private Boolean isparent;
    private Long idvendorparent;
    private String vendorParentName;
    private String vendorParentAlias;
    private Long idvendorbroker;
    private String vendorBrokerName;
    private String vendorBrokerAlias;
    private Long idarea;
    private String areaName;
    private String address1;
    private String address2;
    private String npwp;
    private String phone;
    private Boolean limittransaction;
    private Boolean canloan;
    private Boolean candeposit;
    private List<ListVendorData> listSubParent;

    public List<ListVendorData> getListSubParent() {
        return listSubParent;
    }

    public Boolean getCanloan() {
        return canloan;
    }

    public void setCanloan(Boolean canloan) {
        this.canloan = canloan;
    }

    public Boolean getCandeposit() {
        return candeposit;
    }

    public void setCandeposit(Boolean candeposit) {
        this.candeposit = candeposit;
    }

    public void setListSubParent(List<ListVendorData> listSubParent) {
        this.listSubParent = listSubParent;
    }

    public Boolean getLimittransaction() {
        return limittransaction;
    }

    public void setLimittransaction(Boolean limittransaction) {
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getIdvendorbroker() {
        return idvendorbroker;
    }

    public void setIdvendorbroker(Long idvendorbroker) {
        this.idvendorbroker = idvendorbroker;
    }

    public String getVendorBrokerName() {
        return vendorBrokerName;
    }

    public void setVendorBrokerName(String vendorBrokerName) {
        this.vendorBrokerName = vendorBrokerName;
    }

    public String getVendorBrokerAlias() {
        return vendorBrokerAlias;
    }

    public void setVendorBrokerAlias(String vendorBrokerAlias) {
        this.vendorBrokerAlias = vendorBrokerAlias;
    }

    public String getVendorParentName() {
        return vendorParentName;
    }

    public void setVendorParentName(String vendorParentName) {
        this.vendorParentName = vendorParentName;
    }

    public String getVendorParentAlias() {
        return vendorParentAlias;
    }

    public void setVendorParentAlias(String vendorParentAlias) {
        this.vendorParentAlias = vendorParentAlias;
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

    private List<VendorCategoryProductNotIncludeData> items;

    public List<VendorCategoryProductNotIncludeData> getItems() {
        return items;
    }

    public void setItems(List<VendorCategoryProductNotIncludeData> items) {
        this.items = items;
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

    public String getCreatedbyName() {
        return createdbyName;
    }

    public void setCreatedbyName(String createdbyName) {
        this.createdbyName = createdbyName;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public String getModifiedbyName() {
        return modifiedbyName;
    }

    public void setModifiedbyName(String modifiedbyName) {
        this.modifiedbyName = modifiedbyName;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public String getDeletebyName() {
        return deletebyName;
    }

    public void setDeletebyName(String deletebyName) {
        this.deletebyName = deletebyName;
    }

    public Timestamp getDeletedate() {
        return deletedate;
    }

    public void setDeletedate(Timestamp deletedate) {
        this.deletedate = deletedate;
    }
}
