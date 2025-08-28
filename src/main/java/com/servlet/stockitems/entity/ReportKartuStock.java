package com.servlet.stockitems.entity;

import com.servlet.pelunasanhutang.entity.ReportPelunasanHutangDocumentHutang;

import java.sql.Date;

public class ReportKartuStock implements Comparable<ReportKartuStock>{
    private Long idproduct;
//    private String namaProduct;
    private Long idcategoryproduct;
//    private String namaCategoryProduct;
//    private String sizeCategoryProduct;
    private Date date;
    private Long qty;
    //khusus cancel Packinglist
    //untuk Qty packing list yang dicancel
    private Long qtypackinglistcancel;
    private String nodocument;
    private String vendorName;
    private String vendorAlias;
    private String customerName;
    private String customerAlias;
    private String keterangan;
    private String type;

    private Long idpackinglist; //khusus yang mempunyai idpackinglist saja

    @Override
    public String toString() {
        return "ReportKartuStock{" +
                "idproduct=" + idproduct +
                ", idcategoryproduct=" + idcategoryproduct +
                ", date=" + date +
                ", qty=" + qty +
                ", nodocument='" + nodocument + '\'' +
                ", keterangan='" + keterangan + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Long getQtypackinglistcancel() {
        return qtypackinglistcancel;
    }

    public void setQtypackinglistcancel(Long qtypackinglistcancel) {
        this.qtypackinglistcancel = qtypackinglistcancel;
    }

    public Long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(Long idpackinglist) {
        this.idpackinglist = idpackinglist;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAlias() {
        return vendorAlias;
    }

    public void setVendorAlias(String vendorAlias) {
        this.vendorAlias = vendorAlias;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAlias() {
        return customerAlias;
    }

    public void setCustomerAlias(String customerAlias) {
        this.customerAlias = customerAlias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }

//    public String getNamaProduct() {
//        return namaProduct;
//    }
//
//    public void setNamaProduct(String namaProduct) {
//        this.namaProduct = namaProduct;
//    }

    public Long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(Long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }

//    public String getNamaCategoryProduct() {
//        return namaCategoryProduct;
//    }
//
//    public void setNamaCategoryProduct(String namaCategoryProduct) {
//        this.namaCategoryProduct = namaCategoryProduct;
//    }
//
//    public String getSizeCategoryProduct() {
//        return sizeCategoryProduct;
//    }
//
//    public void setSizeCategoryProduct(String sizeCategoryProduct) {
//        this.sizeCategoryProduct = sizeCategoryProduct;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public int compareTo(ReportKartuStock o) {
        return this.date.compareTo(o.getDate());
    }
}
