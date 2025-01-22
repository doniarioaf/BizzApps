package com.servlet.draftpurchasereceive.entity;

import java.sql.Date;

public class DraftPurchaseReceiveDropDownList {
    private Long id;
    private String nodocument;
    private String smu;
    private Date date;
    private String flightno;
    private String notes1;
    private String notes2;
    private Long box;

    public Long getBox() {
        return box;
    }

    public void setBox(Long box) {
        this.box = box;
    }

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public String getNotes1() {
        return notes1;
    }

    public void setNotes1(String notes1) {
        this.notes1 = notes1;
    }

    public String getNotes2() {
        return notes2;
    }

    public void setNotes2(String notes2) {
        this.notes2 = notes2;
    }

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

    public String getSmu() {
        return smu;
    }

    public void setSmu(String smu) {
        this.smu = smu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
