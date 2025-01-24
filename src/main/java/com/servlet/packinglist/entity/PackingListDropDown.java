package com.servlet.packinglist.entity;

import java.sql.Date;

public class PackingListDropDown {
    private Long id;
    private String nodocument;
    private Date date;
    private Long idcustomer;
    private String customerName;
    private String customerALias;
    private String customerAddress;
    private String city;
    private String attention;
    private String flightnumber;
    private String awbnumber;
    private Double netto;
    private Long koli;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerALias() {
        return customerALias;
    }

    public void setCustomerALias(String customerALias) {
        this.customerALias = customerALias;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getAwbnumber() {
        return awbnumber;
    }

    public void setAwbnumber(String awbnumber) {
        this.awbnumber = awbnumber;
    }

    public Double getNetto() {
        return netto;
    }

    public void setNetto(Double netto) {
        this.netto = netto;
    }

    public Long getKoli() {
        return koli;
    }

    public void setKoli(Long koli) {
        this.koli = koli;
    }
}
