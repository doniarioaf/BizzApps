package com.servlet.journal.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "journal", schema = "public")
public class Journal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="journal_id_seq")
    private Long id;
    private Long idcompany;
    private Long idbranch;
    private String journalnumber;
    private Date journaldate;
    private Long idvendor;
    private String sourcenumber;
    private String sourcetype;
    private Timestamp transaksitime;
    private String description;
    private Long createdby;
    private Timestamp createddate;

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

    public String getJournalnumber() {
        return journalnumber;
    }

    public void setJournalnumber(String journalnumber) {
        this.journalnumber = journalnumber;
    }

    public Date getJournaldate() {
        return journaldate;
    }

    public void setJournaldate(Date journaldate) {
        this.journaldate = journaldate;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getSourcenumber() {
        return sourcenumber;
    }

    public void setSourcenumber(String sourcenumber) {
        this.sourcenumber = sourcenumber;
    }

    public String getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
    }

    public Timestamp getTransaksitime() {
        return transaksitime;
    }

    public void setTransaksitime(Timestamp transaksitime) {
        this.transaksitime = transaksitime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
