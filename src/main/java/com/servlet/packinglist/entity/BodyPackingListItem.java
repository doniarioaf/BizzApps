package com.servlet.packinglist.entity;

public class BodyPackingListItem {
    private Long idproduct;
    private Long idcategoryproduct;
    private Long qty;
    private Double brutoweight;
    private Double allowance;
    private Double nettoweight;
    private Double price;
    private Double totalprice;
    private String box;
    private Boolean check;

    @Override
    public String toString() {
        return "PackingListItem{" +
                "idproduct=" + idproduct +
                ", idcategoryproduct=" + idcategoryproduct +
                ", qty=" + qty +
                ", brutoweight=" + brutoweight +
                ", allowance=" + allowance +
                ", nettoweight=" + nettoweight +
                ", price=" + price +
                ", totalprice=" + totalprice +
                ", check=" + check +
                ", box='" + box + '\'' +
                '}';
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }

    public Long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(Long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
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
