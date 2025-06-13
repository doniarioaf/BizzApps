package com.servlet.purchasereceive.entity;

import com.servlet.categoryproduct.entity.CategoryProductList;
import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveDropDownList;

import java.util.List;

public class SearchDataTemplateByVendor {
    private List<CategoryProductList> categoryproductOpt;
    private Double sisaDeposit;
    private Double sisaPinjaman;
    private List<DraftPurchaseReceiveDropDownList> draftPurchaseReceiveOpt;

    public Double getSisaPinjaman() {
        return sisaPinjaman;
    }

    public void setSisaPinjaman(Double sisaPinjaman) {
        this.sisaPinjaman = sisaPinjaman;
    }

    public List<DraftPurchaseReceiveDropDownList> getDraftPurchaseReceiveOpt() {
        return draftPurchaseReceiveOpt;
    }

    public void setDraftPurchaseReceiveOpt(List<DraftPurchaseReceiveDropDownList> draftPurchaseReceiveOpt) {
        this.draftPurchaseReceiveOpt = draftPurchaseReceiveOpt;
    }

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
