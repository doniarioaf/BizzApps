package com.servlet.stockadjusment.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StockAdjusmentItemPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idstockadjusment;
    private long idproduct;
    private long idcategoryproduct;
    private String type;

    public long getIdstockadjusment() {
        return idstockadjusment;
    }

    public void setIdstockadjusment(long idstockadjusment) {
        this.idstockadjusment = idstockadjusment;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
