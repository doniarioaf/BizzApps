package com.servlet.pinjaman.entity;

public class VendorSisaPinjaman {
    private Long idvendor;
    private String vendorName;
    private String vendorAlias;
    private Double sisaPinjaman;

    public String getVendorAlias() {
        return vendorAlias;
    }

    public void setVendorAlias(String vendorAlias) {
        this.vendorAlias = vendorAlias;
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

    public Double getSisaPinjaman() {
        return sisaPinjaman;
    }

    public void setSisaPinjaman(Double sisaPinjaman) {
        this.sisaPinjaman = sisaPinjaman;
    }
}
