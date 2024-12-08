package com.servlet.purchasereceive.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class PrintDataPurchaseReceive {
    private Long id;
    private String companyName;
    private String nodocument;
    private Long idvendor;
    private String vendorNama;
    private String vendorAlias;
    private Date transactiondate;
    private String koli;
    private String notes;
    private String bank;
    private String accountnobank;
    private String accountnamebank;
    private Double totalprice;
    private Double setor;
    private Long iddeposit;
    private Double depositAmount;
    private Double sisaDeposit;

    /**
     * saldoDepositBeforeNotaSubmit didapat sebelum nota ini dibuat,
     * jadi di table deposit cari berdasarkan idvendor dan tanggalnya harus dibawah tanggal created nota ini.
     * jika sudah dapat angka nya, baru dikurang dengan totalprice nota. caranya sama cari berdasarkan vendor dan tanggal nya harus dibawah tanggal created nota ini.
     */
    private Double saldoDepositBeforeNotaSubmit;
    private Timestamp createddate;
    private List<PrintDataPurchaseReceiveItems> items;
    private List<PrintDataPurchaseReceiveCharge> charges;
    private List<PrintDataPurchaseReceiveInventori> inventori;

    public List<PrintDataPurchaseReceiveInventori> getInventori() {
        return inventori;
    }

    public void setInventori(List<PrintDataPurchaseReceiveInventori> inventori) {
        this.inventori = inventori;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public Double getSaldoDepositBeforeNotaSubmit() {
        return saldoDepositBeforeNotaSubmit;
    }

    public void setSaldoDepositBeforeNotaSubmit(Double saldoDepositBeforeNotaSubmit) {
        this.saldoDepositBeforeNotaSubmit = saldoDepositBeforeNotaSubmit;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getSisaDeposit() {
        return sisaDeposit;
    }

    public void setSisaDeposit(Double sisaDeposit) {
        this.sisaDeposit = sisaDeposit;
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

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public String getVendorNama() {
        return vendorNama;
    }

    public void setVendorNama(String vendorNama) {
        this.vendorNama = vendorNama;
    }

    public String getVendorAlias() {
        return vendorAlias;
    }

    public void setVendorAlias(String vendorAlias) {
        this.vendorAlias = vendorAlias;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getKoli() {
        return koli;
    }

    public void setKoli(String koli) {
        this.koli = koli;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountnobank() {
        return accountnobank;
    }

    public void setAccountnobank(String accountnobank) {
        this.accountnobank = accountnobank;
    }

    public String getAccountnamebank() {
        return accountnamebank;
    }

    public void setAccountnamebank(String accountnamebank) {
        this.accountnamebank = accountnamebank;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Double getSetor() {
        return setor;
    }

    public void setSetor(Double setor) {
        this.setor = setor;
    }

    public Long getIddeposit() {
        return iddeposit;
    }

    public void setIddeposit(Long iddeposit) {
        this.iddeposit = iddeposit;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public List<PrintDataPurchaseReceiveItems> getItems() {
        return items;
    }

    public void setItems(List<PrintDataPurchaseReceiveItems> items) {
        this.items = items;
    }

    public List<PrintDataPurchaseReceiveCharge> getCharges() {
        return charges;
    }

    public void setCharges(List<PrintDataPurchaseReceiveCharge> charges) {
        this.charges = charges;
    }
}
