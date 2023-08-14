package com.servlet.registrasiapps.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.registrasiapps.entity.RegistrasiAppsData;
import com.servlet.registrasiapps.mapper.GetRegistrasiApps;
import com.servlet.registrasiapps.repo.RegistrasiAppsRepo;
import com.servlet.registrasiapps.service.RegistrasiAppsService;

@Service
public class RegistrasiAppsHandler implements RegistrasiAppsService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RegistrasiAppsRepo repository;
	
	@Override
	public List<RegistrasiAppsData> getListRegistrasiApps(Long idcompany, Long idbranch) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetRegistrasiApps().schema());
		sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  ");
		final Object[] queryParameters = new Object[] {idcompany,idbranch};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetRegistrasiApps(), queryParameters);
	}

	@Override
	public boolean checkKeyApps(Long idcompany, Long idbranch, String keyApps) {
		// TODO Auto-generated method stub
		return false;
	}

}
