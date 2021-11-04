package com.servlet.admin.usermobilerole.handler;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.usermobilerole.entity.UserMobileRole;
import com.servlet.admin.usermobilerole.entity.UserMobileRoleData;
import com.servlet.admin.usermobilerole.entity.UserMobileRolePK;
import com.servlet.admin.usermobilerole.mapper.GetUserMobileRoleByIdUserMobile;
import com.servlet.admin.usermobilerole.repo.UserMobileRoleRepo;
import com.servlet.admin.usermobilerole.service.UserMobileRoleService;

@Service
public class UserMobileRoleHandler implements UserMobileRoleService{
	@Autowired
	private UserMobileRoleRepo repository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Object saveUserMobileRole(UserMobileRolePK userMobileRolePK) {
		// TODO Auto-generated method stub
		UserMobileRole table = new UserMobileRole();
		table.setUserMobileRolePK(userMobileRolePK);
		repository.saveAndFlush(table);
		return null;
	}

	@Override
	public Object saveUserMobileRoleList(List<UserMobileRole> listUserMobileRole) {
		// TODO Auto-generated method stub
		if(listUserMobileRole.size() > 0) {
			repository.saveAllAndFlush(listUserMobileRole);
		}
		return null;
	}

	@Override
	public Collection<UserMobileRoleData> getListUserMobileRole(long idusermobile) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetUserMobileRoleByIdUserMobile().schema());
		sqlBuilder.append(" where userrole.idusermobile = ? ");
		final Object[] queryParameters = new Object[] { idusermobile };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetUserMobileRoleByIdUserMobile(), queryParameters);
	}

	@Override
	public Object deleteAllUserMobileRolePKPK(List<UserMobileRolePK> listPK) {
		// TODO Auto-generated method stub
		if(listPK.size() > 0) {
			repository.deleteAllById(listPK);
		}
		return null;
	}

	

}
