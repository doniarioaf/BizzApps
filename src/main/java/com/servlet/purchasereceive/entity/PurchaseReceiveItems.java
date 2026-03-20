package com.servlet.purchasereceive.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchasereceive_item", schema = "public")
public class PurchaseReceiveItems implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PurchaseReceiveItemsPK purchaseReceiveItemsPK;
    private Long qty;
    private Long qtybonus;
    private Long qtynota;
    private Double price;
    private Double subtotalprice;
    private Double hargajual_terakhir;
    private Double hargajual;
    private Double totalusd;
    private Double totalrupiah;
    private Long idpackinglist_acuan_hargajual_terakhir; //idpackinglist yang sudah menjadi invoice
    private Double weight_udang; //Dalam Gram

    public Double getWeight_udang() {
        return weight_udang;
    }

    public void setWeight_udang(Double weight_udang) {
        this.weight_udang = weight_udang;
    }

    public Double getHargajual_terakhir() {
        return hargajual_terakhir;
    }

    public void setHargajual_terakhir(Double hargajual_terakhir) {
        this.hargajual_terakhir = hargajual_terakhir;
    }

    public Double getHargajual() {
        return hargajual;
    }

    public void setHargajual(Double hargajual) {
        this.hargajual = hargajual;
    }

    public Double getTotalusd() {
        return totalusd;
    }

    public void setTotalusd(Double totalusd) {
        this.totalusd = totalusd;
    }

    public Double getTotalrupiah() {
        return totalrupiah;
    }

    public void setTotalrupiah(Double totalrupiah) {
        this.totalrupiah = totalrupiah;
    }

    public Long getIdpackinglist_acuan_hargajual_terakhir() {
        return idpackinglist_acuan_hargajual_terakhir;
    }

    public void setIdpackinglist_acuan_hargajual_terakhir(Long idpackinglist_acuan_hargajual_terakhir) {
        this.idpackinglist_acuan_hargajual_terakhir = idpackinglist_acuan_hargajual_terakhir;
    }

    public PurchaseReceiveItemsPK getPurchaseReceiveItemsPK() {
        return purchaseReceiveItemsPK;
    }

    public Long getQtynota() {
        return qtynota;
    }

    public void setQtynota(Long qtynota) {
        this.qtynota = qtynota;
    }

    public void setPurchaseReceiveItemsPK(PurchaseReceiveItemsPK purchaseReceiveItemsPK) {
        this.purchaseReceiveItemsPK = purchaseReceiveItemsPK;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getQtybonus() {
        return qtybonus;
    }

    public void setQtybonus(Long qtybonus) {
        this.qtybonus = qtybonus;
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
