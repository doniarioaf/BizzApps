package com.servlet.stockadjusment.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class StockAdjsumentDataDetail {
    private Long id;
    private String nodocument;
    private Date date;
    private Date pricedate;
    private String note;
    private String type;
    private Long idpricelist;
    private String noDocumentPriceList;
    private String createdbyName;
    private Timestamp createddate;
    private String modifiedbyName;
    private Timestamp modifieddate;
    private List<StockAdjsumentDataItem> items;

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

    public Date getPricedate() {
        return pricedate;
    }

    public void setPricedate(Date pricedate) {
        this.pricedate = pricedate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdpricelist() {
        return idpricelist;
    }

    public void setIdpricelist(Long idpricelist) {
        this.idpricelist = idpricelist;
    }

    public String getNoDocumentPriceList() {
        return noDocumentPriceList;
    }

    public void setNoDocumentPriceList(String noDocumentPriceList) {
        this.noDocumentPriceList = noDocumentPriceList;
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

    public List<StockAdjsumentDataItem> getItems() {
        return items;
    }

    public void setItems(List<StockAdjsumentDataItem> items) {
        this.items = items;
    }
}
