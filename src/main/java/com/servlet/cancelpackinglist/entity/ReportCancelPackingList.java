package com.servlet.cancelpackinglist.entity;

import java.sql.Date;

public class ReportCancelPackingList {
    private Long idcancel;
    private Long idpackinglist;
    private String noDocumentCPL;
    private String noDocumentPL;
    private String keterangan;
    private Date tanggalcancel;
    private String customerAlias;
    private String customerName;
    private String vendorAlias;
    private String vendorName;
    private Long idProduct;
    private String productName;
    private Long idcategoryProduct;
    private String categoryProductName;
    private String categoryProductSize;
    private Long qtyPL;
    private Long qtyMati;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdcategoryProduct() {
        return idcategoryProduct;
    }

    public void setIdcategoryProduct(Long idcategoryProduct) {
        this.idcategoryProduct = idcategoryProduct;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Long getIdcancel() {
        return idcancel;
    }

    public void setIdcancel(Long idcancel) {
        this.idcancel = idcancel;
    }

    public Long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(Long idpackinglist) {
        this.idpackinglist = idpackinglist;
    }

    public String getNoDocumentCPL() {
        return noDocumentCPL;
    }

    public void setNoDocumentCPL(String noDocumentCPL) {
        this.noDocumentCPL = noDocumentCPL;
    }

    public String getNoDocumentPL() {
        return noDocumentPL;
    }

    public void setNoDocumentPL(String noDocumentPL) {
        this.noDocumentPL = noDocumentPL;
    }

    public Date getTanggalcancel() {
        return tanggalcancel;
    }

    public void setTanggalcancel(Date tanggalcancel) {
        this.tanggalcancel = tanggalcancel;
    }

    public String getCustomerAlias() {
        return customerAlias;
    }

    public void setCustomerAlias(String customerAlias) {
        this.customerAlias = customerAlias;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVendorAlias() {
        return vendorAlias;
    }

    public void setVendorAlias(String vendorAlias) {
        this.vendorAlias = vendorAlias;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryProductName() {
        return categoryProductName;
    }

    public void setCategoryProductName(String categoryProductName) {
        this.categoryProductName = categoryProductName;
    }

    public String getCategoryProductSize() {
        return categoryProductSize;
    }

    public void setCategoryProductSize(String categoryProductSize) {
        this.categoryProductSize = categoryProductSize;
    }

    public Long getQtyPL() {
        return qtyPL;
    }

    public void setQtyPL(Long qtyPL) {
        this.qtyPL = qtyPL;
    }

    public Long getQtyMati() {
        return qtyMati;
    }

    public void setQtyMati(Long qtyMati) {
        this.qtyMati = qtyMati;
    }
}
