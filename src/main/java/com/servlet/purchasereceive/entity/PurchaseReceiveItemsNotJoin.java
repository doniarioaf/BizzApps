package com.servlet.purchasereceive.entity;

public class PurchaseReceiveItemsNotJoin {
    private long qty;
    private long idcategoryproduct;
    private long idproduct;
    private String type;

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

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }
}
