package com.servlet.admin.userappsrole.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_user_apps_role", schema = "public")
public class UserAppsRole {
	@EmbeddedId
    private UserAppsRolePK userAppsRolePK;

	public UserAppsRolePK getUserAppsRolePK() {
		return userAppsRolePK;
	}

	public void setUserAppsRolePK(UserAppsRolePK userAppsRolePK) {
		this.userAppsRolePK = userAppsRolePK;
	}

}
