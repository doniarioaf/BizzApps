package com.servlet.pelunasanhutang.entity;

import com.servlet.cargo.entity.CargoDataNotJoin;
import com.servlet.purchasereceive.entity.PurchaseReceiveDataPelunasanHutang;

import java.util.List;

public class HutangDataList {
    private List<PurchaseReceiveDataPelunasanHutang> listPR;
    private List<CargoDataNotJoin> listCargo;

    public List<CargoDataNotJoin> getListCargo() {
        return listCargo;
    }

    public void setListCargo(List<CargoDataNotJoin> listCargo) {
        this.listCargo = listCargo;
    }

    public List<PurchaseReceiveDataPelunasanHutang> getListPR() {
        return listPR;
    }

    public void setListPR(List<PurchaseReceiveDataPelunasanHutang> listPR) {
        this.listPR = listPR;
    }
}
