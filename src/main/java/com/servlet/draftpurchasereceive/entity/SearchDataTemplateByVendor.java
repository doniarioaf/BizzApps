package com.servlet.draftpurchasereceive.entity;

import com.servlet.categoryproduct.entity.CategoryProductList;

import java.util.List;

public class SearchDataTemplateByVendor {
    private List<CategoryProductList> categoryproductOpt;

    public List<CategoryProductList> getCategoryproductOpt() {
        return categoryproductOpt;
    }

    public void setCategoryproductOpt(List<CategoryProductList> categoryproductOpt) {
        this.categoryproductOpt = categoryproductOpt;
    }
}
