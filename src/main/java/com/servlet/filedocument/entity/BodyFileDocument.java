package com.servlet.filedocument.entity;

public class BodyFileDocument {
    private Long iddata;
    private String menu;
    private String filename;
    private String filedocument;
    private String filecontenttype;

    public Long getIddata() {
        return iddata;
    }

    public void setIddata(Long iddata) {
        this.iddata = iddata;
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
}
