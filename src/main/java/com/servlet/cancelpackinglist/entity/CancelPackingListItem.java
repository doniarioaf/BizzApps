package com.servlet.cancelpackinglist.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cancel_packinglistitems", schema = "public")
public class CancelPackingListItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private CancelPackingListItemPK cancelPackingListItemPK;
    private Long qty;
    private Double brutoweight;
    private Double allowance;
    private Double nettoweight;
    private Double price;
    private Double totalprice;
    private String type;
    @Override
    public String toString() {
        return "CancelPackingListItem{" +
                "brutoweight=" + brutoweight +
                ", allowance=" + allowance +
                ", nettoweight=" + nettoweight +
                ", price=" + price +
                ", totalprice=" + totalprice +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CancelPackingListItemPK getCancelPackingListItemPK() {
        return cancelPackingListItemPK;
    }

    public void setCancelPackingListItemPK(CancelPackingListItemPK cancelPackingListItemPK) {
        this.cancelPackingListItemPK = cancelPackingListItemPK;
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
