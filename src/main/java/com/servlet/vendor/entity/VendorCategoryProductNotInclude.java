package com.servlet.vendor.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "vendor_categoryproduct_not_include", schema = "public")
public class VendorCategoryProductNotInclude implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private VendorCategoryProductNotIncludePK vendorCategoryProductNotIncludePK;

    public VendorCategoryProductNotIncludePK getVendorCategoryProductNotIncludePK() {
        return vendorCategoryProductNotIncludePK;
    }

    public void setVendorCategoryProductNotIncludePK(VendorCategoryProductNotIncludePK vendorCategoryProductNotIncludePK) {
        this.vendorCategoryProductNotIncludePK = vendorCategoryProductNotIncludePK;
    }
}
