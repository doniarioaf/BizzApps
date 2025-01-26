package com.servlet.komisi.entity;

public class ParamKomisi {
    private Long from;
    private Long to;
    private String listIdVendor;
    private Long idbox;
    private String listidpurchaisereceive;
    private String menu;
    private Long idkomisi;

    public Long getIdkomisi() {
        return idkomisi;
    }

    public void setIdkomisi(Long idkomisi) {
        this.idkomisi = idkomisi;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getListidpurchaisereceive() {
        return listidpurchaisereceive;
    }

    public void setListidpurchaisereceive(String listidpurchaisereceive) {
        this.listidpurchaisereceive = listidpurchaisereceive;
    }

    public Long getIdbox() {
        return idbox;
    }

    public void setIdbox(Long idbox) {
        this.idbox = idbox;
    }

    public String getListIdVendor() {
        return listIdVendor;
    }

    public void setListIdVendor(String listIdVendor) {
        this.listIdVendor = listIdVendor;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }
}
