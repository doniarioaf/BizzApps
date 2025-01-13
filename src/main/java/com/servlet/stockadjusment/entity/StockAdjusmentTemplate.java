package com.servlet.stockadjusment.entity;

import com.servlet.categoryproduct.entity.CategoryProductList;
import com.servlet.product.entity.ListProductData;

import java.util.List;

public class StockAdjusmentTemplate {
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
}
