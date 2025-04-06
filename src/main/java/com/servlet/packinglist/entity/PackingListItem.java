package com.servlet.packinglist.entity;

import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveItemsPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "packinglist_item", schema = "public")
public class PackingListItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private PackingListItemPK packingListItemPK;
    private Long qty;
    private Double brutoweight;
    private Double allowance;
    private Double nettoweight;
    private Double price;
    private Double totalprice;

    @Override
    public String toString() {
        return "PackingListItem{" +
                "brutoweight=" + brutoweight +
                ", allowance=" + allowance +
                ", nettoweight=" + nettoweight +
                ", price=" + price +
                ", totalprice=" + totalprice +
                '}';
    }

    public PackingListItemPK getPackingListItemPK() {
        return packingListItemPK;
    }

    public void setPackingListItemPK(PackingListItemPK packingListItemPK) {
        this.packingListItemPK = packingListItemPK;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Double getBrutoweight() {
        return brutoweight;
    }

    public void setBrutoweight(Double brutoweight) {
        this.brutoweight = brutoweight;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public Double getNettoweight() {
        return nettoweight;
    }

    public void setNettoweight(Double nettoweight) {
        this.nettoweight = nettoweight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }
}
