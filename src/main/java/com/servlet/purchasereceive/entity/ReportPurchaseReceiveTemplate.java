package com.servlet.purchasereceive.entity;

import com.servlet.area.entity.AreaList;
import com.servlet.vendor.entity.VendorDataForTemplate;

import java.util.List;

public class ReportPurchaseReceiveTemplate {
    private List<VendorDataForTemplate> vendorOpt;
    private List<AreaList> areaOpt;

    public List<VendorDataForTemplate> getVendorOpt() {
        return vendorOpt;
    }

    public void setVendorOpt(List<VendorDataForTemplate> vendorOpt) {
        this.vendorOpt = vendorOpt;
    }

    public List<AreaList> getAreaOpt() {
        return areaOpt;
    }

    public void setAreaOpt(List<AreaList> areaOpt) {
        this.areaOpt = areaOpt;
    }
}
