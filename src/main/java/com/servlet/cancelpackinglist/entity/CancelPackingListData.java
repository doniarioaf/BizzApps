package com.servlet.cancelpackinglist.entity;

import com.servlet.packinglist.entity.PackingListDataItemDetail;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class CancelPackingListData {
    private Long id;
    private Long idpackinglist;
    private String nodocument;
    private String keterangan;
    private Date datecancel;
    private String nodocumentPL;
    private Long idcustomer;
    private String customerName;
    private String customerAlias;
    private Long idvendor;
    private String vendorName;
    private String vendorAlias;

    private List<CancelPackingListItemData> items;
    private List<PackingListDataItemDetail> itemsPL;
    private String createdbyName;
    private Timestamp createddate;
    private String modifiedbyName;
    private Timestamp modifieddate;

    public List<PackingListDataItemDetail> getItemsPL() {
        return itemsPL;
    }

    public void setItemsPL(List<PackingListDataItemDetail> itemsPL) {
        this.itemsPL = itemsPL;
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



    public List<CancelPackingListItemData> getItems() {
        return items;
    }

    public void setItems(List<CancelPackingListItemData> items) {
        this.items = items;
    }

    public String getCustomerAlias() {
        return customerAlias;
    }

    public void setCustomerAlias(String customerAlias) {
        this.customerAlias = customerAlias;
    }

    public String getVendorAlias() {
        return vendorAlias;
    }

    public void setVendorAlias(String vendorAlias) {
        this.vendorAlias = vendorAlias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(Long idpackinglist) {
        this.idpackinglist = idpackinglist;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getDatecancel() {
        return datecancel;
    }

    public void setDatecancel(Date datecancel) {
        this.datecancel = datecancel;
    }

    public String getNodocumentPL() {
        return nodocumentPL;
    }

    public void setNodocumentPL(String nodocumentPL) {
        this.nodocumentPL = nodocumentPL;
    }

    public Long getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Long idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
