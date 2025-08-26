package com.servlet.cancelpackinglist.entity;


public class BodyCancelPackingList {
    private Long idpackinglist;
    private String keterangan;
    private Long datecancel;
    private BodyCancelPackingListItem[] items;

    public BodyCancelPackingListItem[] getItems() {
        return items;
    }

    public void setItems(BodyCancelPackingListItem[] items) {
        this.items = items;
    }

    public Long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(Long idpackinglist) {
        this.idpackinglist = idpackinglist;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Long getDatecancel() {
        return datecancel;
    }

    public void setDatecancel(Long datecancel) {
        this.datecancel = datecancel;
    }
}
