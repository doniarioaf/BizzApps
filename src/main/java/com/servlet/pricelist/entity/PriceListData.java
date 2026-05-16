package com.servlet.pricelist.entity;

import java.sql.Date;

public class PriceListData {
    private Long id;
    private Date pricedate;
    private Date pricedatethru;
    private Long idcustomer;
    private String customerName;
    private String customerAlias;

    public Long getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Long idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAlias() {
        return customerAlias;
    }

    public void setCustomerAlias(String customerAlias) {
        this.customerAlias = customerAlias;
    }

    public Date getPricedatethru() {
        return pricedatethru;
    }

    public void setPricedatethru(Date pricedatethru) {
        this.pricedatethru = pricedatethru;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPricedate() {
        return pricedate;
    }

    public void setPricedate(Date pricedate) {
        this.pricedate = pricedate;
    }
}
