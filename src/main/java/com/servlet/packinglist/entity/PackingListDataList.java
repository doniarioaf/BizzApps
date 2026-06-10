package com.servlet.packinglist.entity;

import java.sql.Date;

public class PackingListDataList {
    private Long id;
    private String nodocument;
    private Date date;
    private Date datestock;
    private Long idcustomer;
    private String customerNama;
    private String customerAlias;
    private String city;
    private Boolean isalreadyupdateprice;
    private String isallchecked;

    public String getIsallchecked() {
        return isallchecked;
    }

    public void setIsallchecked(String isallchecked) {
        this.isallchecked = isallchecked;
    }

    public Boolean getIsalreadyupdateprice() {
        return isalreadyupdateprice;
    }

    public Date getDatestock() {
        return datestock;
    }

    public void setDatestock(Date datestock) {
        this.datestock = datestock;
    }

    public void setIsalreadyupdateprice(Boolean isalreadyupdateprice) {
        this.isalreadyupdateprice = isalreadyupdateprice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodocument() {
        return nodocument;
    }

    public void setNodocument(String nodocument) {
        this.nodocument = nodocument;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Long idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getCustomerNama() {
        return customerNama;
    }

    public void setCustomerNama(String customerNama) {
        this.customerNama = customerNama;
    }

    public String getCustomerAlias() {
        return customerAlias;
    }

    public void setCustomerAlias(String customerAlias) {
        this.customerAlias = customerAlias;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
