package com.servlet.draftpurchasereceive.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class PrintDataDraftPR {
    private Long id;
    private String nodocument;
    private Date date;
    private Long idvendor;
    private String VendorName;
    private String VendorAlias;
    private Time arriveltime;
    private Time receivetime;
    private String smu;
    private String flightno;
    private String notes1;
    private String notes2;
    private Long totalekor;
    private Double totalkg;
    private Double persentase;
    private Long box;
    private Long countPrint;
    private Long countEdit;
    private String namaUser;
    private List<DraftPurchaseReceiveItemsDetailData> items;

    public List<DraftPurchaseReceiveItemsDetailData> getItems() {
        return items;
    }

    public void setItems(List<DraftPurchaseReceiveItemsDetailData> items) {
        this.items = items;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getVendorAlias() {
        return VendorAlias;
    }

    public void setVendorAlias(String vendorAlias) {
        VendorAlias = vendorAlias;
    }

    public Time getArriveltime() {
        return arriveltime;
    }

    public void setArriveltime(Time arriveltime) {
        this.arriveltime = arriveltime;
    }

    public Time getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Time receivetime) {
        this.receivetime = receivetime;
    }

    public String getSmu() {
        return smu;
    }

    public void setSmu(String smu) {
        this.smu = smu;
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

    public Long getTotalekor() {
        return totalekor;
    }

    public void setTotalekor(Long totalekor) {
        this.totalekor = totalekor;
    }

    public Double getTotalkg() {
        return totalkg;
    }

    public void setTotalkg(Double totalkg) {
        this.totalkg = totalkg;
    }

    public Double getPersentase() {
        return persentase;
    }

    public void setPersentase(Double persentase) {
        this.persentase = persentase;
    }

    public Long getBox() {
        return box;
    }

    public void setBox(Long box) {
        this.box = box;
    }

    public Long getCountPrint() {
        return countPrint;
    }

    public void setCountPrint(Long countPrint) {
        this.countPrint = countPrint;
    }

    public Long getCountEdit() {
        return countEdit;
    }

    public void setCountEdit(Long countEdit) {
        this.countEdit = countEdit;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }
}
