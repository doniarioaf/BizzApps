package com.servlet.admin.rolepermissions.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.admin.rolepermissions.entity.RolePermissions;
import com.servlet.admin.rolepermissions.entity.RolePermissionsPK;

@Repository("RolePermissionsRepo")
public interface RolePermissionsRepo extends JpaRepository<RolePermissions, RolePermissionsPK>{

}
