package com.servlet.draftpurchasereceive.entity;

public class BodyDraftPurchaseReceive {
    private Long date;
    private Long idvendor;
    private String arriveltime;
    private String receivetime;
    private String smu;
    private Long totalekor;
    private Long totalkg;
    private Double persentase;
    private BodyDraftPurchaseReceiveItems[] items;

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

    public Double getPersentase() {
        return persentase;
    }

    public void setPersentase(Double persentase) {
        this.persentase = persentase;
    }

    public BodyDraftPurchaseReceiveItems[] getItems() {
        return items;
    }

    public void setItems(BodyDraftPurchaseReceiveItems[] items) {
        this.items = items;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getArriveltime() {
        return arriveltime;
    }

    public void setArriveltime(String arriveltime) {
        this.arriveltime = arriveltime;
    }

    public String getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(String receivetime) {
        this.receivetime = receivetime;
    }

    public String getSmu() {
        return smu;
    }

    public void setSmu(String smu) {
        this.smu = smu;
    }

    public Long getTotalekor() {
        return totalekor;
    }

    public void setTotalekor(Long totalekor) {
        this.totalekor = totalekor;
    }

    public Long getTotalkg() {
        return totalkg;
    }

    public void setTotalkg(Long totalkg) {
        this.totalkg = totalkg;
    }
}
