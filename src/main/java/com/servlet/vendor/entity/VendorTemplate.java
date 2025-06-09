package com.servlet.vendor.entity;

import com.servlet.area.entity.AreaList;
import com.servlet.categoryproduct.entity.CategoryProductList;

import java.util.List;

public class VendorTemplate {
    private List<CategoryProductList> categoryProductOpt;
    private List<VendorDataForTemplate> vendorParentOpt;
    private List<VendorDataForTemplate> vendorBrokerOpt;
    private List<AreaList> areaOpt;

    public List<AreaList> getAreaOpt() {
        return areaOpt;
    }

    public void setAreaOpt(List<AreaList> areaOpt) {
        this.areaOpt = areaOpt;
    }

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
