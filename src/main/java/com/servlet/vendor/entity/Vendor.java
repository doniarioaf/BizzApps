package com.servlet.vendor.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "m_vendor", schema = "public")
public class Vendor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="m_vendor_id_seq")
    private Long id;
    private Long idcompany;
    private Long idbranch;
    private String nama;
    private String alias;
    private String type;
    private String bank;
    private String accountnobank;
    private String accountnamebank;
    private boolean isdelete;
    private Long createdby;
    private Timestamp createddate;
    private Long modifiedby;
    private Timestamp modifieddate;
    private Long deleteby;
    private Timestamp deletedate;
    private Double pricebox;
    private Double priceongkos;
    private Double packing;
    private Double kurir;
    private Double komisi;
    private Double profit;
    private Double value1;

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", idcompany=" + idcompany +
                ", idbranch=" + idbranch +
                ", nama='" + nama + '\'' +
                ", alias='" + alias + '\'' +
                ", type='" + type + '\'' +
                ", bank='" + bank + '\'' +
                ", accountnobank='" + accountnobank + '\'' +
                ", accountnamebank='" + accountnamebank + '\'' +
                ", pricebox='" + pricebox + '\'' +
                ", priceongkos='" + priceongkos + '\'' +
                ", packing='" + packing + '\'' +
                ", kurir='" + kurir + '\'' +
                ", komisi='" + komisi + '\'' +
                ", profit='" + profit + '\'' +
                ", value1='" + value1 + '\'' +
                '}';
    }

    public Double getPacking() {
        return packing;
    }

    public void setPacking(Double packing) {
        this.packing = packing;
    }

    public Double getKurir() {
        return kurir;
    }

    public void setKurir(Double kurir) {
        this.kurir = kurir;
    }

    public Double getKomisi() {
        return komisi;
    }

    public void setKomisi(Double komisi) {
        this.komisi = komisi;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getPricebox() {
        return pricebox;
    }

    public void setPricebox(Double pricebox) {
        this.pricebox = pricebox;
    }

    public Double getPriceongkos() {
        return priceongkos;
    }

    public void setPriceongkos(Double priceongkos) {
        this.priceongkos = priceongkos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public Long getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(Long modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Long getDeleteby() {
        return deleteby;
    }

    public void setDeleteby(Long deleteby) {
        this.deleteby = deleteby;
    }

    public Timestamp getDeletedate() {
        return deletedate;
    }

    public void setDeletedate(Timestamp deletedate) {
        this.deletedate = deletedate;
    }
}
