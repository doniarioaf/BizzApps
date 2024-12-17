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
