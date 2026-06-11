package com.servlet.komisi.entity;

import java.sql.Date;

public class KomisiDataReportKomisi {
    private Long idvendorbroker;
    private Long idvendor;
    private String vendorname;
    private String vendoralias;
    private String nodocumentPR;
    private String nodocumentKomisi;
    private Date date;
    private Long koli;
    private Double komisiperkoli;
    private Double subtotalkomisi;
    private Double additional_commission;
    private String description;

    public Double getAdditional_commission() {
        return additional_commission;
    }

    public void setAdditional_commission(Double additional_commission) {
        this.additional_commission = additional_commission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNodocumentKomisi() {
        return nodocumentKomisi;
    }

    public void setNodocumentKomisi(String nodocumentKomisi) {
        this.nodocumentKomisi = nodocumentKomisi;
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

    public String getVendoralias() {
        return vendoralias;
    }

    public void setVendoralias(String vendoralias) {
        this.vendoralias = vendoralias;
    }

    public String getNodocumentPR() {
        return nodocumentPR;
    }

    public void setNodocumentPR(String nodocumentPR) {
        this.nodocumentPR = nodocumentPR;
    }

    public Long getIdvendorbroker() {
        return idvendorbroker;
    }

    public void setIdvendorbroker(Long idvendorbroker) {
        this.idvendorbroker = idvendorbroker;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getKoli() {
        return koli;
    }

    public void setKoli(Long koli) {
        this.koli = koli;
    }

    public Double getKomisiperkoli() {
        return komisiperkoli;
    }

    public void setKomisiperkoli(Double komisiperkoli) {
        this.komisiperkoli = komisiperkoli;
    }

    public Double getSubtotalkomisi() {
        return subtotalkomisi;
    }

    public void setSubtotalkomisi(Double subtotalkomisi) {
        this.subtotalkomisi = subtotalkomisi;
    }
}
