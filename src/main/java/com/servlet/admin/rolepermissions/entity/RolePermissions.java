package com.servlet.admin.rolepermissions.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.servlet.admin.companybranch.entity.CompanyBranchPK;

@Entity
@Table(name = "m_role_permissions", schema = "public")
public class RolePermissions {
	
	@EmbeddedId
    private RolePermissionsPK rolePermissionsPK;

	public RolePermissionsPK getRolePermissionsPK() {
		return rolePermissionsPK;
	}

	public void setRolePermissionsPK(RolePermissionsPK rolePermissionsPK) {
		this.rolePermissionsPK = rolePermissionsPK;
	}
	

}
