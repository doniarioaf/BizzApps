package com.servlet.cancelpackinglist.entity;

public class BodyCancelPackingListItem {
    private Long idproduct;
    private Long idcategoryproduct;
    private Long qty;
    private String box;
    private String type;

    @Override
    public String toString() {
        return "CancelPackingListItem{" +
                "idproduct=" + idproduct +
                ", idcategoryproduct=" + idcategoryproduct +
                ", qty=" + qty +
                ", box='" + box + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
