package com.servlet.vendor.entity;

import com.servlet.categoryproduct.entity.CategoryProductList;

import java.util.List;

public class VendorTemplate {
    private List<CategoryProductList> categoryProductOpt;
    private List<VendorDataForTemplate> vendorParentOpt;
    private List<VendorDataForTemplate> vendorBrokerOpt;

    public List<VendorDataForTemplate> getVendorBrokerOpt() {
        return vendorBrokerOpt;
    }

    public void setVendorBrokerOpt(List<VendorDataForTemplate> vendorBrokerOpt) {
        this.vendorBrokerOpt = vendorBrokerOpt;
    }

    public List<VendorDataForTemplate> getVendorParentOpt() {
        return vendorParentOpt;
    }

    public void setVendorParentOpt(List<VendorDataForTemplate> vendorParentOpt) {
        this.vendorParentOpt = vendorParentOpt;
    }

    public List<CategoryProductList> getCategoryProductOpt() {
        return categoryProductOpt;
    }

    public void setCategoryProductOpt(List<CategoryProductList> categoryProductOpt) {
        this.categoryProductOpt = categoryProductOpt;
    }
}
