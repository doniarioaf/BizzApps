package com.servlet.stockadjusment.entity;

import com.servlet.product.entity.ListProductData;

import java.util.List;

public class StockAdjusmentTemplate {
    private List<ListProductData> productOpt;

    public List<ListProductData> getProductOpt() {
        return productOpt;
    }

    public void setProductOpt(List<ListProductData> productOpt) {
        this.productOpt = productOpt;
    }
}
