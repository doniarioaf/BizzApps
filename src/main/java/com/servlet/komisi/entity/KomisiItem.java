package com.servlet.komisi.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "komisi_item", schema = "public")
public class KomisiItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private KomisiItemPK komisiItemPK;
    private Long koli;
    private Double komisiperkoli;
    private Double subtotalkomisi;

    public KomisiItemPK getKomisiItemPK() {
        return komisiItemPK;
    }

    public void setKomisiItemPK(KomisiItemPK komisiItemPK) {
        this.komisiItemPK = komisiItemPK;
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
