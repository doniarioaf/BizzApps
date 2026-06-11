package com.servlet.packinglist.entity;


public class BodyPackingList {
    private Long date;
    private Long datestock;
    private Long idcustomer;
    private String city;
    private String attention;
    private String flightnumber;
    private String awbnumber;
    private Double netto;
    private Long koli;
    private Long idpricelist;
    private BodyPackingListItem[] items;
    private Long idvendor;

    public Long getDatestock() {
        return datestock;
    }

    public void setDatestock(Long datestock) {
        this.datestock = datestock;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Long getIdpricelist() {
        return idpricelist;
    }

    public void setIdpricelist(Long idpricelist) {
        this.idpricelist = idpricelist;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Long idcustomer) {
        this.idcustomer = idcustomer;
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

    public BodyPackingListItem[] getItems() {
        return items;
    }

    public void setItems(BodyPackingListItem[] items) {
        this.items = items;
    }
}
