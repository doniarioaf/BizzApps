package com.servlet.komisi.entity;

import com.servlet.vendor.entity.VendorDataForTemplate;

import java.util.List;

public class KomisiTemplate {
    private Long idbox;
    private List<VendorDataForTemplate> vendorBrokerOpt;

    public Long getIdbox() {
        return idbox;
    }

    public void setIdbox(Long idbox) {
        this.idbox = idbox;
    }

    public List<VendorDataForTemplate> getVendorBrokerOpt() {
        return vendorBrokerOpt;
    }

    public void setVendorBrokerOpt(List<VendorDataForTemplate> vendorBrokerOpt) {
        this.vendorBrokerOpt = vendorBrokerOpt;
    }
}
