package com.servlet.komisi.entity;

public class KomisiItemNotJoin {
    private Long idpurchasereceive;
    private Long koli;
    private Double komisiperkoli;
    private Double subtotalkomisi;

    @Override
    public String toString() {
        return "KomisiItem{" +
                "idpurchasereceive=" + idpurchasereceive +
                ", koli=" + koli +
                ", komisiperkoli=" + komisiperkoli +
                ", subtotalkomisi=" + subtotalkomisi +
                '}';
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
