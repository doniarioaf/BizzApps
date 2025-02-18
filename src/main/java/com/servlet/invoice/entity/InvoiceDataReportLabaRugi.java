package com.servlet.invoice.entity;

import java.sql.Date;

public class InvoiceDataReportLabaRugi {
    private Long id;
    private Long idwo;
    private String nodocument;
    private Date tanggal;
    private String idinvoicetype;
    private String namainvoicetype;
    private Double totalinvoice;
    private Double ppn;
    private Double nilaippn;
    private String notes1;
    private String notes2;
    private String nodocumentreimbursement;
    private String nodocumentjasa;
    private Double nilaijasa;
    private Double nilaireimbursement;
    private String nofakturpajak;

    public Long getIdwo() {
        return idwo;
    }

    public void setIdwo(Long idwo) {
        this.idwo = idwo;
    }

    public String getNodocumentreimbursement() {
        return nodocumentreimbursement;
    }

    public void setNodocumentreimbursement(String nodocumentreimbursement) {
        this.nodocumentreimbursement = nodocumentreimbursement;
    }

    public String getNodocumentjasa() {
        return nodocumentjasa;
    }

    public void setNodocumentjasa(String nodocumentjasa) {
        this.nodocumentjasa = nodocumentjasa;
    }

    public Double getNilaijasa() {
        return nilaijasa;
    }

    public void setNilaijasa(Double nilaijasa) {
        this.nilaijasa = nilaijasa;
    }

    public Double getNilaireimbursement() {
        return nilaireimbursement;
    }

    public void setNilaireimbursement(Double nilaireimbursement) {
        this.nilaireimbursement = nilaireimbursement;
    }

    public String getNofakturpajak() {
        return nofakturpajak;
    }

    public void setNofakturpajak(String nofakturpajak) {
        this.nofakturpajak = nofakturpajak;
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

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getIdinvoicetype() {
        return idinvoicetype;
    }

    public void setIdinvoicetype(String idinvoicetype) {
        this.idinvoicetype = idinvoicetype;
    }

    public String getNamainvoicetype() {
        return namainvoicetype;
    }

    public void setNamainvoicetype(String namainvoicetype) {
        this.namainvoicetype = namainvoicetype;
    }

    public Double getTotalinvoice() {
        return totalinvoice;
    }

    public void setTotalinvoice(Double totalinvoice) {
        this.totalinvoice = totalinvoice;
    }

    public Double getPpn() {
        return ppn;
    }

    public void setPpn(Double ppn) {
        this.ppn = ppn;
    }

    public Double getNilaippn() {
        return nilaippn;
    }

    public void setNilaippn(Double nilaippn) {
        this.nilaippn = nilaippn;
    }

    public String getNotes1() {
        return notes1;
    }

    public void setNotes1(String notes1) {
        this.notes1 = notes1;
    }

    public String getNotes2() {
        return notes2;
    }

    public void setNotes2(String notes2) {
        this.notes2 = notes2;
    }
}
