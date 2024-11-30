package com.servlet.vendor.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VendorCategoryProductNotIncludePK implements Serializable {
    private static final long serialVersionUID = 1L;

    private long idvendor;
    private long idcategoryproduct;

    public long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(long idvendor) {
        this.idvendor = idvendor;
    }

    public long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }
}
