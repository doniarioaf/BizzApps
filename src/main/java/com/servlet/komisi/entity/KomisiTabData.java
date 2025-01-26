package com.servlet.komisi.entity;

import com.servlet.purchasereceive.entity.PurchaseReceiveDataKomisi;

import java.util.List;

public class KomisiTabData {
    private List<KomisiList> listkomisi;
    private List<PurchaseReceiveDataKomisi> listpr;

    public List<KomisiList> getListkomisi() {
        return listkomisi;
    }

    public void setListkomisi(List<KomisiList> listkomisi) {
        this.listkomisi = listkomisi;
    }

    public List<PurchaseReceiveDataKomisi> getListpr() {
        return listpr;
    }

    public void setListpr(List<PurchaseReceiveDataKomisi> listpr) {
        this.listpr = listpr;
    }
}
