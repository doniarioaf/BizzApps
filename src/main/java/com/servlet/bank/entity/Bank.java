package com.servlet.bank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "m_bank", schema = "public")
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="m_bank_id_seq")
    private Long id;
    private Long idcompany;
    private Long idbranch;
    private String bankname;
    private String accname;
    private String accno;
    private Date dateopen;
    private Double saldoawal;
    private String catatan1;
    private String catatan2;
    private boolean isdelete;
    private Long createdby;
    private Timestamp createddate;
    private Long modifiedby;
    private Timestamp modifieddate;
    private Long deleteby;
    private Timestamp deletedate;

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", bankname='" + bankname + '\'' +
                ", accname='" + accname + '\'' +
                ", accno='" + accno + '\'' +
                ", dateopen=" + dateopen +
                ", saldoawal=" + saldoawal +
                ", catatan1='" + catatan1 + '\'' +
                ", catatan2='" + catatan2 + '\'' +
                '}';
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
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

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public Date getDateopen() {
        return dateopen;
    }

    public void setDateopen(Date dateopen) {
        this.dateopen = dateopen;
    }

    public Double getSaldoawal() {
        return saldoawal;
    }

    public void setSaldoawal(Double saldoawal) {
        this.saldoawal = saldoawal;
    }

    public String getCatatan1() {
        return catatan1;
    }

    public void setCatatan1(String catatan1) {
        this.catatan1 = catatan1;
    }

    public String getCatatan2() {
        return catatan2;
    }

    public void setCatatan2(String catatan2) {
        this.catatan2 = catatan2;
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
