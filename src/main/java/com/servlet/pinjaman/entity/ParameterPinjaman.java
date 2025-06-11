package com.servlet.pinjaman.entity;

import org.springframework.web.multipart.MultipartFile;

public class ParameterPinjaman {
    private Long id;
    private Long idcompany;
    private Long idbranch;
    private PinjamanParameterList parameterList;
    private Long iduser;
    private MultipartFile file;
    private BodyPinjaman body;

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
