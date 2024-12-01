package com.servlet.purchasereceive.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "purchasereceive", schema = "public")
public class PurchaseReceive implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="purchasereceive_id_seq")
    private Long id;
    private Long idcompany;
    private Long idbranch;
    private String nodocument;
    private Long idvendor;
    private Date transactiondate;
    private String koli;
    private String notes;
    private String bank;
    private String accountnobank;
    private String accountnamebank;
    private boolean isdelete;
    private Long createdby;
    private Timestamp createddate;
    private Long modifiedby;
    private Timestamp modifieddate;
    private Long deleteby;
    private Timestamp deletedate;

    @Override
    public String toString() {
        return "PurchaseReceive{" +
                "id=" + id +
                ", nodocument='" + nodocument + '\'' +
                ", idvendor=" + idvendor +
                ", transactiondate=" + transactiondate +
                ", koli='" + koli + '\'' +
                ", notes='" + notes + '\'' +
                ", bank='" + bank + '\'' +
                ", accountnobank='" + accountnobank + '\'' +
                ", accountnamebank='" + accountnamebank + '\'' +
                '}';
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

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getKoli() {
        return koli;
    }

    public void setKoli(String koli) {
        this.koli = koli;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountnobank() {
        return accountnobank;
    }

    public void setAccountnobank(String accountnobank) {
        this.accountnobank = accountnobank;
    }

    public String getAccountnamebank() {
        return accountnamebank;
    }

    public void setAccountnamebank(String accountnamebank) {
        this.accountnamebank = accountnamebank;
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
