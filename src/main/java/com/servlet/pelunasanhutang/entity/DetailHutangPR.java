package com.servlet.pelunasanhutang.entity;

import com.servlet.purchasereceive.entity.PurchaseReceiveDataDetail;

import java.util.List;

public class DetailHutangPR {
    private PurchaseReceiveDataDetail detailPR;
    private List<PelunasanHutangDataNotJoin> listpembayaran;

    public PurchaseReceiveDataDetail getDetailPR() {
        return detailPR;
    }

    public void setDetailPR(PurchaseReceiveDataDetail detailPR) {
        this.detailPR = detailPR;
    }

    public List<PelunasanHutangDataNotJoin> getListpembayaran() {
        return listpembayaran;
    }

    public void setListpembayaran(List<PelunasanHutangDataNotJoin> listpembayaran) {
        this.listpembayaran = listpembayaran;
    }
}
