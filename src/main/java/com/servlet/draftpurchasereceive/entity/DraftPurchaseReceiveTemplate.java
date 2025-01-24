package com.servlet.draftpurchasereceive.entity;

import com.servlet.product.entity.ListProductData;
import com.servlet.vendor.entity.VendorDataForTemplate;

import java.util.List;

public class DraftPurchaseReceiveTemplate {
    private List<VendorDataForTemplate> vendorOpt;
    private List<ListProductData> productOpt;

    public List<VendorDataForTemplate> getVendorOpt() {
        return vendorOpt;
    }

    public void setVendorOpt(List<VendorDataForTemplate> vendorOpt) {
        this.vendorOpt = vendorOpt;
    }

    public List<ListProductData> getProductOpt() {
        return productOpt;
    }

    public void setProductOpt(List<ListProductData> productOpt) {
        this.productOpt = productOpt;
    }
}
