package com.servlet.pricelist.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PriceItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private long pricelistid;
    private long categoryproductid;
    private long idproduct;

    @Override
    public String toString() {
        return "PriceItemPK{" +
                "pricelistid=" + pricelistid +
                ", categoryproductid=" + categoryproductid +
                ", idproduct=" + idproduct +
                '}';
    }

    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long idproduct) {
        this.idproduct = idproduct;
    }

    public long getPricelistid() {
        return pricelistid;
    }

    public void setPricelistid(long pricelistid) {
        this.pricelistid = pricelistid;
    }

    public long getCategoryproductid() {
        return categoryproductid;
    }

    public void setCategoryproductid(long categoryproductid) {
        this.categoryproductid = categoryproductid;
    }
}
