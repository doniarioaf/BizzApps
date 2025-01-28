package com.servlet.komisi.entity;

import java.sql.Date;

public class KomisiItemJoinHeader  {
    private Long id;
    private String nodocument;
    private Date date;
    private Long idpurchasereceive;
    private Long koli;
    private Double komisiperkoli;
    private Double subtotalkomisi;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getIdpurchasereceive() {
        return idpurchasereceive;
    }

    public void setIdpurchasereceive(Long idpurchasereceive) {
        this.idpurchasereceive = idpurchasereceive;
    }

    public Long getKoli() {
        return koli;
    }

    public void setKoli(Long koli) {
        this.koli = koli;
    }

    public Double getKomisiperkoli() {
        return komisiperkoli;
    }

    public void setKomisiperkoli(Double komisiperkoli) {
        this.komisiperkoli = komisiperkoli;
    }

    public Double getSubtotalkomisi() {
        return subtotalkomisi;
    }

    public void setSubtotalkomisi(Double subtotalkomisi) {
        this.subtotalkomisi = subtotalkomisi;
    }
}
