package com.servlet.cancelpackinglist.entity;

public class CancelPackingListItemData {
    private Long idproduct;
    private String namaProduct;
    private Long idcategoryproduct;
    private String categoryProductNama;
    private String categoryProductSize;
    private Long qty;

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    public Long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(Long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }

    public String getCategoryProductNama() {
        return categoryProductNama;
    }

    public void setCategoryProductNama(String categoryProductNama) {
        this.categoryProductNama = categoryProductNama;
    }

    public String getCategoryProductSize() {
        return categoryProductSize;
    }

    public void setCategoryProductSize(String categoryProductSize) {
        this.categoryProductSize = categoryProductSize;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
}
