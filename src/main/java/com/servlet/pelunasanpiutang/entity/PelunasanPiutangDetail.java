package com.servlet.pelunasanpiutang.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class PelunasanPiutangDetail {
    private Long id;
    private String nodocument;
    private Date date;
    private Double kurs;
    private String createdbyName;
    private Timestamp createddate;
    private String modifiedbyName;
    private Timestamp modifieddate;
    private List<PelunasanPiutangItemDetail> items;

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

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }

    public String getCreatedbyName() {
        return createdbyName;
    }

    public void setCreatedbyName(String createdbyName) {
        this.createdbyName = createdbyName;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public String getModifiedbyName() {
        return modifiedbyName;
    }

    public void setModifiedbyName(String modifiedbyName) {
        this.modifiedbyName = modifiedbyName;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public List<PelunasanPiutangItemDetail> getItems() {
        return items;
    }

    public void setItems(List<PelunasanPiutangItemDetail> items) {
        this.items = items;
    }
}
