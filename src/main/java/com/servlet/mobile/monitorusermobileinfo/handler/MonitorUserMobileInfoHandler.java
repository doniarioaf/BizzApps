package com.servlet.mobile.monitorusermobileinfo.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.mobile.monitorusermobileinfo.entity.DetailInfo;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfo;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfoPK;
import com.servlet.mobile.monitorusermobileinfo.mapper.GetDistinctInfoId;
import com.servlet.mobile.monitorusermobileinfo.mapper.GetListInfoByInfoHeader;
import com.servlet.mobile.monitorusermobileinfo.mapper.GetMonitorUserMobileByIdMonitor;
import com.servlet.mobile.monitorusermobileinfo.repo.MonitorUserMobileInfoRepo;
import com.servlet.mobile.monitorusermobileinfo.service.MonitorUserMobileInfoService;

@Service
public class MonitorUserMobileInfoHandler implements MonitorUserMobileInfoService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MonitorUserMobileInfoRepo repository;
	
	@Override
	public Object saveMonitorUserMobileInfoList(List<MonitorUserMobileInfo> list) {
		// TODO Auto-generated method stub
		if(list.size() > 0) {
			repository.saveAllAndFlush(list);
		}
		return null;
	}

	@Override
	public List<MonitorUserMobileInfo> getListData(long idmonitoruser) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetMonitorUserMobileByIdMonitor().schema());
		sqlBuilder.append(" where data.idmonitorusermobile = ? ");
		final Object[] queryParameters = new Object[] { idmonitoruser };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetMonitorUserMobileByIdMonitor(), queryParameters);
		
	}

	@Override
	public Object deleteAllMonitorUserMobileInfoByListPK(List<MonitorUserMobileInfoPK> listPK) {
		// TODO Auto-generated method stub
		if(listPK.size() > 0) {
			repository.deleteAllById(listPK);
		}
		return null;
	}

	@Override
	public List<Long> getListDistinctUserMobileInfo(long idmonitoruser) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDistinctInfoId().schema());
		sqlBuilder.append(" where data.idmonitorusermobile = ? ");
		final Object[] queryParameters = new Object[] { idmonitoruser };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDistinctInfoId(), queryParameters);
	}

	@Override
	public List<DetailInfo> getDetailInfo(long infoid,long idmonitoruser) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetListInfoByInfoHeader().schema());
		sqlBuilder.append(" where minfo.infoid = ? and minfo.idmonitorusermobile = ?");
		final Object[] queryParameters = new Object[] { infoid ,idmonitoruser};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetListInfoByInfoHeader(), queryParameters);
	}

}
