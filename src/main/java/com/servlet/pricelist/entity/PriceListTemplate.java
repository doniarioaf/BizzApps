package com.servlet.pricelist.entity;

import com.servlet.categoryproduct.entity.CategoryProductList;
import com.servlet.customer.entity.ListCustomerData;
import com.servlet.product.entity.ListProductData;

import java.util.List;

public class PriceListTemplate {
    private List<CategoryProductList> categoryProductOpt;
    private List<ListProductData> productOpt;
    private List<PriceListItemData> items;
    private List<ListCustomerData> custopt;

    public List<ListCustomerData> getCustopt() {
        return custopt;
    }

    public void setCustopt(List<ListCustomerData> custopt) {
        this.custopt = custopt;
    }

    public List<ListProductData> getProductOpt() {
        return productOpt;
    }

    public void setProductOpt(List<ListProductData> productOpt) {
        this.productOpt = productOpt;
    }

    public List<PriceListItemData> getItems() {
        return items;
    }

    public void setItems(List<PriceListItemData> items) {
        this.items = items;
    }

    public List<CategoryProductList> getCategoryProductOpt() {
        return categoryProductOpt;
    }

    public void setCategoryProductOpt(List<CategoryProductList> categoryProductOpt) {
        this.categoryProductOpt = categoryProductOpt;
    }
}
