package com.servlet.admin.userbranch.repo;

import com.servlet.admin.userbranch.entity.UserBranch;
import com.servlet.admin.userbranch.entity.UserBranchPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserBranchRepo")
public interface UserBranchRepo extends JpaRepository<UserBranch, UserBranchPK> {
}
