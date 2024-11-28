package com.servlet.pricelist.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PriceItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private long pricelistid;
    private long categoryproductid;

    @Override
    public String toString() {
        return "PriceItemPK{" +
                "pricelistid=" + pricelistid +
                ", categoryproductid=" + categoryproductid +
                '}';
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
