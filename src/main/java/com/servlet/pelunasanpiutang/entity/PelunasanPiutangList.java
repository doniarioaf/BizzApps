package com.servlet.pelunasanpiutang.entity;

import java.sql.Date;

public class PelunasanPiutangList {
    private Long id;
    private String nodocument;
    private Date date;
    private Double kurs;
    private Double totalpembayaran;

    private Long idinvoice;
    private String nodocumentInvoice;
    private Double kursInvoice;
    private Double amountInvoice;
    private String customerName;
    private String customerAlias;
    private String noDocumentPL;

    public Double getTotalpembayaran() {
        return totalpembayaran;
    }

    public void setTotalpembayaran(Double totalpembayaran) {
        this.totalpembayaran = totalpembayaran;
    }

    public String getNoDocumentPL() {
        return noDocumentPL;
    }

    public void setNoDocumentPL(String noDocumentPL) {
        this.noDocumentPL = noDocumentPL;
    }

    public Double getKursInvoice() {
        return kursInvoice;
    }

    public void setKursInvoice(Double kursInvoice) {
        this.kursInvoice = kursInvoice;
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

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }

    public Long getIdinvoice() {
        return idinvoice;
    }

    public void setIdinvoice(Long idinvoice) {
        this.idinvoice = idinvoice;
    }

    public String getNodocumentInvoice() {
        return nodocumentInvoice;
    }

    public void setNodocumentInvoice(String nodocumentInvoice) {
        this.nodocumentInvoice = nodocumentInvoice;
    }

    public Double getAmountInvoice() {
        return amountInvoice;
    }

    public void setAmountInvoice(Double amountInvoice) {
        this.amountInvoice = amountInvoice;
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
}
