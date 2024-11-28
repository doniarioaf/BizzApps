package com.servlet.mappingstock.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "mapping_stock", schema = "public")
public class MappingStock {
    @Id
    private long categoryproductid;
    private long categoryproductidmapping;
    private long idcompany;
    private Long idbranch;
    private Long createdby;
    private Timestamp createddate;
    private Long modifiedby;
    private Timestamp modifieddate;

    /**
     * jika ada penambahan, tambahkan pada toString. wajib!!
     * @return
     */

    @Override
    public String toString() {
        return "MappingStock{" +
                "categoryproductid=" + categoryproductid +
                ", categoryproductidmapping=" + categoryproductidmapping +
                ", idcompany=" + idcompany +
                ", idbranch=" + idbranch +
                '}';
    }

    public long getCategoryproductid() {
        return categoryproductid;
    }

    public void setCategoryproductid(long categoryproductid) {
        this.categoryproductid = categoryproductid;
    }

    public long getCategoryproductidmapping() {
        return categoryproductidmapping;
    }

    public void setCategoryproductidmapping(long categoryproductidmapping) {
        this.categoryproductidmapping = categoryproductidmapping;
    }

    public long getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(long idcompany) {
        this.idcompany = idcompany;
    }



    public Long getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(Long idbranch) {
        this.idbranch = idbranch;
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
}
