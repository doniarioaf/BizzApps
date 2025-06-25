package com.servlet.pinjaman.entity;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class ParameterPinjaman {
    private Long id;
    private Long idcompany;
    private Long idbranch;
    private PinjamanParameterList parameterList;
    private Long iduser;
    private MultipartFile file;
    private BodyPinjaman body;

    private Long idvendor;
    private Date date;
    private String operatorPerbandingan;

    public Long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(Long idvendor) {
        this.idvendor = idvendor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperatorPerbandingan() {
        return operatorPerbandingan;
    }

    public void setOperatorPerbandingan(String operatorPerbandingan) {
        this.operatorPerbandingan = operatorPerbandingan;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public PinjamanParameterList getParameterList() {
        return parameterList;
    }

    public void setParameterList(PinjamanParameterList parameterList) {
        this.parameterList = parameterList;
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

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public BodyPinjaman getBody() {
        return body;
    }

    public void setBody(BodyPinjaman body) {
        this.body = body;
    }
}
