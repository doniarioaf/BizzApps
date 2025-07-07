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
    private String vendorBank;
    private String vendorAccNo;
    private String vendorAccNameBank;
    private Date transactiondate;
    private String koli;
    private String notes;
    private String notes2;
    private String bank;
    private String accountnobank;
    private String accountnamebank;
    private Double totalprice;
    private Double setor;
    private Double setorPinjaman;
    private Long iddeposit;
    private Double depositAmount;
    private Double sisaDeposit;
    private Long countPrint;
    private Long countEdit;
    private String namaUser;
    private Double saldoDepositBeforeNotaSubmit;
    private Double saldoPinjaman;
    private Timestamp createddate;
    private List<PrintDataPurchaseReceiveItems> items;
    private List<PrintDataPurchaseReceiveCharge> charges;
    private List<PrintDataPurchaseReceiveInventori> inventori;
    private List<PurchaseReceiveDepositData> deposits;

    private String noSMU;
    private String flightno;
    private String namaArea;
    private String aliasArea;

    public Double getSaldoPinjaman() {
        return saldoPinjaman;
    }

    public void setSaldoPinjaman(Double saldoPinjaman) {
        this.saldoPinjaman = saldoPinjaman;
    }

    public String getVendorBank() {
        return vendorBank;
    }

    public void setVendorBank(String vendorBank) {
        this.vendorBank = vendorBank;
    }

    public String getVendorAccNo() {
        return vendorAccNo;
    }

    public void setVendorAccNo(String vendorAccNo) {
        this.vendorAccNo = vendorAccNo;
    }

    public String getVendorAccNameBank() {
        return vendorAccNameBank;
    }

    public void setVendorAccNameBank(String vendorAccNameBank) {
        this.vendorAccNameBank = vendorAccNameBank;
    }

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public List<PurchaseReceiveDepositData> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<PurchaseReceiveDepositData> deposits) {
        this.deposits = deposits;
    }

    public String getNotes2() {
        return notes2;
    }

    public void setNotes2(String notes2) {
        this.notes2 = notes2;
    }

    public String getNoSMU() {
        return noSMU;
    }

    public void setNoSMU(String noSMU) {
        this.noSMU = noSMU;
    }

    public String getNamaArea() {
        return namaArea;
    }

    public void setNamaArea(String namaArea) {
        this.namaArea = namaArea;
    }

    public String getAliasArea() {
        return aliasArea;
    }

    public void setAliasArea(String aliasArea) {
        this.aliasArea = aliasArea;
    }

    public Long getCountPrint() {
        return countPrint;
    }

    public void setCountPrint(Long countPrint) {
        this.countPrint = countPrint;
    }

    public Long getCountEdit() {
        return countEdit;
    }

    public void setCountEdit(Long countEdit) {
        this.countEdit = countEdit;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

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

    public Double getSetorPinjaman() {
        return setorPinjaman;
    }

    public void setSetorPinjaman(Double setorPinjaman) {
        this.setorPinjaman = setorPinjaman;
    }
}
