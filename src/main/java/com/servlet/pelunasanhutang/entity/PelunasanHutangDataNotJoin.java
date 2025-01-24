package com.servlet.pelunasanhutang.entity;

import java.sql.Date;

public class PelunasanHutangDataNotJoin {
    private Long id;
    private String nodocument;
    private Long idpurchasereceive;
    private Long idcargo;
    private Date date;
    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public Long getIdpurchasereceive() {
        return idpurchasereceive;
    }

    public void setIdpurchasereceive(Long idpurchasereceive) {
        this.idpurchasereceive = idpurchasereceive;
    }

    public Long getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Long idcargo) {
        this.idcargo = idcargo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
