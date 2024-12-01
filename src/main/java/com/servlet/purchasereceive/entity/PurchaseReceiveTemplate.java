package com.servlet.purchasereceive.entity;

import com.servlet.pricelist.entity.PriceItemsDataForTemplate;
import com.servlet.product.entity.ListProductData;
import com.servlet.vendor.entity.VendorDataForTemplate;

import java.util.List;

public class PurchaseReceiveTemplate {
    private List<VendorDataForTemplate> vendorOpt;
    private PriceItemsDataForTemplate priceItems;
    private List<ListProductData> productOpt;

    public List<ListProductData> getProductOpt() {
        return productOpt;
    }

    public void setProductOpt(List<ListProductData> productOpt) {
        this.productOpt = productOpt;
    }

    public PriceItemsDataForTemplate getPriceItems() {
        return priceItems;
    }

    public void setPriceItems(PriceItemsDataForTemplate priceItems) {
        this.priceItems = priceItems;
    }

    public List<VendorDataForTemplate> getVendorOpt() {
        return vendorOpt;
    }

    public void setVendorOpt(List<VendorDataForTemplate> vendorOpt) {
        this.vendorOpt = vendorOpt;
    }
}
