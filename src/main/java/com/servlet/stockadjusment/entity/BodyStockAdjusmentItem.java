package com.servlet.stockadjusment.entity;

public class BodyStockAdjusmentItem {
    private long idproduct;
    private long idcategoryproduct;
    private String type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
