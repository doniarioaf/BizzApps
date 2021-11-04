package com.servlet.admin.usermobilerole.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.admin.userappsrole.entity.UserAppsRole;
import com.servlet.admin.userappsrole.entity.UserAppsRolePK;
import com.servlet.admin.usermobilerole.entity.UserMobileRole;
import com.servlet.admin.usermobilerole.entity.UserMobileRolePK;

@Repository("UserMobileRoleRepo")
public interface UserMobileRoleRepo extends JpaRepository<UserMobileRole, UserMobileRolePK>{


}
