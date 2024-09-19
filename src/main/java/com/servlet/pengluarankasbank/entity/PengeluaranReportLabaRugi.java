package com.servlet.pengluarankasbank.entity;

import java.sql.Date;

public class PengeluaranReportLabaRugi {
    private String noDocument;
    private String keterangan;
    private Double amount;
    private String namabank;
    private Date paymentdate;

    public Date getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
    }

    public String getNamabank() {
        return namabank;
    }

    public void setNamabank(String namabank) {
        this.namabank = namabank;
    }

    public String getNoDocument() {
        return noDocument;
    }

    public void setNoDocument(String noDocument) {
        this.noDocument = noDocument;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
