package com.servlet.bank.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bank_branch", schema = "public")
public class BankBranch implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private BankBranchPK bankBranchPK;

    public BankBranchPK getBankBranchPK() {
        return bankBranchPK;
    }

    public void setBankBranchPK(BankBranchPK bankBranchPK) {
        this.bankBranchPK = bankBranchPK;
    }
}
