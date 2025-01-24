package com.servlet.stockitems.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StockItemsPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idcompany;
    private long idbranch;
    private long idproduct;
    private long idcategoryproduct;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(long idcompany) {
        this.idcompany = idcompany;
    }

    public long getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(long idbranch) {
        this.idbranch = idbranch;
    }

    public long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(long idproduct) {
        this.idproduct = idproduct;
    }

    public long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }
}
