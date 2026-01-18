package com.servlet.journal.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "journal_detail", schema = "public")
public class JournalDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private JournalDetailPK journalDetailPK;
    private Double debit;
    private Double credit;
    private Long idvendor;
    private String sourcenumber;
    private Timestamp transaksitime;
    private String description;
    private Long idcompany;
    private Long idbranch;

    public JournalDetailPK getJournalDetailPK() {
        return journalDetailPK;
    }

    public void setJournalDetailPK(JournalDetailPK journalDetailPK) {
        this.journalDetailPK = journalDetailPK;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
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
}
