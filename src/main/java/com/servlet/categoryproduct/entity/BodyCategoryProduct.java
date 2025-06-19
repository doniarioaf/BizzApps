package com.servlet.categoryproduct.entity;

public class BodyCategoryProduct {
    private String nama;
    private String size;
    private int weightfromingram;
    private int weighttoingram;
    private int jumlahitemsperkoli;
    private String forcategory;

    public String getForcategory() {
        return forcategory;
    }

    public void setForcategory(String forcategory) {
        this.forcategory = forcategory;
    }

    public int getWeightfromingram() {
        return weightfromingram;
    }

    public void setWeightfromingram(int weightfromingram) {
        this.weightfromingram = weightfromingram;
    }

    public int getWeighttoingram() {
        return weighttoingram;
    }

    public void setWeighttoingram(int weighttoingram) {
        this.weighttoingram = weighttoingram;
    }

    public int getJumlahitemsperkoli() {
        return jumlahitemsperkoli;
    }

    public void setJumlahitemsperkoli(int jumlahitemsperkoli) {
        this.jumlahitemsperkoli = jumlahitemsperkoli;
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
}
