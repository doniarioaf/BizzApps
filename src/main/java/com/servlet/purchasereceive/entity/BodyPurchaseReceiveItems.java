package com.servlet.purchasereceive.entity;

public class BodyPurchaseReceiveItems {
    private long idproduct;
    private long idcategoryproduct;
    private long qty;
    private long qtybonus;
    private Long qtynota;
    private Double price;
    private Double subtotalprice;
    private String type;
    private Double hargajual_terakhir;
    private Double hargajual;
    private Double totalusd;
    private Double totalrupiah;
    private Long idpackinglist_acuan_hargajual_terakhir; //idpackinglist yang sudah menjadi invoice
    private Double weight_udang;

    @Override
    public String toString() {
        return "PurchaseReceiveItems{" +
                "idproduct=" + idproduct +
                ", idcategoryproduct=" + idcategoryproduct +
                ", qty=" + qty +
                ", qtybonus=" + qtybonus +
                ", qtynota=" + qtynota +
                ", price=" + price +
                ", subtotalprice=" + subtotalprice +
                ", hargajual_terakhir=" + hargajual_terakhir +
                ", hargajual=" + hargajual +
                ", totalusd=" + totalusd +
                ", totalrupiah=" + totalrupiah +
                ", weight_udang=" + weight_udang +
                ", idpackinglist_acuan_hargajual_terakhir=" + idpackinglist_acuan_hargajual_terakhir +
                ", type='" + type + '\'' +
                '}';
    }

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

    public Long getQtynota() {
        return qtynota;
    }

    public void setQtynota(Long qtynota) {
        this.qtynota = qtynota;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long idproduct) {
        this.idproduct = idproduct;
    }

    public long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public long getQtybonus() {
        return qtybonus;
    }

    public void setQtybonus(long qtybonus) {
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
