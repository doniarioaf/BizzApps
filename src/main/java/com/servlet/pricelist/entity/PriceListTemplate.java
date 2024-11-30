package com.servlet.pricelist.entity;

import com.servlet.categoryproduct.entity.CategoryProductList;

import java.util.List;

public class PriceListTemplate {
    private List<CategoryProductList> categoryProductOpt;

    public List<CategoryProductList> getCategoryProductOpt() {
        return categoryProductOpt;
    }

    public void setCategoryProductOpt(List<CategoryProductList> categoryProductOpt) {
        this.categoryProductOpt = categoryProductOpt;
    }
}
