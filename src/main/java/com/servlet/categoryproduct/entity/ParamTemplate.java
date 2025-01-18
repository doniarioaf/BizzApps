package com.servlet.categoryproduct.entity;

public class ParamTemplate {
    private String menu;
    private Boolean showOnlyCpMapping;
    private long idvendor;
    private String listidcategoryproduct;

    public String getListidcategoryproduct() {
        return listidcategoryproduct;
    }

    public void setListidcategoryproduct(String listidcategoryproduct) {
        this.listidcategoryproduct = listidcategoryproduct;
    }

    public Boolean getShowOnlyCpMapping() {
        return showOnlyCpMapping;
    }

    public void setShowOnlyCpMapping(Boolean showOnlyCpMapping) {
        this.showOnlyCpMapping = showOnlyCpMapping;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public long getIdvendor() {
        return idvendor;
    }

    public void setIdvendor(long idvendor) {
        this.idvendor = idvendor;
    }
}
