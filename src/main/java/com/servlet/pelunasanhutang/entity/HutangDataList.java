package com.servlet.pelunasanhutang.entity;

import com.servlet.purchasereceive.entity.PurchaseReceiveDataPelunasanHutang;

import java.util.List;

public class HutangDataList {
    private List<PurchaseReceiveDataPelunasanHutang> listPR;

    public List<PurchaseReceiveDataPelunasanHutang> getListPR() {
        return listPR;
    }

    public void setListPR(List<PurchaseReceiveDataPelunasanHutang> listPR) {
        this.listPR = listPR;
    }
}
