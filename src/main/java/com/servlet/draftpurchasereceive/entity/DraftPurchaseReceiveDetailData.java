package com.servlet.draftpurchasereceive.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class DraftPurchaseReceiveDetailData {
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
    private Long totalkg;
    private Double persentase;
    private Long createdby;
    private String createdbyName;
    private Timestamp createddate;
    private Long modifiedby;
    private String modifiedbyName;

    private Timestamp modifieddate;
    private Long deleteby;
    private List<DraftPurchaseReceiveItemsDetailData> items;
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

    public String getVendorAlias() {
        return VendorAlias;
    }

    public void setVendorAlias(String vendorAlias) {
        VendorAlias = vendorAlias;
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

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public Long getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(Long modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Long getDeleteby() {
        return deleteby;
    }

    public void setDeleteby(Long deleteby) {
        this.deleteby = deleteby;
    }

    public List<DraftPurchaseReceiveItemsDetailData> getItems() {
        return items;
    }

    public void setItems(List<DraftPurchaseReceiveItemsDetailData> items) {
        this.items = items;
    }

    public String getCreatedbyName() {
        return createdbyName;
    }

    public void setCreatedbyName(String createdbyName) {
        this.createdbyName = createdbyName;
    }

    public String getModifiedbyName() {
        return modifiedbyName;
    }

    public void setModifiedbyName(String modifiedbyName) {
        this.modifiedbyName = modifiedbyName;
    }
}
