package com.servlet.invoice.entity;

public class ParamSearchInvoice {
    private Long from;
    private Long to;
    private Long idcustomer;
    private String listGroup;
    private String status;
    private String listIdProduct;
    private String listIdCategoryProduct;

    public String getListIdProduct() {
        return listIdProduct;
    }

    public void setListIdProduct(String listIdProduct) {
        this.listIdProduct = listIdProduct;
    }

    public String getListIdCategoryProduct() {
        return listIdCategoryProduct;
    }

    public void setListIdCategoryProduct(String listIdCategoryProduct) {
        this.listIdCategoryProduct = listIdCategoryProduct;
    }

    public Long getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Long idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getListGroup() {
        return listGroup;
    }

    public void setListGroup(String listGroup) {
        this.listGroup = listGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
