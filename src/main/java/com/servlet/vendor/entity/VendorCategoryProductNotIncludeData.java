package com.servlet.vendor.entity;

public class VendorCategoryProductNotIncludeData {
    private Long idvendor;
    private Long idcategoryproduct;
    private String categoryproductName;
    private String categoryproductSize;

    public String getCategoryproductSize() {
        return categoryproductSize;
    }

    public void setCategoryproductSize(String categoryproductSize) {
        this.categoryproductSize = categoryproductSize;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(Long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }

    public String getCategoryproductName() {
        return categoryproductName;
    }

    public void setCategoryproductName(String categoryproductName) {
        this.categoryproductName = categoryproductName;
    }
}
