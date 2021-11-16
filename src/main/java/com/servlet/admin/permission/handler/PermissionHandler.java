package com.servlet.admin.permission.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.admin.permission.entity.PermissionData;
import com.servlet.admin.permission.mapper.GetPermissionData;
import com.servlet.admin.permission.service.PermissionService;
import com.servlet.admin.rolepermissions.mapper.GetRolePermissionByIdRole;

@Service
public class PermissionHandler implements PermissionService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<PermissionData> getAllListPermission() {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetPermissionData().schema());
		final Object[] queryParameters = new Object[] {};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetPermissionData(), queryParameters);
	}

}
