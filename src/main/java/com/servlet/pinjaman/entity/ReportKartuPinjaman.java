package com.servlet.pinjaman.entity;

import java.sql.Date;

public class ReportKartuPinjaman implements Comparable<ReportKartuPinjaman>{
    private Long id;
    private Long idvendor;
    private String vendorName;
    private String vendorAlias;
    private Long idvendorParent;
    private String documentNumber;
    private Double amount;
    private Date date;
    private String type;//PINJAMAN or PURCHASERECEIVE


    public Long getIdvendor() {
        return idvendor;
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

    public Long getIdvendorParent() {
        return idvendorParent;
    }

    public void setIdvendorParent(Long idvendorParent) {
        this.idvendorParent = idvendorParent;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(ReportKartuPinjaman o) {
        return this.date.compareTo(o.getDate());
    }

}
