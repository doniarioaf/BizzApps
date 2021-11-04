package com.servlet.admin.userappsrole.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.admin.userappsrole.entity.UserAppsRole;
import com.servlet.admin.userappsrole.entity.UserAppsRolePK;

@Repository("UserAppsRoleRepo")
public interface UserAppsRoleRepo extends JpaRepository<UserAppsRole, UserAppsRolePK>{

}
