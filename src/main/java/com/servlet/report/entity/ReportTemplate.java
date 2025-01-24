package com.servlet.report.entity;

import com.servlet.categoryproduct.entity.CategoryProductList;
import com.servlet.customer.entity.CustomerGrup;
import com.servlet.customer.entity.ListCustomerData;
import com.servlet.product.entity.ListProductData;
import com.servlet.vendor.entity.VendorDataForTemplate;

import java.util.List;

public class ReportTemplate {
    private List<VendorDataForTemplate> vendorOpt;
    private List<ListCustomerData> customerOpt;
    private List<CustomerGrup> customerGrupOpt;
    private List<ListProductData> productOpt;
    private List<CategoryProductList> categoryProductOpt;

    public List<CategoryProductList> getCategoryProductOpt() {
        return categoryProductOpt;
    }

    public void setCategoryProductOpt(List<CategoryProductList> categoryProductOpt) {
        this.categoryProductOpt = categoryProductOpt;
    }

    public List<ListProductData> getProductOpt() {
        return productOpt;
    }

    public void setProductOpt(List<ListProductData> productOpt) {
        this.productOpt = productOpt;
    }

    public List<ListCustomerData> getCustomerOpt() {
        return customerOpt;
    }

    public void setCustomerOpt(List<ListCustomerData> customerOpt) {
        this.customerOpt = customerOpt;
    }

    public List<CustomerGrup> getCustomerGrupOpt() {
        return customerGrupOpt;
    }

    public void setCustomerGrupOpt(List<CustomerGrup> customerGrupOpt) {
        this.customerGrupOpt = customerGrupOpt;
    }

    public List<VendorDataForTemplate> getVendorOpt() {
        return vendorOpt;
    }

    public void setVendorOpt(List<VendorDataForTemplate> vendorOpt) {
        this.vendorOpt = vendorOpt;
    }
}
