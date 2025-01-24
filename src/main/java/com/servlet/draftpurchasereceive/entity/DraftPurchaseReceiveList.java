package com.servlet.draftpurchasereceive.entity;

import java.sql.Date;

public class DraftPurchaseReceiveList {
    private Long id;
    private String nodocument;
    private String smu;
    private Long idvendor;
    private String vendorName;
    private String vendorAlias;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public String getSmu() {
        return smu;
    }

    public void setSmu(String smu) {
        this.smu = smu;
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

    public String getVendorAlias() {
        return vendorAlias;
    }

    public void setVendorAlias(String vendorAlias) {
        this.vendorAlias = vendorAlias;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
