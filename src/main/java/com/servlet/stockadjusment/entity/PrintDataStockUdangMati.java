package com.servlet.stockadjusment.entity;

import com.servlet.categoryproduct.entity.CategoryProductList;
import com.servlet.mappingstock.entity.MappingStockList;

import java.sql.Date;
import java.util.List;

public class PrintDataStockUdangMati {
    private Long id;
    private String nodocument;
    private Date date;
    private String note;
    private String type;
    private String createdbyName;
    private Long countPrint;
    private Long countEdit;
    private String namaUser;
    private List<StockAdjsumentDataItem> items;
    private List<MappingStockList> mappingstock;
    private List<CategoryProductList> listcp;

    public List<MappingStockList> getMappingstock() {
        return mappingstock;
    }

    public void setMappingstock(List<MappingStockList> mappingstock) {
        this.mappingstock = mappingstock;
    }

    public List<CategoryProductList> getListcp() {
        return listcp;
    }

    public void setListcp(List<CategoryProductList> listcp) {
        this.listcp = listcp;
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

    public String getCreatedbyName() {
        return createdbyName;
    }

    public void setCreatedbyName(String createdbyName) {
        this.createdbyName = createdbyName;
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

    public List<StockAdjsumentDataItem> getItems() {
        return items;
    }

    public void setItems(List<StockAdjsumentDataItem> items) {
        this.items = items;
    }
}
