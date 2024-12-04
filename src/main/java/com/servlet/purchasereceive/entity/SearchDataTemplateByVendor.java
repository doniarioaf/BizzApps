package com.servlet.purchasereceive.entity;

import com.servlet.categoryproduct.entity.CategoryProductList;

import java.util.List;

public class SearchDataTemplateByVendor {
    private List<CategoryProductList> categoryproductOpt;
    private Double sisaDeposit;

    public Double getSisaDeposit() {
        return sisaDeposit;
    }

    public void setSisaDeposit(Double sisaDeposit) {
        this.sisaDeposit = sisaDeposit;
    }

    public List<CategoryProductList> getCategoryproductOpt() {
        return categoryproductOpt;
    }

    public void setCategoryproductOpt(List<CategoryProductList> categoryproductOpt) {
        this.categoryproductOpt = categoryproductOpt;
    }
}
