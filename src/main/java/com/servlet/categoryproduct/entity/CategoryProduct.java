package com.servlet.categoryproduct.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "m_category_product", schema = "public")
public class CategoryProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="m_category_product_id_seq")
    private Long id;
    private Long idcompany;
    private Long idbranch;
    private String nama;
    private String size;
    private Integer weightfromingram;
    private Integer weighttoingram;
    /**
     * value jumlahitemsperkoli a/, jumlah udang untuk 1 koli, ini untuk parameter perhitungan report
     * ex, jika 1 koli = 20 ekor udang, maka disini value nya 20
     */

    private Integer jumlahitemsperkoli;
    private boolean isdelete;
    private Long createdby;
    private Timestamp createddate;
    private Long modifiedby;
    private Timestamp modifieddate;
    private Long deleteby;
    private Timestamp deletedate;
    private String forcategory;//CUSTOMER / VENDOR

    /**
     * jika ada penambahan, tambahkan pada toString. wajib!!
     * @return
     */

    @Override
    public String toString() {
        return "CategoryProduct{" +
                "id=" + id +
                ", idcompany=" + idcompany +
                ", idbranch=" + idbranch +
                ", nama='" + nama + '\'' +
                ", size='" + size + '\'' +
                ", weightfromingram='" + weightfromingram + '\'' +
                ", weighttoingram='" + weighttoingram + '\'' +
                ", jumlahitemsperkoli='" + jumlahitemsperkoli + '\'' +
                ", forcategory='" + forcategory + '\'' +
                '}';
    }

    public String getForcategory() {
        return forcategory;
    }

    public void setForcategory(String forcategory) {
        this.forcategory = forcategory;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public Integer getWeightfromingram() {
        return weightfromingram;
    }

    public void setWeightfromingram(Integer weightfromingram) {
        this.weightfromingram = weightfromingram;
    }

    public Integer getWeighttoingram() {
        return weighttoingram;
    }

    public void setWeighttoingram(Integer weighttoingram) {
        this.weighttoingram = weighttoingram;
    }

    public Integer getJumlahitemsperkoli() {
        return jumlahitemsperkoli;
    }

    public void setJumlahitemsperkoli(Integer jumlahitemsperkoli) {
        this.jumlahitemsperkoli = jumlahitemsperkoli;
    }
}
