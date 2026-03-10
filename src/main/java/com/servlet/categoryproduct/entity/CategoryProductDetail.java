package com.servlet.categoryproduct.entity;

import java.sql.Timestamp;

public class CategoryProductDetail {
    private Long id;
    private String nama;
    private String size;
    private Integer weightfromingram;
    private Integer weighttoingram;
    private Integer jumlahitemsperkoli;
    private String createdbyName;
    private Timestamp createddate;
    private String modifiedbyName;
    private Timestamp modifieddate;
    private String forcategory;
    private Integer sequence;

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getForcategory() {
        return forcategory;
    }

    public void setForcategory(String forcategory) {
        this.forcategory = forcategory;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCreatedbyName() {
        return createdbyName;
    }

    public void setCreatedbyName(String createdbyName) {
        this.createdbyName = createdbyName;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public String getModifiedbyName() {
        return modifiedbyName;
    }

    public void setModifiedbyName(String modifiedbyName) {
        this.modifiedbyName = modifiedbyName;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }
}
