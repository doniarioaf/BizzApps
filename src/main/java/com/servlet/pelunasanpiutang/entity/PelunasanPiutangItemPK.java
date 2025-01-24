package com.servlet.pelunasanpiutang.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PelunasanPiutangItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private long idpelunasanpiutang;
    private long idinvoice;

    public long getIdpelunasanpiutang() {
        return idpelunasanpiutang;
    }

    public void setIdpelunasanpiutang(long idpelunasanpiutang) {
        this.idpelunasanpiutang = idpelunasanpiutang;
    }

    public long getIdinvoice() {
        return idinvoice;
    }

    public void setIdinvoice(long idinvoice) {
        this.idinvoice = idinvoice;
    }
}
