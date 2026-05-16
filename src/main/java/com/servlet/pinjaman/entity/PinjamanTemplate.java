package com.servlet.pinjaman.entity;

import com.servlet.vendor.entity.VendorDataForTemplate;

import java.util.List;

public class PinjamanTemplate {
    private List<VendorDataForTemplate> vendorOpt;

    public List<VendorDataForTemplate> getVendorOpt() {
        return vendorOpt;
    }

    public void setVendorOpt(List<VendorDataForTemplate> vendorOpt) {
        this.vendorOpt = vendorOpt;
    }
}
