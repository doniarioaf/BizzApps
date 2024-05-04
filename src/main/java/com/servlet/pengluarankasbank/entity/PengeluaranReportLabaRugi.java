package com.servlet.pengluarankasbank.entity;

public class PengeluaranReportLabaRugi {
    private String noDocument;
    private String keterangan;
    private Double amount;

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
