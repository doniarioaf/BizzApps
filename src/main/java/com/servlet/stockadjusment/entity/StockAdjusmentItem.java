package com.servlet.stockadjusment.entity;

import com.servlet.purchasereceive.entity.PurchaseReceiveItemsPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "stock_adjusment_item", schema = "public")
public class StockAdjusmentItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private StockAdjusmentItemPK stockAdjusmentItemPK;
    private Long qty;
    private Double price;
    private Double subtotalprice;
    private String stocktime;

    public String getStocktime() {
        return stocktime;
    }

    public void setStocktime(String stocktime) {
        this.stocktime = stocktime;
    }

    public StockAdjusmentItemPK getStockAdjusmentItemPK() {
        return stockAdjusmentItemPK;
    }

    public void setStockAdjusmentItemPK(StockAdjusmentItemPK stockAdjusmentItemPK) {
        this.stockAdjusmentItemPK = stockAdjusmentItemPK;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubtotalprice() {
        return subtotalprice;
    }

    public void setSubtotalprice(Double subtotalprice) {
        this.subtotalprice = subtotalprice;
    }
}
