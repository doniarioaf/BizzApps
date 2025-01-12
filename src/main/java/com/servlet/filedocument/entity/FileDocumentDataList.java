package com.servlet.filedocument.entity;

public class FileDocumentDataList {
    private Long id;
    private Long iddata;
    private String menu;
    private String filename;

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
}
