package com.servlet.pelunasanhutang.entity;

public class FilterParamPelunasanHutang {
    private String category; // ALL / SUPPLIER / CARGO / UPI
    private String status; //ALL/LUNAS/BELUMLUNAS
    private Long from;
    private Long to;
    private Long idcargo;
    private Long idpurchasereceive;

    private String listIdCargo;//1,2,3

    public Long getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Long idcargo) {
        this.idcargo = idcargo;
    }

    public Long getIdpurchasereceive() {
        return idpurchasereceive;
    }

    public void setIdpurchasereceive(Long idpurchasereceive) {
        this.idpurchasereceive = idpurchasereceive;
    }

    public String getListIdCargo() {
        return listIdCargo;
    }

    public void setListIdCargo(String listIdCargo) {
        this.listIdCargo = listIdCargo;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
