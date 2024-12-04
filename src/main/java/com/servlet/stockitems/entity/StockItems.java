package com.servlet.stockitems.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "stock_items", schema = "public")
public class StockItems implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private StockItemsPK stockItemsPK;
    private long qty;

    public StockItemsPK getStockItemsPK() {
        return stockItemsPK;
    }

    public void setStockItemsPK(StockItemsPK stockItemsPK) {
        this.stockItemsPK = stockItemsPK;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }
}
