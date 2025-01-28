package com.servlet.pelunasanpiutang.entity;

import java.sql.Date;

public class PelunasanPiutangItemJoinHeader {
    private Long id;
    private String nodocument;
    private Date date;
    private Long idinvoice;

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

    public Long getIdinvoice() {
        return idinvoice;
    }

    public void setIdinvoice(Long idinvoice) {
        this.idinvoice = idinvoice;
    }
}
