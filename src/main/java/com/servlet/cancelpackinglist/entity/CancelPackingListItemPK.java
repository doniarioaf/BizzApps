package com.servlet.cancelpackinglist.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CancelPackingListItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private long idpackinglist;
    private Long idproduct;
    private Long idcategoryproduct;
    private String box;
    private int noseq;

    @Override
    public String toString() {
        return "CancelPackingListItemPK{" +
                "idpackinglist=" + idpackinglist +
                ", idproduct=" + idproduct +
                ", idcategoryproduct=" + idcategoryproduct +
                ", box='" + box + '\'' +
                ", noseq=" + noseq +
                '}';
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public int getNoseq() {
        return noseq;
    }

    public void setNoseq(int noseq) {
        this.noseq = noseq;
    }

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
