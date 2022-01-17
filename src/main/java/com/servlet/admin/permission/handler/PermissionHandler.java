package com.servlet.admin.permission.handler;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.admin.permission.entity.BodyPermission;
import com.servlet.admin.permission.entity.Permission;
import com.servlet.admin.permission.entity.PermissionData;
import com.servlet.admin.permission.mapper.GetPermissionData;
import com.servlet.admin.permission.repo.PermissionRepo;
import com.servlet.admin.permission.service.PermissionService;
import com.servlet.shared.ReturnData;

@Service
public class PermissionHandler implements PermissionService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PermissionRepo repository;
	
	@Override
	public List<PermissionData> getAllListPermission() {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPermissionData().schema());
		final Object[] queryParameters = new Object[] {};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPermissionData(), queryParameters);
	}

	@Override
	public ReturnData savePermission(BodyPermission permission) {
		// TODO Auto-generated method stub		
		List<PermissionData> list = getPermissionByCode(permission.getCode());
		if(list != null && list.size() > 0) {
			ReturnData data = new ReturnData();
			data.setId(list.get(0).getId());
			data.setSuccess(true);
			return data;
		}else {
			Permission table = new Permission();
			table.setCode(permission.getCode());
			table.setDescriptions(permission.getDescription());
			Permission returntable = repository.saveAndFlush(table);
			ReturnData data = new ReturnData();
			data.setId(returntable.getId());
			data.setSuccess(true);
			return data;
		}
		
	}
	
	private List<PermissionData> getPermissionByCode(String code) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPermissionData().schema());
		sqlBuilder.append(" where data.code = ? ");
		final Object[] queryParameters = new Object[] {code};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPermissionData(), queryParameters);
	}

}
