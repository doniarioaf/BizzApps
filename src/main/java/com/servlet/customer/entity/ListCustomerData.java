package com.servlet.customer.entity;

public class ListCustomerData {
    private Long id;
    private String nama;
    private String alias;
    private String grup;
    private String grupcode;

    public String getGrupcode() {
        return grupcode;
    }

    public void setGrupcode(String grupcode) {
        this.grupcode = grupcode;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
