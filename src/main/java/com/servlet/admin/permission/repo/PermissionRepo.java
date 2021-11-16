package com.servlet.admin.permission.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.admin.permission.entity.Permission;

@Repository("PermissionRepo")
public interface PermissionRepo extends JpaRepository<Permission, Long>{

}
