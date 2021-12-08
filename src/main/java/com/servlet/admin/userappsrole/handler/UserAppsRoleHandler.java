package com.servlet.admin.userappsrole.handler;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.userappsrole.entity.UserAppsRole;
import com.servlet.admin.userappsrole.entity.UserAppsRoleData;
import com.servlet.admin.userappsrole.entity.UserAppsRolePK;
import com.servlet.admin.userappsrole.mapper.GetUserAppsRoleByIdUserApps;
import com.servlet.admin.userappsrole.repo.UserAppsRoleRepo;
import com.servlet.admin.userappsrole.service.UserAppsRoleService;

@Service
public class UserAppsRoleHandler implements UserAppsRoleService{
	@Autowired
	private UserAppsRoleRepo repository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public Object saveUserAppsRole(UserAppsRolePK userAppsRolePK) {
		// TODO Auto-generated method stub
		UserAppsRole table = new UserAppsRole();
		table.setUserAppsRolePK(userAppsRolePK);
		repository.saveAndFlush(table);
		return null;
	}

	@Override
	public Object saveUserAppsRoleList(List<UserAppsRole> listUserAppsRole) {
		// TODO Auto-generated method stub
		if(listUserAppsRole.size() > 0) {
			repository.saveAllAndFlush(listUserAppsRole);
		}
		return null;
	}

	@Override
	public Collection<UserAppsRoleData> getListUserAppsRole(long iduserapps) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetUserAppsRoleByIdUserApps().schema());
		sqlBuilder.append(" where userrole.iduserapps = ? and mr.isdelete = false ");
		final Object[] queryParameters = new Object[] { iduserapps };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetUserAppsRoleByIdUserApps(), queryParameters);
	}

	@Override
	public Object deleteAllUserAppsRolePKPK(List<UserAppsRolePK> listPK) {
		// TODO Auto-generated method stub
		if(listPK.size() > 0) {
			repository.deleteAllById(listPK);
		}
		return null;
	}

}
