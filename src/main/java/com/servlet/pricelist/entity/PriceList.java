package com.servlet.pricelist.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "pricelist", schema = "public")
public class PriceList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="pricelist_id_seq")
    private Long id;
    private Long idcompany;
    private Long idbranch;
    private Date pricedate;
    private Date pricedatethru;
    private String notes;
    private boolean isdelete;
    private Long createdby;
    private Timestamp createddate;
    private Long modifiedby;
    private Timestamp modifieddate;
    private Long deleteby;
    private Timestamp deletedate;

    @Override
    public String toString() {
        return "PriceList{" +
                "id=" + id +
                ", pricedate=" + pricedate +
                ", pricedatethru=" + pricedatethru +
                ", notes=" + notes +
                '}';
    }

    public Date getPricedatethru() {
        return pricedatethru;
    }

    public void setPricedatethru(Date pricedatethru) {
        this.pricedatethru = pricedatethru;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Date getPricedate() {
        return pricedate;
    }

    public void setPricedate(Date pricedate) {
        this.pricedate = pricedate;
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
