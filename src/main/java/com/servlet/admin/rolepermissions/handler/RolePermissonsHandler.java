package com.servlet.admin.rolepermissions.handler;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.admin.role.entity.RolePermissionData;
import com.servlet.admin.rolepermissions.entity.RolePermissions;
import com.servlet.admin.rolepermissions.entity.RolePermissionsPK;
import com.servlet.admin.rolepermissions.mapper.GetRolePermissionByIdRole;
import com.servlet.admin.rolepermissions.repo.RolePermissionsRepo;
import com.servlet.admin.rolepermissions.service.RolePermissionService;

@Service
public class RolePermissonsHandler implements RolePermissionService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RolePermissionsRepo repository;
	

	@Override
	public Object saveRolePermissions(RolePermissions rolePermissions) {
		// TODO Auto-generated method stub
		return repository.save(rolePermissions);
	}

	@Override
	public Object saveRolePermissionsList(List<RolePermissions> listrolePermissions) {
		// TODO Auto-generated method stub
		return repository.saveAllAndFlush(listrolePermissions);
	}

	@Override
	public Collection<RolePermissionData> getListRolePermissions(long idrole) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetRolePermissionByIdRole().schema());
		sqlBuilder.append(" where mrp.idrole = ? and mr.isdelete = false ");
		final Object[] queryParameters = new Object[] { idrole };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetRolePermissionByIdRole(), queryParameters);
	}

	@Override
	public Object deleteAllrolePermissionsByListPK(List<RolePermissionsPK> listPK) {
		// TODO Auto-generated method stub
		if(listPK.size() > 0) {
			repository.deleteAllById(listPK);
		}
		return null;
	}

}
