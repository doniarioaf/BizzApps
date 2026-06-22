package com.servlet.pelunasanhutang.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class PelunasanHutangDataDetail {
    private Long id;
    private String nodocument;
    private Long idpurchasereceive;
    private String nodocumentPR;
    private String namavendorPR;
    private String aliasvendorPR;
    private Long idcargo;
    private Date date;
    private Double amount;
    private String notes;
    private String createdbyName;
    private Timestamp createddate;
    private String modifiedbyName;
    private Timestamp modifieddate;

    private Long fileId;
    private String fileName;
    private String fileContentType;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Long getIdpurchasereceive() {
        return idpurchasereceive;
    }

    public void setIdpurchasereceive(Long idpurchasereceive) {
        this.idpurchasereceive = idpurchasereceive;
    }

    public String getNodocumentPR() {
        return nodocumentPR;
    }

    public void setNodocumentPR(String nodocumentPR) {
        this.nodocumentPR = nodocumentPR;
    }

    public String getNamavendorPR() {
        return namavendorPR;
    }

    public void setNamavendorPR(String namavendorPR) {
        this.namavendorPR = namavendorPR;
    }

    public String getAliasvendorPR() {
        return aliasvendorPR;
    }

    public void setAliasvendorPR(String aliasvendorPR) {
        this.aliasvendorPR = aliasvendorPR;
    }

    public Long getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Long idcargo) {
        this.idcargo = idcargo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
}
