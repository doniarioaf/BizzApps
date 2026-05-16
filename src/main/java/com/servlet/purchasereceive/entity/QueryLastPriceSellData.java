package com.servlet.purchasereceive.entity;

import java.sql.Date;

public class QueryLastPriceSellData {
    private Long idproduct;
    private Long idcategoryproduct;
    private Long idpackinglist;
    private Double hargajual;
    private String nodocument;
    private Date transactiondate;

    public Long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(Long idpackinglist) {
        this.idpackinglist = idpackinglist;
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

    public Double getHargajual() {
        return hargajual;
    }

    public void setHargajual(Double hargajual) {
        this.hargajual = hargajual;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }
}
