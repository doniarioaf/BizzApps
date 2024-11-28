package com.servlet.pricelist.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pricelistitem", schema = "public")
public class PriceListItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PriceItemPK priceItemPK;
    private Double amount;

    @Override
    public String toString() {
        return "PriceListItem{" +
                "priceItemPK=" + priceItemPK.toString() +
                ", amount=" + amount +
                '}';
    }

    public PriceItemPK getPriceItemPK() {
        return priceItemPK;
    }

    public void setPriceItemPK(PriceItemPK priceItemPK) {
        this.priceItemPK = priceItemPK;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
