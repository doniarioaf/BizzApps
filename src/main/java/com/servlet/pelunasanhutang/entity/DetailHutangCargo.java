package com.servlet.pelunasanhutang.entity;

import com.servlet.cargo.entity.CargoDetail;

import java.util.List;

public class DetailHutangCargo {
    private CargoDetail detailCargo;
    private List<PelunasanHutangDataNotJoin> listpembayaran;

    public CargoDetail getDetailCargo() {
        return detailCargo;
    }

    public void setDetailCargo(CargoDetail detailCargo) {
        this.detailCargo = detailCargo;
    }

    public List<PelunasanHutangDataNotJoin> getListpembayaran() {
        return listpembayaran;
    }

    public void setListpembayaran(List<PelunasanHutangDataNotJoin> listpembayaran) {
        this.listpembayaran = listpembayaran;
    }
}
