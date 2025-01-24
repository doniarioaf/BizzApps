package com.servlet.cargo.entity;

import java.sql.Date;

public class CargoDataList {
    private Long id;
    private Long idvendor;
    private String vendorNama;
    private String vendorAlias;
    private Date date;
    private String invoicenumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getVendorNama() {
        return vendorNama;
    }

    public void setVendorNama(String vendorNama) {
        this.vendorNama = vendorNama;
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

    public String getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(String invoicenumber) {
        this.invoicenumber = invoicenumber;
    }
}
