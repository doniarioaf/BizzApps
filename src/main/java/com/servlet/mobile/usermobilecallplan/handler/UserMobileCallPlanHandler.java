package com.servlet.mobile.usermobilecallplan.handler;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlan;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanData;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanPK;
import com.servlet.mobile.usermobilecallplan.mapper.GetUserMobileCallPlanByIdUser;
import com.servlet.mobile.usermobilecallplan.repo.UserMobileCallPlanRepo;
import com.servlet.mobile.usermobilecallplan.service.UserMobileCallPlanService;
import com.servlet.shared.ReturnData;

@Service
public class UserMobileCallPlanHandler implements UserMobileCallPlanService{
	@Autowired
	private UserMobileCallPlanRepo repository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Object saveUserMobileCallPlan(UserMobileCallPlanPK userMobileCallPlanPK) {
		// TODO Auto-generated method stub
		UserMobileCallPlan table = new UserMobileCallPlan();
		table.setUserMobileCallPlanPK(userMobileCallPlanPK);
		UserMobileCallPlan returntable = repository.saveAndFlush(table);
		ReturnData data = new ReturnData();
		data.setId(returntable.getUserMobileCallPlanPK().getIdusermobile());
		return data;
	}
	@Override
	public Object saveUserMobileCallPlanList(List<UserMobileCallPlan> list) {
		// TODO Auto-generated method stub
		if(list.size() > 0) {
			repository.saveAllAndFlush(list);
		}
		return null;
	}
	@Override
	public Collection<UserMobileCallPlanData> getListUserMobileCallPlan(long idusermobile) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetUserMobileCallPlanByIdUser().schema());
		sqlBuilder.append(" where mccp.idusermobile = ? ");
		final Object[] queryParameters = new Object[] { idusermobile };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetUserMobileCallPlanByIdUser(), queryParameters);
	}
	@Override
	public Object deleteAllUserMobileCallPlanByListPK(List<UserMobileCallPlanPK> listPK) {
		// TODO Auto-generated method stub
		if(listPK.size() > 0) {
			repository.deleteAllById(listPK);
		}
		return null;
	}
	

}
