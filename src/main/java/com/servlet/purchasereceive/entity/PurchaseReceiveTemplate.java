package com.servlet.purchasereceive.entity;

import com.servlet.area.entity.AreaList;
import com.servlet.charge.entity.ChargeList;
import com.servlet.inventori.entity.ListDropdownData;
import com.servlet.pricelist.entity.PriceItemsDataForTemplate;
import com.servlet.product.entity.ListProductData;
import com.servlet.vendor.entity.VendorDataForTemplate;

import java.util.List;

public class PurchaseReceiveTemplate {
    private List<VendorDataForTemplate> vendorOpt;
    private PriceItemsDataForTemplate priceItems;
    private List<ListProductData> productOpt;
    private List<ChargeList> chargeOpt;
    private List<ListDropdownData> inventoriOpt;
    private List<AreaList> areaOpt;

    public List<AreaList> getAreaOpt() {
        return areaOpt;
    }

    public void setAreaOpt(List<AreaList> areaOpt) {
        this.areaOpt = areaOpt;
    }

    public List<ListDropdownData> getInventoriOpt() {
        return inventoriOpt;
    }

    public void setInventoriOpt(List<ListDropdownData> inventoriOpt) {
        this.inventoriOpt = inventoriOpt;
    }

    public List<ChargeList> getChargeOpt() {
        return chargeOpt;
    }

    public void setChargeOpt(List<ChargeList> chargeOpt) {
        this.chargeOpt = chargeOpt;
    }

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
