package com.servlet.vendor.entity;

import com.servlet.categoryproduct.entity.CategoryProductList;

import java.util.List;

public class VendorTemplate {
    private List<CategoryProductList> categoryProductOpt;
    private List<VendorDataForTemplate> vendorParentOpt;

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
