package com.servlet.invoice.entity;

import java.sql.Date;

public class InvoiceDataReportPelunasanPiutang {
    private Long id;
    private Long idcustomer;
    private String customerName;
    private String customerAlias;
    private String customerGrup;
    private Date date;
    private String noDocument;
    private String flightnumber;
    private String awb;
    private Long koli;
    private Double invoiceAmount;
    private Double kurs;

    @Override
    public String toString() {
        return id+"";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCustomerAlias() {
        return customerAlias;
    }

    public void setCustomerAlias(String customerAlias) {
        this.customerAlias = customerAlias;
    }

    public String getCustomerGrup() {
        return customerGrup;
    }

    public void setCustomerGrup(String customerGrup) {
        this.customerGrup = customerGrup;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNoDocument() {
        return noDocument;
    }

    public void setNoDocument(String noDocument) {
        this.noDocument = noDocument;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getAwb() {
        return awb;
    }

    public void setAwb(String awb) {
        this.awb = awb;
    }

    public Long getKoli() {
        return koli;
    }

    public void setKoli(Long koli) {
        this.koli = koli;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }
}
