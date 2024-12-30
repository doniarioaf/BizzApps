package com.servlet.cargo.entity;

import java.sql.Date;

public class CargoData {
    private Long id;
    private Long idvendor;
    private String vendorNama;
    private String vendorAlias;
    private Date date;
    private String invoicenumber;
    private String smunumber;
    private String awbnumber;
    private Long koli;
    private Double grossamount;
    private Double ppnamount;
    private Double ppn23amount;
    private Double netamount;
    private Double outstanding;
    private String file;

    public Double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Double outstanding) {
        this.outstanding = outstanding;
    }

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

    public String getSmunumber() {
        return smunumber;
    }

    public void setSmunumber(String smunumber) {
        this.smunumber = smunumber;
    }

    public String getAwbnumber() {
        return awbnumber;
    }

    public void setAwbnumber(String awbnumber) {
        this.awbnumber = awbnumber;
    }

    public Long getKoli() {
        return koli;
    }

    public void setKoli(Long koli) {
        this.koli = koli;
    }

    public Double getGrossamount() {
        return grossamount;
    }

    public void setGrossamount(Double grossamount) {
        this.grossamount = grossamount;
    }

    public Double getPpnamount() {
        return ppnamount;
    }

    public void setPpnamount(Double ppnamount) {
        this.ppnamount = ppnamount;
    }

    public Double getPpn23amount() {
        return ppn23amount;
    }

    public void setPpn23amount(Double ppn23amount) {
        this.ppn23amount = ppn23amount;
    }

    public Double getNetamount() {
        return netamount;
    }

    public void setNetamount(Double netamount) {
        this.netamount = netamount;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
