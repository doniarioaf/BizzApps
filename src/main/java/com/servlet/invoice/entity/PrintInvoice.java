package com.servlet.invoice.entity;

import com.servlet.packinglist.entity.PackingListDataDetail;

import java.sql.Date;

public class PrintInvoice {
    private String companyName;
    private String address1;
    private String address2;
    private String address3;
    private String bankCompany;
    private String bankAccNoCompany;
    private String bankAccNameCompany;
    private Long id;
    private String nodocument;
    private Date date;
    private Double kurs;
    private Long idpackinglist;
    private PackingListDataDetail packinglist;
    private String phone;

    private Long countPrint;
    private Long countEdit;
    private String namaUser;

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

    public String getBankCompany() {
        return bankCompany;
    }

    public void setBankCompany(String bankCompany) {
        this.bankCompany = bankCompany;
    }

    public String getBankAccNoCompany() {
        return bankAccNoCompany;
    }

    public void setBankAccNoCompany(String bankAccNoCompany) {
        this.bankAccNoCompany = bankAccNoCompany;
    }

    public String getBankAccNameCompany() {
        return bankAccNameCompany;
    }

    public void setBankAccNameCompany(String bankAccNameCompany) {
        this.bankAccNameCompany = bankAccNameCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
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

    public Long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(Long idpackinglist) {
        this.idpackinglist = idpackinglist;
    }

    public PackingListDataDetail getPackinglist() {
        return packinglist;
    }

    public void setPackinglist(PackingListDataDetail packinglist) {
        this.packinglist = packinglist;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
