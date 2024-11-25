package com.servlet.admin.userbranch.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_branch", schema = "public")
public class UserBranch {

    @EmbeddedId
    private UserBranchPK userBranchPK;

    public UserBranchPK getUserBranchPK() {
        return userBranchPK;
    }

    public void setUserBranchPK(UserBranchPK userBranchPK) {
        this.userBranchPK = userBranchPK;
    }
}
