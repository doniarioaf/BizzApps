package com.servlet.packinglist.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PackingListItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private long idpackinglist;
    private Long idproduct;
    private Long idcategoryproduct;

    public long getIdpackinglist() {
        return idpackinglist;
    }

    public void setIdpackinglist(long idpackinglist) {
        this.idpackinglist = idpackinglist;
    }

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }

    public Long getIdcategoryproduct() {
        return idcategoryproduct;
    }

    public void setIdcategoryproduct(Long idcategoryproduct) {
        this.idcategoryproduct = idcategoryproduct;
    }
}
