package com.servlet.draftpurchasereceive.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "draft_purchasereceive", schema = "public")
public class DraftPurchaseReceive implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="draft_purchasereceive_id_seq")
    private Long id;
    private Long idcompany;
    private Long idbranch;
    private String nodocument;
    private Date date;
    private Long idvendor;
    private Time arriveltime;
    private Time receivetime;
    private String smu;
    private Long totalekor;
    private Double totalkg;
    private boolean isdelete;
    private Long createdby;
    private Timestamp createddate;
    private Long modifiedby;
    private Timestamp modifieddate;
    private Long deleteby;
    private Timestamp deletedate;
    private Double persentase;
    private String flightno;
    private String notes1;
    private String notes2;
    private Long box;

    @Override
    public String toString() {
        return "DraftPurchaseReceive{" +
                "nodocument='" + nodocument + '\'' +
                ", date=" + date +
                ", idvendor=" + idvendor +
                ", arriveltime=" + arriveltime +
                ", receivetime=" + receivetime +
                ", smu='" + smu + '\'' +
                ", totalekor=" + totalekor +
                ", totalkg=" + totalkg +
                ", persentase=" + persentase +
                ", flightno=" + flightno +
                ", notes1=" + notes1 +
                ", notes2=" + notes2 +
                ", box=" + box +
                '}';
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(Long idcompany) {
        this.idcompany = idcompany;
    }

    public Long getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(Long idbranch) {
        this.idbranch = idbranch;
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

    public Double getTotalkg() {
        return totalkg;
    }

    public void setTotalkg(Double totalkg) {
        this.totalkg = totalkg;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
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

    public Timestamp getDeletedate() {
        return deletedate;
    }

    public void setDeletedate(Timestamp deletedate) {
        this.deletedate = deletedate;
    }
}
