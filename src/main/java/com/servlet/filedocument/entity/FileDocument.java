package com.servlet.filedocument.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "files_document", schema = "public")
public class FileDocument implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="files_document_id_seq")
    private Long id;
    private Long iddata;
    private Long idcompany;
    private Long idbranch;
    private String menu;
    private String filename;
    private String filedocument;
    private String filecontenttype;
    private Long createdby;
    private Timestamp createddate;
    private Long modifiedby;
    private Timestamp modifieddate;
    private Long deleteby;
    private Timestamp deletedate;

    @Override
    public String toString() {
        return "FileDocument{" +
                "id=" + id +
                ", iddata=" + iddata +
                ", menu='" + menu + '\'' +
                ", filename='" + filename + '\'' +
                ", filecontenttype='" + filecontenttype + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIddata() {
        return iddata;
    }

    public void setIddata(Long iddata) {
        this.iddata = iddata;
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiledocument() {
        return filedocument;
    }

    public void setFiledocument(String filedocument) {
        this.filedocument = filedocument;
    }

    public String getFilecontenttype() {
        return filecontenttype;
    }

    public void setFilecontenttype(String filecontenttype) {
        this.filecontenttype = filecontenttype;
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
