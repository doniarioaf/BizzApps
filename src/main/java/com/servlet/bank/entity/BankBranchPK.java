package com.servlet.bank.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BankBranchPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idbank;
    private Long idbranch;

    public Long getIdbank() {
        return idbank;
    }

    public void setIdbank(Long idbank) {
        this.idbank = idbank;
    }

    public Long getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(Long idbranch) {
        this.idbranch = idbranch;
    }
}
