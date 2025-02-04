package com.servlet.pelunasanhutang.entity;

import com.servlet.cargo.entity.CargoDataNotJoin;
import com.servlet.cargo.entity.CargoPelunasanHutang;
import com.servlet.purchasereceive.entity.PurchaseReceiveDataPelunasanHutang;

import java.util.List;

public class HutangDataList {
    private List<PurchaseReceiveDataPelunasanHutang> listPR;
    private List<CargoPelunasanHutang> listCargo;

    public List<CargoPelunasanHutang> getListCargo() {
        return listCargo;
    }

    public void setListCargo(List<CargoPelunasanHutang> listCargo) {
        this.listCargo = listCargo;
    }

    public List<PurchaseReceiveDataPelunasanHutang> getListPR() {
        return listPR;
    }

    public void setListPR(List<PurchaseReceiveDataPelunasanHutang> listPR) {
        this.listPR = listPR;
    }
}
