package com.servlet.admin.userbranch.service;

import com.servlet.admin.userbranch.entity.UserBranch;
import com.servlet.admin.userbranch.entity.UserBranchData;
import com.servlet.admin.userbranch.entity.UserBranchPK;


import java.util.List;

public interface UserBranchService {
    Object saveUserBranch(UserBranchPK userbranch);
    Object saveUserBranchList(List<UserBranch> list);
    Object deleteAllUserBranchByListPK(List<UserBranchPK> listPK);
    List<UserBranchData> getListUserBranchByIdUser(long iduser);
    List<UserBranchData> getListUserBranchByIdUserJoinBranch(long iduser);
}
