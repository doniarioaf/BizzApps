package com.servlet.invoice.entity;

import com.servlet.packinglist.entity.PackingListDataDetail;

import java.sql.Date;
import java.sql.Timestamp;

public class InvoiceDataDetail {
    private Long id;
    private String nodocument;
    private Date date;
    private Double kurs;
    private Long idpackinglist;
    private PackingListDataDetail packinglist;
    private String phone;
    private String createdbyName;
    private Timestamp createddate;
    private String modifiedbyName;
    private Timestamp modifieddate;

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

    public Long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(Long idpackinglist) {
        this.idpackinglist = idpackinglist;
    }

    public PackingListDataDetail getPackinglist() {
        return packinglist;
    }

    public void setPackinglist(PackingListDataDetail packinglist) {
        this.packinglist = packinglist;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
