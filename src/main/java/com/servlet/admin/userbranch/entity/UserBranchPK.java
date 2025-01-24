package com.servlet.admin.userbranch.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserBranchPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private long iduser;
    private long idbranch;

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public long getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(long idbranch) {
        this.idbranch = idbranch;
    }
}
