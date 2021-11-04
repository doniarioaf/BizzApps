package com.servlet.admin.usermobilerole.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_user_mobile_role", schema = "public")
public class UserMobileRole {
	@EmbeddedId
    private UserMobileRolePK userMobileRolePK;

	public UserMobileRolePK getUserMobileRolePK() {
		return userMobileRolePK;
	}

	public void setUserMobileRolePK(UserMobileRolePK userMobileRolePK) {
		this.userMobileRolePK = userMobileRolePK;
	}
	
	

}
