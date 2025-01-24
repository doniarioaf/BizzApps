package com.servlet.purchasereceive.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class PurchaseReceiveDataDetail {
    private Long id;
    private String nodocument;
    private Long idvendor;
    private String vendorname;
    private String vendoralias;
    private Date transactiondate;
    private String koli;
    private String notes;
    private String bank;
    private String accountnobank;
    private String accountnamebank;
    private String createdbyName;
    private Timestamp createddate;
    private String modifiedbyName;
    private Timestamp modifieddate;
    private Double totalprice;
    private boolean isdefaultvaluesetor;
    private Double setor;
    private Long iddeposit;
    private Double sisaDeposit;
    private Long iddraftpurchasereceive;
    private String nodocumentDraft;
    private String noSmuDraft;
    private Long idarea;
    private String namaArea;
    private String aliasArea;
    private Double outstanding;
    private String flightno;
    private String smu;
    private String notes2;

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public String getSmu() {
        return smu;
    }

    public void setSmu(String smu) {
        this.smu = smu;
    }

    public String getNotes2() {
        return notes2;
    }

    public void setNotes2(String notes2) {
        this.notes2 = notes2;
    }

    public Double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Double outstanding) {
        this.outstanding = outstanding;
    }

    public Long getIdarea() {
        return idarea;
    }

    public void setIdarea(Long idarea) {
        this.idarea = idarea;
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

    private List<PrintDataPurchaseReceiveItems> items;
    private List<PrintDataPurchaseReceiveCharge> charges;
    private List<PrintDataPurchaseReceiveInventori> inventori;

    public Long getIddraftpurchasereceive() {
        return iddraftpurchasereceive;
    }

    public void setIddraftpurchasereceive(Long iddraftpurchasereceive) {
        this.iddraftpurchasereceive = iddraftpurchasereceive;
    }

    public String getNodocumentDraft() {
        return nodocumentDraft;
    }

    public void setNodocumentDraft(String nodocumentDraft) {
        this.nodocumentDraft = nodocumentDraft;
    }

    public String getNoSmuDraft() {
        return noSmuDraft;
    }

    public void setNoSmuDraft(String noSmuDraft) {
        this.noSmuDraft = noSmuDraft;
    }

    public Double getSisaDeposit() {
        return sisaDeposit;
    }

    public void setSisaDeposit(Double sisaDeposit) {
        this.sisaDeposit = sisaDeposit;
    }

    public String getVendoralias() {
        return vendoralias;
    }

    public void setVendoralias(String vendoralias) {
        this.vendoralias = vendoralias;
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

    public List<PrintDataPurchaseReceiveInventori> getInventori() {
        return inventori;
    }

    public void setInventori(List<PrintDataPurchaseReceiveInventori> inventori) {
        this.inventori = inventori;
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

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
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

    public String getCreatedbyName() {
        return createdbyName;
    }

    public void setCreatedbyName(String createdbyName) {
        this.createdbyName = createdbyName;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public String getModifiedbyName() {
        return modifiedbyName;
    }

    public void setModifiedbyName(String modifiedbyName) {
        this.modifiedbyName = modifiedbyName;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isIsdefaultvaluesetor() {
        return isdefaultvaluesetor;
    }

    public void setIsdefaultvaluesetor(boolean isdefaultvaluesetor) {
        this.isdefaultvaluesetor = isdefaultvaluesetor;
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
}
