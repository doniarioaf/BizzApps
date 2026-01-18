package com.servlet.journal.entity;

import java.sql.Timestamp;

public class PostingJournalParam {
    //untuk service updateJournalDetail , boleh hanya isi,
    //Idcompany, Idbranch,Sourcenumber,amount,descriptionDetailDeposit,AmountPemakaianDeposit,descriptionDetailPinjaman,AmountPembayaranPinjaman,transaksitime
    private Long idcompany;
    private Long idbranch;
    private Double amount;
    private String descriptionDetail;
    private Double amountPemakaianDeposit;
    private String descriptionDetailDeposit;
    private Double amountPembayaranPinjaman;
    private String descriptionDetailPinjaman;
    private Long idvendor;
    private String sourcenumber;
    private String sourcetype;
    private Timestamp transaksitime;
    private String description;
    private Long createdby;

    public String getDescriptionDetail() {
        return descriptionDetail;
    }

    public void setDescriptionDetail(String descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }

    public String getDescriptionDetailDeposit() {
        return descriptionDetailDeposit;
    }

    public void setDescriptionDetailDeposit(String descriptionDetailDeposit) {
        this.descriptionDetailDeposit = descriptionDetailDeposit;
    }

    public String getDescriptionDetailPinjaman() {
        return descriptionDetailPinjaman;
    }

    public void setDescriptionDetailPinjaman(String descriptionDetailPinjaman) {
        this.descriptionDetailPinjaman = descriptionDetailPinjaman;
    }

    public Double getAmountPemakaianDeposit() {
        return amountPemakaianDeposit;
    }

    public void setAmountPemakaianDeposit(Double amountPemakaianDeposit) {
        this.amountPemakaianDeposit = amountPemakaianDeposit;
    }

    public Double getAmountPembayaranPinjaman() {
        return amountPembayaranPinjaman;
    }

    public void setAmountPembayaranPinjaman(Double amountPembayaranPinjaman) {
        this.amountPembayaranPinjaman = amountPembayaranPinjaman;
    }

    public Long getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(Long idcompany) {
        this.idcompany = idcompany;
    }

    public Long getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(Long idbranch) {
        this.idbranch = idbranch;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getSourcenumber() {
        return sourcenumber;
    }

    public void setSourcenumber(String sourcenumber) {
        this.sourcenumber = sourcenumber;
    }

    public String getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
    }

    public Timestamp getTransaksitime() {
        return transaksitime;
    }

    public void setTransaksitime(Timestamp transaksitime) {
        this.transaksitime = transaksitime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    @Override
    public String toString() {
        return "PostingJournal{" +
                "amount=" + amount +
                ", descriptionDetail='" + descriptionDetail + '\'' +
                ", amountPemakaianDeposit=" + amountPemakaianDeposit +
                ", descriptionDetailDeposit='" + descriptionDetailDeposit + '\'' +
                ", amountPembayaranPinjaman=" + amountPembayaranPinjaman +
                ", descriptionDetailPinjaman='" + descriptionDetailPinjaman + '\'' +
                ", idvendor=" + idvendor +
                ", sourcenumber='" + sourcenumber + '\'' +
                ", sourcetype='" + sourcetype + '\'' +
                ", transaksitime=" + transaksitime +
                ", description='" + description + '\'' +
                '}';
    }
}
