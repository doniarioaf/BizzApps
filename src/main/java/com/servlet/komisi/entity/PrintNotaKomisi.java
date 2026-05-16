package com.servlet.komisi.entity;

import com.servlet.purchasereceive.entity.PurchaseReceiveDataKomisi;

import java.sql.Date;
import java.util.List;

public class PrintNotaKomisi {
    private Long id;
    private String nodocument;
    private Date date;
    private String note;
    private Long countPrint;
    private Long countEdit;
    private String namaUser;
    private List<PurchaseReceiveDataKomisi> items;

    public Long getCountPrint() {
        return countPrint;
    }

    public void setCountPrint(Long countPrint) {
        this.countPrint = countPrint;
    }

    public Long getCountEdit() {
        return countEdit;
    }

    public void setCountEdit(Long countEdit) {
        this.countEdit = countEdit;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public List<PurchaseReceiveDataKomisi> getItems() {
        return items;
    }

    public void setItems(List<PurchaseReceiveDataKomisi> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
