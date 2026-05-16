package com.servlet.purchasereceive.entity;

import java.sql.Date;

public class PurchaseReceiveDataKomisi {
    private Long id;
    private Long idvendorbroker;
    private String vendornamabroker;
    private String vendoraliasbroker;
    private String vendorbankbroker;
    private String vendoraccnobroker;
    private String vendoraccnamebroker;

    private Long idvendor;
    private String vendornama;
    private String vendoralias;

    private String nodocument;
    private Date date;
    private Long koli;
    private Double komisi;
    private Double subTotalkomisi;

    public String getVendorbankbroker() {
        return vendorbankbroker;
    }

    public void setVendorbankbroker(String vendorbankbroker) {
        this.vendorbankbroker = vendorbankbroker;
    }

    public String getVendoraccnobroker() {
        return vendoraccnobroker;
    }

    public void setVendoraccnobroker(String vendoraccnobroker) {
        this.vendoraccnobroker = vendoraccnobroker;
    }

    public String getVendoraccnamebroker() {
        return vendoraccnamebroker;
    }

    public void setVendoraccnamebroker(String vendoraccnamebroker) {
        this.vendoraccnamebroker = vendoraccnamebroker;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getVendornama() {
        return vendornama;
    }

    public void setVendornama(String vendornama) {
        this.vendornama = vendornama;
    }

    public String getVendoralias() {
        return vendoralias;
    }

    public void setVendoralias(String vendoralias) {
        this.vendoralias = vendoralias;
    }

    public Double getSubTotalkomisi() {
        return subTotalkomisi;
    }

    public void setSubTotalkomisi(Double subTotalkomisi) {
        this.subTotalkomisi = subTotalkomisi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdvendorbroker() {
        return idvendorbroker;
    }

    public void setIdvendorbroker(Long idvendorbroker) {
        this.idvendorbroker = idvendorbroker;
    }

    public String getVendornamabroker() {
        return vendornamabroker;
    }

    public void setVendornamabroker(String vendornamabroker) {
        this.vendornamabroker = vendornamabroker;
    }

    public String getVendoraliasbroker() {
        return vendoraliasbroker;
    }

    public void setVendoraliasbroker(String vendoraliasbroker) {
        this.vendoraliasbroker = vendoraliasbroker;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getKoli() {
        return koli;
    }

    public void setKoli(Long koli) {
        this.koli = koli;
    }

    public Double getKomisi() {
        return komisi;
    }

    public void setKomisi(Double komisi) {
        this.komisi = komisi;
    }
}
