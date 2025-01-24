package com.servlet.categoryproduct.entity;

public class CategoryProductList {
    private Long id;
    private String nama;
    private String size;
    private String weight;
    private Integer weightfromingram;
    private Integer weighttoingram;
    private Integer jumlahitemsperkoli;

    public Integer getJumlahitemsperkoli() {
        return jumlahitemsperkoli;
    }

    public void setJumlahitemsperkoli(Integer jumlahitemsperkoli) {
        this.jumlahitemsperkoli = jumlahitemsperkoli;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
